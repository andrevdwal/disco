package za.co.discovery.assignment.andrevdwal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.discovery.assignment.andrevdwal.distancecalculator.DistanceCalculator;
import za.co.discovery.assignment.andrevdwal.distancecalculator.Graph;
import za.co.discovery.assignment.andrevdwal.distancecalculator.Node;
import za.co.discovery.assignment.andrevdwal.distancecalculator.ShortestPath;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateRequestDto;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateResponseDto;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;

@Service
public class DistanceCalculatorService {

	private PlanetService planetService;
	private RouteService routeService;
	private DistanceCalculator calculator;
	
	@Autowired
	public DistanceCalculatorService(PlanetService planetService, RouteService routeService, DistanceCalculator calculator) {
		this.planetService = planetService;
		this.routeService = routeService;
		this.calculator = calculator;
	}

	public DistanceCalculateResponseDto calculate(DistanceCalculateRequestDto request) {

		// TODO: Database interaction is not optimal, duplicate queries

		Graph graph = getGraph();
		ShortestPath result = calculator.calculate(graph, request.getSourceKey(), request.getDestinationKey());
		if (result == null)
			return null;

		DistanceCalculateResponseDto resp = new DistanceCalculateResponseDto();

		String sourceKey = result.getSource() == null ? null : result.getSource().getKey();
		Planet sourcePlanet = planetService.getPlanet(sourceKey);

		String destinationKey = result.getDestination() == null ? null : result.getDestination().getKey();
		Planet destinationPlanet = planetService.getPlanet(destinationKey);

		if (sourcePlanet == null || destinationPlanet == null)
			return null;

		resp.setSourceName(sourcePlanet.getName());
		resp.setDestinationName(destinationPlanet.getName());

		List<String> route = new ArrayList<String>();
		for (Node n : result.getNodes()) {
			Planet hop = planetService.getPlanet(n.getKey());
			route.add(hop.getName());
		}
		resp.setRoute(route);

		resp.setDistance(result.getDistance());

		return resp;
	}

	private Graph getGraph() {

		Graph graph = new Graph();

		
		
		routeService.getAllRoutes().forEach(route -> {

			if (!graph.containsKey(route.getOriginID()))
				graph.put(route.getOriginID(), new Node(route.getOriginID()));

			Node node = graph.get(route.getOriginID());
			node.addNeighbour(route.getDestinationID(), route.getDistance());
		});

		return graph;
	}
}
