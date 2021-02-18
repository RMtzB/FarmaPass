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

@Entity
@Data

/**
 * Entidad de empleado
 * @author AKarina
 *
 */
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idEmpleado;
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String correo;
	private String celular;
	private String nivel;
	private String usuario;
	private String password;
	
	@OneToMany(targetEntity = PedidoCliente.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmpleado")
	private final List <PedidoCliente> pedidosCliente = new ArrayList <> ();
	
	
	public Empleado() {}
	public Empleado(String nombre, String apellidoP, String apellidoM, String correo, String celular, String nivel,
			String usuario, String password) {
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.correo = correo;
		this.celular = celular;
		this.nivel = nivel;
		this.usuario = usuario;
		this.password = password;

	}
	
	/**
	 * Agregar un pedido cliente y lo relaciona con el empleado
	 * 
	 * @param pedidoCliente el pedido cliente a relacionar
	 * @return regresa true si el pedido cliente se agrego correctamente y false si no
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
}
