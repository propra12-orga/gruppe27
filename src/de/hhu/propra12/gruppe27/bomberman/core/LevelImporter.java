//package de.hhu.propra12.gruppe27.bomberman.core;
//
//import java.io.*;
//import java.util.Properties;
//
//public abstract class LevelImporter{
//
//	public LevelImporter(String Levelpath) {
//		super(laenge, breite);
//		
//		String input = readFile(Levelpath);							//Damit er die Datei nicht immer neu �ffnen muss.
//		int i = 0;
//		int j = 0;
//		for (int k = 0; k < (input.length()-1); k++){				//Gesamten String einlesen und bis L�nge-1 durchgehen
//			if (input.charAt(k) == ';') {j++; i = 0;}				//Bei Semikolon die erste Dimension um 1 erh�hen
//			else if (input.charAt(k) == '1') {laxbr[i][j] = new Wall(i, j, this);i++;}//Bei "1" neue feste Wand 
//			else if (input.charAt(k) == '2') {laxbr[i][j] = new Block(i, j, this);i++;}//Bei "2" neue zerst. Wand
//			else {laxbr[i][j] = new Path(i, j, this); i++;}			//Bei "0" oder allem anderem Leerzeichen einsetzen.	
//	}
//
//	public String readFile(String Levelpath) throws IOException{	//Lese Leveldatei aus Pfad ein
//			
//			Properties levelstructure = new Properties();	//Properties verwenden, um  Datei zu lesen und Levelstruktur zu importieren
//			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(Levelpath)); //.bml (BomberManLevel) mit BIS einlesen
//			levelstructure.load(stream);					//load geh�rt zur properties-Lib
//			stream.close();									//Schlie�en des BIS
//			String lvlstr = levelstructure.getProperty("LEVEL"); //Lese die Levelstruktur aus der Property-Datei aus
//			return lvlstr;
//		}
//	
//		
//	private int getlaenge(String input) {						//Die Funktion liest den String ein und z�hlt die Semikolons im
//		int value = 0;											//String, um herauszufinden wie lang das Level sein soll.
//		for (int pos = 0; pos < (input.length()-1); pos++){
//			if (input.charAt(pos) == ';') value++;
//		}
//		return value;
//	} 
//	
//	private int getbreite(String input) {						//Hier wird gez�hlt wie oft ein Zeichen vorkommt bis zu einem
//		int value = 0;											//Semikolon. Dadurch kriegt man die breite raus.
//		for (int pos = 0; pos < (input.length()-1); pos++){
//			if (input.charAt(pos) == ';') {
//				value = pos;
//			}
//		}
//		return value;
//	}
//	
//	
//}
//	
//
