package za.co.discovery.assignment.andrevdwal.distancecalculator;

import java.util.HashMap;

public class Graph extends HashMap<String, Node> {

	private static final long serialVersionUID = 1L;

	public Node newNode(String key) {

		Node node = new Node(key);
		this.put(node.getKey(), node);
		return node;
	}
}
