package de.hhu.propra12.gruppe27.bomberman.core;

//oberklasse für alle levels, enthält methode textout um level testweise in der konsole ausgeben zu lassen, später weitere methoden wie draw(),...
//generierungsregeln für level sind von Level abgeleitet,
//(?)level in seperates package(?)
public abstract class Level {
	int laenge;
	int breite;
	AbstractFeld laxbr[][];

	public Level(int laenge, int breite) {// initialisiert länge breite und
											// spielfeld"laxbr" (bin für
											// umbenennung in spielfeld o.ä.
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

}
