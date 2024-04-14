package org.example.technologie_sieciowe_1.controllers.dto.get;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class GetReviewDto {
    @Schema(description = "ID of the review", example = "1")
    private Integer ReviewID;
    @Schema(description = "Book")
    private BookEntity book;
    @Schema(description = "User")
    private UserEntity user;
    @Schema(description = "Rating", example = "4")
    private Integer rate;
    @Schema(description = "Comment", example = "Very interesting book")
    private String comment;
    @Schema(description = "Review date", example = "2024-04-14T12:00:00Z")
    private Date reviewDate;

    public GetReviewDto(Integer reviewID, BookEntity book, Integer rate, String comment, Date reviewDate) {
        ReviewID = reviewID;
        this.book = book;
        this.rate = rate;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public GetReviewDto(Integer reviewID,UserEntity user, BookEntity book, Integer rate, String comment, Date reviewDate) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
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
