package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

public class PlayerManager {
	private Spielfeld owner;
	private ArrayList<AbstractPlayer> PlayerList;

	public PlayerManager(Spielfeld owner) {
		PlayerList = new ArrayList<AbstractPlayer>();
		this.owner = owner;
	}

	public void movePlayers() {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).move();
		}
	}

	public void updatePlayers(int keycode, boolean pressed) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).update(keycode, pressed);
		}

	}

	// liefert true zurück wenn das spiel zu ende ist(also wenn ein spieler das
	// Ende erreicht)
	public boolean checkGameEnde() {// bedingungen für spielende
		if (countPlayersAlive() < 1) // wenn keiner mehr lebt
			return true;
		for (int i = 0; i < PlayerList.size(); i++) {
			if ((PlayerList.get(i).getX() == owner.e.getX())
					&& (PlayerList.get(i).getY() == owner.e.getY())) {
				System.out.println("X übereinstimmung!");
				return true;

			}
		}

		return false;
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

	public void addPlayer(AbstractPlayer p) {
		PlayerList.add(p);
	}

	public void paintPlayers(Graphics g) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).draw(g);
		}
	}
}
