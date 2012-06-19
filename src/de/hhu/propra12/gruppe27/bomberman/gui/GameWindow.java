package de.hhu.propra12.gruppe27.bomberman.gui;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

public class GameWindow extends JFrame {

	SysEinst system = new SysEinst();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameWindow(int levelnr, SysEinst system) {

		this.system = system;

		add(new Spielfeld(levelnr, system, this));
		setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 24);
		setVisible(true);
		repaint();
	}
}