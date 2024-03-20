package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateLoanResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")

public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<GetLoanDto> getAll(){
        return loanService.getAll();
    }

    @GetMapping("/getById")
    public GetLoanDto getById(Integer id) {
        return loanService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateLoanResponseDto add(@RequestBody CreateLoanDto loanEntity){
        return loanService.add(loanEntity);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(Integer id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
