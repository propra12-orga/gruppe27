package de.hhu.propra12.gruppe27.bomberman.core;

public class Bomb {
	int bombstr;
	Player owner;
	int time;
	private int posx;
	private int posy;
	private boolean planted;

	public Bomb(Player owner, int bombstr, int time) {
		planted = true;
		this.bombstr = bombstr;
		this.owner = owner;
		this.time = time;
		posx = owner.getX();
		posy = owner.getY();

	}

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

	public void explode() {
		owner.bombcount++;
		owner.owner.getFeld(getPosx(), getPosy()).explodeOn(bombstr);
		planted = false;

	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	public boolean isPlanted() {
		return planted;
	}

}
