package de.hhu.propra12.gruppe27.bomberman.gui;

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
	public Level level;
	Timer t;
	public PlayerManager Players;
	private BombManager Bombs;
	public Exit e;
	private GameWindow owner;
	private SysEinst system = SysEinst.getSystem();
	private transient Image imagezerwand, imageexit, imagewand;

	private Level loadlevel(int levelnr) {

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

		this.owner = owner;

		level = loadlevel(0);

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

		// if (system.getboolLAN()){
		// // 2 Netzwerkspieler
		// }

		if (system.getboolLAN() == false) {
			Players.addPlayer(new KeyPlayer(1, 1, "Spieler1", this, new Keyset(
					1)));

			if (system.getamplayer() > 1) {
				Players.addPlayer(new KeyPlayer(1, 1, "Spieler2", this,
						new Keyset(2)));
			}
		}

		else {
			Players.addPlayer(new LanPlayer(1, 1, "Spieler1", this, new Keyset(
					1)));
			Players.addPlayer(new LanPlayer(1, 1, "Spieler2", this, new Keyset(
					-1)));

		}
		// TODO Abfrage für Netzwerkspieler

		// TODO Netzwerk übergabe von spielfeld

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
			Bombs.paintBombs(g);// ausgabe der bomben(später auch
								// explosionsgrafiken)
		// TODO auslagerung der Zeichenfunktion des Exit. //TODO
		// implementierung eines ItemManagers

		// g.drawOval(e.getX() * 32, e.getY() * 32, 31, 31);
		g.drawImage(imageexit, e.getX() * 32, e.getY() * 32, 32, 32, owner);

		// Bei bedarf eingangüberzeichnen.
		if (!getFeld(e.getX(), e.getY()).isFrei()) {

			g.drawImage(imagezerwand, e.getX() * 32, e.getY() * 32, 32, 32,
					owner);

			// g.setColor(level.getFeld(e.getX(), e.getY()).getColor());
			// g.fillRect(e.getX() * 32, e.getY() * 32, 32, 32);
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
}
