package de.hhu.propra12.gruppe27.bomberman.gui.menue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.hhu.propra12.gruppe27.bomberman.audio.StdAudio;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;
import de.hhu.propra12.gruppe27.bomberman.gui.OpenFileDialog;
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
		// framemenue.setResizable(false);
		framemenue.setLocationRelativeTo(null);
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
		JToggleButton buttonbmlimport = new JToggleButton("Level laden",
				system.getbmllevel());
		c.gridx = 0;
		c.gridy = 6;
		panel.add(buttonbmlimport, c);

		/**
		 * Leveleditor starten (Button 5/buttonle)
		 */

		JButton buttonle = new JButton("Leveleditor");
		c.gridx = 0;
		c.gridy = 7;
		panel.add(buttonle, c);

		/**
		 * Optionsmenue oeffnen (Button 6/buttonS5)
		 * 
		 */

		JButton buttonS5 = new JButton("Optionen");
		c.gridx = 0;
		c.gridy = 8;
		panel.add(buttonS5, c);

		/**
		 * Spiel beenden bzw. schliessen (Button 7/buttonS6)
		 */

		JButton buttonS6 = new JButton("Spiel verlassen");
		c.gridx = 0;
		c.gridy = 9;
		panel.add(buttonS6, c);

		/**
		 * Aktionen fuer Button 1 Spielfeld wird generiert und gestartet
		 */

		framemenue.setSize(framemenue.getPreferredSize());
		int height = framemenue.getPreferredSize().height;
		int width = framemenue.getPreferredSize().width;
		centerWindow(width, height, framemenue);

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
				system.setboolClient(true);
				system.setamplayer(2);
				try {
					Client client = new Client();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// Host Multiplayer
		buttonS4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setboolLAN(true);
				system.setboolClient(false);
				system.setamplayer(2);
				try {
					Host host = new Host();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		/**
		 * Aktionen f�r Button buttonbmlimport
		 * 
		 * �ffnet ein OpenFileDialog zum �bergeben des Pfads der zu ladenden
		 * Datei. Sollte abgebrochen werden, wird der Toggle Button
		 * zur�ckgesetzt und es wird kein Level geladen.
		 * 
		 */

		buttonbmlimport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				system.setbmllevel(!system.getbmllevel());

				JToggleButton buttonbmlimport = (JToggleButton) e.getSource();
				buttonbmlimport.setText("Level laden");

				try {

					OpenFileDialog filedialog = new OpenFileDialog(); // Neuer
																		// Dialog
					system.setlevelpath(filedialog.loadFile(new Frame(),
							"Lade BomberMan-Level...", ".\\data/levels",
							"*.bml")); // Parameter an neuen Dialog uebergeben
										// und Pfad auf levelpath setzen
					if (system.getlevelpath() == null) {
						system.setbmllevel(false);
						buttonbmlimport.setSelected(false);
						System.out.println("Kein Level importiert!");
					} else {
						system.setbmllevel(true);
						setfeld(system.getlevelpath()); // levelpath an feld
														// uebergeben
						System.out.println(system.getlevelpath() + " geladen!"); // Konsolenausgabe
																					// zum
																					// debuggen

						buttonbmlimport.setText(system.getlevelpath()
								+ " geladen!"); // Umbenennen des Buttons.
												// Nutzer informieren.
					}

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
							.println("Fehler beim einlesen der Levellaenge/-breite in"
									+ system.getlevelpath()
									+ " !\n"
									+ eNFE.getMessage());// Konsolenausgabe zum
															// debuggen
					buttonbmlimport
							.setText("Fehler beim einlesen der Levellaenge/-breite!"); // Umbenennen
																						// des
																						// Buttons.
																						// Nutzer
																						// informieren.
				}

			}
		});

		/**
		 * Aktionen fuer Button buttonle
		 * 
		 * Leveleditor wird aufgerufen
		 */

		buttonle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Leveleditmenue leveledit = new Leveleditmenue();
				leveledit.leveleditmenue();
			}
		});

		/**
		 * Aktionen fuer Button 5
		 * 
		 * Optionsmenue wird in neuem Fenster aufgerufen Aenderungen der
		 * Optionen werden uebernommen und in der system-Instanz gespeichert
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

		/*
		 * Mouse Overs (Audio)
		 */
		if (system.getMouseOverBool()) {

			buttonS1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonS2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonS3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonS4.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonS5.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonS6.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonbmlimport.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonle.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (system.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

		}
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

	public void centerWindow(int width, int height, JFrame frame) {
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		frame.setLocation((screensize.width - width) / 2,
				(screensize.height - height) / 2);
	}

}
