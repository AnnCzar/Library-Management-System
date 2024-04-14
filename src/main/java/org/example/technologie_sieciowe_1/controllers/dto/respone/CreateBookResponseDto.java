package org.example.technologie_sieciowe_1.controllers.dto.respone;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookDetailsEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;

import java.util.List;

public class CreateBookResponseDto {
    @Schema(description = "Book ID", example = "1")
    private Integer id;
    @Schema(description = "ISBN of the book", example = "978-3-16-148410-0")
    private String isbn;
    @Schema(description = "Title of the book", example = "To Kill a Mockingbird")
    private String title;
    @Schema(description = "Author of the book", example = "Harper Lee")
    private String author;
    @Schema(description = "Publisher of the book", example = "J. B. Lippincott & Co.")
    private String publisher;
    @Schema(description = "Publish year of the book", example = "1960")
    private Integer publishYear;
    @Schema(description = "Number of copies available for the book", example = "5")
    private Integer numberCopy;

    public CreateBookResponseDto(Integer id, String isbn, String title, String author, String publisher, Integer publishYear, Integer numberCopy) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.numberCopy = numberCopy;
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

}
