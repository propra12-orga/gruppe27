package de.hhu.propra12.gruppe27.bomberman.core;

/**
 * Ein Programm zur Erstellung der W�nde
 * Dies ist ein Javadoc Kommentar
 * @version 1.0
 */

import java.awt.Color;

// Feld - Aussenwände
public class Wall extends AbstractFeld {

	/**
	 * Hauptpgrogramm
	 * 
	 * @help
	 */
	private char testchar = 'W';

	public Wall(int x, int y, Level owner) {
		super(x, y, owner);
		frei = false;
		zerstoer = false;
	}

	@Override
	public Color getColor() {
		return Color.black;
	}

	public char toChar() {
		return testchar;

	}

}
