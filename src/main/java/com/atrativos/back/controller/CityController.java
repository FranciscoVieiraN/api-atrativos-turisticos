package com.atrativos.back.controller;

import com.atrativos.back.domain.City;
import com.atrativos.back.dto.CityDTO;
import com.atrativos.back.exception.BadRequestException;
import com.atrativos.back.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/city")
@Log4j2
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    @Operation(summary = "List all cities pageable")
    public ResponseEntity<Page<City>> list(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(cityService.listAllPageable(pageable));
    }

    @GetMapping(path = "/all")
    @Operation(summary = "List all cities")
    public ResponseEntity<List<City>> listAll(){
        return ResponseEntity.ok(cityService.listAll());
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Find a specific city")
    public ResponseEntity<City> findById(@PathVariable Long id){
        return ResponseEntity.ok(cityService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/name")
    @Operation(summary = "Returns a list of cities that contain that name")
    public ResponseEntity<List<City>> findCityByName(@RequestParam String name){
        return ResponseEntity.ok(cityService.findByName(name));
    }

    @GetMapping(path = "/state")
    @Operation(summary = "Returns a list of cities that contain that state")
    public ResponseEntity<List<City>> findAllCitiesByState(@RequestParam String state){
        return ResponseEntity.ok(cityService.findAllCitiesByState(state));
    }

    @PostMapping
    @Operation(summary = "Create a city")
    public ResponseEntity<CityDTO> save(@RequestBody @Valid CityDTO cityDTO){
        try {
            return new ResponseEntity<>(cityService.save(cityDTO), HttpStatus.CREATED);
        }catch (BadRequestException erro){
            throw new ResponseStatusException(HttpStatus.CONFLICT, erro.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Remove a city")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    @Operation(summary = "Update a city")
    public ResponseEntity<City> replace(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        try {
            cityService.replace(id, cityDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (BadRequestException erro){
            throw new ResponseStatusException(HttpStatus.CONFLICT, erro.getMessage());
        }
    }
}
