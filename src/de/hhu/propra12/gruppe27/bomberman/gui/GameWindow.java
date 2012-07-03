package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author
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
		 * Festlegung der Groesse
		 */

		// this.system = SysEinst.getSystem();
		this.spielfeld = new Spielfeld(levelnr, this);

		add(spielfeld);
		int width = system.getfeldx() * 32;
		int height = system.getfeldy() * 32 + 24;
		setSize(width, height);
		setVisible(true);
		centerWindow(width, height); // Fenster mittig setzen
		repaint();
	}

	/**
	 * 
	 * @param levelnr
	 * @param isclient
	 * @param system
	 * @param spielfeld
	 */
	// Konstruktor fuer Client im Netzwerk
	public GameWindow(int levelnr, boolean isclient, Spielfeld spielfeldc) {

		if (isclient) {
			System.out.println("Konstruktor client called");
			system.setfeldx(spielfeldc.getsystem().getfeldx());
			system.setfeldy(spielfeldc.getsystem().getfeldy());

			this.spielfeld = spielfeldc;
			spielfeld.setowner(this);
			add(spielfeld);
			int width = system.getfeldx() * 32;
			int height = system.getfeldy() * 32 + 24;
			setSize(width, height);
			setVisible(true);
			centerWindow(width, height); // Fenster mittig setzen
			repaint();
		}
		// else {
		// new GameWindow(levelnr);
		// }
	}

	public Spielfeld getspielfeld() {
		return spielfeld;
	}

	public void centerWindow(int width, int height) {
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		this.setLocation((screensize.width - width) / 2,
				(screensize.height - height) / 2);
	}
}