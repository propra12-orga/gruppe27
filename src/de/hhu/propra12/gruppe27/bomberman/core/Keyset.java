package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.event.KeyEvent;

/**
 * 
 * @author 	
 * @version 1.0
 * Belegung der Tasten für Spieler 
 *
 */

public class Keyset {
	public int KeyUp, KeyDown, KeyLeft, KeyRight, KeyBomb;

	public Keyset(int KeyUp, int KeyDown, int KeyLeft, int KeyRight, int KeyBomb) {
		this.setKeyUp(KeyUp);
		this.setKeyLeft(KeyLeft);
		this.setKeyDown(KeyDown);
		this.setKeyRight(KeyRight);
		this.setKeyBomb(KeyBomb);

	}
	
	/**
	 * 
	 * @param presetnumber
	 * Tastenbelegung für 3 Spieler
	 */

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
	
	/**
	 * 
	 * @return Keyup
	 */

	public int getKeyUp() {
		return KeyUp;
	}
	
	/**
	 * 
	 * @param keyUp
	 */

	public void setKeyUp(int keyUp) {
		KeyUp = keyUp;
	}

	/**
	 * 
	 * @return KeyDown
	 */
	
	public int getKeyDown() {
		return KeyDown;
	}
	
	/**
	 * 
	 * @param keyDown
	 */

	public void setKeyDown(int keyDown) {
		KeyDown = keyDown;
	}
	
	/**
	 * 
	 * @return KeyLeft
	 */

	public int getKeyLeft() {
		return KeyLeft;
	}
	
	/**
	 * 
	 * @param keyLeft
	 */

	public void setKeyLeft(int keyLeft) {
		KeyLeft = keyLeft;
	}
	
	/**
	 * 
	 * @return KeyRight
	 */

	public int getKeyRight() {
		return KeyRight;
	}
	
	/**
	 * 
	 * @param keyRight
	 */

	public void setKeyRight(int keyRight) {
		KeyRight = keyRight;
	}
	
	/**
	 * 
	 * @return KeyBomb
	 */

	public int getKeyBomb() {
		return KeyBomb;
	}
	
	/**
	 * 
	 * @param keyBomb
	 */

	public void setKeyBomb(int keyBomb) {
		KeyBomb = keyBomb;
	}
}
