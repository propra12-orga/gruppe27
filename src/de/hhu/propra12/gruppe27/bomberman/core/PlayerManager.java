package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse des Playermanagers Owner wird augerufen
 */

public class PlayerManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private Spielfeld owner;
	public ArrayList<AbstractPlayer> PlayerList;

	public PlayerManager(Spielfeld owner) {
		PlayerList = new ArrayList<AbstractPlayer>();
		this.owner = owner;
	}

	/**
	 * Bewegung der Spieler Playerlist wird um eins erhoeht wenn 0
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
	 *            Werte fuer Player wird aktualisiert
	 */

	public void updatePlayers(int keycode, boolean pressed) {
		// System.out.println("ArrayListsize " + PlayerList.size());
		for (int i = 0; i < PlayerList.size(); i++) {

			PlayerList.get(i).update(keycode, pressed);
		}

	}

	/**
	 * 
	 * @return true liefert true zurueck wenn das spiel zu ende ist(also wenn
	 *         ein spieler das Ende erreicht)
	 */

	public int checkGameEnde() {
		if (countPlayersAlive() < 1)
			return 1;
		for (int i = 0; i < PlayerList.size(); i++) {

			if ((PlayerList.get(i).getX() == owner.getExit().getX())// TODO
																	// rausfinden
					// warum der
					// vergleich der
					// felder nicht
					// hingehauen hat
					// (:-/)
					&& (PlayerList.get(i).getY() == owner.getExit().getY())) {
				System.out.println("X übereinstimmung!");
				return 2;

			}
		}

		return 0;
	}

	// public boolean checkGameEnde() {// bedingungen für spielende
	// if (countPlayersAlive() < 1) // wenn keiner mehr lebt
	// return true;
	// for (int i = 0; i < PlayerList.size(); i++) {
	//
	//
	// if ((PlayerList.get(i).getX() == owner.e.getX())// TODO rausfinden
	// // warum der
	// // vergleich der
	// // felder nicht
	// // hingehauen hat
	// // (:-/)
	// && (PlayerList.get(i).getY() == owner.e.getY())) {
	// System.out.println("X übereinstimmung!");
	// return true;
	//
	// }
	// }
	//
	// return false;
	// }

	/**
	 * 
	 * @return res Programm zaehlt lebende Spieler
	 */

	// zählt lebende spieler
	public int countPlayersAlive() {
		int res = 0;
		for (int i = 0; i < PlayerList.size(); i++)
			if (PlayerList.get(i).isAlive())
				res++;
		return res;
	}

	/**
	 * PlayerList wird uebergeben
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
	 *            Spieler kann hinzugefuegt werden
	 */

	public void addPlayer(AbstractPlayer p) {
		PlayerList.add(p);
		if (p instanceof LanPlayer)
			((LanPlayer) p).index = PlayerList.indexOf(p);
	}

	/**
	 * 
	 * @param g
	 *            Spielfigur wird ausgegeben
	 */

	public void paintPlayers(Graphics g) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).draw(g);
		}
	}

	/**
	 * 
	 * @return PlayerList PlayerList wird uebergeben
	 */

	public ArrayList<AbstractPlayer> getPlayerList() {
		return PlayerList;
	}

	public void moveremotePlayers(int direction) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).moveremote(direction);
		}
	}
}
