package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Clase encargada de comunicarse con la capa de datos (AsistenciaRepository)
 * 
 * @author Raul Mb
 * @since 15/02/2021
 */
@Service
public class ServicioAsistencia {

	@Autowired
	private AsistenciaRepository asistenciaRepository;

	/**
	 * registroAsistencia: Método encargado de registrar la hora de entrada de un
	 * empleado
	 * 
	 * @param horaInicial
	 * @param fecha
	 * @param empleado
	 * @return true si la asistencia se guardó correctamente, false en caso
	 *         contrario
	 */
	public boolean registroAsistencia(String horaInicial, String fecha, Empleado empleado) {
		Asistencia asistencia = new Asistencia(empleado, horaInicial, fecha);

		return asistenciaRepository.save(asistencia) != null;
	}

	/**
	 * recuperarAsistencias: Recupera todas las asistencias guardadas
	 * 
	 * @return asistencias lista con todos los registros obtenidos
	 */
	public List<Asistencia> recuperarAsistencias() {
		List<Asistencia> asistencias = new ArrayList<>();

		for (Asistencia asistencia : asistenciaRepository.findAll()) {
			asistencias.add(asistencia);
		}

		return asistencias;
	}

	/**
	 * obtenerAsistenciaPorEmpleadoAndFecha: Método encargado de recuperar la
	 * asistencia de un empleado en determinada fecha
	 * 
	 * @param empleado
	 * @param fecha
	 * @return Asistencia el registro encontrado(para tal empleado, y dicha fecha)
	 */
	public Asistencia obtenerAsistenciaPorEmpleadoAndFecha(Empleado empleado, String fecha) {
		return asistenciaRepository.findByEmpleadoAndFecha(empleado, fecha);
	}

	/**
	 * actualizar: Método encargado de modificar un registro de asistencia
	 * 
	 * @param asistenciaAEditar Asistencia modificada
	 * @return true si la asistencia se modificó correctamente, false en caso
	 *         contrario
	 */
	public boolean actualizar(Asistencia asistenciaAEditar) {
		return asistenciaRepository.save(asistenciaAEditar) != null;
	}
}
