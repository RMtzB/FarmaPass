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

	@Override
	public boolean addProduct(String name, String location, float price, String activeSubstance, int pieces, String prescription) {
		return productRepository.save(new Producto(name,
													activeSubstance,
													prescription,
													location,
													price,
													pieces)) != null;
	}
}
