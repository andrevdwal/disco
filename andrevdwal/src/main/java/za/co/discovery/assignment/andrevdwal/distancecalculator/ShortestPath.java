package za.co.discovery.assignment.andrevdwal.distancecalculator;

import java.util.List;

public class ShortestPath {

	private List<Node> nodes;
	private double distance;
	private Node source;
	private Node destination;

	public ShortestPath(List<Node> nodes, double distance) {
		this.nodes = nodes;
		this.distance = distance;
		this.source = nodes.get(0);
		this.destination = nodes.get(nodes.size() - 1);
	}

	public double getDistance() {
		return distance;
	}

	public Node getSource() {
		return source;
	}

	public Node getDestination() {
		return destination;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	@Override
	public String toString() {

		StringBuilder output = new StringBuilder();
		output.append(String.format("Destination between %s and %s is %.2f\n\n", this.source.getKey(),
				this.destination.getKey(), this.distance));

		for (int i = 0; i < nodes.size(); i++) {
			if (i == 0)
				output.append(String.format("\t%s", nodes.get(i).getKey()));
			else
				output.append(String.format(" -> %s", nodes.get(i).getKey()));
		}

		return output.toString();
	}
}
