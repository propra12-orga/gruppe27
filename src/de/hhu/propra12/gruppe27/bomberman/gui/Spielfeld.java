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

import javax.swing.JOptionPane;
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
import de.hhu.propra12.gruppe27.bomberman.core.PathFinder;
import de.hhu.propra12.gruppe27.bomberman.core.PlayerManager;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author Gruppe 27
 * @version 1.0 Klasse Spielfeld implementiert ActionListener private-Elemente
 *          greifen auf dazugehoerige Klassen zu
 * 
 */

public class Spielfeld extends JPanel implements ActionListener, Serializable {

	private static final long serialVersionUID = 1L;
	private Level level;
	Timer t;
	private PlayerManager Players;
	private BombManager Bombs;
	private Special special;
	private GameWindow owner;
	private SysEinst system = SysEinst.getSystem();
	private transient Image imagezerwand, imageexit, imagewand, imagexplode;

	/*
	 * Generierung des Levels
	 */
	/*
	 * Konsistenzpr�fung. Sollte der Weg von 1, 1 bis 13, 13 nicht erreichbar
	 * sein wird der Spieler informiert und das Spielfeld nicht geladen.
	 */

	private static Level loadlevel(int levelnr) {
		SysEinst system = SysEinst.getSystem();
		if (!system.getbmllevel()) {
			LevelGen newLevel = new LevelGen(system.getfeldx(),
					system.getfeldy(), system.getamplayer());
			if (PathFinder.check(PathFinder.convertMap(newLevel), 1, 1,
					newLevel.getExit().getX(), newLevel.getExit().getY())) {
				return newLevel;
			} else {
				System.out
						.println("Zufalls-Level ist durch die Konsistenzpruefung gefallen!");
				JOptionPane
						.showMessageDialog(
								null,
								"Zufalls-Level ist durch die Konsistenzpruefung gefallen!\nEs wird ein neues generiert!",
								"Level-Konsitenz",
								JOptionPane.INFORMATION_MESSAGE);
				return loadlevel(0);
			}
		} else {
			LevelGen newLevel = new LevelGen(system.getfeldxbml(),
					system.getfeldybml(), system.getamplayer(),
					system.getbmllevel());
			if (PathFinder.check(PathFinder.convertMap(newLevel), 1, 1, 13, 13)) {
				return newLevel;
			} else {
				System.out
						.println("BML-Level ist durch die Konsistenzpruefung gefallen!");
				JOptionPane.showMessageDialog(null,
						"BML-Level ist durch die Konsistenzpruefung gefallen!",
						"Level-Konsitenz", JOptionPane.INFORMATION_MESSAGE);
				return null;
			}

		}
	}

	/**
	 * 
	 * @param levelnr
	 * @param owner
	 *            Parameter werden uebergeben
	 */

	public Spielfeld(int levelnr, GameWindow owner) {
		this(loadlevel(0), owner);
	}

	/**
	 * 
	 * @param level
	 * @param owner
	 *            Initialisierung von levelnr und GameWindow KeyListener wird
	 *            hinzugefuegt
	 */

	public Spielfeld(Level level, GameWindow owner) {
		this.level = level;
		this.owner = owner;

		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 500);
		this.setVisible(true);

		// setzt das Special im Solospiel
		if (system.getamplayer() == 1) {
			setrandomspec();
		}

		Bombs = new BombManager(this);
		Players = new PlayerManager(this);

		/**
		 * Highscore wird geloescht
		 */

		system.setHighscoreP1(0);
		system.setHighscoreP2(0);

		/**
		 * Spieler werden hinzugefuegt, je nach bool-Werten fuer Solo- und
		 * Zweispielermodus oder fuer das Lan-Spiel
		 */

		if (system.getboolLAN() == false) {
			Players.addPlayer(new KeyPlayer(1, 1, system.getnamePlayer1(),
					this, new Keyset(1)));

			if (system.getamplayer() > 1) {
				KeyPlayer player2 = (KeyPlayer) new KeyPlayer(
						system.getfeldx() - 2, system.getfeldy() - 2,
						system.getnamePlayer2(), this, new Keyset(2))
						.withColor(new Color(255, 0, 0));
				Players.addPlayer(player2);
			}
		}

