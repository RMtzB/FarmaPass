
package mx.uam.ayd.proyecto.presentacion.principal.encargado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.GenerarPedido.ControlGenerarPedido;
import mx.uam.ayd.proyecto.presentacion.HistorialDeVentas.ControlHistorialDeVentas;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

@Component
public class ControlPrincipalEncargado {

	@Autowired
	private ControlVenta controlVenta;

	@Autowired
	private VentanaPrincipalEncargado ventana;
	
	@Autowired
	private ControlCierreVenta controlCierreVenta;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;
	
	@Autowired
	private ControlHistorialDeVentas controlHistorialDeVenta;
	
	@Autowired
	private ControlGenerarPedido controlGenerarPedido;
	
	

	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	/**
	 * Método que arranca la historia de usuario "agregar productos para la venta"
	 * 
	 */
	public void agregarProductos() {

		controlVenta.inicia();
	}

	public void iniciaCierreVenta(Empleado empleado) {
		controlCierreVenta.inicia(empleado);
		
	}

	public void cerrarSesion() {
		controlInicioSesion.inicia();
		ventana.oculta();
		
	}

	public void muestraHistorialDeVenta(Empleado empleado) {
		controlHistorialDeVenta.inicia(empleado);
		ventana.oculta();
	}

	public void crearPedido(Empleado empleado) {
		controlGenerarPedido.despliega(empleado);
		
	}

}
