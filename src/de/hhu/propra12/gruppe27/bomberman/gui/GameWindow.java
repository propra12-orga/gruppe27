package de.hhu.propra12.gruppe27.bomberman.gui;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author 
 * @version 1.0
 * Klasse zur Erstellung des Spielfensters
 *
 */

public class GameWindow extends JFrame {

	SysEinst system;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param levelnr
	 */

	public GameWindow(int levelnr) {

		/**
		 *Festlegung der Größe 
		 */
		
		this.system = SysEinst.getSystem();

		
		add(new Spielfeld(levelnr, this));
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
	
	// Konstruktor fÃ¼r Client im Netzwerk
	public GameWindow(int levelnr, boolean isclient, SysEinst system,
			Spielfeld spielfeld) {

		if (isclient) {
			this.system = system;

			spielfeld.setowner(this);
			add(spielfeld);
			setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 24);
			setVisible(true);
			repaint();
		} else {
			new GameWindow(levelnr);

		}
	}
}