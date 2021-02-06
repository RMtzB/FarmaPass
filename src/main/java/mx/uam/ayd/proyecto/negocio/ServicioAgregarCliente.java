package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.utils.RegistrarCliente;
import mx.uam.ayd.proyecto.datos.ClienteRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.utils.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioAgregarCliente implements RegistrarCliente.AddClientService{
	@Autowired
	private ClienteRepository clientRepository;

	
	/**
	 * 
	 * Método encargado de verificar si los datos ingresados son correctos.
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param correo
	 * @param numero
	 * @return THERE_ARE_FIELDS_EMPTY = 3 Si alguno de los campos esta vacío.
	 * @return FIELDS_ARE_CORRECT = 0 Si todos los campos son correctos.
	 * @return EMAIL_IS_NOT_EMAIL = 4 Si el email no tiene un formato adecuado.
	 * @return NUMBER_IS_NOT_A_PHONE_NUMBER = 5 Si el número teléfonico no tiene un formato correcto. 
	 */
	@Override
	public int areFieldsCorrect(String nombre, String apellidos, String correo, String numero) {
		if(nombre.isBlank() || apellidos.isBlank() || correo.isBlank() || numero.isBlank()) {
			return Constants.THERE_ARE_FIELDS_EMPTY;
		}
		
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
										"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(correo);
		
		if(matcher.find() == false) {
			return Constants.EMAIL_IS_NOT_EMAIL;
		}
		
		if(numero.length() != 10) {
			return Constants.NUMBER_IS_NOT_A_PHONE_NUMBER;
		}
		
		try {
			Long.parseLong(numero);
		} catch(NumberFormatException e) {
			return Constants.NUMBER_IS_NOT_A_PHONE_NUMBER;
		}
		
		return Constants.FIELDS_ARE_CORRECT;
	}

	
	
	/**
	 * Método encardado de comunicarse con el repositorio para guardar el cliente
	 * 
	 * @param cliente
	 * @return true Si el cliente se guardó correctamente
	 * @return false Si el cliente no pudo ser guardado
	 */
	@Override
	public boolean addClient(Cliente cliente) {
		return clientRepository.save(cliente) != null;
	}
}
