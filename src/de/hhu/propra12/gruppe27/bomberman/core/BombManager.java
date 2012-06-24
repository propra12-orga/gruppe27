package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;

import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author 
 * @version 1.0
 * Klasse BombManager 
 * 
 */

public class BombManager {
	private ArrayList<Bomb> Bomblist;

	public BombManager(Spielfeld Owner) {
		Bomblist = new ArrayList<Bomb>();
	}
	
	/**
	 * Anzahl der Bomben wird überprüft
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
	 * Überprüfung der Bomblist
	 */

	public boolean isEmpty() {
		return Bomblist.isEmpty();
	}

	/**
	 * 
	 * @param b
	 * Bombe wird hinzugefügt
	 */
	
	public void AddBomb(Bomb b) {
		Bomblist.add(b);
	}

	/**
	 * Bombe wird dargestellt
	 * @param g
	 */
	
	public void paintBombs(Graphics g) {
		for (int i = 0; i < Bomblist.size(); i++) {
			Bomblist.get(i).draw(g);
		}
	}
}
