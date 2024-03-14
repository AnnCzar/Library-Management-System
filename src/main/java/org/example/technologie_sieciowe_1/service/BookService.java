package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.controllers.dto.CreateBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.CreateBookResponeDto;
import org.example.technologie_sieciowe_1.controllers.dto.GetBookDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class BookService{
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll(){
        var books = bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false)
                .map(bookEntity -> new GetBookDto(bookEntity.getId(), bookEntity.getIsbn(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getPublisher(), bookEntity.getPublishYear(), bookEntity.getNumberCopy()))
                .collect(Collectors.toList());
    }


//    public List<GetBookDto> getAll(){
//        var books = bookRepository.findAll();
//        return books.stream().map((book) -> new GetBookDto(book.getId(), book.getIsbn(),book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),book.getNumberCopy())).collect(Collectors.toList());
//    }


    public GetBookDto getById(Integer id){
        var book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("BookEntity not found"));
        return new GetBookDto(book.getId(), book.getIsbn(),book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublishYear(),book.getNumberCopy());
        
    }


    public CreateBookResponeDto add(CreateBookDto book1){
        var book = new BookEntity();
        book.setAuthor(book1.getAuthor());
        book.setIsbn(book1.getIsbn());
        book.setPublisher(book1.getPublisher());
        book.setTitle(book1.getTitle());
        book.setNumberCopy(book1.getNumberCopy());
        book.setPublishYear(book1.getPublishYear());
        var newBook = bookRepository.save(book);
        return new CreateBookResponeDto(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getPublishYear(), newBook.getNumberCopy());
    }



}
