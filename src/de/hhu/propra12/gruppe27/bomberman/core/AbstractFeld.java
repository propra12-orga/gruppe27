package de.hhu.propra12.gruppe27.bomberman.core;

public abstract class AbstractFeld {

	// Feld begehbar
	public static boolean frei;

	// Feld zerstörbar
	public static boolean zerstoer;

	public abstract char toChar();

}
