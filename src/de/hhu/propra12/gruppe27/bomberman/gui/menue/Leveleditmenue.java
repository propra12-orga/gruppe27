package de.hhu.propra12.gruppe27.bomberman.gui.menue;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.gui.LevelEditor;

/**
 * 
 * @author
 * @version 1.0 Klasse fuer die Einstellungen des Feldes für den Leveleditor
 */

public class Leveleditmenue {

	SysEinst system = SysEinst.getSystem();

	/**
	 * Einstellungen fuer den Leveleditor --> Feldgroesse (3
	 * Auswahlmoeglichkeiten: 15x15, 19x19, 11x11)
	 * 
	 * @param system
	 * @return sysopfield
	 */

	public void leveleditmenue() {

		final JFrame frameedit = new JFrame("Spielfeldgroesse");
		frameedit.setVisible(true);
		// frameedit.setSize(640, 640);
		frameedit.setResizable(false);
		frameedit.setLocationRelativeTo(null);
		frameedit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel paneledit = new JPanel(new GridBagLayout());
		frameedit.add(paneledit);

		frameedit.getContentPane().add(paneledit, BorderLayout.SOUTH);
		GridBagConstraints cedit = new GridBagConstraints();

		cedit.gridx = 0;
		cedit.gridy = 0;

		/**
		 * Knoepfe werden definiert Button 1 fuer 11x11
		 */

		JButton buttonedit1 = new JButton("Spielfeld: 11 x 11");
		cedit.gridx = 0;
		cedit.gridy = 1;
		cedit.fill = GridBagConstraints.HORIZONTAL;
		cedit.weightx = 1.0;
		paneledit.add(buttonedit1, cedit);

		/**
		 * Button 2 fuer 15x15
		 */

		JButton buttonedit2 = new JButton("Spielfeld 15 x 15 (default)");
		cedit.gridx = 0;
		cedit.gridy = 2;
		paneledit.add(buttonedit2, cedit);

		/**
		 * Button 3 fuer 19x19
		 */

		JButton buttonedit3 = new JButton("Spieldfeld 19 x 19");
		cedit.gridx = 0;
		cedit.gridy = 3;
		paneledit.add(buttonedit3, cedit);

		frameedit.setSize(frameedit.getPreferredSize()); // Frame an Buttons
															// anpassen
		/*
		 * 
		 * jeweils feldx und feldy setzen je nach ausgewählter Größe
		 */

		/**
		 * Aktion der Knoepfe Listener wird ausgefuehrt, damit Knopfdruck
		 * bearbeitet werden kann
		 * 
		 */

		buttonedit1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setfeldx(11);
				system.setfeldy(11);
				LevelEditor editor = new LevelEditor(system.getfeldx(), system
						.getfeldy());
				editor.leveleditor();
				frameedit.dispose();
			}
		});

		buttonedit2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setfeldx(15);
				system.setfeldy(15);
				LevelEditor editor = new LevelEditor(system.getfeldx(), system
						.getfeldy());
				editor.leveleditor();
				frameedit.dispose();
			}
		});

		buttonedit3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setfeldx(19);
				system.setfeldy(19);
				LevelEditor editor = new LevelEditor(system.getfeldx(), system
						.getfeldy());
				editor.leveleditor();
				frameedit.dispose();
			}
		});

	}
}
