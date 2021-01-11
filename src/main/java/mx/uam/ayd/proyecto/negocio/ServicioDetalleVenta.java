package mx.uam.ayd.proyecto.negocio;

/**
 * @author VictorSosa
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Slf4j
@Service
public class ServicioDetalleVenta {
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private DetalleVentaRepository detalleDeVentaRepository;

	/**
	 * Método que registra los detalles la venta de cada producto 
	 * y las  caracteristicas de la venta.
	 * 
	 * @param venta
	 * @param listaProductos
	 */
	
	/**
	 * El método registra la venta y el detalle de 
	 * la venta que son los productos que se vendieron.
	 * 
	 * @param venta
	 * @param listaProductos
	 */
	public boolean agregarDetalleVenta(Venta venta, List<Producto> listaProductos) {
		
		if(venta == null && listaProductos.size()==0) {
			throw new NullPointerException("Se recibió una venta null y una lista vacia");
		}
		
		ventaRepository.save(venta);
		for (Producto producto : listaProductos) {
			DetalleVenta detalle = new DetalleVenta();
			detalleDeVentaRepository.save(detalle);
			venta.addDetalleVenta(detalle);
			producto.addDetalleVenta(detalle);
			ventaRepository.save(venta);
			productoRepository.save(producto);
		}
		return true;

	}

}
