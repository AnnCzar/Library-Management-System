package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer userId;

    private String userName;

    private String password;

    private String Role;

    private String email;

    private String fullUserName;

//    @OneToMany(mappedBy = "userId")
//    private Collection<RentalEntity> rentals;
//
//    @OneToMany(mappedBy =  "userId")
//    private Collection<ReviewEntity> reviews;

    public Integer getId() {
        return userId;
    }

    public void setId(Integer id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
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

    //    W tabeli tej będą przechowywane informacje o użytkownikach systemu bibliotecznego, zarówno o czytelnikach,
//    jak i pracownikach biblioteki.
//    • UserID (klucz podstawowy): unikalny identyfikator każdego użytkownika.
//    • Nazwa użytkownika: nazwa użytkownika wybrana przez użytkownika.
//    • Hasło: zaszyfrowane hasło do uwierzytelniania użytkownika.
//
//    • Rola: Określa rolę użytkownika (np. Czytelnik lub Pracownik biblioteki).
//    • E-mail: adres e-mail użytkownika.
//    • Nazwa: Pełna nazwa użytkownika.
}
