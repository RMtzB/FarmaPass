package mx.uam.ayd.proyecto.presentacion.cobro;

/**
 * @author VictorSosa
 */

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
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
	 * Inicia: Metododo que hace el cobro modificado para pasar al cliente (si es necesario) y al empleado para asociarles su respectiva venta
     * @param total: Representa el precio total de la compra
	 * @param responsable: Representa el nombre del empleado quien esta haciendo la venta
	 * @param cliente: Representa al cliente al que se le esta asociando la venta (si se le asocia)
	 */


	public void inicia(float total, String responsable, Cliente cliente) {
		ventanaCobro.muestra(this, total, responsable, cliente);
	}
	
	/**
	 * 
	 * Inicia historia de usuario: Forma de cobro
	 */
//	public void inicia(float total, String responsable) {
//		ventanaCobro.muestra(this, total,responsable);
//	}

	public void termina() {
		ventanaCobro.setVisible(false);
	}

	/**
	 * obtenerLista: Metodo que se encarga de llamar al metodo que hace lo necesario para realizar una venta y asociarla si es necesario
	 * 
	 * @param total: Representa el precio total de la compra
	 * @param responsable: Representa el nombre del empleado quien esta haciendo la venta
	 * @param cliente: Representa al cliente al que se le esta asociando la venta (si se le asocia)
	 */

	public void obtenerLista(float total, String responsable, Cliente cliente) {
		controlVenta.obtenerLista(total, responsable, cliente);
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

	public void recibirCliente(Cliente cliente) {
		
	}

	public void terminaTodo() {
		controlVenta.termina();
		ventanaCobro.dispose();
	}
	

}
