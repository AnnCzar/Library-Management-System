package org.example.technologie_sieciowe_1.controllers.dto.get;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Schema(description = "DTO for getting loan details")
public class GetLoanDto {
    @Schema(description = "ID of the loan", example = "789")
    private Integer loanid;
    @Schema(description = "Book details")
    private GetBookDto book;

    @Schema(description = "User details")
    private GetUserDto user;

    @Schema(description = "Date of loan", example = "2024-04-14")
    private Date loanDate;

    @Schema(description = "End date of loan", example = "2024-04-30")
    private Date loanEndDate;

    @Schema(description = "Return date of loan", example = "2024-05-15")
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
