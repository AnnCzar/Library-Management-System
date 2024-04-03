package org.example.technologie_sieciowe_1.controllers.dto.get;

import jakarta.persistence.*;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class GetLoanDto {

    private Integer loanid;
    private GetBookDto book;
    private GetUserDto user;
    private Date loanDate;
    private Date loanEndDate;
    private Date returnDate;

    public GetLoanDto(Integer loanid, GetBookDto book, GetUserDto user, Date loanDate, Date loanEndDate, Date returnDate) {
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

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserDto getUser() {
        return user;
    }

    public void setUser(GetUserDto user) {
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
