package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.RentalEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.RentalRepository;
import org.example.technologie_sieciowe_1.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")

public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<RentalEntity> getAll(){
        return rentalService.getAll();
    }

    @GetMapping("/getById")
    public RentalEntity getById(@PathVariable Long id) {
        return rentalService.getById(Math.toIntExact(id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody RentalEntity add(@RequestBody RentalEntity rentalEntity){
        return rentalService.add(rentalEntity);
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable Long id) {
        rentalService.delete(id);
    }

}
