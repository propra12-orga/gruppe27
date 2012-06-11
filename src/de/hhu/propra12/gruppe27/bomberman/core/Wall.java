package de.hhu.propra12.gruppe27.bomberman.core;

/**
 * Ein Programm zur Erstellung der W�nde
 * Dies ist ein Javadoc Kommentar
 * @version 1.0
 */

import java.awt.Color;

// Feld - Aussenwände

/**
 * 
 * @author
 * @verison 1.0 Klasse zur Erstellung der nicht zerst�rbaren W�nde
 */
public class Wall extends AbstractFeld {

	private char testchar = 'W';

	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 *            Eigenschaften der W�nde (unzerst�rbar)
	 */

	public Wall(int x, int y, Level owner) {
		super(x, y, owner);
		frei = false;
		zerstoer = false;
	}

	/**
	 * @return Color
	 */

	@Override
	public Color getColor() {
		return Color.black;
	}

	public char toChar() {
		return testchar;

	}

}
