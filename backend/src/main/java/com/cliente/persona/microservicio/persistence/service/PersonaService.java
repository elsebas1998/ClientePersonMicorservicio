package com.cliente.persona.microservicio.persistence.service;

import com.cliente.persona.microservicio.persistence.entity.PersonaEntity;

public interface PersonaService {
    PersonaEntity obtenerPersona(String identificacion);

    PersonaEntity crearPersona(PersonaEntity persona);

    void eliminarPersona(String identificacion);
}
