package de.hhu.propra12.gruppe27.bomberman.core;

import de.hhu.propra12.gruppe27.bomberman.gui.Startmenue;

public class StartBomberman {

	// Systemeinstellungen sind "default" gesetzt
	// static SysEinst mainsys = new SysEinst();

	/**
	 * Start des Programms
	 */
	public static void main(String[] args) {

		// Menue-Aufruf
		Startmenue start = new Startmenue();
		start.menueaufruf();

		// wird später vom Startmenue übergeben
		// Player player1 = new Player(1, 1, "Spieler1");

		// größe des Levels wird später vom Startmenue übergeben
		// Level0 pg = new Level0(11, 11, 1);

	}

	/*
	 * alternativer Start
	 * 
	 * 
	 * public static void main(String[] args) {
	 * 
	 * // Menue-Aufruf Startmenue start = new Startmenue(); SysEinst mainsys =
	 * start.menueaufruf();
	 * 
	 * mainsys.printSysEinst(); // Start des Spiels GameWindow s = new
	 * GameWindow(0, mainsys.getfeldx(), mainsys.getfeldy(), 1);
	 * 
	 * 
	 * }
	 */
}
