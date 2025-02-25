package com.cliente.persona.microservicio.core.impl;


import com.cliente.persona.microservicio.core.CoreServices;
import com.cliente.persona.microservicio.Dto.CreateRequestDto;
import com.cliente.persona.microservicio.Dto.ResponseDto;
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
        try{
            PersonaEntity persona = personaService.obtenerPersona(identificacion);
            ClienteEntity clienteEntity = clienteService.obtenerCliente(persona.getId());
            ResponseDto requestDto = new ResponseDto();
            requestDto.setGenero(persona.getGenero());
            requestDto.setEdad(persona.getEdad());
            requestDto.setEstado(clienteEntity.getEstado());
            requestDto.setDireccion(persona.getDireccion());
            requestDto.setIdentificacion(persona.getIdentificacion());
            requestDto.setNombre(persona.getNombre());
            requestDto.setTelefono(persona.getTelefono());
            return ObtenerClienteResponse(requestDto);
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }

    }

    @Override
    public  ResponseEntity<Object> crearUsuario(final CreateRequestDto createRequestDto){
        try {
            ClienteEntity cliente = new ClienteEntity();

            cliente.setDireccion(createRequestDto.getDireccion());
            cliente.setEdad(createRequestDto.getEdad());
            cliente.setNombre(createRequestDto.getNombre());
            cliente.setTelefono(createRequestDto.getTelefono());
            cliente.setIdentificacion(createRequestDto.getIdentificacion());
            cliente.setGenero(createRequestDto.getGenero());

            cliente.setEstado(createRequestDto.getEstado());
            cliente.setContrasena(createRequestDto.getContrasena());
            clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creó el usuario");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> eliminarUsuario(final String documento) {
        try {
            PersonaEntity persona = personaService.obtenerPersona(documento);
            personaService.eliminarPersona(persona.getIdentificacion());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }

    }

    @Override
    public ResponseEntity<Object> actualizarUsuario(final String identificacion, final CreateRequestDto createRequestDto) {
        try {
            PersonaEntity persona = personaService.obtenerPersona(identificacion);
            ClienteEntity cliente = clienteService.obtenerCliente(persona.getId());
            cliente.setDireccion(createRequestDto.getDireccion());
            cliente.setEdad(createRequestDto.getEdad());
            cliente.setNombre(createRequestDto.getNombre());
            cliente.setTelefono(createRequestDto.getTelefono());
            cliente.setIdentificacion(createRequestDto.getIdentificacion());
            cliente.setGenero(createRequestDto.getGenero());
            cliente.setEstado(createRequestDto.getEstado());
            cliente.setContrasena(createRequestDto.getContrasena());
            clienteService.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo el usuario");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

}
