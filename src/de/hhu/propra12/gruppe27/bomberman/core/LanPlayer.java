package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.Host;

/**
 * 
 * @author
 * @version 1.0 Klasse LanPlayer und Bestimmung der Faehigkeiten
 */

public class LanPlayer extends AbstractPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Keyset Keys;
	public int index = -1;	//playerindex in playerList...wichtig f�r client/host
	public Host h=null;

	public LanPlayer(int posx, int posy, String pname, Spielfeld owner,
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

	/**
	 * Bewegungseinstellungen
	 */

	public void update(int keycode, boolean pressed) {
//		System.out.println("lanplayerupdate");
		if (alive) {
			if (keycode == Keys.KeyUp) {
				pup = pressed;
				h.hostKeyUpdate(index, keycode, pressed);	
			}
			if (keycode == Keys.KeyLeft) {
				pleft = pressed;
				h.hostKeyUpdate(index, keycode, pressed);
			}
			if (keycode == Keys.KeyDown) {
				pdown = pressed;
				h.hostKeyUpdate(index, keycode, pressed);
			}
			if (keycode == Keys.KeyRight) {
				pright = pressed;
				h.hostKeyUpdate(index, keycode, pressed);
			}
			if (keycode == Keys.KeyBomb) {
				plant = pressed;
				h.hostKeyUpdate(index, keycode, pressed);
			}
		}
	}

	/**
	 * Bewegung des Spielers auf Spielfeldern ohne feste Bloecke
	 */

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

	public Keyset getKeyset() {
		return Keys;
	}

	public void setKeyset(Keyset k) {
		this.Keys = k;
	}

}
