package org.example.technologie_sieciowe_1.controllers;



import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookResponseDto;
import org.example.technologie_sieciowe_1.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/book")
@PostAuthorize("isAuthenticated()")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController( BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.OK)
    public Iterable<GetBookDto> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/getById")
    @ResponseStatus(code = HttpStatus.OK)
    public GetBookDto getById( Integer id) {
        return bookService.getById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateBookResponseDto add(@RequestBody @Validated  CreateBookDto book) {
        return bookService.add(book);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> delete(Integer id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getByTitle")
    @ResponseStatus(code = HttpStatus.OK)
    public GetBookDto getByTitle(@RequestParam String title) {
        return bookService.getByTitle(title);
    }


}
