package org.example.technologie_sieciowe_1.service.book;


import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.service.book.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.StreamSupport;


@Service
public class BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll() {

        var books =  bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false)
                .map(this::mapBook).collect(Collectors.toList());
    }

    public GetBookDto getById(Integer id){

        var bookEntity = bookRepository.findById(id).orElseThrow(BookNotFoundException::create);
        return mapBook(bookEntity);
    }

    public CreateBookResponseDto add(CreateBookDto book) {
        var bookEntity = new BookEntity();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setNumberCopy(book.getNumberCopy());

        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDto(
                newBook.getId(),
                newBook.getIsbn(),
                newBook.getAuthor(),
                newBook.getTitle(),
                newBook.getPublisher(),
                newBook.getPublishYear(),
                newBook.getNumberCopy());
    }

    public void delete(Integer id) {
        if (!bookRepository.existsById(id)){
            throw BookNotFoundException.create();
        }
        bookRepository.deleteById(id);
    }


    public GetBookDto mapBook(BookEntity bookEntity){
//        GetReviewDto reviewDto = new GetReviewDto() // add review to getting book
//        GetBookDetailsDto getBookDetailsDto = new GetBookDetailsDto(
//                bookEntity.getBookDetails().getId(),
//                bookEntity.getBookDetails().getGenre(),
//                bookEntity.getBookDetails().getSummary(),
//                bookEntity.getBookDetails().getCoverImageURL()
//        );
        return new GetBookDto(bookEntity.getId(),
                bookEntity.getIsbn(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getPublishYear(),
                bookEntity.getNumberCopy());

//        return new GetBookDto(bookEntity.getId(),
//                bookEntity.getIsbn(),
//                bookEntity.getTitle(),
//                bookEntity.getAuthor(),
//                bookEntity.getPublisher(),
//                bookEntity.getPublishYear(),
//                bookEntity.getNumberCopy(),
//                bookEntity.getBookDetails());
    }
}
