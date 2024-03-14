package org.example.technologie_sieciowe_1.controllers;



import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

//    @GetMapping("/getAll")
//    public @ResponseBody Iterable<BookEntity> getAllBooks(){
//        return bookRepository.findAll();
//        @RestController
//        @RequestMapping("/review")
//        public class ReviewController {
//            private final ReviewRepository reviewRepository;
//            @Autowired
//            public ReviewController(ReviewRepository reviewRepository) {
//                this.reviewRepository = reviewRepository;
//            }
//
//
//            @PostMapping("/add")
//            @ResponseStatus(code = HttpStatus.CREATED)
//            public @ResponseBody ReviewEntity andReview(@RequestBody ReviewEntity review){
//                return reviewRepository.save(review);
//            }
//
//            @GetMapping("/getAll")
//            public @ResponseBody Iterable<ReviewEntity> getAllReview(){
//                return reviewRepository.findAll();
//            }
//        }


    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody BookEntity addBook(@RequestBody BookEntity bookEntity){
        return bookRepository.save(bookEntity);

    }
}


//

//    @GetMapping("/getAll")
//    public @ResponseBody Iterable<BookEntity> getAllBooks(){
//        return bookRepository.findAll();
//    }
//}
