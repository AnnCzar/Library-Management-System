package org.example.technologie_sieciowe_1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookResponseDto;
import org.example.technologie_sieciowe_1.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/book")
@PostAuthorize("isAuthenticated()")
@Tag(name = "Book", description = "Endpoints for managing books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all books")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "List of all books")
    public Iterable<GetBookDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/getById")
    @Operation(summary = "Get book by ID")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content)
    })
    public GetBookDto getById( Integer id) {
        return bookService.getById(id);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Book added")
    public @ResponseBody CreateBookResponseDto add(@RequestBody @Validated  CreateBookDto book) {
        return bookService.add(book);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a book by ID")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Removed"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content)
    })
    public ResponseEntity<Void> delete(Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getByTitle")
    @Operation(summary = "Get book by title")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content)
    })
    public GetBookDto getByTitle(@RequestParam String title) {
        return bookService.getByTitle(title);
    }

    @PostMapping("/update")
    @Operation(summary = "Update a book")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Book updated"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content)
    })
    public @ResponseBody CreateBookResponseDto update(@RequestBody @Validated  CreateBookDto book) {
        return bookService.update(book);
    }

}
