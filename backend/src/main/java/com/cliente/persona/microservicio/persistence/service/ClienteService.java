package com.cliente.persona.microservicio.persistence.service;

import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ClienteService {

    /**
     * Elimina un cliente mediante su codigo.
     * @param clientId codigo unico para identificar al cliente
     */
    void eliminarCliente(Long clientId);

    /**
     * Crea un cliente mediante sus respectivos campos.
     * @param clienteDto cuerpo requerido para crear al cliente
     * @return el objeto de ClienteEntity
     */
    ClienteEntity crearCliente(ClienteEntity clienteDto);
    /**
     * Actualiza un cliente mediante sus respectivos campos.
     * @param idCliente parametro para obtener al cliente
     * @return el objeto de ClienteEntity
     */
    ClienteEntity actualizarCliente(Long idCliente, ClienteEntity clienteDetalles);

    /**
     * Obtiene un cliente mediante su codigo identificador
     * @param identificacion parametro para obtener al cliente
     * @return el objeto de ClienteEntity
     */
    ClienteEntity obtenerCliente(final Long identificacion);
}
