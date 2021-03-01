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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana encargada de desplegar el historial de ventas de un cliente.
 * 
 * @author Cristo Carlos Lopez Manzano
 * @since 10/02/2021
 */
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
	JButton btnModificar;
	int id;

	/**
	 * Create the frame.
	 */
	public VentanaHistorialCliente() {
		setTitle("Historial de: cliente");
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
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (verificar()) {
					txtDescuento.setEditable(false);
					btnConfirmar.setEnabled(false);
					btnModificar.setEnabled(true);
					controlHistorialCliente.actualizarDescuentoActual(txtDescuento.getText());
				}
			}
		});
		btnConfirmar.setEnabled(false);
		btnConfirmar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnConfirmar.setBounds(523, 11, 89, 23);
		panel_1.add(btnConfirmar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDescuento.setEditable(false);
				btnConfirmar.setEnabled(false);
				btnModificar.setEnabled(true);
				dispose();
			}
		});
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
		txtDescuento.setEditable(false);
		txtDescuento.setBounds(191, 20, 22, 20);
		panel_2.add(txtDescuento);
		txtDescuento.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDescuento.setEditable(true);
				btnConfirmar.setEnabled(true);
				btnModificar.setEnabled(false);
			}
		});
		btnModificar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnModificar.setBounds(236, 19, 89, 23);
		panel_2.add(btnModificar);

		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(215, 22, 22, 14);
		panel_2.add(lblNewLabel_1);

		tablaHistorial = new JTable(modeloHistorial) { // 1.2
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		txtDescuento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		modeloHistorial.addColumn("Fecha");
		modeloHistorial.addColumn("Cantidad de Productos");
		modeloHistorial.addColumn("Total");
		Dimension a = panel.getSize();
		Dimension b = new Dimension(a.width - 10, a.width - 10);
		tablaHistorial.setPreferredScrollableViewportSize(b);
		tablaHistorial.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
	}

	/**
	 * muestra: inicializa la ventana con los datos recibidos
	 * 
	 * @param controlHistorialClientes : control correspondiente
	 * @param id                       : id del cliente del cual se desplegara la ventana y su historial
	 * @param desc                     : descuento actual que tiene este cliente.
	 * @param nombre                   : nombre del cliente del cual se desplegara
	 *                                 la informacion
	 */
	public void muestra(ControlHistorialCliente controlHistorialClientes, int id, int desc, String nombre) {
		this.controlHistorialCliente = controlHistorialClientes;
		this.setVisible(true);
		limpiarTabla();
		controlHistorialClientes.llenarTabla(id);
		scrollPane.setViewportView(tablaHistorial);
		txtDescuento.setText(desc + "");
		setTitle("Historial de : " + nombre);
	}

	/**
	 * limpiarTabla : limpia la tabla, eliminando todos los elementos
	 */
	public void limpiarTabla() { // 3
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

	/**
	 * agregarVenta : agrega un renglon con la informacion de una venta a la tabla
	 * @param venta : informacion de la venta dada en un string separado por %
	 */
	public void agregarVenta(String venta) {
		String e[] = venta.split("%");
		String a[] = new String[3];
		a[0] = e[0];
		a[2] = e[1];
		a[1] = e[2];
		modeloHistorial.addRow(a);
		tablaHistorial.setModel(modeloHistorial);
	}

	/**
	 * verificar : verifica que el cuadro de texto txtDescuento no se encuentre vacio
	 * 				asi mismo que el dato ingresado sea mayor que 5 y menor que 15
	 * @return : devuelve true si cumple con todas las condiciones y false de forma contraria
	 */
	private boolean verificar() {
		String aux = txtDescuento.getText();
		if (aux == "") {
			JOptionPane.showMessageDialog(null, "Ingresa un valor");
			return false;
		} else {
			int auxili = Integer.parseInt(aux);
			if ((auxili < 5 || auxili > 15) && auxili != 0) {
				JOptionPane.showMessageDialog(null,
						"El valor debe estar en el rango 5 a 15 o 0 para eliminar descuento de clente");
				return false;
			} else
				return true;
		}
	}
}
