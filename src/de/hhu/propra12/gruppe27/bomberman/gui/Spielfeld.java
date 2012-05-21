package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.Level0;
import de.hhu.propra12.gruppe27.bomberman.core.Player;

public class Spielfeld extends JPanel implements KeyListener {

	public Level level;
	private Dimension d;
	public Player p1, p2, p3, p4;

	private static Level loadlevel(int levelnr, int laenge, int breite,
			int spielerzahl) {
		return new Level0(laenge, laenge, spielerzahl);
	}

	// Konstruktor
	public Spielfeld(int levelnr, int laenge, int breite, int spielerzal) {
		level = loadlevel(0, laenge, breite, spielerzal);
		level.textout();// testweise spielfeld in konsole zeichnen
		d = new Dimension(laenge * 32, laenge * 32);
		this.setSize(d);
		this.setVisible(true);
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawLine(0, 0, 99, 99);
		for (int i = 0; i < level.getlaenge(); i++)
			for (int j = 0; j < level.getbreite(); j++) {
				g.setColor(level.getFeld(i, j).getColor());
				g.fillRect(i * 32, j * 32, i * 32 + 32, j * 32 + 32);
			}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
