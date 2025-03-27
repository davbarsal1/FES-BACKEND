package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class User {
    @Id
    private String id;
    private String username;
    private String habbousername;
    private String email;
    private String password;

    // Constructor vacío
    public User() {}

    // Constructor con parámetros
    public User(String username, String habbousername, String email, String password) {
        this.username = username;
        this.habbousername = habbousername;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHabbousername() {
        return habbousername;
    }

    public void setHabbousername(String habbousername) {
        this.habbousername = habbousername;
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
