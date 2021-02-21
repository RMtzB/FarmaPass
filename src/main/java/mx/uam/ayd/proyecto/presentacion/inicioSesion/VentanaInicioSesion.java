package mx.uam.ayd.proyecto.presentacion.inicioSesion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.stereotype.Component;
import javax.swing.border.TitledBorder;
import java.awt.Font;

@SuppressWarnings("serial")
@Component
public class VentanaInicioSesion extends JFrame {

	private JPanel contentPane;

	private ControlInicioSesion control;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private String usuario;
	private String password;
	
	

	/**
	 * Create the frame.
	 */
	public VentanaInicioSesion() {
		
		setTitle("Inicio de sesión");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 0, 314, 34);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFarmapass = new JLabel("FARMAPASS");
		lblFarmapass.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));
		lblFarmapass.setBounds(10, 11, 309, 17);
		panel.add(lblFarmapass);
		lblFarmapass.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_form = new JPanel();
		panel_form.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_form.setBounds(10, 84, 314, 80);
		contentPane.add(panel_form);
		panel_form.setLayout(null);

		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblUsuario.setBounds(32, 17, 62, 14);
		panel_form.add(lblUsuario);

		JLabel lblPassword = new JLabel("CONTRASEÑA:");
		lblPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblPassword.setBounds(10, 45, 84, 14);
		panel_form.add(lblPassword);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		txtUsuario.setBounds(104, 11, 200, 20);
		panel_form.add(txtUsuario);
		txtUsuario.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(104, 42, 200, 20);
		panel_form.add(passwordField);

		JButton btnIniciaSesion = new JButton("INICIA SESION");
		btnIniciaSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validaCamposVacios()) {
					control.validaUsuario(usuario, password);
				} else {
					JOptionPane.showMessageDialog(null, "Verfique que los campos estan correctamente llenos!",
							"Error de inicio de sesión!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIniciaSesion.setBackground(new Color(50, 205, 50));
		btnIniciaSesion.setBounds(201, 175, 124, 23);
		contentPane.add(btnIniciaSesion);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 45, 314, 28);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLeyenda = new JLabel("Ingresa tu usuario y contraseña para iniciar sesion: ");
		lblLeyenda.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		lblLeyenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeyenda.setBounds(0, 0, 314, 28);
		panel_1.add(lblLeyenda);
	}

	private boolean validaCamposVacios() {
		if (txtUsuario.getText().isEmpty() || passwordField.getPassword().length < 8) {
			return false;
		} else {
			this.usuario = txtUsuario.getText();
			this.password = new String(passwordField.getPassword());
			return true;

		}

	}

	public void muestra(ControlInicioSesion control) {

		this.control = control;

		setVisible(true);

	}

	public void muestraErrorPassword(Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error de inicio de sesión!", JOptionPane.ERROR_MESSAGE);
	}

	public void oculta() {
		setVisible(false);
		
	}
}
