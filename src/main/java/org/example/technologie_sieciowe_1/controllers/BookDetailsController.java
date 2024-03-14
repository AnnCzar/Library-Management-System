package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookDetails")
public class BookDetailsController {

    private final BookDetailsRepository bookDetailsRepository;
    @Autowired
    public BookDetailsController(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookDetailsEntity addBookDetails(@RequestBody BookDetailsEntity bookDetailsEntity){
        return bookDetailsRepository.save(bookDetailsEntity);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<BookDetailsEntity> getAllBookDetails(){
        return bookDetailsRepository.findAll();
    }
}
