package org.example.technologie_sieciowe_1.service;


import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

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

    public Iterable<BookEntity> getAll() {
        return bookRepository.findAll();
    }


    public BookEntity getById(Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    public BookEntity add(BookEntity book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(Math.toIntExact(id));
    }





}
