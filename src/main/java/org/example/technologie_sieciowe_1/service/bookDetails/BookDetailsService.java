package org.example.technologie_sieciowe_1.service.bookDetails;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookDetailsResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
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
//    private  final GetBookDto getBookDto;
    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository, BookRepository bookRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
        this.bookRepository = bookRepository;
//        this.getBookDto = getBookDto;
    }

    public List<GetBookDetailsDto> getAll() {

        var bookDetails = bookDetailsRepository.findAll();

        return StreamSupport.stream(bookDetails.spliterator(),false)
                .map(bookDetails1 -> new GetBookDetailsDto(bookDetails1.getId(),
                        bookDetails1.getGenre(),
                        bookDetails1.getSummary(),
                        bookDetails1.getCoverImageURL()))
                .collect(Collectors.toList());
    }

    public GetBookDetailsDto getById(Integer id){
        var bookDetailsEntity=  bookDetailsRepository.findById(id).orElseThrow(() -> BookDetailsNotFoundException.create(id));

        return new GetBookDetailsDto(bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL());
    }

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

    public void delete(Integer id) {
        if (!bookDetailsRepository.existsById(id)){
            throw BookDetailsNotFoundException.create(id);
        }
        bookDetailsRepository.deleteById(id);
    }

    public GetBookDetailsDto getByBookId(Integer id) {
        var bookDetailsEntity=  bookDetailsRepository.findByBookId(id).orElseThrow(() -> BookDetailsNotFoundException.create(id));

        return new GetBookDetailsDto(bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL());
    }
}
