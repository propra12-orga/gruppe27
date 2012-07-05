package de.hhu.propra12.gruppe27.bomberman.core;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/*
 * Das ist eine Test-Klasse! Kommt mir bloß nicht mit "So wird das aber nicht funktionieren!" -.-
 * Fehlen noch ein paar returns...
 * 
 * Die Funktion sollte sich rekursiv mit den nächsten drei feld-koordinaten aufrufen... die funktion geht nicht den letzten block wieder zurück... d.h. ->
 * wenn check("left"...) aufgerufen wird... wird check("right") ausgelassen...sonst geht er wieder auf die alte pos.
 * 
 * da fehlen noch startpositionen startx und starty...
 */

public class Consistency {

	public boolean check(String direction, Spielfeld owner, int posx, int posy,
			int zielx, int ziely) {
		if ((zielx == posx) && (ziely == posy)) {
			return true;
		} else {
			if (direction == "up") {
				if (owner.getFeld(posx, posy - 1).isFrei()
						|| owner.getFeld(posx, posy - 1).isZerstoer())
					check("left", owner, posx, posy--, zielx, ziely);
				check("right", owner, posx, posy--, zielx, ziely);
				check("up", owner, posx, posy--, zielx, ziely);
			} else if (direction == "left") {
				if (owner.getFeld(posx - 1, posy).isFrei()
						|| owner.getFeld(posx - 1, posy).isZerstoer())
					check("right", owner, posx--, posy, zielx, ziely);
				check("up", owner, posx--, posy, zielx, ziely);
				check("down", owner, posx--, posy, zielx, ziely);
			} else if (direction == "down") {
				if (owner.getFeld(posx, posy + 1).isFrei()
						|| owner.getFeld(posx, posy + 1).isZerstoer())
					check("right", owner, posx, posy++, zielx, ziely);
				check("left", owner, posx, posy++, zielx, ziely);
				check("down", owner, posx, posy++, zielx, ziely);
			} else if (direction == "right") {
				if (owner.getFeld(posx + 1, posy).isFrei())
					check("right", owner, posx++, posy, zielx, ziely);
				check("up", owner, posx++, posy, zielx, ziely);
				check("down", owner, posx++, posy, zielx, ziely);
			} else {
				System.out.println("Alle Wege sind blockiert!");
				return false;
			}
		}
	}
}
