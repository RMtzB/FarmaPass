package mx.uam.ayd.proyecto.presentacion.agregarProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioAgregarProductoInventario;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;
import mx.uam.ayd.proyecto.utils.AgregarProductoAInventario;
import mx.uam.ayd.proyecto.utils.Constants;

@Component
public class ControlAgregarProductoAInventario implements AgregarProductoAInventario.ControlAddProduct{
	@Autowired
	private VentanaAgregarProductoAInventario addProductWindow;
	
	@Autowired
	private ServicioAgregarProductoInventario addProductInventoryService;
	
	private ControlInventario inventaryControl;

	@Override
	public void closeWindow() {
		addProductWindow.resetWindow();
		addProductWindow.dispose();
	}

	@Override
	public void showWindow(ControlInventario inventaryControl) {
		this.inventaryControl = inventaryControl;
		addProductWindow.show(this);
	}

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
					if(addProductInventoryService.addProduct(name,
															location,
															Float.parseFloat(price),
															activeSubstance, Integer.parseInt(pieces),
															receta)) {
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
