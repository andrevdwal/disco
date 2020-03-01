package za.co.discovery.assignment.andrevdwal.distancecalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
class DjikstraDistanceCalculator implements DistanceCalculator {

	@Override
	public ShortestPath calculate(Graph graph, String sourceKey, String destinationKey) {

		// TODO: A design shortcoming means we are changing the graph instance which do
		// not belong to us
		// We can either clone or rethink the design
		determineShortestDistancesToSource(graph, sourceKey);
		ShortestPath result = determineShortestPath(graph, sourceKey, destinationKey);

		return result;
	}

	private static void determineShortestDistancesToSource(Graph graph, String sourceKey) {

		// get the source node instance and default the distance to itself to 0
		Node sourceNode = graph.get(sourceKey);
		if(sourceNode == null)
			return;
		sourceNode.addDistanceToSource(sourceNode.getKey(), 0);

		// add it to the list of nodes to visit next
		Set<Node> nextToVisit = new HashSet<Node>();
		nextToVisit.add(sourceNode);

		// while there are nodes to visit
		while (!nextToVisit.isEmpty()) {

			// get the node to visit
			Node nodeWeAreVisiting = getNextToVisit(nextToVisit);

			for (Entry<String, Double> neighbourEntry : nodeWeAreVisiting.getNeighbourDistances().entrySet()) {

				Node neighbourNode = graph.get(neighbourEntry.getKey());
				if (neighbourNode == null)
					continue;

				double distanceToNeighbour = neighbourEntry.getValue();

				// if the new calculated distance to source for the neighbour is less than what
				// is currently set, override
				double potentialShortestDistanceToSource = nodeWeAreVisiting.getDistanceToSource()
						+ distanceToNeighbour;
				if (neighbourNode.getDistanceToSource() > potentialShortestDistanceToSource)
					neighbourNode.addDistanceToSource(nodeWeAreVisiting.getKey(), potentialShortestDistanceToSource);

				// add this neighbour to list of nodes to visit
				nextToVisit.add(neighbourNode);
			}

			// we are done with this node
			nextToVisit.remove(nodeWeAreVisiting);
		}
	}

	private static Node getNextToVisit(Set<Node> pool) {

		// find the node with the shortest distance to the source
		Node next = null;

		for (Node current : pool) {
			if (next == null || current.getDistanceToSource() < next.getDistanceToSource())
				next = current;
		}

		return next;
	}

	private static ShortestPath determineShortestPath(Graph graph, String source, String dest) {

		List<Node> nodes = new ArrayList<Node>();

		Node sourceNode = graph.get(source);
		if (sourceNode == null)
			return null;

		Node destNode = graph.get(dest);
		if (destNode == null)
			return null;

		Node currentNode = destNode;
		double distanceToSource = currentNode.getDistanceToSource();

		while (currentNode != null) {
			nodes.add(currentNode);

			Node nextNode = graph.get(currentNode.getDistanceToSourceNodeKey());
			if (nextNode == sourceNode) {
				nodes.add(nextNode);
				break;
			}
			currentNode = nextNode;
		}

		Collections.reverse(nodes);
		return new ShortestPath(nodes, distanceToSource);
	}

}
