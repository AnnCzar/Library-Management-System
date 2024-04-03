package org.example.technologie_sieciowe_1.controllers.dto.create;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;

import java.util.Date;

public class CreateLoanDto {

    @NotNull
    private Integer book;
    @NotNull
    private Integer user;
    @NotNull
    private Date loanDate;
    @NotNull
    private Date loanEndDate;
    private Date returnDate;

    public CreateLoanDto(Integer book, Integer user, Date loanDate, Date loanEndDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.loanDate = new Date(System.currentTimeMillis());
        this.loanEndDate = loanEndDate;
        this.returnDate = returnDate;
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
