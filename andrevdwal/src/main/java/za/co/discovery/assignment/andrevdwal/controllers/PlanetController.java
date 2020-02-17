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

import za.co.discovery.assignment.andrevdwal.dto.PlanetDto;
import za.co.discovery.assignment.andrevdwal.dto.ServiceResponseDto;
import za.co.discovery.assignment.andrevdwal.dto.ServiceResponseFactory;
import za.co.discovery.assignment.andrevdwal.mappers.Mapper;
import za.co.discovery.assignment.andrevdwal.repositories.Planet;
import za.co.discovery.assignment.andrevdwal.services.PlanetService;

@RestController
@RequestMapping(path = "api/planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@GetMapping("/{id}")
	public ServiceResponseDto<PlanetDto> get(@PathVariable("id") String id) {

		Planet result = planetService.getPlanet(id);
		PlanetDto dto = Mapper.map(result);
		
		if (dto == null)
			return new ServiceResponseFactory().notFound();
		return new ServiceResponseFactory().ok(dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {

		planetService.deletePlanet(id);
		// TODO: because the DB setup isn't 100% associated routes will not be automatically deleted
	}

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ServiceResponseDto<PlanetDto> create(@RequestBody PlanetDto planetDto) {

		Planet result = planetService.createPlanet(planetDto);
		PlanetDto respDto = Mapper.map(result);

		if (respDto == null)
			return new ServiceResponseFactory().badRequest("Cannot create");
		return new ServiceResponseFactory().ok(respDto);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public ServiceResponseDto<PlanetDto> update(@RequestBody PlanetDto planetDto) {

		Planet result = planetService.updatePlanet(planetDto);
		PlanetDto respDto = Mapper.map(result);

		if (respDto == null)
			return new ServiceResponseFactory().badRequest("Cannot update");
		return new ServiceResponseFactory().ok(respDto);
	}
}
