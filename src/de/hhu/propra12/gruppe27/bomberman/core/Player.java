package de.hhu.propra12.gruppe27.bomberman.core;

public class Player {

	int posx; // entspricht i im Array
	int posy; // enstpricht j im Array

	int bombstr; // Bombenstärke 1 pro Feld
	int bombanz; // Bombenanzahl

	// int speed;

	String name;

	// Konstruktor
	Player(int posx, int posy, int bombstr, int bombanz, String p1name) {

		this.posx = posx;
		this.posy = posy;
		this.bombstr = bombstr;
		this.bombanz = bombanz;
		this.name = p1name;

	}

	// Bewegungs-Methoden

	/*
	 * 
	 * moveleft (){ }
	 * 
	 * moveright (){ }
	 * 
	 * moveup (){ }
	 * 
	 * movedown (){ }
	 * 
	 * laybomb() { }
	 */

}
