package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PedidoProveedorRepository;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
/**
 * Servicio para el repository de Pedido Proveedor
 * @author AKarina
 *
 */
@Service
public class ServicioPedidoProveedor {

	@Autowired
	private PedidoProveedorRepository pedidoProveedorRepository;

	public List<PedidoProveedor> recuperaPedidos() {

		List<PedidoProveedor> pedidos = new ArrayList<>();

		for (PedidoProveedor pedido : pedidoProveedorRepository.findAll()) {
			pedidos.add(pedido);
		}

		return pedidos;
	}
	
	
	public void guardar(PedidoProveedor pedido) {
		pedidoProveedorRepository.save(pedido);
	}

	public List<PedidoProveedor> obtenerPedidosPorFechaCreacion(String fechaF) {
		List<PedidoProveedor> pedidos = pedidoProveedorRepository.findByFechaDeCreacion(fechaF);
		return pedidos;
	}


	public List<PedidoProveedor> obtenerPedidoSinFechaDeRecepcion() {
		String sinFecha = null;
		List<PedidoProveedor> listaPedidosSinFecha = new ArrayList<>();
		List<PedidoProveedor> pedidos = (List<PedidoProveedor>) pedidoProveedorRepository.findAll();
		for (PedidoProveedor pedidoProveedor : pedidos) {
			if(pedidoProveedor.getFechaDeRecepcion() == null) {
				listaPedidosSinFecha.add(pedidoProveedor);
			}
		}
		return listaPedidosSinFecha;
	}
}
