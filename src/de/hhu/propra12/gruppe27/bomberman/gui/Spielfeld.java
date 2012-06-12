// <<<<<<< HEAD
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
import de.hhu.propra12.gruppe27.bomberman.core.Bomb;
import de.hhu.propra12.gruppe27.bomberman.core.BombManager;
import de.hhu.propra12.gruppe27.bomberman.core.KeyPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Keyset;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.Level0;
import de.hhu.propra12.gruppe27.bomberman.core.PlayerManager;

public class Spielfeld extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public Level level;
	Timer t;
	private PlayerManager Players;
	private BombManager Bombs;
	public Exit e;

	private static Level loadlevel(int levelnr, int laenge, int breite,
			int spielerzahl) {
		return new Level0(laenge, laenge, spielerzahl);
	}

	// Konstruktor
	public Spielfeld(int levelnr, int laenge, int breite, int spielerzal) {

		level = loadlevel(0, laenge, breite, spielerzal);

		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(laenge * 32, breite * 32 + 500);
		this.setVisible(true);
		e = new Exit(level.getFeld(laenge - 2, breite - 2)); // asugang
		// level.setFeld(new Path(laenge - 2, breite - 2, level), laenge - 2,
		// breite - 2);
		Bombs = new BombManager(this);
		Players = new PlayerManager(this);
		Players.addPlayer(new KeyPlayer(1, 1, "Spieler1", this, new Keyset(1)));
		// TODO menüanbindung Mehrspieler
		if (spielerzal > 1)
			Players.addPlayer(new KeyPlayer(1, 1, "Spieler2", this, new Keyset(
					2)));
		this.repaint();
		this.startgame();
	}

	private void startgame() {
		t = new Timer(500, this);
		t.start();

	}

	private void StatusUpdate() {

		// TODO Extihandling

		if (Players.checkGameEnde())
			e.doOnExit(this);
		if (!Bombs.isEmpty())
			Bombs.CheckBombs();// bomben ticken oder explodieren lassen
	}

	public AbstractFeld getFeld(int x, int y) {
		return level.getFeld(x, y);
	}

	public void setFeld(AbstractFeld input, int x, int y) {
		level.setFeld(input, x, y);
	}

	public void plantBomb(Bomb b) {
		Bombs.AddBomb(b);
	}

	public void hitThings(AbstractFeld Feld) {
		Bombs.hitBombs(Feld);// Bomben zerstören
		// TODO Spieler Töten
		Players.hitPlayers(Feld);// später auch spieler treffen
	}

	protected void paintComponent(Graphics g) {
		StatusUpdate();
		for (int i = 0; i < level.getlaenge(); i++)
			for (int j = 0; j < level.getbreite(); j++) {
				g.setColor(level.getFeld(i, j).getColor());
				g.fillRect(i * 32, j * 32, 32, 32);
			}
		Players.paintPlayers(g);
		if (!Bombs.isEmpty())
			Bombs.paintBombs(g);// ausgabe der bomben(später auch
								// explosionsgrafiken)
		// TODO auslagerung der Zeichenfunktion des Exit. //TODO
		// implementierung eines ItemManagers
		g.setColor(Color.pink);
		g.drawOval(e.getX() * 32, e.getY() * 32, 31, 31);
		// Bei bedarf eingangüberzeichnen.
		if (!getFeld(e.getX(), e.getY()).isFrei()) {
			g.setColor(level.getFeld(e.getX(), e.getY()).getColor());
			g.fillRect(e.getX() * 32, e.getY() * 32, 32, 32);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Players.movePlayers();
		repaint();

	}

	// Mainfunktion nur zum testen um Menü zu überspringen
	public static void main(String[] args) {// um nicht immer durch Startmenü zu
											// müssen später entfernen.
		@SuppressWarnings("unused")
		JFrame f = new GameWindow(0, 13, 13, 1);
	}

	// ------------------------------------------------------

	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			Players.updatePlayers(e.getKeyCode(), true);
			// p2.update(e.getKeyCode(), true);
		}

		public void keyReleased(KeyEvent e) {
			Players.updatePlayers(e.getKeyCode(), false);
			// p2.update(e.getKeyCode(), false);
		}
	}

}
