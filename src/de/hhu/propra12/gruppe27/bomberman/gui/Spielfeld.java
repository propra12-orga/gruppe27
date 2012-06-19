// <<<<<<< HEAD
package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author
 * @version 1.0 Klasse Spielfeld implementiert ActionListener
 */

public class Spielfeld extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	public Level level;
	Timer t;
	private PlayerManager Players;
	private BombManager Bombs;
	public Exit e;
	private GameWindow owner;
	SysEinst system = new SysEinst();

	// private static Level loadlevel(int levelnr, int laenge, int breite,
	// int spielerzahl) {
	private static Level loadlevel(int levelnr, SysEinst system) {

		// this.system = system;
		return new Level0(system.getfeldx(), system.getfeldy(),
				system.getamplayer());
	}

	/**
	 * 
	 * @param levelnr
	 * @param laenge
	 * @param breite
	 * @param spielerzal
	 *            Konstruktur wird erstellt
	 */

	// Konstruktor
	public Spielfeld(int levelnr, SysEinst system, GameWindow owner) {

		this.system = system;
		this.owner = owner;

		level = loadlevel(0, system);

		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 500);
		this.setVisible(true);
		e = new Exit(
				level.getFeld(system.getfeldx() - 2, system.getfeldy() - 2)); // asugang
		// level.setFeld(new Path(laenge - 2, breite - 2, level), laenge - 2,
		// breite - 2);
		Bombs = new BombManager(this);
		Players = new PlayerManager(this);
		Players.addPlayer(new KeyPlayer(1, 1, "Spieler1", this, new Keyset(1)));
		// TODO menüanbindung Mehrspieler
		if (system.getamplayer() > 1)
			Players.addPlayer(new KeyPlayer(1, 1, "Spieler2", this, new Keyset(
					2)));
		this.repaint();
		this.startgame();
	}

	/**
	 * Spielstart
	 */

	private void startgame() {
		t = new Timer(500, this);
		t.start();

	}

	/**
	 * Zeit von Bombe laeuft ab, wenn Ende Bombe explodiert
	 */

	private void StatusUpdate() {

		// TODO Exithandling

		if (!Bombs.isEmpty())
			Bombs.CheckBombs();// bomben ticken oder explodieren lassen
		if (Players.checkGameEnde())
			e.doOnExit(this);

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return level
	 */

	public AbstractFeld getFeld(int x, int y) {
		return level.getFeld(x, y);
	}

	/**
	 * 
	 * @param input
	 * @param x
	 * @param y
	 */

	public void setFeld(AbstractFeld input, int x, int y) {
		level.setFeld(input, x, y);
	}

	/**
	 * 
	 * @param b
	 */

	public void plantBomb(Bomb b) {
		Bombs.AddBomb(b);
	}

	/**
	 * 
	 * @param Feld
	 */

	public void hitThings(AbstractFeld Feld) {
		Bombs.hitBombs(Feld);// Bomben zerstören
		// TODO Spieler Töten
		Players.hitPlayers(Feld);// später auch spieler treffen
	}

	/**
	 * Einstellungen des Levels
	 */

	protected void paintComponent(Graphics g) {
		StatusUpdate();
		for (int i = 0; i < level.getlaenge(); i++)
			for (int j = 0; j < level.getbreite(); j++) {
				g.setColor(level.getFeld(i, j).getColor());
				g.fillRect(i * 32, j * 32, 32, 32);
			}

		/**
		 * Aussehen des Spielers wird festgelegt
		 */

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

	public void dispose() {

		owner.dispose();
	}
}
