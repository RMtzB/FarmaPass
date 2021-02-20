package mx.uam.ayd.proyecto.presentacion.historialCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.Clientes.ControlListaClientes;
/**
 * Clase de control para la ventana Historial de cliente
 * @author Cristo Carlos Lopez Manzano
 * @since  10/02/2021    
 */
@Component
public class ControlHistorialCliente {
	@Autowired
		private VentanaHistorialCliente ventanaHistorialCliente;
	@Autowired
		private ServicioCliente servicioCliente;
	@Autowired
		private ControlListaClientes controlListaClientes;
	
	private int id;
	/**
	 * inicia : Inicia la ventana VentanaHistorialCliente, tambien se guarda el id del cliente para poder hacer uso de este.
	 * @param id: id de cliente seleccionado, del cual se desplegara la informacion.
	 */
	public void inicia(int id) {
		this.id=id;
		
		ventanaHistorialCliente.muestra(this,id, servicioCliente.getDescuentoDeCliente(id),servicioCliente.getNombreDeCliente(id) );
	}
	/**
	 * llenarTabla : mediante este metodo se solicita el historial de compras de un cliente y, se llena la tabla con esta informacion.
	 * @param id : id de cliente del cual se solicitara el historial de compras.
	 */
	public void llenarTabla(int id) {
		Cliente a = servicioCliente.obtenerCliente(id);
		for(String venta : a.getHistorial()) {
			ventanaHistorialCliente.agregarVenta(venta);
		}
	}
	/**
	 * refreshTable: este metodo re-llena la informacion de la tabla.
	 * se utiliza cuando se han modificado valores de la db y se desean mostrar actualizados
	 */
	public void refreshTable() {
		ventanaHistorialCliente.limpiarTabla();
		llenarTabla(id);
	}
	/**
	 * actualizarDescuentoActual: dado descuento dado se solicita al servicio que se actualice el dato en el repositorio
	 * se utiliza el existente en esta clase, debe ser previamente cargado este dato.
	 * @param descuento : valor del nuevo descuento que se actualizara en la db.
	 * @apiNote se debe verificar previamente que el parametro descuento sea un valor numerico
	 * 			en caso contrario este metodo dara un error no controlado. 
	 */
	public void actualizarDescuentoActual(String descuento){
		servicioCliente.actualizarDescuento(id,Integer.parseInt(descuento));
		ventanaHistorialCliente.dispose();
		controlListaClientes.refreshTable();
	}
}
