package de.hhu.propra12.gruppe27.bomberman.gui;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

/**
 * Klasse Special
 * @author gruppe 27
 * @version 1.0
 */

public class Special {

	private AbstractFeld Feld;

	public Special(AbstractFeld Feld) {
		this.Feld = Feld;

	}

	/**
	 * 
	 * @return Feld
	 * Position wird uebergeben
	 */
	
	public int getX() {
		
		return Feld.getX();
	}
/**
 * 
 * @return Feld
 * Posiotion wird uebergeben
 */
	
	public int getY() {
		
		return Feld.getY();
	}

}
