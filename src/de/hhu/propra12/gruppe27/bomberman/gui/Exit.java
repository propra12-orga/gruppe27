package de.hhu.propra12.gruppe27.bomberman.gui;

public class Exit {
	private int posx, posy;

	public Exit(int x, int y) {
		setPosx(x);
		setPosy(y);
	}

	public void doOnExit(Spielfeld owner) {
		owner.t.stop();
		// TODO
	}

	public int getX() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getY() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
}
