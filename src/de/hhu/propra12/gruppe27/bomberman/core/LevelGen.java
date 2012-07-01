package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * Klasse Levelgenerator, Bestimmung der Eigenschaften des Spielfeldes.
 * 
 * @author
 * @version 1.0
 * 
 */

public class LevelGen extends Level implements Serializable {

	private static final long serialVersionUID = 1L;

	SysEinst system = SysEinst.getSystem();

	int dens = system.getdensWall();
	int feldx = system.getfeldx();
	int feldy = system.getfeldy();

	boolean wandoderfrei;
	boolean zerstoderfest;
	boolean konsist = true;

	/**
	 * Level-Generator Konstruktor.
	 * 
	 * @param laengex
	 *            L�nge des Zufalls-Levels.
	 * @param breitey
	 *            Breite des Zufalls-Levels.
	 * @param Spieleranzahl
	 *            Anzahl, der Spieler, die hinzugef�gt werden.
	 * 
	 */

	public LevelGen(int laengex, int breitey, int Spieleranzahl) {

		super(laengex, breitey);
		name = "Zufallslevel";

		// Feldaufbau

		do {

			if (true == system.getstandardlvl()
					&& false == system.getspiegelung()) {
				generatestan();
			} else if (true == system.getstandardlvl()
					&& true == system.getspiegelung()) {
				generatestanspiegel();
			} else if (false == system.getstandardlvl()
					&& false == system.getspiegelung()) {
				generatelvl();
			} else {
				generatespiegelung();
			}

			generatefreistart();

			// TODO Konsistenz-Prüfung
			// konsist(); ???

		} while (false == konsist);

	}

	/**
	 * 
	 * @param laengex
	 * @param breitey
	 * @param Spieleranzahl
	 * @param bmllevel
	 */
	public LevelGen(int laengex, int breitey, int Spieleranzahl,
			boolean bmllevel) {

		super(laengex, breitey);

		try {
			generatebml(system.getlevelpath());
		} catch (NumberFormatException e) {
			// TODO Fehlerbehandlung
		} catch (IOException e) {
			// TODO Fehlerbehandlung
		}

	}

	/**
	 * Boolean zur Zufalls Generierung des Spielfeldes
	 * 
	 * int r = (int) Math.random() * 10;
	 * 
	 * wenn a == 1 ist, dann wird die die Variable aus der Mauerdichte mit
	 * eingezogen (Wand oder freies Feld). Ansonsten sind 75% der Waende
	 * zerst�rbar
	 * 
	 * @return Gibt True oder False zur�ck.
	 */
	private boolean randomBoolean(int a) {

		if (1 == a) {
			if (Math.random() < system.getdensWall() * 0.1) {
				return true;
			} else {
				return false;
			}
		} else {
			if (Math.random() < 0.75) {
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * Generierung der Felder für ein zufälliges Level
	 * 
	 * Alle Felder bekommen Eigenschaften zugewiesen und das Spielfeld mit
	 * unzerstoerbaren Mauern umrandet
	 */

	/*
	 * Methode um alle Felder zu initialisieren
	 */
	private void generatelvl() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				// Umrandung des Spielfelds sind unzerst. Blöcke
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				// Rest des Spielfeldes füllen
				else {
					wandoderfrei = randomBoolean(1);
					zerstoderfest = randomBoolean(2);
					if (true == wandoderfrei) {
						if (true == zerstoderfest) {
							laxbr[i][j] = new Block(i, j, this);
						} else {
							laxbr[i][j] = new Wall(i, j, this);
						}
					} else {
						laxbr[i][j] = new Path(i, j, this);
					}
				}
			}
		}
	}

	/**
	 * Felder eines Standardlevels werden generiert (Ausenwaende, Innenblocks,
	 * Begehbare Felder
	 */

