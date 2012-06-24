package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

/**
 * 
 * @author
 * @version 1.0 Klasse zur Erstellung eines Feldes
 */

public abstract class AbstractFeld {
	public static final int DIR_NULL = 0;
	public static final int DIR_TOP = 1;
	public static final int DIR_LEFT = 2;
	public static final int DIR_RIGHT = 3;
	public static final int DIR_BOTTOM = 4;

	// (?)sollte ein feld seine eigenen koordinaten kennen? vllt sinnvoll um
	// später
	// einfacher nachbarfelder abzufragen(?) in dem fall zusätzliche methoden
	// nord/sued/ost/west o.ä.
	// Feld begehbar

	/**
	 * Feld begehbar
	 */

	protected boolean frei;

	/**
	 * Feld zerst�rbar
	 */

	// Feld zerstörbar
	protected boolean zerstoer;

	// TODO private boolean bombplanted;

	private int posx, posy;

	private Color c;

	Level owner;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 */

	public AbstractFeld(int x, int y, Level owner) {
		posx = x;
		posy = y;
		this.owner = owner;

	}

	public abstract char toChar();// bis noch kein gui da ist...

	/**
	 * 
	 * @return posyx
	 */

	// public Image im;
	public int getX() {
		return posx;
	}

	/**
	 * 
	 * @return posy
	 */

	public int getY() {
		return posy;
	}

	/**
	 * 
	 * @return c
	 */

	public Color getColor() {// später ersetzen durch bildausgabe
		return c;
	}

	/**
	 * 
	 * @return frei
	 */

	public final boolean isFrei() {
		return frei;
	}

	
  /**
   * 
   * @return this
   * Feld oben
   */


	public final boolean isZerstoer() {
		return zerstoer;
	}

	/**
	 * 
	 * @return this Abstract Feld oben
	 */



	public AbstractFeld top() {
		if (posy > 0)
			return owner.getFeld(posx, posy - 1);
		else
			return this;

	}

	/**
	 * 
	 * @return this
	 * Feld links
	 * @return this Abstract Feld links
	 * 
	 */

	public AbstractFeld left() {

		if (posx > 0)
			return owner.getFeld(posx - 1, posy);
		else
			return this;

	}

	/**
	 * 
	 * @return this
	 * Feld rechts
	 * @return this Abstract Feld rechts

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
	 * @return this
	 * Feld Mitte

	 * @return this Abstract Feld Mitte

	 */

	public AbstractFeld bottom() {
		if (posx <= owner.laenge + 1)
			return owner.getFeld(posx, posy + 1);
		else
			return this;

	}

}