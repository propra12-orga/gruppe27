package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Color;
import java.awt.Graphics;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld2P;

public abstract class AbstractPlayer {

	boolean alive;

	boolean pleft, pright, pup, pdown, plant;

	int posx; // entspricht i im Array
	int posy; // enstpricht j im Array

	int bombdelay = 7;// explosionsverzögerung
	int bombstr; // Bombenst�rke 1 pro Feld
	int bombanz; // Bombenanzahl
	int bombcount;// Bombenanzahl aktuell
	int playerWER; // Welcher Spieler? 1 - 4

	private Color playercolor = new Color(0, 255, 0);
	Spielfeld owner;
	Spielfeld2P owner2;
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
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
			}
		}

	}

	public void move(int i) {
		if (pup) {
			if (owner2.getFeld(posx, posy - 1).isFrei())
				posy--;
		} else if (pleft) {
			if (owner2.getFeld(posx - 1, posy).isFrei())
				posx--;
		} else if (pdown) {
			if (owner2.getFeld(posx, posy + 1).isFrei())
				posy++;
		} else if (pright) {
			if (owner2.getFeld(posx + 1, posy).isFrei())
				posx++;
		} else if (plant) {
			if (bombcount > 0) {
				owner2.b1 = new Bomb(this, 1, 7);
				bombcount--;
			}
		}
	}

	public AbstractFeld getFeld() {
		return owner.getFeld(posx, posy);
	}

	public void hit() {
		alive = false;
	}

	// TODO überschreiben in abgeleiteten spielern mit bildausgabe an
	// entsprechender stelle
	public void draw(Graphics g) {
		if (alive) {
			g.setColor(playercolor);// zeichne spieler
			// g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 * 32);
			g.drawLine(posx * 32, posy * 32, posx * 32 + 32, posy * 32 + 32);
			g.drawLine(posx * 32 + 32, posy * 32, posx * 32, posy * 32 + 32);
		}
	}

}
