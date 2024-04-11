package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateLoanResponseDto;
import org.example.technologie_sieciowe_1.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/loan")
@PostAuthorize("isAuthenticated()")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

//    @GetMapping("/getAll")
//    @ResponseStatus(code = HttpStatus.OK)
//    public Iterable<GetLoanDto> getAll(){
//        return loanService.getAll();
//    }
    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<GetLoanDto> getAll(Principal principal){
        String username = principal.getName();
        return loanService.getAll(username);
    }

    @GetMapping("/getById")
    @ResponseStatus(code = HttpStatus.OK)
    public GetLoanDto getById(Integer id ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Nazwa użytkownika
//        String username = principal.getName();
        return loanService.getById(id, username);
    }

    @PostMapping("/add")
//    @Secured("ROLE_LIBRARIAN")
//    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateLoanResponseDto add(@RequestBody @Validated CreateLoanDto loanEntity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Nazwa użytkownika
        return loanService.add(loanEntity, username);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> delete(Integer id) {

        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/return")
    @PreAuthorize("hasRole('LIBRARIAN') or #loanDto.username == authentication.name")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Void> updateReturnDate(@RequestParam Integer loanId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Nazwa użytkownika
        loanService.updateReturnDate(loanId, username);
        return ResponseEntity.ok().build();
    }



}
