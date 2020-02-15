package za.co.discovery.assignment.andrevdwal.mappers;

import za.co.discovery.assignment.andrevdwal.dto.PlanetDto;
import za.co.discovery.assignment.andrevdwal.dto.RouteDto;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.repositories.Route;

public class Mapper {

	private Mapper() {
	}

	public static PlanetDto map(Planet planet) {

		if (planet == null)
			return null;

		PlanetDto dto = new PlanetDto();
		dto.setId(planet.getId());
		dto.setName(planet.getName());
		return dto;
	}

	public static Planet map(PlanetDto dto) {

		if (dto == null)
			return null;

		Planet planet = new Planet();
		planet.setId(dto.getId());
		planet.setName(dto.getName());
		return planet;
	}

	public static RouteDto map(Route route) {

		if (route == null)
			return null;

		RouteDto dto = new RouteDto();
		dto.setDestinationID(route.getDestinationID());
		dto.setDistance(route.getDistance());
		dto.setId(route.getId());
		dto.setOriginID(route.getOriginID());
		return dto;
	}

	public static Route map(RouteDto dto) {

		if (dto == null)
			return null;

		Route route = new Route();
		route.setDestinationID(dto.getDestinationID());
		route.setDistance(dto.getDistance());
		route.setId(dto.getId());
		route.setOriginID(dto.getOriginID());
		return route;
	}

}
