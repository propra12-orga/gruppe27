package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.JPanel;
import javax.swing.Timer;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;
import de.hhu.propra12.gruppe27.bomberman.core.Bomb;
import de.hhu.propra12.gruppe27.bomberman.core.BombManager;
import de.hhu.propra12.gruppe27.bomberman.core.KeyPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Keyset;
import de.hhu.propra12.gruppe27.bomberman.core.LanPlayer;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.LevelGen;
import de.hhu.propra12.gruppe27.bomberman.core.PlayerManager;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author
 * @version 1.0 Klasse Spielfeld implementiert ActionListener
 */

public class Spielfeld extends JPanel implements ActionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private Level level;
	Timer t;
	private PlayerManager Players;
	private BombManager Bombs;
	private Exit e;
	private GameWindow owner;
	private SysEinst system = SysEinst.getSystem();
	private transient Image imagezerwand, imageexit, imagewand;

	/*
	 * Generierung des Spielfeldes
	 */

	private static Level loadlevel(int levelnr) {
		SysEinst system = SysEinst.getSystem();
		if (false == system.getbmllevel()) {
			return new LevelGen(system.getfeldx(), system.getfeldy(),
					system.getamplayer());
		} else {
			return new LevelGen(system.getfeldxbml(), system.getfeldybml(),
					system.getamplayer(), system.getbmllevel());

		}
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
	public Spielfeld(int levelnr, GameWindow owner) {
		this(loadlevel(0), owner);
	}

	/**
	 * 
	 * @param level
	 * @param laenge
	 * @param breite
	 * @param spielerzal
	 *            Konstruktur wird erstellt
	 */

	// Konstruktor
	public Spielfeld(Level level, GameWindow owner) {
		this.level = level;
		this.owner = owner;

		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 500);
		this.setVisible(true);

		// Ausgang wird nur bei Solospiel und im 2 Spielermodus gesetzt
		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			setrandomexit();
		}
		// ansonsten wird der Exit auf ein nicht erreichbares Feld gesetzt
		else {
			e = new Exit(level.getFeld(0, 0));
		}

		Bombs = new BombManager(this);
		Players = new PlayerManager(this);

		// Highscore löschen
		system.setHighscoreP1(0);
		system.setHighscoreP2(0);

		/*
		 * Spieler werden zugefuegt
		 * 
		 * je nach bool Werten fuer Solo- und 2 Spielermodus oder fuer das
		 * LanSpiel
		 */

		if (system.getboolLAN() == false) {
			Players.addPlayer(new KeyPlayer(1, 1, "Spieler1", this, new Keyset(
					1)));

			if (system.getamplayer() > 1) {
				KeyPlayer player2 = (KeyPlayer) new KeyPlayer(
						system.getfeldx() - 2, system.getfeldy() - 2,
						"Spieler2", this, new Keyset(2)).withColor(new Color(
						255, 0, 0));
				Players.addPlayer(player2);
			}
		}

		else {
			if (system.getboolClient()) {
				Players.addPlayer(new LanPlayer(system.getfeldx() - 2, system
						.getfeldy() - 2, "Spieler1", this, new Keyset(1)));

				LanPlayer player2 = (LanPlayer) new LanPlayer(1, 1, "Spieler2",
						this, new Keyset(-1)).withColor(new Color(255, 0, 0));
				Players.addPlayer(player2);
			} else {
				Players.addPlayer(new LanPlayer(1, 1, "Spieler1", this,
						new Keyset(1)));

				LanPlayer player2 = (LanPlayer) new LanPlayer(
						system.getfeldx() - 2, system.getfeldy() - 2,
						"Spieler2", this, new Keyset(-1)).withColor(new Color(
						255, 0, 0));
				Players.addPlayer(player2);
			}
		}

		initImages();
		this.repaint();
		this.startgame();
	}

	/**
	 * Spielstart
	 */

	public void startgame() {
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

		// Prüfung, ob das Spiel zu Ende ist
		int intendgame = Players.checkGameEnde();

		// was passiert, wenn das Spiel zu Ende ist,
		if (intendgame > PlayerManager.ENDE) {

			// highscores werden gespeichert in den Systemeinstellungen
			system.setHighscoreP1(Players.PlayerList.get(0).getCountthesteps());
			system.setHighscoreP2(Players.PlayerList.get(0).getCountthesteps());

			// Fall, wenn es ein Singleplayerspiel ist
			if (system.getamplayer() == 1) {

				if (PlayerManager.ALLDEAD == intendgame)
					e.doOnKill(this);
				else if (PlayerManager.EXIT == intendgame) {
					System.out.println("Anzahl der Schritte: "
							+ Players.PlayerList.get(0).getCountthesteps());
					e.doOnExit(this);
				}
			}

			// Fall, wenn es der 2Spieler-Modus ist
			else if (PlayerManager.ALLDEAD == intendgame) {

				// if (PlayerManager.ALLDEAD == Players.checkGameEnde())
				e.doOnKill(this);

			}

			// Fall, wenn es der Netzwerkmodus ist
			else if (system.getboolLAN()) {
				if (PlayerManager.LASTMAN == Players.checkGameEnde())
					e.doOnLastMan(this);
				if (PlayerManager.LASTMANP2 == Players.checkGameEnde())
					e.doOnLastManP2(this);

			}
		}
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

				// TODO Checken wieso die nächsten 2 Zeilen nötig sind
				g.setColor(level.getFeld(i, j).getColor());
				g.fillRect(i * 32, j * 32, 32, 32);

				if (level.getFeld(i, j).isFrei() == true) {
				} else if (level.getFeld(i, j).isZerstoer() == true) {
					g.drawImage(imagezerwand, level.getFeld(i, j).getX() * 32,
							level.getFeld(i, j).getY() * 32, 32, 32, owner);
				} else {
					g.drawImage(imagewand, level.getFeld(i, j).getX() * 32,
							level.getFeld(i, j).getY() * 32, 32, 32, owner);
				}
				// g.drawImage(image, Feld.getX() * 32, Feld.getY() * 32, 32,
				// 32, pg);
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

		// g.drawOval(e.getX() * 32, e.getY() * 32, 31, 31);

		// Exit wird nur im Singleplayer-Spiel überschrieben
		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			g.drawImage(imageexit, e.getX() * 32, e.getY() * 32, 32, 32, owner);

			// Bei bedarf eingangüberzeichnen.
			if (!getFeld(e.getX(), e.getY()).isFrei()) {

				g.drawImage(imagezerwand, e.getX() * 32, e.getY() * 32, 32, 32,
						owner);

				// g.setColor(level.getFeld(e.getX(), e.getY()).getColor());
				// g.fillRect(e.getX() * 32, e.getY() * 32, 32, 32);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Players.movePlayers();
		repaint();
	}

	private class TAdapter extends KeyAdapter implements Serializable {

		private static final long serialVersionUID = 1L;

		public void keyPressed(KeyEvent e) {
			Players.updatePlayers(e.getKeyCode(), true);
			// p2.update(e.getKeyCode(), true);
			// System.out.println("keypressed " + e.getKeyCode());
		}

		public void keyReleased(KeyEvent e) {
			Players.updatePlayers(e.getKeyCode(), false);
			// p2.update(e.getKeyCode(), false);
		}
	}

	public void dispose() {

		owner.dispose();
	}

	// nicht löschen, braucht man für Netzwerk
	public void setowner(GameWindow owner) {
		this.owner = owner;
	}

	public SysEinst getsystem() {
		return system;
	}

	public void initImages() {

		imagezerwand = Toolkit
				.getDefaultToolkit()
				.getImage(
						"src/de/hhu/propra12/gruppe27/bomberman/graphics/ZerstoerbareWand.gif");
		imagewand = Toolkit.getDefaultToolkit().getImage(
				"src/de/hhu/propra12/gruppe27/bomberman/graphics/Wand.gif");

		imageexit = Toolkit.getDefaultToolkit().getImage(
				"src/de/hhu/propra12/gruppe27/bomberman/graphics/TorTranz.gif");

	}

	public Level getLevel() {
		return level;
	}

	public PlayerManager getPlayers() {
		return Players;
	}

	public Exit getExit() {
		return e;
	}

	int exitx;
	int exity;

	private void setrandomexit() {

		if (system.getrandomlvl() == true) {
			setcoords();
		}

		else {
			do {
				setcoords();
			} while ((exitx % 2) == 0 && (exity % 2) == 0);
		}

		e = new Exit(level.getFeld(exitx, exity));
	}

	private void setcoords() {

		do {
			this.exitx = getrandomcoordx();
			this.exity = getrandomcoordy();

		} while ((exitx == 0 || exitx == system.getfeldx() - 1 || exity == 0 || exity == system
				.getfeldy() - 1)
				|| ((exitx == 1 && exity == 1) || (exitx == 1 && exity == 2) || (exitx == 2 && exity == 1))
				|| ((exitx == system.getfeldx() - 2 && exity == system
						.getfeldy() - 2)
						|| (exitx == system.getfeldx() - 3 && exity == system
								.getfeldy() - 2) || (exitx == system.getfeldx() - 2 && exity == system
						.getfeldy() - 3)));

	}

	private int getrandomcoordx() {
		int x = system.getfeldx();
		x = (int) (x * Math.random());
		return x;
	}

	private int getrandomcoordy() {
		int y = system.getfeldy();
		y = (int) ((int) y * Math.random());
		return y;
	}
}
