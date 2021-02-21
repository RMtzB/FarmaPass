package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@ExtendWith(MockitoExtension.class)
class ServicioVentaTest {
	
	@Mock
	private VentaRepository ventaRepository;
	


	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@InjectMocks
	private ServicioVenta servicio;

	@Test
	void testObtenerVentasPorFecha() {
		
			String fechaF = "18/02/2021";
			// Prueba 1: corroborar que regresa una lista vacía si no hay ventas por esa fecha en la BD
		
			List <Venta> ventas = servicio.obtenerVentasPorFecha(fechaF);
				
			assertTrue(ventas.isEmpty());
				

			// Prueba 2: corroborar que regresa una lista con ventas de esa fecha
			LinkedList <Venta> lista = new LinkedList <> ();
			
			Venta venta1 = new Venta();
			venta1.setFecha("18/02/2021");
			venta1.setTotal(50);
			venta1.setResponsable("anver");

			Venta venta2 = new Venta();
			venta2.setFecha("18/02/2021");
			venta2.setTotal(40);
			venta2.setResponsable("anver");
				 
			lista.add(venta1);
			lista.add(venta2);
				
				
			when(ventaRepository.findByFecha(fechaF)).thenReturn(lista);
				
			ventas = servicio.obtenerVentasPorFecha(fechaF);
				
			assertEquals(2,ventas.size()); // Corroboro que tenga dos elementos
	}

	@Test
	void testRecuperaTodasLasVentas() {
		
		// Prueba 1: corroborar que regresa una lista vacía si no hay ventas guardadas en la BD
		
		List <Venta> ventas = servicio.recuperaTodasLasVentas();
		
		assertTrue(ventas.isEmpty());
		

		// Prueba 2: corroborar que regresa una lista con ventas
		LinkedList <Venta> lista = new LinkedList <> ();

	
		Venta venta1 = new Venta();
		venta1.setFecha("18/02/2021");
		venta1.setTotal(50);
		venta1.setResponsable("anver");

		Venta venta2 = new Venta();
		venta2.setFecha("18/02/2021");
		venta2.setTotal(40);
		venta2.setResponsable("anver");
		 
		lista.add(venta1);
		lista.add(venta2);
		
		
		
		when(ventaRepository.findAll()).thenReturn(lista);
		
		ventas = servicio.recuperaTodasLasVentas();
		
		assertEquals(2,ventas.size()); // Corroboro que tenga dos elementos

	}

	@Test
	void testObtenerVentasPorEmpleado() {

		String usuario = "anver";
		// Prueba 1: corroborar que regresa una lista vacía si no hay ventas por el usuario/responsable en la BD
	
		List <Venta> ventas = servicio.obtenerVentasPorEmpleado(usuario);
			
		assertTrue(ventas.isEmpty());
			

		// Prueba 2: corroborar que regresa una lista con ventas del usuario/responsable
		LinkedList <Venta> lista = new LinkedList <> ();
		
		Venta venta1 = new Venta();
		venta1.setFecha("18/02/2021");
		venta1.setTotal(50);
		venta1.setResponsable("anver");

		Venta venta2 = new Venta();
		venta2.setFecha("18/02/2021");
		venta2.setTotal(40);
		venta2.setResponsable("anver");
			 
		lista.add(venta1);
		lista.add(venta2);
			
			
		when(ventaRepository.findByResponsable(usuario)).thenReturn(lista);
			
		ventas = servicio.obtenerVentasPorEmpleado(usuario);
			
		assertEquals(2,ventas.size()); // Corroboro que tenga dos elementos
	}

}
