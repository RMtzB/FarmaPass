package mx.uam.ayd.proyecto.presentacion.Clientes;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.historialCliente.ControlHistorialCliente;

@Component
public class ControlListaClientes {
	@Autowired
	private VentanaListaClientes ventanaListaClientes;
	@Autowired
	private ServicioCliente servicioCliente;
	@Autowired
	private ControlHistorialCliente controlHistorialCliente;

	public void inicia() {
		ventanaListaClientes.muestra(this);
	}
	
	public void iniciarHistorial(int Id, int desc) {
		controlHistorialCliente.inicia(Id,desc);
	}
	
	public void llenarTabla() {
		Iterator<Cliente> it=servicioCliente.obtenerClientes().iterator();
		while(it.hasNext()){
			ventanaListaClientes.agregaProductos(it.next());
		}
	}
	
	public void refreshTable() {
		ventanaListaClientes.limpiarTabla();
		llenarTabla();
	}
}