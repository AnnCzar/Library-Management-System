package org.example.technologie_sieciowe_1.controllers.dto.respone;

import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;

public class CreateBookDetailsResponseDto {
    private Integer id;
    private String genre;
    private String summary;
    private  String CoverImageURL;
    private BookEntity book;

    public CreateBookDetailsResponseDto(Integer id, String genre, String summary, String coverImageURL, BookEntity book) {
        this.id = id;
        this.genre = genre;
        this.summary = summary;
        CoverImageURL = coverImageURL;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
