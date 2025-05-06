package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "peticiones")
public class Peticion {

    @Id
    private String id;

    private String username; // quien hace la petición
    private RANGO rango;     // nuevo rango solicitado (opcional)
    private UserType userType; // nuevo tipo solicitado (opcional)
    private String rangoEspecifico;

    public String getRangoEspecifico() {
        return rangoEspecifico;
    }

    public void setRangoEspecifico(String rangoEspecifico) {
        this.rangoEspecifico = rangoEspecifico;
    }

    private String texto;    // motivo de la solicitud

    private EstadoPeticion estado = EstadoPeticion.PENDIENTE;

    // Getters y Setters...

    public EstadoPeticion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPeticion estado) {
        this.estado = estado;
    }
    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RANGO getRango() {
        return rango;
    }

    public void setRango(RANGO rango) {
        this.rango = rango;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
