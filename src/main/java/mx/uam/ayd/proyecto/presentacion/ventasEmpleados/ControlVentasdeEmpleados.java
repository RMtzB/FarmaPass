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

/**
 * Control para ventas de empleado
 * @author David Castellanos
 * @since 16/02/2021
 *
 */
@Component
public class ControlVentasdeEmpleados {
	
	@Autowired
	private VentanaVentasdeEmpleados ventanaVentasdeEmpleados;
	
	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ControlAltaYBajaUsuarios controlAltaUsuario;
	
	/**
	 * inicia ventana de ventas de empleado
	 * @param empleado: trae al empleado que fue seleccionado 
	 */
	public void inicia(Empleado empleado){
		ventanaVentasdeEmpleados.muestra(this, empleado);
	}

	/***
	 * Recupera ventas(en una lista) del empleado seleccionado pasando el nombre de usuario(manera mas rapida de distinguir las ventas)
	 * @param usuario: es el usuario que tiene el empleado 
	 */
	public void obtenerVentasEmpleado(String usuario) {
		List<Venta> ventasEmpleado = servicioVenta.obtenerVentasPorEmpleado(usuario);
		
		if(ventasEmpleado.isEmpty()) {
			ventanaVentasdeEmpleados.sinProductos("No hay productos para mostrar");
		}else {
			for (Venta venta : ventasEmpleado){ 
				ventanaVentasdeEmpleados.agregaVentasdeEmpleados(venta);
			}
		}
	}
	
	/**
	 * Regresa lista de ventas por la fecha que se pasa por parametro del empleado/usuario seleccionado
	 * @param fechaF: Fecha en la que quiere esas ventas
	 * @param usuario: Ventas con el usuario/responsable correspondiente
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
