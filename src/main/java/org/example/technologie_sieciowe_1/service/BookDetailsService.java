package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateBookDetailsResponseDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateUserResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookDetailsRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public List<GetBookDetailsDto> getAll() {

        var bookDetails = bookDetailsRepository.findAll();

        return StreamSupport.stream(bookDetails.spliterator(),false)
                .map(bookDetails1 -> new GetBookDetailsDto(bookDetails1.getId(),
                        bookDetails1.getGenre(),
                        bookDetails1.getSummary(),
                        bookDetails1.getCoverImageURL(),
                        bookDetails1.getBook()))
                .collect(Collectors.toList());
    }

    public GetBookDetailsDto getById(Integer id){
        var bookDetailsEntity=  bookDetailsRepository.findById(id).orElse(null);
        assert bookDetailsEntity != null;
        return new GetBookDetailsDto(bookDetailsEntity.getId(),
                bookDetailsEntity.getGenre(),
                bookDetailsEntity.getSummary(),
                bookDetailsEntity.getCoverImageURL(),
                bookDetailsEntity.getBook());
    }

    public CreateBookDetailsResponseDto add(CreateBookDetailsDto book) {
        var bookDetailsEntity = new BookDetailsEntity();
        bookDetailsEntity.setBook(book.getBook());
        bookDetailsEntity.setGenre(book.getGenre());
        bookDetailsEntity.setSummary(book.getSummary());
        bookDetailsEntity.setSummary((book.getCoverImageURL()));
        var newBookDetails = bookDetailsRepository.save(bookDetailsEntity);
        return new CreateBookDetailsResponseDto(newBookDetails.getId(), newBookDetails.getGenre(), newBookDetails.getSummary(), newBookDetails.getCoverImageURL(), newBookDetails.getBook());


    }

    public void delete(Integer id) {
        if (!bookDetailsRepository.existsById(id)){
            throw new RuntimeException();
        }
        bookDetailsRepository.deleteById(id);
    }

}
