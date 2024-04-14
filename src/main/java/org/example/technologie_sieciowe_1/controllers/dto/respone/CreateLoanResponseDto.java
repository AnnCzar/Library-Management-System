package org.example.technologie_sieciowe_1.controllers.dto.respone;


import io.swagger.v3.oas.annotations.media.Schema;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;

import java.util.Date;

@Schema(description = "DTO for response when creating a loan")
public class CreateLoanResponseDto {
    @Schema(description = "ID of the loan", example = "789")
    private Integer loanid;
    @Schema(description = "ID of the book", example = "123")
    private Integer book;

    @Schema(description = "ID of the user", example = "123")
    private Integer user;

    @Schema(description = "Date of loan", example = "2024-04-14")
    private Date loanDate;

    @Schema(description = "End date of loan", example = "2024-04-30")
    private Date loanEndDate;

    @Schema(description = "Return date of loan", example = "2024-05-15")
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
