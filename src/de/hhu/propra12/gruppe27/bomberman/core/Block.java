package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;

public class Block extends AbstractFeld {

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
