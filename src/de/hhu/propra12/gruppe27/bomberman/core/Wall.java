package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

// Feld - Aussenwände
public class Wall extends AbstractFeld {

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

	@Override
	public void explodeOn(int i) {

	}

}
