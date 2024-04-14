package org.example.technologie_sieciowe_1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookDetailsResponseDto;
import org.example.technologie_sieciowe_1.service.bookDetails.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookDetails")
@PostAuthorize("isAuthenticated()")
@Tag(name = "Book details")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;
    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all book details", description = "Retrieve a list of all book details")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "The request succeeded")
    public Iterable<GetBookDetailsDto> getAll() {
        return bookDetailsService.getAll();
    }

    @GetMapping("/getById")
    @Operation(summary = "Get book details by ID", description = "Retrieve book details by its ID")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "Book details not found", content = @Content)
    })
    public GetBookDetailsDto getById(Integer id) {
        return bookDetailsService.getById(id);
    }

    @GetMapping("/getByBookId")
    @Operation(summary = "Get book details by Book ID", description = "Retrieve book details by the associated Book's ID")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "Book details not found", content = @Content)
    })
    public GetBookDetailsDto getByBookId(Integer id) {
        return bookDetailsService.getByBookId(id);
    }

    @PostMapping("/add")
    @Operation(summary = "Add book details", description = "Add new book details")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Book details added"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content)
    })
    public @ResponseBody CreateBookDetailsResponseDto save(@RequestBody @Validated CreateBookDetailsDto bookDetails) {
        return bookDetailsService.add(bookDetails);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete book details", description = "Delete book details by its ID")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Removed"),
            @ApiResponse(responseCode = "404", description = "Book details not found", content = @Content)
    })
    public ResponseEntity<Void> delete(Integer id) {
        bookDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
