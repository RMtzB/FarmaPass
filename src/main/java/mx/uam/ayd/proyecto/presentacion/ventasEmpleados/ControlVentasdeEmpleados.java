package mx.uam.ayd.proyecto.presentacion.ventasEmpleados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.ControlAltaYBajaUsuarios;
import mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.AltaUsuarios.ControlAltaUsuario;

@Component
public class ControlVentasdeEmpleados {
	
	@Autowired
	private VentanaVentasdeEmpleados ventanaVentasdeEmpleados;
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ControlAltaYBajaUsuarios controlAltaUsuario;
	
	/**
	 * inicia ventana e ventas de empleado
	 * @param product
	 */
	public void inicia(Empleado product) {
		ventanaVentasdeEmpleados.muestra(this, product);
	}

	/***
	 * Recupera ventas(en una lista) del empleado seleccionado pasando el nombre de usuario(manera mas rapida de distinguir las ventas)
	 * @param usuario
	 */
	public void obtenerVentasEmpleado(String usuario) {
		List<Venta> ventasEmpleado = servicioVenta.obtenerVentasPorEmpleado(usuario);
		
		if(ventasEmpleado.isEmpty()) {
			ventanaVentasdeEmpleados.sinProductos("No hay productos para mostrar");
		} else {
			for (Venta venta : ventasEmpleado) { 
				ventanaVentasdeEmpleados.agregaVentasdeEmpleados(venta);
			}
		}
	}
	
	/**
	 * Regresa lista de ventas por la fecha que se pasa por parametro del empleado/usuario seleccionado
	 * @param fechaF
	 * @param usuario
	 */
	
	public void obtenerVentas(String fechaF,String usuario) {
		List<Venta> ventasEmpleado = servicioVenta.obtenerVentasPorEmpleado(usuario);
		
		if(ventasEmpleado.isEmpty()) {
			ventanaVentasdeEmpleados.sinProductos("No hay productos para mostrar");
		} else {
			for (Venta ventaF : ventasEmpleado) { 
					
					if(ventaF.getFecha().equals(fechaF)) {
						System.out.println(ventaF.getFecha()+" "+fechaF);
						ventanaVentasdeEmpleados.agregaVentasdeEmpleados(ventaF);
					}
				}
			}
		
	}
	/**
	 * Cierra ventana
	 */
	public void termina() {
		ventanaVentasdeEmpleados.setVisible(false);
	}
	
	public void terminaVentas() {
		ventanaVentasdeEmpleados.setVisible(false);
		controlAltaUsuario.termina();
	}
	
	/**
	 * Termina Historia de usuario 8
	 */
}