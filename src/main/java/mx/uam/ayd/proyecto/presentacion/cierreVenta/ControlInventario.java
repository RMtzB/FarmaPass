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
import mx.uam.ayd.proyecto.presentacion.modificar.ControlModificarProducto;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProductoAInventario;
import mx.uam.ayd.proyecto.presentacion.descuentos.AgregarDescuento.ControlDescuentos;
import mx.uam.ayd.proyecto.presentacion.descuentos.EliminarDescuentos.ControlEliminarDescuento;

@Component
public class ControlInventario {
	
	@Autowired
	private VentanaCierreVenta ventana;
	
	@Autowired
	private VentanaInventario ventanaI;

	@Autowired
	private ServicioVenta servicioVenta;
	
	@Autowired
	private ControlAgregarProductoAInventario addProductControl;

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
	
	@Autowired
	private ControlDescuentos ctrlDesc;
	
	@Autowired
	private ControlModificarProducto controlmodificar;
	@Autowired
	private ControlEliminarDescuento controlEliminarDescuento;
	
	public void inicia(Empleado empleado) {
		ventanaI.muestra(this, empleado);
	}
	
	public void llenarTabla() {
		List<Producto> productos = servicioProducto.obtenerProductos();
		if(productos.isEmpty()) {
			ventana.sinProductos("No hay productos para mostrar");
		} else {
			for (Producto producto : productos) {
				
				ventanaI.agregaProductos(producto);
			}
		}
	}
	
	public void refreshTable() {
		ventanaI.limpiarTablas();
		llenarTabla();
	}
	
	public void showAddProductWindow() {
		addProductControl.showWindow(this);
	}
	
	public void nuevoDescuento(String nombre,String precio) {
		ctrlDesc.iniciar(nombre,precio);
	}
	
	public void EliminarDescuento(String nombre) {
		Producto aux=servicioProducto.buscarProducto(nombre);
		
		
		controlEliminarDescuento.iniciar(aux.getNombre(),aux.getDescuento(),aux.getFecha());
	}
	
	public Producto buscarProducto(String nombre) {
		return servicioProducto.buscarProducto(nombre);
	}
	
	/***
	 * Inicia historia de usuario 4
	 * @param producto
	 */
	public void iniciaModificar(Producto producto) {
		controlmodificar.iniciaM(producto);
	}
	
	public void termina() {
		ventanaI.setVisible(false);
	}


}
