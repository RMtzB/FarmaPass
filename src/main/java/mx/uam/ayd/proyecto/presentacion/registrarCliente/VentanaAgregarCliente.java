package mx.uam.ayd.proyecto.presentacion.registrarCliente;

import mx.uam.ayd.proyecto.utils.RegistrarCliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
@Component
public class VentanaAgregarCliente extends JFrame implements RegistrarCliente.AddClientWindow {
	private ControlAgregarCliente addClientControl;
	
	
	
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfEmail;
	private JTextField tfNumber;

	/**
	 * Create the frame.
	 */
	public VentanaAgregarCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 64, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Dialog", Font.BOLD, 14));
		lblApellidos.setBounds(247, 13, 76, 14);
		contentPane.add(lblApellidos);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(10, 36, 154, 20);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellidos = new JTextField();
		tfApellidos.setColumns(10);
		tfApellidos.setBounds(247, 36, 177, 20);
		contentPane.add(tfApellidos);
		
		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCorreoElectronico.setBounds(10, 91, 137, 14);
		contentPane.add(lblCorreoElectronico);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(10, 116, 154, 20);
		contentPane.add(tfEmail);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTelefono.setBounds(247, 93, 76, 14);
		contentPane.add(lblTelefono);
		
		tfNumber = new JTextField();
		tfNumber.setColumns(10);
		tfNumber.setBounds(247, 116, 154, 20);
		contentPane.add(tfNumber);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 172, 89, 23);
		contentPane.add(btnCancelar);
	

		
		/**
		 * Listener para el boton cancelar
		 */
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addClientControl.closeWindow();
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(335, 172, 89, 23);
		contentPane.add(btnGuardar);
		
		
		
		/**
		 * Listener para el boton guardar cliente
		 */
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addClientControl.addCliente(tfNombre.getText(),
											tfApellidos.getText(),
											tfEmail.getText(),
											tfNumber.getText());
			}
		});
	}

	
	
	/**
	 * 
	 * Método encargado de mostrar, y conectar la ventana con su control
	 * 
	 * @param addClientControl
	 */
	@Override
	public void show(ControlAgregarCliente addClientControl) {
		this.addClientControl = addClientControl;
		this.setVisible(true);
	}

	
	
	/**
	 * Método encargado de mostrar un diálogo con el cual el usuario podrá decidir
	 * si continua o no con la operación (Registrar cliente)
	 * 
	 * @param mensaje Detalle de los datos ingresados
	 * @return true Si el usurio elige la opción "Si"
	 * @return false Si el usuario elige la opción "No"
	 */
	@Override
	public boolean showMessagetoConfirmData(String mensaje) {
		return JOptionPane.showOptionDialog(this,
											mensaje,
											"Continuar",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.OK_CANCEL_OPTION,
											null,
											new Object[] {"Si", "No"},
											"Si") == JOptionPane.OK_OPTION;
	}

	
	
	/**
	 * 
	 * Método encargado de mostrar un mensaje
	 * de error (cuando algún dato ingresado es incorrecto)
	 * 
	 * @param message
	 */
	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	
	
	/**
	 * Método encargado de borrar de limpiar la ventana cuando es cerrada
	 */
	@Override
	public void resetWindow() {
		tfNombre.setText("");
		tfApellidos.setText("");
		tfEmail.setText("");
		tfNumber.setText("");
	}
}
