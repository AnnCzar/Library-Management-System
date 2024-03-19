package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public Iterable<ReviewEntity> getAll() {
        return reviewRepository.findAll();
    }

    public ReviewEntity getById(Integer id){
        return reviewRepository.findById(id).orElse(null);
    }

    public ReviewEntity add(ReviewEntity review) {
        return reviewRepository.save(review);
    }
    public void delete(Integer id) {
        if(!reviewRepository.existsById(id)){
            throw new RuntimeException();
        }
        reviewRepository.deleteById(id);
    }




}
