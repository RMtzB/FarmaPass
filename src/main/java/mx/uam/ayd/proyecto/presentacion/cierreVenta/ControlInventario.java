package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoCliente;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
public class ControlInventario {
	
	@Autowired
	private VentanaCierreVenta ventana;
	
	@Autowired
	private VentanaInventario ventanaI;

	@Autowired
	private ServicioVenta servicioVenta;

	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioPedidoCliente servicioPedidoCliente;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	@Autowired
	private ControlInicioSesion controlInicioSesion;
	
	
	public void inicia2(Empleado empleado) {
		ventanaI.muestra(this, empleado);
	}
	
	public void obtenerProductos2() {
		List<Producto> productos = servicioProducto.obtenerProductos();
			for (Producto producto : productos) {
				ventanaI.agregaProductos(producto);
			}
	}
	

}
