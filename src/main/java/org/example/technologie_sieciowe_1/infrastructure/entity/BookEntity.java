package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "isbn")
    private String isbn;
    @Basic
    @Column(name = "title")
    private  String title;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "publisher")
    private String publisher;
    @Basic
    @Column(name = "publisherYear")
    private Integer publishYear;
    @Basic
    @Column(name = "numberCopy")
    private Integer numberCopy;

    @OneToMany(mappedBy = "book")
    private List<RentalEntity> rental;

//    @OneToMany(mappedBy = "book")
//    private List<RentalEntity> rentals;
//
//
//    @OneToMany(mappedBy = "book")
//    private List<ReviewEntity> reviews;
//

//    @OneToOne(mappedBy = "bookId")
//    private BookDetailsEntity bookDetails;


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
