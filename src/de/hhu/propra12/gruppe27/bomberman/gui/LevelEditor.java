package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.hhu.propra12.gruppe27.bomberman.core.Block;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.Path;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;
import de.hhu.propra12.gruppe27.bomberman.core.Wall;

/**
 * 
 * @author
 * @version 1.0 Klasse zum Einstellen der Level
 * 
 */

public class LevelEditor extends Level implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Feldbestimmung
	 */

	SysEinst system = SysEinst.getSystem();
	int feldx = system.getfeldx();
	int feldy = system.getfeldy();

	int intfeld = 0;

	Exit e;

	JButton button[][] = new JButton[feldx][feldy];

	/**
	 * Konstruktor
	 */

	public LevelEditor(int feldx, int feldy) {
		super(feldx, feldy);
	}

	public void leveleditor() {

		final JFrame frameeditor = new JFrame("Leveleditor");
		frameeditor.setVisible(true);
		frameeditor.setSize(feldx * 50, feldy * 32);
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
				c.gridx = i + 1;
				c.gridy = j;
				c.fill = GridBagConstraints.HORIZONTAL;
				// c.weightx = 1.0;
				panel.add(button[i][j], c);

				laxbr[i][j] = new Path(i, j, this);

			}
		}

		/*
		 * Buttons um die Felder zu setzen F = freies Feld, W = feste Wand, Z =
		 * zerstoerbarer Block, E = Ausgang, V = versteckter Ausgang
		 */

		JToggleButton buttonF = new JToggleButton("Frei");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(buttonF, c);

		JToggleButton buttonW = new JToggleButton("FesteWand");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(buttonW, c);

		JToggleButton buttonZ = new JToggleButton("Zerstoer");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(buttonZ, c);

		JToggleButton buttonE = new JToggleButton("Ausgang");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(buttonE, c);

		JToggleButton buttonV = new JToggleButton("Versteckt");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(buttonV, c);

		JButton buttonS = new JButton("Speichern");
		c.gridx = 0;
		c.gridy = 5;
		panel.add(buttonS, c);

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				final int k = i;
				final int l = j;

				button[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (0 == intfeld) {
							laxbrpath(k, l);
							button[k][l].setText("F");
						}
						if (1 == intfeld) {
							laxbrwall(k, l);
							button[k][l].setText("W");
						}
						if (2 == intfeld) {
							laxbrblock(k, l);
							button[k][l].setText("B");
						}
						if (3 == intfeld) {
							laxbrexit(k, l);
							button[k][l].setText("E");
						}
						if (4 == intfeld) {
							laxbrhidden(k, l);
							button[k][l].setText("V");
						}

					}
				});
			}
		}

		buttonF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				intfeld = 0;

			}
		});

		buttonW.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				intfeld = 1;
			}
		});

		buttonZ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				intfeld = 2;
			}
		});

		buttonE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				intfeld = 3;
			}
		});

		buttonV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				intfeld = 4;
			}
		});

	}

	/*
	 * Methoden um die einzelnen Felder zu bestimmen
	 */

	public void laxbrpath(int i, int j) {
		laxbr[i][j] = new Path(i, j, this);
	}

	public void laxbrwall(int i, int j) {
		laxbr[i][j] = new Wall(i, j, this);
	}

	public void laxbrblock(int i, int j) {
		laxbr[i][j] = new Block(i, j, this);
	}

	public void laxbrexit(int i, int j) {
		laxbr[i][j] = new Path(i, j, this);
		e = new Exit(laxbr[i][j]);
	}

	public void laxbrhidden(int i, int j) {
		laxbr[i][j] = new Block(i, j, this);
		e = new Exit(laxbr[i][j]);
	}

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		LevelEditor editor = new LevelEditor(15, 15);
		editor.leveleditor();

		// Export-Button Routine ANFANG
		Properties levelstructure = new Properties();
		levelstructure.setProperty("LEVEL", editor.convertButtons()); // Alle
																		// Werte
																		// in
																		// ein
																		// neues
																		// Prperty
																		// Objekt
																		// setzen
		levelstructure.setProperty("LAENGE", Integer.toString(editor.feldx));
		levelstructure.setProperty("BREITE", Integer.toString(editor.feldy));

		OpenFileDialog filedialog = new OpenFileDialog();
		if (editor.export(filedialog.saveFile(new Frame(),
				"Level exportieren...", ".\\data\\levels", "*.bml"),
				levelstructure)) { // File-Dialog. Pfad an den
									// Filewriter/Exporter schicken

			System.out.println("Level erfolgreich gespeichert!");
		} else {
			System.out.println("Fehler beim speichern der Leveldatei!");
		}
		// Export-Button Routine ENDE

	}

	public String convertButtons() {

		String output = "";

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {
				if (button[i][j].getText() == "U") { // An den Levelimporter
														// orientiert. Bei den
														// jeweiligen Zeichen
														// wird eine 1, 0 oder 2
														// an den String geh�ngt
					output += "1";
				} else if (button[i][j].getText() == "Z") {
					output += "2";
				} else if (button[i][j].getText() == "F") {
					output += "0";
				}
			}
			output += ";"; // Um das Ende einer Array Dimension zu deklarieren
							// ein Semikolon. Siehe auch Levelimporter in
							// LevelGen.java
		}
		return output;
	}

	public boolean export(String filepath, Properties ToBeSaved) {
		if (filepath != null) {
			try {
				BufferedOutputStream bos = new BufferedOutputStream( // BOS
																		// initialisieren

						new FileOutputStream(filepath)); // FOS initialisieren
				ToBeSaved.store(bos, "Saved BomberMan-Level"); // .store ist
																// eine
																// Properties
																// Methode.
																// �bergibt
																// die Werte an
																// den
																// OutPutStream.
				bos.close(); // Datei/Stream schlie�en
				return true; // Wenn erfolgreich true zur�ckgeben
			} catch (IOException eIO) {
				System.out.println("Fehler beim Schreiben in die Leveldatei!");
				return false; // Bei IOException Konsolenausgabe + false zur�ck.
			}
		} else {
			System.out.println("Keine Zieldatei ausgew�hlt!");
			return false;
		}
	}

	@Override
	public int[] getStartposition(int spielernummer) {
		// TODO Auto-generated method stub
		return null;
	}
}
