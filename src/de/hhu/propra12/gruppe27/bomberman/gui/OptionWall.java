package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class OptionWall {

	int opwall = 1;

	public int optionwall() {

		final JFrame framemauer = new JFrame("Mauerdichte");
		framemauer.setVisible(true);
		framemauer.setSize(640, 640);
		framemauer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelmauer = new JPanel(new GridBagLayout());
		framemauer.add(panelmauer);

		framemauer.getContentPane().add(panelmauer, BorderLayout.SOUTH);
		GridBagConstraints cmauer = new GridBagConstraints();

		cmauer.gridx = 0;
		cmauer.gridy = 0;

		JButton buttonMau01 = new JButton("0");
		cmauer.gridx = 0;
		cmauer.gridy = 1;
		cmauer.fill = GridBagConstraints.HORIZONTAL;
		cmauer.weightx = 1.0;
		panelmauer.add(buttonMau01, cmauer);

		JButton buttonMau02 = new JButton("1");
		cmauer.gridx = 0;
		cmauer.gridy = 2;
		panelmauer.add(buttonMau02, cmauer);

		buttonMau01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				opwall = 0;
				// system.setdensWall(0);
				framemauer.dispose();
			}

		});

		buttonMau01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				opwall = 1;
				framemauer.dispose();
			}

		});

		return opwall;

	}

}
