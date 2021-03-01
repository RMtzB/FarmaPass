package mx.uam.ayd.proyecto.presentacion.recarga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.cobro.ControlCobro;

/**
 * @author
 */
@Component
public class ControlRecarga {

	@Autowired
	private VentanaRecarga ventanaRecarga;
	
	@Autowired
	private VentanaConfirmacion ventanaConfirmacion;
	
	@Autowired
	private ControlCobro controlCobro;
	
	
	/**
	 * iniciaRecarga: Inicia Historia de Usuario
	 * @param usuario
	 */
	public void iniciaRecarga(String usuario) {
		ventanaRecarga.muestra(this,usuario);
	}
	
	
	/**
	 * iniciaConfirmacion: Abre ventana de confirmacion  pasandole los datos de la recarga 
	 * @param numero
	 * @param compañia
	 * @param monto
	 */
	public void iniciaConfirmacion(int numero, String compañia,int monto,String responsable) {
		ventanaConfirmacion.muestra(this, numero, compañia, monto,responsable);
	}
	
	
	/**
	 * termina: 
	 */
	public void termina() {
		ventanaRecarga.setVisible(false);
	}
	
	
	/**
	 * terminaE: 
	 */
	public void terminaE() {
		ventanaConfirmacion.setVisible(false);
	}
	
	
	/**
	 * iniciaCobro: Pasa el cobro de la recarga
	 * @param monto
	 * @param responsable
	 * @param cliente
	 */
	public void iniciaCobro (int monto, String responsable,Cliente cliente) {
		controlCobro.inicia(monto, responsable, cliente);
	}
}
