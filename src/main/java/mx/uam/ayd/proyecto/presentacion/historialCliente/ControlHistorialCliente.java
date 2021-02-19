package mx.uam.ayd.proyecto.presentacion.historialCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.Clientes.ControlListaClientes;

@Component
public class ControlHistorialCliente {
	@Autowired
		private VentanaHistorialCliente ventanaHistorialCliente;
	@Autowired
		private ServicioCliente servicioCliente;
	@Autowired
		private ControlListaClientes controlListaClientes;
	
	private int id;
	
	public void inicia(int id,int desc) {
		this.id=id;
		ventanaHistorialCliente.muestra(this,id,desc);
	}
	
	public void llenarTabla(int id) {
		Cliente a = servicioCliente.obtenerCliente(id);
		for(String venta : a.getHistorial()) {
			ventanaHistorialCliente.agregarVenta(venta);
		}
	}
	
	public void refreshTable() {
		ventanaHistorialCliente.limpiarTabla();
		llenarTabla(id);
	}
	
	public void actualizarDescuentoActual(String descuento){
		servicioCliente.actualizarDescuento(id,Integer.parseInt(descuento));
		ventanaHistorialCliente.dispose();
		controlListaClientes.refreshTable();
	}
}