package mx.uam.ayd.proyecto.presentacion.descuentos.EliminarDescuentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioDescuentos;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;

@Component
public class ControlEliminarDescuento {
	@Autowired
	private VentanaEliminarDesuento ventanaEliminarDesucento;
	@Autowired
	private ServicioDescuentos servicioDescuento;
	@Autowired
	private ControlInventario controlInventario;
	
	public void iniciar(String producto,String descuento,String fecha) {
		ventanaEliminarDesucento.mostrar(this,producto,descuento,fecha);
	}

	public void EliminarDescuento(String nombre) {
		servicioDescuento.cambiarDescuento(nombre,"","");
		controlInventario.refreshTable();
	}

}
