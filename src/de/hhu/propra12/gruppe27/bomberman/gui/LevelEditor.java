package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
 * 
 * @author
 * @version 1.0 Klasse zum Einstellen der Level
 * 
 */

public class LevelEditor {

	/**
	 * Feldbestimmung
	 */

	SysEinst system = SysEinst.getSystem();
	int feldx = system.getfeldx();
	int feldy = system.getfeldy();

	JButton button[][] = new JButton[feldx][feldy];

	/**
	 * Größe
	 */

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

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		LevelEditor editor = new LevelEditor();
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
														// an den String gehängt
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
																// Übergibt
																// die Werte an
																// den
																// OutPutStream.
				bos.close(); // Datei/Stream schließen
				return true; // Wenn erfolgreich true zurückgeben
			} catch (IOException eIO) {
				System.out.println("Fehler beim Schreiben in die Leveldatei!");
				return false; // Bei IOException Konsolenausgabe + false zurück.
			}
		} else {
			System.out.println("Keine Zieldatei ausgewählt!");
			return false;
		}
	}
}
