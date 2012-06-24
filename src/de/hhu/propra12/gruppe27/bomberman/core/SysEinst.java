package de.hhu.propra12.gruppe27.bomberman.core;

/**
 * 
 * @author 
 * @verion 1.0
 * Einstellungen um ein Spielfeld zu erstellen, player = Anzahl der Spieler, Feldgöße = feldx, feldy,
 * denswall = Mauerdichte, randomlvl = Generiertes Level, spiegelung = Level soll gespiegelt werden,
 * standardlvl = Festgelegte unerstörbare Wände im Level, namePlayer = Name des Spielers 
 */


public class SysEinst {

	static SysEinst system;
	int amplayer = 1;
	int feldx = 15;
	int feldy = 15;
	int densWall = 1;
	boolean randomlvl = true;
	boolean spiegelung = false;
	boolean standardlvl = true;

	String namePlayer1 = "Spieler 1";
	String namePlayer2 = "Spieler 2";
	String namePlayer3 = "Spieler 3";
	String namePlayer4 = "Spieler 4";
	
	/**
	 * 
	 * @return system
	 * Systemeinstellungen werden festgelegt
	 */

	public static SysEinst getSystem() {
		if (system == null) {
			system = new SysEinst();
		}
	
		return system;
	}

	
	private SysEinst() {

	}
	
	/**
	 * 
	 * @return amplayer
	 */

	// get-Methoden

	public int getamplayer() {
		return amplayer;
	}
	
	/**
	 * 
	 * @return feldx
	 */

	public int getfeldx() {
		return feldx;
	}
	
	/**
	 * 
	 * @return feldy
	 */

	public int getfeldy() {
		return feldy;
	}
	
	/**
	 * 
	 * @return denswall
	 */

	public int getdensWall() {
		return densWall;
	}
	
	/**
	 * 
	 * @return randomlvl
	 */

	public boolean getrandomlvl() {
		return randomlvl;
	}
	
	/**
	 * 
	 * @return spiegelung
	 */

	public boolean getspiegelung() {
		return spiegelung;
	}
	
	/***
	 * 
	 * @return standardlvl
	 */

	public boolean getstandardlvl() {
		return standardlvl;
	}
	
	/**
	 * 
	 * @return namePlayer1
	 */

	public String getnamePlayer1() {
		return namePlayer1;
	}
	
	/**
	 * 
	 * @return namePlayer2
	 */

	public String getnamePlayer2() {
		return namePlayer2;
	}
	
	/**
	 * 
	 * @return namePlayer3
	 */

	public String getnamePlayer3() {
		return namePlayer3;
	}
	
	/**
	 * 
	 * @return namePlayer4
	 */

	public String getnamePlayer4() {
		return namePlayer4;
	}
	
	/**
	 * 
	 * @param players
	 */

	// set-Methoden

	public void setamplayer(int players) {
		this.amplayer = players;
	}
	
	/**
	 * 
	 * @param feldx
	 */

	public void setfeldx(int feldx) {
		this.feldx = feldx;
	}
	
	/**
	 * 
	 * @param feldy
	 */

	public void setfeldy(int feldy) {
		this.feldy = feldy;
	}
	
	/**
	 * 
	 * @param densWall
	 */

	public void setdensWall(int densWall) {
		this.densWall = densWall;
	}
	
	/**
	 * 
	 * @param lvl
	 */

	public void setrandomlvl(boolean lvl) {
		this.randomlvl = lvl;
	}
	
	/**
	 * 
	 * @param spiegelung
	 */

	public void setspiegelung(boolean spiegelung) {
		this.spiegelung = spiegelung;
	}
	
	/**
	 * 
	 * @param lvl
	 */

	public void setstandardlvl(boolean lvl) {
		this.standardlvl = lvl;
	}
	
	/**
	 * 
	 * @param namePlayer1
	 */

	public void setnamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}
	
	/**
	 * 
	 * @param namePlayer2
	 */

	public void setnamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}
	
	/**
	 * 
	 * @param namePlayer3
	 */

	public void setnamePlayer3(String namePlayer3) {
		this.namePlayer3 = namePlayer3;
	}
	
	/**
	 * 
	 * @param namePlayer4
	 */

	public void setnamePlayer4(String namePlayer4) {
		this.namePlayer4 = namePlayer4;
	}
	
	/**
	 * Ausgabe aller Werte der Symstemeinstellungen
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
