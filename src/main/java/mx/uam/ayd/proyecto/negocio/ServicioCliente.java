package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public List<Cliente> buscarClientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		for(Cliente cliente:clienteRepository.findAll())
			clientes.add(cliente);
				
				
		return clientes;
    }
}
