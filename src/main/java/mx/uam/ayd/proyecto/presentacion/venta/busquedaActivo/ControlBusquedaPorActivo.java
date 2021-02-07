package mx.uam.ayd.proyecto.presentacion.venta.busquedaActivo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
@Component
public class ControlBusquedaPorActivo {
	@Autowired
	private VentanaBusquedaPorActivo ventanaBusquedaPorActivo;
	
	@Autowired
	private ServicioProducto servicioProducto;
	@Autowired
	private ControlVenta controlVenta;
	
	public void inicia(String activo) {
		ventanaBusquedaPorActivo.muestra(this,activo);
	}
	
	public boolean llenarTabla(String activo) {
		List<Producto> productos = servicioProducto.obtenerProductosPorActivo(activo);
		if(productos.isEmpty()) {
			return false;
		} else {
			for (Producto producto : productos) {
				
				ventanaBusquedaPorActivo.agregaProductos(producto);
			}
		}
		return true;
	}

	public void agregarElemento(String nombre) {
		Producto aux=servicioProducto.buscarProducto(nombre);
		controlVenta.agregarTabla(aux);
		controlVenta.total(aux.getPrecio());
	}
}
