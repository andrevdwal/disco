package za.co.discovery.assignment.andrevdwal.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateRequestDto;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateResponseDto;
import za.co.discovery.assignment.andrevdwal.services.DistanceCalculatorService;

@SpringBootTest
@AutoConfigureMockMvc
public class DistanceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DistanceCalculatorService distanceCalculatorService;

	@Test
	void calculateOK() throws Exception {

		DistanceCalculateResponseDto respDto = new DistanceCalculateResponseDto();
		respDto.setDestinationName("B");
		respDto.setDistance(1);
		respDto.setSourceName("A");

		String expectedBody = "{\"success\":true,\"status\":200,\"description\":null,\"payload\":{\"sourceName\":\"A\",\"destinationName\":\"B\",\"route\":null,\"distance\":1.0}}";

		when(distanceCalculatorService.calculate(any(DistanceCalculateRequestDto.class))).thenReturn(respDto);

		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/distance/calculate")
				.content("{}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
		
		boolean expected = result.getResponse().getContentAsString().equals(expectedBody);
		assertTrue(expected);
	}
	
	@Test
	void calculate400() throws Exception {

		when(distanceCalculatorService.calculate(any(DistanceCalculateRequestDto.class))).thenReturn(null);

		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/distance/calculate")
				.content("{}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
		
		boolean expected = result.getResponse().getContentAsString().contains("Route not found");
		assertTrue(expected);
	}
}
