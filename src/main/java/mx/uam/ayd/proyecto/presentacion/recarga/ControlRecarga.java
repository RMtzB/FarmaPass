package mx.uam.ayd.proyecto.presentacion.recarga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.cobro.ControlCobro;

@Component
public class ControlRecarga {

	@Autowired
	private VentanaRecarga ventanaRecarga;
	
	@Autowired
	private VentanaConfirmacion ventanaConfirmacion;
	
	@Autowired
	private ControlCobro controlCobro;
	
	/**
	 * Inicia Historia de Usuario 4
	 */
	public void iniciaRecarga() {
		ventanaRecarga.muestra(this);
	}
	
	public void iniciaConfirmacion(int numero, String compañia,int monto) {
		ventanaConfirmacion.muestra(this, numero, compañia, monto);
		
	}
	
	public void termina() {
		ventanaRecarga.setVisible(false);
	}
	public void terminaE() {
		ventanaConfirmacion.setVisible(false);
	}
	/**
	 * Pasa el cobro de la recarga
	 * @param monto
	 */
	public void iniciaCobro (int monto) {
		controlCobro.inicia(monto);
	}
	/**
	 * Termina historia de Usuario 4
	 */
}
