package mx.uam.ayd.proyecto.presentacion.ventasEmpleados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Component
public class ControlVentasdeEmpleados {
	
	@Autowired
	private VentanaVentasdeEmpleados ventanaVentasdeEmpleados;
	
	public void inicia(Empleado product) {
		ventanaVentasdeEmpleados.muestra(this, product);
	}

}
