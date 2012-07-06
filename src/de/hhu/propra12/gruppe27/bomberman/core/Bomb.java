package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;

import de.hhu.propra12.gruppe27.bomberman.audio.StdAudio;
import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author gruppe 27
 * @version 1.0 Klasse Bomb, Werte werden serialisierd, Bild der Bombe wird
 *          uebergeben
 */

public class Bomb implements Serializable {

	private static final long serialVersionUID = 1L;
	int bombstr;
	AbstractPlayer owner;
	Spielfeld pg;
	int time;
	public AbstractFeld Feld;
	private boolean planted;

	final Image image = Toolkit.getDefaultToolkit().getImage(
			"src/de/hhu/propra12/gruppe27/bomberman/graphics/Bombe2.gif");

	// final Image image = Toolkit.getDefaultToolkit().getImage("test.jpg");

	/**
	 * 
	 * @param owner
	 * @param bombstr
	 * @param time
	 *            Bombe gelegt ist true, Kunstruktoren werden aufgerufen
	 */

	public Bomb(AbstractPlayer owner, int bombstr, int time) {
		planted = true;
		this.bombstr = bombstr;
		this.owner = owner;
		this.time = time;
		this.pg = owner.owner;
		Feld = owner.owner.getFeld(owner.posx, owner.posy);
	}

	/**
	 * Wenn time=O, explodiert die Bombe
	 */

	public boolean check() {
		System.out.println(time);
		if (time > 0) {
			time--;
			return false;
		} else {
			explode(bombstr);
			return true;
		}

	}

	/**
	 * Bombe wurde nicht plaziert
	 */

	public Bomb() {
		planted = false;

	}

	/**
	 * Radius der Bombe, rechtes Feld
	 */

	public void explode(int radius) {
		if (planted) {
			owner.bombcount++;
			planted = false;
			time = 0;
			AbstractFeld Next;
			pg.hitThings(Feld);
			Next = Feld.top();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				// Level.Next.setXplode(true);
				Next = Next.top();
			}

			/**
			 * Linkes Feld
			 */

			Next = Feld.left();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				Next = Next.left();
			}

			/**
			 * Rechtes Feld
			 */

			Next = Feld.right();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				Next = Next.right();
			}

			/**
			 * Unteres Feld
			 */

			Next = Feld.bottom();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				Next = Next.bottom();
			}
		}

		/**
		 * Audio Datei wird gespielt wenn Bombe explodiert
		 */

		StdAudio.play("data/audio/bomb.wav");
	}

	/**
	 * Wenn Bombe geplanted, Werte werden an diese Stelle uebergeben
	 * 
	 * @return planted
	 */

	public boolean isPlanted() {
		return planted;
	}

	/**
	 * @param g
	 *            Feld wird erstellt
	 */

	public void draw(Graphics g) {
		// g.drawOval(Feld.getX() * 32, Feld.getY() * 32, 32, 32);

		g.drawImage(image, Feld.getX() * 32, Feld.getY() * 32, 32, 32, pg);
	}

	/**
	 * Bombe explodiert
	 */

	public void hit() {
		explode(bombstr);

	}
}
