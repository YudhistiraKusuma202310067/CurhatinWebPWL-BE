package com.pwl.curhatin.User;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "User")
public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    @NotEmpty(message = "Username is required")
    private String username;

    @Column(length = 50)
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(length = 100)
    @NotEmpty(message = "Password is required")
    private String password;

    public User() {
    }

    public User(@NotEmpty(message = "Username is required") String username,
            @NotEmpty(message = "Email is required") String email,
            @NotEmpty(message = "Password is required") String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
