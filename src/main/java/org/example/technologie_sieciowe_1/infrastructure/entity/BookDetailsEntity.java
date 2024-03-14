package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

@Entity
public class BookDetailsEntity {

//    @OneToOne
//    @JoinColumn(name = "bookId")
//    private BookEntity bookId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String genre;

    private String summary;

    private  String CoverImageURL;

//    public BookEntity getBook() {
//        return bookId;
//    }
//
//    public void setBook(BookEntity book) {
//        this.bookId = book;
//    }

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
}
