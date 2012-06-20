package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/*
 * grafisches Startmenü
 * 
 * Einstieg für:
 * Spiel starten
 * Multiplayer joinen und hosten
 * Einstellungen setzen
 */

public class Startmenue {

	SysEinst system = new SysEinst();

	final Image image = Toolkit.getDefaultToolkit().getImage(
			"warofstickmen.gif");

	/*
	 * Methode menueaufruf
	 * 
	 * Das Menue-Fenster wird geöffnet
	 */
	public SysEinst menueaufruf(SysEinst system) {

		this.system = system;
		menueaufruf();

		return (system);
	}

	public SysEinst menueaufruf() {

		final JFrame framemenue = new JFrame(" StartenBomberman Startmenue");
		framemenue.setVisible(true);
		framemenue.setSize(640, 640);
		framemenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JLabel bild = new JLabel("Platzhalter Bild");
		JPanel panel = new JPanel(new GridBagLayout());
		framemenue.add(panel);

		framemenue.getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		// panel.add(image);
		// panel.paintComponent(g);

		/*
		 * Button 1 - buttonS1
		 * 
		 * Spiel Starten
		 */
		JButton buttonS1 = new JButton("Spiel starten (Solo)");
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		panel.add(buttonS1, c);

		JButton buttonS2 = new JButton("Spiel starten (2 SPieler-Modus)");
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		panel.add(buttonS2, c);

		/*
		 * Button 3 - buttonS3
		 * 
		 * Multiplayer Verbinden
		 */
		JButton buttonS3 = new JButton("Multiplayer (Join Game)");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(buttonS3, c);

		/*
		 * Button 4 - buttonS4
		 * 
		 * Multiplayer Hosten
		 */
		JButton buttonS4 = new JButton("Multiplayer (Host Game)");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(buttonS4, c);

		/*
		 * Button 5 - buttonS5
		 * 
		 * Optionsmenue öffnen
		 */
		JButton buttonS5 = new JButton("Optionen");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(buttonS5, c);

		/*
		 * Button 6 - buttonS6
		 * 
		 * Spiel beenden / schließen
		 */
		JButton buttonS6 = new JButton("Spiel verlassen");
		c.gridx = 0;
		c.gridy = 6;
		panel.add(buttonS6, c);

		/*
		 * Aktionen für Button 1
		 * 
		 * Spielfeld wird generiert und gestartet
		 */

		buttonS1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Ausgabe der Einstellung-Parameter

				system.printSysEinst();
				GameWindow s = new GameWindow(0, system);
				framemenue.setVisible(false);

				// framemenue.dispose();
				// Variablen können aus "system" gezogen werden
			}
		});

		// buttonS1.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// // Ausgabe der Einstellung-Parameter
		//
		// system.printSysEinst();
		// GameWindow s = new GameWindow(0, system.getfeldx(), system
		// .getfeldy(), system.getamplayer());
		// framemenue.setVisible(false);
		//
		// // framemenue.dispose();
		// // Variablen können aus "system" gezogen werden
		// }
		// });

		buttonS2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setamplayer(2);
				// Ausgabe der Einstellung-Parameter
				system.printSysEinst();

				GameWindow s = new GameWindow(0, system);
				framemenue.setVisible(false);

				// framemenue.dispose();
				// Variablen können aus "system" gezogen werden
			}
		});

		buttonS3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Vorbereitung

			}
		});

		buttonS4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Vorbereitung

			}
		});

		/*
		 * Aktionen für Button 5
		 * 
		 * Optionsmenue wird in neuem Fenster aufgerufen Änderungen der Optionen
		 * werden übernommen und in der system-Instanz gespeichert
		 */
		buttonS5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Optionmenue option = new Optionmenue();
				system = option.optionaufruf(system);
			}
		});

		/*
		 * Aktionen für Button 6
		 * 
		 * Alle Fenster und das Spiel werden geschlossen
		 */
		buttonS6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		return (system);
	}
}
