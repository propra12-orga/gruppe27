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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import de.hhu.propra12.gruppe27.bomberman.core.Block;
import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.LevelGen;
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
	int feldx = system.getfeldxbml();
	int feldy = system.getfeldybml();

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

		/*
		 * Frame initialisieren und Sichtbar machen. Feste Gr��e. Mittig
		 * ausrichten.
		 */
		final JFrame frameeditor = new JFrame("Leveleditor");
		frameeditor.setVisible(true);
		frameeditor.setResizable(false);
		frameeditor.setLocationRelativeTo(null);
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
		ButtonGroup group = new ButtonGroup();
		JToggleButton buttonF = new JToggleButton("Frei");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(buttonF, c);
		group.add(buttonF);

		JToggleButton buttonW = new JToggleButton("Feste Wand");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(buttonW, c);
		group.add(buttonW);

		JToggleButton buttonZ = new JToggleButton("Zerst\u00f6rbar");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(buttonZ, c);
		group.add(buttonZ);

		JToggleButton buttonE = new JToggleButton("Ausgang");
		c.gridx = 0;
		c.gridy = 3;
		panel.add(buttonE, c);
		group.add(buttonE);

		JToggleButton buttonV = new JToggleButton("Versteckt");
		c.gridx = 0;
		c.gridy = 4;
		panel.add(buttonV, c);
		group.add(buttonV);

		JButton buttonImp = new JButton("Importieren");
		c.gridx = 0;
		c.gridy = 6;
		panel.add(buttonImp, c);

		JButton buttonEx = new JButton("Exportieren");
		c.gridx = 0;
		c.gridy = 7;
		panel.add(buttonEx, c);

		frameeditor.setSize(frameeditor.getPreferredSize());

		/*
		 * ActionListener hinzuf�gen. Wenn ein Button gedr�ckt wird wird
		 * �berpr�ft, was das aktuelle Label ist und wird umbenannt. Dabei wird
		 * das Fenster an die neue Buttongr��e angepasst, um zu verhindern, dass
		 * Buttons au�erhalb des Fensterrands geraten
		 */
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
							frameeditor.setSize(frameeditor.getPreferredSize());
						}
						if (1 == intfeld) {
							laxbrwall(k, l);
							button[k][l].setText("W");
							frameeditor.setSize(frameeditor.getPreferredSize());
						}
						if (2 == intfeld) {
							laxbrblock(k, l);
							button[k][l].setText("B");
							frameeditor.setSize(frameeditor.getPreferredSize());
						}
						if (3 == intfeld) {
							laxbrexit(k, l);
							button[k][l].setText("E");
							frameeditor.setSize(frameeditor.getPreferredSize());
						}
						if (4 == intfeld) {
							laxbrhidden(k, l);
							button[k][l].setText("V");
							frameeditor.setSize(frameeditor.getPreferredSize());
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

		buttonImp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				OpenFileDialog filedialog = new OpenFileDialog();

				if (importlvl(filedialog.loadFile(new Frame(),
						"Level importieren...", ".\\data\\levels", "*.bml"))) {
					System.out.println("Level erfolgreich eingelesen!");
					frameeditor.setSize(frameeditor.getPreferredSize());
				} else {
					System.out.println("Fehler beim Einlesen der Datei!");
				}
			}
		});

		buttonEx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Properties levelstructure = new Properties();
				levelstructure.setProperty("LEVEL", convertButtons());
				levelstructure.setProperty("LAENGE", Integer.toString(feldx));
				levelstructure.setProperty("BREITE", Integer.toString(feldy));

				OpenFileDialog filedialog = new OpenFileDialog();

				if (exportlvl(filedialog.saveFile(new Frame(),
						"Level exportieren...", ".\\data\\levels", "*.bml"),
						levelstructure)) {
					System.out.println("Level erfolgreich gespeichert!");
				} else {
					System.out.println("Fehler beim speichern der Leveldatei!");
				}
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
	 * Eine Methode, die die Labels der Level-Editor Buttons in einen String
	 * umsetzt und f�r das Ende der ersten Dimension immer ein Semikolon setzt.
	 * 
	 * @return Gibt einen String zur�ck, der die Button-Labels enth�lt.
	 * */
	public String convertButtons() {
		String output = "";

		for (int i = 0; i < feldx; i++) {
			for (int j = 0; j < feldy; j++) {

				String compare = button[j][i].getText(); // Damit .getText()
															// nicht bei jedem
															// if ausgef�hrt
															// werden muss.

				if (compare == "F") {
					output += "0";
				} else if (compare == "W") {
					output += "1";
				} else if (compare == "B") {
					output += "2";
				} else if (compare == "E") {
					output += 3;
				} else if (compare == "V") {
					output += 4;
				}
			}
			output += ";"; // Um das Ende einer Array Dimension zu deklarieren
							// ein Semikolon. Siehe auch Levelimporter in
							// LevelGen.java
		}
		return output;
	}

	/**
	 * Eine Funktion, um die Levelstruktur in eine *.bml-Datei zu schreiben.
	 * Initialisiert einen BufferedOutPutStream(FileOutPutStream(Pfad)) und
	 * verwendet .store aus der Properties-Lib, um den Dateiinhalt zu schreiben.
	 * 
	 * @param filepath
	 *            Totaler Pfad zur Datei in die geschrieben werden soll.
	 * @param ToBeSaved
	 *            Property-Objekt, dass geschrieben/gespeichert werden soll.
	 * @return Gibt true bei erfolgreichem Export oder false bei nicht
	 *         erfolgreichem Export zur�ck.
	 */
	public boolean exportlvl(String filepath, Properties ToBeSaved) {
		String manual = "\nInformationen:\n0 ist ein begehbarer Weg\n1 ist ein unzerst�rbarer Block\n2 ist ein zerst�rbarer Block\n3 ist ein Ausgang\n4 ist ein Versteck";
		if (filepath != null) {
			try {
				BufferedOutputStream bos = new BufferedOutputStream( // BOS
																		// initialisieren
						new FileOutputStream(filepath)); // FOS initialisieren

				ToBeSaved.store(bos, "Exported BomberMan-Level" + manual); // .store
																			// ist
				// eine
				// Properties
				// Methode.
				// �bergibt
				// die Werte
				// an
				// den
				// OutPutStream.
				bos.close(); // Datei/Stream schlie�en
				return true; // Wenn erfolgreich true zur�ckgeben
			} catch (IOException eIO) {
				System.out.println("Fehler:" + eIO.getMessage());
				return false; // Bei IOException Konsolenausgabe + false
								// zur�ck.
			}
		} else {
			System.out.println("Keine Zieldatei ausgew\u00e4hlt!");
			return false;
		}
	}

	public boolean importlvl(String Levelpath) {
		try {
			// Damit er die Datei nicht immer neu oeffnen muss.
			String input = LevelGen.readFile(Levelpath, "LEVEL");
			int i = 0;
			int j = 0;

			for (int k = 0; k < (input.length() - 1); k++) {

				if (input.charAt(k) == ';') {
					j++;
					i = 0;
				} else if (input.charAt(k) == '4') {
					button[j][i].setText("V");
					i++;
				} else if (input.charAt(k) == '3') {
					button[j][i].setText("E");
					i++;
				} else if (input.charAt(k) == '2') {
					button[j][i].setText("Z");
					i++;
				} else if (input.charAt(k) == '1') {
					button[j][i].setText("W");
					i++;
				} else {
					button[j][i].setText("F");
					i++;
				}

			}
		} catch (IOException eIO) {
			System.out.println(eIO.getMessage());
			return false;
		} catch (NumberFormatException eNFE) {
			System.out.println(eNFE.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public int[] getStartposition(int spielernummer) {
		// TODO Auto-generated method stub
		return null;
	}
}
