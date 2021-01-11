package mx.uam.ayd.proyecto.presentacion.GenerarPedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

/*
 * Esta clase lleva el flujo de la ventana de ConfirmacionDeGenerarPedio
 */

@Component
public class ControlConfirmacionDeGenerarPedido {

	@Autowired
	private VentanaConfirmacionDePedido ventanaConfirmacionDePedido;
	
	@Autowired
	private VentanaGenerarPedido ventanaGenerarPedido;
	
	@Autowired
	private ControlGenerarPedido controlGenerarPedido;
	
	/**
	 * Inicia la historia de usuario:Procedo de generar pedido
	 * 
	 */
	public void inicia(List<Producto> lista, List<Integer> listaPiezas, PedidoProveedor pedido1, Empleado empleado) {
		ventanaConfirmacionDePedido.despliega(this,lista,listaPiezas,pedido1,empleado);
	}

	public void limpiarTabla() {
		ventanaGenerarPedido.limpia();
	}
	
	public void cierra(Empleado empleado) {
		controlGenerarPedido.despliega(empleado);
		ventanaConfirmacionDePedido.oculta();
	}
	
	/**
	 * Termina historia de usuario: Forma de Cobro
	 */
	
	

}
