package org.example.technologie_sieciowe_1.controllers.dto.get;

import jakarta.persistence.*;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;

public class GetBookDetailsDto {

    private Integer id;
    private String genre;
    private String summary;
    private  String CoverImageURL;
    private GetBookDto book;   // change to GetBookDto

    public GetBookDetailsDto(Integer id, String genre, String summary, String coverImageURL) {
        this.id = id;
        this.genre = genre;
        this.summary = summary;
        this.CoverImageURL = coverImageURL;
//        this.book = book;
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

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }
}
