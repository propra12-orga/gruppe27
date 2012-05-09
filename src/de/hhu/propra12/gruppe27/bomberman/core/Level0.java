package de.hhu.propra12.gruppe27.bomberman.core;

// Level0 oder Probelevel
// Aufbau "normal"

public class Level0 extends Level {

	// laenge = von links nach rechts
	// breite = von oben nach unten

	boolean freioderzerst;

	public Level0(int laenge, int breite, int spielerzahl) {
		super(laenge, breite);

		// Wenn Feld zu klein Abbruch
		// Minimum 10 x 10 Felder
		// Länge und Breite müssen ungerade sein

		if ((laenge < 10) || (breite < 10) || (laenge % 2 == 0)
				|| (breite % 2 == 0)) {
			System.out.println("Spielfeld entspricht nicht den Anforderungen");
		}

		else {

			// Feldaufbau
			for (int i = 0; i < laenge; i++) {
				for (int j = 0; j < breite; j++) {

					// Aussenwände
					if ((i == 0) || (i == laenge - 1) || (j == 0)
							|| (j == breite - 1)) {
						laxbr[i][j] = new Wall('W');
					}

					// Innenblocks
					else if ((i % 2 == 0) && (j % 2 == 0)) {
						laxbr[i][j] = new Wall('W');
					}

					// Begehbare Felder mit zerstörbaren Objekten
					else {
						freioderzerst = randomBoolean();
						if (freioderzerst == true) {
							laxbr[i][j] = new Block();
						} else {
							laxbr[i][j] = new Path();
						}
						laxbr[1][1] = new Path();// Startfelder nachträglich
													// einfügen macht es
													// einfacher später
													// untschschiedliche
													// spielerzahlen zu händeln
						laxbr[2][1] = new Path();// neben den Startpositionen
													// müssen jeweils freie
													// felder liegen(da sonst
													// die erste bombe den tod
													// bedeutet!)
						laxbr[1][2] = new Path();
						// 1 spieler minimum für weitere spieler später switch
						// auf spielerzahl...

					}

				}
			}
		}

	}

	private boolean randomBoolean() {

		// int r = (int) Math.random() * 10;

		if (Math.random() < 0.7) {
			return true;
		} else {
			return false;
		}
	}

}
