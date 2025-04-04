package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tiempos")
public class TiempoEnSala {

    @Id
    private String id;

    private String username;

    private long segundosTotales = 0; // tiempo acumulado
    private LocalDateTime inicio;     // cuando se inició la sesión
    private boolean activo = false;   // si está en sala
    private String iniciadoPor;
    // Getters y Setters...

    public String getIniciadoPor() {
        return iniciadoPor;
    }

    public void setIniciadoPor(String iniciadoPor) {
        this.iniciadoPor = iniciadoPor;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public long getSegundosTotales() { return segundosTotales; }
    public void setSegundosTotales(long segundosTotales) { this.segundosTotales = segundosTotales; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
