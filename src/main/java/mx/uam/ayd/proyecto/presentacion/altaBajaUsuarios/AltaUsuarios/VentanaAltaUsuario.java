package mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.AltaUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@Component
public class VentanaAltaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCelular;
	private JTextField txtApellido;
	private JTextField txtApeM;
	private JTextField txtCorreo;
	private JTextField txtUsuario;
	private JPasswordField txtContra;
	private JPasswordField txtContra1;
	private ControlAltaUsuario controlAltaUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaUsuario frame = new VentanaAltaUsuario();
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
	public VentanaAltaUsuario() {
		setTitle("Agregar Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 374, 198);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(23, 11, 46, 14);
		panel.add(lblNewLabel);

		txtNombre = new JTextField();
		txtNombre.setBounds(23, 26, 100, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(213, 64, 131, 20);
		panel.add(txtCelular);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(213, 48, 46, 14);
		panel.add(lblCelular);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(23, 85, 100, 20);
		panel.add(txtApellido);

		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno");
		lblApellidoPaterno.setBounds(23, 70, 86, 14);
		panel.add(lblApellidoPaterno);

		txtApeM = new JTextField();
		txtApeM.setColumns(10);
		txtApeM.setBounds(23, 151, 100, 20);
		panel.add(txtApeM);

		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setBounds(23, 136, 86, 14);
		panel.add(lblApellidoMaterno);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(213, 123, 131, 20);
		panel.add(txtCorreo);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(213, 107, 46, 14);
		panel.add(lblCorreo);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 220, 374, 115);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(10, 52, 86, 20);
		panel_1.add(txtUsuario);

		JLabel lblNewLabel_6 = new JLabel("Usuario");
		lblNewLabel_6.setBounds(10, 37, 46, 14);
		panel_1.add(lblNewLabel_6);

		txtContra = new JPasswordField();
		txtContra.setColumns(10);
		txtContra.setBounds(216, 26, 86, 20);
		panel_1.add(txtContra);

		JLabel lblNewLabel_7 = new JLabel("Contraseña");
		lblNewLabel_7.setBounds(216, 11, 86, 14);
		panel_1.add(lblNewLabel_7);

		txtContra1 = new JPasswordField();
		txtContra1.setColumns(10);
		txtContra1.setBounds(216, 72, 86, 20);
		panel_1.add(txtContra1);

		JLabel lblNewLabel_8 = new JLabel("Verifica Contraseña");
		lblNewLabel_8.setBounds(216, 57, 106, 14);
		panel_1.add(lblNewLabel_8);
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verif()) {
					controlAltaUsuario.agregarUsuario( txtNombre.getText(),
														txtApellido.getText(),
														txtApeM.getText(),
														txtCorreo.getText(),
														txtCelular.getText(),
														txtUsuario.getText(),
														new String(txtContra.getPassword())	);
					reset();
					dispose();
				}
				

			}
		});
		btnAceptar.setBounds(279, 360, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				dispose();
			}
		});
		btnSalir.setBounds(10, 360, 89, 23);
		contentPane.add(btnSalir);
	}
	
	
	public void mostrar(ControlAltaUsuario c) {
		controlAltaUsuario=c;
		setVisible(true);
	}

	private boolean verif() {
		if (txtApellido.getText() == "" || txtApeM.getText() == "" || txtCelular.getText() == ""
				|| txtCorreo.getText() == "" || txtNombre.getText() == "" || txtUsuario.getText() == "") {
			JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
			return false;
		}
		if (txtContra.getPassword() == null || txtContra1.getPassword() == null) {
			JOptionPane.showMessageDialog(null, "Todos los campos son requeridos");
			return false;
		} else {

			if ( new String(txtContra.getPassword()) .equals(new String(txtContra1.getPassword())) )
				return true;
			else {
				JOptionPane.showMessageDialog(null, "tu contraseña no coincide "+new String(txtContra.getPassword())+"  "+new String(txtContra1.getPassword()));
				return false;
			}
		}

	}
	
	public void reset() {
		
		txtNombre.setText("");
		txtApellido.setText("");
		txtApeM.setText("");
		txtCorreo.setText("");
		txtCelular.setText("");
		txtUsuario.setText("");
		txtContra.setText("");
		txtContra1.setText("");
		
		
	}
}
