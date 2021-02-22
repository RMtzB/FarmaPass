package mx.uam.ayd.proyecto.presentacion.recarga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
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
	public void iniciaRecarga(String usuario) {
		ventanaRecarga.muestra(this,usuario);
	}
	
	/**
	 * Abre ventana de confirmacion  pasandole los datos de la recarga 
	 * @param numero
	 * @param compañia
	 * @param monto
	 */
	public void iniciaConfirmacion(int numero, String compañia,int monto,String responsable) {
		ventanaConfirmacion.muestra(this, numero, compañia, monto,responsable);
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
	public void iniciaCobro (int monto,Cliente cliente) {
		controlCobro.inicia(monto, cliente);
	}
	
	/**
	 * Pasa el cobro de la recarga
	 * @param monto
	 */
	public void iniciaCobro (int monto, String usuario) {
		controlCobro.inicia(monto, usuario);
	}
}
