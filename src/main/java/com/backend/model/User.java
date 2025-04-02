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
    private RANGO rango;
    private UserType userType;

    public RANGO getRango() {
        return rango;
    }

    public void setRango(RANGO rango) {
        this.rango = rango;
    }

    // Constructor con parámetros
    public User(String username, String habbousername, String email, String password, UserType userType) {
        this.username = username;
        this.habbousername = habbousername;
        this.email = email;
        this.password = password;
        this.userType = userType;
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

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", habbousername='" + habbousername + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rango=" + rango +
                ", userType=" + userType +
                '}';
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
