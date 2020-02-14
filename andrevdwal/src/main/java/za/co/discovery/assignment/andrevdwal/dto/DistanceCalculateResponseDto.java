package za.co.discovery.assignment.andrevdwal.dto;

import java.util.Collection;

public class DistanceCalculateResponseDto {

	private String sourceName;
	private String destinationName;
	private Collection<String> route;
	private double distance;

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public Collection<String> getRoute() {
		return route;
	}

	public void setRoute(Collection<String> route) {
		this.route = route;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
