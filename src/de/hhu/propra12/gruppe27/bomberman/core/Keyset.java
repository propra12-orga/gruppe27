package de.hhu.propra12.gruppe27.bomberman.core;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * 
 * @author gruppe 27
 * @version 1.0 
 * Belegung der Tasten fuer Spieler, siehe KeyPlayer
 * 
 */

public class Keyset implements Serializable {

	private static final long serialVersionUID = 1L;
	public int KeyUp, KeyDown, KeyLeft, KeyRight, KeyBomb;
	SysEinst system = SysEinst.getSystem();

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
	 * Tastenbelegung fuer Spieler 1
	 * w = oben, s = unten, a= links, d= rechts, space = Bombe wird gelegt
	 */

	public Keyset(int presetnumber) {

		if (presetnumber == 1) {

			if (false == system.getboolClient() || false == system.getboolLAN()) {
				setKeyUp(KeyEvent.VK_W); 
				setKeyLeft(KeyEvent.VK_A);
				setKeyDown(KeyEvent.VK_S);
				setKeyRight(KeyEvent.VK_D);
				setKeyBomb(KeyEvent.VK_SPACE);
			}
			
			/**
			 * Tastebelegung fuer Spieler 2
			 * Pfeil rechts = rechts, Pfeil links = links, Pfeil unten = unten, Pfeil oben = oben, Enter = Bombe wird gelegt
			 */

		} else if (presetnumber == 2) {

			if (true == system.getboolClient() || false == system.getboolLAN()) {
				setKeyUp(KeyEvent.VK_UP); 
				setKeyLeft(KeyEvent.VK_LEFT);
				setKeyDown(KeyEvent.VK_DOWN);
				setKeyRight(KeyEvent.VK_RIGHT);
				setKeyBomb(KeyEvent.VK_ENTER);
			}
		}
		/**
		 * Lanplayer mit diesem Keyset kann nicht ueber Tasten gesteuert werden
		 */
		
		else if (presetnumber == -1) {

		}
		
		/**
		 * Tastenbelegung fuer Spieler 3
		 * i = Oben, l = rechts, j = links, k = unten, Komma = Bombe wird gelegt
		 */

		else // if (presetnumber == 3)
		{// ---------------And so on...
			setKeyUp(KeyEvent.VK_I); 
			setKeyLeft(KeyEvent.VK_J);
			setKeyDown(KeyEvent.VK_K);
			setKeyRight(KeyEvent.VK_L);
			setKeyBomb(KeyEvent.VK_COMMA);
		}

	}

	/**
	 * Werte fuer KEyup werden uebergeben
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
	 * Werte fuer KeyDown werden uebergeben
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
	 * Werte fuer KeyLeft werden uebergeben
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
	 * Werte für KeyRight werden uebergeben
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
	 * Werte für KeyBomb werden uebergeben
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
