package de.hhu.propra12.gruppe27.bomberman.gui.menue;

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

import de.hhu.propra12.gruppe27.bomberman.gui.GameWindow;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse fuer verlorenes Spiel
 * Grafik wird uebergeben
 */

public class LosetheGame {

	Icon loseicon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/VerlorenTranz.gif");

	/**
	 * Einblendung von "Verloren", "Noch einmal" und "Zurueck zum Startmenue"
	 */

	public void losethegame() {

		final JFrame framelose = new JFrame("Verloren");
		framelose.setVisible(true);
		framelose.setResizable(false);
		framelose.setLocationRelativeTo(null);
		framelose.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panellose = new JPanel(new GridBagLayout());
		framelose.add(panellose);

		framelose.getContentPane().add(panellose, BorderLayout.SOUTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		JButton buttonL0 = new JButton(loseicon);
		c.gridx = 0;
		c.gridy = 1;
		// c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2.0;
		panellose.add(buttonL0, c);

		JButton buttonL1 = new JButton("Noch einmal!");
		c.gridx = 0;
		c.gridy = 2;
		// c.gridwidth = 2;
		panellose.add(buttonL1, c);

		JButton buttonL2 = new JButton("Zurück zum Startmenue");
		c.gridx = 0;
		c.gridy = 3;
		// c.gridwidth = 2;
		panellose.add(buttonL2, c);

		framelose.setSize(framelose.getPreferredSize());

		buttonL1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow s = new GameWindow(0);
				framelose.dispose();
			}
		});

		buttonL2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Startmenue start = new Startmenue();
				start.menueaufruf();

				framelose.dispose();
			}
		});

	}

}
