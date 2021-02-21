
package mx.uam.ayd.proyecto.presentacion.inicioSesion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDescuentos;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;
import mx.uam.ayd.proyecto.presentacion.listarUsuarios.ControlListarUsuarios;
import mx.uam.ayd.proyecto.presentacion.monitoreo.ControlMonitoreo;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author humbertocervantes
 *
 */
@Component
public class ControlInicioSesion {

	@Autowired
	private ControlAgregarUsuario controlAgregarUsuario;
	
	@Autowired
	private ControlListarUsuarios controlListarUsuarios;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleados;
	
	@Autowired
	private VentanaInicioSesion ventanaInicioSesion;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlMonitoreo controlmonitoreo;
	
	@Autowired
	private ServicioDescuentos servDesc;
	
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 */
	public void inicia() {
		ventanaInicioSesion.muestra(this);
	}

	/**
	 * Método que arranca la historia de usuario "agregar usuario"
	 */
	public void agregarUsuario() {
		controlAgregarUsuario.inicia();
	}
	
	/**
	 * Método que arranca la historia de usuario "listar usuarios"
	 */
	public void listarUsuarios() {
		controlListarUsuarios.inicia();
	}

	/**
	 * Método encargado de verificar que el usuario y contraseña sean correctos e iniciar sesión,
	 * así como registra la hora de entrada.
	 * 
	 * @param usuario dato ingresado por el "empleado/encargado"
	 * @param password dato ingresado por el "empleado/encargado"
	 */
	public void validaUsuario(String usuario, String password) {
		try {
			Empleado empleado = servicioEmpleado.validaUsuario(usuario, password);
			
			servDesc.verificarDescuentosVencidos();
			
			if(empleado.getNivel().equals("empleado")) {
				controlPrincipalEmpleados.inicia(empleado);
			} else {
				controlPrincipalEncargado.inicia(empleado);
			}
			
			controlmonitoreo.registrarInicio(empleado);
			ventanaInicioSesion.oculta();
		} catch (Exception e) {
			ventanaInicioSesion.muestraErrorPassword(e);
		}
		
	}
}
