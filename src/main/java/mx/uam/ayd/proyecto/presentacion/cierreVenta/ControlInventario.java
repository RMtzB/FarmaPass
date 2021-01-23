package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProductoAInventario;

@Component
public class ControlInventario {
	@Autowired
	private ControlAgregarProductoAInventario addProductControl;
	
	@Autowired
	private VentanaInventario ventanaI;

	@Autowired
	private ServicioProducto servicioProducto;
	
	
	
	public void inicia(Empleado empleado) {
		ventanaI.muestra(this, empleado);
	}
	
	public void llenarTabla() {
		List<Producto> productos = servicioProducto.obtenerProductos();
		
		if(productos.isEmpty()) {
			ventanaI.sinProductos("No hay productos para mostrar");
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
}
