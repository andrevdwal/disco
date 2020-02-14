package za.co.discovery.assignment.andrevdwal.distancecalculator;

public interface DistanceCalculator {

	ShortestPath calculate(Graph graph, String sourceKey, String destinationKey);
}
