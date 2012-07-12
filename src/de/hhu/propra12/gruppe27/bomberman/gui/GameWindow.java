package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.hhu.propra12.gruppe27.bomberman.core.Level;
import de.hhu.propra12.gruppe27.bomberman.core.SysEinst;

/**
*
* @author Gruppe 27
* @version 1.0 Klasse zur Erstellung des Spielfensters
*
*/
public class GameWindow extends JFrame {

SysEinst system = SysEinst.getSystem();
Spielfeld spielfeld = null;

private static final long serialVersionUID = 1L;

/**
*
* @param levelnr
*/
public GameWindow(int levelnr) {

/**
* Festlegung der Groesse (x und y Koordinate) Fenster wird mittig
* gesetzt this.system = SysEinst.getSystem();
*/

this.spielfeld = new Spielfeld(levelnr, this);
add(spielfeld);
int width = system.getfeldx() * 32;
int height = system.getfeldy() * 32 + 24;
setSize(width, height);
setVisible(true);
centerWindow(width, height); // Fenster mittig setzen
repaint();
}

/**
*
* @param levelnr
* @param isclient
* @param system
* @param spielfeld
* Konstruktor fuer Client im Netzwerk
*/

public GameWindow(Level level) {
System.out.println("Konstruktor client called");
Spielfeld spielfeld = new Spielfeld(level, this);
system.setfeldx(spielfeld.getsystem().getfeldx());
system.setfeldy(spielfeld.getsystem().getfeldy());

/**
* Festlegung der Groesse (x und y Koordinate) Fenster wird mittig
* gesetzt
*/

this.spielfeld = spielfeld;
spielfeld.setowner(this);
add(spielfeld);
int width = system.getfeldx() * 32;
int height = system.getfeldy() * 32 + 24;
setSize(width, height);
setVisible(true);
centerWindow(width, height);
repaint();
}

/**
*
* @return spielfeld Spielfeld wird an die Methode zurueck geliefert
*/

public Spielfeld getspielfeld() {
return spielfeld;
}

/**
*
* @param width
* @param height
* Die Hoehe und Breite des Spielfensters wird festgelegt
*/

public void centerWindow(int width, int height) {
Dimension screensize = java.awt.Toolkit.getDefaultToolkit()
.getScreenSize();
this.setLocation((screensize.width - width) / 2,
(screensize.height - height) / 2);
}

}