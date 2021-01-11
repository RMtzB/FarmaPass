package mx.uam.ayd.proyecto.presentacion.HistorialDeVentas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.ServicioVenta;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.HistorialDeVentas.ControlHistorialDeVentas;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

@Component
/*
 * Esta clase lleva el flujo de la ventana de Historial de Ventas
 */
public class ControlHistorialDeVentas {

	@Autowired
	private ServicioProducto servicioProducto;

	@Autowired
	private ServicioDetalleVenta servicioDetalleVenta;

	@Autowired
	private VentanaHistorialDeVentas ventanaHistorialDeVentas;

	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;

	/**
	 * Inicia la historia de usuario: Historial de ventas
	 * 
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventanaHistorialDeVentas.despliega(this, empleado);
	}
	/**
	 * MÃ©todo que busca invoca al servio de venta y producto para buscar las ventas por
	 * fecha y así poder obtener los productos asociados a esas ventas.
	 * 
	 * @param fechaF
	 */
	public void obtenerVentas(String fechaF) {
		List<Venta> listaVentas = servicioVenta.obtenerVentasPorFecha(fechaF);
		List<Producto> productos = new ArrayList<>();
		for (Venta venta : listaVentas) {
			for (DetalleVenta detalleVenta : venta.getVentas()) {
				productos = servicioProducto.obtenerProductoPorVenta(detalleVenta);
				ventanaHistorialDeVentas.agregarAtabla(venta, productos);
			}
		}
	}
	
	/**
	 * MÃ©todo que busca invoca al servio de venta y producto para buscar todas las ventas 
	 * y así poder obtener los productos asociados a esas ventas.
	 * 
	 * 
	 */
	public void obtenerTodasLasVentas() {
		List<Venta> listaVentas = servicioVenta.recuperaTodasLasVentas();
		List<Producto> productos = new ArrayList<>();
		for (Venta venta : listaVentas) {
			for (DetalleVenta detalleVenta : venta.getVentas()) {
				productos = servicioProducto.obtenerProductoPorVenta(detalleVenta);
				ventanaHistorialDeVentas.agregarAtabla(venta, productos);
			}
		}

	}
	/**
	 *Oculta la ventana de historial de ventas
	 * 
	 * 
	 */
	public void cerrar(Empleado empleado) {
		controlPrincipalEncargado.inicia(empleado);
		ventanaHistorialDeVentas.oculta();
	}
	
	/**
	 * Termina historia de usuario: Forma de Cobro
	 */

}
