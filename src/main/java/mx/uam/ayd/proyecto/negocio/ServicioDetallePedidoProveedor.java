package mx.uam.ayd.proyecto.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.DetallePedidoProveedorRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.DetallePedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Service
public class ServicioDetallePedidoProveedor {
	@Autowired
	private DetallePedidoProveedorRepository detallePedidoProveedorRepository;
	
	@Autowired
	private ServicioPedidoProveedor servicioPedidoProveedor;
	
	@Autowired
	private ServicioProducto servicioProducto;

	public void agregarDetallePedidoProveedor(PedidoProveedor pedidoProveedor, Producto producto, int piezas) {
		DetallePedidoProveedor detallePedidoProveedor = new DetallePedidoProveedor();
		detallePedidoProveedor.setProducto(producto);
		detallePedidoProveedor.setNumeroPiezas(piezas);
		detallePedidoProveedorRepository.save(detallePedidoProveedor);
		pedidoProveedor.addDetallePedidoProveedor(detallePedidoProveedor);
		servicioPedidoProveedor.guardar(pedidoProveedor);
		servicioProducto.guardar(producto);
	}
}
