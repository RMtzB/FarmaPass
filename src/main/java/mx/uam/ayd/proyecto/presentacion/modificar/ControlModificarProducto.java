package mx.uam.ayd.proyecto.presentacion.modificar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;

@Component
public class ControlModificarProducto {

	@Autowired
	private VentanaModificarProducto ventanamodificar;
	
	@Autowired
	private ServicioProducto servicioProducto;
	
	@Autowired
	private ControlInventario controlInventario;
	
	public void iniciaM(Producto product) {
		ventanamodificar.muestra(this, product);
	}
	
	/***
	 *  Permite modificar el producto pasando el nombre del producto que esta guardado y los nuevos paramatros que se le podian modificar
	 * @param produco
	 * @param nombre
	 * @param compuesto
	 * @param receta
	 * @param ubicacion
	 * @param precio
	 * @param piezas
	 */
	public void modificar(String produco,String nombre, String compuesto,String receta,String ubicacion, float precio, int piezas) {
		
		servicioProducto.modificar(produco, nombre, compuesto, receta, ubicacion, precio, piezas);
	} 
	
	/***
	 * Permite una vez que se haya modificado los productos actualizar la tabla del inventario
	 */
	public void actualiza() {
		controlInventario.refreshTable();
	}
	
	public void termina() {
		ventanamodificar.setVisible(false);
	}
	
	
}
