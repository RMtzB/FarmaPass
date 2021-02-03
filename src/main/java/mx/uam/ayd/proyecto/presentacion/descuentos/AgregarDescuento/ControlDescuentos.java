package mx.uam.ayd.proyecto.presentacion.descuentos.AgregarDescuento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDescuentos;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;

@Component
public class ControlDescuentos {
	@Autowired
	private VentanaDescuentos ventanaDesc;
	@Autowired
	private ServicioDescuentos servDesc;
	@Autowired
	private ControlInventario controlInventario;
	
	public void iniciar(String producto,String precio) {
		ventanaDesc.mostrar(this,producto,precio);
	}
	
	public void agregarDesc(String nombre,String descuento) {
		servDesc.cambiarDescuento(nombre,descuento,"");
		controlInventario.refreshTable();
		ventanaDesc.dispose();
		
	}
	
	public void agregarDescConDuracion(String nombre,String descuento,String fecha) {
		servDesc.cambiarDescuento(nombre,descuento,fecha);		
		controlInventario.refreshTable();
		ventanaDesc.dispose();
	}
	
	
}
