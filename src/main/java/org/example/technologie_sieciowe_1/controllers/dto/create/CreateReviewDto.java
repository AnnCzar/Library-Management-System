package org.example.technologie_sieciowe_1.controllers.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
@Schema(description = "DTO for creating a review")
public class CreateReviewDto {
    @NotNull
    @Schema(description = "ID of the book", required = true, example = "1")
    private Integer book;
    @Schema(description = "Rating", example = "4")
    private Integer rate;
    @Schema(description = "Comment", example = "Very interesting book")
    private String comment;
    @Schema(description = "Review date", example = "2024-04-14T12:00:00Z")
    private Date reviewDate;

    public CreateReviewDto(Integer book, Integer rate, String comment, Date reviewDate) {
        this.book = book;
        this.rate = rate;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }


    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}