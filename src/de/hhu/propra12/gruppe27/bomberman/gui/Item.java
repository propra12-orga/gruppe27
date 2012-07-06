package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;


import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
/**
 * 
 * @author Gruppe 27
 * @version 1.0
 * Klasse zur Erstellung eines Items
 *
 */

public abstract class Item {
	
	/**
	 * kann Waende zerstoeren
	 */

	public AbstractFeld Feld;
	public boolean canDestroy;
	Spielfeld pg;
	
	/**
	 * 
	 * @param g
	 * Grafiken bei Zerstoerung
	 */

	public abstract void draw(Graphics g);

	public void hit() {
		if (canDestroy)
			;// TODO

	}

}
