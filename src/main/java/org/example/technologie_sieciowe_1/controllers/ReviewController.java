package org.example.technologie_sieciowe_1.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateReviewResponseDto;
import org.example.technologie_sieciowe_1.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@PostAuthorize("isAuthenticated()")
@Tag(name = "Review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController( ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all reviews", description = "Retrieve all reviews based on user role")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public @ResponseBody Iterable<GetReviewDto> getAllReview(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username = authentication.getName();
        return reviewService.getAll(username);
    }

    @GetMapping("/getById")
    @Operation(summary = "Get review by ID", description = "Retrieve a review by its ID")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "The request succeeded"),
            @ApiResponse(responseCode = "404", description = "User or review not found", content = @Content)
    })
    public @ResponseBody GetReviewDto getById(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username = authentication.getName();
        return reviewService.getById(id, username);
    }

    @GetMapping("/getByTitleOrBookId")
    @Operation(summary = "Get reviews by title or book ID", description = "Retrieve reviews by title or book ID")
    public @ResponseBody List<GetReviewDto> getByTitleOrBookId(@RequestParam Integer id, @RequestParam(required = false) Object object) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username = authentication.getName();
        return reviewService.getByTitleOrBookId(id, username, object);
    }

    @PostMapping("/add")
    @Operation(summary = "Add a review", description = "Add a new review")
    @PreAuthorize("hasRole('READER')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Review added"),
            @ApiResponse(responseCode = "409", description = "Book not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public @ResponseBody CreateReviewResponseDto add(@RequestBody @Validated CreateReviewDto review){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username = authentication.getName();
        return reviewService.add(review, username);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a review", description = "Delete a review by ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Removed"),
            @ApiResponse(responseCode = "404", description =  "User or review not found", content = @Content)
    })
    public ResponseEntity<Void> delete(Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  username = authentication.getName();
        reviewService.delete(id, username);
        return ResponseEntity.noContent().build();
    }

}
