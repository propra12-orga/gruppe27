package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.*;
import java.util.Properties;

public abstract class LevelImporter{
/*
	public LevelImporter(int laenge, int breite) {
		super(laenge, breite);
		
	}*/

	public static void main(String[] args) throws IOException{	//Fürs erste Standaloneprojekt - FileInputStream könnte bei nicht Vorhanden sein der Datei Exception auswerfen 
			
			Properties levelstructure = new Properties();	//Properties verwenden, um  Datei zu lesen und Levelstruktur zu importieren
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("level_1.bml")); //.bml (BomberManLevel) mit BIS einlesen
			levelstructure.load(stream);					//load gehört zur properties-Lib
			stream.close();									//Schließen des BufferedInputStream
			String lvlstr = levelstructure.getProperty("LEVEL"); //Lese die Levelstruktur aus der Property-Datei aus
			readLevel(lvlstr);																	
		}
	
	private static void readLevel(String input) {
	
		char[][] test = new char[15][15];
		
		int i = 0;
		int j = 0;
		for (int k = 0; k < (input.length()-1); k++){				//Gesamten String einlesen und bis Länge-1 durchgehen
			if (input.charAt(k) == ';') {j++; i = 0;}				//Bei Semikolon die erste Dimension um 1 erhöhen
			else if (input.charAt(k) == '1') {test[j][i] = 'W';i++;}//Bei "1" neue feste Wand 
			else if (input.charAt(k) == '2') {test[j][i] = 'B';i++;}//Bei "2" neue zerst. Wand
			else {test[j][i] = ' '; i++;}							//Bei "0" oder allem anderem Leerzeichen einsetzen.
		}
	}
}
