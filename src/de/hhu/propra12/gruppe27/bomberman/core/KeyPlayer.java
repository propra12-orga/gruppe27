package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * Klasse Keyplayers und Bestimmung der Faehigkeiten Unterklasse
 * vom AbstractPlayer, implementiert Serializable
 * @author Gruppe 27
 * @version 1.0 
 */

public class KeyPlayer extends AbstractPlayer implements Serializable {

	private static final long serialVersionUID = 1L;
	public Keyset Keys;

	/**
	 * 
	 * @param posx
	 * @param posy
	 * @param pname
	 * @param owner
	 * @param Movement
	 *            Der Spieler bewegt sich auf einem zweidimensionalen Feld mit
	 *            den Koordinaten y und x 2 sind die Bombenstuecke pro Feld Die
	 *            Bombenanzahl, die der Spieler besitzt, ist 1. Diese Bombe
	 *            explodiert innerhalb von 7 Sekunden. Die Bewegung des Spielers
	 *            wird mit der Tastatur gelenkt
	 * 
	 * 
	 */

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

	/**
	 * Bewegungseinstellungen Wenn Taste gedrueckt wird, wird an das Programm
	 * 'pressed' uebergeben
	 */

	public void update(int keycode, boolean pressed) {
		if (alive) {
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
	}

	/**
	 * Die Bewegung des Spieler auf dem Feld ohne festen Bloecken wird definiert
	 */

	public void move() {
		if (pup) {
			if (owner.getFeld(posx, posy - 1).isFrei()) {
				posy--;
				// if (system.getamplayer() == 1)
				countthesteps++;
			}
		} else if (pleft) {
			if (owner.getFeld(posx - 1, posy).isFrei()) {
				posx--;
				// if (system.getamplayer() == 1)
				countthesteps++;
			}
		} else if (pdown) {
			if (owner.getFeld(posx, posy + 1).isFrei()) {
				posy++;
				// if (system.getamplayer() == 1)
				countthesteps++;
			}
		} else if (pright) {
			if (owner.getFeld(posx + 1, posy).isFrei()) {
				posx++;
				// if (system.getamplayer() == 1)
				countthesteps++;
			}
		} else if (plant) {
			if (bombcount > 0) {
				owner.plantBomb(new Bomb(this, bombstr, bombdelay));
				bombcount--;
			}
		}

	}

}
