// <<<<<<< HEAD
package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.Graphics;

public class Bomb {
	int bombstr;
	// Player owner;
	AbstractPlayer owner;
	int time;
	public AbstractFeld Feld;
	private boolean planted;

	// public Bomb(AbstractPlayer owner, int bombstr, int time) {
	public Bomb(AbstractPlayer owner, int bombstr, int time) {
		planted = true;
		this.bombstr = bombstr;
		this.owner = owner;
		this.time = time;
		Feld = owner.owner.getFeld(owner.posx, owner.posy);

	}

	// returns true if bomb explodes
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

	public void explode(int radius) {
		if (planted) {
			owner.bombcount++;
			planted = false;
			AbstractFeld Next;
			Next = Feld.top();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--)
				Next = Next.top();
			Next = Feld.left();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--)
				Next = Next.left();
			Next = Feld.right();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--)
				Next = Next.right();
			Next = Feld.bottom();
			for (int i = radius; (i > 0) && (Next.owner.DestroyFeld(Next)); i--)
				Next = Next.bottom();
		}
	}

	/*
	 * public int getPosx() { return Feld.; }
	 * 
	 * public int getPosy() { return posy; }
	 */

	public boolean isPlanted() {
		return planted;
	}

	public void draw(Graphics g) {
		g.drawOval(Feld.getX() * 32, Feld.getY() * 32, 32, 32);
	}

}

/*
 * ======= package de.hhu.propra12.gruppe27.bomberman.core;
 * 
 * public class Bomb { int bombstr; Player owner; int time; private int posx;
 * private int posy; private boolean planted;
 * 
 * public Bomb(Player owner, int bombstr, int time) { planted = true;
 * this.bombstr = bombstr; this.owner = owner; this.time = time; posx =
 * owner.getX(); posy = owner.getY();
 * 
 * }
 * 
 * public void check() { System.out.println(time); if (time > 0) time--; else {
 * explode(); System.out.println("BOOM!"); }
 * 
 * }
 * 
 * public Bomb() { planted = false;
 * 
 * }
 * 
 * public void explode() { owner.bombcount++; owner.owner.getFeld(getPosx(),
 * getPosy()).explodeOn(bombstr); planted = false;
 * 
 * }
 * 
 * public int getPosx() { return posx; }
 * 
 * public int getPosy() { return posy; }
 * 
 * public boolean isPlanted() { return planted; }
 * 
 * } >>>>>>> 990ed1b2d3afa24fa90ce7ab3a767be1bbfffa4f
 */
