package org.example.technologie_sieciowe_1.controllers.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Schema(description = "DTO for creating a loan")
public class CreateLoanDto {

    @NotNull
    @Schema(description = "ID of the book", example = "123")
    private Integer book;
    @NotNull
    @Schema(description = "ID of the user", example = "456")
    private Integer user;
    @NotNull
    @Schema(description = "Date of loan", example = "2024-04-14")
    private Date loanDate;
    @NotNull
    @Schema(description = "End date of loan", example = "2024-04-30")
    private Date loanEndDate;
    @Schema(description = "Return date of loan", example = "2024-05-15")
    private Date returnDate;

    public CreateLoanDto(Integer book, Integer user, Date loanEndDate, Date returnDate) {
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
