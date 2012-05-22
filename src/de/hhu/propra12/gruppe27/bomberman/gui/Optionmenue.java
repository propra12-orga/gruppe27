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

public class Optionmenue {

	int opwall;

	public int optionaufruf() {

		final JFrame frameoption = new JFrame("Optionsmenue");
		frameoption.setVisible(true);
		frameoption.setSize(640, 640);
		frameoption.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // DISPOSE
																		// statt
																		// EXIT!

		JLabel optionbild = new JLabel("Platzhalter Bild");
		JPanel optionpanel = new JPanel(new GridBagLayout());
		frameoption.add(optionpanel);

		frameoption.getContentPane().add(optionpanel, BorderLayout.SOUTH);
		GridBagConstraints coption = new GridBagConstraints();

		coption.gridx = 0;
		coption.gridy = 0;
		optionpanel.add(optionbild);

		JButton buttonO1 = new JButton("Mauerdichte");
		coption.gridx = 0;
		coption.gridy = 1;
		coption.fill = GridBagConstraints.HORIZONTAL;
		coption.weightx = 1.0;
		optionpanel.add(buttonO1, coption);

		JButton buttonO2 = new JButton("Spielfeldgroesse");
		coption.gridx = 0;
		coption.gridy = 2;
		optionpanel.add(buttonO2, coption);

		JButton buttonO3 = new JButton("zurück");
		coption.gridx = 0;
		coption.gridy = 3;
		optionpanel.add(buttonO3, coption);

		buttonO1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				OptionWall density = new OptionWall();
				opwall = density.optionwall();
			}

		});

		buttonO3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameoption.dispose();
			}

		});

		return (1);
	}
}