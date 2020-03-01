package za.co.discovery.assignment.andrevdwal.mockdata;

import za.co.discovery.assignment.andrevdwal.distancecalculator.Graph;
import za.co.discovery.assignment.andrevdwal.distancecalculator.Node;

public class MockGraphHelper {

	public static Graph getGraph() {
		
		/* A -1- B -14- C -1- E -1- F
		 *   -5- D  -3-       E
		 * G -1- H  
		 * Shortest path A-F = A->D->E->F (9)
		 * No path between A-G
		 */
		
		Graph graph = new Graph();
		// nodes
		Node a = createNode(graph, "A");
		Node b = createNode(graph, "B");
		Node c = createNode(graph, "C");
		Node d = createNode(graph, "D");
		Node e = createNode(graph, "E");
		Node f = createNode(graph, "F");
		Node g = createNode(graph, "G");
		Node h = createNode(graph, "H");
		
		// neighbours/distancesy
		a.addNeighbour(b.getKey(), 1);
		b.addNeighbour(c.getKey(), 14);
		c.addNeighbour(e.getKey(), 1);
		e.addNeighbour(f.getKey(), 1);
		
		a.addNeighbour(d.getKey(), 5);
		d.addNeighbour(e.getKey(), 3);
//		e.addNeighbour(f.getKey(), 1);
		
		g.addNeighbour(h.getKey(), 1);

		return graph;
	}
	
	private static Node createNode(Graph graph, String key) {
		Node node = new Node(key);
		graph.put(key, node);
		return node;
	}
}
