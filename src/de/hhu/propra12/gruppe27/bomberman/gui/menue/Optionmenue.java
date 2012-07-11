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
import javax.swing.JToggleButton;

import de.hhu.propra12.gruppe27.bomberman.audio.StdAudio;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse fuer das Optionsmenue Einstellungen wie Spiegelung,
 *          Standardlvl und KI
 */

public class Optionmenue {

	SysEinst sys = SysEinst.getSystem();

	String spiegel = "Spiegelung ";
	String standard = "Standardlevel ";
	String stringKI = "KI ";

	Icon optionicon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/warofstickmen.gif");

	/**
	 * Methode um das Optionsmenue aufzurufen
	 * 
	 * @param system
	 * @return sys Systemeinstellungen koennen getaetigt werden, Buttons werden
	 *         definiert
	 */

	public void optionaufruf() {

		final JFrame frameoption = new JFrame("Optionsmenue");
		frameoption.setVisible(true);
		// frameoption.setResizable(false);
		frameoption.setLocationRelativeTo(null);
		frameoption.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel optionpanel = new JPanel(new GridBagLayout());
		frameoption.add(optionpanel);

		frameoption.getContentPane().add(optionpanel, BorderLayout.NORTH);
		GridBagConstraints coption = new GridBagConstraints();

		coption.gridx = 0;
		coption.gridy = 0;

		JButton buttonO0 = new JButton(optionicon);
		coption.gridx = 0;
		coption.gridy = 1;
		coption.fill = GridBagConstraints.HORIZONTAL;
		coption.weightx = 1.0;
		optionpanel.add(buttonO0, coption);

		/*
		 * Button 1 - buttonO1
		 */

		/**
		 * Mauerdichte kann eingestellt werden (Fenster wird geoeffnet) -->
		 * zerstoerbare Waende
		 */

		JButton buttonO1 = new JButton("Mauerdichte");
		coption.gridx = 0;
		coption.gridy = 2;
		// coption.fill = GridBagConstraints.HORIZONTAL;
		// coption.weightx = 1.0;
		optionpanel.add(buttonO1, coption);

		/*
		 * Button 2 - buttonO2
		 */
		/**
		 * Spielfeldgroesse kann ausgewaehlt werden (Fenster oeffnet sich)
		 */

		JButton buttonO2 = new JButton("Spielfeldgroesse");
		coption.gridx = 0;
		coption.gridy = 3;
		optionpanel.add(buttonO2, coption);

		/**
		 * Spiegelung an/aus (Button 3/buttonO3)
		 */
		JToggleButton buttonO3 = new JToggleButton(spiegel
				+ getBoolState(sys.getspiegelung()), sys.getspiegelung());
		coption.gridx = 0;
		coption.gridy = 4;
		optionpanel.add(buttonO3, coption);

		/**
		 * 
		 * Standardlevel an/aus (Button 4/buttonO4)
		 */

		JToggleButton buttonO4 = new JToggleButton(standard
				+ getBoolState(sys.getstandardlvl()), sys.getstandardlvl());
		coption.gridx = 0;
		coption.gridy = 5;
		optionpanel.add(buttonO4, coption);

		// /** KI ist noch nicht implementiert
		// *
		// * KI an/aus (Button 5/buttonKI)
		// */
		//
		// JToggleButton buttonKI = new JToggleButton(stringKI
		// + getBoolState(sys.getboolKI()), sys.getboolKI());
		// coption.gridx = 0;
		// coption.gridy = 6;
		// optionpanel.add(buttonKI, coption);

		JToggleButton buttonSound = new JToggleButton("Sound "
				+ getBoolState(sys.getSound()), sys.getSound());
		coption.gridx = 0;
		coption.gridy = 7;
		optionpanel.add(buttonSound, coption);

		/**
		 * Optionsmenue wird verlassen (Button 6/buttonO5)
		 */

		JButton buttonO5 = new JButton("Zur" + "\u00FC" + "ck");
		coption.gridx = 0;
		coption.gridy = 8;
		optionpanel.add(buttonO5, coption);

		frameoption.setSize(frameoption.getPreferredSize());
		int height = frameoption.getPreferredSize().height;
		int width = frameoption.getPreferredSize().width;
		centerWindow(width, height, frameoption);

		/**
		 * Listener werden hinzugefuegt, damit Buttons eine Funktion haben
		 * oeffnen eines Fensters, um die Mauerdichte einzustellen Mauerdichte
		 * wird in die lokale Variable sys uebernommen (Button 1 - Mauerdichte)
		 */

		buttonO1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionWall density = new OptionWall();
				density.optionwall();
			}
		});

		/**
		 * Spielfeld waehlen (Button 2/Spielfeldgroessee)
		 */

		buttonO2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Optionfield opfield = new Optionfield();
				opfield.opfieldEinst();
			}
		});

		/**
		 * Die Spiegelung wird eingestellt --> Aus oder ein (Button
		 * 3/Spiegelung)
		 */

		buttonO3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sys.setspiegelung(!sys.getspiegelung());
				System.out.println("Spiegelung: " + sys.getspiegelung());

				JToggleButton buttonO3 = (JToggleButton) e.getSource();
				buttonO3.setText(spiegel + getBoolState(sys.getspiegelung()));
			}
		});

		/**
		 * Ein und ausschalten des Standardlevels (Button 3/Standardlevel)
		 */

		buttonO4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sys.setstandardlvl(!sys.getstandardlvl());
				System.out.println("StandardLevel: " + sys.getstandardlvl());

				JToggleButton buttonO4 = (JToggleButton) e.getSource();
				buttonO4.setText(standard + getBoolState(sys.getstandardlvl()));
			}
		});

		// /** KI ist noch nicht implementiert
		// * Ein und ausschalten der KI (buttonKI)
		// */
		//
		// buttonKI.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// sys.setboolKI(!sys.getboolKI());
		// System.out.println("KI: " + sys.getboolKI());
		//
		// JToggleButton buttonKI = (JToggleButton) e.getSource();
		// buttonKI.setText(stringKI + getBoolState(sys.getboolKI()));
		// }
		// });

		buttonSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sys.setSound(!sys.getSound());
				System.out.println("Sound: " + sys.getSound());

				JToggleButton buttonSound = (JToggleButton) e.getSource();
				buttonSound.setText("Sound " + getBoolState(sys.getSound()));
			}
		});

		/**
		 * Fenster schliessen und zurueck gehen Audio wird erzeugt
		 */

		buttonO5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameoption.dispose();
			}
		});

		if (sys.getMouseOverBool()) {

			buttonO1.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (sys.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonO2.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (sys.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonO3.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (sys.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonO4.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (sys.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			buttonO5.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (sys.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});

			// buttonKI.addMouseListener(new java.awt.event.MouseAdapter() {
			// public void mouseEntered(java.awt.event.MouseEvent evt) {
			// if (sys.getSound()) {
			// StdAudio.play("data/audio/mouseover.wav");
			// }
			// }
			// });

			buttonSound.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if (sys.getSound()) {
						StdAudio.play("data/audio/mouseover.wav");
					}
				}
			});
		}
	}

	/*
	 * Kleine Funktion die nur in Optionsmenu ben�tigt wird. Daher private.
	 */

	/**
	 * Funktion, um True/False gegen aktiviert/deaktiviert zu tauschen.
	 * 
	 * @param state
	 * @return Gibt den String aktiviert bei True oder deaktiviert bei False
	 *         zurueck.
	 */

	private String getBoolState(boolean state) {
		if (state) {
			return "aktiviert";
		} else {
			return "deaktiviert";
		}
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
