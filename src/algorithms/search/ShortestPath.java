package algorithms.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import core.components.Edge;
import core.components.Pair;
import edu.uci.ics.jung.graph.Graph;

/**
 * An implementation of Dijkstra's single source shortest path
 * search algorithm.
 * 
 * @author Michael Nowicki
 *
 * @param <V> The vertex class
 * @param <E> The edge class, must extend core.Edge
 */
public class ShortestPath<V, E extends Edge> {

	/**
	 * Searches the graph and searches for the shortest path
	 * from a given start node to the destination. Returns {@code null}
	 * if there are no vertices, edges, or path to the goal,
	 * otherwise a list with the order of vertices on the shortest
	 * path.
	 * 
	 * @param graph The graph to search
	 * @param source The vertex to start the search from
	 * @param destination The goal vertex
	 * 
	 * @return A list with the order of vertices on the shortest path,
	 * 		   null if no path exists in the graph.
	 */
	public List<V> search(Graph<V,E> graph, V source, V destination) {
		
		// Check if it is even possible to find a path, return null
		// if the graph has no vertices or edges
		if (graph.getVertexCount() == 0  ) {
			System.out.println("No nodes in the graph, " +
							   "no shortest path can be found");
			return null;
		} else if (graph.getEdgeCount() == 0) {
			System.out.println("No edges in graph, no path " + 
							   "can be found");
			return null;
		}
		
		// Keep record of distance to each vertex, map each vertex
		// in the graph to it's distance
		HashMap<V, Number> distanceTable = new HashMap<>();
		
		// Unvisited node queue, uses a pair <Vertex, Double> and ordered
		// by the distance to the vertex
		PriorityQueue<Pair<V, Number> > queue = new PriorityQueue<>(new QueueComparator());
		
		// Map of nodes on the path, parents is value, key is child
		HashMap<V, V> parent = new HashMap<>();
		
		Number maxValue;
		E edgeTest = graph.getEdges().iterator().next();

		// This is so ugly, I hate Java Numbers
		int numberType = 0;
		if (edgeTest.getWeight() instanceof Integer) {
			numberType = 1;
		} else if (edgeTest.getWeight() instanceof Double) {
			numberType = 2;
		}
		// Place each vertex in the map, initialize distances and put
		// the pairings into the queue.
		for (V vertex : graph.getVertices()) {
			if (numberType == 1) {
				maxValue = Integer.MAX_VALUE;
				if (vertex.equals(source)) {
					distanceTable.put(source, 0);
					queue.add(new Pair<>(vertex, 0));
				} else {
					distanceTable.put(vertex, Integer.MAX_VALUE);
					queue.add(new Pair<>(vertex, maxValue));
				}
			} else if (numberType == 2) {
				maxValue = Double.MAX_VALUE;
				if (vertex.equals(source)) {
					distanceTable.put(source, 0.0);
					queue.add(new Pair<>(vertex, 0.0));
				} else {
					distanceTable.put(vertex, Double.MAX_VALUE);
					queue.add(new Pair<>(vertex, maxValue));
				}
			}
		}
		
		parent.put(source, null);

		while (!queue.isEmpty()) {
			
			Pair<V, Number> topPair = queue.remove();
			V vertex = topPair.getLeft();
			
			// Goal test, return the list of nodes on the path
			// if we reach the destination
			if (vertex.equals(destination)) {
				return tracePath(parent, destination);
			}
			
			Collection<V> neighbours = graph.getNeighbors(vertex);
			
			for (V neighbour : neighbours) {
				
				E edge = graph.findEdge(vertex, neighbour);
				assert(edge != null);
								
				// Test for type of number used for weight, work accordingly
				// Did I mention I hate the Java Number class.
				if (numberType == 1) {

					Integer alternateDistance = (Integer)edge.getWeight();

					if (alternateDistance < (Integer)distanceTable.get(neighbour)) {
						distanceTable.put(neighbour, alternateDistance);
						parent.put(neighbour, vertex);
						queue.add(new Pair<>(neighbour, alternateDistance));
					}
				} else if (numberType == 2) {
					Double alternateDistance = (Double)edge.getWeight();

					if (alternateDistance < (Double)distanceTable.get(neighbour)) {
						distanceTable.put(neighbour, alternateDistance);
						parent.put(neighbour, vertex);
						queue.add(new Pair<>(neighbour, alternateDistance));
					}
				}
			}
		}
		// Exhausted all possible paths from source, could not find a path
		// to the goal.
		return null;
	}

	/**
	 * Traces back through the hashmap to find all the nodes
	 * in order from the destination node.
	 * 
	 * @param parentMap The map of child, parents
	 * @param destination The goal vertex
	 * @return A list of the path from source to destination
	 */
	private List<V> tracePath(HashMap<V, V> parentMap, V destination) {
		
		List<V> path = new ArrayList<>();

		V ancestor = parentMap.get(destination);
		path.add(destination);
		
		while (ancestor != null) {
			path.add(ancestor);
			ancestor = parentMap.get(ancestor);
		}
		
		Collections.reverse(path);
		
		return path;
	
	}

	/**
	 * Private comparator used to order the pairings in the queue
	 *
	 * Generic numbers suck for comparisons..
	 *
	 * @author Michael Nowicki
	 *
	 */
	private class QueueComparator implements Comparator<Pair<V, Number> > {

		@Override
		public int compare(Pair<V, Number> pairOne, Pair<V, Number> pairTwo) {

			Number leftSide = pairOne.getRight();
			Number rightSide = pairTwo.getRight();
					
			if (leftSide.getClass() == Double.class) {
				Double left = (Double)leftSide;
				Double right = (Double)rightSide;
			
				if (left > right) {
					return 1;
				} else if (right > left) {
					return -1;
				}
			
			} else if (leftSide.getClass() == Integer.class) {
				Integer left = (Integer)leftSide;
				Integer right = (Integer)rightSide;
			
				if (left > right) {
					return 1;
				} else if (right > left) {
					return -1;
				}
			
			}
			
			return 0;
		}
	}

}
