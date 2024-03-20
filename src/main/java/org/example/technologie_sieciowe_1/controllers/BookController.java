package org.example.technologie_sieciowe_1.controllers;



import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Iterable<GetBookDto> getAll() {
        return bookService.getAll();
    }
    @GetMapping("/getById")
    public GetBookDto getById( Integer id) {
        return bookService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateBookResponseDto add(@RequestBody CreateBookDto book) {
        return bookService.add(book);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
