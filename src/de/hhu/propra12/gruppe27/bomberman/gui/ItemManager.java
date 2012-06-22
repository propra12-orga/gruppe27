package de.hhu.propra12.gruppe27.bomberman.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import de.hhu.propra12.gruppe27.bomberman.core.AbstractFeld;

public class ItemManager {
	private ArrayList<Item> ItemList;

	public ItemManager(Spielfeld owner) {
		ItemList = new ArrayList<Item>();
	}

	// Funktion um zu testen ob player Items aufgenommen haben
	// Funktion um Items durch bombe zerstören zu lassen
	//

	public boolean isEmpty() {
		return ItemList.isEmpty();
	}

	public void AddItem(Item i) {
		ItemList.add(i);
	}

	public void hitItems(AbstractFeld Feld) {
		for (int i = 0; i < ItemList.size(); i++) {
			Item item = ItemList.get(i);
			if ((Feld == item.Feld))
				item.hit();

		}
	}

	public void paintItems(Graphics g) {
		for (int i = 0; i < ItemList.size(); i++) {
			ItemList.get(i).draw(g);
		}

	}
}
