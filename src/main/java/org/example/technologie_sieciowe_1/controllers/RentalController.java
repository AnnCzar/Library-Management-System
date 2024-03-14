package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.RentalEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")

public class RentalController {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalController(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody RentalEntity addRental(@RequestBody RentalEntity rentalEntity){
        return rentalRepository.save(rentalEntity);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<RentalEntity> getAllRental(){
        return rentalRepository.findAll();
    }
//
//    @GetMapping("/getRentalById")
}
