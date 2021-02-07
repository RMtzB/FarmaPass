package mx.uam.ayd.proyecto.presentacion.venta;

/**
 * @author VictorSosa
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.cobro.ControlCobro;
import mx.uam.ayd.proyecto.presentacion.recarga.ControlRecarga;
import mx.uam.ayd.proyecto.presentacion.registrarCliente.ControlAgregarCliente;
import mx.uam.ayd.proyecto.presentacion.venta.busquedaActivo.ControlBusquedaPorActivo;

/*
 * Esta clase lleva el flujo de la ventana de venta
 */

@Component
public class ControlVenta {
	@Autowired
	ControlAgregarCliente addClientControl; 

	@Autowired
	private VentanaVenta ventanaVenta;

	@Autowired
	private VentanaProducto ventanaProducto;

	@Autowired
	private ControlCobro controlCobro;
	
	@Autowired
	private ControlRecarga controlRecarga;

	@Autowired
	private ServicioProducto servicioProducto;

	@Autowired
	private ServicioDetalleVenta servicioDetalleVenta;
	
	@Autowired
	private ControlBusquedaPorActivo controlBusquedaPorActivo;
	
	private List<Producto> listaProductos = new ArrayList<>();

	/**
	 * Inicia la historia de usuario: Agregar productos para la venta
	 * 
	 */
	public void inicia() {
		ventanaVenta.muestra(this);
	}
	/**
	 * Método que invoca al servicio de producto para producto por nombre
	 * 
	 * @param nombre
	 * @return Un objeto de tipo producto si lo encuentra y nulo si lo encuentra.
	 */
	public Producto obtenerProducto(String nombre) {

		try {
			return servicioProducto.buscarProducto(nombre);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * Oculta la venta de descripcion del producto
	 */
	public void termina() {
		ventanaProducto.setVisible(false);
	}

	/**
	 * Método que llena la tabla de la ventana venta.
	 * 
	 * @param producto
	 */

	public void agregarTabla(Producto producto) {

		try {
			ventanaVenta.llenaTabla(producto);
			ventanaVenta.muestraDialogoConMensaje("Producto agregado exitosamente");
		} catch (Exception ex) {
			ventanaVenta.muestraDialogoConMensaje("Error al buscar el Producto");
		}
		termina();
	}

	/**
	 * Método que actualiza invoca al servicio para actualizar el inventario de un
	 * producto
	 * 
	 * @param nombre
	 */

	public void actulizaInventarioMenos(List<Producto> listaProductos) {
		servicioProducto.actualizaInventarioMenos(listaProductos);
	}

	/**
	 * Termina historia de usuario: Agregar productos para la venta.
	 * 
	 * 
	 */

	/**
	 * 
	 * Inicia historia de forma de cobro
	 */

	/**
	 * Método que se comunica con el control cobro para mostrar la ventana.
	 * 
	 * @param total
	 */
	public void muentraCobro(float total) {
		controlCobro.inicia(total);
	}

	/**
	 * Método que obtiene el total de la ventana de venta.
	 * 
	 * @param precio
	 */
	public void total(float precio) {
		ventanaVenta.textTotal(precio);
	}

	/**
	 * Método que obtien los producto de la venta
	 * 
	 * @param total
	 */
	public void obtenerLista(float total) {

		listaProductos = ventanaVenta.recorrerTabla();
		Venta venta = new Venta();
		Calendar fecha = new GregorianCalendar();
		int ano = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String fechaF = ano + "/" + mes + "/" + dia;
		venta.setFecha(fechaF);
		venta.setTotal(total);
		actulizaInventarioMenos(listaProductos);
		servicioDetalleVenta.agregarDetalleVenta(venta, listaProductos);
		controlCobro.muestraDialogo();
	}
	
	public void iniciarecarga() {
		controlRecarga.iniciaRecarga();
	}

	public void limpiarTabla() {
		ventanaVenta.limpia();
	}
	
	public void showAddClientWindow() {
		addClientControl.showWindow(this);
	}

	public void buscarProducto(String nombre) {

		try {
			ventanaProducto.llena(servicioProducto.buscarProducto(nombre));
			ventanaProducto.muestra(this);

		} catch (Exception ex) {
			controlBusquedaPorActivo.inicia(nombre);
		}
	}
}