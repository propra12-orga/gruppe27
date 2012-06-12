package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.event.KeyEvent;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author 
 * @version
 * Klasse für Einstellungen von Spieler 1 und seine Eigenschaften
 */

public class Player1 extends AbstractPlayer {

	// Konstruktor
	public Player1(int posx, int posy, String pname, Spielfeld owner) {
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
	
	

	/*
	 * TODO kann gelÃ¶scht werden, ist jetzt in AbstractPlayer public boolean
	 * isAlive() { return alive; }
	 * 
	 * public int getX() { return posx; }
	 * 
	 * public int getY() { return posy; }
	 */
	
	/**
	 * Bewegung des Spielers
	 */

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

	/*
	 * public void move() { if (pup) { if (owner.getFeld(posx, posy -
	 * 1).isFrei()) posy--; } else if (pleft) { if (owner.getFeld(posx - 1,
	 * posy).isFrei()) posx--; } else if (pdown) { if (owner.getFeld(posx, posy
	 * + 1).isFrei()) posy++; } else if (pright) { if (owner.getFeld(posx + 1,
	 * posy).isFrei()) posx++; } else if (plant) { if (bombcount > 0) { owner.b1
	 * = new Bomb(this, 1, 7); bombcount--; } } ; }
	 */

}