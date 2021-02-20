package mx.uam.ayd.proyecto.negocio;

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

	public void guardarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	public List<Cliente> obtenerClientePorPedido(PedidoCliente pedidoCliente) {
		return clienteRepository.findByPedidosCliente(pedidoCliente);
	}

	public Iterable<Cliente> obtenerClientes() {
		return clienteRepository.findAll();
	}

	/**
	 * obtenerCliente : devuelve el Cliente con el id dado.
	 * @param id : id del cliente que sera devuelto.
	 * @return : cliuente con el id dado.
	 * @apiNote returna null en caso de no existir.
	 */
	public Cliente obtenerCliente(int id) {
		return clienteRepository.findById(id);
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
}
