package de.hhu.propra12.gruppe27.bomberman.core;

public class Bomb {
	int bombstr;
	Player owner;
	int time;
	int posx, posy;

	public Bomb(Player owner, int bombstr, int time) {
		this.bombstr = bombstr;
		this.owner = owner;
		this.time = time;
		posx = owner.getX();
		posy = owner.getY();
	}

	public void explode() {

	}
}
