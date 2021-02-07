package mx.uam.ayd.proyecto.presentacion.venta.busquedaActivo;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
@Component
public class VentanaBusquedaPorActivo extends JFrame {

	private ControlBusquedaPorActivo controlBusquedaPorActivo;
	String activo;
	private JPanel contentPane;
	JPanel panel;
	JScrollPane scrollPaneBusquedaPorActivo;
	private JTable tablaBusquedaPorACtivo;
	DefaultTableModel modeloBusquedaPorActivo = new DefaultTableModel();

	/**
	 * Create the frame.
	 */
	public VentanaBusquedaPorActivo() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 601, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel= new JPanel();
		panel.setBounds(10, 11, 565, 235);
		contentPane.add(panel);
		
		scrollPaneBusquedaPorActivo = new JScrollPane();
		panel.add(scrollPaneBusquedaPorActivo);
		
		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlBusquedaPorActivo.agregarElemento(getNombre());
				dispose();
			}
		});
		btnAñadir.setBounds(456, 259, 89, 23);
		contentPane.add(btnAñadir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(357, 257, 89, 23);
		contentPane.add(btnCancelar);
		
		tablaBusquedaPorACtivo = new JTable(modeloBusquedaPorActivo) { //1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloBusquedaPorActivo.addColumn("Nombre"); //2
		modeloBusquedaPorActivo.addColumn("Compuesto");
		modeloBusquedaPorActivo.addColumn("Total\n Productos");
		modeloBusquedaPorActivo.addColumn("Precio");
		modeloBusquedaPorActivo.addColumn("Receta");
		modeloBusquedaPorActivo.addColumn("Descuento         ");		
		tablaBusquedaPorACtivo.setPreferredScrollableViewportSize(panel.getSize());
		
		tablaBusquedaPorACtivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if(tablaBusquedaPorACtivo.getSelectedRow()!=-1) {
						controlBusquedaPorActivo.agregarElemento(getNombre());
						dispose();
					}
				}
			}
		});
		
	}
	public void muestra(ControlBusquedaPorActivo controlBusquedaPorActivo,String activo) {
		this.controlBusquedaPorActivo=controlBusquedaPorActivo;
		this.activo=activo;
		limpiarTabla();
		boolean aux= controlBusquedaPorActivo.llenarTabla(activo);
		if(aux) {
			scrollPaneBusquedaPorActivo.setViewportView(tablaBusquedaPorACtivo);
			this.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(null, "No se a podido encontrar ningun producto por esta entrada"+activo);
		}
	}
	
	
	public void limpiarTabla() { //3 
		if (tablaBusquedaPorACtivo.getRowCount() > 0) { // solo esto
			int filas = tablaBusquedaPorACtivo.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloBusquedaPorActivo.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla de usuarios.");
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
		modeloBusquedaPorActivo.addRow(a);
		tablaBusquedaPorACtivo.setModel(modeloBusquedaPorActivo);
	}
	
	private String descuento(Producto p) {
		if(p.getDescuento().equals(""))
			return "Sin descuento";
		else
			return p.getDescuento()+"% -"+p.getFecha();
	}
	
	public String getNombre() {
		int row=tablaBusquedaPorACtivo.getSelectedRow();
		return tablaBusquedaPorACtivo.getValueAt(row, 0).toString();
	}
	
	public void sinProductos(String string) {
		
	}
	
	
	
}
