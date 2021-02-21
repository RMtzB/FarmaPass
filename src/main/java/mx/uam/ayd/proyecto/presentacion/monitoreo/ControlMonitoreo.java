package mx.uam.ayd.proyecto.presentacion.monitoreo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import mx.uam.ayd.proyecto.negocio.ServicioAsistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;


/**
 * Clase encargada de controlar la VentanaMonitoreo y comunicarse con la
 * la capa de negocio (ServicioAsistencia)
 * 
 * @author RaulMb
 * @since 15/02/2021
 */
@Component
public class ControlMonitoreo {
	
	@Autowired
	private ServicioAsistencia servicioAsistencia;
	
	@Autowired
	private VentanaMonitoreo ventanaMonitoreo;

	
	
	/**
	 * inicia:
	 * Método encargado de mostrar la ventana de Monitoreo y cargar las asistencias
	 * 
	 * @param empleado
	 */
	public void inicia(Empleado empleado) {
		this.iniciaAsistencias();
	    ventanaMonitoreo.muestra(this);
	}
	
	

	/**
	 * registrarInicio:
	 * Método que guarda la asistencia del empleado
	 * 
	 * @param empleado empleado del cual se requiere registrar la asistencia
	 */
	public void registrarInicio(Empleado empleado) {
		Asistencia asistencia;
		LocalDateTime ahora = LocalDateTime.now();
		String horaInical = ahora.getHour() + ":" + ahora.getMinute() + ":" + ahora.getSecond();
		String fecha = ahora.getDayOfMonth() + "/" + ahora.getMonthValue() + "/" + ahora.getYear();
		
		asistencia = servicioAsistencia.obtenerAsistenciaPorEmpleadoAndFecha(empleado, fecha);
		
		if (empleado.getUsuario() != "alma") {
			if(asistencia == null) {
				while(!servicioAsistencia.registroAsistencia(horaInical, fecha, empleado)) {}
			}
		}
	}
	
	
	
	/**
	 * registrarCerrar:
	 * Método encargado de registrar la hora de salida del empleado
	 * 
	 * @param empleado empleado del cual se requiere registrar la hora de salida
	 */
	public void registrarCerrar(Empleado empleado) {
		LocalDateTime ahora= LocalDateTime.now();
		String horafinal= ahora.getHour() + ":" + ahora.getMinute() + ":" + ahora.getSecond();
		String fecha = ahora.getDayOfMonth() + "/" + ahora.getMonthValue() + "/" + ahora.getYear();
		Asistencia asistencia = servicioAsistencia.obtenerAsistenciaPorEmpleadoAndFecha(empleado, fecha);
		
		if(asistencia != null) {
			asistencia.setHoraFinal(horafinal);
			servicioAsistencia.actualizar(asistencia);
		}
	}

	
	
	/**
	 * iniciaAsistencias:
	 * Metodo encardo de recupera las asistencias y llenar la tabla que las mostrará
	 */
	public void iniciaAsistencias() {
		List <Asistencia> asistencias = servicioAsistencia.recuperarAsistencias();
		
		for(Asistencia asistencia:asistencias) {
			ventanaMonitoreo.llenaTabla(asistencia);
		}
	}
}
