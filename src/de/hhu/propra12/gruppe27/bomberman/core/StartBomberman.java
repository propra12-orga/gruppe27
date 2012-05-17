package de.hhu.propra12.gruppe27.bomberman.core;

import de.hhu.propra12.gruppe27.bomberman.gui.Startmenue;

public class StartBomberman {

	/**
	 * Start des Programms
	 */

	public static void main(String[] args) {

		// Menue-Aufruf
		Startmenue start = new Startmenue();
		start.menueaufruf();

		Level0 pg = new Level0(11, 11, 1);
		pg.textout();

	}
}
