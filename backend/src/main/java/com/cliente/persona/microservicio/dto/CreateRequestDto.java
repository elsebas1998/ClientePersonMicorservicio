package com.cliente.persona.microservicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDto {
    @NotNull
    private String nombre;
    @NotNull
    private String genero;
    @NotNull
    private int edad;
    @NotNull
    private String identificacion;
    @NotNull
    private String direccion;
    @NotNull
    private String telefono;
    @NotNull
    private String contrasena;
    @NotNull
    private String estado;
}
