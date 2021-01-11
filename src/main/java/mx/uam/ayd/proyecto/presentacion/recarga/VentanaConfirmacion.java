package mx.uam.ayd.proyecto.presentacion.recarga;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaConfirmacion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConfirmacion frame = new VentanaConfirmacion();
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
	public VentanaConfirmacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(294, 71, 168, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNmero = new JLabel("Número");
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmero.setBounds(125, 69, 66, 30);
		contentPane.add(lblNmero);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMonto.setBounds(125, 154, 66, 30);
		contentPane.add(lblMonto);
		
		JLabel lblNmero_1_1 = new JLabel("Compañía");
		lblNmero_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmero_1_1.setBounds(125, 238, 93, 30);
		contentPane.add(lblNmero_1_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(294, 154, 168, 34);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(294, 238, 168, 34);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("CONFIRMACIÓN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(160, 10, 266, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(472, 304, 95, 30);
		contentPane.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(330, 304, 95, 30);
		contentPane.add(btnEditar);
		
		JLabel lblNewLabel_1 = new JLabel("Farmapass");
		lblNewLabel_1.setBounds(10, 10, 65, 13);
		contentPane.add(lblNewLabel_1);
	}
}
