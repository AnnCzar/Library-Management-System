package org.example.technologie_sieciowe_1.controllers.dto.respone;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

public class CreateReviewResponseDto {
    @Schema(description = "ID of the review", example = "1")
    private Integer ReviewID;
    @Schema(description = "ID of the book", example = "1")
    private Integer book;
    @Schema(description = "ID of the user", example = "1")
    private Integer user;
    @Schema(description = "Rating", example = "4")
    private Integer rate;
    @Schema(description = "Comment", example = "Very interesting book")
    private String comment;
    @Schema(description = "Review date", example = "2024-04-14T12:00:00Z")
    private Date reviewDate;

    public CreateReviewResponseDto(Integer reviewID, Integer book, Integer user, Integer rate, String comment, Date reviewDate) {
        ReviewID = reviewID;
        this.book = book;
        this.user = user;
        this.rate = rate;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Integer getReviewID() {
        return ReviewID;
    }

    public void setReviewID(Integer reviewID) {
        ReviewID = reviewID;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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