

/**
 * @author VictorSosa
 */

package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@Slf4j
@Service
public class ServicioVenta {
	
	@Autowired
	private VentaRepository ventaRepository;

	public void creaVenta() {
		
	}
	
	/**
	 * Obtiene las ventas por una fecha dada
	 * @param fechaF 
	 * @return
	 */
	public List<Venta> obtenerVentasPorFecha(String fechaF) {
		List<Venta> ventas = ventaRepository.findByFecha(fechaF);
		return ventas;
	}
	
	public List<Venta> recuperaTodasLasVentas() {
		List<Venta> ventas = new ArrayList<>();
		for (Venta venta : ventaRepository.findAll()) {
			ventas.add(venta);
		}
		return ventas;
		
	}
}
