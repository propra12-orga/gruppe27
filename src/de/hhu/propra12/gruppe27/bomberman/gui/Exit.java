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
	}u

	public int getPosx() {
		return posx;
	}

	public void setPosx(int posx) {
		this.posx = posx;
	}

	public int getPosy() {
		return posy;
	}

	public void setPosy(int posy) {
		this.posy = posy;
	}
}
