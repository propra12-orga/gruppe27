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

import java.util.*;

/**

 * @version 1.0
 * @author Gruppe 27
 * Klasse AStar wird erzeugt
 */
public abstract class AStar<T>
{
		private class Path implements Comparable{
				public T point;
				public Double f;
				public Double g;
				public Path parent;
				
				/**
				 * path und T gleich null
				 */
				
				public Path(){
						parent = null;
						point = null;
						g = f = 0.0;
				}

				/**
				 * Konstruktor kopiert ein Objekt
				 * 
				 * @param p Das Path Objekt das geklont wird
				 */
				public Path(Path p){
						this();
						parent = p;
						g = p.g;
						f = p.f;
				}

				/**
				 * Vergleich zu einem Objekt
				 *
				 * @param o 
				 * Das zu vergleichende Objekt
				 * @return f
				 * Wert f wird zureckgegeben
				 */
				public int compareTo(Object o){
						Path p = (Path)o;
						return (int)(f - p.f);
				}

				/**
				 *
				 * @return point
				 * Der letzte Punkt wird zurueckgegeben (path)
				 */
				public T getPoint(){
						return point;
				}

				/**
				 * Parameter P wird uebergeben
				 * @param p
				 */
				
				public void setPoint(T p){
						point = p;
				}
		}

		/**
		 * Kontrollieren des Knotens
		 * @param node Der zu kontollierende Knoten
		 * @return wenn der Knoten ein Ziel ist
		 */
		protected abstract boolean isGoal(T node);

		/**
		 * @param from dem Knoten der verlassen wird
		 * @param to Knoten der erreicht wird
		 * @return Aufwand
		 */
		
		protected abstract Double g(T from, T to);

		/**
		 * Berechnung wie gross der Aufwand ist
		 * @param from dem Knoten der verlassen wird
		 * @param to Knoten der ereicht wird
		 * @return Aufwand
		 */
		protected abstract Double h(T from, T to);


		/**
		 * Nachfolger fuer den letzten Knoten
		 * @param node Knoten der erreicht werden soll
		 * @return Liste angestrebter Knoten
		 */
		protected abstract List<T> generateSuccessors(T node);


		private PriorityQueue<Path> paths;
		private HashMap<T, Double> mindists;
		private Double lastCost;
		private int expandedCounter;

		/**
		 * Pruefung wie oft ein Knoten erreicht wurde
		 *
		 * @return exoandCounter
		 * zaehlt wie oft ein Knoten erreicht wurde
		 */
		public int getExpandedCounter(){
				return expandedCounter;
		}

	/**
	 * @see oben
	 */
		public AStar(){
				paths = new PriorityQueue<Path>();
				mindists = new HashMap<T, Double>();
				expandedCounter = 0;
				lastCost = 0.0;
		}


		/**
		
		 * Definition des Aufwands: f(x) = g(x) + h(x).
		 * @param from dem Knoten der verlassen wird
		 * @param to Der Knoten der erreicht wird
		 * @return p.f Aufwand
		 */
		protected Double f(Path p, T from, T to){
				Double g =  g(from, to) + ((p.parent != null) ? p.parent.g : 0.0);
				Double h = h(from, to);

				p.g = g;
				p.f = g + h;

				return p.f;
		}

		/**
		 * Entwickeln Path
		 *
		 * @param path der zu entwickelnde path
		 */
		private void expand(Path path){
				T p = path.getPoint();
				Double min = mindists.get(path.getPoint());

				
				if(min == null || min.doubleValue() > path.f.doubleValue())
						mindists.put(path.getPoint(), path.f);
				else
						return;

				List<T> successors = generateSuccessors(p);

				for(T t : successors){
						Path newPath = new Path(path);
						newPath.setPoint(t);
						f(newPath, path.getPoint(), t);
						paths.offer(newPath);
				}

				expandedCounter++;
		}

		/**
		 * Aufwand den letzten Knoten zu erreichen
		 *
		 * @return lastCost Aufwand des gefundenen Knoten
		 */
		public Double getCost(){
				return lastCost;
		}


		/**
		 *Kuerzesten Weg zum Ziel finden
		 * @param start der anfangs Knoten
		 * @return A Liste der Knoten vom Anfang zum Ziel
		 */
		public List<T> compute(T start){
				try{
						Path root = new Path();
						root.setPoint(start);

					
						f(root, start, start);

						expand(root);

						for(;;){
								Path p = paths.poll();

								if(p == null){
										lastCost = Double.MAX_VALUE;
										return null;
								}

								T last = p.getPoint();

								lastCost = p.g;

								if(isGoal(last)){
										LinkedList<T> retPath = new LinkedList<T>();

										for(Path i = p; i != null; i = i.parent){
												retPath.addFirst(i.getPoint());
										}

										return retPath;
								}
								expand(p);
						}
				}
				catch(Exception e){
						e.printStackTrace();
				}
				return null;
						
		}
}
