package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class PlayerManager {
	private ArrayList<AbstractPlayer> PlayerList;

	public PlayerManager(Spielfeld Owner) {
		PlayerList = new ArrayList<AbstractPlayer>();
	}

	// zählt lebende spieler
	public int countPlayersAlive() {
		int res = 0;
		for (int i = 0; i < PlayerList.size(); i++)
			if (PlayerList.get(i).isAlive())
				res++;
		return res;
	}

	public void hitPlayers(AbstractFeld Feld) {
		for (int i = 0; i < PlayerList.size(); i++) {
			if (Feld == PlayerList.get(i).getFeld()) {
				PlayerList.get(i).hit();
			}
		}

	}

	public void addPlayer(Player p) {
		PlayerList.add(p);
	}

	public void paintPlayers(Graphics g) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).draw(g);
		}
	}
}
