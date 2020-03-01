package za.co.discovery.assignment.andrevdwal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.discovery.assignment.andrevdwal.dto.PlanetDto;
import za.co.discovery.assignment.andrevdwal.mappers.Mapper;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.repositories.PlanetRepository;
import za.co.discovery.assignment.andrevdwal.repositories.Route;
import za.co.discovery.assignment.andrevdwal.repositories.RouteRepository;

@Service
public class PlanetService {

	private PlanetRepository planetRepository;
	private RouteRepository routeRepository;
	
	@Autowired
	public PlanetService(PlanetRepository planetRepository, RouteRepository routeRepository) {
		this.planetRepository = planetRepository;
		this.routeRepository = routeRepository;
	}

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
	
	
	public Planet getPlanet(String id) {
		
		Optional<Planet> result = planetRepository.findById(id);
		return result.isPresent() ? result.get() : null;
	}
	
	public void deletePlanet(String id) {
		
		planetRepository.deleteById(id);
	}
	
	public Planet createPlanet(PlanetDto dto) {
		
		Planet planet = Mapper.map(dto);
		return planetRepository.save(planet);
	}
	
	public Planet updatePlanet(PlanetDto dto) {
		
		Planet planet = Mapper.map(dto);
		if(planet == null)
			return null;
		
		Planet existing = getPlanet(planet.getId());
		if(existing == null)
			return null;
		
		return planetRepository.save(planet);
	}
}
