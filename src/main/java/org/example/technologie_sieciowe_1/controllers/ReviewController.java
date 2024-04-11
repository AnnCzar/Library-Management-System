package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.controllers.dto.create.CreateReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateReviewResponseDto;
import org.example.technologie_sieciowe_1.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@PostAuthorize("isAuthenticated()")
public class ReviewController {
    private final ReviewService reviewService;

    // dodac metode któa zwróci wszyskie recenzje dla danej ksiazki
    @Autowired
    public ReviewController( ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/getAll")
    public @ResponseBody Iterable<GetReviewDto> getAllReview(){
        return reviewService.getAll();
    }
    @GetMapping("/getById")
    public @ResponseBody GetReviewDto getById(Integer id) {
        return reviewService.getById(id);
    }

    @PostMapping("/add")
    // dodanie recencji do ksiakzi którą sie wyporzycyzło
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody CreateReviewResponseDto add(@RequestBody @Validated CreateReviewDto review){
        return reviewService.add(review);
    }
    @DeleteMapping("/delete")
//    @Secured("ROLE_LIBRARIAN")
    // dodanie zabezpiczenia ze mozna usunać tylko swoją opinie
    public ResponseEntity<Void> delete(Integer id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
