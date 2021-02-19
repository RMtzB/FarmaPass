package mx.uam.ayd.proyecto.presentacion.Clientes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
@Component
public class VentanaListaClientes extends JFrame {

	private JPanel contentPane;
	private ControlListaClientes controlListaClientes;
	private JPanel panel;
	JScrollPane scrollPane;
	private JTable tablaListaClientes;
	DefaultTableModel modeloListaClientes = new DefaultTableModel();

	/**
	 * Create the frame.
	 */
	public VentanaListaClientes() {
		setTitle("Lista de clientes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 642, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(10, 44, 606, 232);
		contentPane.add(panel);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 279, 606, 57);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlListaClientes.iniciarHistorial(getIdSeleccionado() , getdescSeleccionado());
			}
		});
		btnConfirmar.setBounds(507, 11, 89, 23);
		panel_1.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnCancelar.setBounds(408, 11, 89, 23);
		panel_1.add(btnCancelar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlListaClientes.eliminarCliente(getIdSeleccionado());
			}
		});
		
		btnEliminar.setBounds(4, 11, 89, 23);
		panel_1.add(btnEliminar);
		
		JLabel lblNewLabel = new JLabel("Selecciona un cliente para ver su historial completo");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel.setBounds(30, 11, 487, 22);
		contentPane.add(lblNewLabel);
		
		tablaListaClientes = new JTable(modeloListaClientes) { //1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloListaClientes.addColumn("Id");
		modeloListaClientes.addColumn("Nombre");
		modeloListaClientes.addColumn("Correo");
		modeloListaClientes.addColumn("Celular");
		modeloListaClientes.addColumn("Desc. de cliente");
		
		Dimension a = panel.getSize();
		Dimension b = new Dimension(a.width - 10 , a.width - 10);
		
		tablaListaClientes.setPreferredScrollableViewportSize(b);
		tablaListaClientes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		
		
		
		
	}
	
	public void muestra(ControlListaClientes controlListaClientes){
		this.controlListaClientes=controlListaClientes;
		this.setVisible(true);
		limpiarTabla();
		controlListaClientes.llenarTabla();
		scrollPane.setViewportView(tablaListaClientes);
	}
	
	public void limpiarTabla() { //3 
		if (tablaListaClientes.getRowCount() > 0) { // solo esto
			int filas = tablaListaClientes.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloListaClientes.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla de usuarios.");
			}
		}		
	}

	public void agregaProductos(Cliente e) {
		String a[] = new String[5];
		a[0] = e.getIdCliente()+"";
		a[1] = e.getNombre()+" "+e.getNombre()+" "+e.getApellidoPaterno()+" "+ e.getApellidoMaterno();
		a[2] = e.getCorreo();
		a[3] = e.getTelefono();
		a[4] = e.getDescuentoCliente()+" %";
		modeloListaClientes.addRow(a);
		tablaListaClientes.setModel(modeloListaClientes);
	}
	
	private int getIdSeleccionado() {
		int row=tablaListaClientes.getSelectedRow();
		return Integer.parseInt(tablaListaClientes.getValueAt(row, 0).toString());
	}
	private int getdescSeleccionado(){
		int row=tablaListaClientes.getSelectedRow();
		return Integer.parseInt(tablaListaClientes.getValueAt(row, 4).toString());
	}
	
	
}
