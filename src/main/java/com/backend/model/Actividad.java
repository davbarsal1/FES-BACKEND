package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document("actividades")
public class Actividad {
    @Id
    private String id;

    private String tipo; // "JUEGO", "ATAQUE", "DEFENSA", "MARCHA"
    private String supervisor;
    private String guia;
    private Map<String, Double> participantes; // { "usuario1": 0.5, "usuario2": 1.0, ... }
    // nombre -> pda
    private LocalDateTime fecha = LocalDateTime.now();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getGuia() {
        return guia;
    }

    public void setGuia(String guia) {
        this.guia = guia;
    }

    public Map<String, Double> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Map<String, Double> participantes) {
        this.participantes = participantes;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
