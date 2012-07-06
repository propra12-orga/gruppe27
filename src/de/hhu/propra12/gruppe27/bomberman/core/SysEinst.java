package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteClient;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteHost;

/**
 * 
 * @author
 * @verion 1.0 Einstellungen um ein Spielfeld zu erstellen, player = Anzahl der
 *         Spieler, Feldgoesse = feldx, feldy, denswall = Mauerdichte, randomlvl
 *         = Generiertes Level, spiegelung = Level soll gespiegelt werden,
 *         standardlvl = Festgelegte unzersoerbare Waende im Level, namePlayer =
 *         Name des Spielers
 */

public class SysEinst implements Serializable {

	private static final long serialVersionUID = 1L;
	static SysEinst system;
	int amplayer = 1;
	int feldx = 15;
	int feldxbml;
	int feldy = 15;
	int feldybml;
	int densWall = 1;
	int highscoreP1 = 0;
	int highscoreP2 = 0;

	boolean randomlvl = true;
	boolean spiegelung = false;
	boolean standardlvl = true;
	boolean bmllevel = false;
	boolean boolKI = false;
	boolean boolLAN = false;
	boolean boolClient = false;
	boolean sound = false;
	boolean MouseOverBool = false; // Sound-MouseOvers initialisiern oder nicht
	IRemoteHost remotehost = null;
	IRemoteClient remoteclient = null;

	String levelpath = "data/levels/level_1.bml";
	String levelname = "";
	String message2P;
	String namePlayer1 = "Spieler 1";
	String namePlayer2 = "Spieler 2";
	String namePlayer3 = "Spieler 3";
	String namePlayer4 = "Spieler 4";

	/**
	 * 
	 * @return system Systemeinstellungen werden festgelegt
	 */

	public static SysEinst getSystem() {
		if (system == null) {
			system = new SysEinst();
		}

		return system;
	}

	public SysEinst() {

	}

	/**
	 * 
	 * @return amplayer amplayer wird uebergeben
	 */

	// get-Methoden

	public int getamplayer() {
		return amplayer;
	}

	/**
	 * feldx Werte werden ueberliefert
	 * 
	 * @return feldx
	 */

	public int getfeldx() {
		return feldx;
	}

	/**
	 * feldxbml Wetre werden uebergeben
	 * 
	 * @return feldxbml
	 */
	public int getfeldxbml() {
		return feldxbml;
	}

	/**
	 * feldy Werte werden uebergeben
	 * 
	 * @return feldy
	 */

	public int getfeldy() {
		return feldy;
	}

	/**
	 * feldybml Werte werden uebergeben
	 * 
	 * @return feldybml
	 */

	public int getfeldybml() {
		return feldybml;
	}

	/**
	 * denswall Werte werden uebergeben
	 * 
	 * @return denswall
	 */

	public int getdensWall() {
		return densWall;
	}

	/**
	 * randomlvl Werte werden uebergeben
	 * 
	 * @return randomlvl
	 */

	public boolean getrandomlvl() {
		return randomlvl;
	}

	/**
	 * spiegelgung Werte werden uebergeben
	 * 
	 * @return spiegelung
	 */

	public boolean getspiegelung() {
		return spiegelung;
	}

	/***
	 * standardlvl Werte werden uebergeben
	 * 
	 * @return standardlvl
	 */

	public boolean getstandardlvl() {
		return standardlvl;
	}

	/**
	 * bmllevel Werte werden uebergeben
	 * 
	 * @return bmllevel
	 */

	public boolean getbmllevel() {
		return bmllevel;
	}

	/**
	 * boolKI Werte werden uebergeben
	 * 
	 * @return boolKI
	 */

	public boolean getboolKI() {
		return boolKI;
	}

	/**
	 * boolLAN Werte werden uebergeben
	 * 
	 * @return boolLAN
	 */

	public boolean getboolLAN() {
		return boolLAN;
	}

	/**
	 * boolClient Werte werden uebergeben
	 * 
	 * @return boolClient
	 */

	public boolean getboolClient() {
		return boolClient;
	}

	/**
	 * sound Werte werden uebergeben
	 * 
	 * @return sound
	 */

	public boolean getSound() {
		return sound;
	}

	/**
	 * MouseOverBool Werte werden uebergben
	 * 
	 * @return MouseOverBool
	 */

	public boolean getMouseOverBool() {
		// TODO Auto-generated method stub
		return MouseOverBool;
	}

	/**
	 * levelpath Werte werden uebergeben
	 * 
	 * @return levelpath
	 */

	public String getlevelpath() {
		return levelpath;
	}

	/**
	 * levelname Werte werden uebergeben
	 * 
	 * @return levelname
	 */

	public String getlevelname() {
		return levelname;
	}

	/**
	 * namePlayer 1 Werte werden uebergeben
	 * 
	 * @return namePlayer1
	 */

	public String getnamePlayer1() {
		return namePlayer1;
	}

	/**
	 * namePlayer 2 Werte werden uebergeben
	 * 
	 * @return namePlayer2
	 */

	public String getnamePlayer2() {
		return namePlayer2;
	}

	/**
	 * namePlayer3 Werte werden uebergeben
	 * 
	 * @return namePlayer3
	 */

	public String getnamePlayer3() {
		return namePlayer3;
	}

	/**
	 * namePlayer4 Werte werden uebergeben
	 * 
	 * @return namePlayer4
	 */

