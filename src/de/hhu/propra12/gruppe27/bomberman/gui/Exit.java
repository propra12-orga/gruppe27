package de.hhu.propra12.gruppe27.bomberman.gui;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
import de.hhu.propra12.gruppe27.bomberman.gui.menue.LosetheGame;
import de.hhu.propra12.gruppe27.bomberman.gui.menue.WintheGame;

/**
 * Klasse zum erstellen vom Ausgang Implementiert Serializable
 * @author Gruppe 27
 * @version 1.0 
 * 
 */

public class Exit implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * private int posx, posy;
	 */
	private AbstractFeld Feld;

	/**
	 * Groesse des Feldes
	 * 
	 * @param Feld
	 *            mit den Koordinaten x und y --> posx = Feld.getX(); posy =
	 *            Feld.getY();
	 */

	public Exit(AbstractFeld Feld) {
		this.Feld = Feld;

	}

	/**
	 * Beim Erreichen des Ausgangs --> "Sie haben das ende erreicht"
	 * 
	 * @param owner
	 * 
	 */

	public void doOnExit(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();
		System.out.println("Sie haben das ende erreicht");

		WintheGame wingame = new WintheGame();
		wingame.winthegame();

		owner.dispose();
	}

	/**
	 * Parameter owner wird uebergeben
	 * @param owner
	 * Losethegame
	 * 
	 */

	public void doOnKill(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();

		LosetheGame losegame = new LosetheGame();
		losegame.losethegame();

		owner.dispose();
	}

	/**
	 * Parameter owner wird uebergeben
	 * @param owner
	 * Winthegame
	 */

	public void doOnLastMan(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();

		WintheGame wingame = new WintheGame();
		wingame.winthegame();
		// System.out.println();

		owner.dispose();
	}

	/**
	 * Parameter owner wird uebergeben
	 * @param owner
	 * LosetheGame
	 */

	public void doOnLastManP2(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();

		LosetheGame losegame = new LosetheGame();
		losegame.losethegame();

		owner.dispose();
	}

	/**
	 * Postion x wird aufgerufen
	 * @return Feld
	 */

	public int getX() {
		// return posx;
		return Feld.getX();
	}

	/**
	 * Parmaeter posx wird uebergeben
	 * @param posx
	 */

	// public void setPosx(int posx) {
	// this.posx = posx;
	// }

	/**
	 * Position y wird aufgerufen
	 * @return Feld
	 */

	public int getY() {
		// return posy;
		return Feld.getY();
	}

	/**
	 * Parameter posy wird uebergeben
	 * @param posy
	 */

	// public void setPosy(int posy) {
	// this.posy = posy;
	// }

	/**
	 * Feld wird aufgerufen
	 * @return Feld
	 */

	public AbstractFeld getFeld() {
		return Feld;
	}
}
