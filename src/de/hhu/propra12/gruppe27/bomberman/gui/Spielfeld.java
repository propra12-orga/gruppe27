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
import de.hhu.propra12.gruppe27.bomberman.core.Path;
import de.hhu.propra12.gruppe27.bomberman.core.Player;
import de.hhu.propra12.gruppe27.bomberman.core.PlayerManager;

public class Spielfeld extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public Level level;
	public KeyPlayer p1;
	public Player p2;
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
		level.textout();// testweise spielfeld in konsole zeichnen

		// for(int i=0; i< spielerzal; i++){ //für mehr spieler anzupassen!
		// int[] pos = level.getStartposition(0);
		p1 = new KeyPlayer(1, 1, "Spieler1", this, new Keyset(1));
		// p2 = new Player(laenge - 2, breite - 2, "Buhman", this, 2);
		// }
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(laenge * 32, breite * 32 + 500);
		this.setVisible(true);
		level.textout();
		level.setFeld(new Path(laenge - 2, breite - 2, level), laenge - 2,
				breite - 2); // platz für ausgang schaffen später auch durch
								// level übernommen
		e = new Exit(laenge - 2, breite - 2); // asugang plazieren
		Bombs = new BombManager(this);// init Bombmanager
		Players = new PlayerManager(this);
		this.repaint();
		this.startgame();
	}

	private void startgame() {
		t = new Timer(500, this);
		t.start();

	}

	private void StatusUpdate() {
		if ((p1.getX() == e.getX()) && (p1.getY() == e.getY()))
			e.doOnExit(this);
		// if ((p2.getX() == e.getX()) && (p2.getY() == e.getY()))
		// e.doOnExit(this);
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
		// Players.hitPlayers(Feld);//später auch spieler treffen
	}

	protected void paintComponent(Graphics g) {
		StatusUpdate();
		for (int i = 0; i < level.getlaenge(); i++)
			for (int j = 0; j < level.getbreite(); j++) {
				g.setColor(level.getFeld(i, j).getColor());
				g.fillRect(i * 32, j * 32, 32, 32);
			}
		g.setColor(Color.GREEN);// zeichne spieler
		// g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 * 32);
		g.drawLine(p1.getX() * 32, p1.getY() * 32, p1.getX() * 32 + 32,
				p1.getY() * 32 + 32);
		g.drawLine(p1.getX() * 32 + 32, p1.getY() * 32, p1.getX() * 32,
				p1.getY() * 32 + 32);

		/*
		 * g.setColor(Color.BLUE); // g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 *
		 * 32); g.drawLine(p2.getX() * 32, p2.getY() * 32, p2.getX() * 32 + 32,
		 * p2.getY() * 32 + 32); g.drawLine(p2.getX() * 32 + 32, p2.getY() * 32,
		 * p2.getX() * 32, p2.getY() * 32 + 32);
		 */
		if (!Bombs.isEmpty())
			Bombs.paintBombs(g);// ausgabe der bomben(später auch
								// explosionsgrafiken)
		g.setColor(Color.pink);
		g.drawOval(e.getX() * 32, e.getY() * 32, 32, 32);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		p1.move();
		// p2.move();
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
			p1.update(e.getKeyCode(), true);
			// p2.update(e.getKeyCode(), true);
		}

		public void keyReleased(KeyEvent e) {
			p1.update(e.getKeyCode(), false);
			// p2.update(e.getKeyCode(), false);
		}
	}

}

