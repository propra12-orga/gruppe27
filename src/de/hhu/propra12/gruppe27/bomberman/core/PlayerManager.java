package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * Klasse des Playermanagers wird augerufen
 * @author gruppe 27
 * @version 1.0 
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
	 *true wird uebergeben wenn das spiel zu ende ist(also wenn
	 *ein spieler das Ende erreicht)
	 */
	 

	public final static int ENDE = 0, ALLDEAD = 1, EXIT = 2, LASTMAN = 3,
			LASTMANP2 = 4;
	SysEinst sys = SysEinst.getSystem();

	public boolean checkspecial() {
		if (sys.getamplayer() == 1) {
			for (int i = 0; i < PlayerList.size(); i++) {

				if ((PlayerList.get(i).getX() == owner.getSpecial().getX())
						&& (PlayerList.get(i).getY() == owner.getSpecial()
								.getY())) {

					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Ende wenn niemand mehr lebt Ende oder wenn der Ausgang betreten wir
	 * 
	 * @return ALLDEAD
	 */

	public int checkGameEnde() {

		if (countPlayersAlive() < 1) {
			return ALLDEAD;
		}

		else if (sys.getamplayer() == 1) {
			for (int i = 0; i < PlayerList.size(); i++) {

				if ((PlayerList.get(i).getX() == owner.getLevel().getExit()
						.getX())
						// TODO rausfinden warum der vergleich der felder nicht
						// hingehauen
						// hat (:-/)
						&& (PlayerList.get(i).getY() == owner.getLevel()
								.getExit().getY())) {
					System.out.println("X übereinstimmung!");
					return EXIT;
				}
			}
		} else if (sys.getamplayer() > 1 && sys.getboolLAN() == false) {
			if (countPlayersAlive() == 1)

				return LASTMAN;
		}

		/**
		 * im Mehrspielermodus wir das Spiel beendet, wenn nur noch 1 Spieler
		 * lebt
		 */
		else if (sys.getboolLAN()) {
			if (countPlayersAlive() == 1) {

				if (PlayerList.get(0).isAlive())
					return LASTMAN;
				else
					return LASTMANP2;
			}
		}

		return ENDE;
	}

	/**
	 * 
	 * @return res Programm zaehlt lebende Spieler
	 */

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
			PlayerList.get(i).draw(g, i);
		}
	}

	/**
	 * 
	 * @return PlayerList PlayerList wird uebergeben
	 */

	public ArrayList<AbstractPlayer> getPlayerList() {
		return PlayerList;
	}
	
	/**
	 * Parameter direction wird uebergeben
	 * @param direction
	 */

	public void moveremotePlayers(int direction) {
		for (int i = 0; i < PlayerList.size(); i++) {
			PlayerList.get(i).moveremote(direction);
		}
	}
}
