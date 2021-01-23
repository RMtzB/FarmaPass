package mx.uam.ayd.proyecto.presentacion.PedidoCliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioDetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoCliente;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.principal.empleado.ControlPrincipalEmpleados;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;
@Component
public class ControlPedidoCliente {

	@Autowired
	private VentanaPedidoCliente ventana;
	
	@Autowired
	private ServicioCliente servicioCliente;
	
	@Autowired 
	private ServicioPedidoCliente servicioPedidoCliente;
	
	@Autowired
	private ServicioDetallePedidoCliente servicioDetallePedidoCliente;

	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlPrincipalEmpleados controlPrincipalEmpleado;
	
	@Autowired
	private ControlPrincipalEncargado controlPrincipalEncargado;

	/**
	 * Inicia el flujo de control de la ventana crear pedido cliente
	 * 
	 */
	public void inicia(Empleado empleado) {
		ventana.muestra(this, empleado);
	}

	public List<Producto> obtenerProductosConReceta() {
		return servicioProducto.obtenerProductosConReceta();
	}

	public void cancelarPedidoCliente(Empleado empleado) {
		if(empleado.getNivel().equals("empleado")) {
			controlPrincipalEmpleado.inicia(empleado);
			ventana.oculta();
		} else if (empleado.getNivel().equals("encargado")) {
			controlPrincipalEncargado.inicia(empleado);
			ventana.oculta();
		}
		
	}

	public Producto obtenerProducto(String nombreProducto) {
		return servicioProducto.buscarProducto(nombreProducto);
		
	}

	public void agregarPedidoCliente(Cliente cliente, List<Producto> listaProductos, List<Integer> listaPiezas,
			Empleado empleado, String fechaCreacion, float precioTotal) {
		int totalProductos = 0;
		int contador = 0;
		for (Integer piezasXProd : listaPiezas) {
			totalProductos += piezasXProd;
		}
		
		servicioCliente.guardarCliente(cliente);
		PedidoCliente pedidoCliente = new PedidoCliente(fechaCreacion, totalProductos, precioTotal);
		servicioPedidoCliente.guardar(pedidoCliente);
		empleado.addPedidoCliente(pedidoCliente);
		servicioEmpleado.guardarEmpleado(empleado);
		cliente.addPedidoCliente(pedidoCliente);
		servicioCliente.guardarCliente(cliente);
		
		for (Producto producto : listaProductos) {
			servicioDetallePedidoCliente.agregarDetallePedidoCliente(pedidoCliente, producto, listaPiezas.get(contador));
			contador++;
		}
		
		ventana.mostrarMensajeExito();
		
	}

}
