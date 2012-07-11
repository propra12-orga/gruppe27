package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import de.hhu.propra12.gruppe27.bomberman.gui.Exit;
import de.hhu.propra12.gruppe27.bomberman.gui.Special;

/**
 * 
 * @author Gruppe 27
 * @version 1.0 Klasse Levelgenerator, Bestimmung der Eigenschaften des
 *          Spielfeldes. Ist eine Unterklasse von Level und implementiert
 *          Serializable
 * 
 */

public class LevelGen extends Level implements Serializable {

	private static final long serialVersionUID = 1L;

	SysEinst system = SysEinst.getSystem();

	/**
	 * Initialisierung von Feldern Boolean fuer Waende, ob zerstoerbar oder fest
	 */

	int dens = system.getdensWall();
	// int feldx = system.getfeldx();
	// int feldy = system.getfeldy();
	int feldx, feldy;
	int exitx;
	int exity;

	boolean wandoderfrei;
	boolean zerstoderfest;
	boolean konsist = true;

	/**
	 * Level-Generator Konstruktor.
	 * 
	 * @param laengex
	 *            Laenge des Zufalls-Levels.
	 * @param breitey
	 *            Breite des Zufalls-Levels.
	 * @param Spieleranzahl
	 *            Anzahl, der Spieler, die hinzugefuegt werden.
	 * 
	 */

	public LevelGen(int laengex, int breitey, int Spieleranzahl) {

		super(laengex, breitey);
		name = "Zufallslevel";
		feldx = system.getfeldx();
		feldy = system.getfeldy();
		/**
		 * Feldaufbau
		 */

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

		} while (false == konsist);

