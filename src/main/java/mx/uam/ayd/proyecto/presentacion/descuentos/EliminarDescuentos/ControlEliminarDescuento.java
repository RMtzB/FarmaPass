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
	
	/**
	 * Inicia la ventana Eliminar cargandola con los datos del producto seleccionado
	 * @param producto - Nombre del producto seleccionado
	 * @param descuento - Cantidad del descuento actual del producto
	 * @param fecha - Fecha definida en la que este terminara
	 */
	public void iniciar(String producto,String descuento,String fecha) {
		ventanaEliminarDesucento.mostrar(this,producto,descuento,fecha);
	}
	/**
	 * Elimina el descuento del producto definido
	 * @param nombre - Nombre del producto del cual se eliminara el descuento
	 */
	public void EliminarDescuento(String nombre) {
		servicioDescuento.cambiarDescuento(nombre,"","");
		controlInventario.refreshTable();
	}

}
