package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rental")

public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<LoanEntity> getAll(){
        return loanService.getAll();
    }

    @GetMapping("/getById")
    public LoanEntity getById(@PathVariable Long id) {
        return loanService.getById(Math.toIntExact(id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody LoanEntity add(@RequestBody LoanEntity loanEntity){
        return loanService.add(loanEntity);
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }

}
