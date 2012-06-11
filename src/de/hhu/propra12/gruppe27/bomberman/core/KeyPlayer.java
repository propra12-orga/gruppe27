package de.hhu.propra12.gruppe27.bomberman.core;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;
import java.awt.event.KeyEvent;

@SuppressWarnings("unused")
public class KeyPlayer extends AbstractPlayer {
	Keyset Keys;
	
	public KeyPlayer(int posx, int posy, String pname, Spielfeld owner, Keyset Keys) {
		alive = true;
		this.posx = posx;
		this.posy = posy;
		this.Keys=Keys;
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
	}

}
