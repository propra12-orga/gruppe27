package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.FileDialog;
import java.awt.Frame;

/**
 * Klasse OpenFileDialog zum auswaehlen einer Datei
 * Oeffnet ein Fenster zum auswaehlen einer Datei und gibt dessen Pfad zurueck.
 * Zum Laden gedacht.
 * @param fenster
 * @param title
 * Fenstertitel des FileDialog
 * @param defDir
 * Der Pfad der beim Oeffnen des FileDialogs automatisch angezeigt
 * wird. (Falls vorhanden)
 * @param fileType
 * Dateityp, der angezeigt werden soll. "*" dient als Platzhalter
 * fuer beliebig viele Zeichen.
 * @author Gruppe 27
 * @version 1.0
 *
 */

public class OpenFileDialog {

	
	@SuppressWarnings("deprecation")
	public String loadFile(Frame fenster, String title, String defDir,
			String fileType) { 

		
		FileDialog fd = new FileDialog(fenster, title, FileDialog.LOAD); 
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

	/**
	 * Oeffnet ein Fenster zum auswaehlen einer Datei und gibt dessen Pfad zurueck.
	 * Zum Speichern gedacht.
	 * 
	 * @param fenster
	 * @param title
	 *            Fenstertitel des FileDialog
	 * @param defDir
	 *            Der Pfad der beim oeffnen des FileDialogs automatisch angezeigt
	 *            wird. (Falls vorhanden)
	 * @param fileType
	 *            Dateityp, der angezeigt werden soll. "*" dient als Platzhalter
	 *            fuer beliebig viele Zeichen.
	 * @return null
	 */
	@SuppressWarnings("deprecation")
	public String saveFile(Frame fenster, String title, String defDir,
			String fileType) { 
		
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