package mx.uam.ayd.proyecto.presentacion.recarga;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VentanaRecarga extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRecarga frame = new VentanaRecarga();
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
	public VentanaRecarga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Seleccione compañía");
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(285, 220, 148, 48);
		comboBox.addItem("Movistar");
		comboBox.addItem("Telcel");
		comboBox.addItem("Unefón");
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Seleccione monto");
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setBounds(285, 138, 148, 48);
		comboBox_1.addItem("...");
		comboBox_1.addItem("20");
		comboBox_1.addItem("50");
		comboBox_1.addItem("100");
		comboBox_1.addItem("150");
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Monto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(119, 144, 66, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblCompaa = new JLabel("Compañía");
		lblCompaa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCompaa.setBounds(119, 231, 95, 21);
		contentPane.add(lblCompaa);
		
		JLabel lblNmero = new JLabel("Número");
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmero.setBounds(119, 68, 66, 30);
		contentPane.add(lblNmero);
		
		textField = new JTextField();
		textField.setToolTipText("Ingrese número");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(285, 68, 148, 41);
		contentPane.add(textField);
		textField.setColumns(1);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(442, 316, 95, 30);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(300, 316, 95, 30);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("RECARGA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(154, 10, 284, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Farmapass");
		lblNewLabel_1_1.setBounds(10, 10, 65, 13);
		contentPane.add(lblNewLabel_1_1);
	}
}
