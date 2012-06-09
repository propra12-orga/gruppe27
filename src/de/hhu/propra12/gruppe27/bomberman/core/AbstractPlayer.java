package de.hhu.propra12.gruppe27.bomberman.core;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public abstract class AbstractPlayer {

	boolean alive;

	boolean pleft, pright, pup, pdown, plant;

	int posx; // entspricht i im Array
	int posy; // enstpricht j im Array

	int bombstr; // Bombenst�rke 1 pro Feld
	int bombanz; // Bombenanzahl
	int bombcount;// Bombenanzahl aktuell

	Spielfeld owner;
	// int speed;

	String name;

	public boolean isAlive() {
		return alive;
	}

	public int getX() {
		return posx;
	}

	public int getY() {
		return posy;
	}

	public void update(int keycode, boolean pressed) {
	}

	public void move() {
		if (pup) {
			if (owner.getFeld(posx, posy - 1).isFrei())
				posy--;
		} else if (pleft) {
			if (owner.getFeld(posx - 1, posy).isFrei())
				posx--;
		} else if (pdown) {
			if (owner.getFeld(posx, posy + 1).isFrei())
				posy++;
		} else if (pright) {
			if (owner.getFeld(posx + 1, posy).isFrei())
				posx++;
		} else if (plant) {
			if (bombcount > 0) {
				owner.b1 = new Bomb(this, 1, 7);
				bombcount--;
			}
		}
		;
	}

}
