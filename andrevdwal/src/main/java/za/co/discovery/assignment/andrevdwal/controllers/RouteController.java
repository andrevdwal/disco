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
import za.co.discovery.assignment.andrevdwal.mappers.Mapper;
import za.co.discovery.assignment.andrevdwal.repositories.Route;
import za.co.discovery.assignment.andrevdwal.services.RouteService;

@RestController
@RequestMapping(path = "api/routes")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@GetMapping("/{id}")
	public RouteDto get(@PathVariable("id") long id) throws BadRequestException {

		Route result = routeService.getRoute(id);
		RouteDto dto = Mapper.map(result);

		if (dto == null)
			throw new BadRequestException("Not found");
		return dto;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") long id) {

		routeService.deleteRoute(id);
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public RouteDto create(@RequestBody RouteDto routeDto) throws BadRequestException {

		Route result = routeService.createRoute(routeDto);
		RouteDto respDto = Mapper.map(result);

		if (respDto == null)
			throw new BadRequestException("Cannot create");
		return respDto;
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public RouteDto update(@RequestBody RouteDto routeDto) throws BadRequestException {

		Route result = routeService.updateRoute(routeDto);
		RouteDto respDto = Mapper.map(result);

		if (respDto == null)
			throw new BadRequestException("Cannot update");
		return respDto;
	}
}
