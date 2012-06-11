package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

/**
 * 
 * @author 
 * @version 1.0
 * Die Klasse dient der Erstellung von Bl�cken
 */

public class Block extends AbstractFeld {

	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 * Erstellung eines zerst�rbaren Blocks
	 */
	
	public Block(int x, int y, Level owner) {
		super(x, y, owner);
		frei = false;
		zerstoer = true;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return Color
	 */

	@Override
	
	public Color getColor() {
		return Color.red;
	}

	@Override
	public char toChar() {
		// TODO Auto-generated method stub
		return 'B';
	}

}
