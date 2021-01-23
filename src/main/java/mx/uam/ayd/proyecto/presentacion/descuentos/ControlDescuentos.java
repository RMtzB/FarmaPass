package mx.uam.ayd.proyecto.presentacion.descuentos;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControlDescuentos {
	@Autowired
	private VentanaDescuentos ventanaDesc;
	
	public void iniciar(String producto) {
		ventanaDesc.mostrar(this,producto);
	}
	
	public void agregarDesc(int descuento) {
		JOptionPane.showMessageDialog(null, ""+descuento);
		
	}
	
	public void agregarDescConDuracion(int descuento,String fecha) {
		JOptionPane.showMessageDialog(null, descuento+" hasta "+fecha);
	}
	
	
}
