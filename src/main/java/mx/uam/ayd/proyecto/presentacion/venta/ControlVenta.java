package mx.uam.ayd.proyecto.presentacion.venta;

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
 * Esta clase lleva el flujo de VentanaVenta
 * 
 * @author VictorSosa
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
	public void inicia(String nom) {
		ventanaVenta.muestra(this,nom);
	}

	
	/**
	 * obtenerProducto: Método que invoca al servicio de producto para producto por nombre
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
	 * termina: Oculta la venta de descripcion del producto
	 */
	public void termina() {
		//limpiarTabla();
		ventanaProducto.setVisible(false);
	}

	
	/**
	 * agregarTabla: Método que llena la tabla de la ventana venta.
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
	 * actulizaInventarioMenos: Método que actualiza invoca al servicio para actualizar el inventario de un
	 * producto
	 * 
	 * @param actulizaInventarioMenos
	 */
	public void actulizaInventarioMenos(List<Producto> actulizaInventarioMenos) {
		servicioProducto.actualizaInventarioMenos(listaProductos);
	}
	
	
	/**
	 * muentraCobro: Inicia ventana de cobro pasando el total de ventas y el responsable de venta
	 * 
	 * @param total
	 * @param responsabl
	 */
	public void muentraCobro(float total,String responsabl, Cliente cliente) {
		controlCobro.inicia(total,responsabl, cliente);
	}


	/**
	 * total: Método que obtiene el total de la ventana de venta.
	 * 
	 * @param precio
	 */
	public void total(float precio) {
		ventanaVenta.textTotal(precio);
	}



/**
	 * Método para obtener una lista de los productos de la venta modificado para pasar el cliente de ser necesario y el empleado para poder asociar la venta y mostrarlas despues
	 * 
	 * @param total,responsable,cliente
	 */

	public void obtenerLista(float total, String responsable, Cliente cliente) {
		listaProductos = ventanaVenta.recorrerTabla();
		Venta venta = new Venta();
		Calendar fecha = new GregorianCalendar();
		int ano = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH)+1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String fechaF = dia + "/" + mes + "/" + ano;
		venta.setFecha(fechaF);
		venta.setTotal(total);
		venta.setCantidad(listaProductos.size());
		venta.setResponsable(responsable);
		actulizaInventarioMenos(listaProductos);
		servicioDetalleVenta.agregarDetalleVenta(venta, listaProductos);
		
		if (cliente != null) {
			cliente.agregarVenta(fechaF, total, listaProductos.size());
			servicioCliente.guardarCompraCliente(cliente);
			//venta terminada y guardada 
			cliente = null;
		}
		
		controlCobro.muestraDialogo();
	}
	

	/**
	 * iniciarecarga: 
	 * 
	 * @param usuario
	 */
	public void iniciarecarga(String usuario) {
		controlRecarga.iniciaRecarga(usuario);
	}

	
	/**
	 * limpiarTabla: 
	 */
	public void limpiarTabla() {
		ventanaVenta.limpia();
	}

	
	/**
	 * showAddClientWindow: 
	 */
	public void showAddClientWindow() {
		addClientControl.showWindow(this);
	}

	
	/**
	 * buscarProducto: 
	 * @param nombre
	 */
	public void buscarProducto(String nombre) {

		try {
			ventanaProducto.llena(servicioProducto.buscarProducto(nombre));
			ventanaProducto.muestra(this);

		} catch (Exception ex) {
			controlBusquedaPorActivo.inicia(nombre);
		}
	}

	
	/**
	 * buscarPorIdCliente: Método que a partir del ID del cliente lo busca y lo regresa de entre todos los clientes registrados
	 * 
	 * @param id
	 */
	public Cliente buscarPorIdCliente(int id) {
		Cliente cliente = servicioCliente.obtenerCliente(id);
		String mensaje;
		
		if(cliente != null) {
			mensaje = "Cliente encontrado puede proseguir con la venta";
		} else {
			mensaje = "Cliente no encontrado verifique el ID";
		}
		
		ventanaVenta.muestraDialogoConMensaje(mensaje);
		
		return cliente;
	}
}