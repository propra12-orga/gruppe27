package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

// Feld - Aussenwände
public class Wall extends AbstractFeld {

	static boolean frei = false;
	static boolean zerstoer = false;
	private char testchar = 'W';

	public Wall() {

	}

	@Override
	public Color getColor() {
		return Color.black;
	}

	public char toChar() {
		return testchar;

	}
}
