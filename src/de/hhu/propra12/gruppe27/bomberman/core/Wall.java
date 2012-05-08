package de.hhu.propra12.gruppe27.bomberman.core;

// Feld - Aussenwände
public class Wall extends AbstractFeld {

	static boolean frei = false;
	static boolean zerstoer = false;
	private char testchar;

	public Wall(char c) {
		testchar = c;

	}

	public char toChar() {
		return testchar;

	}
}
