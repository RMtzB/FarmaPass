package mx.uam.ayd.proyecto.presentacion.principal.encargado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.ControlInventario;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipalEncargado extends JFrame {

	private JPanel contentPane;
	private ControlPrincipalEncargado control;
	private Empleado empleado;
	private JTextField txtNombreEmpleado;
	private JTextField txtNivel;

	public VentanaPrincipalEncargado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 307);
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
		btnCierreVenta.setBounds(203, 11, 156, 23);
		panel_2.add(btnCierreVenta);
		panel_1.setLayout(null);

		JButton btnVenta = new JButton("Venta");
		btnVenta.setBounds(10, 30, 97, 23);
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

		JButton btnhistorial = new JButton("Historial de ventas");
		btnhistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.muestraHistorialDeVenta(empleado);
			}
		});
		btnhistorial.setBounds(146, 30, 128, 23);
		panel_1.add(btnhistorial);
		
		JButton btnpedido = new JButton("Generar pedido");
		
		btnpedido.setBounds(10, 77, 128, 23);
		panel_1.add(btnpedido);
		
		JButton invent = new JButton("Inventario");
		invent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

    			control.start(empleado);
//				scrollPaneCierreVenta.setViewportView(tabla_inventario);
			}
		});
		
		invent.setBounds(199, 77, 89, 23);
		panel_1.add(invent);
		contentPane.add(panel_2);

		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.cerrarSesion();
			}
		});
		btnCerrarSesion.setBounds(10, 11, 124, 23);
		panel_2.add(btnCerrarSesion);
	
		btnpedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.crearPedido(empleado);
			}
		});
		
	}

	public void muestra(ControlPrincipalEncargado control, Empleado empleado) {
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

