package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.RemoteException;

import de.hhu.propra12.gruppe27.bomberman.core.StartBomberman;
import de.hhu.propra12.gruppe27.bomberman.gui.menue.Startmenue;

public class Mainmeth {

	public static void main(String[] args) {
		
		Startmenue s_host = new Startmenue();
		s_host.menueaufruf();
		Startmenue s_client = new Startmenue();
		s_client.menueaufruf();
		
	}
}
