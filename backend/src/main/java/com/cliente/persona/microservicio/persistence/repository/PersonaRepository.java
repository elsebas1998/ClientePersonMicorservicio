package com.cliente.persona.microservicio.persistence.repository;

import com.cliente.persona.microservicio.persistence.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByIdentificacion(final String identificacion);

    void deleteByIdentificacion(final String identificacion);
}
