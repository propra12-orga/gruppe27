package de.hhu.propra12.gruppe27.bomberman.gui;

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
		JPanel panel = new JPanel();

		framemenue.add(panel);
		panel.add(bild);

		JButton buttonS1 = new JButton("Spiel starten");
		panel.add(buttonS1);
		JButton buttonS2 = new JButton("Optionen");
		panel.add(buttonS2);
		JButton buttonS3 = new JButton("Spiel verlassen");
		panel.add(buttonS3);

		buttonS2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Optionmenue option = new Optionmenue();
				option.optionaufruf();
			}
		});

		return (1);
	}
}
