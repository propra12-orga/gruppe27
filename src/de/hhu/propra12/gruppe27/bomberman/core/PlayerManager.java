package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;

import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author 
 * @version 1.0
 * Klasse des Playermanagers
 */

public class PlayerManager {
	private Spielfeld owner;
	private ArrayList<AbstractPlayer> PlayerList;

	public PlayerManager(Spielfeld owner) {
		PlayerList = new ArrayList<AbstractPlayer>();
		this.owner = owner;
	}

	/**
	 * Bewegung der Spieler
	 */
	
	public void movePlayers() {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).move();
		}
	}
	
	/**
	 * 
	 * @param keycode
	 * @param pressed
	 */

	public void updatePlayers(int keycode, boolean pressed) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).update(keycode, pressed);
		}

	}

	// liefert true zurÃ¼ck wenn das spiel zu ende ist(also wenn ein spieler das
	// Ende erreicht)
	
	/**
	 * 
	 * @return true/false
	 * Einstellungen für das Ende des Spiels
	 */
	
	public boolean checkGameEnde() {// bedingungen fÃ¼r spielende
		if (countPlayersAlive() < 1) // wenn keiner mehr lebt
			return true;
		for (int i = 0; i < PlayerList.size(); i++) {
		
			
			if ((PlayerList.get(i).getX() == owner.e.getX())// TODO rausfinden
															// warum der
															// vergleich der
															// felder nicht
															// hingehauen hat
															// (:-/)
					&& (PlayerList.get(i).getY() == owner.e.getY())) {
				System.out.println("X Ã¼bereinstimmung!");
				return true;

			}
		}

		return false;
	}
	
	/**
	 * 
	 * @return res
	 * Programm zählt lebende Spieler
	 */

	// zÃ¤hlt lebende spieler
	public int countPlayersAlive() {
		int res = 0;
		for (int i = 0; i < PlayerList.size(); i++)
			if (PlayerList.get(i).isAlive())
				res++;
		return res;
	}
	
	/**
	 * 
	 * @param Feld
	 */

	public void hitPlayers(AbstractFeld Feld) {
		for (int i = 0; i < PlayerList.size(); i++) {
			if (Feld == PlayerList.get(i).getFeld()) {
				PlayerList.get(i).hit();
			}
		}

	}
	
	/**
	 * 
	 * @param p
	 * Spieler kann hinzugefügt
	 */

	public void addPlayer(AbstractPlayer p) {
		PlayerList.add(p);
	}
	
	/**
	 * 
	 * @param g
	 * Spielfigur wird ausgegeben
	 */

	public void paintPlayers(Graphics g) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).draw(g);
		}
	}
}
