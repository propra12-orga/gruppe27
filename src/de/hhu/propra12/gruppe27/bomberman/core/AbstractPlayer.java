package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteClient;

/**
 * Klasse AbstractPlayer Eigenschaften fuer die Bombe,Spieler und
 * Feld werden initialisiert int posx entspricht i am Array, int posy
 * entspricht y im Array in bobdelay ist zustaendig fuer die
 * explosionsverzoegerung int bombstr ist gleich die Anzahl der
 * Bombenstuecke pro Feld int bombanz ist gleich die Bombenanzahl, int
 * bombcount ist die aktuelle Bombenanzahl int playerWER bestimmt den
 * Spieler
 * @author gruppe 27
 * @version 1.0 
 * 
 */

public abstract class AbstractPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	boolean alive;

	boolean pleft, pright, pup, pdown, plant;

	int posx;
	int posy;

	int bombdelay = 7;
	int bombstr;
	int bombanz;
	int bombcount;
	int playerWER;
	int countthesteps = 0;

	private Color playercolor = new Color(0, 255, 0);

	Spielfeld owner;
	SysEinst system = SysEinst.getSystem();

	String name;

	/**
	 * 
	 * @return playercolot, die Farbe des Spielers wird bestimmt
	 */

	public Color getPlayercolor() {
		return playercolor;
	}

	/**
	 * 
	 * @param playercolor
	 *            playercolor wird uebergeben
	 */

	public void setPlayercolor(Color playercolor) {
		this.playercolor = playercolor;
	}

	/**
	 * Werte werden an die Methode zurueckgeliefert
	 * 
	 * @param playercolor
	 * @return this
	 */

	public AbstractPlayer withColor(Color playercolor) {
		this.playercolor = playercolor;
		return this;
	}

	/**
	 * 
	 * @return alive Ueberpruefung ob Spieler lebt
	 */

	public boolean isAlive() {
		return alive;
	}

	/**
	 * 
	 * @return posx Position x wird bestimmt
	 */

	public int getX() {
		return posx;
	}

	/**
	 * Parameter fuer Position y werden uebergeben
	 * @param posy
	 */

	public void setY(int posy) {
		this.posy = posy;
	}

	/**
	 * Parameter fuer Position x werden uebergeben
	 * @param posx
	 */

	public void setX(int posx) {
		this.posx = posx;
	}

	/**
	 * 
	 * @return posy Position y wird bestimmt
	 */

	public int getY() {
		return posy;
	}

	/**
	 * 
	 * @param keycode
	 * @param pressed
	 *            Boolean und Keycode werden aktualisiert
	 */

	abstract public void update(int keycode, boolean pressed);

	/**
	 * Bewegung des Spielers (Client) und Platzierung der Bombe Recht, links,
	 * runter , hoch und Bombe
	 * 
	 */

	public void move() {
		if (pup) {
			move(IRemoteClient.UP);
		} else if (pleft) {
			move(IRemoteClient.LEFT);
		} else if (pdown) {
			move(IRemoteClient.DOWN);
		} else if (pright) {
			move(IRemoteClient.RIGHT);
		} else if (plant) {
			move(IRemoteClient.BOMB);
		}
	}

	/**
	 * 
	 * @param direction
	 *            Konstanten siehe IRemoteClient
	 */

	public void move(int direction) {

		if (direction == IRemoteClient.UP) {
			if (owner.getFeld(posx, posy - 1).isFrei()) {
				posy--;
				countthesteps++;
				// Wenn es ein Lanspiel ist, dann sollen Remotemethoden
				// aufgerufen werden
				moveifremote(direction);

			}
		}

		else if (direction == IRemoteClient.LEFT) {
			if (owner.getFeld(posx - 1, posy).isFrei()) {
				posx--;
				countthesteps++;
				moveifremote(direction);
			}
		} else if (direction == IRemoteClient.DOWN) {
			if (owner.getFeld(posx, posy + 1).isFrei()) {
				posy++;
				countthesteps++;
				moveifremote(direction);
			}

		} else if (direction == IRemoteClient.RIGHT) {
			if (owner.getFeld(posx + 1, posy).isFrei()) {
				posx++;
				countthesteps++;
				moveifremote(direction);
			}
		} else if (direction == IRemoteClient.BOMB) {
			if (bombcount > 0) {
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
				moveifremote(direction);
			}
		}

	}
	
	/**
	 * Parameter fuer direction wird uebergeben
	 * Die Methode wird bei if vom Host aufgerufen und bei else vom Client
	 * @param direction
	 */

	public void moveifremote(int direction) {

		System.out.println("moveifremote: " + direction);
		System.out.println("system: " + system.getboolClient());
		System.out.println("system boolLAN: " + system.getboolLAN());
		try {
			if (system.getboolLAN()) {

				if (system.getboolClient()) {
					
					system.getRemoteHost().movep2c(direction);

				} else {
					
					system.getRemoteClient().movep2h(direction);
				}
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param direction
	 *            Konstanten siehe IRemoteClient
	 */

	public void moveremote(int direction) {
		System.out.println("moveremote: " + direction);
		if (direction == Keyset.REMUP) {
			if (owner.getFeld(posx, posy - 1).isFrei()) {
				posy--;
				countthesteps++;
			}
		} else if (direction == Keyset.REMLEFT) {
			if (owner.getFeld(posx - 1, posy).isFrei()) {
				posx--;
				countthesteps++;
			}
		} else if (direction == Keyset.REMDOWN) {
			if (owner.getFeld(posx, posy + 1).isFrei()) {
				posy++;
				countthesteps++;
			}
		} else if (direction == Keyset.REMRIGHT) {
			if (owner.getFeld(posx + 1, posy).isFrei()) {
				posx++;
				countthesteps++;
			}
		} else if (direction == Keyset.REMBOMB) {
			if (bombcount > 0) {
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
			}
		}

	}

	/**
	 * @return owner.getFeld Positionen x und y werden aus an die aufrufende
	 *         Methode zrueckgegeben
	 */

	public AbstractFeld getFeld() {
		return owner.getFeld(posx, posy);
	}

	/**
	 * Sterben des Spielers und Ausgabe des Namens
	 */

	public void hit() {
		System.out.println(name + "tot!");
		alive = false;
	}

	// TODO überschreiben in abgeleiteten spielern mit bildausgabe an
	// entsprechender stelle

     /**
	 * 
	 * 
	 * Spieler wird gezeichnet wenn lebend
	 * Bilder werden eingefuegt           
	 */


	private Image imgSpieler1 = Toolkit
			.getDefaultToolkit()
			.getImage(
					"src/de/hhu/propra12/gruppe27/bomberman/graphics/Spielfigurblau.gif");
	private Image imgSpieler2 = Toolkit
			.getDefaultToolkit()
			.getImage(
					"src/de/hhu/propra12/gruppe27/bomberman/graphics/Spielfigurrot.gif");

	public void draw(Graphics g, int playerNumber) {
		if (alive) {

			if (playerNumber == 0) {
				g.drawImage(imgSpieler1, posx * 32, posy * 32, 32, 32, owner);
			}
			if (playerNumber == 1) {
				g.drawImage(imgSpieler2, posx * 32, posy * 32, 32, 32, owner);
			}
			// g.setColor(playercolor);// zeichne spieler
			// // g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 * 32);
			// g.drawLine(posx * 32, posy * 32, posx * 32 + 32, posy * 32 + 32);
			// g.drawLine(posx * 32 + 32, posy * 32, posx * 32, posy * 32 + 32);

			// g.drawImage(imagezerwand, e.getX() * 32, e.getY() * 32, 32, 32,
			// owner);
		}
	}

	/**
	 * 
	 * @return countthesteps Schritte des Spielers werden fuer den Highscore
	 *         gezaehlt
	 */

	public int getCountthesteps() {
		return countthesteps;
	}

	/**
	 * Countthestpes werden werden aus Konstruktur aufgerufen
	 * 
	 * @param countthesteps
	 */

	public void setCountthesteps(int countthesteps) {
		this.countthesteps = countthesteps;
	}

	/**
	 * 
	 * @return name name wird aufgerufen
	 */

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            Parameter name wird uebergeben
	 */

	public void setName(String name) {
		this.name = name;
	}

}
