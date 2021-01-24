package mx.uam.ayd.proyecto.utils;

import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProductoAInventario;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;

public class AgregarProductoAInventario {
	public interface VentanaAddProduct {
		public void show(ControlAgregarProductoAInventario addProductControl);
		
		public boolean showMessagetoConfirmData(String mensaje);
		
		public void showMessage(String message);
		
		public void resetWindow();
	}
	
	
	
	public interface ControlAddProduct {
		public void closeWindow();
		
		public void showWindow(ControlInventario inventaryControl);
		
		public void addProdruct(String name, String location, String price,
								String activeSubstance, String pieces, boolean prescription);
	}
	
	
	
	public interface ServicioAddProduct {
		public int areFieldsCorrect(String name, String location, String price,
									String activeSubstance, String pieces);
		
		public boolean addProduct(Producto product);
	}
}
