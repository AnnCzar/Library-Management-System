package org.example.technologie_sieciowe_1.infrastructure.entity;

import jakarta.persistence.*;
import org.example.technologie_sieciowe_1.commonTypes.UserRole;

@Entity
@Table(name ="auth")
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    public AuthEntity(Integer id, String username, String password, UserRole role, UserEntity user) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.user = user;
    }

    public AuthEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
