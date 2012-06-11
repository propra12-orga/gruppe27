package de.hhu.propra12.gruppe27.bomberman.core;

/**
 * 
 * @author 
 * @version 1.0
 */

public class Bomb {
	int bombstr;
	Player owner;
	int time;
	private int posx;
	private int posy;
	private boolean planted;
	
	/**
	 * 
	 * @param owner
	 * @param bombstr
	 * @param time
	 */

	public Bomb(Player owner, int bombstr, int time) {
		planted = true;
		this.bombstr = bombstr;
		this.owner = owner;
		this.time = time;
		posx = owner.getX();
		posy = owner.getY();

	}
	/**
	 * Wenn time=O, explodiert die Bombe
	 */

	public void check() {
		System.out.println(time);
		if (time > 0)
			time--;
		else {
			explode();
			System.out.println("BOOM!");
		}

	}
	

	public Bomb() {
		planted = false;

	}
	
	/**
	 * Bombenzeit wird bestimmt
	 */

	public void explode() {
		owner.bombcount++;
		owner.owner.getFeld(getPosx(), getPosy()).explodeOn(bombstr);
		planted = false;

	}
	
	/**
	 * 
	 * @return Position x
	 */

	public int getPosx() {
		return posx;
	}
	
	/**
	 * 
	 * @return Position y
	 */

	public int getPosy() {
		return posy;
	}

	/**
	 * 
	 * @return 
	 */
	
	public boolean isPlanted() {
		return planted;
	}

}
