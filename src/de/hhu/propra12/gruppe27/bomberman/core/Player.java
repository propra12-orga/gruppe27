package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.event.KeyEvent;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class Player extends AbstractPlayer {

	// Konstruktor
	public Player(int posx, int posy, String pname, Spielfeld owner,
			int playerWER) {
		alive = true;
		this.posx = posx;
		this.posy = posy;
		bombstr = 2;
		bombanz = 1;
		name = pname;
		pleft = false;
		pright = false;
		pup = false;
		pdown = false;
		plant = false;
		bombcount = 3;
		this.owner = owner;

		this.playerWER = playerWER;
	}

	/*
	 * TODO kann gelöscht werden, ist jetzt in AbstractPlayer public boolean
	 * isAlive() { return alive; }
	 * 
	 * public int getX() { return posx; }
	 * 
	 * public int getY() { return posy; }
	 */

	//
	public void update(int keycode, boolean pressed) {

		if (1 == playerWER) {
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

		if (2 == playerWER) {
			switch (keycode) {
			case KeyEvent.VK_I:
				pup = pressed;
				break;
			case KeyEvent.VK_J:
				pleft = pressed;
				break;
			case KeyEvent.VK_K:
				pdown = pressed;
				break;
			case KeyEvent.VK_L:
				pright = pressed;
				break;
			case KeyEvent.VK_O: {
				plant = pressed;
				break;
			}
			default:
				break;
			}
		}

	}

	/*
	 * TODO kann gelöscht werden, ist im AbstractPlayer public void move() { if
	 * (pup) { if (owner.getFeld(posx, posy - 1).isFrei()) posy--; } else if
	 * (pleft) { if (owner.getFeld(posx - 1, posy).isFrei()) posx--; } else if
	 * (pdown) { if (owner.getFeld(posx, posy + 1).isFrei()) posy++; } else if
	 * (pright) { if (owner.getFeld(posx + 1, posy).isFrei()) posx++; } else if
	 * (plant) { if (bombcount > 0) { owner.b1 = new Bomb(this, 1, 7);
	 * bombcount--; } } ; }
	 */

}