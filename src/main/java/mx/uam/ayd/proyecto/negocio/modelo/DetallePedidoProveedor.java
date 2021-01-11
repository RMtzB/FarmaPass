package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

/**
 * Entidad de negocio Detalle de Pedido a proveedor
 * @author AKarina
 *
 */

@Entity
@Data
public class DetallePedidoProveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPedido;
	private int numeroPiezas;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Producto producto;
	
	public DetallePedidoProveedor() {}
	public DetallePedidoProveedor(int numeroPiezas) {
		this.numeroPiezas = numeroPiezas;
	}
	
}
