package mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
@Component
public class VentanaAltaYBajaUsuarios extends JFrame {
	private JPanel contentPane;
	private ControlAltaYBajaUsuarios controlAltaYBaja;
	private JPanel panel;
	private JScrollPane scrollPaneAltaBayaClientes;
	private JTable tablaAltaBajaUsuarios;
	DefaultTableModel modeloAltaBajaUsuario = new DefaultTableModel();

	/**
	 * Create the frame.
	 */
	public VentanaAltaYBajaUsuarios() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 748, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		panel = new JPanel();
		panel.setBounds(10, 31, 712, 270);
		contentPane.add(panel);
		scrollPaneAltaBayaClientes = new JScrollPane();		
		panel.add(scrollPaneAltaBayaClientes);
		
		JLabel lblNewLabel = new JLabel("Usuarios regstrados");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(296, 6, 141, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnAgregar = new JButton("Agregar Empleado");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlAltaYBaja.iniciaVentanaAltaUsuario();
			}
		});
		btnAgregar.setBounds(564, 339, 158, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEliminar = new JButton("Eliminar Empleado");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaAltaBajaUsuarios.getSelectedRow()!=-1) {
					if( JOptionPane.showConfirmDialog(null, "Seguro que desea elimiar a "+getUsuarioSeleccionado(),"a",0)==0 )
					controlAltaYBaja.EliminarEmpleado(getUsuarioSeleccionado() );
				}
			}
		});
		btnEliminar.setBounds(389, 339, 165, 23);
		contentPane.add(btnEliminar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(10, 339, 89, 23);
		contentPane.add(btnSalir);
		
		tablaAltaBajaUsuarios = new JTable(modeloAltaBajaUsuario) { //1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloAltaBajaUsuario.addColumn("Nombre");
		modeloAltaBajaUsuario.addColumn("Correo");
		modeloAltaBajaUsuario.addColumn("Celular");
		modeloAltaBajaUsuario.addColumn("Usuario");
		modeloAltaBajaUsuario.addColumn("Tipo");
		tablaAltaBajaUsuarios.setPreferredScrollableViewportSize(panel.getSize());
		
	}

	public void muestra(ControlAltaYBajaUsuarios controlAltaYBaja) {
		this.controlAltaYBaja=controlAltaYBaja;
		this.setVisible(true);
		limpiarTabla();
		controlAltaYBaja.llenarTabla();
		scrollPaneAltaBayaClientes.setViewportView(tablaAltaBajaUsuarios);
	}
	
	public void limpiarTabla() { //3 
		if (tablaAltaBajaUsuarios.getRowCount() > 0) { // solo esto
			int filas = tablaAltaBajaUsuarios.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloAltaBajaUsuario.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla de usuarios.");
			}
		}		
	}
	
	public void agregaProductos(Empleado e) {
		String a[] = new String[6];
		a[0] = e.getNombre()+" "+e.getApellidoP()+" "+e.getApellidoP();
		a[1] = e.getCorreo();
		a[2] = e.getCelular();
		a[3] = e.getUsuario();
		a[4] = e.getNivel();
		modeloAltaBajaUsuario.addRow(a);
		tablaAltaBajaUsuarios.setModel(modeloAltaBajaUsuario);
		RowsRenderer rr = new RowsRenderer(2);
		tablaAltaBajaUsuarios.setDefaultRenderer(Object.class, rr);
	}

	public void sinProductos(String string) {
		// TODO Auto-generated method stub
		
	}
	private String getUsuarioSeleccionado() {
		int row=tablaAltaBajaUsuarios.getSelectedRow();
		return tablaAltaBajaUsuarios.getValueAt(row, 3).toString();
	}

	public void EliminarGerente() {
		JOptionPane.showMessageDialog(null, "No se puede eliminar al encargado");

		
	}
	
	
}
