package mx.uam.ayd.proyecto.presentacion.cobro;

/**
 * @author VictorSosa
 */

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.recarga.ControlRecarga;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

/*
 * Esta clase lleva el flujo del cobro
 */
@Component
public class ControlCobro {

	@Autowired
	private VentanaCobro ventanaCobro;
	@Autowired
	private ControlVenta controlVenta;
	
	@Autowired
	private ControlRecarga controlRecarga;

	/**
	 * 
	 * Inicia historia de usuario: Forma de cobro
	 */
	public void inicia(float total, String responsable) {
		ventanaCobro.muestra(this, total,responsable);
	}

	public void termina() {
		ventanaCobro.setVisible(false);
	}

	/**
	 * Método para obtener un lista de los productos de la venta.
	 * 
	 * @param total
	 */
	public void obtenerLista(float total,String responsable) {
		controlVenta.obtenerLista(total,responsable);
	}
	/**
	 * Método que muestra los dialogos de la ventana
	 */
	public void muestraDialogo() {
		ventanaCobro.muestraDialogo();
	}
	/**
	 * Método que invoca al método limpiar tabla del control venta, 
	 * para limpiar la tabla de la ventana venta,
	 */
	public void limpiarTabla() {
		controlVenta.limpiarTabla();
		
	}

	/**
	 * Termina historia de usuario: Forma de Cobro
	 */
	
	
	/**
	 * Termina/cierra las ventanas para una recarga y acaba HU-6
	 */
	public void terminaRecarga() {
		controlRecarga.terminaE();
		controlRecarga.termina();
		
	}

}
