package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.utils.AgregarProductoAInventario;
import mx.uam.ayd.proyecto.utils.Constants;

@Service
public class ServicioAgregarProductoInventario implements AgregarProductoAInventario.ServicioAddProduct {
	@Autowired
	private ProductoRepository productRepository;
	
	/**
	 * Método encargado de verificar si los datos ingresados son correctos
	 * 
	 * @param name
	 * @param location
	 * @param price
	 * @param activeSubstance
	 * @param pieces
	 * @return THERE_ARE_FIELDS_EMPTY = 3 Si alguno de los campos
	 * name, location, componente activo esta vacío.
	 * @return PRICE_IS_NOT_A_NUMBER = 1 Si el campo precio no es un número o esta vacío
	 * @return PIECES_IS_NOT_A_INTEGER_NUMBER = 2 Si el campo piezas 
	 * es un número entero o esta vacío
	 * @return FIELDS_ARE_CORRECT = 0 Si todos los campos son correctos
	 */
	@Override
	public int areFieldsCorrect(String name, String location, String price, String activeSubstance, String pieces) {
		
		if(name.isBlank() || location.isBlank() || activeSubstance.isBlank())
			return Constants.THERE_ARE_FIELDS_EMPTY;
		
		try {
			Float.parseFloat(price);
		} catch(NumberFormatException e) {
			return Constants.PRICE_IS_NOT_A_NUMBER;
		}
		
		try {
			Integer.parseInt(pieces);
		} catch(NumberFormatException e) {
			return Constants.PIECES_IS_NOT_A_INTEGER_NUMBER;
		}
		
		return Constants.FIELDS_ARE_CORRECT;
	}

	/**
	 * Método encardado de comunicarse con el repositorio para guardar el producto
	 * 
	 * @param product
	 * @return true Si el producto se guardó correctamente
	 * @return false Si el producto no pudo ser guardado
	 */
	@Override
	public boolean addProduct(Producto product) {
		return productRepository.save(product) != null;
	}
}
