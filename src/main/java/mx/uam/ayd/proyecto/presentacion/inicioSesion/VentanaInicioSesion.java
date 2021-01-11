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
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VentanaPrincipal frame = new
	 * VentanaPrincipal(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public VentanaInicioSesion() {
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(0, 0, 334, 28);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblFarmapass = new JLabel("FARMAPASS");
		lblFarmapass.setBounds(0, 11, 309, 10);
		panel.add(lblFarmapass);
		lblFarmapass.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_form = new JPanel();
		panel_form.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_form.setBounds(10, 66, 314, 76);
		contentPane.add(panel_form);
		panel_form.setLayout(null);

		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setBounds(32, 17, 62, 14);
		panel_form.add(lblUsuario);

		JLabel lblPassword = new JLabel("CONTRASEÑA:");
		lblPassword.setBounds(10, 45, 84, 14);
		panel_form.add(lblPassword);

		txtUsuario = new JTextField();
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
		btnIniciaSesion.setBounds(200, 153, 124, 23);
		contentPane.add(btnIniciaSesion);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 32, 314, 28);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblLeyenda = new JLabel("Ingresa tu usuario y contraseña para iniciar sesion: ");
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
