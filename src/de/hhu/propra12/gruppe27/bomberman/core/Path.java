package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

/**
 * 
 * @author 
 * @version 1.0
 * Klasse Path wird erstellt
 *
 */

public class Path extends AbstractFeld {
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 * Eigenschaften von Path werden bestimmt
	 * 
	 */

	public Path(int x, int y, Level owner) {
		super(x, y, owner);
		frei = true;
		zerstoer = true;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return ' '
	 */

	@Override
	public char toChar() {
		// TODO Auto-generated method stub
		return ' ';
	}
	
	/**
	 * @return Color.white
	 */

	public Color getColor() {
		return Color.white;
	}

}
