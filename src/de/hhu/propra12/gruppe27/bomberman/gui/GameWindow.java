package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author Gruppe 27
 * @version 1.0 Klasse zur Erstellung des Spielfensters
 * 
 */
public class GameWindow extends JFrame {

	SysEinst system = SysEinst.getSystem();
	Spielfeld spielfeld = null;

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param levelnr
	 */
	public GameWindow(int levelnr) {

		/**
		 * Festlegung der Groesse (x und y Koordinate) Fenster wird mittig
		 * gesetzt this.system = SysEinst.getSystem();
		 */

		this.spielfeld = new Spielfeld(levelnr, this);
<<<<<<< HEAD
		System.out.println("Beginne Level-Consistency-Check");

		/**
		 * Konsistenzpr�fung. Sollte der Weg von 1, 1 bis 13, 13 nicht
		 * erreichbar sein wird der Spieler informiert und das Spielfeld nicht
		 * geladen.
		 */

		if (!PathFinder.check(convertMap(spielfeld), 1, 1,
				system.getfeldx() - 2, system.getfeldy() - 2)) {
			System.out
					.println("Level ist durch die Konsistenzpr�fung gefallen!");
			JOptionPane.showMessageDialog(null,
					"Level ist durch die Konsistenzpr�fung gefallen!",
					"Level-Konsitenz", JOptionPane.INFORMATION_MESSAGE);
		} else {
			System.out.println("Level hat die Konsistenzpr�fung bestanden!");
			add(spielfeld);
			int width = system.getfeldx() * 32;
			int height = system.getfeldy() * 32 + 24;
			setSize(width, height);
			setVisible(true);
			centerWindow(width, height); // Fenster mittig setzen
			repaint();
		}
=======
		add(spielfeld);
		int width = system.getfeldx() * 32;
		int height = system.getfeldy() * 32 + 24;
		setSize(width, height);
		setVisible(true);
		centerWindow(width, height); // Fenster mittig setzen
		repaint();
>>>>>>> 60e7ab532d50f59258288d393db25eeada05de64
	}

	/**
	 * 
	 * @param levelnr
	 * @param isclient
	 * @param system
	 * @param spielfeld
	 *            Konstruktor fuer Client im Netzwerk
	 */

	public GameWindow(Level level) {
		System.out.println("Konstruktor client called");
		Spielfeld spielfeld = new Spielfeld(level, this);
		system.setfeldx(spielfeld.getsystem().getfeldx());
		system.setfeldy(spielfeld.getsystem().getfeldy());

		/**
		 * Festlegung der Groesse (x und y Koordinate) Fenster wird mittig
		 * gesetzt
		 */

		this.spielfeld = spielfeld;
		spielfeld.setowner(this);
		add(spielfeld);
		int width = system.getfeldx() * 32;
		int height = system.getfeldy() * 32 + 24;
		setSize(width, height);
		setVisible(true);
		centerWindow(width, height);
		repaint();
	}

	/**
	 * 
	 * @return spielfeld Spielfeld wird an die Methode zurueck geliefert
	 */

	public Spielfeld getspielfeld() {
		return spielfeld;
	}

	/**
	 * 
	 * @param width
	 * @param height
	 *            Die Hoehe und Breite des Spielfensters wird festgelegt
	 */

	public void centerWindow(int width, int height) {
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((screensize.width - width) / 2,
				(screensize.height - height) / 2);
	}

<<<<<<< HEAD
	/**
	 * Konvertiert eine Map in eine Array mit 1 fuer zerst. Bloecke + freie Wege
	 * und 0 fuer unzerst. Bloecke...Wird benoetigt, um A*-PathFinder zu
	 * verwenden!
	 * 
	 * @param owner
	 *            Spielfeld
	 * @return Gibt ein Array zurueck, dass die Map als 1 und 0 enthaelt.
	 */
	public int[][] convertMap(Spielfeld owner) {

		int ergebx;
		int ergeby;

		if (system.getbmllevel()) {
			ergebx = system.getfeldxbml();
			ergeby = system.getfeldybml();
		} else {
			ergebx = system.getfeldx();
			ergeby = system.getfeldy();
		}
		int ergebnis[][] = new int[ergebx][ergeby];

		for (int i = 0; i < ergebx; i++) {
			System.out.println();
			for (int j = 0; j < ergeby; j++) {
				if (owner.getFeld(i, j).isFrei()
						|| owner.getFeld(i, j).isZerstoer()) {
					ergebnis[i][j] = 1;
				} else {
					ergebnis[i][j] = 0;
				}
			}
		}

		return ergebnis;
	}
=======
>>>>>>> 60e7ab532d50f59258288d393db25eeada05de64
}