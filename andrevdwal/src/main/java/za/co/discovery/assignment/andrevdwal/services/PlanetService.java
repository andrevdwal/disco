package za.co.discovery.assignment.andrevdwal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.repositories.PlanetRepository;
import za.co.discovery.assignment.andrevdwal.repositories.Route;
import za.co.discovery.assignment.andrevdwal.repositories.RouteRepository;

@Service
public class PlanetService {

	@Autowired
	private PlanetRepository planetRepository;
	@Autowired
	private RouteRepository routeRepository;

	public List<Planet> getAllPlanets() {

		List<Planet> planets = new ArrayList<Planet>();
		planetRepository.findAll().forEach(planets::add);
		return planets;
	}

	public List<Route> getAllRoutes() {

		List<Route> routes = new ArrayList<Route>();
		routeRepository.findAll().forEach(routes::add);
		return routes;
	}
}
