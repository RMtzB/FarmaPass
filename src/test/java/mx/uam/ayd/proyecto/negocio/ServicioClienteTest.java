package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

@ExtendWith(MockitoExtension.class)
class ServicioClienteTest {
	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ServicioCliente servicioCliente = new ServicioCliente();

	@Test
	void actualizarDescuentoTest() {
		Cliente testCliente = new Cliente("Pedro", "Hernandez", "Lopez", "joana@gmail.com", "5544332211");
		when(clienteRepository.findById(0)).thenReturn(testCliente);
		servicioCliente.actualizarDescuento(0, 7);
		assertEquals(7,testCliente.getDescuentoCliente());
	}

	@Test
	void getDescuentoDeClienteTest() {
		Cliente testCliente = new Cliente("Pedro", "Hernandez", "Lopez", "joana@gmail.com", "5544332211");
		when(clienteRepository.findById(0)).thenReturn(testCliente);
		servicioCliente.actualizarDescuento(0, 10);
		int aux = servicioCliente.getDescuentoDeCliente(0);
		assertEquals(10,aux);
	}

	@Test
	void getNombreDeClienteTest() {
		Cliente testCliente = new Cliente("Pedro", "Hernandez", "Lopez", "joana@gmail.com", "5544332211");
		when(clienteRepository.findById(0)).thenReturn(testCliente);
		String aux= servicioCliente.getNombreDeCliente(0);
		assertEquals("Pedro Hernandez",aux);
	}
}
