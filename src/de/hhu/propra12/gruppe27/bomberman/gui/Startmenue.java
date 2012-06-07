package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

public class Startmenue {

	/*
	 * grafisches Startmenü
	 * 
	 * Einstellungen setzen
	 */

	// Systemeinstellungen sind "default" gesetzt
	SysEinst system = new SysEinst();

	/*
	 * Methode menueaufruf
	 * 
	 * Das Menue-Fenster wird geöffnet
	 */
	public int menueaufruf() {

		JFrame framemenue = new JFrame(" StartenBomberman Startmenue");
		framemenue.setVisible(true);
		framemenue.setSize(640, 640);
		framemenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel bild = new JLabel("Platzhalter Bild");
		JPanel panel = new JPanel(new GridBagLayout());
		framemenue.add(panel);

		framemenue.getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		panel.add(bild);

		/*
		 * Button 1 - buttonS1
		 * 
		 * Spiel Starten
		 */
		JButton buttonS1 = new JButton("Spiel starten");
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		panel.add(buttonS1, c);

		/*
		 * Button 2 - buttonS2
		 * 
		 * Optionsmenue öffnen
		 */
		JButton buttonS2 = new JButton("Optionen");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(buttonS2, c);

		/*
		 * Button 3 - buttonS3
		 * 
		 * Spiel beenden / schließen
		 */
		JButton buttonS3 = new JButton("Spiel verlassen");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(buttonS3, c);

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
				GameWindow s = new GameWindow(0, system.getfeldx(), system
						.getfeldy(), 1);
				// Variablen können aus "system" gezogen werden
			}
		});

		/*
		 * Aktionen für Button 2
		 * 
		 * Optionsmenue wird in neuem Fenster aufgerufen Änderungen der Optionen
		 * werden übernommen und in der system-Instanz gespeichert
		 */
		buttonS2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Optionmenue option = new Optionmenue();
				system = option.optionaufruf(system);
			}
		});

		/*
		 * Aktionen für Button 3
		 * 
		 * Alle Fenster und das Spiel werden geschlossen
		 */
		buttonS3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		return (1);
	}
}
