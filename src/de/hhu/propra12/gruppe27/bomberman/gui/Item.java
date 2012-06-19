package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

public abstract class Item {

	public AbstractFeld Feld;
	public boolean canDestroy;
	Spielfeld pg;

	public abstract void draw(Graphics g);

	public void hit() {
		if (canDestroy)
			;// TODO

	}

}
