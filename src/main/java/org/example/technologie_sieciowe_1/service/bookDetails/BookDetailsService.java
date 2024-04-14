package org.example.technologie_sieciowe_1.service.bookDetails;

import io.swagger.v3.oas.annotations.Operation;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookDetailsResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookDetailsRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.service.book.exceptions.BookNotFoundException;
import org.example.technologie_sieciowe_1.service.bookDetails.exceptions.BookDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
    }

    @Operation(summary = "Get all book details", description = "Retrieve a list of all book details")
    public List<GetBookDetailsDto> getAll() {

        var bookDetails = bookDetailsRepository.findAll();

        return StreamSupport.stream(bookDetails.spliterator(),false)
                .map(bookDetails1 -> new GetBookDetailsDto(bookDetails1.getId(),
                        bookDetails1.getGenre(),
                        bookDetails1.getSummary(),
                        bookDetails1.getCoverImageURL()))
                .collect(Collectors.toList());
    }
    @Operation(summary = "Get book details by ID", description = "Retrieve book details by its ID")
    public GetBookDetailsDto getById(Integer id){
        var bookDetailsEntity=  bookDetailsRepository.findById(id).orElseThrow(() -> BookDetailsNotFoundException.create(id));

        return new GetBookDetailsDto(bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL());
    }
    @Operation(summary = "Add book details", description = "Add new book details")
    public CreateBookDetailsResponseDto add(CreateBookDetailsDto bookDetails) {
        BookEntity book = bookRepository.findById(bookDetails.getBook()).orElseThrow(BookNotFoundException::create);

        var bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setBook(book);
        bookDetailsEntity.setGenre(bookDetails.getGenre());
        bookDetailsEntity.setSummary(bookDetails.getSummary());
        bookDetailsEntity.setSummary((bookDetails.getCoverImageURL()));
        var newBookDetails = bookDetailsRepository.save(bookDetailsEntity);
        return new CreateBookDetailsResponseDto(
                newBookDetails.getGenre(),
                newBookDetails.getSummary(),
                newBookDetails.getCoverImageURL(),
                newBookDetails.getBook().getId());
    }
    @Operation(summary = "Delete book details", description = "Delete book details by its ID")
    public void delete(Integer id) {
        if (!bookDetailsRepository.existsById(id)){
            throw BookDetailsNotFoundException.create(id);
        }
        bookDetailsRepository.deleteById(id);
    }
    @Operation(summary = "Get book details by Book ID", description = "Retrieve book details by the associated Book's ID")
    public GetBookDetailsDto getByBookId(Integer id) {
        var bookDetailsEntity=  bookDetailsRepository.findByBookId(id).orElseThrow(() -> BookDetailsNotFoundException.create(id));

        return new GetBookDetailsDto(bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL());
    }
}
