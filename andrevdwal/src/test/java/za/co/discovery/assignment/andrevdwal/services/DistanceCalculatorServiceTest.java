package za.co.discovery.assignment.andrevdwal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import za.co.discovery.assignment.andrevdwal.distancecalculator.DistanceCalculator;
import za.co.discovery.assignment.andrevdwal.distancecalculator.Graph;
import za.co.discovery.assignment.andrevdwal.distancecalculator.Node;
import za.co.discovery.assignment.andrevdwal.distancecalculator.ShortestPath;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateRequestDto;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateResponseDto;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.repositories.Route;

@SpringBootTest
public class DistanceCalculatorServiceTest {

	@MockBean
	private PlanetService mockPlanetService;
	@MockBean
	private RouteService mockRouteService;
	@MockBean
	private DistanceCalculator mockCalculator;

	@BeforeEach
	public void init() {

		/*
		 * Scheme below sets up the conditions when a node A (Earth) and B (Moon) are a
		 * distance of 1 from each other. Anything else (C, D etc) does not exists.
		 */

		// Routes
		List<Route> routes = new ArrayList<Route>();
		Route routeAB = new Route();
		routeAB.setOriginID("A");
		routeAB.setDestinationID("B");
		routeAB.setDistance(1);
		routeAB.setId(1);
		routes.add(routeAB);

		when(mockRouteService.getAllRoutes()).thenReturn(routes);

		// Nodes for calculator's calculate response
		List<Node> nodes = new ArrayList<Node>();

		Node nodeA = new Node("A");
		nodeA.addNeighbour("B", 1);
		nodeA.addDistanceToSource("A", 0);
		nodes.add(nodeA);

		Node nodeB = new Node("B");
		nodeB.addDistanceToSource("A", 1);
		nodes.add(nodeB);

		ShortestPath shortestPathFound = new ShortestPath(nodes, 1);
		ShortestPath shortestPathNotFound = null;

		when(mockCalculator.calculate(any(Graph.class), matches("A"), matches("B"))).thenReturn(shortestPathFound);
		when(mockCalculator.calculate(any(Graph.class), matches("C"), matches("D"))).thenReturn(shortestPathNotFound);

		// Planets
		Planet planetA = new Planet();
		planetA.setId("A");
		planetA.setName("Earth");

		Planet planetB = new Planet();
		planetB.setId("B");
		planetB.setName("Moon");

		when(mockPlanetService.getPlanet("A")).thenReturn(planetA);
		when(mockPlanetService.getPlanet("B")).thenReturn(planetB);
	}

	@Test
	void calculateRouteFound() {

		DistanceCalculateRequestDto req = new DistanceCalculateRequestDto();
		req.setSourceKey("A");
		req.setDestinationKey("B");

		DistanceCalculatorService service = new DistanceCalculatorService(mockPlanetService, mockRouteService,
				mockCalculator);
		DistanceCalculateResponseDto resp = service.calculate(req);

		assertNotNull(resp);
		assertTrue(resp.getRoute().size() == 2);

		List<String> routes = new ArrayList<String>(resp.getRoute());
		assertEquals(routes.get(0), "Earth");
		assertEquals(routes.get(1), "Moon");

		assertEquals(resp.getDestinationName(), "Moon");
		assertEquals(resp.getDistance(), 1);

		assertEquals(resp.getSourceName(), "Earth");
	}

	@Test
	void calculateRouteNotFound() {

		DistanceCalculateRequestDto req = new DistanceCalculateRequestDto();
		req.setSourceKey("C");
		req.setDestinationKey("D");

		DistanceCalculatorService service = new DistanceCalculatorService(mockPlanetService, mockRouteService,
				mockCalculator);
		DistanceCalculateResponseDto resp = service.calculate(req);

		assertNull(resp);
	}
}
