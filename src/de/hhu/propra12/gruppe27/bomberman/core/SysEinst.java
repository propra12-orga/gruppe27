package de.hhu.propra12.gruppe27.bomberman.core;

/* Systemeinstellungen um ein neues Spielfeld zu erstellen
 * 
 * Anzahl der Spieler 	-> players
 * Feldgröße			-> feldx, feldy
 * densWall				-> Mauerdichte
 * namePlayer1			-> Name von Spieler1
 * namePlayer2			-> Name von Spieler2
 */

public class SysEinst {

	int amplayer = 1;
	int feldx = 15;
	int feldy = 15;
	int densWall = 1;

	String namePlayer1 = "Spieler 1";
	String namePlayer2 = "Spieler 2";
	String namePlayer3 = "Spieler 3";
	String namePlayer4 = "Spieler 4";

	// get-Methoden

	public int getamplayer() {
		return amplayer;
	}

	public int getfeldx() {
		return feldx;
	}

	public int getfeldy() {
		return feldy;
	}

	public int getdensWall() {
		return densWall;
	}

	public String getnamePlayer1() {
		return namePlayer1;
	}

	public String getnamePlayer2() {
		return namePlayer2;
	}

	public String getnamePlayer3() {
		return namePlayer3;
	}

	public String getnamePlayer4() {
		return namePlayer4;
	}

	// set-Methoden

	public void setamplayer(int players) {
		this.amplayer = players;
	}

	public void setfeldx(int feldx) {
		this.feldx = feldx;
	}

	public void setfeldy(int feldy) {
		this.feldy = feldy;
	}

	public void setdensWall(int densWall) {
		this.densWall = densWall;
	}

	public void setnamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}

	public void setnamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}

	public void setnamePlayer3(String namePlayer3) {
		this.namePlayer3 = namePlayer3;
	}

	public void setnamePlayer4(String namePlayer4) {
		this.namePlayer4 = namePlayer4;
	}

	/*
	 * Methode zur Ausgabe aller Werte der System-Einstellungen
	 */
	public void printSysEinst() {
		System.out.println("Spieleranzahl = " + getamplayer());
		System.out.println("Feldx = " + getfeldx());
		System.out.println("Feldy = " + getfeldy());
		System.out.println("Walldensity = " + getdensWall());
		System.out.println("Name Spieler1 = " + getnamePlayer1());
		System.out.println("Name Spieler2 = " + getnamePlayer2());
		System.out.println("Name Spieler3 = " + getnamePlayer3());
		System.out.println("Name Spieler4 = " + getnamePlayer4());
	}

}
