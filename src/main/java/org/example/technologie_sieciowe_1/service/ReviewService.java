package org.example.technologie_sieciowe_1.service;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateReviewResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.BookRepository;
import org.example.technologie_sieciowe_1.infrastructure.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public List<GetReviewDto> getAll() {
        var reviews =  reviewRepository.findAll();
        return StreamSupport.stream(reviews.spliterator(), false)
                .map(review -> new GetReviewDto(review.getReviewID(),
                        review.getBook(),
                        review.getUser(),
                        review.getRate(),
                        review.getComment(),
                        review.getReviewDate()))
                .collect(Collectors.toList());
    }

    public GetReviewDto getById(Integer id){
        var reviewEntity =  reviewRepository.findById(id).orElse(null);
        assert reviewEntity != null;
        return new GetReviewDto(reviewEntity.getReviewID(),
                reviewEntity.getBook(),
                reviewEntity.getUser(),
                reviewEntity.getRate(),
                reviewEntity.getComment(),
                reviewEntity.getReviewDate());
    }

    public CreateReviewResponseDto add(CreateReviewDto review) {
        var reviewEntity = new ReviewEntity();
        reviewEntity.setBook(review.getBook());
        reviewEntity.setUser(review.getUser());
        reviewEntity.setRate(review.getRate());
        reviewEntity.setComment(review.getComment());
        reviewEntity.setReviewDate(review.getReviewDate());


        var newReview =  reviewRepository.save(reviewEntity);
        return new CreateReviewResponseDto(newReview.getReviewID(),
                newReview.getBook(),
                newReview.getUser(),
                newReview.getRate(),
                newReview.getComment(),
                newReview.getReviewDate()
                );
    }
    public void delete(Integer id) {
        if(!reviewRepository.existsById(id)){
            throw new RuntimeException();
        }
        reviewRepository.deleteById(id);
    }




}
