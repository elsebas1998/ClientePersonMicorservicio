package com.cliente.persona.microservicio.persistence.service.impl;

import com.cliente.persona.microservicio.config.KafkaTopicConfig;
import com.cliente.persona.microservicio.core.CoreServices;
import com.cliente.persona.microservicio.dto.ClienteInfoRequest;
import com.cliente.persona.microservicio.dto.ClienteInfoResponse;
import com.cliente.persona.microservicio.dto.ResponseDto;
import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import com.cliente.persona.microservicio.persistence.entity.PersonaEntity;
import com.cliente.persona.microservicio.persistence.service.ClienteService;

import com.cliente.persona.microservicio.persistence.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static java.lang.Long.parseLong;

@Service
public class ClienteInfoServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ClienteInfoServiceImpl.class);

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private KafkaTemplate<String, ClienteInfoResponse> kafkaTemplate;

    @Autowired
    private PersonaService personaService;

    /**
     * Escucha las solicitudes de información de clientes
     */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC_CLIENTE_REQUEST)
    public void handleClienteInfoRequest(ClienteInfoRequest request, String identificacion) {
        logger.info("Recibida solicitud de información para clienteId: {}", request.getClienteId());

        try {
            PersonaEntity persona = personaService.obtenerPersona(identificacion);
            ClienteEntity clienteEntity = clienteService.obtenerCliente(persona.getId());

            ClienteInfoResponse response = new ClienteInfoResponse();
            response.setRequestId(request.getRequestId());
            response.setClienteId(clienteEntity.getId());
            response.setNombre(clienteEntity.getNombre());
            response.setGenero(clienteEntity.getGenero());
            response.setEdad(clienteEntity.getEdad());
            response.setIdentificacion(clienteEntity.getIdentificacion());
            response.setDireccion(clienteEntity.getDireccion());
            response.setTelefono(clienteEntity.getTelefono());
            response.setEstado(clienteEntity.getEstado());

            kafkaTemplate.send(KafkaTopicConfig.TOPIC_CLIENTE_RESPONSE, clienteEntity.getId()+"", response);
            logger.info("Enviada respuesta para clienteId: {}", clienteEntity.getId());

        } catch (Exception e) {
            logger.error("Error procesando solicitud de información de cliente: {}", e.getMessage());

            ClienteInfoResponse errorResponse = new ClienteInfoResponse();
            errorResponse.setRequestId(request.getRequestId());
            errorResponse.setClienteId(parseLong(request.getClienteId()));

            kafkaTemplate.send(KafkaTopicConfig.TOPIC_CLIENTE_RESPONSE, request.getClienteId(), errorResponse);
        }
    }
}
