package org.example.technologie_sieciowe_1.controllers.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CreateBookDto {
    @Schema(description = "ISBN", example = "1234567890123")
    private String isbn;
    @NotNull
    @Schema(description = "Title", example = "Title of the book")
    private String title;
    @NotNull
    @Schema(description = "Author", example = "John Doe")
    private String author;
    @Schema(description = "Publisher", example = "Publisher Name")
    private String publisher;
    @Schema(description = "Date of publication", example = "2024")
    private Integer publishYear;
    @NotNull
    @Schema(description = "Number of copies", example = "5")
    private Integer numberCopy;

    public CreateBookDto(String isbn, String title, String author, String publisher, Integer publishYear, Integer numberCopy) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.numberCopy = numberCopy;
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
