package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteClient;

/**
 * 
 * @author
 * @version 1.0 Klasse AbstractPlayer Eigenschaften f�r die Bombe,Spieler und
 *          Feld
 * 
 */

public abstract class AbstractPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	boolean alive;

	boolean pleft, pright, pup, pdown, plant;

	int posx; // entspricht i im Array
	int posy; // enstpricht j im Array

	int bombdelay = 7;// explosionsverzögerung
	int bombstr; // Bombenst�rke 1 pro Feld
	int bombanz; // Bombenanzahl
	int bombcount;// Bombenanzahl aktuell
	int playerWER; // Welcher Spieler? 1 - 4

	public Color playercolor = new Color(0, 255, 0);
	Spielfeld owner;
	SysEinst system = SysEinst.getSystem();
	SysEinstClient systemclient = SysEinstClient.getSystemClient();

	// int speed;

	String name;

	/**
	 * 
	 * @return alive �berpr�fung ob Spieler lebt
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
	 */

	abstract public void update(int keycode, boolean pressed);

	/**
	 * Bewegung des Spielers und Platzierung der Bombe
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
		try {
			if (direction == IRemoteClient.UP) {
				if (owner.getFeld(posx, posy - 1).isFrei()) {
					posy--;

					// Wenn es ein Lanspiel ist, dann sollen Remotemethoden
					// aufgerufen werden
					if (system.getboolLAN()) {

						if (systemclient.getboolClient()) {
							// Methode vom Host aufrufen
							systemclient.getRemoteHost().movep2c(direction);
						} else {
							// Methode vom Client aufrufen
							system.getRemoteClient().movep2h(direction);
						}
					}
				}

				// TODO move player2 im 2ten system
			} else if (direction == IRemoteClient.LEFT) {
				if (owner.getFeld(posx - 1, posy).isFrei())
					posx--;
			} else if (direction == IRemoteClient.DOWN) {
				if (owner.getFeld(posx, posy + 1).isFrei())
					posy++;

				// Wenn es ein Lanspiel ist, dann sollen Remotemethoden
				// aufgerufen werden
				if (system.getboolLAN()) {
					system.printSysEinst();
					systemclient.printSysEinst();
					if (systemclient.getboolClient()) {
						// Methode vom Host aufrufen
						System.out.println("movep2");
						systemclient.getRemoteHost().movep2c(direction);
					} else {
						// Methode vom Client aufrufen
						System.out.println("sysref h:" + system);
						system.getRemoteClient().movep2h(direction);
					}
				}

			} else if (direction == IRemoteClient.RIGHT) {
				if (owner.getFeld(posx + 1, posy).isFrei())
					posx++;
			} else if (direction == IRemoteClient.BOMB) {
				if (bombcount > 0) {
					owner.plantBomb(new Bomb(this, bombstr, bombdelay));
					bombcount--;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return owner.getFeld
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
	 *            Spieler wird gezeichnet
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
