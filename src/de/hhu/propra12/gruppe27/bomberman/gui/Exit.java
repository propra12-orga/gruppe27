package de.hhu.propra12.gruppe27.bomberman.gui;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
import de.hhu.propra12.gruppe27.bomberman.gui.menue.LosetheGame;
import de.hhu.propra12.gruppe27.bomberman.gui.menue.WintheGame;

/**
 * 
 * @author
 * @version 1.0 Klasse zum erstellen vom Ausgang
 * 
 */

public class Exit implements Serializable {

	private static final long serialVersionUID = 1L;
//	private int posx, posy;
	private AbstractFeld Feld;

	/**
	 * Gr��e des Feldes
	 * 
	 * @param Feld
	 */

	public Exit(AbstractFeld Feld) {
		this.Feld = Feld;
//		posx = Feld.getX();
//		posy = Feld.getY();
	}

	/**
	 * Beim erreichen des Ausgangs --> "Sie haben das ende erreicht"
	 * 
	 * @param owner
	 */

	public void doOnExit(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();
		System.out.println("Sie haben das ende erreicht");

		WintheGame wingame = new WintheGame();
		wingame.winthegame();
		// Startmenue start = new Startmenue();
		// start.menueaufruf();

		owner.dispose();
	}

	/**
	 * 
	 * @param owner
	 */

	public void doOnKill(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();

		LosetheGame losegame = new LosetheGame();
		losegame.losethegame();

		owner.dispose();
	}

	/**
	 * 
	 * @return
	 */

	public int getX() {
//		return posx;
		return Feld.getX();
	}

	/**
	 * 
	 * @param posx
	 */

//	public void setPosx(int posx) {
//		this.posx = posx;
//	}

	/**
	 * 
	 * @return
	 */

	public int getY() {
//		return posy;
		return Feld.getY();
	}

	/**
	 * 
	 * @param posy
	 */

//	public void setPosy(int posy) {
//		this.posy = posy;
//	}

	/**
	 * 
	 * @return
	 */

	public AbstractFeld getFeld() {
		return Feld;
	}
}
