package de.hhu.propra12.gruppe27.bomberman.core;

public class LevelGen extends Level {

	SysEinst system = SysEinst.getSystem();

	int dens = system.getdensWall();
	int feldx = system.getfeldx();
	int feldy = system.getfeldy();

	boolean wandoderfrei;
	boolean zerstoderfest;
	boolean konsist = true;

	public LevelGen(int laengex, int breitey, int Spieleranzahl) {

		// laxbr = new AbstractFeld[feldx][feldy];
		super(laengex, breitey);
		name = "Zufallslevel";

		// Feldaufbau

		do {

			if (system.getstandardlvl()) {
				generatestan();
			} else if (system.getspiegelung()) {
				generatespiegel();
			} else {
				generatelvl();
			}

			generatefreistart();

			// TODO Konsistenz-Prüfung
			// konsist(); ???

		} while (false == konsist);

	}

	/*
	 * Boolean zur Zufalls Generierung des Spielfeldes
	 * 
	 * int r = (int) Math.random() * 10;
	 * 
	 * wenn a == 1 ist, dann wird die die Variable aus der Mauerdichte mit
	 * eingezogen (Wand oder freies Feld). Ansonsten sind 75% der Waende
	 * zerstörbar
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

	/*
	 * Methode um das Spielfeld zu spiegeln
	 */
	private void generatespiegel() {

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

	/*
	 * Methode um die Startfelder frei zu machen
	 */
	private void generatefreistart() {
		// Startposition Spieler 1 freimachen
		laxbr[1][1] = new Path(1, 1, this);
		laxbr[1][2] = new Path(1, 2, this);
		laxbr[2][1] = new Path(2, 1, this);

		// Startposition Spieler 2 freimachen
		if (1 <= system.getamplayer()) {
			laxbr[feldx - 2][feldy - 2] = new Path(feldx - 2, feldy - 2, this);
			laxbr[feldx - 2][feldy - 3] = new Path(feldx - 2, feldy - 3, this);
			laxbr[feldx - 3][feldy - 2] = new Path(feldx - 3, feldy - 2, this);
		}
	}

	public int[] getStartposition(int spielernummer) {
		int[] a = { 1, 1 };
		return a;

	}

}
