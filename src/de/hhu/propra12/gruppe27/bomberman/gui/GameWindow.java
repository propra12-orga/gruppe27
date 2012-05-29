package de.hhu.propra12.gruppe27.bomberman.gui;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameWindow(int levelnr, int laenge, int breite, int spilerzahl) {
		add(new Spielfeld(levelnr, laenge, breite, spilerzahl));
		setSize(laenge * 32, breite * 32 + 24);
		setVisible(true);
		repaint();

	}
}
