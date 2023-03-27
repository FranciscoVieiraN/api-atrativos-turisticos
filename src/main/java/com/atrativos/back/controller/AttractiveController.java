package com.atrativos.back.controller;

import com.atrativos.back.domain.Attractive;
import com.atrativos.back.dto.AttractiveDTO;
import com.atrativos.back.service.AttractiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/attractive")
@Log4j2
@RequiredArgsConstructor
public class AttractiveController {

    private final AttractiveService attractiveService;

    //localhost:8080/api/attractive
    @GetMapping
    public ResponseEntity<Page<Attractive>> list(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(attractiveService.listAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Attractive>> listAll() {
        return ResponseEntity.ok(attractiveService.listAllNonPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AttractiveDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(AttractiveDTO.toDTO(attractiveService.findByIdOrThrowBadRequestException(id)));
    }

    @GetMapping(path = "/name")
    public ResponseEntity<List<Attractive>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(attractiveService.findByName(name));
    }

    @GetMapping(path = "/address")
    public ResponseEntity<List<Attractive>> findByAddress(@RequestParam String address) {
        return ResponseEntity.ok(attractiveService.findByAddress(address));
    }

    @GetMapping(path = "/attractivebycity/{id}")
    public ResponseEntity<List<Attractive>> findAllAttractivesByCity(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(attractiveService.findAllAttractivesByCity(id));
    }

    @PostMapping
    public ResponseEntity<Attractive> save(@RequestBody AttractiveDTO attractiveDTO){
        return new ResponseEntity<>(attractiveService.save(attractiveDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        attractiveService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody AttractiveDTO attractiveDTO){
        attractiveService.replace(id, attractiveDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
