package de.hhu.propra12.gruppe27.bomberman.core;

/*    

 * A* algorithm implementation.
 * Copyright (C) 2007, 2009 Giuseppe Scrivano <gscrivano@gnu.org>

 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses/>.
 */

import java.util.LinkedList;
import java.util.List;

/*
 * Example.
 */
public class PathFinder extends AStar<PathFinder.Node> {
	private int[][] map;
	public static int zielx;
	public static int ziely;

	/**
	 * Setter-Methode, um die Ziel-Node des Pathfinder festzusetzen.
	 * 
	 * @param zielx
	 *            X-Koordinate
	 * @param ziely
	 *            Y-Koordinate
	 */

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

	public PathFinder(int[][] map) {
		this.map = map;
	}

	/**
	 * Setzt Ziel-Node fest.
	 */
	protected boolean isGoal(Node node) {
		return (node.x == zielx) && (node.y == ziely);
	}

	protected Double g(Node from, Node to) {

		if (from.x == to.x && from.y == to.y)
			return 0.0;

		if (map[to.y][to.x] == 1)
			return 1.0;

		return Double.MAX_VALUE;
	}

	protected Double h(Node from, Node to) {
		/* Use the Manhattan distance heuristic. */
		return new Double(Math.abs(map[0].length - 1 - to.x)
				+ Math.abs(map.length - 1 - to.y));
	}

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
	 * Prüft, ob das geladene Spielfeld gültig ist. Sollte der StartNode keine
	 * Verbindung zum ZielNode haben wird false ausgegeben. Am Ende der Prüfung
	 * wird in der Konsole einLog geschrieben.
	 * 
	 * @param map
	 *            Das Spielfeld als Integer-Array
	 * @param startx
	 *            Start-Node(X-Koord.)
	 * @param starty
	 *            Start-Node(Y-Koord.)
	 * @param zielx
	 *            Ziel-Node(X-Koord.)
	 * @param ziely
	 *            Ziel-Node(Y-Koord.)
	 * @return Gibt True zurück, wenn Konsistenz-Prüfung erfolgreich. False,
	 *         wenn die Map durchfällt.
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

}
