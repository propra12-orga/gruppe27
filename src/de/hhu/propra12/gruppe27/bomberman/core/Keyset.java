package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.event.KeyEvent;

public class Keyset {
	private int KeyUp, KeyDown, KeyLeft, KeyRight, KeyBomb;

	public Keyset(int KeyUp, int KeyDown, int KeyLeft, int KeyRight, int KeyBomb) {
		this.setKeyUp(KeyUp);
		this.setKeyLeft(KeyLeft);
		this.setKeyDown(KeyDown);
		this.setKeyRight(KeyRight);
		this.setKeyBomb(KeyBomb);

	}

	public Keyset(int presetnumber) {
		if (presetnumber == 1) {
			setKeyUp(KeyEvent.VK_W); // Standartbelegung 1 (WASD/SPACE)
			setKeyLeft(KeyEvent.VK_A);
			setKeyDown(KeyEvent.VK_S);
			setKeyRight(KeyEvent.VK_D);
			setKeyBomb(KeyEvent.VK_SPACE);
		} else if (presetnumber == 2) {
			setKeyUp(KeyEvent.VK_UP); // Standartbelegung 2
										// ("Pfeiltasten"/ENTER)
			setKeyLeft(KeyEvent.VK_LEFT);
			setKeyDown(KeyEvent.VK_DOWN);
			setKeyRight(KeyEvent.VK_RIGHT);
			setKeyBomb(KeyEvent.VK_ENTER);
		} else // if (presetnumber == 3)
		{// ---------------And so on...
			setKeyUp(KeyEvent.VK_I); // Standartbelegung 3 (IJKL/KOMMA",")
			setKeyLeft(KeyEvent.VK_J);
			setKeyDown(KeyEvent.VK_K);
			setKeyRight(KeyEvent.VK_L);
			setKeyBomb(KeyEvent.VK_COMMA);
		}

	}

	public int getKeyUp() {
		return KeyUp;
	}

	public void setKeyUp(int keyUp) {
		KeyUp = keyUp;
	}

	public int getKeyDown() {
		return KeyDown;
	}

	public void setKeyDown(int keyDown) {
		KeyDown = keyDown;
	}

	public int getKeyLeft() {
		return KeyLeft;
	}

	public void setKeyLeft(int keyLeft) {
		KeyLeft = keyLeft;
	}

	public int getKeyRight() {
		return KeyRight;
	}

	public void setKeyRight(int keyRight) {
		KeyRight = keyRight;
	}

	public int getKeyBomb() {
		return KeyBomb;
	}

	public void setKeyBomb(int keyBomb) {
		KeyBomb = keyBomb;
	}
}
