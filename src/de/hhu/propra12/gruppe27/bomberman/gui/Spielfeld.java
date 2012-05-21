package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.Level0;
import de.hhu.propra12.gruppe27.bomberman.core.Player;

public class Spielfeld extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	public Level level;
	public Player p1;

	private static Level loadlevel(int levelnr, int laenge, int breite,
			int spielerzahl) {
		return new Level0(laenge, laenge, spielerzahl);
	}

	// Konstruktor
	public Spielfeld(int levelnr, int laenge, int breite, int spielerzal) {
		level = loadlevel(0, laenge, breite, spielerzal);
		level.textout();// testweise spielfeld in konsole zeichnen

		// for(int i=0; i< spielerzal; i++){ //für mehr spieler anzupassen!
		// int[] pos = level.getStartposition(0);
		p1 = new Player(1, 1, "Hernman", this);
		// }
		this.setSize(laenge * 32, breite * 32 + 500);
		this.setVisible(true);
		this.repaint();
	}

	public AbstractFeld getFeld(int x, int y) {
		return level.getFeld(x, y);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		for (int i = 0; i < level.getlaenge(); i++)
			for (int j = 0; j < level.getbreite(); j++) {
				g.setColor(level.getFeld(i, j).getColor());
				g.fillRect(i * 32, j * 32, 32, 32);
			}
		g.setColor(Color.GREEN);
		// g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 * 32);
		g.drawLine(p1.getX() * 32, p1.getY() * 32, p1.getX() * 32 + 32,
				p1.getY() * 32 + 32);
		g.drawLine(p1.getX() * 32 + 32, p1.getY() * 32, p1.getX() * 32,
				p1.getY() * 32 + 32);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		p1.update(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		p1.update(e.getKeyCode(), false);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
