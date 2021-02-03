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
	
	public void cambiarDescuento(String nombre,String descuento,String fecha) {
		Producto aux=repProducto.findByNombre(nombre);
		aux.setDescuento(descuento);
		aux.setFecha(fecha);
		repProducto.save(aux);
	}
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
