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
 * @author Gruppe 27
 * @version 1.0
 * Klasse Spielfeld implementiert ActionListener 
 * private-Elemente greifen auf dazugehoerige Klassen zu
 * 
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

	/**
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
 * @param owner
 * Initialisierung von levelnr und Gamewindow
 */
	public Spielfeld(int levelnr, GameWindow owner) {
		this(loadlevel(0), owner);
	}

/**
 * 
 * @param level
 * @param owner
 * Initialisierung von levelnr und GameWindow
 * KeyListener wird hinzugefuegt
 * Feld mit den Koordinaten x und y
 */

	
	public Spielfeld(Level level, GameWindow owner) {
		this.level = level;
		this.owner = owner;

		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 500);
		this.setVisible(true);
		
		/**
		 * Ausgang wird nur bei einem Solospiel und im Zweispielermodus gesetzt
		 */

		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			setrandomexit();
		}
		
		/**
		 * Ansonsten wird der Exit auf ein nicht erreichbares Feld gesetzt
		 */
		
		else {
			e = new Exit(level.getFeld(0, 0));
		}

		
		Bombs = new BombManager(this);
		Players = new PlayerManager(this);
		
		/**
		 * Highscore wird geloescht
		 */

		system.setHighscoreP1(0);
		system.setHighscoreP2(0);
		
		/**
		 * Spieler werden hinzugefuegt, 
		 * je nach bool-Werten fuer Solo- und Zweispielermodus oder fuer
		 * das Lan-Spiel
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
	 * Bomben ticken oder explodieren lassen, 
	 * Pruefung, ob das Spiel zu Ende ist, 
	 * was passiert, wenn das Spiel zu Ende ist,
	 * Highscores werden in den Systemeinstellungen gespeichert
	 * 
	 */
	 // if (system.getamplayer() == 1) { --> bei einem Singlespiel

	private void StatusUpdate() {

		// TODO Exithandling

		if (!Bombs.isEmpty())
			Bombs.CheckBombs();

		int intendgame = Players.checkGameEnde();

		if (intendgame > PlayerManager.ENDE) {

			system.setHighscoreP1(Players.PlayerList.get(0).getCountthesteps());
			if (system.getamplayer() > 1) {
				system.setHighscoreP2(Players.PlayerList.get(1)
						.getCountthesteps());
			}


			
			if (system.getamplayer() == 1) {

/**
 * Anzahl der Schritte fuer den Highscore
 */
			if (PlayerManager.ALLDEAD == intendgame) {
				e.doOnKill(this);
			}


			else if (PlayerManager.EXIT == intendgame) {
				System.out.println("Anzahl der Schritte: "
						+ Players.PlayerList.get(0).getCountthesteps());
				e.doOnExit(this);
			}

			/**
			 * Bei Zweispielermodus
			 */
			
			else if (PlayerManager.ALLDEAD == intendgame) {
				
				// if (PlayerManager.ALLDEAD == Players.checkGameEnde())
			


			else if (PlayerManager.ALLDEAD == intendgame) {
				e.doOnKill(this);
			}

			
			/**
			 * Bei Netzwerkmodus
			 */
			
			else if (system.getboolLAN()) {
				if (PlayerManager.LASTMAN == Players.checkGameEnde())
					e.doOnLastMan(this);
				if (PlayerManager.LASTMANP2 == Players.checkGameEnde())
					e.doOnLastManP2(this);

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
				e.doOnLastMan(this);

			} else if (PlayerManager.LASTMANP2 == intendgame)
				e.doOnLastManP2(this);


		}

	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return level
	 * Level wird an die Methode zurueck geliefert
	 * Koordinaten vom Spielfeld: x und y
	 */
	

	public AbstractFeld getFeld(int x, int y) {
		return level.getFeld(x, y);
	}

	/**
	 * 
	 * @param input
	 * @param x
	 * @param y
	 * Koordinaten von setFeld werden festgelegt
	 */

	public void setFeld(AbstractFeld input, int x, int y) {
		level.setFeld(input, x, y);
	}

	/**
	 * 
	 * @param b
	 * Bombe legen + neue erhalten
	 */

	public void plantBomb(Bomb b) {
		Bombs.AddBomb(b);
	}

	/**
	 * 
	 * @param Feld
	 * Bombe zerstoeren + spaeter auch Spieler treffen
	 */

	public void hitThings(AbstractFeld Feld) {
		Bombs.hitBombs(Feld);
		// TODO Spieler Töten
		Players.hitPlayers(Feld);
	}

	/**
	 * Einstellungen des Levels:
	 * Breite, Laenge, Grafiken, Farbe
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
		 * Ausgabe der Bomben, Implementierung eines ItemManagers
		 * Exit wird nur im Singleplayer-Spiel ueberschrieben
		 */

		// g.drawOval (e.getX() * 32, e.get() * 32, 31, 31);
		
		Players.paintPlayers(g);
		if (!Bombs.isEmpty())
			Bombs.paintBombs(g);
		// TODO auslagerung der Zeichenfunktion des Exit. //TODO

		if (false == system.getboolLAN() && 1 == system.getamplayer()) {
			g.drawImage(imageexit, e.getX() * 32, e.getY() * 32, 32, 32, owner);

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
	
/**
 * 
 * @author Gruppe 27
 * Klasse TAdapter implementiert Serializable
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
	 * Owner wird veraendert
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
	 * @return system
	 * system wird an die Methode zurueck geliefert
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

	}
	
	/**
	 * 
	 * @return level
	 * level wird an die Methode zurueck geliefert
	 */

	public Level getLevel() {
		return level;
	}
	
	/**
	 * 
	 * @return Players
	 * Players wird an die Methode zurueck geliefert
	 */

	public PlayerManager getPlayers() {
		return Players;
	}

	/**
	 * 
	 * @return e
	 * e wird an die Methode zurueck geliefert
	 */
	
	public Exit getExit() {
		return e;
	}
	


	int exitx;
	int exity;
	
	/**
	 * Zufaelliger Exit wird bestimmt
	 */

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

	
	/**
	 * Koordinaten des willkuerlich gesetzten Extis werden bestimmt
	 */
	
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
	 * @return x
	 * x wird an die Methode zurueck geliefert
	 * 
	 */
	
	private int getrandomcoordx() {
		int x = system.getfeldx();
		x = (int) (x * Math.random());
		return x;
	}

	/**
	 * 
	 * @return y
	 * y wird an die Methode zurueck geliefert
	 */
	
	private int getrandomcoordy() {
		int y = system.getfeldy();
		y = (int) ((int) y * Math.random());
		return y;
	}
}
