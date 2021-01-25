package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sun.tools.javac.util.List;

import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@ExtendWith(MockitoExtension.class)

class ServicioEmpleadoTest {
	@Mock
	EmpleadoRepository empleadoRepository;
	
	@InjectMocks
	private ServicioEmpleado addempleadoSevice = new ServicioEmpleado();
	
	@Test
	void EliminarEmpleado() {
		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara", "Guzman", "karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda", "Rodriguez", "ximena@gmail.com", "5587388642",
				"encargado", "alma", "987654321");
		
		ArrayList<Empleado> ListaPrueba = new ArrayList<>();
		
		ListaPrueba.add(pruebaEmpleado);
		ListaPrueba.add(pruebaEncargado);
		
		when(empleadoRepository.findByUsuario("anver")).thenReturn(pruebaEmpleado);
		
		when(empleadoRepository.delete(pruebaEmpleado));
		when(empleadoRepository.delete(pruebaEncargado));
		
	}

}
