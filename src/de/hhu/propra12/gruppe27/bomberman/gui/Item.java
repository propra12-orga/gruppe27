package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;


import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
/**
 * Klasse zur Erstellung eines Items 
 * @author Gruppe 27
 * @version 1.0
 *
 */

public abstract class Item {
	
	/**
	 * Waende koennen zerstoert werden
	 */

	public AbstractFeld Feld;
	public boolean canDestroy;
	Spielfeld pg;
	
	/**
	 * Parameter g wird uebergeben
	 * @param g
	 * Grafiken bei Zerstoerung
	 */

	public abstract void draw(Graphics g);

	public void hit() {
		if (canDestroy)
			;// TODO

	}

}
