package za.co.discovery.assignment.andrevdwal.distancecalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateRequestDto;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateResponseDto;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.repositories.PlanetRepository;
import za.co.discovery.assignment.andrevdwal.repositories.RouteRepository;

@Service
public class DistanceCalculatorService {

	@Autowired
	private PlanetRepository planetRepository;
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private DistanceCalculator calculator;

	public DistanceCalculateResponseDto calculate(DistanceCalculateRequestDto request) {

		// TODO: Database interaction is not optimal, duplicate queries

		Graph graph = getGraph();
		ShortestPath result = calculator.calculate(graph, request.getSourceKey(), request.getDestinationKey());
		if (result == null)
			return null;

		DistanceCalculateResponseDto resp = new DistanceCalculateResponseDto();

		String sourceKey = result.getSource() == null ? null : result.getSource().getKey();
		Optional<Planet> sourcePlanet = planetRepository.findById(sourceKey);

		String destinationKey = result.getDestination() == null ? null : result.getDestination().getKey();
		Optional<Planet> destinationPlanet = planetRepository.findById(destinationKey);

		if (!sourcePlanet.isPresent() || !destinationPlanet.isPresent())
			return null;

		resp.setSourceName(sourcePlanet.get().getName());
		resp.setDestinationName(destinationPlanet.get().getName());

		List<String> route = new ArrayList<String>();
		for (Node n : result.getNodes()) {
			Optional<Planet> hop = planetRepository.findById(n.getKey());
			route.add(hop.get().getName());
		}
		resp.setRoute(route);

		resp.setDistance(result.getDistance());

		return resp;
	}

	private Graph getGraph() {

		Graph graph = new Graph();

		routeRepository.findAll().forEach(route -> {

			if (!graph.containsKey(route.getOriginID()))
				graph.put(route.getOriginID(), new Node(route.getOriginID()));

			Node node = graph.get(route.getOriginID());
			node.addNeighbour(route.getDestinationID(), route.getDistance());
		});

		return graph;
	}
}
