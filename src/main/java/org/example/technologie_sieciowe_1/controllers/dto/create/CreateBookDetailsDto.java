package org.example.technologie_sieciowe_1.controllers.dto.create;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;

public class CreateBookDetailsDto {
    private String genre;
    private String summary;
    private  String CoverImageURL;
    private BookEntity book;

    public CreateBookDetailsDto(String genre, String summary, String coverImageURL, BookEntity book) {
        this.genre = genre;
        this.summary = summary;
        CoverImageURL = coverImageURL;
        this.book = book;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageURL() {
        return CoverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        CoverImageURL = coverImageURL;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
