package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@ExtendWith(MockitoExtension.class)
class ServicioAgregarProductoInventarioTest {	
	@Mock
	ProductoRepository productRepository;
	
	@InjectMocks
	private ServicioAgregarProductoInventario addProductSevice = new ServicioAgregarProductoInventario();

	
	
	@Test
	public void addProductTest() {
		Producto product = new Producto("Nombre",
				"Componente activo",
				"Receta",
				"Ubicación",
				0,
				0);
		
		
		
		when(productRepository.save(product)).thenReturn(product);
		
		System.out.println("Prueba 1: Regresa true (si el producto es guardado)");
		assertEquals(true, addProductSevice.addProduct(product));
		
		
		when(productRepository.save(product)).thenReturn(null);
		
		System.out.println("Prueba 2: Regresa el false (si el producto no es guardado)");
		assertEquals(false, addProductSevice.addProduct(product));
	}
	
	@Test
	public void areFieldsCorrectTest() {
		System.out.println("Prueba 1: Regresa 3 si alguno de los campos nombre, ubicación y"
							+ " componente activo esta vacío o solo contiene espacios"
							+ " en blanco");
		assertEquals(3, addProductSevice.areFieldsCorrect("", "Ubicación", "0",
														"Componente activo", "0"));
		
		
		System.out.println("Prueba 2: Regresa 1 si el campo precio no es un número"
							+ " o esta vacío");
		assertEquals(1, addProductSevice.areFieldsCorrect("Nombre", "Ubicación", "",
														"Componente activo", "0"));
		
		
		System.out.println("Prueba 2: Regresa 2 si el campo piezas no es un número entero"
							+ " o esta vacío");
		assertEquals(2, addProductSevice.areFieldsCorrect("Nombre", "Ubicación", "0",
														"Componente activo", "SDASD"));
	}
}
