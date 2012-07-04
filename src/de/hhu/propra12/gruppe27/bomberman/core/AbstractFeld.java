package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.io.Serializable;

/**
 * 
 * @author Gruppe 27
 * @version 1.0
 * Klasse zur Erstellung eines Feldes
 * Koordinaten des Spielfeldes werden bestimmt, Feld kennt die eigenen Koordinaten
 */

public abstract class AbstractFeld implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int DIR_NULL = 0;
	public static final int DIR_TOP = 1;
	public static final int DIR_LEFT = 2;
	public static final int DIR_RIGHT = 3;
	public static final int DIR_BOTTOM = 4;



	/**
	 * Wenn boolean frei, dann Feld begehbar
	 */

	protected boolean frei;

	/**
	 * Wenn boolean zerstoer, dann Feld zerstoerbar
	 */

	
	protected boolean zerstoer;


	private int posx, posy;

	private Color c;

	Level owner;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 * Positionen auf dem Feld: x bzw. y
	 */

	public AbstractFeld(int x, int y, Level owner) {
		posx = x;
		posy = y;
		this.owner = owner;

	}

	public abstract char toChar();

	/**
	 * 
	 * @return posyx
	 * Position x auf dem Feld
	 * 
	 */

	// public Image im;
	public int getX() {
		return posx;
	}

	/**
	 * 
	 * @return posy
	 * Position y auf dem Feld
	 * 
	 */

	public int getY() {
		return posy;
	}

	/**
	 * 
	 * @return c
	 * Farbe des Feldes
	 */

	public Color getColor() {
		return c;
	}

	/**
	 * 
	 * @return frei
	 * Position auf dem Feld ist frei
	 */

	public final boolean isFrei() {
		return frei;
	}

	/**
	 * 
	 * @return this Feld oben
	 * Feld ist zerstoerbar
	 */

	public final boolean isZerstoer() {
		return zerstoer;
	}

	/**
	 * 
	 * @return this Abstract Feld oben
	 * Die Figur bewegt sich nach oben, wenn Position y > 0 
	 * 
	 */

	public AbstractFeld top() {
		if (posy > 0)
			return owner.getFeld(posx, posy - 1);
		else
			return this;

	}

	/**
	 * 
	 * @return this Feld links
	 * @return this Abstract Feld links
	 * Die Figur bewegt sich nach links, wenn Position x > 0
	 */

	public AbstractFeld left() {

		if (posx > 0)
			return owner.getFeld(posx - 1, posy);
		else
			return this;

	}

	/**
	 * 
	 * @return this Feld rechts
	 * @return this Abstract Feld rechts
	 * Die Figur bewegt sich nach rechts, wenn Position x + 1
	 * 
	 */

	public AbstractFeld right() {
		if (posx < owner.breite - 1)
			return owner.getFeld(posx + 1, posy);
		else
			return this;

	}

	/**
	 * 
	 * @return this Feld Mitte
	 * 
	 * @return this Abstract Feld Mitte
	 * Die Figur bewegt sich nach unten, wenn Position y + 1
	 */

	public AbstractFeld bottom() {
		if (posx <= owner.laenge + 1)
			return owner.getFeld(posx, posy + 1);
		else
			return this;

	}

}