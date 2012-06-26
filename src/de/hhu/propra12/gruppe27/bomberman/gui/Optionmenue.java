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
import javax.swing.JToggleButton;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author
 * @version 1.0
 *  Klasse für das Optionsmenü
 */

public class Optionmenue {

	SysEinst sys = SysEinst.getSystem();

	String spiegel = "Spiegelung: ";
	String standard = "Standardlevel: ";

	// Icon icon = new ImageIcon(
	// "src/de/hhu/propra12/gruppe27/bomberman/graphics/warofstickman.gif");

	Icon optionicon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/warofstickmen.gif");

	/**
	 * Methode um das Optionsmenü aufzurufen
	 * @param system
	 * @return sys Systemeinstellungen können getätigt werden, Buttons werden
	 *         definiert
	 */

	public SysEinst optionaufruf() {

		final JFrame frameoption = new JFrame("Optionsmenue");
		frameoption.setVisible(true);
		frameoption.setSize(640, 640);
		frameoption.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel optionpanel = new JPanel(new GridBagLayout());
		frameoption.add(optionpanel);

		frameoption.getContentPane().add(optionpanel, BorderLayout.NORTH);
		GridBagConstraints coption = new GridBagConstraints();

		coption.gridx = 0;
		coption.gridy = 0;

		// JLabel optionbild = new JLabel(optionicon);
		// coption.fill = GridBagConstraints.HORIZONTAL;
		// coption.weightx = 1.0;
		//
		// optionpanel.add(optionbild, coption);

		// optionpanel.add(optionbild);

		/**
		 * Button 0 - buttonO0
		 * 
		 * Bild für Optionsmenü
		 */
		
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
		 * Mauerdichte kann eingestellt werden (Fenster wird geöffnet) --> zerstörbare Wände
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
		 * Spielfeldgröße kann ausgewählt werden (Fenster öffnet sich)
		 */
		
		JButton buttonO2 = new JButton("Spielfeldgroesse");
		coption.gridx = 0;
		coption.gridy = 3;
		optionpanel.add(buttonO2, coption);
		
		/**
		 * Optionsmenü wird verlassen (Button 3/buttonO3)
		 */
		
		final JToggleButton buttonO3 = new JToggleButton(spiegel
				+ sys.getspiegelung(), sys.getspiegelung());
		coption.gridx = 0;
		coption.gridy = 4;
		optionpanel.add(buttonO3, coption);

		/**
		 * 
		 * Optionsmenü wird verlassen (Button 4/buttonO4)
		 */
		
		
		JToggleButton buttonO4 = new JToggleButton(standard
				+ sys.getstandardlvl(), sys.getstandardlvl());
		coption.gridx = 0;
		coption.gridy = 5;
		optionpanel.add(buttonO4, coption);

	
		/**
		 * Optionsmenü wird verlassen (Button 5/buttonO5)
		 */
		
		JButton buttonO5 = new JButton("zurÃ¼ck");
		coption.gridx = 0;
		coption.gridy = 6;
		optionpanel.add(buttonO5, coption);


		/**
		 * Listener werden hinzugefï¿½gt, damit Buttons eine Funktion haben
		 * Öffnen eines Fensters, um die Mauerdichte einzustellen
		 * Mauerdichte wird in die lokale Variable sys übernommen (Button 1 - Mauerdichte)
		 */

		buttonO1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionWall density = new OptionWall();
				density.optionwall();
			}
		});

		
		/**
		 * Spielfeld wählen (Button 2/Spielfeldgröße)
		 */
		
		buttonO2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Optionfield opfield = new Optionfield();
				opfield.opfieldEinst();
			}
		});


		
		/**
		 * Die Spiegelung wird eingestellt --> Aus oder ein (Button 3/Spiegelung)
		 */
		
		buttonO3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sys.setspiegelung(!sys.getspiegelung());
				System.out.println(sys.getspiegelung());

				JToggleButton buttonO3 = (JToggleButton) e.getSource();
				buttonO3.setText(spiegel + sys.getspiegelung());
			}
		});


		
		/**
		 * Ein und ausschalten des Standardlevels (Button 3/Standardlevel)
		 */
		
		buttonO4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sys.setstandardlvl(!sys.getstandardlvl());
				System.out.println(sys.getstandardlvl());

				JToggleButton buttonO4 = (JToggleButton) e.getSource();
				buttonO4.setText(standard + sys.getstandardlvl());
			}
		});

		/**
		 * Fenster schlieÃŸen und zurüch gehen
		 */
		
		buttonO5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameoption.dispose();
			}
		});

		return (sys);
	}
}
