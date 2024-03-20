package org.example.technologie_sieciowe_1.service;


import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
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
                .map(book -> new GetBookDto(book.getId(),
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getPublisher(),
                        book.getPublishYear(),
                        book.getNumberCopy(),
                        book.getLoan(),
                        book.getBookDetails(),
                        book.getReview()))
                .collect(Collectors.toList());
    }


    public GetBookDto getById(Integer id){

        var bookEntity = bookRepository.findById(id).orElse(null);
        return new  GetBookDto(bookEntity.getId(),
                bookEntity.getIsbn(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getPublisher(),
                bookEntity.getPublishYear(),
                bookEntity.getNumberCopy(),
                bookEntity.getLoan(),
                bookEntity.getBookDetails(),
                bookEntity.getReview());
    }

    public CreateBookResponseDto add(CreateBookDto book) {
        var bookEntity = new BookEntity();
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setPublishYear(book.getPublishYear());
        bookEntity.setNumberCopy(book.getNumberCopy());
        bookEntity.setLoan(book.getLoan());
        bookEntity.setBookDetails(book.getBookDetails());
        bookEntity.setReview(book.getReview());

        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDto(newBook.getId(), newBook.getIsbn(), newBook.getAuthor(), newBook.getTitle(), newBook.getPublisher(), newBook.getPublishYear(), newBook.getNumberCopy(), newBook.getLoan(), newBook.getReview(), newBook.getBookDetails());
    }

    public void delete(Integer id) {
        if (!bookRepository.existsById(id)){
            throw new RuntimeException();
        }
        bookRepository.deleteById(id);
    }





}
