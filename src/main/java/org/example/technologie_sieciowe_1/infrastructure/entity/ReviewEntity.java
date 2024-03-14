package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ReviewID;

//    @ManyToOne
//    @JoinColumn(name = "bookId")
//    private BookEntity book;
//
//
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private UserEntity user;

    private Integer rate;

    private String comment;

    private Date reviewDate;

    public Integer getReviewID() {
        return ReviewID;
    }

    public void setReviewID(Integer reviewID) {
        ReviewID = reviewID;
    }

//    public BookEntity getBook() {
//        return book;
//    }
//
//    public void setBook(BookEntity book) {
//        this.book = book;
//    }
//
//    public UserEntity getUser() {
//        return user;
//    }
//
//    public void setUser(UserEntity user) {
//        this.user = user;
//    }

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

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Date getReviewDate() {
        return reviewDate;
    }
}
