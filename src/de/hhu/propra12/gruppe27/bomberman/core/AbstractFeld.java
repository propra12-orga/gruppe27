package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

public abstract class AbstractFeld {
	public static final int DIR_TOP = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_BOTTOM = 3;

	// (?)sollte ein feld seine eigenen koordinaten kennen? vllt sinnvoll um
	// später
	// einfacher nachbarfelder abzufragen(?) in dem fall zusätzliche methoden
	// nord/sued/ost/west o.ä.
	// Feld begehbar
	public boolean frei;

	// Feld zerstörbar
	public static boolean zerstoer;

	private int posx, posy;

	private Color c;

	private Level owner;

	public AbstractFeld(int x, int y, Level owner) {
		posx = x;
		posy = y;
		this.owner = owner;
	}

	public abstract char toChar();// bis noch kein gui da ist...

	// public Image im;

	public Color getColor() {// später ersetzen durch bildausgabe
		return c;
	}

	public final AbstractFeld destroy(int str, int direction) {
		if (zerstoer)
			return new Path(posx, posy, owner);
		else {
			if (str > 1) {
				switch (direction) {
				case DIR_TOP:
					owner.getFeld(posx - 1, posy).destroy(str - 1, DIR_TOP);
					break;
				case DIR_LEFT:
					owner.getFeld(posx, posy - 1).destroy(str - 1, DIR_LEFT);
					break;
				case DIR_BOTTOM:
					owner.getFeld(posx + 1, posy).destroy(str - 1, DIR_BOTTOM);
					break;
				case DIR_RIGHT:
					owner.getFeld(posx, posy + 1).destroy(str - 1, DIR_RIGHT);
					break;
				default:
					break;
				}
			}
			return (this);
		}
	}

	public void explodeOn(int i) {

	}

}
