package de.hhu.propra12.gruppe27.bomberman.gui;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

public class Special {

	private AbstractFeld Feld;

	public Special(AbstractFeld Feld) {
		this.Feld = Feld;

	}

	public int getX() {
		// return posx;
		return Feld.getX();
	}

	public int getY() {
		// return posx;
		return Feld.getY();
	}

}
