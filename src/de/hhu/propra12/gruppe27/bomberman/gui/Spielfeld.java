package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.Level0;
import de.hhu.propra12.gruppe27.bomberman.core.Player;

public class Spielfeld extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public Level level;
	public Player p1;
	private Timer t;

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
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(laenge * 32, breite * 32 + 500);
		this.setVisible(true);
		level.textout();
		this.repaint();
		this.startgame();
	}

	private void startgame() {
		t = new Timer(300, this);
		t.start();

	}

	public AbstractFeld getFeld(int x, int y) {
		return level.getFeld(x, y);
	}

	protected void paintComponent(Graphics g) {
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
	public void actionPerformed(ActionEvent arg0) {
		p1.move();
		repaint();
	}

	public static void main(String[] args) {// um nicht immer durch Startmenü zu
											// müssen später entfernen.
		@SuppressWarnings("unused")
		JFrame f = new GameWindow(0, 13, 13, 1);
	}

	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			p1.update(e.getKeyCode(), true);
			System.out.println("Key Pressed");
		}

		public void keyReleased(KeyEvent e) {
			p1.update(e.getKeyCode(), false);
		}
	}

}
