package com.cliente.persona.microservicio.persistence.repository;

import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository <ClienteEntity, Long> {

    /**
     * Busca un cliente por medio de su identificador
     * @param idCliente parametro para bucar al cliente
     * @return un objeto de clienteEntity
     */
     Optional<ClienteEntity> findById(final Integer idCliente);
}
