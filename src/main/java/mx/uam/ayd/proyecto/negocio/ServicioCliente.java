package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
@Service
public class ServicioCliente {
	@Autowired
	private ClienteRepository clienteRepository;

  /**
	 * Guarda cambios en un cliente
	 * 
	 * @param cliente entidad de cliente a guardar
	 */
  
	public void guardarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	public List<Cliente> obtenerClientePorPedido(PedidoCliente pedidoCliente) {
		return clienteRepository.findByPedidosCliente(pedidoCliente);
	}
	
	/**
	 * Guarda al cliente si es que se le asocio la venta 
	 * 
	 * @param cliente entidad de cliente a guardar
	 */
	
	public boolean guardarCompraCliente(Cliente cliente) {
		return clienteRepository.save(cliente) != null;
	}

	public Iterable<Cliente> obtenerClientes() {
		return clienteRepository.findAll();
	}

	/**
	 * obtenerCliente: devuelve el Cliente con el id dado.
	 * @param id: id del cliente que sera devuelto.
	 * @return Cliente: cliuente con el id dado.
	 * @apiNote returna null en caso de no existir.
	 */
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

	/**
	 * actualizarDescuento : cambia el valor del descuento de cliente del cliente con el id dado
	 * @param id : id del cliente del cual se cambiara el descuento
	 * @param desc : valor del nuevo descuento, el cual sera guardado
	 */
	public void actualizarDescuento(int id, int desc) {
		Cliente aux = clienteRepository.findById(id);
		aux.setDescuentoCliente(desc);
		clienteRepository.save(aux);
	}

	/**
	 * getDescuentoDeCliente : devuelve el descuento que tiene el cliente que tiene el indice dado
	 * @param id : id del cliente del cual se obtendra el descuento actual 
	 * @return : descuento del cliente indicado
	 */
	public int getDescuentoDeCliente(int id) {
		return clienteRepository.findById(id).getDescuentoCliente();
	}

	/**
	 * getNombreDeCliente : devuelve el nombre del cliente que tiene el indice dado 
	 * @param id : id del cliente del cual se obtendra el nombre
	 * @return : nombre del cliente indicado 
	 */
	public String getNombreDeCliente(int id) {
		Cliente aux = clienteRepository.findById(id);
		return aux.getNombre() + " " + aux.getApellidoPaterno();
	}
  
  /**
	 * Agregar un pedido cliente y lo relaciona con el cliente
	 * 
	 * @param pedidoCliente pedido cliente a relacionar
	 * @return regresa true si el pedido cliente se agrego correctamente y false si
	 *         no
	 * @throws IllegalArgumentException si el pedido cliente es nulo
	 */
  
  public List<Cliente> buscarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		for(Cliente cliente:clienteRepository.findAll())
			clientes.add(cliente);
				
				
		return clientes;
    }
}

