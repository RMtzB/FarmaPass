package mx.uam.ayd.proyecto.presentacion.GenerarPedido;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
@Component
public class VentanaGenerarPedido extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Empleado empleado;
	private ControlGenerarPedido controlGenerarPedido;
	private String FechaDeEntrega = "2020/11/20";

	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			if (column == 2) {
				return true;
			} else {
				return false;
			}
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaGenerarPedido frame = new VentanaGenerarPedido();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaGenerarPedido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblfechentrega = new JLabel("Fecha de entrega");
		panel.add(lblfechentrega);

		JComboBox comboday = new JComboBox();
		comboday.setModel(new DefaultComboBoxModel(
				new String[] { "1 ", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboday.setToolTipText("dia");
		panel.add(comboday);

		JComboBox combomonth = new JComboBox();
		combomonth.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		combomonth.setToolTipText("mes");
		panel.add(combomonth);

		JButton btnregresa = new JButton("Regresar");

		panel.add(btnregresa);

		JButton btnGenera = new JButton("Generar lista");
		panel.add(btnGenera);

		JButton btnquitar = new JButton("Quitar");
		btnquitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = table.getSelectedRow();
				int y = table.getSelectedColumn();
				try {
					modelo.removeRow(x);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto para quitar");
				}
			}
		});
		panel.add(btnquitar);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblpedido = new JLabel("Realizar Pedido");
		lblpedido.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblpedido);

		JLabel lblinventario = new JLabel("Inventario");
		panel_1.add(lblinventario);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(new DefaultTableModel(new Object[][] {}, new String[] {}));

		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad ");
		modelo.addColumn("Productos a surtir");
		modelo.addColumn("Añadir a la lista");

		scrollPane.setViewportView(table);

		// Listeners
		btnregresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				controlGenerarPedido.cerrar(empleado);

			}
		});

		btnGenera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					//limpia();
				    String dia = comboday.getSelectedItem().toString();
					String mes = combomonth.getSelectedItem().toString();
					recorrerTabla(dia, mes, FechaDeEntrega);
					//limpia();
			}
		});
	}

	public void inicia(ControlGenerarPedido controlGenerarPedido, Empleado empleado) {
		this.empleado = empleado;
		this.controlGenerarPedido = controlGenerarPedido;
		llenarTablaProductos();
		setVisible(true);
	}

	private void llenarTablaProductos() {
		List<Producto> listaproductos = controlGenerarPedido.obtenerProductos();
		List<Producto> listaproductos2 = new ArrayList<>();
		for (Producto producto : listaproductos) {
			if (producto.getPiezas() < 8) {
				listaproductos2.add(producto);
			}
		}
		for (Producto producto : listaproductos2) {
			String a[] = new String[4];
			a[0] = producto.getNombre();
			a[1] = String.valueOf(producto.getPiezas());
			a[2] = "";
			a[3] = "Selecciona para quitar";
			modelo.addRow(a);
			table.setModel(modelo);
		}
	}
	/**
	 * MÃ©todo que entrega una fecha y campos validos para la generacion del pedido
	 * 
	 * @param dia, mes, fechaDeEntrega2
	 */
	public void recorrerTabla(String dia, String mes, String fechaDeEntrega2) {
		List<Producto> lista = new ArrayList<>();
		Producto producto;
		List<Integer> listaPiezas = new ArrayList<>();
		try {
			for (int i = 0; i < table.getRowCount(); i++) {
				producto = controlGenerarPedido.obtenerProducto((String) table.getValueAt(i, 0));
				lista.add(producto);
				String dato = (String) table.getValueAt(i, 2);
				int aux = Integer.valueOf(dato);
				if (aux <= 0 || aux > 20) {
					JOptionPane.showMessageDialog(null, "Error de una cantidad valida.");	
				} else
					listaPiezas.add(aux);
			}
			Calendar fecha1 = new GregorianCalendar();
			int ano = fecha1.get(Calendar.YEAR);
			FechaDeEntrega = ano + "/" + mes + "/" + dia;
			controlGenerarPedido.generarPedidoProveedor(lista, listaPiezas, FechaDeEntrega, empleado);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al leer la tabla.");
		}
	}

	public void limpia() {
		int filas = table.getRowCount();
		try {
			for (int i = 0; filas > i; i++) {
				modelo.removeRow(0);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
		}
	}

	public void oculta() {
		setVisible(false);

	}

}
