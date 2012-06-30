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
 * @author
 * @version 1.0
 * Klasse f�r das Gewinnen des Spiels
 *
 */

public class WintheGame {


	Icon winicon = new ImageIcon(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/GewonnenTranz.gif");
	
	/**
	 * "Gewonnen" wird ausgegeben, wenn man das Spiel erfolgreich beendet hat
	 */

	public void winthegame() {

		final JFrame framewin = new JFrame("Gewonnen");
		framewin.setVisible(true);
		framewin.setSize(640, 640);
		framewin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelwin = new JPanel(new GridBagLayout());
		framewin.add(panelwin);

		framewin.getContentPane().add(panelwin, BorderLayout.SOUTH);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;

		JButton buttonW0 = new JButton(winicon);
		c.gridx = 0;
		c.gridy = 1;
		// c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 2.0;
		panelwin.add(buttonW0, c);

		/**
		 * Button, f�r einen neuen Spielstart
		 */
		
		JButton buttonW1 = new JButton("Noch einmal!");
		c.gridx = 0;
		c.gridy = 2;
		// c.gridwidth = 2;
		panelwin.add(buttonW1, c);

		/**
		 * Button, wenn man zur�ck zum Startmen� m�chte
		 */
		
		JButton buttonW2 = new JButton("Zurück zum Startmenue");
		c.gridx = 0;
		c.gridy = 3;
		// c.gridwidth = 2;
		panelwin.add(buttonW2, c);

		buttonW1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow s = new GameWindow(0);
				framewin.dispose();
			}
		});


		
		buttonW2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Startmenue start = new Startmenue();
				start.menueaufruf();

				framewin.dispose();
			}
		});

	}
}
