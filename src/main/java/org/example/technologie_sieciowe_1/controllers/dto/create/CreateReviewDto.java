package org.example.technologie_sieciowe_1.controllers.dto.create;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class CreateReviewDto {
    private BookEntity book;
    private UserEntity user;
    private Integer rate;
    private String comment;
    private Date reviewDate;

    public CreateReviewDto(BookEntity book, UserEntity user, Integer rate, String comment, Date reviewDate) {
        this.book = book;
        this.user = user;
        this.rate = rate;
        this.comment = comment;
        this.reviewDate = reviewDate;
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
