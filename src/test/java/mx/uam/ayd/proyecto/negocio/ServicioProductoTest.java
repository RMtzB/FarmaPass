package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@ExtendWith(MockitoExtension.class)
class ServicioProductoTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Mock
	private ProductoRepository productoRepository;

	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioProducto servicio;

	
	@Test
	void testBuscarProductos() {
		
		Producto producto = new Producto();
		List<Producto> ListaProductos = new ArrayList<Producto>();
		
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		Producto p2 = new Producto();
		p2.setNombre("Pepto");
		p2.setCompuesto("ff");
		p2.setReceta("Si");
		p2.setUbicacion("aqui");
		p2.setPrecio(20);
		p2.setPiezas(5);
		ListaProductos.add(p1);
		ListaProductos.add(p2);
		
		when(productoRepository.findByNombre("aspirina")).thenReturn(p1);
		// Prueba 2: El método regresa el producto 
		//si lo encuantra
		Producto p = new Producto(); 
		p=servicio.buscarProducto("aspirina");
		
		assertEquals(p1, p);
		
		//Prueba 2: El método regresa null si no encuantra el producto
		/*p=servicio.buscarProducto("dualgos");
		
		assertNull(null);
		//Prueba 3: El método se le pase un null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.buscarProducto(null);*/
			

		
		
		//Prueba pt 2
		
		when(productoRepository.findByCompuesto("ff")).thenReturn(ListaProductos);
		// Prueba 1: El método regresa el producto 
		//si lo encuantra
		List<Producto> Lista = new ArrayList<Producto>();
		Lista=servicio.obtenerProductosPorActivo("ff");
		
		assertEquals(ListaProductos, Lista);
		
		//Prueba 2: El método regresa null si no encuantra el producto
		Lista=servicio.obtenerProductosPorActivo("dualgos");
		
		assertNull(Lista);
		//Prueba 3: El método se le pase un null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {

			servicio.buscarProducto(null);
			

		});

	}

	

	@Test
	void testActulizaInventarioMenos() {
		//Prueba 1: Se actualiza la cantidad de piezas de un producto
		LinkedList<Producto> lista = new LinkedList<>();
		Producto producto = new Producto();
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		
		lista.add(p1);
		
		servicio.actualizaInventarioMenos(lista);
		
		int p = p1.getPiezas();
		
		assertEquals(4,p);
		
		//Prueba 2: No se actualizó el inventario
		
		servicio.actualizaInventarioMenos(lista);
		
		int o = p1.getPiezas();
		
		assertNotEquals(8,o);
	}
	
	@Test
	void testModificar() {
		//Prueba 1: Se modifica un producro
		Producto p1 = new Producto();
		p1.setNombre("aspirina");
		p1.setCompuesto("ff");
		p1.setReceta("Si");
		p1.setUbicacion("aqui");
		p1.setPrecio(20);
		p1.setPiezas(5);
		productoRepository.save(p1);
		
		// modificamos el precio
		float cambio= 30;
		p1.setPrecio(cambio);
		productoRepository.save(p1);

		assertEquals(cambio, p1.getPrecio());
		
		
		
	}
}
