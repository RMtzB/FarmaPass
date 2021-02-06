package mx.uam.ayd.proyecto.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

@Service
public class ServicioDescuentos {
	
	@Autowired
	private ProductoRepository repProducto;
	/**
	 * Metodo para agregar un descuento a un producto dado
	 * @param nombre Nombre del producto al que se le cambiara el descuento y fecha
	 * @param descuento Porcentaje del descuento que se agregara
	 * @param fecha Fecha hasta la que durara el descuento/
	 * @apiNote Este metodo podria ser utilizado para cambiar un producto a sin descuento
	 * dando como valor a los parametros descuento = "" y fecha = ""
	 * 
	 */
	public void cambiarDescuento(String nombre,String descuento,String fecha) {
		Producto aux=repProducto.findByNombre(nombre);
		aux.setDescuento(descuento);
		aux.setFecha(fecha);
		repProducto.save(aux);
	}
	/**
	 * Este metodo verifica si hay descuentos activos que vencen en este dia
	 * asi mismo los eliminalos descuentos que caigan en este caso
	 */
	public void verificarDescuentosVencidos() {
		Date fecha1 = new Date();
		SimpleDateFormat ff= new SimpleDateFormat("YYYY-MM-DD");
		String fecha= ff.format(fecha1);
		List<Producto> aux=repProducto.findByFecha(fecha);
		for(Producto p:aux ) {
			p.setDescuento("");
			p.setFecha("");
			repProducto.save(p);
		}
		
		
		
	}
}
