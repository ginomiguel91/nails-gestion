package com.sis.nailsgestion.controllers;

import com.sis.nailsgestion.models.ArrangementTypeDto;
import com.sis.nailsgestion.services.ArrangementTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api")
public class ArrangementTypeController {

    ArrangementTypeService arrangementTypeService;

    public ArrangementTypeController(ArrangementTypeService arrangementTypeService) {
        this.arrangementTypeService = arrangementTypeService;
    }

    @GetMapping("/arrangements")
    public ResponseEntity<List<ArrangementTypeDto>> getArrangements() {
        if (arrangementTypeService.getArrangements().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(arrangementTypeService.getArrangements(), HttpStatus.OK);
    }

    @GetMapping("/arrangements/{id}")
    public ResponseEntity<ArrangementTypeDto> getArrangementTypeById(@PathVariable Long id) {

        return new ResponseEntity<>(arrangementTypeService.getArrangementTypeById(id), HttpStatus.OK);
    }


    @PostMapping("/arrangements")
    public ResponseEntity<ArrangementTypeDto> createArrangementType(@RequestBody ArrangementTypeDto arrangementTypeDto) {

        return new ResponseEntity<>(arrangementTypeService.createArrangementType(arrangementTypeDto), HttpStatus.CREATED);
    }

    @PutMapping("/arrangements/{id}")
    public ResponseEntity<ArrangementTypeDto> updateArrangementType(@RequestBody ArrangementTypeDto arrangementTypeDto, @PathVariable Long id) {

        return new ResponseEntity<>(arrangementTypeService.updateArrangementType(arrangementTypeDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/arrangements/{id}")
    public ResponseEntity<Void> removeArrangementTypeById(@PathVariable Long id) {
        arrangementTypeService.removeArrangementTypeById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
