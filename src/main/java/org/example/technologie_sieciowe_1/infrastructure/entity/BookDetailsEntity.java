package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

@Entity
public class BookDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "genre")
    private String genre;
    @Basic
    @Column(name = "summary")
    private String summary;
    @Basic
    @Column(name = "CoverImageURL")
    private  String CoverImageURL;
    @OneToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
    private BookEntity book;

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
