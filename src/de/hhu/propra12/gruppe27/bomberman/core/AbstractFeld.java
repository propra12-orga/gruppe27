package de.hhu.propra12.gruppe27.bomberman.core;

public abstract class AbstractFeld {
	// (?)sollte ein feld seine eigenen koordinaten kennen? vllt sinnvoll um
	// später
	// einfacher nachbarfelder abzufragen(?) in dem fall zusätzliche methoden
	// nord/sued/ost/west o.ä.
	// Feld begehbar
	public static boolean frei;

	// Feld zerstörbar
	public static boolean zerstoer;

	public abstract char toChar();// bis noch kein gui da ist...

}
