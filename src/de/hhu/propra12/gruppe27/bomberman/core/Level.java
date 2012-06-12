package de.hhu.propra12.gruppe27.bomberman.core;

//oberklasse f�r alle levels, enth�lt methode textout um level testweise in der konsole ausgeben zu lassen, sp�ter weitere methoden wie draw(),...
//generierungsregeln f�r level sind von Level abgeleitet,
//(?)level in seperates package(?)
public abstract class Level {
	int laenge;
	int breite;
	String name;
	protected AbstractFeld laxbr[][];

	public Level() {

	}

	public Level(int laenge, int breite) {// initialisiert laenge breite und
		// spielfeld"laxbr" (bin fuer
		// umbenennung in spielfeld o.ae.
		// ;-))
		this.laenge = laenge;
		this.breite = breite;
		laxbr = new AbstractFeld[laenge][breite];

	}

	public void textout() {// gibt level in konsole aus
		for (int i = 0; i < laenge; i++) {
			System.out.println(" ");
			for (int j = 0; j < breite; j++) {
				System.out.print(laxbr[i][j].toChar());
			}

		}
	}

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

	public boolean DestroyFeld(AbstractFeld Feld) {
		return DestroyFeld(Feld.getX(), Feld.getY());
	}

	public AbstractFeld getFeld(int x, int y) {
		return laxbr[x][y];
	}

	public void setFeld(AbstractFeld input, int x, int y) {
		laxbr[x][y] = input;
	}

	public abstract int[] getStartposition(int spielernummer);

	public int getlaenge() {
		return laenge;
	}

	public int getbreite() {
		return breite;
	}

}