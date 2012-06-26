package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;

import java.util.ArrayList;


import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

/**
 * 
 * @author 
 * @version 1.0
 * Klasse für die Eigenschaften eines Item
 *
 */

public class ItemManager {
	
	private ArrayList<Item> ItemList;
	
	/**
	 * 
	 * @param owner
	 */

	public ItemManager(Spielfeld owner) {
		ItemList = new ArrayList<Item>();
	}

	// Funktion um zu testen ob player Items aufgenommen haben
	// Funktion um Items durch bombe zerstÃ¶ren zu lassen
	//
/**
 * Test, ob Item vom Player aufgenommen worden ist (Funktion)
 * Item kann durch Bombe zerstört werden (Funktion)
 * @return
 */
	
	public boolean isEmpty() {
		return ItemList.isEmpty();
	}
	
	/**
	 * Item wird aufgenommen
	 * @param i
	 */

	public void AddItem(Item i) {
		ItemList.add(i);
	}

	/**
	 * Größeneinstellung
	 * @param Feld
	 */
	
	public void hitItems(AbstractFeld Feld) {
		for (int i = 0; i < ItemList.size(); i++) {
			Item item = ItemList.get(i);
			if ((Feld == item.Feld))
				item.hit();

		}
	}
	
	/**
	 * 
	 * @param g
	 */

	public void paintItems(Graphics g) {
		for (int i = 0; i < ItemList.size(); i++) {
			ItemList.get(i).draw(g);
		}

	}
}
