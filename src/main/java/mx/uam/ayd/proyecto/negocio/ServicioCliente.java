package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;

@Service
public class ServicioCliente {
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Guarda cambios en un cliente
	 * @param cliente entidad de cliente a guardar
	 */
	public void guardarCliente(Cliente cliente) {
		clienteRepository.save(cliente); 
	}

	public List<Cliente> obtenerClientePorPedido(PedidoCliente pedidoCliente) {
		return clienteRepository.findByPedidosCliente(pedidoCliente);
	}
	public Iterable<Cliente> obtenerClientes(){
		return clienteRepository.findAll();
	}
	public Cliente obtenerCliente(int id) {
		return clienteRepository.findById(id);
	}
	
	/**
	*	eliminarCliente: Elimina un cliente seleccionado de acuerdo a su identificador. 
	*	@param idSeleccionado: Es un entero que corresponde al identificador del cliente.
	*/
	public void eliminarCliente(int id) {
			clienteRepository.delete(obtenerCliente(id));
	}
	
	public void actualizarDescuento(int id,int desc) {
		Cliente aux = clienteRepository.findById(id);
		aux.setDescuentoCliente(desc);
		clienteRepository.save(aux);
	}
}
