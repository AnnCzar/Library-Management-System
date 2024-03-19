package org.example.technologie_sieciowe_1.controllers.dto.get;

import jakarta.persistence.*;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class GetBookDto {

    private Integer id;
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private Integer publishYear;
    private Integer numberCopy;
    private List<LoanEntity> loan;
    private BookDetailsEntity bookDetails;
    private List<ReviewEntity> review;

    public GetBookDto(Integer id, String isbn, String title, String author, String publisher, Integer publishYear, Integer numberCopy, List<LoanEntity> loan, BookDetailsEntity bookDetails, List<ReviewEntity> review) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.numberCopy = numberCopy;
        this.loan = loan;
        this.bookDetails = bookDetails;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getNumberCopy() {
        return numberCopy;
    }

    public void setNumberCopy(Integer numberCopy) {
        this.numberCopy = numberCopy;
    }

    public List<LoanEntity> getLoan() {
        return loan;
    }

    public void setLoan(List<LoanEntity> loan) {
        this.loan = loan;
    }

    public BookDetailsEntity getBookDetails() {
        return bookDetails;
    }

    public void setBookDetails(BookDetailsEntity bookDetails) {
        this.bookDetails = bookDetails;
    }

    public List<ReviewEntity> getReview() {
        return review;
    }

    public void setReview(List<ReviewEntity> review) {
        this.review = review;
    }
}
