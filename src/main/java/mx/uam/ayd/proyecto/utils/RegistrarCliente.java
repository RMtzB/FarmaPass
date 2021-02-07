package mx.uam.ayd.proyecto.utils;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.registrarCliente.ControlAgregarCliente;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;

public class RegistrarCliente {
	public interface AddClientWindow {
		public void show(ControlAgregarCliente addClientControl);
		
		public boolean showMessagetoConfirmData(String mensaje);
		
		public void showMessage(String message);
		
		public void resetWindow();
	}
	
	
	public interface AddClientControl {
		public void closeWindow();
		
		public void showWindow(ControlVenta controlVenta);
		
		public void addCliente(String name, String apellidos, String email, String numero);
	}
	
	
	public interface AddClientService {
		public int areFieldsCorrect(String nombre, String apellidos, String correo, String numero);
		
		public boolean addClient(Cliente cliente);
	}
}
