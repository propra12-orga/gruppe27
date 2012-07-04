package de.hhu.propra12.gruppe27.bomberman.core;

import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteClient;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.IRemoteHost;

/**
 * 
 * @author
 * @verion 1.0 Einstellungen um ein Spielfeld zu erstellen, player = Anzahl der
 *         Spieler, Feldgï¿½ï¿½e = feldx, feldy, denswall = Mauerdichte,
 *         randomlvl = Generiertes Level, spiegelung = Level soll gespiegelt
 *         werden, standardlvl = Festgelegte unerstï¿½rbare Wï¿½nde im Level,
 *         namePlayer = Name des Spielers
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
	 * @return amplayer
	 */

	// get-Methoden

	public int getamplayer() {
		return amplayer;
	}

	/**
	 * 
	 * @return Gibt die Spielfeldgröße "X" zurück.
	 */

	public int getfeldx() {
		return feldx;
	}

	/**
	 * 
	 * @return Gibt die aus einer BML-Datei eingelesene Spielfeldgröße "X"
	 *         zurück.
	 */
	public int getfeldxbml() {
		return feldxbml;
	}

	/**
	 * 
	 * @return Gibt die Spielfeldgröße "Y" zurück.
	 */

	public int getfeldy() {
		return feldy;
	}

	/**
	 * 
	 * @return Gibt die aus einer BML-Datei eingelesene Spielfeldgröße "Y"
	 *         zurück.
	 */

	public int getfeldybml() {
		return feldybml;
	}

	/**
	 * 
	 * @return Gibt die eingestellte Wandstärke zurück.
	 */

	public int getdensWall() {
		return densWall;
	}

	/**
	 * 
	 * @return Gibt True bei aktivierter "Zufallslevel"-Einstellung und False
	 *         bei deaktivierter "Zufallslevel"-Einstellung zurück.
	 */

	public boolean getrandomlvl() {
		return randomlvl;
	}

	/**
	 * 
	 * @return Gibt True bei aktivierter "Levelspiegelung" und False bei
	 *         deaktivierter "Levelspiegelung" zurück.
	 */

	public boolean getspiegelung() {
		return spiegelung;
	}

	/***
	 * 
	 * @return Gibt True bei aktiviertem "Standardlevel" und False bei
	 *         deaktiviertem "Standardlevel" zurück.
	 */

	public boolean getstandardlvl() {
		return standardlvl;
	}

	/**
	 * 
	 * @return Gibt True bei aktivierter "BML-Importierung" und False bei
	 *         deaktivierter "BML-Importierung" zurück.
	 */

	public boolean getbmllevel() {
		return bmllevel;
	}

	/**
	 * 
	 * @return Gibt True bei aktivierter "KI" und False bei deaktivierter "KI"
	 *         zurück.
	 */

	public boolean getboolKI() {
		return boolKI;
	}

	/**
	 * 
	 * @return Gibt True bei "LAN-Spiel"-Aktivierung und False bei
	 *         "LAN-Spiel"-Deaktivierung zurück.
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

	/**
	 * 
	 * @return Gibt True bei aktiviertem "Sound" und False bei deaktiviertem
	 *         "Sound" zurück.
	 */

	public boolean getSound() {
		return sound;
	}

	/**
	 * 
	 * @return True - Button-MouseOvers(Sound) werden bei Start initialisiert.
	 *         False - Button-MouseOvers(Sound) werden bei Start initialisiert.
	 */

	public boolean getMouseOverBool() {
		// TODO Auto-generated method stub
		return MouseOverBool;
	}

	/**
	 * 
	 * @return Gibt den gespeicherten Levelpfad zurück.
	 */

	public String getlevelpath() {
		return levelpath;
	}

	/**
	 * 
	 * @return Gibt den Namen, des aktuellen Level zurück. (Sofern definiert.)
	 */

	public String getlevelname() {
		return levelname;
	}

	/**
	 * 
	 * @return Gibt den Namen, des ersten Spielers zurück.
	 */

	public String getnamePlayer1() {
		return namePlayer1;
	}

	/**
	 * 
	 * @return Gibt den Namen, des zweiten Spielers zurück.
	 */

	public String getnamePlayer2() {
		return namePlayer2;
	}

	/**
	 * 
	 * @return Gibt den Namen, des dritten Spielers zurück.
	 */

	public String getnamePlayer3() {
		return namePlayer3;
	}

	/**
	 * 
	 * @return Gibt den Namen, des vierten Spielers zurück.
	 */

	public String getnamePlayer4() {
		return namePlayer4;
	}

	/**
	 * 
	 * @return Gibt die Adresse, des Host zurück.
	 */

	public IRemoteHost getRemoteHost() {
		return remotehost;
	}

	/**
	 * 
	 * @return Gibt die Adresse, des Client zurück.
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
