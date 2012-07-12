package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.Host;

/**
 * Klasse LanPlayer und Bestimmung der Faehigkeiten Ist eine
 * Unterklasse von AbstractPLayer und implementiert Serializable
 * @author Gruppe 27
 * @version 1.0 
 */

public class LanPlayer extends AbstractPlayer implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Playerindex in PlayerList, ist wichtig fuer client/host Index betraegt -1
	 * 
	 */

	private Keyset Keys;
	public int index = -1;
	public Host h = null;

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
	 */

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
	 * Bewegungseinstellungen Wenn der Spieler noch am Leben ist und die Tasten
	 * zur Steuerung oder Ablegung der Bombe drueckt, wird 'pressed' an das
	 * Programm uebergeben. --> h.hostKeyUpdate(index, keycode, pressed)
	 */

	public void update(int keycode, boolean pressed) {
		// System.out.println("lanplayerupdate");
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
	 * 
	 * @return Werte fuer Keys werden uebergeben
	 */

	public Keyset getKeyset() {
		return Keys;
	}

	public void setKeyset(Keyset k) {
		this.Keys = k;
	}

}
