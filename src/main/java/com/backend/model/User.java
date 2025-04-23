package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Document(collection = "usuarios")
public class User {
    @Id
    private String id;
    private String username;
    private String habbousername;
    private String email;
    private String password;
    private RANGO rango;
    private String rangoEspecifico;
    private UserType userType;
    private int idUser;
    private Map<Ventaja, LocalDate> ventajas;
    public String getId() {
        return id;
    }

    public Map<Ventaja, LocalDate> getVentajas() {
        return ventajas;
    }

    public void setVentajas(Map<Ventaja, LocalDate> ventajas) {
        this.ventajas = ventajas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRangoEspecifico() {
        return rangoEspecifico;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setRangoEspecifico(String rangoEspecifico) {
        this.rangoEspecifico = rangoEspecifico;
    }

    public RANGO getRango() {
        return rango;
    }

    public void setRango(RANGO rango) {
        this.rango = rango;
    }

    public User(String username, int idUser, String habbousername, String email, String password, RANGO rango, String rangoEspecifico, UserType userType) {
        this.username = username;
        this.habbousername = habbousername;
        this.email = email;
        this.password = password;
        this.rango = rango;
        this.rangoEspecifico = rangoEspecifico;
        this.userType = userType;
        this.idUser = idUser;
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
