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

	private boolean bombplanted;

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
	public int getX() {
		return posx;
	}

	public int getY() {
		return posy;
	}

	public Color getColor() {// später ersetzen durch bildausgabe
		return c;
	}

	public final AbstractFeld destroy(int str, int direction) {
		if (zerstoer) {
			System.out.println("dest");
			return new Path(posx, posy, owner);
		} else {
			if (str > 1) {
				switch (direction) {
				case DIR_TOP:
					owner.setFeld((owner.getFeld(posx - 1, posy).destroy(
							str - 1, DIR_RIGHT)), posx - 1, posy);
					break;
				case DIR_LEFT:
					owner.setFeld((owner.getFeld(posx, posy - 1).destroy(
							str - 1, DIR_RIGHT)), posx, posy - 1);
				case DIR_BOTTOM:
					owner.setFeld((owner.getFeld(posx + 1, posy).destroy(
							str - 1, DIR_RIGHT)), posx + 1, posy);
					break;
				case DIR_RIGHT:
					owner.setFeld((owner.getFeld(posx, posy + 1).destroy(
							str - 1, DIR_RIGHT)), posx, posy + 1);
					break;
				default:
					break;
				}
			}
			return (this);
		}
	}

	public void explodeOn(int str) {
		owner.getFeld(posx - 1, posy).destroy(str, DIR_TOP);
		owner.getFeld(posx, posy - 1).destroy(str, DIR_LEFT);
		owner.getFeld(posx + 1, posy).destroy(str, DIR_BOTTOM);
		owner.getFeld(posx + 1, posy).destroy(str, DIR_RIGHT);
		this.destroy(0, DIR_NULL);

	}

	public final boolean isFrei() {
		return frei;
	}

}
