package de.hhu.propra12.gruppe27.bomberman.core;

import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class BombManager {
	private Spielfeld Owner;
	private ArrayList<Bomb> Bomblist;

	public BombManager(Spielfeld Owner) {
		this.Owner = Owner;
		Bomblist = new ArrayList<Bomb>();
	}

	public void CheckBombs() {
		int i = 0;
		while (i < Bomblist.size()) {
			if (Bomblist.get(i).check()) {
				Bomblist.remove(i);
			} else
				i++;
		}
	}

	public void AddBomb(Bomb b) {
		Bomblist.add(b);
	}

}
