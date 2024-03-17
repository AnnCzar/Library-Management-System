package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookDetailsRepository;
import org.example.technologie_sieciowe_1.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookDetails")
public class BookDetailsController {

    private final BookDetailsService bookDetailsService;
    @Autowired
    public BookDetailsController(BookDetailsService bookDetailsService) {
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping("/getAll")
    public Iterable<BookDetailsEntity> getAll() {
        return bookDetailsService.getAll();
    }
    @GetMapping("/getById")
    public BookDetailsEntity getById(Integer id) {
        return bookDetailsService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookDetailsEntity save(@RequestBody BookDetailsEntity bookDetails) {
        return bookDetailsService.add(bookDetails);
    }

    @DeleteMapping("/delete")
    public void delete(Integer id) {
        bookDetailsService.delete(id);
    }
}
