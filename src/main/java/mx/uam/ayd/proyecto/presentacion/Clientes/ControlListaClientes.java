package mx.uam.ayd.proyecto.presentacion.Clientes;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.historialCliente.ControlHistorialCliente;

/**
 * @author Cristo Carlos Lopez Manzano
 * @since 10/02/2021
 */
@Component
public class ControlListaClientes {
	@Autowired
	private VentanaListaClientes ventanaListaClientes;
	@Autowired
	private ServicioCliente servicioCliente;
	@Autowired
	private ControlHistorialCliente controlHistorialCliente;

	/**
	 * inicia : inicia la ventana VentanaListaCliente
	 */
	public void inicia() {
		ventanaListaClientes.muestra(this);
	}

	/**
	 * iniciarHistorial : inicializa el historial de un cliente
	 * @param Id : id del cliente del cual se desplegara su informacion e historial
	 */
	public void iniciarHistorial(int Id) {
		controlHistorialCliente.inicia(Id);
	}

	/**
	 * llenarTabla : llena la tabla con la informacion de todos los clientes
	 */
	public void llenarTabla() {
		Iterator<Cliente> it = servicioCliente.obtenerClientes().iterator();
		while (it.hasNext()) {
			ventanaListaClientes.agregaClientes(it.next());
		}
	}

	/**
	 * refreshTable: este metodo re-llena la informacion de la tabla.
	 * se utiliza cuando se han modificado valores de la db y se desean mostrar actualizados
	 */
	public void refreshTable() {
		ventanaListaClientes.limpiarTabla();
		llenarTabla();
	}
}