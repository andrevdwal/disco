package za.co.discovery.assignment.andrevdwal.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import za.co.discovery.assignment.andrevdwal.dto.PlanetDto;
import za.co.discovery.assignment.andrevdwal.dto.RouteDto;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.repositories.Route;

@SpringBootTest
public class MapperTest {

	@Test
	void mapPlanetDtoOK() {
		
		String expectedId = "test-id";
		String expectedName = "test-name";
		
		PlanetDto dto = new PlanetDto();
		dto.setId(new String(expectedId));
		dto.setName(new String(expectedName));
		
		Planet result = Mapper.map(dto);
		
		assertNotNull(result);
		assertEquals(expectedId, result.getId());
		assertEquals(expectedName, result.getName());
	}
	
	@Test
	void mapPlanetDtoNull() {
		
		PlanetDto input = null;
		Planet result = Mapper.map(input);
		
		assertNull(result);
	}
	
	@Test
	void mapPlanetOK() {
		
		String expectedId = "test-id";
		String expectedName = "test-name";
		
		Planet planet = new Planet();
		planet.setId(new String(expectedId));
		planet.setName(new String(expectedName));
		
		PlanetDto result = Mapper.map(planet);
		
		assertNotNull(result);
		assertEquals(expectedId, result.getId());
		assertEquals(expectedName, result.getName());
	}
	
	@Test
	void mapPlanetNull() {
		
		Planet input = null;
		PlanetDto result = Mapper.map(input);
		
		assertNull(result);
	}
	
	@Test
	void mapRouteDtoOK() {
		
		long expectedID = 123;
		String expectedDestinationId = "test-destination-id";
		double expectedDistance = 44.56;
		String expectedOriginID = "test-origin-id";
		
		RouteDto dto = new RouteDto();
		dto.setDestinationID(expectedDestinationId);
		dto.setDistance(expectedDistance);
		dto.setId(expectedID);
		dto.setOriginID(expectedOriginID);
		
		Route result = Mapper.map(dto);
		
		assertNotNull(result);
		assertEquals(expectedID, result.getId());
		assertEquals(expectedDestinationId, result.getDestinationID());
		assertEquals(expectedDistance, result.getDistance());
		assertEquals(expectedOriginID, result.getOriginID());	
	}
	
	@Test
	void mapRouteDtoNull() {
		
		RouteDto dto = null;
		Route result = Mapper.map(dto);
		
		assertNull(result);
	}
	
	@Test
	void mapRouteOK() {
		
		long expectedID = 123;
		String expectedDestinationId = "test-destination-id";
		double expectedDistance = 44.56;
		String expectedOriginID = "test-origin-id";
		
		Route input = new Route();
		input.setDestinationID(expectedDestinationId);
		input.setDistance(expectedDistance);
		input.setId(expectedID);
		input.setOriginID(expectedOriginID);
		
		RouteDto result = Mapper.map(input);
		
		assertNotNull(result);
		assertEquals(expectedID, result.getId());
		assertEquals(expectedDestinationId, result.getDestinationID());
		assertEquals(expectedDistance, result.getDistance());
		assertEquals(expectedOriginID, result.getOriginID());	
	}
	
	@Test
	void mapRouteNull() {
		
		Route dto = null;
		RouteDto result = Mapper.map(dto);
		
		assertNull(result);
	}
}
