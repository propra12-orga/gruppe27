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
 * Klasse zur Einstellung der Waende, (Mauerdichte)
 * Methode um die Mauerdichte einzustellen
 * 
 * int-Wer wird zwischen 0 und 10 festgelegt (0 = keine Mauer, 10 = nur
 * Mauern) 
 * Buttons durchnummeriert von 0 bis 10 Wert wird gesetzt, Fenster
 * geschlossen und zurueck an das Optionsmenue gegeben
 * @author gruppe 27
 * @version 1.0 
 * 
 */

public final class OptionWall {

	SysEinst sysopwall = SysEinst.getSystem();



	public void optionwall() {

		final JFrame framemauer = new JFrame("Mauerdichte");
		framemauer.setVisible(true);
		// framemauer.setResizable(false);
		framemauer.setLocationRelativeTo(null);
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

		JButton buttonMau03 = new JButton("2");
		cmauer.gridx = 0;
		cmauer.gridy = 3;
		panelmauer.add(buttonMau03, cmauer);

		JButton buttonMau04 = new JButton("3");
		cmauer.gridx = 0;
		cmauer.gridy = 4;
		panelmauer.add(buttonMau04, cmauer);

		JButton buttonMau05 = new JButton("4");
		cmauer.gridx = 0;
		cmauer.gridy = 5;
		panelmauer.add(buttonMau05, cmauer);

		JButton buttonMau06 = new JButton("5");
		cmauer.gridx = 0;
		cmauer.gridy = 6;
		panelmauer.add(buttonMau06, cmauer);

		JButton buttonMau07 = new JButton("6");
		cmauer.gridx = 0;
		cmauer.gridy = 7;
		panelmauer.add(buttonMau07, cmauer);

		JButton buttonMau08 = new JButton("7");
		cmauer.gridx = 0;
		cmauer.gridy = 8;
		panelmauer.add(buttonMau08, cmauer);

		JButton buttonMau09 = new JButton("8");
		cmauer.gridx = 0;
		cmauer.gridy = 9;
		panelmauer.add(buttonMau09, cmauer);

		JButton buttonMau10 = new JButton("9");
		cmauer.gridx = 0;
		cmauer.gridy = 10;
		panelmauer.add(buttonMau10, cmauer);

		JButton buttonMau11 = new JButton("10");
		cmauer.gridx = 0;
		cmauer.gridy = 11;
		panelmauer.add(buttonMau11, cmauer);

		framemauer.setSize(framemauer.getPreferredSize());
		int height = framemauer.getPreferredSize().height;
		int width = framemauer.getPreferredSize().width;
		centerWindow(width, height, framemauer);

		buttonMau01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(0);
				framemauer.dispose();
			}
		});

		buttonMau02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(1);
				framemauer.dispose();
			}
		});

		buttonMau03.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(2);
				framemauer.dispose();
			}
		});

		buttonMau04.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(3);
				framemauer.dispose();
			}
		});

		buttonMau05.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(4);
				framemauer.dispose();
			}
		});

		buttonMau06.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(5);
				framemauer.dispose();
			}
		});

		buttonMau07.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(6);
				framemauer.dispose();
			}
		});

		buttonMau08.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(7);
				framemauer.dispose();
			}
		});

		buttonMau09.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(8);
				framemauer.dispose();
			}
		});

		buttonMau10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(9);
				framemauer.dispose();
			}
		});

		buttonMau11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sysopwall.setdensWall(10);
				framemauer.dispose();
			}
		});
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @param frame
	 * Parameter fuer Fenster wird uebergeben
	 */

	public void centerWindow(int width, int height, JFrame frame) {
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		frame.setLocation((screensize.width - width) / 2,
				(screensize.height - height) / 2);
	}

}
