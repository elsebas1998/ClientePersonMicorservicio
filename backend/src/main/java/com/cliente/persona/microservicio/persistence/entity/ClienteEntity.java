package com.cliente.persona.microservicio.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity extends PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String contrasena;
    private String estado;
}
