package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LevelImport extends Level {

	public LevelImport(int laenge, int breite) {
		super(laenge, breite);

		// TODO Auto-generated constructor stub
	}

	public LevelImport(String Levelpath) {

		super(laenge, breite);

		String input = readFile(Levelpath); // Damit er die Datei nicht immer
		// neu �ffnen muss.
		int i = 0;
		int j = 0;
		for (int k = 0; k < (input.length() - 1); k++) { // Gesamten String
			// einlesen und bis
			// L�nge-1
			// durchgehen
			if (input.charAt(k) == ';') {
				j++;
				i = 0;
			} // Bei Semikolon die erste Dimension um 1 erh�hen
			else if (input.charAt(k) == '1') {
				laxbr[i][j] = new Wall(i, j, this);
				i++;
			}// Bei "1" neue feste Wand
			else if (input.charAt(k) == '2') {
				laxbr[i][j] = new Block(i, j, this);
				i++;
			}// Bei "2" neue zerst. Wand
			else {
				laxbr[i][j] = new Path(i, j, this);
				i++;
			} // Bei "0" oder allem anderem Leerzeichen einsetzen.
		}
	}

	public String readFile(String Levelpath) throws IOException { // Lese
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
		String lvlstr = levelstructure.getProperty("LEVEL"); // Lese die
		// Levelstruktur
		// aus der
		// Property-Datei
		// aus
		return lvlstr;
	}

	@Override
	public int[] getStartposition(int spielernummer) {
		// TODO Auto-generated method stub
		return null;
	}

}
