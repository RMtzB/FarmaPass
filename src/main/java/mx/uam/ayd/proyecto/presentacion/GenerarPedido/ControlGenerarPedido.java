package mx.uam.ayd.proyecto.presentacion.GenerarPedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoProveedor;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

/*
 * Esta clase lleva el flujo de la ventana de GenerarPedio
 */

@Component
public class ControlGenerarPedido {

	@Autowired
	private VentanaGenerarPedido ventanaGenerarPedido;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ServicioDetallePedidoProveedor servicioDetallePedidoProveedor;
	
	@Autowired
	private ControlConfirmacionDeGenerarPedido controlConfirmacionDeGenerarPedido;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;
	
	/**
	 * Inicia la historia de usuario:Procedo de generar pedido
	 * 
	 */
	public void despliega(Empleado empleado) {
		ventanaGenerarPedido.inicia(this,empleado);	
	}
	/**
	 * Método que invoca al servio de producto para buscar todosl los productos
	 * 
	 */

	public List<Producto> obtenerProductos() {
		return servicioProducto.obtenerProductos();
	}

	/**
	 * Método que invoca al servio de producto para buscar un producto por nombre
	 * 
	 */

	public Producto obtenerProducto(String nombreProducto) {
		return servicioProducto.buscarProducto(nombreProducto);
	}

	/**
	 * Método que invoca al servicio Pedido Proveedor y al servicioDetallePedidoProveedor para comenzar la generazion
	 * de la tabla de pedidos
	 * @param lista, listaPiezas, fechaDeEntrega, empleado
	 * 
	 */
	public void generarPedidoProveedor(List<Producto> lista, List<Integer> listaPiezas, String fechaDeEntrega, Empleado empleado) {
		int totalpiezas = 0;
		int totalprecio = 0;
		int contador = 0;
		for (Integer integer : listaPiezas) {
			totalpiezas+= integer;
		}
		
		for (Producto producto : lista) {
			totalprecio+= producto.getPrecio();
		}
		
		
		PedidoProveedor pedido1 = new PedidoProveedor(fechaDeEntrega,totalpiezas,totalprecio);
		servicioPedidoProveedor.guardar(pedido1);
		
		for (Producto producto : lista) {
			servicioDetallePedidoProveedor.agregarDetallePedidoProveedor(pedido1, producto, listaPiezas.get(contador));
			contador ++;
		}
		
		controlConfirmacionDeGenerarPedido.inicia(lista,listaPiezas,pedido1,empleado);
		
		
		
	}


	public void limpiarTabla() {
		ventanaGenerarPedido.limpia();
	}


	public void cerrar(Empleado empleado) {
		controlPrincipalEncargado.inicia(empleado);
		ventanaGenerarPedido.oculta();
		
	}
	
	/**
	 * Termina historia de usuario: Forma de Cobro
	 */


}
