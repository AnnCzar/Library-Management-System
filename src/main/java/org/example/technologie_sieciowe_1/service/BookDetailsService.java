package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookDetailsRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDetailsService {

    private final BookDetailsRepository bookDetailsRepository;

    @Autowired
    public BookDetailsService(BookDetailsRepository bookDetailsRepository) {
        this.bookDetailsRepository = bookDetailsRepository;
    }

    public Iterable<BookDetailsEntity> getAll() {
        return bookDetailsRepository.findAll();
    }

    public BookDetailsEntity getById(Integer id){
        return bookDetailsRepository.findById(id).orElse(null);
    }

    public BookDetailsEntity add(BookDetailsEntity book) {
        return bookDetailsRepository.save(book);
    }

    public void delete(Integer id) {
        if (!bookDetailsRepository.existsById(id)){
            throw new RuntimeException();
        }
        bookDetailsRepository.deleteById(id);
    }

}
