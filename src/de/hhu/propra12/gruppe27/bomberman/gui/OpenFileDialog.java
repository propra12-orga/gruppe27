package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.FileDialog;
import java.awt.Frame;

public class OpenFileDialog {

	/**
	 * Öffnet ein Fenster zum auswählen einer Datei und gibt dessen Pfad zurück.
	 * Zum Laden gedacht.
	 * 
	 * @param fenster
	 * @param title
	 *            Fenstertitel des FileDialog
	 * @param defDir
	 *            Der Pfad der beim Öffnen des FileDialogs automatisch angezeigt
	 *            wird. (Falls vorhanden)
	 * @param fileType
	 *            Dateityp, der angezeigt werden soll. "*" dient als Platzhalter
	 *            für beliebig viele Zeichen.
	 * @return Gibt den totalen Pfad der ausgewählten Datei zurück.
	 */
	@SuppressWarnings("deprecation")
	public String loadFile(Frame fenster, String title, String defDir,
			String fileType) { // Lade-Methode (Frame, Fenstertitel,
								// Startordner, Dateityp der angezeigt/geladen
								// werden soll)
		FileDialog fd = new FileDialog(fenster, title, FileDialog.LOAD); // Neues
																			// Ladefenster
																			// mit
																			// Fenstertitel
		fd.setFile(fileType); // Dateityp
		fd.setDirectory(defDir); // Startordner / Default-Ordner
		fd.setLocation(100, 100); // Fensterposition. Will momentan nicht
									// funktionieren.
		fd.show(); // Frame zeichnen
		if (fd.getFile() != null) {
			return fd.getDirectory() + fd.getFile();
		} else {
			return null;
		}
	}

	/**
	 * Öffnet ein Fenster zum auswählen einer Datei und gibt dessen Pfad zurück.
	 * Zum Speichern gedacht.
	 * 
	 * @param fenster
	 * @param title
	 *            Fenstertitel des FileDialog
	 * @param defDir
	 *            Der Pfad der beim Öffnen des FileDialogs automatisch angezeigt
	 *            wird. (Falls vorhanden)
	 * @param fileType
	 *            Dateityp, der angezeigt werden soll. "*" dient als Platzhalter
	 *            für beliebig viele Zeichen.
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String saveFile(Frame fenster, String title, String defDir,
			String fileType) { // Wie bei loadFile. Nur eben zum Speichern
								// gedacht.
		FileDialog fd = new FileDialog(fenster, title, FileDialog.SAVE);
		fd.setFile(fileType);
		fd.setDirectory(defDir);
		fd.setLocation(100, 100);
		fd.show();
		if (fd.getFile() != null) {
			return fd.getDirectory() + fd.getFile();
		} else {
			return null;
		}
	}
}