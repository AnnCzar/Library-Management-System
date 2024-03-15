package org.example.technologie_sieciowe_1.controllers;



import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService) {
        this.bookService = bookService;

    }

    @GetMapping("/getAll")
    public Iterable<BookEntity> getAll() {
        return bookService.getAll();
    }
    @GetMapping("/getById")
    public BookEntity getById(@PathVariable Long id) {
        return bookService.getById(Math.toIntExact(id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookEntity add(@RequestBody BookEntity book) {
        return bookService.add(book);
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }


}
