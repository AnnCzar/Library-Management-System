package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookDetailsResponseDto;
import org.example.technologie_sieciowe_1.service.bookDetails.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookDetails")
@PostAuthorize("isAuthenticated()")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;
    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping("/getAll")
    public Iterable<GetBookDetailsDto> getAll() {
        return bookDetailsService.getAll();
    }
    @GetMapping("/getById")
    public GetBookDetailsDto getById(Integer id) {
        return bookDetailsService.getById(id);
    }

    @GetMapping("/getByBookId")
    public GetBookDetailsDto getByBookId(Integer id) {
        return bookDetailsService.getByBookId(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateBookDetailsResponseDto save(@RequestBody @Validated CreateBookDetailsDto bookDetails) {
        return bookDetailsService.add(bookDetails);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> delete(Integer id) {
        bookDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
