package mx.uam.ayd.proyecto.presentacion.agregarProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAgregarProductoInventario;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;
import mx.uam.ayd.proyecto.utils.AgregarProductoAInventario;
import mx.uam.ayd.proyecto.utils.Constants;

/**
 * 
 * @author RaulMb
 *
 */
@Component
public class ControlAgregarProductoAInventario implements AgregarProductoAInventario.ControlAddProduct{
	@Autowired
	private VentanaAgregarProductoAInventario addProductWindow;
	
	@Autowired
	private ServicioAgregarProductoInventario addProductInventoryService;
	
	private ControlInventario inventaryControl;

	/**
	 * Método encargado de cerrar la ventana
	 */
	@Override
	public void closeWindow() {
		addProductWindow.resetWindow();
		addProductWindow.dispose();
	}

	/**
	 * Método encargado de comunicarse con la ventana para mostrarla
	 * 
	 * @param inventaryControl
	 */
	@Override
	public void showWindow(ControlInventario inventaryControl) {
		this.inventaryControl = inventaryControl;
		addProductWindow.show(this);
		addProductWindow.resetWindow();
	}

	/**
	 * Método encargado de comunicarse con el servicio para guardar un producto
	 * 
	 * @param name
	 * @param location
	 * @param price
	 * @param activeSubstance
	 * @param pieces
	 * @param prescription
	 */
	@Override
	public void addProdruct(String name, String location, String price, String activeSubstance, String pieces, boolean prescription) {
		switch(addProductInventoryService.areFieldsCorrect(name, location, price, activeSubstance, pieces)) {
			case Constants.FIELDS_ARE_CORRECT:
				String receta = prescription ? "si" : "no";
				
				if(addProductWindow.showMessagetoConfirmData("Nombre:\n" + name +
																"\nUbicación:\n" + location +
																"\nPrecio:\n$ " + price +
																"\nComponente activo:\n" + activeSubstance +
																"\nPiezas:\n" + pieces +
																"\nNecesita receta:\n" + receta)) {
					
					if(addProductInventoryService.addProduct(new Producto(name,
																	activeSubstance,
																	receta,
																	location,
																	Float.parseFloat(price),
																	Integer.parseInt(pieces)))) {
						
						addProductWindow.showMessage("El producto fue agregado correctamente");
						closeWindow();
						inventaryControl.refreshTable();
					}
				}
			break;
			
			case Constants.PRICE_IS_NOT_A_NUMBER:
				addProductWindow.showMessage("Favor de verificar que el precio sea correcto");
			break;
			
			case Constants.PIECES_IS_NOT_A_INTEGER_NUMBER:
				addProductWindow.showMessage("Favor de verificar que el número de piezas sea correcto");
			break;
			
			case Constants.THERE_ARE_FIELDS_EMPTY:
				addProductWindow.showMessage("Favor de llenar todos los campos");
			break;
		}
	}
}
