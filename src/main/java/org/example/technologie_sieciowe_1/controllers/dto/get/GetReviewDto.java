package org.example.technologie_sieciowe_1.controllers.dto.get;

import jakarta.persistence.*;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class GetReviewDto {

    private Integer ReviewID;
//    private BookEntity book;
//    private UserEntity user;
    private Integer rate;
    private String comment;
    private Date reviewDate;

    public GetReviewDto(Integer reviewID, Integer rate, String comment, Date reviewDate) {
        ReviewID = reviewID;
//        this.book = book;
//        this.user = user;
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
