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

import javax.swing.JFrame;
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
	public Level level = null;
	public Timer t; //timer gets stopped in Exit-class
	public PlayerManager Players;
	private BombManager Bombs;
	public Exit e; // Zugriff in PlayerManager
	private GameWindow owner;
	private SysEinst system = SysEinst.getSystem();
	private transient Image imagezerwand, imageexit, imagewand;

	/**
	 * 
	 * @param levelnr
	 * @param laenge
	 * @param breite
	 * @param spielerzal
	 *
	 */
	
	public Spielfeld(int levelnr, GameWindow owner) {
		system.printSysEinst();
		level = loadlevel(levelnr);
		this.owner = owner;
		
		this.addKeyListener(new TAdapter());
		this.setFocusable(true);
		this.setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 500);
		this.setVisible(true);
		//Ausgang rechts unten
		e = new Exit(
				level.getFeld(system.getfeldx() - 2, system.getfeldy() - 2)); // ausgang
		// level.setFeld(new Path(laenge - 2, breite - 2, level), laenge - 2,
		// breite - 2);
		Bombs = new BombManager(this);
		Players = new PlayerManager(this);

		// bei lokalem Spiel werden die Spieler normal geaddet
		if (false == system.getboolLAN()) {
			Players.addPlayer(new KeyPlayer(1, 1, "Spieler1", this, new Keyset(
					1)));
			if (system.getamplayer() > 1) {
				KeyPlayer lp2 = (new KeyPlayer(1, 1, "Spieler2", this,
						new Keyset(2)));
				lp2.playercolor = new Color(255, 0, 0);
				Players.addPlayer(lp2);
			}

//			Keyset k1 = new Keyset(1);
//			Keyset k2 = new Keyset(2);
//			((KeyPlayer)(Players.getPlayerList().get(0))).Keys = k2;
//			((KeyPlayer)(Players.getPlayerList().get(1))).Keys = k1;			
			
			initImages();
			this.repaint();
			this.startgame(); //Timer starten
		}
	}

	/**
	 * Spielstart
	 */

	public void startgame() {
		if (system.getboolClient())
			System.out.println("client.t.start");
		else
			System.out.println("host.t.start");

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

		// }
		// PlayerList.size()
		// if (Players.checkGameEnde() > 0) {
		//
		// // if (Players.countPlayersAlive() < 1) {
		// // e.doOnKill(this);
		// // }
		// if (1 == Players.checkGameEnde())
		// e.doOnKill(this);
		// if (2 == Players.checkGameEnde())
		// e.doOnExit(this);
		// }
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
			Bombs.paintBombs(g);// ausgabe der bomben(spaeter auch
								// explosionsgrafiken)
		// TODO auslagerung der Zeichenfunktion des Exit. //TODO
		// implementierung eines ItemManagers

		// g.drawOval(e.getX() * 32, e.getY() * 32, 31, 31);
		g.drawImage(imageexit, e.getX() * 32, e.getY() * 32, 32, 32, owner);

		// Bei bedarf eingang ueberzeichnen.
		if (!getFeld(e.getX(), e.getY()).isFrei()) {

			g.drawImage(imagezerwand, e.getX() * 32, e.getY() * 32, 32, 32,
					owner);

			// g.setColor(level.getFeld(e.getX(), e.getY()).getColor());
			// g.fillRect(e.getX() * 32, e.getY() * 32, 32, 32);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// if (system.getboolClient())
		 System.out.println("tickt");
		Players.movePlayers();
		repaint();
	}

	private class TAdapter extends KeyAdapter implements Serializable {

		private static final long serialVersionUID = 1L;

		public void keyPressed(KeyEvent e) {
			if (system.getboolClient())
				System.out.println("Client pressed " + e.getKeyCode());
			else
				System.out.println("Host pressed " + e.getKeyCode());
			System.out.println("size=" + Players.PlayerList.size());
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

	// Methode um beim Client die Keysets zu tauschen
	public void switchKeyset() {

		if (system.getboolLAN() == true && system.getboolClient() == true) {

			LanPlayer host = (LanPlayer) Players.getPlayerList().get(0);
			LanPlayer client = (LanPlayer) Players.getPlayerList().get(1);
			//
			// Keyset k = host.getKeyset();
			// host.setKeyset(client.getKeyset());
			// client.setKeyset(k);

			host.setKeyset(new Keyset(2));
			client.setKeyset(new Keyset(1));

		}
	}

	public void initPlayer() {
		// Abfrage Netzwerkspieler
		if (system.getboolClient() == false) {
			this.setFocusable(true);
			Players.addPlayer(new LanPlayer(1, 1, "Spieler1", this, new Keyset(
					1)));

			LanPlayer lp2 = (new LanPlayer(1, 1, "Spieler2", this, new Keyset(
					-1)));
			lp2.playercolor = new Color(255, 0, 0);
			Players.addPlayer(lp2);

		}

		//
		else {
			// (system.getboolLAN() == true && system.getboolClient() == true) {
			Players.addPlayer(new LanPlayer(1, 1, "Spieler1", this, new Keyset(
					-1)));

			LanPlayer lp2 = (new LanPlayer(1, 1, "Spieler2", this,
					new Keyset(1)));
			lp2.playercolor = new Color(255, 0, 0);
			Players.addPlayer(lp2);

		}

		initImages();
		this.repaint();
		this.startgame();
	}
	
	public void setowner(GameWindow gw){
		owner = gw;
	}

	public void setsystem(SysEinst sys) {
		system = sys;
	}

	
	private Level loadlevel(int levelnr) {
		if (false == system.getbmllevel()) {
			return new LevelGen(system.getfeldx(), system.getfeldy(),
					system.getamplayer());
		} else {
			return new LevelGen(system.getfeldxbml(), system.getfeldybml(),
					system.getamplayer(), system.getbmllevel());
		}
	}
}
