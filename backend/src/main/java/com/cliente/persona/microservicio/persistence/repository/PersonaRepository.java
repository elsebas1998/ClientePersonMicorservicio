package com.cliente.persona.microservicio.persistence.repository;

import com.cliente.persona.microservicio.persistence.entity.PersonaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByIdentificacion(final String identificacion);

    @Transactional
    void deleteByIdentificacion(final String identificacion);
}
