package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author
 * @version 1.0 Oberklasse f�r alle Level, Leveleigenschaften werden festgelegt
 */

// oberklasse f�r alle levels, enth�lt methode textout um level testweise in der
// konsole ausgeben zu lassen, sp�ter weitere methoden wie draw(),...
// generierungsregeln f�r level sind von Level abgeleitet,
// (?)level in seperates package(?)
public abstract class Level {
	int laenge;
	int breite;
	String name;
	protected AbstractFeld laxbr[][];

	SysEinst system = SysEinst.getSystem();

	public Level() {

	}

	/**
	 * 
	 * @param laenge
	 * @param breite
	 *            Initialisierung von L�nge und Breite des Spielfeldes
	 */

	public Level(int laenge, int breite) {// initialisiert laenge breite und
		// spielfeld"laxbr" (bin fuer
		// umbenennung in spielfeld o.ae.
		// ;-))
		this.laenge = laenge;
		this.breite = breite;
		laxbr = new AbstractFeld[laenge][breite];

	}

	public Level(String Levelpath) throws NumberFormatException, IOException {

		laenge = Integer.parseInt(readFile(Levelpath, "LAENGE"));
		breite = Integer.parseInt(readFile(Levelpath, "BREITE"));
		laxbr = new AbstractFeld[laenge][breite];

		// String input = readFile(Levelpath, "LEVEL"); // Damit er die Datei
		// nicht immer
		// // neu �ffnen muss.
		// int i = 0;
		// int j = 0;
		// for (int k = 0; k < (input.length() - 1); k++) { // Gesamten String
		// // einlesen und bis
		// // L�nge-1
		// // durchgehen
		// if (input.charAt(k) == ';') {j++; i = 0;} // Bei Semikolon die erste
		// Dimension um 1 erh�hen
		// else if (input.charAt(k) == '1') { // Bei "1" neue feste Wand
		// laxbr[i][j] = new Wall(i, j, this);
		// i++;
		// }
		// else if (input.charAt(k) == '2') { // Bei "2" neue zerst. Wand
		// laxbr[i][j] = new Block(i, j, this);
		// i++;
		// }
		// else {
		// laxbr[i][j] = new Path(i, j, this); // Bei "0" oder allem anderem
		// begehbaren Weg einsetzen.
		// i++;}
		//
		// }
	}

	public static String readFile(String Levelpath, String ToBeLoaded)
			throws IOException { // Lese
									// Leveldatei
									// aus Pfad
									// ein

		Properties levelstructure = new Properties(); // Properties verwenden,
														// um Datei zu lesen und
														// Levelstruktur zu
														// importieren
		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream(Levelpath)); // .bml (BomberManLevel) mit
													// BIS einlesen
		levelstructure.load(stream); // load geh�rt zur properties-Lib
		stream.close(); // Schlie�en des BIS
		String data = levelstructure.getProperty(ToBeLoaded); // Lese die
																// Levelstruktur
																// aus der
																// Property-Datei
																// aus
		return data;
	}

	/**
	 * Level werden ausgegeben
	 */

	public void textout() {// gibt level in konsole aus
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
	 * @return true/false Wenn ein zerst�rbares Feld getroffen wird, wird es
	 *         durch ein normales Feld ersetzt
	 */

	// überladene funktion DestroyFeld ersetzt Feld , bzw. Feld an [x][y] durch
	// ein freies feld, falls zerstörbar ist
	// rückgabe true wenn explosion etwas getroffen hat und somit nich
	// fortgesetzt werden soll
	public boolean DestroyFeld(int x, int y) {
		boolean warfrei = laxbr[x][y].frei;
		if (laxbr[x][y].zerstoer) {
			if (!laxbr[x][y].frei) { // dann ist es ein block der zerstört wird
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
	 * @return DestroyFeld
	 * 
	 */

	public boolean DestroyFeld(AbstractFeld Feld) {
		return DestroyFeld(Feld.getX(), Feld.getY());
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return laxbr
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
	 */

	public void setFeld(AbstractFeld input, int x, int y) {
		laxbr[x][y] = input;
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
	 * @return breite
	 */

	public int getbreite() {
		return breite;
	}

}