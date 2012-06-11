package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

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
	protected boolean frei;

	// Feld zerstörbar
	protected boolean zerstoer;

	// TODO private boolean bombplanted;

	private int posx, posy;

	private Color c;

	Level owner;

	public AbstractFeld(int x, int y, Level owner) {
		posx = x;
		posy = y;
		this.owner = owner;

	}

	public abstract char toChar();// bis noch kein gui da ist...

	// public Image im;
	public int getX() {
		return posx;
	}

	public int getY() {
		return posy;
	}

	public Color getColor() {// später ersetzen durch bildausgabe
		return c;
	}

	public final boolean isFrei() {
		return frei;
	}

	public AbstractFeld top() {
		if (posy > 0)
			return owner.getFeld(posx, posy - 1);
		else
			return this;

	}

	public AbstractFeld left() {

		if (posx > 0)
			return owner.getFeld(posx - 1, posy);
		else
			return this;

	}

	public AbstractFeld right() {
		if (posx < owner.breite - 1)
			return owner.getFeld(posx + 1, posy);
		else
			return this;

	}

	public AbstractFeld bottom() {
		if (posx <= owner.laenge + 1)
			return owner.getFeld(posx, posy + 1);
		else
			return this;

	}

}