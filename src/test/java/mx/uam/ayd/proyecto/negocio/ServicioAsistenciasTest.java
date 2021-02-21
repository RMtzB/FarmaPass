package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Pruebas unitarias para ServicioAsistencia
 * 
 * @author Raul Mb
 * @since 20/02/2021
 */
@ExtendWith(MockitoExtension.class)
class ServicioAsistenciasTest {
	@Mock
	private AsistenciaRepository asistenciaRepository;

	@InjectMocks
	private ServicioAsistencia servicio;

	@Test
	public void registroAsistenciaTest() {
		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara", "Guzman", "karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Asistencia asistencia = new Asistencia(pruebaEmpleado, "00:00:00", "00/00/0000");

		when(asistenciaRepository.save(asistencia)).thenReturn(asistencia);
		assertTrue(servicio.registroAsistencia("00:00:00", "00/00/0000", pruebaEmpleado));

		when(asistenciaRepository.save(asistencia)).thenReturn(null);
		assertFalse(servicio.registroAsistencia("00:00:00", "00/00/0000", pruebaEmpleado));
	}

	@Test
	public void obtenerAsistenciaPorEmpleadoAndFechaTest() {
		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara", "Guzman", "karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Asistencia asistencia = new Asistencia(pruebaEmpleado, "17:08:03", "12/11/2020");

		when(asistenciaRepository.findByEmpleadoAndFecha(pruebaEmpleado, "12/11/2020")).thenReturn(asistencia);
		assertEquals(asistencia, servicio.obtenerAsistenciaPorEmpleadoAndFecha(pruebaEmpleado, "12/11/2020"));

		when(asistenciaRepository.findByEmpleadoAndFecha(pruebaEmpleado, "00/00/0000")).thenReturn(null);
		assertEquals(null, servicio.obtenerAsistenciaPorEmpleadoAndFecha(pruebaEmpleado, "00/00/0000"));
	}

	@Test
	public void actualizarTest() {
		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara", "Guzman", "karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Asistencia asistencia = new Asistencia(pruebaEmpleado, "17:08:03", "12/11/2020");

		when(asistenciaRepository.save(asistencia)).thenReturn(asistencia);
		assertTrue(servicio.actualizar(asistencia));

		when(asistenciaRepository.save(asistencia)).thenReturn(null);
		assertFalse(servicio.actualizar(asistencia));
	}
}
