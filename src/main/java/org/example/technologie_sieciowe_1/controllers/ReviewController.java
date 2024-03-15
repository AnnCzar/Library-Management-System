package org.example.technologie_sieciowe_1.controllers;

import org.example.technologie_sieciowe_1.infrastructure.entity.RentalEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.ReviewRepository;
import org.example.technologie_sieciowe_1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController( ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/getAll")
    public @ResponseBody Iterable<ReviewEntity> getAllReview(){
        return reviewService.getAll();
    }
    @GetMapping("/getById")
    public @ResponseBody ReviewEntity getById(@PathVariable Long id) {
        return reviewService.getById(Math.toIntExact(id));
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody ReviewEntity add(@RequestBody ReviewEntity review){
        return reviewService.add(review);
    }
    @DeleteMapping("/delete")
    public void delete(@PathVariable Long id) {
        reviewService.delete(id);
    }


}
