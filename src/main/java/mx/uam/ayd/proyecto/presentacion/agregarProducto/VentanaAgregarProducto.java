package mx.uam.ayd.proyecto.presentacion.agregarProducto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
@Component
public class VentanaAgregarProducto extends JFrame implements AgregarProducto.VentanaAddProduct {
	private JPanel contentPane;
	private JTextField jtfNombre;
	private JTextField jtfUbicacion;
	private JTextField jtfPrecio;
	private JTextField jtfComponenteActivo;
	private JTextField jtfPiezas;

	/**
	 * Create the frame.
	 */
	public VentanaAgregarProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNombre.setBounds(12, 12, 70, 15);
		contentPane.add(lblNombre);
		
		jtfNombre = new JTextField();
		jtfNombre.setBounds(12, 30, 150, 20);
		jtfNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(jtfNombre);
		jtfNombre.setColumns(10);
		
		
		
		JLabel lblUbicacin = new JLabel("Ubicación:");
		lblUbicacin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUbicacin.setBounds(288, 12, 85, 15);
		contentPane.add(lblUbicacin);
		
		jtfUbicacion = new JTextField();
		jtfUbicacion.setBounds(288, 30, 150, 20);
		jtfUbicacion.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(jtfUbicacion);
		jtfUbicacion.setColumns(10);
		
		
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrecio.setBounds(12, 80, 70, 15);
		contentPane.add(lblPrecio);
		
		jtfPrecio = new JTextField();
		jtfPrecio.setBounds(12, 98, 114, 20);
		jtfPrecio.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(jtfPrecio);
		jtfPrecio.setColumns(10);
		
		
		
		JLabel lblComponenteActivo = new JLabel("Componente activo:");
		lblComponenteActivo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblComponenteActivo.setBounds(166, 80, 170, 15);
		contentPane.add(lblComponenteActivo);
		
		jtfComponenteActivo = new JTextField();
		jtfComponenteActivo.setBounds(166, 98, 272, 20);
		jtfComponenteActivo.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(jtfComponenteActivo);
		jtfComponenteActivo.setColumns(10);
		
		
		
		JLabel lblPiezas = new JLabel("Piezas:");
		lblPiezas.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPiezas.setBounds(12, 150, 70, 15);
		contentPane.add(lblPiezas);
		
		jtfPiezas = new JTextField();
		jtfPiezas.setBounds(12, 168, 114, 20);
		jtfPiezas.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(jtfPiezas);
		jtfPiezas.setColumns(10);
		
		
		
		JCheckBox chckbxNecesitaReceta = new JCheckBox("Necesita receta");
		chckbxNecesitaReceta.setFont(new Font("Dialog", Font.BOLD, 14));
		chckbxNecesitaReceta.setBounds(283, 168, 155, 23);
		contentPane.add(chckbxNecesitaReceta);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(12, 226, 117, 25);
		contentPane.add(btnCancelar);
		
		//ListenerCancelar
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(321, 226, 117, 25);
		contentPane.add(btnAgregar);
		
		//ListenerAgregar
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

	@Override
	public void showMessagetoConfirmData(String mensaje) {
	}
}
