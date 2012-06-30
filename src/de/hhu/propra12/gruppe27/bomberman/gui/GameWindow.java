package de.hhu.propra12.gruppe27.bomberman.gui;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author
 * @version 1.0 Klasse zur Erstellung des Spielfensters
 * 
 */

public class GameWindow extends JFrame {

	SysEinst system;
	Spielfeld spielfeld;

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param levelnr
	 */

	public GameWindow(int levelnr) {

		/**
		 * Festlegung der Groesse
		 */

		this.system = SysEinst.getSystem();

		this.spielfeld = new Spielfeld(levelnr, this);
		add(spielfeld);

		setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 24);
		setVisible(true);

		repaint();
	}

	/**
	 * 
	 * @param levelnr
	 * @param isclient
	 * @param system
	 * @param spielfeld
	 */

	// Konstruktor für Client im Netzwerk
	public GameWindow(int levelnr, boolean isclient, SysEinst sys,
			Spielfeld spielfeldc) {

		if (isclient) {
			system.setfeldx(sys.getfeldx());
			system.setfeldy(sys.getfeldy());

			this.spielfeld = spielfeldc;
			spielfeld.setowner(this);
			add(spielfeld);
			setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 24);
			setVisible(true);
			repaint();
		} else {
			new GameWindow(levelnr);

		}
	}

	public Spielfeld getspielfeld() {
		return spielfeld;
	}
}