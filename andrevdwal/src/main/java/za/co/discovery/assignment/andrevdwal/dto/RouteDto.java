package za.co.discovery.assignment.andrevdwal.dto;

public class RouteDto {

	private long id;
	private String originID;
	private String destinationID;
	private double distance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOriginID() {
		return originID;
	}

	public void setOriginID(String originID) {
		this.originID = originID;
	}

	public String getDestinationID() {
		return destinationID;
	}

	public void setDestinationID(String destinationID) {
		this.destinationID = destinationID;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
