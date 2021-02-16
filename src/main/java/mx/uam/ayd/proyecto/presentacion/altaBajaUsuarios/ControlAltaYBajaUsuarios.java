package mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.AltaUsuarios.ControlAltaUsuario;
import mx.uam.ayd.proyecto.presentacion.ventasEmpleados.ControlVentasdeEmpleados;

@Component
public class ControlAltaYBajaUsuarios {
	@Autowired
	private VentanaAltaYBajaUsuarios ventanaAltaYBaja;
	
	@Autowired
	private ServicioEmpleado servicioEmpleado;
	
	@Autowired
	private ControlAltaUsuario controlAltaUsuario;
	
	@Autowired
	private ControlVentasdeEmpleados controlVentasdeEmpleados;
	
	public void inicia() {
		ventanaAltaYBaja.muestra(this);
	}
	
	public void iniciaVentanaAltaUsuario() {
		controlAltaUsuario.inicia();
	}
	
	
	public void llenarTabla() {
		Iterator<Empleado> it=servicioEmpleado.obtenerEmpleados().iterator();
		
		while(it.hasNext()){
			ventanaAltaYBaja.agregaProductos(it.next());
		}

		
	}
	
	public void refreshTable() {
		ventanaAltaYBaja.limpiarTabla();
		llenarTabla();
	}

	public void EliminarEmpleado(String usuarioSeleccionado) {
		if(servicioEmpleado.EliminarEmpleado(usuarioSeleccionado))
			refreshTable();
		else
			ventanaAltaYBaja.EliminarGerente();
	}
	
	public void iniciaVerVentas(String usuarioSeleccionado) {
		Empleado aux= servicioEmpleado.buscarEmpleado(usuarioSeleccionado);
		controlVentasdeEmpleados.inicia(aux);
		
	}
	

}
