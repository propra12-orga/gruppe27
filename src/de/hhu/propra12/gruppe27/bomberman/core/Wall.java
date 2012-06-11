package de.hhu.propra12.gruppe27.bomberman.core;

/**
 * Ein Programm zur Erstellung der Wände
 * Dies ist ein Javadoc Kommentar
 * @version 1.0
 */

import java.awt.Color;

// Feld - AussenwÃ¤nde

/**
 * 
 * @author 
 * @verison 1.0
 *Klasse zur Erstellung der nicht zerstörbaren Wände
 */
public class Wall extends AbstractFeld {


	private char testchar = 'W';
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 * Eigenschaften der Wände (unzerstörbar)
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

	@Override
	public void explodeOn(int i) {

	}

}
