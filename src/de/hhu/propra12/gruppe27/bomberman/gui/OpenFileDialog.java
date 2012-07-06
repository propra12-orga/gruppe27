package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.FileDialog;
import java.awt.Frame;

/**
 * 
 * @author Gruppe 27
 * @version 1.0
 * Klasse OpenFileDialog zum auswaehlen einer Datei
 *
 */

public class OpenFileDialog {

	/**
	 * �ffnet ein Fenster zum auswaehlen einer Datei und gibt dessen Pfad zur�ck.
	 * Zum Laden gedacht.
	 * 
	 * @param fenster
	 * @param title
	 *            Fenstertitel des FileDialog
	 * @param defDir
	 *            Der Pfad der beim �ffnen des FileDialogs automatisch angezeigt
	 *            wird. (Falls vorhanden)
	 * @param fileType
	 *            Dateityp, der angezeigt werden soll. "*" dient als Platzhalter
	 *            f�r beliebig viele Zeichen.
	 * @return Gibt den totalen Pfad der ausgew�hlten Datei zur�ck.
	 */
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
	 * �ffnet ein Fenster zum ausw�hlen einer Datei und gibt dessen Pfad zur�ck.
	 * Zum Speichern gedacht.
	 * 
	 * @param fenster
	 * @param title
	 *            Fenstertitel des FileDialog
	 * @param defDir
	 *            Der Pfad der beim �ffnen des FileDialogs automatisch angezeigt
	 *            wird. (Falls vorhanden)
	 * @param fileType
	 *            Dateityp, der angezeigt werden soll. "*" dient als Platzhalter
	 *            f�r beliebig viele Zeichen.
	 * @return
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