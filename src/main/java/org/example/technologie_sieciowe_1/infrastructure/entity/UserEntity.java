package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "fullUserName")
    private String fullUserName;

    @OneToMany(mappedBy = "user")
    private List<LoanEntity> rental;

    @OneToMany(mappedBy = "user")
    private List<ReviewEntity> review;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AuthEntity auth;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public List<LoanEntity> getRental() {
        return rental;
    }

    public void setRental(List<LoanEntity> rental) {
        this.rental = rental;
    }

    public List<ReviewEntity> getReview() {
        return review;
    }

    public void setReview(List<ReviewEntity> review) {
        this.review = review;
    }
}
