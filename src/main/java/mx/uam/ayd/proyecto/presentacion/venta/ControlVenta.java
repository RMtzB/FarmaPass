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

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
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
	private ServicioCliente servicioCliente;

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
	 * @param cliente
	 */
	public void muentraCobro(float total, Cliente cliente) {
		controlCobro.inicia(total, cliente);
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
	 * Modificado para poder asociar las ventas a los clintes 
	 * @param total
	 */
	public void obtenerLista(float total, Cliente cliente) {
		if (cliente == null) {
			System.out.println("entre al if");
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
			
		
		} else {
			System.out.println("entre else");
			listaProductos = ventanaVenta.recorrerTabla();
			Venta venta = new Venta();
			Calendar fecha = new GregorianCalendar();
			int ano = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			String fechaF = ano + "/" + mes + "/" + dia;
			venta.setFecha(fechaF);
			venta.setTotal(total);
			cliente.agregarVenta(fechaF, total, listaProductos.size());
			System.out.println(cliente.getHistorial().get(0));
			actulizaInventarioMenos(listaProductos);
			servicioDetalleVenta.agregarDetalleVenta(venta, listaProductos);
			controlCobro.muestraDialogo();
			cliente = null;
		}
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

	/**
	 * Método que obtien al cliente por su ID
	 * 
	 * @param total
	 */
	
	public Cliente buscarPorIdCliente(double id) {
		Cliente cliente = new Cliente();
		for (int i = 0; i < servicioCliente.buscarClientes().size(); i++) {
			System.out.println((servicioCliente.buscarClientes().get(i).getNombre()));
			System.out.println((servicioCliente.buscarClientes().get(i).getCorreo()));
			System.out.println((servicioCliente.buscarClientes().get(i).getIdCliente()));
			;
			if (servicioCliente.buscarClientes().get(i).getIdCliente() == id) {
				cliente = servicioCliente.buscarClientes().get(i);
				System.out.println(cliente.getNombre());
				String encontrado = "Cliente encontrado puede proseguir con la venta";
				ventanaVenta.muestraDialogoConMensaje(encontrado);
			}
		}

		if (cliente.getNombre() == null) {
			String noencontrado = "Cliente no encontrado verifique el ID";
			ventanaVenta.muestraDialogoConMensaje(noencontrado);
		}

		return cliente;
	}

	
	
}