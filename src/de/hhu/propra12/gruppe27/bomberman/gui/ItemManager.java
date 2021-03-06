package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

/**
 * Klasse fuer die Eigenschaften eines Item
 * @author gruppe 27
 * @version 1.0 
 * 
 */

public class ItemManager {

	private ArrayList<Item> ItemList;

	public ItemManager(Spielfeld owner) {
		ItemList = new ArrayList<Item>();
	}

	/**
	 * Test, ob Item vom Player aufgenommen worden ist (Funktion) Item kann
	 * durch Bombe zerstoert werden (Funktion)
	 * 
	 * @return ItemList
	 */

	public boolean isEmpty() {
		return ItemList.isEmpty();
	}

	/**
	 * Item wird aufgenommen
	 * Parameter i wird uebergebn
	 * @param i
	 */

	public void AddItem(Item i) {
		ItemList.add(i);
	}

	/**
	 * Groesseneinstellung
	 * Parameter Feld wird uerbergeben
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
	 * Items werden gezeichnet
	 * Parameter g wird uebergeben
	 * @param g
	 */

	public void paintItems(Graphics g) {
		for (int i = 0; i < ItemList.size(); i++) {
			ItemList.get(i).draw(g);
		}

	}
}