		else {
			if (system.getboolClient()) {
				Players.addPlayer(new LanPlayer(system.getfeldx() - 2, system
						.getfeldy() - 2, system.getnamePlayer1(), this,
						new Keyset(1)));

				LanPlayer player2 = (LanPlayer) new LanPlayer(1, 1,
						system.getnamePlayer2(), this, new Keyset(-1))
						.withColor(new Color(255, 0, 0));
				Players.addPlayer(player2);
			} else {
				Players.addPlayer(new LanPlayer(1, 1, system.getnamePlayer1(),
						this, new Keyset(1)));

				LanPlayer player2 = (LanPlayer) new LanPlayer(
						system.getfeldx() - 2, system.getfeldy() - 2,
						system.getnamePlayer2(), this, new Keyset(-1))
						.withColor(new Color(255, 0, 0));
				Players.addPlayer(player2);
			}
		}

		initImages();
		this.repaint();
		this.startgame();
	}

	/**
	 * Spielstart wird generiert
	 */

	public void startgame() {
		t = new Timer(500, this);
		t.start();

	}

	/**
	 * Bomben ticken oder explodieren lassen, Pruefung, ob das Spiel zu Ende
	 * ist, was passiert, wenn das Spiel zu Ende ist, Highscores werden in den
	 * Systemeinstellungen gespeichert
	 * 
	 */

	private void StatusUpdate() {

		if (!Bombs.isEmpty())
			Bombs.CheckBombs();

		int intendgame = Players.checkGameEnde();

		// Prüfen, ob das SPiel zu Ende ist
		if (intendgame > PlayerManager.ENDE) {

			system.setHighscoreP1(Players.PlayerList.get(0).getCountthesteps());
			if (system.getamplayer() > 1) {
				system.setHighscoreP2(Players.PlayerList.get(1)
						.getCountthesteps());
			}

			if (PlayerManager.ALLDEAD == intendgame) {

				level.getExit().doOnKill(this);
			}

			else if (PlayerManager.EXIT == intendgame) {
				System.out.println("Anzahl der Schritte: "
						+ Players.PlayerList.get(0).getCountthesteps());
				level.getExit().doOnExit(this);

			} else if (PlayerManager.ALLDEAD == intendgame) {
				level.getExit().doOnKill(this);
			}

			/**
			 * Bei Netzwerkmodus Highscore wird ausgegeben
			 */

			else if (system.getboolLAN()) {
				if (PlayerManager.LASTMAN == Players.checkGameEnde())
					level.getExit().doOnLastMan(this);
				if (PlayerManager.LASTMANP2 == Players.checkGameEnde())
					level.getExit().doOnLastManP2(this);
			}

			else if (PlayerManager.LASTMAN == intendgame) {
				if (Players.PlayerList.get(0).isAlive() == true) {
					system.setMessage2P(system.getnamePlayer1() + " hat "
							+ system.getnamePlayer2() + " innerhalb von "
							+ system.getHighscoreP1() + " Schritten getötet");
				} else {
					system.setMessage2P(system.getnamePlayer2() + " hat "
							+ system.getnamePlayer1() + " innerhalb von "
							+ system.getHighscoreP2() + " Schritten getötet");
				}
				level.getExit().doOnLastMan(this);

			} else if (PlayerManager.LASTMANP2 == intendgame)
				level.getExit().doOnLastManP2(this);
		}

		if (Players.checkspecial()) {

			int newposx, newposy;

			do {
				newposx = getrandomcoordx();
				newposy = getrandomcoordy();

			} while (level.getFeld(newposx, newposy).isFrei() == false);

			Players.PlayerList.get(0).setX(newposx);
			Players.PlayerList.get(0).setY(newposx);

		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return level Level wird an die Methode zurueck geliefert Koordinaten
	 *         werden initialisiert
	 */

	public AbstractFeld getFeld(int x, int y) {
		return level.getFeld(x, y);
	}

	/**
	 * 
	 * @param input
	 * @param x
	 * @param y
	 *            Koordinaten von setFeld werden festgelegt
	 */

	public void setFeld(AbstractFeld input, int x, int y) {
		level.setFeld(input, x, y);
	}

	/**
	 * 
	 * @param b
	 *            Bombe wird gesetzt
	 */

	public void plantBomb(Bomb b) {
		Bombs.AddBomb(b);
	}

	/**
	 * 
	 * @param Feld
	 *            Bombe zerstoeren
	 */

	public void hitThings(AbstractFeld Feld) {
		Bombs.hitBombs(Feld);
		level.setboolxplode(Feld.getX(), Feld.getY(), true);
		Players.hitPlayers(Feld);
	}

	/**
	 * Einstellungen des Levels: Breite, Laenge, Grafiken, Farbe Explosion wird
	 * zurueckgesetzt
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

				if (level.getboolxplode(i, j)) {
					g.drawImage(imagexplode, level.getFeld(i, j).getX() * 32,
							level.getFeld(i, j).getY() * 32, 32, 32, owner);
				}

				level.setboolxplode(i, j, false);

			}

		/**
		 * Aussehen des Spielers wird festgelegt Ausgabe der Bomben,
		 * Implementierung eines ItemManagers Exit wird nur im
		 * Singleplayer-Spiel ueberschrieben
		 */

		// g.drawOval (e.getX() * 32, e.get() * 32, 31, 31);

		Players.paintPlayers(g);
		if (!Bombs.isEmpty())
			Bombs.paintBombs(g);

		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			g.drawImage(imageexit, level.getExit().getX() * 32, level.getExit()
					.getY() * 32, 32, 32, owner);

			if (!getFeld(level.getExit().getX(), level.getExit().getY())
					.isFrei()) {

				g.drawImage(imagezerwand, level.getExit().getX() * 32, level
						.getExit().getY() * 32, 32, 32, owner);

				// g.setColor(level.getFeld(e.getX(), e.getY()).getColor());
				// g.fillRect(e.getX() * 32, e.getY() * 32, 32, 32);
			}
		}
		if (system.getamplayer() == 1) {
			g.drawImage(imageexit, getSpecial().getX() * 32, getSpecial()
					.getY() * 32, 32, 32, owner);

			if (!getFeld(getSpecial().getX(), getSpecial().getY()).isFrei()) {

				g.drawImage(imagezerwand, getSpecial().getX() * 32,
						getSpecial().getY() * 32, 32, 32, owner);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Players.movePlayers();
		repaint();
	}

	/**
	 * 
	 * @author Gruppe 27 Klasse TAdapter implementiert Serializable KeyEvents
	 *         werden hinzugefuegt
	 * 
	 * 
	 */

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

	/**
	 * Owner wird bearbeitet
	 */

	public void dispose() {

		owner.dispose();
	}

	// nicht löschen, braucht man für Netzwerk
	public void setowner(GameWindow owner) {
		this.owner = owner;
	}

	/**
	 * 
	 * @return system system wird an die Methode zurueck geliefert
	 */

	public SysEinst getsystem() {
		return system;
	}

	/**
	 * Grafiken werden eingebunden (Zertstoerbare Wand, normale Wand, Ausgang)
	 */

	public void initImages() {

		imagezerwand = Toolkit
				.getDefaultToolkit()
				.getImage(
						"src/de/hhu/propra12/gruppe27/bomberman/graphics/ZerstoerbareWand.gif");
		imagewand = Toolkit.getDefaultToolkit().getImage(
				"src/de/hhu/propra12/gruppe27/bomberman/graphics/Wand.gif");

		imageexit = Toolkit.getDefaultToolkit().getImage(
				"src/de/hhu/propra12/gruppe27/bomberman/graphics/TorTranz.gif");

		imagexplode = Toolkit.getDefaultToolkit().getImage(
				"src/de/hhu/propra12/gruppe27/bomberman/graphics/Feuer.gif");
	}

	/**
	 * 
	 * @return level level wird an die Methode zurueck geliefert
	 */

	public Level getLevel() {
		return level;
	}

	/**
	 * 
	 * @return Players Players wird an die Methode zurueck geliefert
	 */

	public PlayerManager getPlayers() {
		return Players;
	}

	public Special getSpecial() {
		return special;
	}

	/**
	 * Zufaelliges Special wird bestimmt
	 */

	private void setrandomspec() {

		if (system.getrandomlvl() == true) {
			setcoords();
		}

		else {
			do {
				setcoords();
			} while ((exitx % 2) == 0 && (exity % 2) == 0);
		}

		special = new Special(level.getFeld(exitx, exity));
	}

	/**
	 * Koordinaten des willkuerlich gesetzten Extis werden bestimmt
	 */
	int exitx, exity;

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

	/**
	 * 
	 * @return x x wird an die Methode zurueck geliefert
	 * 
	 */

	private int getrandomcoordx() {
		int x;
		if (system.getbmllevel()) {
			x = system.getfeldxbml();
		} else {
			x = system.getfeldx();
		}
		x = (int) (x * Math.random());
		return x;
	}

	/**
	 * 
	 * @return y y wird an die Methode zurueck geliefert
	 */

	private int getrandomcoordy() {
		int y;
		if (system.getbmllevel()) {
			y = system.getfeldybml();
		} else {
			y = system.getfeldy();
		}
		y = (int) ((int) y * Math.random());
		return y;
	}
}
