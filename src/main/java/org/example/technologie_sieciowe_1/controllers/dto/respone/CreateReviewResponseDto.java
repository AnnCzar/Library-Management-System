package org.example.technologie_sieciowe_1.controllers.dto.respone;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class CreateReviewResponseDto {

    private Integer ReviewID;
    private BookEntity book;
    private UserEntity user;
    private Integer rate;
    private String comment;
    private Date reviewDate;

    public CreateReviewResponseDto(Integer reviewID, BookEntity book, UserEntity user, Integer rate, String comment, Date reviewDate) {
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

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
