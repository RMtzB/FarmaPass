package mx.uam.ayd.proyecto.presentacion.HistorialDeVentas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.principal.encargado.ControlPrincipalEncargado;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
@Component
public class VentanaHistorialDeVentas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ControlHistorialDeVentas controlHistorialDeVentas;
	private Empleado empleado;
	private ControlPrincipalEncargado controlPrincipalEncargado;
	

	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};

	/**
	 * Create the frame.
	 */
	public VentanaHistorialDeVentas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Ventas por:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		JButton btndia = new JButton("D\u00EDa");

		panel.add(btndia);

		JButton btnmes = new JButton("Mes");

		panel.add(btnmes);

		JButton btnyear = new JButton("A\u00F1o");

		panel.add(btnyear);

		JButton btntodo = new JButton("Todo ");

		panel.add(btntodo);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable(modelo);

		modelo.addColumn("Fecha");
		modelo.addColumn("Producto");
		modelo.addColumn("Precio ");
		modelo.addColumn("Cantidad");

		scrollPane.setViewportView(table);

		JButton btnregresar = new JButton("Regresar");

		contentPane.add(btnregresar, BorderLayout.SOUTH);

		// Listeners
		btndia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				Calendar fecha = new GregorianCalendar();
				int ano = fecha.get(Calendar.YEAR);
				int mes = fecha.get(Calendar.MONTH);
				// System.out.println(mes);
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				String fechaF = ano + "/" + mes + "/" + dia;
				controlHistorialDeVentas.obtenerVentas(fechaF);
			}
		});

		btnmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				Calendar fecha = new GregorianCalendar();
				int ano = fecha.get(Calendar.YEAR);
				int mes = fecha.get(Calendar.MONTH);
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				for (int i = 1; i < 32; i++) {
					dia = i;
					String fechaF = ano + "/" + mes + "/" + dia;
					controlHistorialDeVentas.obtenerVentas(fechaF);
				}

			}
		});

		btnyear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				Calendar fecha = new GregorianCalendar();
				int ano = fecha.get(Calendar.YEAR);
				int mes = fecha.get(Calendar.MONTH);
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				for (int m = 1; m < 13; m++) {
					for (int d = 1; d < 32; d++) {
						dia = d;
						mes = m;
						String fechaF = ano + "/" + mes + "/" + dia;
						controlHistorialDeVentas.obtenerVentas(fechaF);
					}
				}
				
			}
		});

		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				controlHistorialDeVentas.cerrar(empleado);
			}
		});

		btntodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				controlHistorialDeVentas.obtenerTodasLasVentas();
			}
		});

	}

	public void despliega(ControlHistorialDeVentas controlHistorialDeVentas, Empleado empleado) {
		this.controlHistorialDeVentas = controlHistorialDeVentas;
		this.empleado = empleado;
		setVisible(true);
		System.out.println(empleado);

	}
   // A�ade a la tabla de la interfaz los campos correspondientes
	public void agregarAtabla(Venta venta, List<Producto> productos) {
		String a[] = new String[4];
		for (Producto producto : productos) {
			a[0] = venta.getFecha();
			a[1] = producto.getCompuesto();
			a[2] = String.valueOf(producto.getPrecio());
			a[3] = String.valueOf(producto.getPiezas());
			modelo.addRow(a);
			table.setModel(modelo);
		}
		
	}
	
	public void oculta() {
		setVisible(false);
	}
	// limpia todos los campos de la tabla
	public void limpia() {
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
