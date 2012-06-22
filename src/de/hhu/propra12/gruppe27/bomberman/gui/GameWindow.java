package de.hhu.propra12.gruppe27.bomberman.gui;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

public class GameWindow extends JFrame {

	SysEinst system;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameWindow(int levelnr) {

		this.system = SysEinst.getSystem();

		add(new Spielfeld(levelnr, this));
		setSize(system.getfeldx() * 32, system.getfeldy() * 32 + 24);
		setVisible(true);
		repaint();
	}
}