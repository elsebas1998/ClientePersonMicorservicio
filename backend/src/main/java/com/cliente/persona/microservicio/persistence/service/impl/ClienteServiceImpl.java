package com.cliente.persona.microservicio.persistence.service.impl;

import com.cliente.persona.microservicio.exception.ClientePersonaException;
import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import com.cliente.persona.microservicio.persistence.repository.ClienteRepository;
import com.cliente.persona.microservicio.persistence.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void eliminarCliente(final Long idCliente) {
        try {
            clienteRepository.deleteById(idCliente);
        } catch (Exception e){
            throw new ClientePersonaException("No se encontro al cliente con id: " + idCliente);
        }
    }

    @Override
    public ClienteEntity crearCliente(final ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public  ClienteEntity actualizarCliente(Long idCliente, ClienteEntity clienteDetalles) {
        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setContrasena(clienteDetalles.getContrasena());
        cliente.setEstado(clienteDetalles.getEstado());
        return clienteRepository.save(cliente);
    }

    @Override
    public ClienteEntity obtenerCliente(final Long identificacion) {
        return clienteRepository.findById(identificacion)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
