package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import de.hhu.propra12.gruppe27.bomberman.gui.Spielfeld;

/**
 * 
 * @author
 * @version 1.0
 */

public class Bomb {
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

	
	public Bomb() {
		planted = false;

	}

	/**
	 * Radius der Bombe
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
				Next = Next.top();
			}
			Next = Feld.left();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				Next = Next.left();
			}
			Next = Feld.right();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				Next = Next.right();
			}
			Next = Feld.bottom();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--) {
				pg.hitThings(Next);
				Next = Next.bottom();
			}
		}
	}

	/**
	 * 
	 * @return planted
	 */

	public boolean isPlanted() {
		return planted;
	}

	/**
	 * @param g
	 */
	public void draw(Graphics g) {
		// g.drawOval(Feld.getX() * 32, Feld.getY() * 32, 32, 32);

		g.drawImage(image, Feld.getX() * 32, Feld.getY() * 32, 32, 32, pg);
	}

	
	public void hit() {
		explode(bombstr);

	}
}
