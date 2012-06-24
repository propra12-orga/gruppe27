package de.hhu.propra12.gruppe27.bomberman.core;

/**
 * 
 * @author
 * @version 1.0
 * Oberklasse für alle Level, Leveleigenschaften werden festgelegt
 */

//oberklasse fï¿½r alle levels, enthï¿½lt methode textout um level testweise in der konsole ausgeben zu lassen, spï¿½ter weitere methoden wie draw(),...
//generierungsregeln fï¿½r level sind von Level abgeleitet,
//(?)level in seperates package(?)
public abstract class Level {
	int laenge;
	int breite;
	String name;
	protected AbstractFeld laxbr[][];

	public Level() {

	}
	
	/**
	 * 
	 * @param laenge
	 * @param breite
	 * Initialisierung von Länge und Breite des Spielfeldes
	 */

	public Level(int laenge, int breite) {// initialisiert laenge breite und
		// spielfeld"laxbr" (bin fuer
		// umbenennung in spielfeld o.ae.
		// ;-))
		this.laenge = laenge;
		this.breite = breite;
		laxbr = new AbstractFeld[laenge][breite];

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
	 * @return true/false
	 * Wenn ein zerstörbares Feld getroffen wird, wird es durch ein normales Feld ersetzt
	 */
	
	// Ã¼berladene funktion DestroyFeld ersetzt Feld , bzw. Feld an [x][y] durch
	// ein freies feld, falls zerstÃ¶rbar ist
	// rÃ¼ckgabe true wenn explosion etwas getroffen hat und somit nich
	// fortgesetzt werden soll
	public boolean DestroyFeld(int x, int y) {
		boolean warfrei = laxbr[x][y].frei;
		if (laxbr[x][y].zerstoer) {
			if (!laxbr[x][y].frei) { // dann ist es ein block der zerstÃ¶rt wird
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
	 * @return labrx
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
	 * @return laenge
	 * Startposition wird festgelegt
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