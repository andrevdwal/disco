package za.co.discovery.assignment.andrevdwal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateRequestDto;
import za.co.discovery.assignment.andrevdwal.dto.DistanceCalculateResponseDto;
import za.co.discovery.assignment.andrevdwal.dto.ServiceResponseDto;
import za.co.discovery.assignment.andrevdwal.dto.ServiceResponseFactory;
import za.co.discovery.assignment.andrevdwal.services.DistanceCalculatorService;

@RestController
@RequestMapping(path = "api/distance")
public class DistanceController {

	@Autowired
	private DistanceCalculatorService distanceCalculatorService;

	@PostMapping(path = "/calculate", consumes = "application/json", produces = "application/json")
	public ServiceResponseDto<DistanceCalculateResponseDto> calculate(
			@RequestBody DistanceCalculateRequestDto calculateRequest) throws Exception {

		DistanceCalculateResponseDto resp = distanceCalculatorService.calculate(calculateRequest);

		if (resp == null)
			// "badRequest" to set the status code in the response object but still treated as a 200. This is
			// not a CRUD operation so not point in returning a 400 - the request was valid.
			return new ServiceResponseFactory().badRequest("Route not found");

		return new ServiceResponseFactory().ok(resp);
	}
}
