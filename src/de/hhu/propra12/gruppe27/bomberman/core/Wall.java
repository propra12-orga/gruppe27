package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.io.Serializable;

// Feld - Aussenwände

/**
 * 
 * @author
 * @verison 1.0 Klasse zur Erstellung der nicht zerstoerbaren Waende
 */
public class Wall extends AbstractFeld implements Serializable {

	private static final long serialVersionUID = 1L;
	private char testchar = 'W';

	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 *            Eigenschaften der Waende (unzerstoerbar)
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
