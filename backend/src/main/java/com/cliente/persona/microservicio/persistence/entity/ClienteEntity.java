package com.cliente.persona.microservicio.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity {
    private String contrasena;
    private Boolean estado;

    public ClienteEntity(String contrasena, Boolean estado) {
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public ClienteEntity(){

    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
