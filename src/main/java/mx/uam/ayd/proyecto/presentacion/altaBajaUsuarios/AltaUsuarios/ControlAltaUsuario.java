package mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.AltaUsuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.ControlAltaYBajaUsuarios;
@Component
public class ControlAltaUsuario {
	
	@Autowired
	private VentanaAltaUsuario ventanaAltaUsuario;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlAltaYBajaUsuarios controlAltaYBajaUsuarios;
	
	public void inicia() {
		ventanaAltaUsuario.mostrar(this);
		
	}

	public void agregarUsuario(String nombre, String apellidoP, String apellidoM, String correo, String celular,String usuario, String password) {
		servicioEmpleado.guardarEmpleado(new Empleado(nombre,  apellidoP,  apellidoM,  correo,  celular,"empleado", usuario,  password));
		controlAltaYBajaUsuarios.refreshTable();
	}

}
