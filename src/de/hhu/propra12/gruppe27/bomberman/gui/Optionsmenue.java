package de.hhu.propra12.gruppe27.bomberman.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Optionmenue {

	public int optionaufruf() {

		JFrame frameoption = new JFrame("Optionsmenue");
		frameoption.setVisible(true);
		frameoption.setSize(400, 400);
		frameoption.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //DISPOSE statt EXIT!

		JLabel optionbild = new JLabel("Platzhalter Bild");
		JPanel optionpanel = new JPanel();

		frameoption.add(optionpanel);
		optionpanel.add(optionbild);

		JButton buttonO1 = new JButton("Mauerdichte");
		optionpanel.add(buttonO1);
		JButton buttonO2 = new JButton("Spielfeldgröße");
		optionpanel.add(buttonO2);

		return (1);
	}
}