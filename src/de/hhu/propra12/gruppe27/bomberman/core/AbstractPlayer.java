package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

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
			if (owner.getFeld(posx, posy - 1).isFrei())
				posy--;
		} else if (pleft) {
			if (owner.getFeld(posx - 1, posy).isFrei())
				posx--;
		} else if (pdown) {
			if (owner.getFeld(posx, posy + 1).isFrei())
				posy++;
		} else if (pright) {
			if (owner.getFeld(posx + 1, posy).isFrei())
				posx++;
		} else if (plant) {
			if (bombcount > 0) {
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
			}
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
