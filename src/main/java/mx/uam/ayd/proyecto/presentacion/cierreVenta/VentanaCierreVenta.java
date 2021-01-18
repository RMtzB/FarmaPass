package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoCliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;

@SuppressWarnings("serial")
@Component
public class VentanaCierreVenta extends JFrame {

	private JPanel contentPane;
	private JButton btnFinalizar;
	private ControlCierreVenta control;

	DefaultTableModel modeloInventario = new DefaultTableModel(); // 1
	DefaultTableModel modeloVenta = new DefaultTableModel();
	DefaultTableModel modeloPedido = new DefaultTableModel();
	private JTable tabla_inventario;//1.1
	private JTable tabla_venta;
	private JTable tabla_pedidos;
	private JTextField txtNombreEmpleado;
	private Empleado empleado;
	private JTextField txtNivel;

	private Calendar fecha = new GregorianCalendar();
	private int ano = fecha.get(Calendar.YEAR);
	private int mes = fecha.get(Calendar.MONTH);
	private int dia = fecha.get(Calendar.DAY_OF_MONTH);
	private String fechaF = ano + "/" + mes + "/" + dia;
	private JTextField txtError;

	public VentanaCierreVenta() {
		setBounds(100, 100, 500, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tabla_inventario = new JTable(modeloInventario) { //1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		//;

		modeloInventario.addColumn("Nombre"); //2
		modeloInventario.addColumn("Compuesto");
		modeloInventario.addColumn("Total\n Productos");
		modeloInventario.addColumn("Precio");
		modeloInventario.addColumn("Receta");

		tabla_venta = new JTable(modeloVenta) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		;
		modeloVenta.addColumn("Fecha");
		modeloVenta.addColumn("Total de\n Productos");
		modeloVenta.addColumn("Precio total");

		tabla_pedidos = new JTable(modeloPedido) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		;
		modeloPedido.addColumn("Nombre\n Cliente");
		modeloPedido.addColumn("Fecha");
		modeloPedido.addColumn("Telefono");
		modeloPedido.addColumn("Producto(s)");
		modeloPedido.addColumn("Total");

		JPanel panel = new JPanel();
		panel.setLayout(null);

		JScrollPane scrollPaneCierreVenta = new JScrollPane();
		scrollPaneCierreVenta.setBounds(0, 0, 474, 135);
		panel.add(scrollPaneCierreVenta);

//    	tabla_inventario = new JTable(
//				new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Compuesto", "Precio" }));

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);
		txtNombreEmpleado.setBounds(106, 6, 358, 22);
		panel_1.add(txtNombreEmpleado);

		JPanel panel_2 = new JPanel();

		JPanel panel_3 = new JPanel();

		JPanel panel_4 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
				.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtError = new JTextField();
		txtError.setHorizontalAlignment(SwingConstants.CENTER);
		txtError.setEditable(false);
		txtError.setBounds(0, 0, 474, 135);
		panel.add(txtError);
		txtError.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setColumns(10);
		txtNivel.setBounds(10, 7, 86, 20);
		panel_1.add(txtNivel);
		panel_4.setLayout(null);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cancelaCierreVenta(empleado);
				limpiarTablas();
				
			}
		});
		btnCancelar.setBounds(276, 11, 89, 23);
		panel_4.add(btnCancelar);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cerrarSesion(empleado);
			}
		});
		btnFinalizar.setBounds(375, 11, 89, 23);
		panel_4.add(btnFinalizar);
		panel_3.setLayout(null);

		JButton btnInventario = new JButton("Inventario");
		btnInventario.setBounds(0, 17, 120, 23);
		panel_3.add(btnInventario);

		JButton btnVentas = new JButton("Ventas");
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablas();
				control.obtenerVentasDia(fechaF);
				scrollPaneCierreVenta.setViewportView(tabla_venta);

			}
		});
		btnVentas.setBounds(130, 17, 120, 23);
		panel_3.add(btnVentas);

		btnInventario.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				limpiarTablas(); //3
				control.obtenerProductos(); //4
				scrollPaneCierreVenta.setViewportView(tabla_inventario);
			}
		});

		JButton btnPedidos = new JButton("Pedidos");
		btnPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablas();
				control.obtenerPedidosClienteDelDia(fechaF);
				scrollPaneCierreVenta.setViewportView(tabla_pedidos);
			}
		});
		btnPedidos.setBounds(260, 17, 120, 23);
		panel_3.add(btnPedidos);
		panel_2.setLayout(null);

		JLabel lblCierreVenta = new JLabel("DETALLE DE CIERRE DE VENTA");
		lblCierreVenta.setBounds(0, 0, 474, 21);
		panel_2.add(lblCierreVenta);
		lblCierreVenta.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(gl_contentPane);
	}

	public void muestra(ControlCierreVenta control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.txtNivel.setText(empleado.getNivel());
		setVisible(true);
	}

	public void agregaVentas(Venta venta, int size) {
		String a[] = new String[3];
		a[0] = venta.getFecha();
		a[1] = String.valueOf(size);
		a[2] = String.valueOf(venta.getTotal());
		modeloVenta.addRow(a);
		tabla_venta.setModel(modeloVenta);
	}

	public void agregaProductos(Producto producto) {
		String a[] = new String[5];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPiezas());
		a[3] = String.valueOf(producto.getPrecio());
		a[4] = producto.getReceta();
		modeloInventario.addRow(a);
		tabla_inventario.setModel(modeloInventario);
		RowsRenderer rr = new RowsRenderer(2);
		tabla_inventario.setDefaultRenderer(Object.class, rr);
	}

	public void agregarPedido(PedidoCliente pedidoCliente, List<Producto> productos, List<Cliente> cliente) {
		String a[] = new String[5];
		a[0] = cliente.get(0).getNombre() + " " + cliente.get(0).getApellidoPaterno() + " " + cliente.get(0).getApellidoMaterno();
		a[1] = pedidoCliente.getFechaDeCreacion();
		a[2] = cliente.get(0).getTelefono();
		a[3] = "";
		for (Producto producto: productos) {
			a[3] += producto.getNombre() +"\n";
		}
		a[4] = String.valueOf(pedidoCliente.getPrecioTotal());
		modeloPedido.addRow(a);
		tabla_pedidos.setModel(modeloPedido);
		tabla_pedidos.getColumnModel().getColumn(3).setCellRenderer(
			      new TextAreaRenderer());
		tabla_pedidos.getColumnModel().getColumn(0).setCellRenderer(
			      new TextAreaRenderer());
		tabla_pedidos.setRowHeight(0, 50);
	    tabla_pedidos.setRowHeight(5, 50);
	}
	
	private void limpiarTablas() { //3 
		if (tabla_venta.getRowCount() > 0) {
			int filas = tabla_venta.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloVenta.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla venta.");
			}
		} else if (tabla_inventario.getRowCount() > 0) { // solo esto
			int filas = tabla_inventario.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloInventario.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla inventario.");
			}
		} else if (tabla_pedidos.getRowCount() > 0) {
			int filas = tabla_pedidos.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloPedido.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla pedido.");
			}
		}

	}

	public void oculta() {
		setVisible(false);
	}

	public void sinProductos(String mensaje) {
		txtError.setText(mensaje);
		
	}
}
