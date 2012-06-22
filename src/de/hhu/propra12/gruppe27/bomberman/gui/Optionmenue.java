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

/**
 * 
 * @author
 * @version 1.0 Klasse f�r das Optionsmen�
 */

public class Optionmenue {

	SysEinst sys = SysEinst.getSystem();

	/*
	 * Methode um das Optionsmenue aufzurufen
	 */
	/**
	 * 
	 * @param system
	 * @return sys Systemeinstellungen k�nnen get�tigt werden, Buttons werden
	 *         definiert
	 */

	public SysEinst optionaufruf() {

		final JFrame frameoption = new JFrame("Optionsmenue");
		frameoption.setVisible(true);
		frameoption.setSize(640, 640);
		frameoption.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel optionbild = new JLabel("Platzhalter Bild");
		JPanel optionpanel = new JPanel(new GridBagLayout());
		frameoption.add(optionpanel);

		frameoption.getContentPane().add(optionpanel, BorderLayout.SOUTH);
		GridBagConstraints coption = new GridBagConstraints();

		coption.gridx = 0;
		coption.gridy = 0;
		optionpanel.add(optionbild);

		/*
		 * Button 1 - buttonO1
		 * 
		 * Fenster öffnen um die Mauerdichte einzustellen Mauerdichte der
		 * zerstörbaren Wände
		 */
		JButton buttonO1 = new JButton("Mauerdichte");
		coption.gridx = 0;
		coption.gridy = 1;
		coption.fill = GridBagConstraints.HORIZONTAL;
		coption.weightx = 1.0;
		optionpanel.add(buttonO1, coption);

		/*
		 * Button 2 - buttonO2
		 * 
		 * Fenster öffnen um die Spielfeldgröße auszuwählen
		 */
		JButton buttonO2 = new JButton("Spielfeldgroesse");
		coption.gridx = 0;
		coption.gridy = 2;
		optionpanel.add(buttonO2, coption);

		/*
		 * Button 3 - buttonO3
		 * 
		 * Optionsmenue verlassen
		 */
		JButton buttonO3 = new JButton("zurück");
		coption.gridx = 0;
		coption.gridy = 3;
		optionpanel.add(buttonO3, coption);

		/*
		 * Button 1 - Mauerdichte
		 * 
		 * Öffnen eines Fensters um die Mauerdichte einzustellen Übernahme der
		 * Mauerdichte in die lokale Variable sys
		 */

		/**
		 * Listener werden hinzugef�gt, damit Buttons eine Funktion haben
		 */

		buttonO1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionWall density = new OptionWall();
				density.optionwall();
			}

		});

		/*
		 * Button 2 - Spielfeldgröße
		 * 
		 * Spielfeld wählen
		 */
		buttonO2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Optionfield opfield = new Optionfield();
				opfield.opfieldEinst();
			}

		});

		buttonO3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frameoption.dispose();
			}

		});

		return (sys);
	}
}