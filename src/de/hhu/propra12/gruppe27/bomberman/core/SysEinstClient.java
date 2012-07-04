package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteClient;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteHost;

/**
 * 
 * @author
 * @verion 1.0 Einstellungen um ein Spielfeld zu erstellen, player = Anzahl der
 *         Spieler, Feldg��e = feldx, feldy, denswall = Mauerdichte, randomlvl =
 *         Generiertes Level, spiegelung = Level soll gespiegelt werden,
 *         standardlvl = Festgelegte unerst�rbare W�nde im Level, namePlayer =
 *         Name des Spielers
 */

public class SysEinstClient implements Serializable {

	private static final long serialVersionUID = 1L;
	static SysEinstClient system;
	int amplayer = 1;
	int feldx = 15;
	int feldxbml;
	int feldy = 15;
	int feldybml;
	int densWall = 1;
	boolean randomlvl = true;
	boolean spiegelung = false;
	boolean standardlvl = true;
	boolean bmllevel = false;
	boolean boolKI = false;
	boolean boolLAN = false;
	boolean boolClient;
	boolean sound = false;
	boolean MouseOverBool = false; // Sound-MouseOvers initialisiern oder nicht
	IRemoteHost remotehost = null;
	IRemoteClient remoteclient = null;

	String levelpath = "data/levels/level_1.bml";
	String levelname = "";
	String namePlayer1 = "Spieler 1";
	String namePlayer2 = "Spieler 2";
	String namePlayer3 = "Spieler 3";
	String namePlayer4 = "Spieler 4";

	/**
	 * 
	 * @return system Systemeinstellungen werden festgelegt
	 */

	public static SysEinstClient getSystemClient() {
		if (system == null) {
			system = new SysEinstClient();
		}

		return system;
	}

	public SysEinstClient() {

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
	 * @return feldxbml
	 */
	public int getfeldxbml() {
		return feldxbml;
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
	 * @return feldybml
	 */

	public int getfeldybml() {
		return feldybml;
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
	 * @return bmllevel
	 */

	public boolean getbmllevel() {
		return bmllevel;
	}

	/**
	 * 
	 * @return boolKI
	 */

	public boolean getboolKI() {
		return boolKI;
	}

	/**
	 * 
	 * @return boolLAN
	 */

	public boolean getboolLAN() {
		return boolLAN;
	}

	/**
	 * 
	 * @return boolClient
	 */

	public boolean getboolClient() {
		return boolClient;
	}

	public boolean getSound() {
		return sound;
	}

	public boolean getMouseOverBool() {
		// TODO Auto-generated method stub
		return MouseOverBool;
	}

	/**
	 * 
	 * @return levelpath
	 */

	public String getlevelpath() {
		return levelpath;
	}

	public String getlevelname() {
		return levelname;
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
	 * @return remotehost
	 */

	public IRemoteHost getRemoteHost() {
		return remotehost;
	}

	/**
	 * 
	 * @return remotehost
	 */

	public IRemoteClient getRemoteClient() {
		return remoteclient;
	}

	// set-Methoden

	/**
	 * 
	 * @param players
	 */

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
	 * @param feldx
	 */

	public void setfeldxbml(int feldxbml) {
		this.feldxbml = feldxbml;
	}

	/**
	 * 
	 * @param feldy
	 */

	public void setfeldybml(int feldybml) {
		this.feldybml = feldybml;
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
	 * @param bmllevel
	 */

	public void setbmllevel(boolean bmllvl) {
		this.bmllevel = bmllvl;
	}

	/**
	 * 
	 * @param boolKI
	 */

	public void setboolKI(boolean boolKI) {
		this.boolKI = boolKI;
	}

	/**
	 * 
	 * @param boolLAN
	 */

	public void setboolLAN(boolean boolLAN) {
		this.boolLAN = boolLAN;
	}

	/**
	 * 
	 * @param boolClient
	 */

	public void setboolClient(boolean boolClient) {
		this.boolClient = boolClient;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public void setMouseOverBool(boolean MouseOverBool) {
		this.MouseOverBool = MouseOverBool;
	}

	/**
	 * 
	 * @param namePlayer1
	 */

	public void setlevelpath(String levelpath) {
		this.levelpath = levelpath;
	}

	public void setlevelname(String levelname) {
		this.levelname = levelname;
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
	 * 
	 * @return remotehost
	 */

	public void setRemoteHost(IRemoteHost remotehost) {
		this.remotehost = remotehost;
	}

	/**
	 * 
	 * @return remotehost
	 */

	public void setRemoteClient(IRemoteClient remoteclient) {
		this.remoteclient = remoteclient;
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
		System.out.println("---\n" + "randomlvl= " + randomlvl + "spiegelung= "
				+ spiegelung + "standardlvl= " + standardlvl + "bmllvl= "
				+ bmllevel + "boolKI= " + boolKI + "boolLAN= " + boolLAN
				+ "boolClient= " + boolClient);
	}

}
