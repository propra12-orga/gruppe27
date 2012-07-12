package de.hhu.propra12.gruppe27.bomberman.core;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasse Pathfinder extends AStar Initialisierung von zielx und
 * ziely
 * Setter-Methode, um die Ziel-Knoten des Pathfinder festzusetzen.
 * @param zielx
 * X-Koordinate
 * @param ziely
 * Y-Koordinate
 * @author gruppe 27
 * @version 1.0 
 * 
 */

public class PathFinder extends AStar<PathFinder.Node> {
	private int[][] map;
	public static int zielx;
	public static int ziely;


	public static class Node {
		public int x;
		public int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "(" + x + ", " + y + ") ";
		}
	}
	
	/**
	 * Parameter map wird uebergeben
	 * @param map
	 */

	public PathFinder(int[][] map) {
		this.map = map;
	}

	/**
	 * Setzt Ziel-Knoten fest.
	 */
	protected boolean isGoal(Node node) {
		return (node.x == zielx) && (node.y == ziely);
	}

	/**
	 * Start Knoten zu erreichendem Knoten
	 */

	protected Double g(Node from, Node to) {

		if (from.x == to.x && from.y == to.y)
			return 0.0;

		if (map[to.y][to.x] == 1)
			return 1.0;

		return Double.MAX_VALUE;
	}
	

	protected Double h(Node from, Node to) {
		return new Double(Math.abs(map[0].length - 1 - to.x)
				+ Math.abs(map.length - 1 - to.y));
	}

	/**
	 * Initialisierung von Knoten x und y
	 */

	protected List<Node> generateSuccessors(Node node) {
		List<Node> ret = new LinkedList<Node>();
		int x = node.x;
		int y = node.y;
		if (y < map.length - 1 && map[y + 1][x] == 1)
			ret.add(new Node(x, y + 1));

		if (x < map[0].length - 1 && map[y][x + 1] == 1)
			ret.add(new Node(x + 1, y));

		return ret;
	}

	/**
	 * Prueft, ob das geladene Spielfeld g�ltig ist. Sollte der StartNode keine
	 * Verbindung zum ZielKnoten haben wird false ausgegeben. Am Ende der
	 * Pruefung wird in der Konsole einLog geschrieben.
	 * 
	 * @param map
	 *            Das Spielfeld als Integer-Array
	 * @param startx
	 *            Start-Knoten(X-Koord.)
	 * @param starty
	 *            Start-Knoten(Y-Koord.)
	 * @param zielx
	 *            Ziel-Knoten(X-Koord.)
	 * @param ziely
	 *            Ziel-Knoten(Y-Koord.)
	 * @return Gibt True zurueck, wenn Konsistenz-Pruefung erfolgreich. False,
	 *         wenn die Map durchfaellt.
	 */
	public static boolean check(int[][] map, int startx, int starty, int zielx,
			int ziely) {
		PathFinder.zielx = zielx;
		PathFinder.ziely = ziely;
		PathFinder pf = new PathFinder(map);

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++)
				System.out.print(map[i][j] + " ");
			System.out.println();
		}

		long begin = System.currentTimeMillis();

		List<Node> nodes = pf.compute(new PathFinder.Node(startx, starty));

		long end = System.currentTimeMillis();

		System.out.println("Time = " + (end - begin) + " ms");
		System.out.println("Expanded = " + pf.getExpandedCounter());
		System.out.println("Cost = " + pf.getCost());

		if (nodes == null) {
			System.out.println("No path");
			return false;
		} else {
			System.out.print("Path = ");
			for (Node n : nodes)
				System.out.print(n);
			System.out.println();
			return true;
		}
	}

	/**
	 * Konvertiert eine Map in eine Array mit 1 fuer zerst. Bloecke + freie Wege
	 * und 0 fuer unzerst. Bloecke...Wird benoetigt, um A*-PathFinder zu
	 * verwenden!
	 * 
	 * @param owner
	 *            Spielfeld
	 * @return Gibt ein Array zurueck, dass die Map als 1 und 0 enthaelt.
	 */
	public static int[][] convertMap(Level owner) {
		SysEinst system = SysEinst.getSystem();

		int ergebx;
		int ergeby;

		if (system.getbmllevel()) {
			ergebx = system.getfeldxbml();
			ergeby = system.getfeldybml();
		} else {
			ergebx = system.getfeldx();
			ergeby = system.getfeldy();
		}
		int ergebnis[][] = new int[ergebx][ergeby];

		for (int i = 0; i < ergebx; i++) {
			for (int j = 0; j < ergeby; j++) {
				if (owner.laxbr[i][j].isFrei()
						|| owner.laxbr[i][j].isZerstoer()) {
					ergebnis[i][j] = 1;
				} else {
					ergebnis[i][j] = 0;
				}
			}
		}

		return ergebnis;
	}

}
