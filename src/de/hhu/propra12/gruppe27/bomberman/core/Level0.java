package de.hhu.propra12.gruppe27.bomberman.core;

// Level0 oder Probelevel
// Aufbau "normal"

public class Level0 {

	// laenge = von links nach rechts
	// breite = von oben nach unten

	int laenge;
	int breite;
	AbstractFeld laxbr[][];
	boolean freioderzerst;

	public Level0(int laenge, int breite) {

		this.laenge = laenge;
		this.breite = breite;
		laxbr = new AbstractFeld[laenge][breite];
		{

		}
		;

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
						laxbr[i][j] = new Wall('+');
					}

					// Innenblocks
					else if ((i % 2 == 0) && (j % 2 == 0)) {
						laxbr[i][j] = new Wall('+');
					}

					// Startfeld Spieler 1
					else if ((i == 1) && (j == 1)) {
						laxbr[i][j] = new Wall('S');
					}

					// Begehbare Felder mit zerstörbaren Objekten
					else {
						freioderzerst = randomBoolean();
						if (freioderzerst == true) {
							laxbr[i][j] = new Wall('-');
						} else {
							laxbr[i][j] = new Wall('-');
						}

					}

				}
			}
		}

	}

	public boolean randomBoolean() {

		int r = (int) Math.random() * 10;

		if (r < 7) {
			return true;
		} else {
			return false;
		}
	}

	public void draw() {
		for (int i = 0; i < laenge; i++) {
			System.out.println(" ");
			for (int j = 0; j < breite; j++) {
				System.out.print(laxbr[i][j].toChar());
			}

		}
	}

}