	public String getnamePlayer4() {
		return namePlayer4;
	}

	/**
	 * remotehost Werte werden uebergeben
	 * 
	 * @return remotehost
	 */

	public IRemoteHost getRemoteHost() {
		return remotehost;
	}

	/**
	 * remotehost Werte werden uebergeben
	 * 
	 * @return remotehost
	 */

	public IRemoteClient getRemoteClient() {
		return remoteclient;
	}

	// set-Methoden

	/**
	 * amplyers wird aufgerufen
	 * 
	 * @param players
	 */

	public void setamplayer(int players) {
		this.amplayer = players;
	}

	/**
	 * feldx wird aufgerufen
	 * 
	 * @param feldx
	 */

	public void setfeldx(int feldx) {
		this.feldx = feldx;
	}

	/**
	 * feldy wird aufgerufen
	 * 
	 * @param feldy
	 */

	public void setfeldy(int feldy) {
		this.feldy = feldy;
	}

	/**
	 * feldx wird aufgerufen
	 * 
	 * @param feldx
	 */

	public void setfeldxbml(int feldxbml) {
		this.feldxbml = feldxbml;
	}

	/**
	 * feldy wird aufgerufen
	 * 
	 * @param feldy
	 */

	public void setfeldybml(int feldybml) {
		this.feldybml = feldybml;
	}

	/**
	 * densWall wird aufgerufen
	 * 
	 * @param densWall
	 */

	public void setdensWall(int densWall) {
		this.densWall = densWall;
	}

	/**
	 * lvl wird aufgerufen
	 * 
	 * @param lvl
	 */

	public void setrandomlvl(boolean lvl) {
		this.randomlvl = lvl;
	}

	/**
	 * spiegelung wird aufgerufen
	 * 
	 * @param spiegelung
	 */

	public void setspiegelung(boolean spiegelung) {
		this.spiegelung = spiegelung;
	}

	/**
	 * lvl wird aufgerufen
	 * 
	 * @param lvl
	 */

	public void setstandardlvl(boolean lvl) {
		this.standardlvl = lvl;
	}

	/**
	 * bmllevel wird aufgerufen
	 * 
	 * @param bmllevel
	 */

	public void setbmllevel(boolean bmllvl) {
		this.bmllevel = bmllvl;
	}

	/**
	 * boolKI wird aufgerufen
	 * 
	 * @param boolKI
	 */

	public void setboolKI(boolean boolKI) {
		this.boolKI = boolKI;
	}

	/**
	 * boolLan wird aufgerufen
	 * 
	 * @param boolLAN
	 */

	public void setboolLAN(boolean boolLAN) {
		this.boolLAN = boolLAN;
	}

	/**
	 * boolClient wird aufgerufen
	 * 
	 * @param boolClient
	 */

	public void setboolClient(boolean boolClient) {
		this.boolClient = boolClient;
	}

	/**
	 * sound wird aufgerufen
	 * 
	 * @param sound
	 */

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	/**
	 * MouseOverBool wird aufgerufen
	 * 
	 * @param MouseOverBool
	 */

	public void setMouseOverBool(boolean MouseOverBool) {
		this.MouseOverBool = MouseOverBool;
	}

	/**
	 * namePlayer1 wird aufgerufen
	 * 
	 * @param namePlayer1
	 */

	public void setlevelpath(String levelpath) {
		this.levelpath = levelpath;
	}

	/**
	 * levelname wird aufgerufen
	 * 
	 * @param levelname
	 */

	public void setlevelname(String levelname) {
		this.levelname = levelname;
	}

	/**
	 * namePlayer1 wird aufgerufen
	 * 
	 * @param namePlayer1
	 */

	public void setnamePlayer1(String namePlayer1) {
		this.namePlayer1 = namePlayer1;
	}

	/**
	 * namePlayer2 wird aufgerufen
	 * 
	 * @param namePlayer2
	 */

	public void setnamePlayer2(String namePlayer2) {
		this.namePlayer2 = namePlayer2;
	}

	/**
	 * namePlayer3 wird aufgerufen
	 * 
	 * @param namePlayer3
	 */

	public void setnamePlayer3(String namePlayer3) {
		this.namePlayer3 = namePlayer3;
	}

	/**
	 * namePlayer4 wird aufgerufen
	 * 
	 * @param namePlayer4
	 */

	public void setnamePlayer4(String namePlayer4) {
		this.namePlayer4 = namePlayer4;
	}

	/**
	 * remotehost wird aufgerufen
	 * 
	 * @return remotehost
	 */

	public void setRemoteHost(IRemoteHost remotehost) {
		this.remotehost = remotehost;
	}

	/**
	 * remotehost wird aufgerufen
	 * 
	 * @return remotehost
	 */

	public void setRemoteClient(IRemoteClient remoteclient) {
		this.remoteclient = remoteclient;
	}

	public int getHighscoreP1() {
		return highscoreP1;
	}

	public void setHighscoreP1(int highscoreP1) {
		this.highscoreP1 = highscoreP1;
	}

	public int getHighscoreP2() {
		return highscoreP2;
	}

	public void setHighscoreP2(int highscoreP2) {
		this.highscoreP2 = highscoreP2;
	}

	public String getMessage2P() {
		return message2P;
	}

	public void setMessage2P(String message2p) {
		this.message2P = message2p;
	}

	/**
	 * Ausgabe einiger Werte der Symstemeinstellungen
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
