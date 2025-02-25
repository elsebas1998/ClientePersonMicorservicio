package cliente.persona.microservicio;

import com.cliente.persona.microservicio.core.impl.CoreServicesImpl;
import com.cliente.persona.microservicio.Dto.CreateRequestDto;
import com.cliente.persona.microservicio.persistence.entity.ClienteEntity;
import com.cliente.persona.microservicio.persistence.service.ClienteService;
import com.cliente.persona.microservicio.persistence.service.PersonaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CoreServicesImplTest {
    @InjectMocks
    private CoreServicesImpl coreServices;

    @Mock
    private ClienteService clienteService;

    @Mock
    private PersonaService personaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearUsuario() {
        // Arrange
        CreateRequestDto createRequestDto = new CreateRequestDto();
        createRequestDto.setDireccion("123 Main St");
        createRequestDto.setEdad(30);
        createRequestDto.setNombre("Juan Perez");
        createRequestDto.setTelefono("555-1234");
        createRequestDto.setIdentificacion("123456789");
        createRequestDto.setGenero("M");
        createRequestDto.setEstado(true);
        createRequestDto.setContrasena("password");

        ClienteEntity clienteEntity = new ClienteEntity(createRequestDto.getContrasena(), createRequestDto.getEstado());
        clienteEntity.setDireccion(createRequestDto.getDireccion());
        clienteEntity.setEdad(createRequestDto.getEdad());
        clienteEntity.setNombre(createRequestDto.getNombre());
        clienteEntity.setTelefono(createRequestDto.getTelefono());
        clienteEntity.setIdentificacion(createRequestDto.getIdentificacion());
        clienteEntity.setGenero(createRequestDto.getGenero());

        when(clienteService.crearCliente(clienteEntity)).thenReturn(clienteEntity);

        // Act
        ResponseEntity<Object> response = coreServices.crearUsuario(createRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Se creó el usuario", response.getBody());
    }
}
