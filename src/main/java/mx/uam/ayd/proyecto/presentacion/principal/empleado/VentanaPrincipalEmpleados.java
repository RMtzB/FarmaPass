package mx.uam.ayd.proyecto.presentacion.principal.empleado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipalEmpleados extends JFrame {

	private JPanel contentPane;
	private ControlPrincipalEmpleados control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;

	public VentanaPrincipalEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 369, 34);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 45, 369, 123);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 174, 369, 52);
		panel_2.setLayout(null);

		JButton btnCierreVenta = new JButton("Inicia cierre de venta");
		btnCierreVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaCierreVenta(empleado);
			}
		});
		btnCierreVenta.setForeground(new Color(255, 255, 255));
		btnCierreVenta.setBackground(new Color(255, 0, 0));
		btnCierreVenta.setBounds(10, 11, 156, 23);
		panel_2.add(btnCierreVenta);
		panel_1.setLayout(null);

		JButton btnVenta = new JButton("Venta");
		btnVenta.setBounds(10, 44, 349, 23);
		panel_1.add(btnVenta);
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agregarProductos();
			}
		});
		contentPane.setLayout(null);
		panel.setLayout(null);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setBounds(106, 6, 254, 22);
		panel.add(txtNombreEmpleado);
		txtNombreEmpleado.setColumns(10);

		txtNivel = new JTextField();
		txtNivel.setEditable(false);
		txtNivel.setBounds(10, 7, 86, 20);
		panel.add(txtNivel);
		txtNivel.setColumns(10);
		contentPane.add(panel);
		contentPane.add(panel_1);
		
		JButton btnNuevoPedidoCliente = new JButton("Pedido cliente");
		btnNuevoPedidoCliente.setEnabled(false);
		btnNuevoPedidoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agregaPedidoCliente(empleado);
			}
		});
		btnNuevoPedidoCliente.setBounds(199, 89, 160, 23);
		panel_1.add(btnNuevoPedidoCliente);
		
		JButton btnNewButtonBusqueda = new JButton("Busqueda");
		btnNewButtonBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.busqueda();
			}
		});
		btnNewButtonBusqueda.setBounds(10, 11, 349, 23);
		panel_1.add(btnNewButtonBusqueda);
		
		JButton btnRecepcionMercancia = new JButton("Recepcion de Mercancia");
		btnRecepcionMercancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.iniciaRecepcionMercancia(empleado);
			}
		});
		btnRecepcionMercancia.setBounds(10, 89, 179, 23);
		panel_1.add(btnRecepcionMercancia);
		contentPane.add(panel_2);
	}

	public void muestra(ControlPrincipalEmpleados control, Empleado empleado) {
		this.control = control;
		this.empleado = empleado;
		this.txtNombreEmpleado
				.setText(empleado.getNombre() + " " + empleado.getApellidoP() + " " + empleado.getApellidoM());
		this.txtNivel.setText(empleado.getNivel() + ":");
		setVisible(true);

	}

	public void oculta() {
		setVisible(false);
	}
}
