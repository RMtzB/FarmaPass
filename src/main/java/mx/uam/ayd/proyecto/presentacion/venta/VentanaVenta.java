package mx.uam.ayd.proyecto.presentacion.venta;

/**
 * VictorSosa
 * 
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import javax.swing.JList;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
@Component
public class VentanaVenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtIngresaProducto;
	private JTextField textTotal;
	private ControlVenta controlVenta;
	private JTable table;
	float total = 0;
	Producto producto;
	Cliente cliente;

	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override 
		public boolean isCellEditable(int row, int column) { 
			return false; 
			} 
	};
	
	public VentanaVenta() {
		setTitle("Venta");
		setBounds(100, 100, 650, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Venta");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(246, 11, 63, 14);
		contentPane.add(lblNewLabel);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setEnabled(false);
		btnBuscar.setBounds(208, 27, 89, 23);
		contentPane.add(btnBuscar);

		txtIngresaProducto = new JTextField();
		txtIngresaProducto.setText("");
		txtIngresaProducto.setBounds(21, 28, 171, 20);
		contentPane.add(txtIngresaProducto);
		txtIngresaProducto.setColumns(10);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(467, 231, 51, 14);
		contentPane.add(lblTotal);

		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(528, 228, 96, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);

		JButton btnCobrar = new JButton("Cobrar");
		btnCobrar.setBounds(535, 269, 89, 23);
		contentPane.add(btnCobrar);

		JButton btnQuitarDeLista = new JButton("Quitar de lista");
		btnQuitarDeLista.setBounds(154, 228, 155, 23);
		contentPane.add(btnQuitarDeLista);

		JButton btnRecarga = new JButton("Recarga");
		btnRecarga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.iniciarecarga();
			}
		});
		btnRecarga.setBounds(10, 228, 89, 23);
		contentPane.add(btnRecarga);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 614, 150);
		contentPane.add(scrollPane);

		table = new JTable(modelo);
		
		modelo.addColumn("Nombre");
		modelo.addColumn("Compuesto");
		modelo.addColumn("Precio del Prodcuto");
		modelo.addColumn("Seleccionar");

		scrollPane.setViewportView(table);
		
		JButton btnRegisterClient = new JButton("Registrar Cliente");
		btnRegisterClient.setBounds(503, 27, 121, 23);
		contentPane.add(btnRegisterClient);
		
		//obtiene al cliente de la busqueda para asociarlo a la venta
		JButton cleinteventa = new JButton("Asociar venta");
		cleinteventa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double id = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el ID del cliente"));
				cliente = controlVenta.buscarPorIdCliente(id);
//				controlVenta.asociarVenta(cliente);
			}
		});
		cleinteventa.setBounds(331, 27, 136, 23);
		contentPane.add(cleinteventa);
		
		btnRegisterClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlVenta.showAddClientWindow();
			}
		});

		// listener
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVenta.buscarProducto(txtIngresaProducto.getText());

			}
		});

		txtIngresaProducto.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				btnBuscar.setEnabled(txtIngresaProducto.getText().length() != 0);
			}

		});

		btnQuitarDeLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = table.getSelectedRow();
				int y = table.getSelectedColumn();
				
				try {
					Object a = table.getValueAt(x, y - 1);
					float r = Float.parseFloat(a.toString());
					total -= r;
					textTotal.setText(String.valueOf(total));
					modelo.removeRow(x);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto para quitar");
				}
			}
		});

		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cliente == null) {
					controlVenta.muentraCobro(Float.parseFloat(textTotal.getText()),cliente);
					btnBuscar.setEnabled(false);
				}else {
					controlVenta.muentraCobro(Float.parseFloat(textTotal.getText()),cliente);
					btnBuscar.setEnabled(false);
				}
				
			}
		});
		
		table.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(table.getRowCount() == 0){
					btnQuitarDeLista.setEnabled(false);
					btnCobrar.setEnabled(false);
				}else {
					btnQuitarDeLista.setEnabled(true);
					btnCobrar.setEnabled(true);
				}
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	

	}

	// Muestra la ventana
	public void muestra(ControlVenta controlVenta) {

		this.controlVenta = controlVenta;

		setVisible(true);

	}

	// Metodos para la Ventana
	public void llenaTabla(Producto producto) {
		String a[] = new String[4];
		a[0] = producto.getNombre();
		a[1] = producto.getCompuesto();
		a[2] = String.valueOf(producto.getPrecio());
		a[3] = "Selecciona para quitar ";
		modelo.addRow(a);
		table.setModel(modelo);
		this.producto = producto;

	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void textTotal(float precio) {

		total += precio;
		textTotal.setText(String.valueOf(total));

	}

	/**
	 * Método que recorre la tabla
	 * 
	 * @return venta de tipo lista
	 */
	public List<Producto> recorrerTabla() {
		List<Producto> lista = new ArrayList<>();
		Producto producto;
		
		try {
			for (int i = 0; i < table.getRowCount(); i++) {
	
				producto = controlVenta.obtenerProducto((String) table.getValueAt(i, 0));
	
				lista.add(producto);
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
        }	

		return lista;

	}

	public void limpia() {
		textTotal.setText("");
		txtIngresaProducto.setText("");
		total=0;
		int filas = table.getRowCount();
		try {
			for (int i = 0; filas>i ; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }	
	}



	
}
