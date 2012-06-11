package de.hhu.propra12.gruppe27.bomberman.core;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class KeyPlayer extends AbstractPlayer {
	private Keyset Keys;

	public KeyPlayer(int posx, int posy, String pname, Spielfeld owner,
			Keyset Movement) {
		alive = true;
		this.posx = posx;
		this.posy = posy;
		this.Keys = Movement;
		bombstr = 2;
		bombanz = 1;
		bombdelay = 7;
		name = pname;
		pleft = false;
		pright = false;
		pup = false;
		pdown = false;
		plant = false;
		bombcount = 3;
		this.owner = owner;
	}

	public void update(int keycode, boolean pressed) {
		if (keycode == Keys.KeyUp) {
			pup = pressed;
		}
		if (keycode == Keys.KeyLeft) {
			pleft = pressed;
		}
		if (keycode == Keys.KeyDown) {
			pdown = pressed;
		}
		if (keycode == Keys.KeyRight) {
			pright = pressed;
		}
		if (keycode == Keys.KeyBomb) {
			plant = pressed;
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
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
			}
		}

	}

}
