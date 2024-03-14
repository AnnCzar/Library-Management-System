package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")
    private BookEntity book;

//    @ManyToOne
//    @JoinColumn(name = "bookId")
//    private BookEntity book;
//
//    @ManyToOne
//    @JoinColumn(name = "user")
//    private UserEntity userId;


    private Date rentalDate;

    private Date rentalEndDate;
    private Date returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRentalDate() {
        return rentalDate;
    }


    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    //    @OneToMany
//    @JoinColumn(name="id")
//    private BookEntity bookId;
}
