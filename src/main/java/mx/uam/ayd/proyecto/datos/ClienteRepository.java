package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	public List<Cliente> findByPedidosCliente(PedidoCliente pedidoCliente);
	public Iterable<Cliente> findAll();
	public Cliente findById(long id);
}
