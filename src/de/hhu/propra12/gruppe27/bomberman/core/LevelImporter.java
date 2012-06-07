package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.*;
import java.util.Properties;

public class LevelImporter {
	public static void importLevel() throws IOException{	//Fürs erste Standaloneprojekt - FileInputStream könnte bei nicht Vorhanden sein der Datei Exception auswerfen 
			
			Properties levelstructure = new Properties();	//Properties verwenden, um  Datei zu lesen und Levelstruktur zu importieren
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("level_1.bml")); //.bml (BomberManLevel) mit BIS einlesen
			levelstructure.load(stream);					//load gehört zur properties-Lib
			stream.close();									//Schließen des BufferedInputStream
			String lvlstr = levelstructure.getProperty("LEVEL"); //Lese die Levelstruktur aus der Property-Datei aus
			readLevel(lvlstr);																	
		
		}
	
	private static void readLevel(String input) {
	
		for (int i = 0; i < input.length(); i++){					//Lese String ein und splitte 
			if (input.charAt(i) == ';') System.out.print("\n");		//bei einem ';'. Ersetze 0, 1, 2
			else if (input.charAt(i) == '1') System.out.print("W");	//gegen 'W','B',' '
			else if (input.charAt(i) == '2') System.out.print("B"); 
			else System.out.print(" ");
			}
			
	}
}
