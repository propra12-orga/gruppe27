package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

public abstract class AbstractFeld {
	// (?)sollte ein feld seine eigenen koordinaten kennen? vllt sinnvoll um
	// später
	// einfacher nachbarfelder abzufragen(?) in dem fall zusätzliche methoden
	// nord/sued/ost/west o.ä.
	// Feld begehbar
	public static boolean frei;

	// Feld zerstörbar
	public static boolean zerstoer;

	private Color c;

	public abstract char toChar();// bis noch kein gui da ist...

	// public Image im;

	public Color getColor() {// später ersetzen durch bildausgabe
		return c;
	}

	public abstract void explodeOn(); // bombe explodiert an stelle

	public abstract AbstractFeld destroy();// von bombenexplosion betroffen

}
