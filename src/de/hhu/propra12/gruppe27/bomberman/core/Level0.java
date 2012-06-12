package de.hhu.propra12.gruppe27.bomberman.core;

// Level0 oder Probelevel
// Aufbau "normal"

/**
 * 
 * @author 
 * @version 1.0
 * Klasse zur Erstellung eines Level0 extends Level
 */

public class Level0 extends Level {

	// laenge = von links nach rechts
	// breite = von oben nach unten

	/**
	 * frei oder zerstörbar
	 */
	
	boolean freioderzerst;

	/**
	 * 
	 * @param laenge
	 * @param breite
	 * @param spielerzahl
	 * Level wird getestet
	 */
	
	public Level0(int laenge, int breite, int spielerzahl) {
		super(laenge, breite);
		this.name = "Testlevel";

		// Wenn Feld zu klein Abbruch
		// Minimum 10 x 10 Felder
		// LÃ¤nge und Breite muessen ungerade sein

		if ((laenge < 10) || (breite < 10) || (laenge % 2 == 0)
				|| (breite % 2 == 0)) {
			System.out.println("Spielfeld entspricht nicht den Anforderungen");
		}
		
		/**
		 * Wenn Feld den Anforderungen entspricht, kann das Feld aufgebaut werden
		 */

		else {

			// Feldaufbau
			for (int i = 0; i < laenge; i++) {
				for (int j = 0; j < breite; j++) {
					
					/**
					 * Bestimmung der Eigenschaften der Außenwände
					 */

					// Aussenwaende
					if ((i == 0) || (i == laenge - 1) || (j == 0)
							|| (j == breite - 1)) {
						laxbr[i][j] = new Wall(i, j, this);
					}
					
					/**
					 * Bestimmung der Eigenschaften der Innenblocks
					 */

					// Innenblocks
					else if ((i % 2 == 0) && (j % 2 == 0)) {
						laxbr[i][j] = new Wall(i, j, this);
					}
					
					/**
					 * Bestimmung von begehbaren Feldern aber mit zerstörbaren Objekten 
					 */

					// Begehbare Felder mit zerstoerbaren Objekten
					else {
						freioderzerst = randomBoolean();
						if (freioderzerst == true) {
							laxbr[i][j] = new Block(i, j, this);
						} else {
							laxbr[i][j] = new Path(i, j, this);
						}
						laxbr[1][1] = new Path(1, 1, this);// Startfelder
															// nachtrÃ¤glich
															// einfuegen macht
															// es
															// einfacher spÃ¤ter
															// untschschiedliche
															// spielerzahlen zu
															// hÃ¤ndeln
						laxbr[2][1] = new Path(2, 1, this);// neben den
															// Startpositionen
															// muessen jeweils
															// freie
															// felder liegen(da
															// sonst
															// die erste bombe
															// den
															// tod
															// bedeutet!)
						laxbr[1][2] = new Path(1, 2, this);

					}

				}
			}
		}

	}
	
	/**
	 * 
	 * @return true/false
	 */

	private boolean randomBoolean() {

		// int r = (int) Math.random() * 10;

		if (Math.random() < 0.25) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Spielernummer
	 */
	
	@Override
	// fuer mehr spieler anzupassen!
	public int[] getStartposition(int spielernummer) {
		int[] a = { 1, 1 };
		return a;
	}

}
