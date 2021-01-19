package mx.uam.ayd.proyecto.negocio;

/**
 * @author VictorSosa
 */

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Slf4j
@Service
public class ServicioProducto {

	@Autowired
	private ProductoRepository productoRepository;

	/**
	 * Método que busca el produco en la base de datos
	 * 
	 * @param nombre
	 * @return Regresa un producto si lo encontro y null si no está
	 * @throws IllegalArgumentException si se pasa un null.
	 */
	public Producto buscarProducto(String nombre) {

		Producto producto = productoRepository.findByNombre(nombre);

		if (producto == null) {
			throw new IllegalArgumentException("No se encontro el producto");
		} else {
			log.info("Producto encontrado:" + nombre);
			return producto;
		}

	}

	/**
	 * Método que decrementa la cantidad de piezas de un producto.
	 * 
	 * @param lista de productos de la venta
	 */
	public void actualizaInventarioMenos(List<Producto> listaProductos) {
		try {
			for (Producto producto : listaProductos) {
				producto.setPiezas(producto.getPiezas() - 1);
				productoRepository.save(producto);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se actualizaron las piezas");
		}

	}

	/**
	 * Obtiene los productos de una venta
	 * 
	 * @param detalleVenta detalle de los productos en la venta
	 * @return lista de productos segun la venta.
	 */
	public List<Producto> obtenerProductoPorVenta(DetalleVenta detalleVenta) {
		return productoRepository.findByVentas(detalleVenta);

	}

	public Producto buscarProductoCompuesto(String compuesto) {

		Producto producto = productoRepository.findByCompuesto(compuesto);

		System.out.print(producto);
		if (producto == null) {
			throw new IllegalArgumentException("No se encuentra el producto");
		} else {
			return producto;
		}
	}

	public List<Producto> obtenerProductos() {
		return (List<Producto>) productoRepository.findAll();
	}

	public void guardar(Producto producto) {
		productoRepository.save(producto);
	}

	public List<Producto> obtenerProductosConReceta() {
		String receta = "Si";
		return productoRepository.findByReceta(receta);
	}

	public boolean actualizarProducto(Producto producto) {
		Producto productoN =productoRepository.save(producto);
		if(productoN == null) {
			return false;
		} else {
			return true;
		}
		
	}
}
