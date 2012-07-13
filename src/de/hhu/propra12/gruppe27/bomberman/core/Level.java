package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.gui.Exit;
import de.hhu.propra12.gruppe27.bomberman.gui.Special;

/**
 * /**
 * Oberklasse fuer alle Level, Leveleigenschaften werden festgelegt
 * @author gruppe 27
 * @version 1.0
 */

public abstract class Level implements Serializable {

	private static final long serialVersionUID = 1L;
	int laenge;
	int breite;
	String name;
	protected Exit e;
	protected Special special;
	protected AbstractFeld laxbr[][];

	SysEinst system = SysEinst.getSystem();

	public Level() {

	}

	/**
	 * 
	 * @param laenge
	 * @param breite
	 *            Initialisierung von Laenge und Breite des Spielfeldes
	 */

	public Level(int laenge, int breite) {
		this.laenge = laenge;
		this.breite = breite;
		laxbr = new AbstractFeld[laenge][breite];

	}

	/**
	 * Level werden in der Konsole ausgegeben
	 */

	public void textout() {
		for (int i = 0; i < laenge; i++) {
			System.out.println(" ");
			for (int j = 0; j < breite; j++) {
				System.out.print(laxbr[i][j].toChar());
			}

		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return false Wenn ein zerstoerbares Feld getroffen wird, wird es durch
	 *         ein normales Feld ersetzt
	 * @return true wenn explosion etwas getroffen hat und somit nicht
	 *         fortgesetz werden soll
	 */

	public boolean DestroyFeld(int x, int y) {
		boolean warfrei = laxbr[x][y].frei;
		if (laxbr[x][y].zerstoer) {
			if (!laxbr[x][y].frei) {
				laxbr[x][y] = new Path(x, y, this);
			}
		}
		if (warfrei)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @param Feld
	 * @return DestroyFeld DestroyFeld wird zurueckgegeben
	 */

	public boolean DestroyFeld(AbstractFeld Feld) {
		return DestroyFeld(Feld.getX(), Feld.getY());
	}

	/**
	 * Parameter fuer x und y werden uebergeben
	 * @param x
	 * @param y
	 * @return laxbr laxbr wird zurueckgegeben
	 * 
	 */

	public AbstractFeld getFeld(int x, int y) {
		return laxbr[x][y];
	}

	/**
	 * 
	 * @param input
	 * @param x
	 * @param y
	 *            Das Feld wird festgelegt
	 */

	public void setFeld(AbstractFeld input, int x, int y) {
		laxbr[x][y] = input;
	}

	public void setboolxplode(int x, int y, boolean z) {
		laxbr[x][y].setXplode(z);
	}

	public boolean getboolxplode(int x, int y) {
		return laxbr[x][y].isXplode();
	}

	/**
	 * 
	 * @param spielernummer
	 * @return laenge Startposition wird festgelegt
	 */

	public abstract int[] getStartposition(int spielernummer);

	public int getlaenge() {
		return laenge;
	}

	/**
	 * 
	 * @return breite Breite wird zurueckgegeben
	 */

	public int getbreite() {
		return breite;
	}
	
	/**
	 * Exist wird uebergeben
	 * @return e
	 */

	public Exit getExit() {
		return e;
	}

}