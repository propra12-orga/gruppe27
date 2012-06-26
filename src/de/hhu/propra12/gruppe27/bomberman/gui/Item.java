package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;


import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
/**
 * 
 * @author
 * @version 1.0
 * Erstellung eines Items
 *
 */

public abstract class Item {
	
	/**
	 * kann Wände zerstören
	 */

	public AbstractFeld Feld;
	public boolean canDestroy;
	Spielfeld pg;

	public abstract void draw(Graphics g);

	public void hit() {
		if (canDestroy)
			;// TODO

	}

}
