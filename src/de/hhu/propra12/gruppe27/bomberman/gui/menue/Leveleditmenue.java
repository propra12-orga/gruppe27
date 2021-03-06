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
import de.hhu.propra12.gruppe27.bomberman.gui.LevelEditor;

/**
 * Klasse fuer die Einstellungen des Feldes fuer den Leveleditor
 * @author gruppe 27
 * @version 1.0
 */

public class Leveleditmenue {

	SysEinst system = SysEinst.getSystem();

/**
 * Einstellungen fuer den Leveleditor --> Feldgroesse (3
 * Auswahlmoeglichkeiten: 15x15, 19x19, 11x11)
 */

	public void leveleditmenue() {

		final JFrame frameedit = new JFrame("Spielfeldgroesse");
		frameedit.setVisible(true);
		// frameedit.setResizable(false);
		frameedit.setLocationRelativeTo(null);
		frameedit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel paneledit = new JPanel(new GridBagLayout());
		frameedit.add(paneledit);

		frameedit.getContentPane().add(paneledit, BorderLayout.SOUTH);
		GridBagConstraints cedit = new GridBagConstraints();

		cedit.gridx = 0;
		cedit.gridy = 0;

		

		JButton buttonedit1 = new JButton("Spielfeld: 11 x 11");
		cedit.gridx = 0;
		cedit.gridy = 1;
		cedit.fill = GridBagConstraints.HORIZONTAL;
		cedit.weightx = 1.0;
		paneledit.add(buttonedit1, cedit);

		

		JButton buttonedit2 = new JButton("Spielfeld 15 x 15 (default)");
		cedit.gridx = 0;
		cedit.gridy = 2;
		paneledit.add(buttonedit2, cedit);

		

		JButton buttonedit3 = new JButton("Spieldfeld 19 x 19");
		cedit.gridx = 0;
		cedit.gridy = 3;
		paneledit.add(buttonedit3, cedit);

		frameedit.setSize(frameedit.getPreferredSize());
		int height = frameedit.getPreferredSize().height;
		int width = frameedit.getPreferredSize().width;
		centerWindow(width, height, frameedit);

		

		buttonedit1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setfeldxbml(11);
				system.setfeldybml(11);
				LevelEditor editor = new LevelEditor(system.getfeldxbml(),
						system.getfeldybml());
				editor.leveleditor();
				frameedit.dispose();
			}
		});
		
		/**
		 * ActionListener wird hinzugefuegt, fuer Knopfdruck
		 */

		buttonedit2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setfeldxbml(15);
				system.setfeldybml(15);
				LevelEditor editor = new LevelEditor(system.getfeldxbml(),
						system.getfeldybml());
				editor.leveleditor();
				frameedit.dispose();
			}
		});
		
		/**
		 * @see buttonedit2
		 */

		buttonedit3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				system.setfeldxbml(19);
				system.setfeldybml(19);
				LevelEditor editor = new LevelEditor(system.getfeldxbml(),
						system.getfeldybml());
				editor.leveleditor();
				frameedit.dispose();
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
