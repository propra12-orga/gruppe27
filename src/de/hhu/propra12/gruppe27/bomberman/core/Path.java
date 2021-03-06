package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.io.Serializable;

/**
 * Klasse Path wird erstellt extends Klasse AbstractFeld
 * @author gruppe 27
 * @version 1.0 
 * 
 * 
 */

public class Path extends AbstractFeld implements Serializable {

	private static final long serialVersionUID = 1L;

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
	 * Farbe Weiss wird uebergeben
	 */

	public Color getColor() {
		return Color.white;
	}

}
