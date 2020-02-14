package za.co.discovery.assignment.andrevdwal.distancecalculator;

import java.util.HashMap;
import java.util.Map;

public class Node {

	private String key;
	private Map<String, Double> neighbourDistances;
	private double distanceToSource;
	private String distanceToSourceNodeKey;

	public Node(String key) {
		this.key = key;
		this.neighbourDistances = new HashMap<String, Double>();
		this.distanceToSource = Double.MAX_VALUE; // infinity
	}

	public void addNeighbour(String key, double distance) {
		this.neighbourDistances.put(key, distance);
	}

	public String getKey() {
		return key;
	}

	public Map<String, Double> getNeighbourDistances() {
		return neighbourDistances;
	}

	public void addDistanceToSource(String key, double distance) {

		this.distanceToSource = distance;
		this.distanceToSourceNodeKey = key;
	}

	public double getDistanceToSource() {
		return distanceToSource;
	}

	public String getDistanceToSourceNodeKey() {
		return distanceToSourceNodeKey;
	}
}