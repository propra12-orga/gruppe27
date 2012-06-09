package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

public class Optionfield {

	SysEinst sysopfield = new SysEinst();

	/*
	 * Methode um die Spielfeldgröße einzurichten
	 * 
	 * Wahl zwischen 3 Spielfeldgrößen Feld1 15x15 Feld2 19x19 Feld3 11x11
	 */

	public SysEinst opfieldEinst(SysEinst system) {

		sysopfield = system;

		final JFrame frameopfield = new JFrame("Spielfeldgröße");
		frameopfield.setVisible(true);
		frameopfield.setSize(640, 640);
		frameopfield.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelopfield = new JPanel(new GridBagLayout());
		frameopfield.add(panelopfield);

		frameopfield.getContentPane().add(panelopfield, BorderLayout.SOUTH);
		GridBagConstraints copfield = new GridBagConstraints();

		copfield.gridx = 0;
		copfield.gridy = 0;

		/*
		 * Definition der Knöpfe
		 * 
		 * Button 1 Spielfeldgröße 11x11
		 */
		JButton buttonopfield1 = new JButton("Spielfeld: 11 x 11");
		copfield.gridx = 0;
		copfield.gridy = 1;
		copfield.fill = GridBagConstraints.HORIZONTAL;
		copfield.weightx = 1.0;
		panelopfield.add(buttonopfield1, copfield);

		/*
		 * Definition der Knöpfe
		 * 
		 * Button 2 Spielfeldgröße 15x15
		 */
		JButton buttonopfield2 = new JButton("Spielfeld 15 x 15 (default)");
		copfield.gridx = 0;
		copfield.gridy = 2;
		panelopfield.add(buttonopfield2, copfield);

		/*
		 * Definition der Knöpfe
		 * 
		 * Button 3 Spielfeldgröße 19x25
		 */
		JButton buttonopfield3 = new JButton("Spieldfeld 19 x 19");
		copfield.gridx = 0;
		copfield.gridy = 3;
		panelopfield.add(buttonopfield3, copfield);

		/*
		 * Aktion der Knöpfe
		 * 
		 * jeweils feldx und feldy setzen je nach ausgewählter Größe
		 */
		buttonopfield1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopfield.setfeldx(11);
				sysopfield.setfeldy(11);
				frameopfield.dispose();
			}
		});

		buttonopfield2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopfield.setfeldx(15);
				sysopfield.setfeldy(15);
				frameopfield.dispose();
			}
		});

		buttonopfield3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopfield.setfeldx(19);
				sysopfield.setfeldy(19);
				frameopfield.dispose();
			}
		});

		return (sysopfield);
	}
}
