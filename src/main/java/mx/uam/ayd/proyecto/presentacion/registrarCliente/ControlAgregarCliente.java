package mx.uam.ayd.proyecto.presentacion.registrarCliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAgregarCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.presentacion.venta.ControlVenta;
import mx.uam.ayd.proyecto.utils.Constants;
import mx.uam.ayd.proyecto.utils.RegistrarCliente;

@Component
public class ControlAgregarCliente implements RegistrarCliente.AddClientControl {
	@Autowired
	private VentanaAgregarCliente addClientWindow;
	
	@Autowired
	private ServicioAgregarCliente addClientService;
	
	private ControlVenta controlVenta;
	
	
	/**
	 * Método encargado de cerrar la ventana
	 */
	@Override
	public void closeWindow() {
		addClientWindow.resetWindow();
		addClientWindow.dispose();
	}

	
	
	/**
	 * Método encargado de comunicarse con la ventana para mostrarla
	 * 
	 * @param controlVenta
	 */
	@Override
	public void showWindow(ControlVenta controlVenta) {
		this.controlVenta = controlVenta;
		addClientWindow.show(this);
		addClientWindow.resetWindow();
	}


	
	/**
	 * Método encargado de comunicarse con el servicio para guardar un cliente
	 * 
	 * @param name
	 * @param apellidos
	 * @param email
	 * @param number
	 */
	@Override
	public void addCliente(String name, String apellidos, String email, String number) {
		switch(addClientService.areFieldsCorrect(name, apellidos, email, number)) {
		case Constants.FIELDS_ARE_CORRECT:
			if(addClientWindow.showMessagetoConfirmData("Nombre:\n" + name +
														"\nApellidos:\n" + apellidos +
														"\nCorreo:\n" + email +
														"\nTeléfono:\n" + number)) {
				String[] apellido = apellidos.split(" ");
				String lastName = apellido.length == 2 ? apellido[1] : "";
				
				if(addClientService.addClient(new Cliente(name,
															apellido[0],
															lastName, 
															email,
															number))) {
					
					addClientWindow.showMessage("El cliente fue guardado correctamente");
					closeWindow();
				}
			}
		break;
		case Constants.THERE_ARE_FIELDS_EMPTY:
			addClientWindow.showMessage("Favor de llenar todos los campos");
		break;
		case Constants.EMAIL_IS_NOT_EMAIL:
			addClientWindow.showMessage("Favor de verificar que el correo electronico sea correcto");
		break;
		case Constants.NUMBER_IS_NOT_A_PHONE_NUMBER:
			addClientWindow.showMessage("Favor de verificar que el número telefónico sea correcto");
		break;
		}
	}

}
