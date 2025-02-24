package com.cliente.persona.microservicio.core.impl;


import com.cliente.persona.microservicio.core.CoreServices;
import com.cliente.persona.microservicio.dto.CreateRequestDto;
import com.cliente.persona.microservicio.dto.ResponseDto;
import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import com.cliente.persona.microservicio.persistence.entity.PersonaEntity;
import com.cliente.persona.microservicio.persistence.service.ClienteService;
import com.cliente.persona.microservicio.persistence.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CoreServicesImpl implements CoreServices {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PersonaService personaService;
    private ResponseEntity<Object> buildResponseError(final String detalle) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Error");
        response.put("detalle", detalle);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ResponseEntity<Object> ObtenerClienteResponse(final Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("Cliente", data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Object> obtenerUsuario(final String identificacion){
        PersonaEntity persona = personaService.obtenerPersona(identificacion);
        ClienteEntity clienteEntity = clienteService.obtenerCliente(identificacion);
        ResponseDto requestDto = new ResponseDto();
        requestDto.setGenero(persona.getGenero());
        requestDto.setEdad(persona.getEdad());
        requestDto.setEstado(clienteEntity.getEstado());
        requestDto.setDireccion(persona.getDireccion());
        requestDto.setIdentificacion(persona.getIdentificacion());
        requestDto.setNombre(persona.getNombre());
        requestDto.setTelefono(persona.getTelefono());
            return ObtenerClienteResponse(requestDto);
    }

    @Override
    public  ResponseEntity<Object> crearUsuario(final CreateRequestDto createRequestDto){
        PersonaEntity persona = new PersonaEntity();
        ClienteEntity cliente = new ClienteEntity();
        persona.setDireccion(createRequestDto.getDireccion());
        persona.setEdad(createRequestDto.getEdad());
        persona.setNombre(createRequestDto.getNombre());
        persona.setTelefono(createRequestDto.getTelefono());
        persona.setIdentificacion(createRequestDto.getIdentificacion());
        persona.setGenero(createRequestDto.getGenero());

        cliente.setEstado(createRequestDto.getEstado());
        cliente.setContrasena(createRequestDto.getContrasena());

        personaService.crearPersona(persona);
        clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Se creo el usuario");
    }

    @Override
    public ResponseEntity<Object> eliminarUsuario(final String documento) {
        personaService.eliminarPersona(documento);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Object> actualizarUsuario(final CreateRequestDto createRequestDto) {
        PersonaEntity persona = personaService.obtenerPersona(createRequestDto.getIdentificacion());
        ClienteEntity clienteEntity = clienteService.obtenerCliente(createRequestDto.getIdentificacion());
        ResponseDto requestDto = new ResponseDto();
        requestDto.setGenero(persona.getGenero());
        requestDto.setEdad(persona.getEdad());
        requestDto.setEstado(clienteEntity.getEstado());
        requestDto.setDireccion(persona.getDireccion());
        requestDto.setIdentificacion(persona.getIdentificacion());
        requestDto.setNombre(persona.getNombre());
        requestDto.setTelefono(persona.getTelefono());
        return ResponseEntity.status(HttpStatus.OK).body("Se actualizo el usuario");
    }


}
