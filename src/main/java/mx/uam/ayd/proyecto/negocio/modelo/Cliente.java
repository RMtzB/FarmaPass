package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

/**
 * Entidad de negocio Cliente
 * 
 * @author AKarina
 *
 */

@Entity
@Data
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCliente;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String correo;
	private String telefono;
	private ArrayList<String> historial; 

	
	@OneToMany(targetEntity = PedidoCliente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private final List<PedidoCliente> pedidosCliente = new ArrayList<>();

	public Cliente() {
		historial = new ArrayList<String>();
	}

	public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.correo = correo;
		this.telefono = telefono;
		historial = new ArrayList<String>();
	}

	/**
	 * Agregar un pedido cliente y lo relaciona con el cliente
	 * 
	 * @param pedidoCliente pedido cliente a relacionar
	 * @return regresa true si el pedido cliente se agrego correctamente y false si
	 *         no
	 * @throws IllegalArgumentException si el pedido cliente es nulo
	 */

	public boolean addPedidoCliente(PedidoCliente pedidoCliente) {
		if (pedidoCliente == null) {
			throw new IllegalArgumentException("El detalle de venta no puede ser null");
		}
		if (pedidosCliente.contains(pedidoCliente)) {
			return false;
		}
		return pedidosCliente.add(pedidoCliente);
	}

	public void agregarVenta(String fecha, double total,int cantidad) {
		historial.add(fecha+"?"+total+"?"+cantidad);
	}
	
	

}
