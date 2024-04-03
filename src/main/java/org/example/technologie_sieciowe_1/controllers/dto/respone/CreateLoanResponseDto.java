package org.example.technologie_sieciowe_1.controllers.dto.respone;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class CreateLoanResponseDto {
    private Integer loanid;
    private Integer book;
    private Integer user;
    private Date loanDate;
    private Date loanEndDate;
    private Date returnDate;

    public CreateLoanResponseDto(Integer loanid, Integer book, Integer user, Date loanDate, Date loanEndDate, Date returnDate) {
        this.loanid = loanid;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.loanEndDate = loanEndDate;
        this.returnDate = returnDate;
    }

    public Integer getLoanid() {
        return loanid;
    }

    public void setLoanid(Integer loanid) {
        this.loanid = loanid;
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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
