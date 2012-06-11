package de.hhu.propra12.gruppe27.bomberman.core;



import java.awt.Color;

/**
 * 
 * @author 
 * @version 1.0
 *Klasse zur Erstellung des Spielfeldes
 */

public abstract class AbstractFeld {
	public static final int DIR_NULL = 0;
	public static final int DIR_TOP = 1;
	public static final int DIR_LEFT = 2;
	public static final int DIR_RIGHT = 3;
	public static final int DIR_BOTTOM = 4;
	
    // (?)sollte ein feld seine eigenen koordinaten kennen? vllt sinnvoll um
	// spÃ¤ter
	// einfacher nachbarfelder abzufragen(?) in dem fall zusÃ¤tzliche methoden
	// nord/sued/ost/west o.Ã¤.
	// Feld begehbar
	
	/**
	 * Ist das Feld begehbar...
	 */
	
	protected boolean frei;
	
	/**
	 * Ist das Feld zerstörbar...
	 */

	// Feld zerstÃ¶rbar
	protected boolean zerstoer;
	
	/**
	 * ...Kann die Bombe gelegt werden
	 */

	private boolean bombplanted;
	
	/**
	 * Bestimmung Feldgröße
	 */

	private int posx, posy;
	

	private Color c;


	private Level owner;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param owner
	 * Zuweisung der verschiedenen Größen
	 */

	public AbstractFeld(int x, int y, Level owner) {
		posx = x;
		posy = y;
		this.owner = owner;

	}
	
	/**
	 * 
	 * @return c
	 * 
	 */

	public abstract char toChar();// bis noch kein gui da ist...

	// public Image im;

	public Color getColor() {// spÃ¤ter ersetzen durch bildausgabe
		return c;
	}
	
	/**
	 * 
	 * @param str
	 * @param direction
	 * @return new Path
	 * Wenn Spielt tot dann Neubeginn, ansonsten Zerstörung der naheiegenden Blöcke
	 */

	public final AbstractFeld destroy(int str, int direction) {
		if (zerstoer) {
			System.out.println("dest");
			return new Path(posx, posy, owner);
		} else {
			if (str > 1) {
				switch (direction) {
				case DIR_TOP:
					owner.setFeld((owner.getFeld(posx - 1, posy).destroy(
							str - 1, DIR_RIGHT)), posx - 1, posy);
					break;
				case DIR_LEFT:
					owner.setFeld((owner.getFeld(posx, posy - 1).destroy(
							str - 1, DIR_RIGHT)), posx, posy - 1);
				case DIR_BOTTOM:
					owner.setFeld((owner.getFeld(posx + 1, posy).destroy(
							str - 1, DIR_RIGHT)), posx + 1, posy);
					break;
				case DIR_RIGHT:
					owner.setFeld((owner.getFeld(posx, posy + 1).destroy(
							str - 1, DIR_RIGHT)), posx, posy + 1);
					break;
				default:
					break;
				}
				
			}
			
			return (this);
		}
	}
	
	/**
	 * @param str
	 * ?
	 */

	public void explodeOn(int str) {
		owner.getFeld(posx - 1, posy).destroy(str, DIR_TOP);
		owner.getFeld(posx, posy - 1).destroy(str, DIR_LEFT);
		owner.getFeld(posx + 1, posy).destroy(str, DIR_BOTTOM);
		owner.getFeld(posx + 1, posy).destroy(str, DIR_RIGHT);
		this.destroy(0, DIR_NULL);

	}

	/**
	 * 
	 * @return frei
	 */
	
	public final boolean isFrei() {
		return frei;
	}

}
