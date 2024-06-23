package org.example.technologie_sieciowe_1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoanDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetLoansPageDto;
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
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/loan")
//@PostAuthorize("isAuthenticated()")
@Tag(name = "Loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


@GetMapping("/getAll")
public GetLoansPageDto getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }
    String username = authentication.getName();

    return loanService.getAll(username, 0, size);

}


    @GetMapping("/getById")
    @Operation(summary = "Get loan by ID")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    public GetLoanDto getById(Integer id ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return loanService.getById(id, username);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a loan")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Loan added"),
            @ApiResponse(responseCode = "409", description = "Book not found or no books available", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public @ResponseBody CreateLoanResponseDto add(@RequestBody @Validated CreateLoanDto loanEntity){
        return loanService.add(loanEntity);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a loan")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Removed"),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    public ResponseEntity<Void> delete(Integer id) {

        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/returnBook/{loanId}")
    @Operation(summary = "Update return date for a loan")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content)
    })
    public ResponseEntity<Void> updateReturnDate(@PathVariable Integer loanId) {
        loanService.updateReturnDate(loanId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getAllByUserId")
    @Operation(summary = "Get all loans for a user")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public List<GetLoanDto> getAllByUserId(@RequestParam Integer userId) {
        return loanService.getAllByUserId(userId);
    }
}
