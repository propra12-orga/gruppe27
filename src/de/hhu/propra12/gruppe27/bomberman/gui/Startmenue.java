package de.hhu.propra12.gruppe27.bomberman.gui;

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
		JLabel wahl = new JLabel("Triff deine Wahl");
		JPanel panel = new JPanel();

		framemenue.add(panel);
		panel.add(bild);

		JButton buttonS1 = new JButton("Spiel starten");
		panel.add(buttonS1);
		JButton buttonS2 = new JButton("Optionen");
		panel.add(buttonS2);
		JButton buttonS3 = new JButton("Spiel verlassen");
		panel.add(buttonS3);

		return (1);
	}
	// Fenster

	// Bild oben

	// 3 Buttons

	// Button Spielstart

	// Button Spiel beenden

	// Optionen

}
