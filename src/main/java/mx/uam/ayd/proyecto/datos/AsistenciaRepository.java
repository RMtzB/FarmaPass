package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

/**
 * Repositorio para Asistencias
 * 
 * @author Raul Mb
 * @since 15/02/2021
 */
public interface AsistenciaRepository extends CrudRepository<Asistencia, Long> {
	/**
	 * findByEmpleado:
	 * Método encargado de obtener de la BD los registros de asistencia de un empleado
	 * 
	 * @param empleado empleado del cual se requiere obtener los registros
	 * @return List<Asistencia> lista con todos lo registros encontrados (de tal empleado)
	 */
	public List<Asistencia> findByEmpleado(Empleado empleado);
	
	
	
	/**
	 * findByEmpleadoAndFecha:
	 * Método encargado de obtener de la BD el registro de asistencia de un empleado en
	 * una fecha determinada 
	 * 
	 * @param empleado empleado del cual se requiere obtener el registro
	 * @param fecha la fecha del registro que se requiere obtener
	 * @return Asistencia la asistencia encontrada (de tal empleado)
	 */
	public Asistencia findByEmpleadoAndFecha(Empleado empleado, String fecha);
}
