package com.cliente.persona.microservicio.persistence.service.impl;

import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import com.cliente.persona.microservicio.persistence.entity.PersonaEntity;
import com.cliente.persona.microservicio.persistence.repository.PersonaRepository;
import com.cliente.persona.microservicio.persistence.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    public PersonaEntity obtenerPersona(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
         return personaRepository.save(persona);
    }

    @Override
    public void eliminarPersona(String identificacion) {
        personaRepository.deleteByIdentificacion(identificacion);
    }

}
