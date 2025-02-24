package com.cliente.persona.microservicio.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
@Data
public class ResponseDto {
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
    private String estado;
}
