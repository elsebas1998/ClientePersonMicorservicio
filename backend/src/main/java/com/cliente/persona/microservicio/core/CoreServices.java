package com.cliente.persona.microservicio.core;

import com.cliente.persona.microservicio.dto.CreateRequestDto;
import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import org.springframework.http.ResponseEntity;

public interface CoreServices {
    ResponseEntity<Object> obtenerUsuario(final String identificacion);

    ResponseEntity<Object> crearUsuario(CreateRequestDto createRequestDto);

    ResponseEntity<Object> eliminarUsuario(String documento);

    ResponseEntity<Object> actualizarUsuario(final String identificacion, final CreateRequestDto createRequestDto);
}
