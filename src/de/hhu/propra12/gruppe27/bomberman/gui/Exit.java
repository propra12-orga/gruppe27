package de.hhu.propra12.gruppe27.bomberman.gui;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

public class Exit {
	private int posx, posy;
	private AbstractFeld Feld;

	public Exit(AbstractFeld Feld) {
		this.Feld = Feld;
		posx = Feld.getX();
		posy = Feld.getY();
	}

	public void doOnExit(Spielfeld owner) {
		owner.setVisible(false);
		owner.t.stop();
		System.out.println("Sie haben das ende erreicht");

		Startmenue start = new Startmenue();
		start.menueaufruf();

		owner.dispose();
		// framemenue.setVisible(false);
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

	public AbstractFeld getFeld() {
		return Feld;
	}
}
