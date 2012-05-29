package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.event.KeyEvent;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class Player {

	private boolean alive;

	boolean pleft, pright, pup, pdown, plant;

	private int posx; // entspricht i im Array
	private int posy; // enstpricht j im Array

	int bombstr; // Bombenst�rke 1 pro Feld
	int bombanz; // Bombenanzahl
	int bombcount;// Bombenanzahl aktuell

	Spielfeld owner;
	// int speed;

	String name;

	// Konstruktor
	public Player(int posx, int posy, String pname, Spielfeld owner) {
		alive = true;
		this.posx = posx;
		this.posy = posy;
		bombstr = 1;
		bombanz = 1;
		name = pname;
		pleft = false;
		pright = false;
		pup = false;
		pdown = false;
		plant = false;
		bombcount = 1;
		this.owner = owner;

	}

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
		switch (keycode) {
		case KeyEvent.VK_W:
			pup = pressed;
			break;
		case KeyEvent.VK_A:
			pleft = pressed;
			break;
		case KeyEvent.VK_S:
			pdown = pressed;
			break;
		case KeyEvent.VK_D:
			pright = pressed;
			break;
		case KeyEvent.VK_SPACE: {
			plant = pressed;
			break;
		}
		default:
			break;
		}

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