package de.hhu.propra12.gruppe27.bomberman.gui;

public class Exit {
	private int posx, posy;

	public Exit(int x, int y) {
		setPosx(x);
		setPosy(y);
	}

	public void doOnExit(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();
		System.out.println("Sie haben das ende erreicht");
	}

	public void doOnExit(Spielfeld2P owner) {
		owner.setVisible(false);
		owner.t.stop();
		System.out.println("Sie haben das ende erreicht");
	}

	// TODO

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