/*
 * ======= package de.hhu.propra12.gruppe27.bomberman.gui;
 * 
 * import java.awt.Color; import java.awt.Graphics; import
 * java.awt.event.ActionEvent; import java.awt.event.ActionListener; import
 * java.awt.event.KeyAdapter; import java.awt.event.KeyEvent;
 * 
 * import javax.swing.JFrame; import javax.swing.JPanel; import
 * javax.swing.Timer;
 * 
 * import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld; import
 * de.hhu.propra12.gruppe27.bomberman.core.Bomb; import
 * de.hhu.propra12.gruppe27.bomberman.core.Level; import
 * de.hhu.propra12.gruppe27.bomberman.core.Level0; import
 * de.hhu.propra12.gruppe27.bomberman.core.Path; import
 * de.hhu.propra12.gruppe27.bomberman.core.Player;
 * 
 * public class Spielfeld extends JPanel implements ActionListener {
 * 
 * private static final long serialVersionUID = 1L; public Level level; public
 * Player p1; Timer t; public Bomb b1; public Exit e;
 * 
 * private static Level loadlevel(int levelnr, int laenge, int breite, int
 * spielerzahl) { return new Level0(laenge, laenge, spielerzahl); }
 * 
 * // Konstruktor public Spielfeld(int levelnr, int laenge, int breite, int
 * spielerzal) { level = loadlevel(0, laenge, breite, spielerzal);
 * level.textout();// testweise spielfeld in konsole zeichnen
 * 
 * // for(int i=0; i< spielerzal; i++){ //für mehr spieler anzupassen! // int[]
 * pos = level.getStartposition(0); p1 = new Player(1, 1, "Hernman", this); // }
 * this.addKeyListener(new TAdapter()); this.setFocusable(true);
 * this.setSize(laenge * 32, breite * 32 + 500); this.setVisible(true);
 * level.textout(); level.setFeld(new Path(laenge - 2, breite - 2, level),
 * laenge - 2, breite - 2); // platz für ausgang schaffen später auch durch //
 * level übernommen e = new Exit(laenge - 2, breite - 2); // asugang plazieren
 * this.repaint(); this.startgame(); b1 = new Bomb(); }
 * 
 * private void startgame() { t = new Timer(500, this); t.start();
 * 
 * }
 * 
 * private void StatusUpdate() { if ((p1.getX() == e.getX()) && (p1.getY() ==
 * e.getY())) e.doOnExit(this); if (b1.isPlanted()) b1.check();// TODO später
 * automatisch alle bomben auf spielfeld }
 * 
 * public AbstractFeld getFeld(int x, int y) { return level.getFeld(x, y); }
 * 
 * public void setFeld(AbstractFeld input, int x, int y) { level.setFeld(input,
 * x, y); }
 * 
 * protected void paintComponent(Graphics g) { StatusUpdate(); for (int i = 0; i
 * < level.getlaenge(); i++) for (int j = 0; j < level.getbreite(); j++) {
 * g.setColor(level.getFeld(i, j).getColor()); g.fillRect(i * 32, j * 32, 32,
 * 32); } g.setColor(Color.GREEN); // g.fillRect(1 * 32, 1 * 32, 1 * 32, 1 *
 * 32); g.drawLine(p1.getX() * 32, p1.getY() * 32, p1.getX() * 32 + 32,
 * p1.getY() * 32 + 32); g.drawLine(p1.getX() * 32 + 32, p1.getY() * 32,
 * p1.getX() * 32, p1.getY() * 32 + 32); if (b1.isPlanted()) {
 * g.drawOval(b1.getPosx() * 32, b1.getPosy() * 32, 32, 32); }
 * g.setColor(Color.pink); g.drawOval(e.getX() * 32, e.getY() * 32, 32, 32);
 * 
 * }
 * 
 * @Override public void actionPerformed(ActionEvent arg0) { p1.move();
 * repaint();
 * 
 * }
 * 
 * // Mainfunktion nur zum testen um Menü zu überspringen public static void
 * main(String[] args) {// um nicht immer durch Startmenü zu // müssen später
 * entfernen.
 * 
 * @SuppressWarnings("unused") JFrame f = new GameWindow(0, 13, 13, 1); }
 * 
 * // ------------------------------------------------------
 * 
 * private class TAdapter extends KeyAdapter { public void keyPressed(KeyEvent
 * e) { p1.update(e.getKeyCode(), true); }
 * 
 * public void keyReleased(KeyEvent e) { p1.update(e.getKeyCode(), false); } }
 * 
 * } >>>>>>> 990ed1b2d3afa24fa90ce7ab3a767be1bbfffa4f
 */
