package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioProducto;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlCierreVenta;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

import java.awt.Panel;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
@Component
public class VentanaInventario extends JFrame {

	private JPanel contentPane;

	private ControlInventario controlInventario;
	
	//private ControlCierreVenta control2;
	
	DefaultTableModel modeloInventario = new DefaultTableModel(); // 1
	
	private Empleado empleado;

	private JTable tabla_inventario;//1.1
	
	
	JScrollPane scrollPaneInvent;
	
	Producto producto;

	
	/**
	 * Create the frame.
	 */
	public VentanaInventario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tabla_inventario = new JTable(modeloInventario) { //1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloInventario.addColumn("Nombre"); //2
		modeloInventario.addColumn("Compuesto");
		modeloInventario.addColumn("Total\n Productos");
		modeloInventario.addColumn("Precio");
		modeloInventario.addColumn("Receta");
		modeloInventario.addColumn("Descuento         ");
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		scrollPaneInvent = new JScrollPane();
		panel.add(scrollPaneInvent);
		
		JLabel inventariotxt = new JLabel("Inventario");
		inventariotxt.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(inventariotxt, BorderLayout.NORTH);
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton retur = new JButton("Regresar");
		retur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlInventario.termina();
			}
		});
		
		JButton btnAgregarProducto = new JButton("Agregar Producto");
		
		/**
		 * 
		 * Listenar btnAgregarProducto
		 * 
		 */
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlInventario.showAddProductWindow();
			}
		});
		
		JButton btnDescuento = new JButton("Agregar Descuento");
		
		btnDescuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabla_inventario.getSelectedRow()!=-1)
					controlInventario.nuevoDescuento( getNombreSeleccionado(), getPrecioSeleccionado());
				else
					JOptionPane.showMessageDialog(null, "Es necesario seleccionar un producto");
			}
		});
		
		JButton btnNewButton = new JButton("Modificar Producto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 producto= controlInventario.buscarProducto(getNombre());
				 controlInventario.iniciaModificar(producto);	 
			}
		});
		
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDescuento)
					.addGap(26)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addComponent(btnAgregarProducto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(retur))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(retur)
						.addComponent(btnAgregarProducto)
						.addComponent(btnDescuento)
						.addComponent(btnNewButton)))
		);
		panel_1.setLayout(gl_panel_1);
		
	}
	
	
	public void muestra(ControlInventario control, Empleado empleado) {
		this.controlInventario = control;
		this.empleado = empleado;
		setVisible(true);
		control.refreshTable();;
		scrollPaneInvent.setViewportView(tabla_inventario);
		
	}


	
	public void limpiarTablas() { //3 
		if (tabla_inventario.getRowCount() > 0) { // solo esto
			int filas = tabla_inventario.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloInventario.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla inventario.");
			}
		}		
	}
	
	public void agregaProductos(Producto producto) {
		String a[] = new String[6];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPiezas());
		a[3] = String.valueOf(producto.getPrecio());
		a[4] = producto.getReceta();
		a[5] = descuento(producto);
		modeloInventario.addRow(a);
		tabla_inventario.setModel(modeloInventario);
		RowsRenderer rr = new RowsRenderer(2);
		tabla_inventario.setDefaultRenderer(Object.class, rr);
	}

	public String getNombreSeleccionado() {
		int row=tabla_inventario.getSelectedRow();
		return tabla_inventario.getValueAt(row, 0).toString();
	}
	public String getPrecioSeleccionado() {
		int row=tabla_inventario.getSelectedRow();
		return tabla_inventario.getValueAt(row, 3).toString();
	}
	
	
	public String getNombre() {
		
		int row=tabla_inventario.getSelectedRow();
		return tabla_inventario.getValueAt(row, 0).toString();
	}
	
	private String descuento(Producto p) {
		if(p.getDescuento().equals(""))
			return "Sin descuento";
		else
			return p.getDescuento()+"% -"+p.getFecha();
	}
	
	public void sinProductos(String string) {
		// TODO Auto-generated method stub
		
	}
}
