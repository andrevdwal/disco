package za.co.discovery.assignment.andrevdwal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.discovery.assignment.andrevdwal.dto.RouteDto;
import za.co.discovery.assignment.andrevdwal.dto.ServiceResponseDto;
import za.co.discovery.assignment.andrevdwal.dto.ServiceResponseFactory;
import za.co.discovery.assignment.andrevdwal.mappers.Mapper;
import za.co.discovery.assignment.andrevdwal.repositories.Route;
import za.co.discovery.assignment.andrevdwal.services.RouteService;

@RestController
@RequestMapping(path = "api/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@GetMapping("/{id}")
	public ServiceResponseDto<RouteDto> get(@PathVariable("id") long id) {

		Route result = routeService.getRoute(id);
		RouteDto dto = Mapper.map(result);

		if (dto == null)
			return new ServiceResponseFactory().notFound();
		return new ServiceResponseFactory().ok(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {

		routeService.deleteRoute(id);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ServiceResponseDto<RouteDto> create(@RequestBody RouteDto routeDto) {

		Route result = routeService.createRoute(routeDto);
		RouteDto respDto = Mapper.map(result);

		if (respDto == null)
			return new ServiceResponseFactory().badRequest("Cannot create");
		return new ServiceResponseFactory().ok(respDto);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ServiceResponseDto<RouteDto> update(@RequestBody RouteDto routeDto) {

		Route result = routeService.updateRoute(routeDto);
		RouteDto respDto = Mapper.map(result);

		if (respDto == null)
			return new ServiceResponseFactory().badRequest("Cannot update");
		return new ServiceResponseFactory().ok(respDto);
	}
}
