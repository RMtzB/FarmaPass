package mx.uam.ayd.proyecto;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.AsistenciaRepository;
import mx.uam.ayd.proyecto.datos.DetalleVentaRepository;
import mx.uam.ayd.proyecto.datos.EmpleadoRepository;
import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.ProductoRepository;
import mx.uam.ayd.proyecto.datos.VentaRepository;
import mx.uam.ayd.proyecto.negocio.ServicioCliente;
import mx.uam.ayd.proyecto.negocio.ServicioDetallePedidoCliente;
import mx.uam.ayd.proyecto.negocio.ServicioDetalleVenta;
import mx.uam.ayd.proyecto.negocio.ServicioPedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Asistencia;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.inicioSesion.ControlInicioSesion;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

/**
 * 
 * Clase principal que arranca la aplicación
 * 
 * @author SosaPiña
 *
 */
@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlInicioSesion controlInicioSesion;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	EmpleadoRepository empleadoRepository;

	@Autowired
	DetalleVentaRepository detalleVentaRepository;

	@Autowired
	VentaRepository ventaRepository;

	@Autowired
	ControlPrincipal controlPrincipal;

	@Autowired
	GrupoRepository grupoRepository;
	
	@Autowired
	AsistenciaRepository asistenciaRepository;

	@Autowired
	ServicioDetallePedidoCliente servicioDetallePedidoCliente;

	@Autowired
	ServicioPedidoCliente servicioPedidoCliente;

	@Autowired
	ServicioCliente servicioCliente;
	
	@Autowired
	ServicioDetalleVenta servicio;
	

	public static void main(String[] args) {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	@PostConstruct
	public void inicia() {

		inicializaBD();
		controlInicioSesion.inicia();
	}

	/**
	 * 
	 * Inicializa la BD con datos
	 * 
	 * 
	 */
	public void inicializaBD() {

		Producto producto = new Producto("AJOLOTIUS", "Miel de abeja", "No", "Estante 2, segundo anaquel", 50, 10);
		productoRepository.save(producto);

		Producto producto1 = new Producto("DICLOFENACO", "Diclofenaco Sodico", "No", "Estante 2, tercer anaquel", 45,
				10);
		producto1.setDescuento("50");
		producto1.setFecha("2021-01-24");
		productoRepository.save(producto1);

		Producto producto2 = new Producto("XL3XTRA", "Parecetamol-fenilefina-clorfenamina", "No",
				"Estante 2, primer anaquel", 48, 5);
		productoRepository.save(producto2);

		Producto producto3 = new Producto("CLORANFENICOL", "Cloranfenicol", "No", "Estante 1, segundo anaquel", 22, 10);
		productoRepository.save(producto3);

		Producto producto4 = new Producto("DIURMESSEL", "furosemina", "Si", "Estante 1, cuarto anaquel", 35, 5);
		productoRepository.save(producto4);

		Producto producto5 = new Producto("DUALGOS", "Paracetamol-Ubuprofeno", "No", "Estante 2, segundo anaquel", 29,
				10);
		productoRepository.save(producto5);
//
//		Venta venta1 = new Venta("2019/5/1", 50);
//
//		DetalleVenta detalleDeVenta = new DetalleVenta();
//
//		ventaRepository.save(venta1);
//		detalleVentaRepository.save(detalleDeVenta);
//
//		List<Producto> productoaux = new ArrayList<>();
//
//		productoaux.add(producto1);
//		productoaux.add(producto2);
//
//		servicio.agregarDetalleVenta(venta1, productoaux);

		Empleado pruebaEmpleado = new Empleado("Karina", "Vergara", "Guzman", "karina@gmail.com", "5587388643",
				"empleado", "anver", "123456789");
		Empleado pruebaEncargado = new Empleado("Ximena", "Pereda", "Rodriguez", "ximena@gmail.com", "5587388642",
				"encargado", "alma", "987654321");
		empleadoRepository.save(pruebaEmpleado);
		empleadoRepository.save(pruebaEncargado);

		Cliente pruebaCliente = new Cliente("Joana", "Hernandez", "Ruiz", "joana@gmail.com", "5544332211");
		servicioCliente.guardarCliente(pruebaCliente);
		PedidoCliente pruebaPedidoCliente = new PedidoCliente("2020/10/17", 2, 119);
		servicioPedidoCliente.guardar(pruebaPedidoCliente);

		pruebaEmpleado.addPedidoCliente(pruebaPedidoCliente);
		empleadoRepository.save(pruebaEmpleado);
		pruebaCliente.addPedidoCliente(pruebaPedidoCliente);
		servicioCliente.guardarCliente(pruebaCliente);

		servicioDetallePedidoCliente.agregarDetallePedidoCliente(pruebaPedidoCliente, producto1, 2);
		servicioDetallePedidoCliente.agregarDetallePedidoCliente(pruebaPedidoCliente, producto5, 1);

		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);

		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);
		
		
		Asistencia asistencia = new Asistencia();
		asistencia.setHoraInicial("08:00:45");
		asistencia.setHoraFinal("18:30:24");
		asistencia.setFecha("7/2/2021");
		asistencia.setEmpleado(pruebaEmpleado);
		asistenciaRepository.save(asistencia);
		
		Asistencia asistencia1 = new Asistencia();
		asistencia1.setHoraInicial("08:00:45");
		asistencia1.setHoraFinal("18:30:24");
		asistencia1.setFecha("5/2/2021");
		asistencia1.setEmpleado(pruebaEmpleado);
		asistenciaRepository.save(asistencia1);
		
		Venta venta0 = new Venta("16/2/2020",95,2,"anver");
		ventaRepository.save(venta0);
		
		Venta venta1 = new Venta("16/2/2021",95,2,"anver");
		ventaRepository.save(venta1);

		Venta venta2 = new Venta("17/2/2021",48,1,"anver");
		ventaRepository.save(venta2);
		
		Venta venta3 = new Venta("17/2/2021",45,2,"alma");
		ventaRepository.save(venta3);
		
		Venta venta4 = new Venta("18/2/2021",95,2,"anver");
		ventaRepository.save(venta4);
		

		
	}
}