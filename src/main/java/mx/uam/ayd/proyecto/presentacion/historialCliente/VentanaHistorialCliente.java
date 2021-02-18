package mx.uam.ayd.proyecto.presentacion.historialCliente;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.springframework.stereotype.Component;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
@Component
public class VentanaHistorialCliente extends JFrame {
	
	private JPanel contentPane;
	private ControlHistorialCliente controlHistorialCliente;
	private JPanel panel;
	JScrollPane scrollPane;
	private JTable tablaHistorial;
	DefaultTableModel modeloHistorial = new DefaultTableModel();
	private JTextField txtDescuento;

	/**
	 * Create the frame.
	 */
	public VentanaHistorialCliente() {
		setTitle("Lista de clientes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 658, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(10, 11, 622, 232);
		contentPane.add(panel);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 329, 622, 36);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnConfirmar.setBounds(523, 11, 89, 23);
		panel_1.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnCancelar.setBounds(424, 11, 89, 23);
		panel_1.add(btnCancelar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 254, 622, 62);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descuento de cliente actual:");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 22, 171, 14);
		panel_2.add(lblNewLabel);
		
		txtDescuento = new JTextField();
		txtDescuento.setBounds(191, 20, 22, 20);
		panel_2.add(txtDescuento);
		txtDescuento.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnModificar.setBounds(236, 19, 89, 23);
		panel_2.add(btnModificar);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(215, 22, 22, 14);
		panel_2.add(lblNewLabel_1);
		
		tablaHistorial = new JTable(modeloHistorial) { //1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		txtDescuento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		modeloHistorial.addColumn("Fecha");
		modeloHistorial.addColumn("Cantidad de Productos");
		modeloHistorial.addColumn("Total");
		Dimension a = panel.getSize();
		Dimension b = new Dimension(a.width - 10 , a.width - 10); 
		tablaHistorial.setPreferredScrollableViewportSize(b);
		tablaHistorial.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
	}
	public void muestra(ControlHistorialCliente controlHistorialClientes,int id,int desc){
		this.controlHistorialCliente= controlHistorialClientes;
		this.setVisible(true);
		limpiarTabla();
		controlHistorialClientes.llenarTabla(id);
		scrollPane.setViewportView(tablaHistorial);
		txtDescuento.setText(desc+"");
	}
	
	public void limpiarTabla() { //3 
		if (tablaHistorial.getRowCount() > 0) { // solo esto
			int filas = tablaHistorial.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloHistorial.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla de usuarios.");
			}
		}		
	}
	
	public void agregarVenta(String venta) {
		String e[]= venta.split("%");
		String a[] = new String[3];
		a[0] = e[0];
		a[1] = e[1];
		a[2] = e[2];
		modeloHistorial.addRow(a);
		tablaHistorial.setModel(modeloHistorial);
	}
}
