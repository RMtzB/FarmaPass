package mx.uam.ayd.proyecto.presentacion.cierreVenta;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;


@SuppressWarnings("serial")
@Component
public class VentanaInventario extends JFrame {

	private JPanel contentPane;

	private ControlInventario controlInventario;
	
	DefaultTableModel modeloInventario = new DefaultTableModel(); // 1
	
	private Empleado empleado;

	private JTable tabla_inventario;//1.1

	
	JScrollPane scrollPaneInvent;

	
	/**
	 * Create the frame.
	 */
	public VentanaInventario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDescuento)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
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
						.addComponent(btnDescuento)))
		);
		panel_1.setLayout(gl_panel_1);
		
	}
	
	
	public void muestra(ControlInventario control, Empleado empleado) {
		this.controlInventario = control;
		this.empleado = empleado;
		setVisible(true);
		control.llenarTabla();
		scrollPaneInvent.setViewportView(tabla_inventario);
	}


	
	public void limpiarTablas() {
		if (tabla_inventario.getRowCount() > 0) {
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


	public void sinProductos(String string) {
	}
}
