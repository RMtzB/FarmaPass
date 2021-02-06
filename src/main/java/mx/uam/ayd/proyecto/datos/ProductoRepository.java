package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.DetalleVenta;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/**
 * 
 * @author VictorSosa
 *
 */

public interface ProductoRepository extends CrudRepository <Producto, Long> {
	
	public Producto findByNombre(String nombre);

	public List<Producto> findByVentas(DetalleVenta detalleVenta);

	public List<Producto> findByReceta(String receta);
	
	public List<Producto> findByCompuesto(String compuesto);
	
	public List<Producto> findByFecha(String fecha);
}