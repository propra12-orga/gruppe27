package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteClient;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse AbstractPlayer Eigenschaften fuer die Bombe,Spieler und
 *          Feld werden initialisiert int posx entspricht i am Array, int posy
 *          entspricht y im Array in bobdelay ist zustaendig fuer die
 *          explosionsverzoegerung int bombstr ist gleich die Anzahl der
 *          Bombenstuecke pro Feld int bombanz ist gleich die Bombenanzahl, int
 *          bombcount ist die aktuelle Bombenanzahl int playerWER bestimmt den
 *          Spieler
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

	private Color playercolor = new Color(0, 255, 0);

	Spielfeld owner;
	SysEinst system = SysEinst.getSystem();
	SysEinstClient systemclient = SysEinstClient.getSystemClient();

	// int speed;

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

				// Wenn es ein Lanspiel ist, dann sollen Remotemethoden
				// aufgerufen werden
				moveifremote(direction);
			}
		}

		else if (direction == IRemoteClient.LEFT) {
			if (owner.getFeld(posx - 1, posy).isFrei())
				posx--;
			moveifremote(direction);
		} else if (direction == IRemoteClient.DOWN) {
			if (owner.getFeld(posx, posy + 1).isFrei())
				posy++;
			moveifremote(direction);

		} else if (direction == IRemoteClient.RIGHT) {
			if (owner.getFeld(posx + 1, posy).isFrei())
				posx++;
			moveifremote(direction);
		} else if (direction == IRemoteClient.BOMB) {
			if (bombcount > 0) {
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
				moveifremote(direction);
			}
		}

	}

	public void moveifremote(int direction) {

		System.out.println("moveifremote: " + direction);
		System.out.println("systemclient: " + systemclient.getboolClient());
		System.out.println("system boolLAN: " + system.getboolLAN());
		try {
			if (system.getboolLAN()) {

				if (systemclient.getboolClient()) {
					// Methode vom Host aufrufen
					systemclient.getRemoteHost().movep2c(direction);

				} else {
					// Methode vom Client aufrufen
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
			if (owner.getFeld(posx, posy - 1).isFrei())
				posy--;
		} else if (direction == Keyset.REMLEFT) {
			if (owner.getFeld(posx - 1, posy).isFrei())
				posx--;
		} else if (direction == Keyset.REMDOWN) {
			if (owner.getFeld(posx, posy + 1).isFrei())
				posy++;
		} else if (direction == Keyset.REMRIGHT) {
			if (owner.getFeld(posx + 1, posy).isFrei())
				posx++;
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
	 * @param g
	 *            Spieler wird gezeichnet wenn lebend
	 */

	public void draw(Graphics g) {
		if (alive) {
			g.setColor(playercolor);// zeichne spieler
			// g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 * 32);
			g.drawLine(posx * 32, posy * 32, posx * 32 + 32, posy * 32 + 32);
			g.drawLine(posx * 32 + 32, posy * 32, posx * 32, posy * 32 + 32);
		}
	}

}
