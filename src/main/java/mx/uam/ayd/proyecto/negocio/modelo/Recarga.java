package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class Recarga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int celular;
	private int monto;
	private String compania;
	public Recarga() {}
	public Recarga(int celular, int monto, String compania) {
		this.celular = celular;
		this.compania = compania;
		this.monto = monto;
	}
}
