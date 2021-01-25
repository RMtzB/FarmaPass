package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@ExtendWith(MockitoExtension.class)
class ServicioDescuentosTest {
	@Mock
	ProductoRepository productoRepository;
	
	@InjectMocks
	private ServicioDescuentos servicioDescuento= new ServicioDescuentos();
	

	@Test
	void cambiarDescuentoTest() {
		Producto producto = new Producto("AJOLOTIUS", "Miel de abeja", "No", "Estante 2, segundo anaquel", 50, 10);
		
		when(productoRepository.findByNombre("AJOLOTIUS")).thenReturn(producto);
		String descuentoAnterior= producto.getDescuento();
		String fechaAnterior = producto.getFecha();
		
		servicioDescuento.cambiarDescuento("AJOLOTIUS", "30","2021-01-27");
		assertNotEquals(descuentoAnterior,producto.getDescuento());
		
		assertNotEquals(fechaAnterior,producto.getFecha());
		
		}
	
	@Test
	void verificarDescuentosVencidosTest() {
		Producto producto = new Producto("AJOLOTIUS", "Miel de abeja", "No", "Estante 2, segundo anaquel", 50, 10);
		producto.setDescuento("30");
		//Ingresasr la fecha del dia de hoy para probar
		producto.setFecha("2021-01-25");
		
		ArrayList<Producto> aux= new ArrayList<>();
		aux.add(producto);
		
		when(productoRepository.findByFecha("2021-01-25")).thenReturn(aux);
		
		servicioDescuento.verificarDescuentosVencidos();
		
		assertEquals("",producto.getDescuento());
		assertEquals("",producto.getFecha());
	}
	
	

}
