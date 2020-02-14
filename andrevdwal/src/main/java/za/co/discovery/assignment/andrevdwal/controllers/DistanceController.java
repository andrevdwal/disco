package za.co.discovery.assignment.andrevdwal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.discovery.assignment.andrevdwal.distancecalculator.DistanceCalculatorService;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateRequestDto;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateResponseDto;

@RestController
@RequestMapping(path = "api/distance")
public class DistanceController {

	@Autowired
	private DistanceCalculatorService distanceCalculatorService;

	@PostMapping(path = "/calculate", consumes = "application/json", produces = "application/json")
	public DistanceCalculateResponseDto calculate(@RequestBody DistanceCalculateRequestDto calculateRequest)
			throws Exception {

		DistanceCalculateResponseDto resp = distanceCalculatorService.calculate(calculateRequest);

		if (resp == null)
			throw new BadRequestException("Route not found");

		return resp;
	}
}
