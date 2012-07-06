package de.hhu.propra12.gruppe27.bomberman.gui.menue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse fuer das Gewinnen des Spiels, Gewinner Bild wird
 *          aufgerufen
 * 
 */

public class WintheGame {

	SysEinst system = SysEinst.getSystem();
	Icon winicon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/GewonnenTranzkleiner.gif");

	/**
	 * "Gewonnen" wird ausgegeben, wenn man das Spiel erfolgreich beendet hat
	 */

	public void winthegame() {

		final JFrame framewin = new JFrame("Gewonnen");
		framewin.setVisible(true);
		// framewin.setResizable(false);
		framewin.setLocationRelativeTo(null);
		framewin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelwin = new JPanel(new GridBagLayout());
		framewin.add(panelwin);

		framewin.getContentPane().add(panelwin, BorderLayout.SOUTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		JButton buttonW0 = new JButton(winicon);
		c.gridx = 0;
		c.gridy = 1;
		// c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2.0;
		panelwin.add(buttonW0, c);

		/**
		 * Button, fuer einen neuen Spielstart
		 */

		JButton buttonW1 = new JButton("Noch einmal!");
		c.gridx = 0;
		c.gridy = 2;
		// c.gridwidth = 2;
		if (system.getboolLAN() == false) {
			panelwin.add(buttonW1, c);
		}

		/**
		 * Button, wenn man zurueck zum Startmenue moechte
		 */

		JButton buttonW2 = new JButton("Zurueck zum Startmenue");
		c.gridx = 0;
		c.gridy = 3;
		// c.gridwidth = 2;
		panelwin.add(buttonW2, c);

		/**
		 * Button, auf dem der Highscore steht
		 */
		if (system.getamplayer() == 1) {
			JButton buttonScore = new JButton(
					"Du hast den Ausgang innerhalb von "
							+ system.getHighscoreP1() + " Schritten erreicht");
			c.gridx = 0;
			c.gridy = 4;
			// c.gridwidth = 2;
			panelwin.add(buttonScore, c);
		}

		else if (system.getamplayer() == 2 && system.getboolLAN() == false) {
			JButton buttonScore = new JButton(system.getMessage2P());
			c.gridx = 0;
			c.gridy = 4;
			// c.gridwidth = 2;
			panelwin.add(buttonScore, c);
		}

		else if (system.getboolLAN()) {
			JButton buttonScore = new JButton(
					"Du hast deinen Gegner innerhalb von "
							+ system.getHighscoreP1() + " Schritten zerstört");
			c.gridx = 0;
			c.gridy = 4;
			// c.gridwidth = 2;
			panelwin.add(buttonScore, c);
		}
		
		/**
		 * Goesse des Fensters
		 */

		framewin.setSize(framewin.getPreferredSize());
		int height = framewin.getPreferredSize().height;
		int width = framewin.getPreferredSize().width;
		centerWindow(width, height, framewin);

		buttonW1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow s = new GameWindow(0);
				framewin.dispose();
			}
		});

		/**
		 * Action Listener wird hinzugefuegt
		 */

		buttonW2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Startmenue start = new Startmenue();
				start.menueaufruf();

				framewin.dispose();
			}
		});

	}

	/**
	 * 
	 * @param width
	 * @param height
	 * @param frame
	 *            Position des Fensters
	 */

	public void centerWindow(int width, int height, JFrame frame) {
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		frame.setLocation((screensize.width - width) / 2,
				(screensize.height - height) / 2);
	}
}
