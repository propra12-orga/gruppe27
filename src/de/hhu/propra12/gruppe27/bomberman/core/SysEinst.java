package de.hhu.propra12.gruppe27.bomberman.core;

/* Systemeinstellungen um ein neues Spielfeld zu erstellen
 * 
 * Anzahl der Spieler 	-> players
 * Feldgröße			-> feldx, feldy
 * densWall				-> Mauerdichte
 */

public class SysEinst {

	int players = 1;
	int feldx = 15;
	int feldy = 15;
	int densWall = 1;

	String namePlayer1 = "Spieler 1";
	String namePlayer2 = "Spieler 2";

	// get-Methoden

	public int getplayers() {
		return players;
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

	// set-Methoden

	public void setplayers(int players) {
		this.players = players;
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

}
