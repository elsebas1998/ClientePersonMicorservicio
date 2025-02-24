package com.cliente.persona.microservicio.controller;

import com.cliente.persona.microservicio.core.CoreServices;
import com.cliente.persona.microservicio.dto.CreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente/")
public class UsuarioController {
    @Autowired
    private CoreServices coreServices;

    @GetMapping("obtener")
    public ResponseEntity<Object> obtenerUsuario(@PathVariable final String identificacion ){
        return coreServices.obtenerUsuario(identificacion);
    }
    @PostMapping("crear")
    public ResponseEntity<Object> crearUsuario(final CreateRequestDto createRequestDto){
       return coreServices.crearUsuario(createRequestDto);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Object> actualizarUsuario(final CreateRequestDto createRequestDto){
        return coreServices.actualizarUsuario(createRequestDto);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable final String identificacion){
        return coreServices.eliminarUsuario(identificacion);
    }
}
