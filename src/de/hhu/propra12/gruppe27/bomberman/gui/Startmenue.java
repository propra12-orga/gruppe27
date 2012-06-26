package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
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
import de.hhu.propra12.gruppe27.bomberman.netzwerk.Client;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.Host;
/**
 * 
 * @author
 * @version 1.0
 * Klasse für das grafische Startmenü (Spiel starten, Multiplayer joinen und hosten, Einstellungen setzen)
 *
 */


public class Startmenue {

	static Startmenue startmen;
	SysEinst system = SysEinst.getSystem();

	Icon icon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/warofstickmen.gif");

	
	// public SysEinst menueaufruf() {
	/**
	 * Methode um das Menü-Fenster zu öffnen
	 * @return
	 */
	
	public static Startmenue getMenue() {
		if (startmen == null) {
			startmen = new Startmenue();
		}
		return startmen;
	}

	
	
	public void menueaufruf() {
		final JFrame framemenue = new JFrame(" StartenBomberman Startmenue");
		framemenue.setVisible(true);
		framemenue.setSize(640, 590);
		framemenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		framemenue.add(panel);

		// JPanel panel2 = new JPanel(new GridBagLayout());
		// framemenue.add(panel2);

		framemenue.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		// Button mit Bild als erstes
		
		JButton buttonS0 = new JButton(icon);
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		panel.add(buttonS0, c);

	
		/**
		 * Spiel starten (Button 1/buttonS1) - solo
		 */
		
		JButton buttonS1 = new JButton("Spiel starten (Solo)");
		c.gridx = 0;
		c.gridy = 2;

		panel.add(buttonS1, c);
		
		/**
		 * Spiel starten (Button 2/buttonS2) - 2Spieler - Modus
		 */

		JButton buttonS2 = new JButton("Spiel starten (2 SPieler-Modus)");
		c.gridx = 0;
		c.gridy = 3;

		panel.add(buttonS2, c);


		/**
		 * Multiplayer verbinden (Button 3/buttonS3)
		 */
		
		JButton buttonS3 = new JButton("Multiplayer (Join Game)");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(buttonS3, c);


		/**
		 * Multiplayer hosten (Button 4/buttonS4)
		 */
		
		JButton buttonS4 = new JButton("Multiplayer (Host Game)");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(buttonS4, c);

	
		/**
		 * Optionsmenü öffnen (Button 5/buttonS5)
		 */
		
		JButton buttonS5 = new JButton("Optionen");
		c.gridx = 0;
		c.gridy = 6;
		panel.add(buttonS5, c);

		/**
		 * Spiel beenden bzw. schließen (Button 6/buttonS6)
		 */
		
		JButton buttonS6 = new JButton("Spiel verlassen");
		c.gridx = 0;
		c.gridy = 7;
		panel.add(buttonS6, c);


		
		/**
		 * Aktionen für Button 1
		 * Spielfeld wird generiert und gestartet
		 */

		buttonS1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Ausgabe der Einstellung-Parameter
				system.printSysEinst();

				GameWindow s = new GameWindow(0);
				framemenue.dispose();
			}
		});

		buttonS2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setamplayer(2);
				// Ausgabe der Einstellung-Parameter
				system.printSysEinst();

				GameWindow s = new GameWindow(0);
				// framemenue.setVisible(false);

				framemenue.dispose();
				// Variablen kÃ¶nnen aus "system" gezogen werden
			}
		});

		// Join Multiplayer
		buttonS3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Client client = new Client();

			}
		});

		// Host Multiplayer
		buttonS4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Host host = new Host();

			}
		});

		/**
		 * Aktionen für Button 5
		 * 
		 * Optionsmenü wird in neuem Fenster aufgerufen Änderungen der Optionen
		 * werden übernommen und in der system-Instanz gespeichert
		 */
		
		buttonS5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Optionmenue option = new Optionmenue();
				option.optionaufruf();
			}
		});

		/**
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

		// return (system);
	}
}
