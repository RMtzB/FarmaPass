package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.presentacion.agregarProducto.ControlAgregarProducto;

@Service
public class ServicioAgregarProducto {
	@Autowired
	private ControlAgregarProducto control;
	@Autowired
	private ProductoRepository repository;
}
