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

public class Startmenue {

	public int menueaufruf() {

		JFrame framemenue = new JFrame("Bomberman Startmenue");
		framemenue.setVisible(true);
		framemenue.setSize(500, 500);
		framemenue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel bild = new JLabel("Platzhalter Bild");
		JPanel panel = new JPanel(new GridBagLayout());
		framemenue.add(panel);

		framemenue.getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		panel.add(bild);

		JButton buttonS1 = new JButton("Spiel starten");
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		panel.add(buttonS1, c);

		JButton buttonS2 = new JButton("Optionen");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(buttonS2, c);

		JButton buttonS3 = new JButton("Spiel verlassen");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(buttonS3, c);

		buttonS1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GameWindow s = new GameWindow(0, 15, 15, 1);
			}
		});

		buttonS2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Optionmenue option = new Optionmenue();
				option.optionaufruf();
			}
		});

		buttonS3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);
			}
		});

		return (1);
	}
}
