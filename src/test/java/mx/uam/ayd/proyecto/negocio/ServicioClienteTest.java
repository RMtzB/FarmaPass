package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;


@ExtendWith(MockitoExtension.class)
class ServicioClienteTest {
	// Al usar la anotación @Mock, el framework Mockito crea un sustituto
	// de la clase que regresa valores por default
	@Mock
	private ClienteRepository clienteRepository;

	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioCliente servicioCliente;

	@Test
	void testBuscarClientes() {
		// Prueba 1: corroborar que regresa una lista vacía si no hay asistencias en la BD
		
		// en este momento, la invocación a asistenciarepository.findAll() regresa una lista vacía
		List <Cliente> cliente = servicioCliente.buscarClientes();
		
		assertTrue(cliente.isEmpty());
		
		// Prueba 2: corroborar que regresa una lista con asistencias
		LinkedList <Cliente> lista = new LinkedList <> ();

		// Tengo que crear un Iterable <Asistencia> para que el método 
		// asistenciaRepository.findAll() no me regrese una lista vacía
		// cuando lo invoco
		ArrayList<String> datos = new ArrayList<String>();
		datos.add("2021/1/21?58.0?2");
		Cliente cliente1 = new Cliente();
		cliente1.setApellidoMaterno("Morales");
		cliente1.setApellidoPaterno("Gonzalez");
		cliente1.setCorreo("MAR123@GMAIL.COM");
		cliente1.setHistorial(datos);
		cliente1.setIdCliente(0);
		cliente1.setNombre("Martin");
		cliente1.setTelefono("5536984523");
		
		ArrayList<String> datos2 = new ArrayList<String>();
		datos2.add("2021/1/2?53.0?1");
		Cliente cliente2 = new Cliente();
		cliente2.setApellidoMaterno("Morales2");
		cliente2.setApellidoPaterno("Gonzalez2");
		cliente2.setCorreo("MAR123@GMAIL2.COM");
		cliente2.setHistorial(datos2);
		cliente2.setIdCliente(0);
		cliente2.setNombre("Martin2");
		cliente2.setTelefono("5536982222");
		
		lista.add(cliente1);
		lista.add(cliente2);
		
		// Al usar when, lo que hacemos es que definimos un comportamiento
		// para la invoación del método.
		// A partir de este punto, la invocación a asistenciaRepository.findAll() ya
		// no me regresa una lista vacía, si no que me regresa una listaLigada
		// vista como Iterable que tiene dos elementos
		when(clienteRepository.findAll()).thenReturn(lista);
		
		cliente = servicioCliente.buscarClientes();
		
		assertEquals(2,cliente.size()); // Corroboro que tenga dos elementos
	}
	
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
