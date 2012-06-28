package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.Client;
import de.hhu.propra12.gruppe27.bomberman.netzwerk.Host;

/**
 * 
 * @author
 * @version 1.0 Klasse fuer das grafische Startmenue (Spiel starten, Multiplayer
 *          joinen und hosten, Einstellungen setzen)
 * 
 */

public class Startmenue {

	static Startmenue startmen;
	SysEinst system = SysEinst.getSystem();

	Icon icon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/warofstickmen.gif");

	/**
	 * Methode um das Menue-Fenster zu oeffnen
	 * 
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
		framemenue.setSize(640, 615);
		framemenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		framemenue.add(panel);

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

		JButton buttonS2 = new JButton("Spiel starten (2 Spieler-Modus)");
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
		 * Datei laden (Button buttonbmlimport)
		 * 
		 * Level importieren
		 */
		JToggleButton buttonbmlimport = new JToggleButton("Level importieren",
				system.getbmllevel());
		c.gridx = 0;
		c.gridy = 6;
		panel.add(buttonbmlimport, c);

		/**
		 * Optionsmenue oeffnen (Button 5/buttonS5)
		 * 
		 */

		JButton buttonS5 = new JButton("Optionen");
		c.gridx = 0;
		c.gridy = 7;
		panel.add(buttonS5, c);

		/**
		 * Spiel beenden bzw. schlie�en (Button 6/buttonS6)
		 */

		JButton buttonS6 = new JButton("Spiel verlassen");
		c.gridx = 0;
		c.gridy = 8;
		panel.add(buttonS6, c);

		/**
		 * Aktionen f�r Button 1 Spielfeld wird generiert und gestartet
		 */

		buttonS1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setamplayer(1);
				system.setboolLAN(false);
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
				system.setboolLAN(false);
				// Ausgabe der Einstellung-Parameter
				system.printSysEinst();

				GameWindow s = new GameWindow(0);
				// framemenue.setVisible(false);

				framemenue.dispose();
				// Variablen können aus "system" gezogen werden
			}
		});

		// Join Multiplayer
		buttonS3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setboolLAN(true);
				Client client = new Client();

			}
		});

		// Host Multiplayer
		buttonS4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setboolLAN(true);
				Host host = new Host();

			}
		});

		/**
		 * Aktionen für Button buttonbmlimport
		 * 
		 * soll einen Boolean setzen, damit das Level beim Spielstart geladen
		 * wird
		 */

		buttonbmlimport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setbmllevel(!system.getbmllevel());

				JToggleButton buttonbmlimport = (JToggleButton) e.getSource();
				buttonbmlimport.setText("Level importieren");

				try {

					OpenFileDialog filedialog = new OpenFileDialog(); // Neuer
																		// Dialog
					system.setlevelpath(filedialog.loadFile(new Frame(),
							"Lade BomberMan-Level...", ".\\data/levels",
							"*.bml")); // Parameter
					// an
					// neuen
					// Dialog
					// �bergeben
					// und
					// Pfad
					// auf
					// levelpath
					// setzen
					setfeld(system.getlevelpath()); // levelpath an feld
													// �bergeben
					System.out.println(system.getlevelpath() + " geladen!"); // Konsolenausgabe
																				// zum
																				// debuggen
					buttonbmlimport.setText(system.getlevelpath() + " geladen!"); // Umbenennen
																					// des
																					// Buttons.
																					// Nutzer
																					// informieren

				} catch (IOException eIO) {

					System.out.println("Fehler: " + system.getlevelpath()
							+ " konnte nicht geladen werden!\n"
							+ eIO.getMessage());// Konsolenausgabe zum debuggen
					buttonbmlimport.setText("Fehler beim Laden des Levels!"); // Umbenennen
																				// des
																				// Buttons.
																				// Nutzer
																				// informieren.

				} catch (NumberFormatException eNFE) {

					System.out
							.println("Fehler beim einlesen der Levell�nge/-breite in"
									+ system.getlevelpath()
									+ " !\n"
									+ eNFE.getMessage());// Konsolenausgabe zum
															// debuggen
					buttonbmlimport
							.setText("Fehler beim einlesen der Levell�nge/-breite!"); // Umbenennen
																						// des
																						// Buttons.
																						// Nutzer
																						// informieren.

				}

			}
		});

		/**
		 * Aktionen f�r Button 5
		 * 
		 * Optionsmen� wird in neuem Fenster aufgerufen �nderungen der Optionen
		 * werden �bernommen und in der system-Instanz gespeichert
		 */

		buttonS5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Optionmenue option = new Optionmenue();
				option.optionaufruf();
			}
		});

		/**
		 * Aktionen f�r Button 6
		 * 
		 * Alle Fenster und das Spiel werden geschlossen
		 */
		buttonS6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

	}

	/*
	 * Methode um die Größe des einzulesenden Spielfeldes in die
	 * Systemeinstellungen zu schreiben
	 */
	public void setfeld(String Levelpath) throws NumberFormatException,
			IOException {

		system.setfeldxbml(Integer.parseInt(readFile(Levelpath, "LAENGE")));
		system.setfeldybml(Integer.parseInt(readFile(Levelpath, "BREITE")));

	}

	// Lese Leveldatei aus Pfad ein
	public static String readFile(String Levelpath, String ToBeLoaded)
			throws IOException {

		// Properties verwenden um Datei zu lesen und Levelstruktur zu
		// importieren
		Properties levelfile = new Properties();
		// .bml (BomberManLevel) mit BIS einlesen
		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream(Levelpath));
		// load geh�rt zur properties-Lib
		levelfile.load(stream);
		// Schliessen des BIS
		stream.close();
		// Lese die Levelstruktur aus der Property-Datei aus
		String data = levelfile.getProperty(ToBeLoaded);
		return data;
	}

}
