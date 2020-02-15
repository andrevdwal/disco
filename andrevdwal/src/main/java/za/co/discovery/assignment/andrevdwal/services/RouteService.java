package za.co.discovery.assignment.andrevdwal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.discovery.assignment.andrevdwal.dto.RouteDto;
import za.co.discovery.assignment.andrevdwal.mappers.Mapper;
import za.co.discovery.assignment.andrevdwal.repositories.Route;
import za.co.discovery.assignment.andrevdwal.repositories.RouteRepository;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;

	public List<Route> getAllRoutes() {

		List<Route> routes = new ArrayList<Route>();
		routeRepository.findAll().forEach(routes::add);
		return routes;
	}

	public Route getRoute(long id) {

		Optional<Route> result = routeRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}

	public void deleteRoute(long id) {

		routeRepository.deleteById(id);
	}

	public Route createRoute(RouteDto dto) {

		Route route = Mapper.map(dto);
		return routeRepository.save(route);
	}

	public Route updateRoute(RouteDto dto) {

		Route route = Mapper.map(dto);
		if (route == null)
			return null;

		Route existing = getRoute(route.getId());
		if (existing == null)
			return null;

		return routeRepository.save(route);
	}
}
