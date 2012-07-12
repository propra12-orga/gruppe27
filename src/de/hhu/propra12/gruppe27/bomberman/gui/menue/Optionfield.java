package de.hhu.propra12.gruppe27.bomberman.gui.menue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * Klasse fuer die Einstellungen des Optionsfeldes
 * @author gruppe 27
 * @version 1.0 
 */

public class Optionfield {

	SysEinst sysopfield = SysEinst.getSystem();

	/**
	 * Einstellungen fuer das Optionsmenue --> Spielfeldgroesse (3
	 * Auswahlmoeglichkeiten: 15x15, 19x19, 11x11)
	 * 
	 * @param system
	 * 
	 */

	public void opfieldEinst() {

		final JFrame frameopfield = new JFrame("Spielfeldgroesse");
		frameopfield.setVisible(true);
		// frameopfield.setResizable(false);
		frameopfield.setLocationRelativeTo(null);
		frameopfield.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panelopfield = new JPanel(new GridBagLayout());
		frameopfield.add(panelopfield);

		frameopfield.getContentPane().add(panelopfield, BorderLayout.SOUTH);
		GridBagConstraints copfield = new GridBagConstraints();

		copfield.gridx = 0;
		copfield.gridy = 0;


		JButton buttonopfield1 = new JButton("Spielfeld: 11 x 11");
		copfield.gridx = 0;
		copfield.gridy = 1;
		copfield.fill = GridBagConstraints.HORIZONTAL;
		copfield.weightx = 1.0;
		panelopfield.add(buttonopfield1, copfield);


		JButton buttonopfield2 = new JButton("Spielfeld 15 x 15 (default)");
		copfield.gridx = 0;
		copfield.gridy = 2;
		panelopfield.add(buttonopfield2, copfield);

		
		JButton buttonopfield3 = new JButton("Spieldfeld 19 x 19");
		copfield.gridx = 0;
		copfield.gridy = 3;
		panelopfield.add(buttonopfield3, copfield);

		frameopfield.setSize(frameopfield.getPreferredSize());
		int height = frameopfield.getPreferredSize().height;
		int width = frameopfield.getPreferredSize().width;
		centerWindow(width, height, frameopfield);


		/**
		 * Aktion der Knoepfe Listener wird ausgefuehrt, damit Knopfdruck
		 * bearbeitet werden kann
		 * 
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
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param frame
	 * Parameter fuer das Fenster werden uebergeben
	 */

	public void centerWindow(int width, int height, JFrame frame) {
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		frame.setLocation((screensize.width - width) / 2,
				(screensize.height - height) / 2);
	}
}
