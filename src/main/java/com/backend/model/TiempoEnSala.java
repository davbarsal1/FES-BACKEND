package com.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "tiempos")
public class TiempoEnSala {

    @Id
    private String id;

    private String username;
    private String iniciadoPor;
    private String estado = "NORMAL"; // puede ser NORMAL, PUBLICIDAD o ACTIVIDAD

    private long segundosTotales = 0;
    private LocalDateTime inicio;
    private boolean activo = false;

    public String getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getIniciadoPor() { return iniciadoPor; }
    public void setIniciadoPor(String iniciadoPor) { this.iniciadoPor = iniciadoPor; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public long getSegundosTotales() { return segundosTotales; }
    public void setSegundosTotales(long segundosTotales) { this.segundosTotales = segundosTotales; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

}
