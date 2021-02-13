package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

@ExtendWith(MockitoExtension.class)
class ServicioAgregarClienteTest {
	@Mock
	ClienteRepository clientRepository;
	
	@InjectMocks
	ServicioAgregarCliente addClientService;
	
	
	@Test
	public void addClientTest() {
		Cliente client = new Cliente("Nombre", "Apellido01", "Apellido02", "Correo", "Tel");
		
		
		
		when(clientRepository.save(client)).thenReturn(client);
		
		System.out.println("Prueba 1: Regresa true (si el cliente es guardado)");
		assertEquals(client.getIdCliente(), addClientService.addClient(client));
		
		
		when(clientRepository.save(client)).thenReturn(null);
		
		System.out.println("Prueba 2: Regresa el false (si el cliente no es guardado)");
		assertEquals(-1, addClientService.addClient(client));
	}
	
	
	
	@Test
	public void areFieldsCorrectTest() {
		System.out.println("Prueba 1: Regresa 3 si alguno de los campos esta vacío o solo contiene espacios"
				+ " en blanco");
		assertEquals(3, addClientService.areFieldsCorrect("Nombre", "Apellidos", "Correo", ""));


		System.out.println("Prueba 2: Regresa 4 si el campo correo no tiene un formato adecuado");
		assertEquals(4, addClientService.areFieldsCorrect("Nombre", "Apellidos", "sdasdas",
											"1234567890"));


		System.out.println("Prueba 3: Regresa 5 si el campo número no tiene un formato adecuado");
		assertEquals(5, addClientService.areFieldsCorrect("Nombre", "Apellidos", "asda@fsdf.com",
											"asda"));
		
		System.out.println("Prueba 4: Regresa 0 si todos los campos son correctos");
		assertEquals(0, addClientService.areFieldsCorrect("Nombre", "Apellidos", "asda@fsdf.com",
											"1234567890"));
	}
}
