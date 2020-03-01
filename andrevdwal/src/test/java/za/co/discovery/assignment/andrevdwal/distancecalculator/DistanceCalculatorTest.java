package za.co.discovery.assignment.andrevdwal.distancecalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.discovery.assignment.andrevdwal.mockdata.MockGraphHelper;

@SpringBootTest
public class DistanceCalculatorTest {

	@Test
	void calculateOK() {

		Graph graph = MockGraphHelper.getGraph();

		DistanceCalculator calculator = new DjikstraDistanceCalculator();
		ShortestPath result = calculator.calculate(graph, "A", "F");

		Node source = result.getSource();
		assertNotNull(source);
		assertEquals("A", source.getKey());

		Node destination = result.getDestination();
		assertNotNull(destination);
		assertEquals("F", destination.getKey());

		assertEquals(9, result.getDistance());

		List<Node> nodes = result.getNodes();
		assertEquals(4, nodes.size());
		assertEquals("A", nodes.get(0).getKey());
		assertEquals("D", nodes.get(1).getKey());
		assertEquals("E", nodes.get(2).getKey());
		assertEquals("F", nodes.get(3).getKey());

	}

	@Test
	void calculateNoRoutePossible() {

		Graph graph = MockGraphHelper.getGraph();

		DistanceCalculator calculator = new DjikstraDistanceCalculator();
		ShortestPath result = calculator.calculate(graph, "A", "G");

		Node source = result.getSource();
		assertNotNull(source);
		assertEquals("G", source.getKey());

		Node destination = result.getDestination();
		assertNotNull(destination);
		assertEquals("G", destination.getKey());

		assertEquals(Double.MAX_VALUE, result.getDistance()); // INFINITY

		List<Node> nodes = result.getNodes();
		assertEquals(1, nodes.size());
		assertEquals("G", nodes.get(0).getKey());
	}
}