		// Ausgang wird nur bei einem Solospiel gesetzt
		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			setrandomexit();
		}
		// ansonsten wird der Exit auf ein nicht erreichbares Feld gesetzt
		else {
			e = new Exit(getFeld(0, 0));
		}
	}

	/**
	 * 
	 * @param laengex
	 * @param breitey
	 * @param Spieleranzahl
	 * @param bmllevel
	 *            Laengenkoordinate: x Breitekoordinate: y Initialisierung der
	 *            Spielerazahl
	 * 
	 */
	public LevelGen(int laengex, int breitey, int Spieleranzahl,
			boolean bmllevel) {

		super(laengex, breitey);

		feldx = system.getfeldxbml();
		feldy = system.getfeldybml();

		try {
			generatebml(system.getlevelpath());
		} catch (NumberFormatException e) {
			// TODO Fehlerbehandlung
		} catch (IOException e) {
			// TODO Fehlerbehandlung
		}

		// Ausgang wird nur bei einem Solospiel gesetzt
		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			setrandomexit();
		}
		// ansonsten wird der Exit auf ein nicht erreichbares Feld gesetzt
		else {
			e = new Exit(getFeld(0, 0));
		}
	}

	/**
	 * Boolean zur Zufalls Generierung des Spielfeldes
	 * 
	 * int r = (int) Math.random() * 10;
	 * 
	 * wenn a == 1 ist, dann wird die die Variable aus der Mauerdichte mit
	 * eingezogen (Wand oder freies Feld). Ansonsten sind 75% der Waende
	 * zerstoerbar
	 * 
	 * @return Gibt True oder False zurueck.
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
	 * Generierung der Felder fuer ein zufaelliges Level
	 * 
	 * Alle Felder bekommen Eigenschaften zugewiesen und das Spielfeld mit
	 * unzerstoerbaren Mauern umrandet Methode um alle zu initialisieren
	 */

	private void generatelvl() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				/**
				 * Umrandung des Spielfelds sind zerstoerbare Bloecke
				 */

				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				/**
				 * Rest des Spielfeldes fuellen
				 */

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
	 * Felder eines Standardlevels werden generiert (Aussenwaende, Innenblocks,
	 * Begehbare Felder Methode um die Felder eines Standardlevels zu generieren
	 */

	private void generatestan() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				/**
				 * Aussenwaende
				 */
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				// Innenblocks
				else if ((i % 2 == 0) && (j % 2 == 0)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				/**
				 * Begehbare Felder mit zerstoerbaren Objekten
				 */
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
	 * Spiegelung des Standardlevels Methode um das Standardlevel zu spiegeln
	 */

	private void generatestanspiegel() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				/**
				 * Aussenwaende
				 */
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				/**
				 * Innenblocks
				 */
				else if ((i % 2 == 0) && (j % 2 == 0)) {
					laxbr[i][j] = new Wall(i, j, this);
				}

				/**
				 * Begehbare Felder mit zerstoerbaren Objekten
				 */
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
	 * Spiegelung des Zufall-Spielfeldes Methode um das Zufalls-Soielfeld zu
	 * spiegeln
	 */

	private void generatespiegelung() {

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				/**
				 * Umrandung des Spielfelds sind unzerst. Blöcke
				 */
				if ((i == 0) || (i == feldx - 1) || (j == 0)
						|| (j == feldy - 1)) {
					laxbr[i][j] = new Wall(i, j, this);
					laxbr[feldx - 1 - i][feldy - 1 - j] = new Wall(feldx - 1
							- i, feldy - 1 - j, this);
				}

				/**
				 * Rest des Spielfeldes füllen
				 */
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
	 * Sartfelder werden begehbar gemacht, damit Spieler starten kann Methode um
	 * die Startfelder frei zu machen Startposition Spieler 1 freimachen
	 */
	private void generatefreistart() {
		laxbr[1][1] = new Path(1, 1, this);
		laxbr[1][2] = new Path(1, 2, this);
		laxbr[2][1] = new Path(2, 1, this);

		/**
		 * Startposition Spieler 2 freimachen
		 * 
		 * wenn der Exit random gesetzt wird und der zweite Spieler an
		 * gegenüberliegener Position gesetzt ist if (system.getamplayer() > 1 |
		 * system.getboolKI() == true | system.getboolLAN() == true) {
		 */

		if (1 < system.getamplayer()) {
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

		/**
		 * Damit er die Datei nicht immer neu oeffnen muss.
		 */

		String input = readFile(Levelpath, "LEVEL");
		int i = 0;
		int j = 0;

		/**
		 * Gesamten String einlesen und bis Laenge-1 durchgehen Bei Semikolon
		 * die erste Dimension um 1 erhoehen
		 */

		for (int k = 0; k < (input.length() - 1); k++) {

			if (input.charAt(k) == ';') {
				j++;
				i = 0;
			}
			/**
			 * Bei "1" neue feste Wand
			 */
			else if (input.charAt(k) == '1') {
				laxbr[i][j] = new Wall(i, j, this);
				i++;
				/**
				 * Bei "2" neue zerst. Wand
				 */
			} else if (input.charAt(k) == '2') {
				laxbr[i][j] = new Block(i, j, this);
				i++;
				/**
				 * Bei "0" oder allem anderem begehbaren Weg einsetzen.
				 */
			} else {
				laxbr[i][j] = new Path(i, j, this);
				i++;
			}

		}
	}

	public static String readFile(String Levelpath, String ToBeLoaded)
			throws IOException {
		/**
		 * Lese Leveldatei aus Pfad ein
		 * 
		 */

		Properties levelstructure = new Properties();
		/**
		 * Properties verwenden, um Datei zu lesen und Levelstruktur zu
		 * importieren
		 */

		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream(Levelpath));
		/**
		 * .bml (BomberManLevel) mit BIS einlesen load gehoert zur
		 * properties-Lib stream-close() zum schliessen des BIS
		 */

		levelstructure.load(stream);
		stream.close();
		String data = levelstructure.getProperty(ToBeLoaded);
		/**
		 * Lese die Levelstrukter aus der Property-Datei aus
		 */

		return data;
	}

	/**
	 * Zufaelliges Special wird bestimmt
	 */
	private void setrandomspec() {

		if (system.getrandomlvl() == true) {
			setcoords();
		}

		else {
			do {
				setcoords();
			} while ((exitx % 2) == 0 && (exity % 2) == 0);
		}
		special = new Special(getFeld(exitx, exity));
	}

	/**
	 * Zufaelliger Exit wird bestimmt
	 */

	private void setrandomexit() {

		if (system.getrandomlvl() == true) {
			setcoords();
		}

		else {
			do {
				setcoords();
			} while ((exitx % 2) == 0 && (exity % 2) == 0);
		}

		e = new Exit(getFeld(exitx, exity));
	}

	/**
	 * Koordinaten des willkuerlich gesetzten Extis werden bestimmt
	 */

	private void setcoords() {

		do {
			this.exitx = getrandomcoordx();
			this.exity = getrandomcoordy();

		} while ((exitx == 0 || exitx == system.getfeldx() - 1 || exity == 0 || exity == system
				.getfeldy() - 1)
				|| ((exitx == 1 && exity == 1) || (exitx == 1 && exity == 2) || (exitx == 2 && exity == 1))
				|| ((exitx == system.getfeldx() - 2 && exity == system
						.getfeldy() - 2)
						|| (exitx == system.getfeldx() - 3 && exity == system
								.getfeldy() - 2) || (exitx == system.getfeldx() - 2 && exity == system
						.getfeldy() - 3)));

	}

	/**
	 * 
	 * @return x x wird an die Methode zurueck geliefert
	 * 
	 */

	private int getrandomcoordx() {
		int x = feldx;
		x = (int) (x * Math.random());
		return x;
	}

	/**
	 * 
	 * @return y y wird an die Methode zurueck geliefert
	 */

	private int getrandomcoordy() {
		int y = feldy;
		y = (int) ((int) y * Math.random());
		return y;
	}

}
