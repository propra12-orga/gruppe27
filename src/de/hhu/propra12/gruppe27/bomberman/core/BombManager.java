package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author
 * @version 1.0
 *  Klasse BombManager implementiert Serializable
 *  Erstellung einer Bomblist, um den Bestand zu kontrollieren
 * 
 */

public class BombManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Bomb> Bomblist;

	public BombManager(Spielfeld Owner) {
		Bomblist = new ArrayList<Bomb>();
	}

	/**
	 * Anzahl der Bomben wird ueberprueft, nur eine kann zur bestimmten Zeit benutzt/gelegt werden. 
	 * Sobald diese Bombe explodiert, kann eine weitere gelegt werden
	 */

	public void CheckBombs() {
		int i = 0;
		while (i < Bomblist.size()) {
			if (Bomblist.get(i).check()) {
				Bomblist.remove(i);
			} else
				i++;
		}
	}

	/**
	 * @param Feld
	 * Bombenanzahl = 1
	 * 
	 */

	public void hitBombs(AbstractFeld Feld) {
		for (int i = 0; i < Bomblist.size(); i++) {
			if (Feld == Bomblist.get(i).Feld) {
				Bomblist.get(i).hit();
			}
		}

	}

	/**
	 * 
	 * @return Bomblist is.Empty 
	 * Ueberpruefung der Bomblist
	 */

	public boolean isEmpty() {
		return Bomblist.isEmpty();
	}

	/**
	 * 
	 * @param b
	 * Bombe wird hinzugefuegt
	 */

	public void AddBomb(Bomb b) {
		Bomblist.add(b);
	}

	/**
	 * Bombe wird dargestellt
	 * 
	 * @param g
	 */

	public void paintBombs(Graphics g) {
		for (int i = 0; i < Bomblist.size(); i++) {
			Bomblist.get(i).draw(g);
		}
	}
}
