package de.hhu.propra12.gruppe27.bomberman.netzwerk;

import java.rmi.RemoteException;

public class Mainmeth {

	public static void main(String[] args) {
		try {
			new Host();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			new Client();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
