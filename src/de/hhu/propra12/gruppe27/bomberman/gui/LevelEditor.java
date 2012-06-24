package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

public class LevelEditor {

	SysEinst system = SysEinst.getSystem();
	int feldx = system.getfeldx();
	int feldy = system.getfeldy();

	JButton button[][] = new JButton[feldx][feldy];

	public void leveleditor() {

		final JFrame frameeditor = new JFrame("Leveleditor");
		frameeditor.setVisible(true);
		frameeditor.setSize(640, 640);
		frameeditor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		frameeditor.add(panel);

		frameeditor.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				button[i][j] = new JButton("F");
				c.gridx = i;
				c.gridy = j;
				c.fill = GridBagConstraints.HORIZONTAL;
				// c.weightx = 1.0;
				panel.add(button[i][j], c);

			}
		}

	}

	public static void main(String[] args) {
		LevelEditor editor = new LevelEditor();
		editor.leveleditor();
	}

}