	/*
	 * Methode um die Felder eines Standardlevels zu generieren
	 */
	private void generatestan() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				// Aussenwaende
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				// Innenblocks
				else if ((i % 2 == 0) && (j % 2 == 0)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				// Begehbare Felder mit zerstoerbaren Objekten
				else {
					wandoderfrei = randomBoolean(1);
					if (wandoderfrei == true) {
						laxbr[i][j] = new Block(i, j, this);
					} else {
						laxbr[i][j] = new Path(i, j, this);
					}
				}
			}
		}
	}

	/**
	 * Spiegelung des Standardlevels
	 */

	/*
	 * Methode um das Standardlevel zu spiegeln
	 */
	private void generatestanspiegel() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				// Aussenwaende
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				// Innenblocks
				else if ((i % 2 == 0) && (j % 2 == 0)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				// Begehbare Felder mit zerstoerbaren Objekten
				else {
					wandoderfrei = randomBoolean(1);
					if (wandoderfrei == true) {
						laxbr[i][j] = new Block(i, j, this);
						laxbr[feldx - 1 - i][feldy - 1 - j] = new Block(feldx
								- 1 - i, feldy - 1 - j, this);
					} else {
						laxbr[i][j] = new Path(i, j, this);
						laxbr[feldx - 1 - i][feldy - 1 - j] = new Path(feldx
								- 1 - i, feldy - 1 - j, this);
					}
				}
			}
		}
	}

	/**
	 * Spiegelung des Zufall-Spielfeldes
	 */

	/*
	 * Methode um das Zufalls-Spielfeld zu spiegeln
	 */
	private void generatespiegelung() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				// Umrandung des Spielfelds sind unzerst. Blöcke
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
					laxbr[feldx - 1 - i][feldy - 1 - j] = new Wall(feldx - 1
							- i, feldy - 1 - j, this);
				}

				// Rest des Spielfeldes füllen
				else {
					wandoderfrei = randomBoolean(1);
					zerstoderfest = randomBoolean(2);
					if (true == wandoderfrei) {
						if (true == zerstoderfest) {
							laxbr[i][j] = new Block(i, j, this);
							laxbr[feldx - 1 - i][feldy - 1 - j] = new Block(
									feldx - 1 - i, feldy - 1 - j, this);
						} else {
							laxbr[i][j] = new Wall(i, j, this);
							laxbr[feldx - 1 - i][feldy - 1 - j] = new Wall(
									feldx - 1 - i, feldy - 1 - j, this);
						}
					} else {
						laxbr[i][j] = new Path(i, j, this);
						laxbr[feldx - 1 - i][feldy - 1 - j] = new Path(feldx
								- 1 - i, feldy - 1 - j, this);
					}
				}
			}
		}
	}

	/**
	 * Sartfelder werden begehbar gemacht, damit Spieler starten kann
	 */

	/*
	 * Methode um die Startfelder frei zu machen
	 */
	private void generatefreistart() {
		// Startposition Spieler 1 freimachen
		laxbr[1][1] = new Path(1, 1, this);
		laxbr[1][2] = new Path(1, 2, this);
		laxbr[2][1] = new Path(2, 1, this);

		// Startposition Spieler 2 freimachen

		// wenn der Exit random gesetzt wird und der zweite Spieler an
		// gegenüberliegener Position gesetzt ist
		// if (system.getamplayer() > 1 | system.getboolKI() == true |
		// system.getboolLAN() == true) {
		if (1 <= system.getamplayer()) {
			laxbr[feldx - 2][feldy - 2] = new Path(feldx - 2, feldy - 2, this);
			laxbr[feldx - 2][feldy - 3] = new Path(feldx - 2, feldy - 3, this);
			laxbr[feldx - 3][feldy - 2] = new Path(feldx - 3, feldy - 2, this);
		}
	}

	/**
	 * @return a Startposition und Spielnummer werden festgelegt
	 */

	public int[] getStartposition(int spielernummer) {
		int[] a = { 1, 1 };
		return a;

	}

	public void generatebml(String Levelpath) throws NumberFormatException,
			IOException {

		// Damit er die Datei nicht immer neu oeffnen muss.
		String input = readFile(Levelpath, "LEVEL");
		int i = 0;
		int j = 0;

		// Gesamten String einlesen und bis Laenge-1 durchgehen

		for (int k = 0; k < (input.length() - 1); k++) {

			// Bei Semikolon die erste Dimension um 1 erhoehen
			if (input.charAt(k) == ';') {
				j++;
				i = 0;
			}
			// Bei "1" neue feste Wand
			else if (input.charAt(k) == '1') {
				laxbr[i][j] = new Wall(i, j, this);
				i++;
				// Bei "2" neue zerst. Wand
			} else if (input.charAt(k) == '2') {
				laxbr[i][j] = new Block(i, j, this);
				i++;
				// Bei "0" oder allem anderem begehbaren Weg einsetzen.
			} else {
				laxbr[i][j] = new Path(i, j, this);
				i++;
			}

		}
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

}
