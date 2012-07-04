package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.io.Serializable;

/**
 * 
 * @author Gruppe 27
 * @version 1.0
 *  Die Klasse dient der Erstellung von Bloecken
 *  Ist eine Unterklasse von AbstractFeld und implementiert Serializable
 */

public class Block extends AbstractFeld implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 * Erstellung eines zerstoerbaren Blocks, die auf dem Feld liegen 
	 */

	public Block(int x, int y, Level owner) {
		super(x, y, owner);
		frei = false;
		zerstoer = true;
		
	}

	/**
	 * @return Color
	 * Farbe des Blocks = rot
	 */

	@Override
	public Color getColor() {
		return Color.red;
	}

	@Override
	public char toChar() {
	
		return 'B';
	}

}
