package mx.uam.ayd.proyecto.presentacion.GenerarPedido;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.PedidoProveedor;
import mx.uam.ayd.proyecto.negocio.modelo.Producto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaConfirmacionDePedido extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private DefaultTableModel modelo = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private ControlConfirmacionDeGenerarPedido controlConfirmacionDeGenerarPedido;
	private Empleado empleado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfirmacionDePedido frame = new VentanaConfirmacionDePedido();
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
	public VentanaConfirmacionDePedido() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lbllista = new JLabel("Lista de productos a abastecer");
		contentPane.add(lbllista, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnreturn2 = new JButton("Regresar");

		panel.add(btnreturn2);

		table = new JTable(modelo);

		modelo.addColumn("Productos a surtir");
		modelo.addColumn("Numero de productos ");
		modelo.addColumn("Fecha de recepcion");

		scrollPane.setViewportView(table);

		// Listeners
		btnreturn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpia();
				controlConfirmacionDeGenerarPedido.limpiarTabla();
				controlConfirmacionDeGenerarPedido.cierra(empleado);
			}
		});

	}
    
	/**
	 * Método que // llena latbla para el pedido con los parametros validados previamente
	 * 
	 * @param controlConfirmacionDeGenerarPedido, lista, listaPiezas, pedido 1 , empleado
	 */
	public void despliega(ControlConfirmacionDeGenerarPedido controlConfirmacionDeGenerarPedido, List<Producto> lista, List<Integer> listaPiezas, PedidoProveedor pedido1, Empleado empleado) {
		this.empleado = empleado;
		this.controlConfirmacionDeGenerarPedido = controlConfirmacionDeGenerarPedido;
		llenarTabla(lista,listaPiezas,pedido1);
		setVisible(true);
		
	}
    
	/**
	 * Método que // llena la tabla para obtener los parametros que iran a la confirmacion del pedido
	 * 
	 * @param lista, listaPiezas, pedido1
	 */
	private void llenarTabla(List<Producto> lista, List<Integer> listaPiezas, PedidoProveedor pedido1) {
		int contador = 0 ;
		for (Producto producto : lista) {
			String a[] = new String[3];
			a[0] = producto.getNombre();
			a[1] = String.valueOf(listaPiezas.get(contador));
			a[2] = pedido1.getFechaDeCreacion();
			modelo.addRow(a);
			table.setModel(modelo);
			contador ++;
		}
		
	}

	public void oculta() {
		setVisible(false);
	}
	
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
