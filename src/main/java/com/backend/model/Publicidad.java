package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "publicidades")
public class Publicidad {

    @Id
    private String id;

    private int personasTraidas;
    private String supervisor;
    private String username;

    public String getId() {
        return id;
    }

    public int getPersonasTraidas() {
        return personasTraidas;
    }

    public void setPersonasTraidas(int personasTraidas) {
        this.personasTraidas = personasTraidas;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }
}
