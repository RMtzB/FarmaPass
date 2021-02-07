package mx.uam.ayd.proyecto.presentacion.agregarProducto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.utils.AgregarProductoAInventario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 * 
 * @author RaulMb
 *
 */
@SuppressWarnings("serial")
@Component
public class VentanaAgregarProductoAInventario extends JFrame implements AgregarProductoAInventario.VentanaAddProduct {
	private ControlAgregarProductoAInventario addProductControl;
	
	
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfUbicacion;
	private JTextField tfPrecio;
	private JTextField tfComponenteActivo;
	private JTextField tfPiezas;
	private JCheckBox chckbxNecesitaReceta;
	
	
	/**
	 * Create the frame.
	 */
	public VentanaAgregarProductoAInventario() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNombre.setBounds(12, 12, 70, 15);
		contentPane.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(12, 30, 150, 20);
		tfNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		
		
		JLabel lblUbicacin = new JLabel("Ubicación:");
		lblUbicacin.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUbicacin.setBounds(288, 12, 85, 15);
		contentPane.add(lblUbicacin);
		
		tfUbicacion = new JTextField();
		tfUbicacion.setBounds(288, 30, 150, 20);
		tfUbicacion.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(tfUbicacion);
		tfUbicacion.setColumns(10);
		
		
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrecio.setBounds(12, 80, 70, 15);
		contentPane.add(lblPrecio);
		
		tfPrecio = new JTextField();
		tfPrecio.setBounds(12, 98, 114, 20);
		tfPrecio.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(tfPrecio);
		tfPrecio.setColumns(10);
		
		
		
		JLabel lblComponenteActivo = new JLabel("Componente activo:");
		lblComponenteActivo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblComponenteActivo.setBounds(166, 80, 170, 15);
		contentPane.add(lblComponenteActivo);
		
		tfComponenteActivo = new JTextField();
		tfComponenteActivo.setBounds(166, 98, 272, 20);
		tfComponenteActivo.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(tfComponenteActivo);
		tfComponenteActivo.setColumns(10);
		
		
		
		JLabel lblPiezas = new JLabel("Piezas:");
		lblPiezas.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPiezas.setBounds(12, 150, 70, 15);
		contentPane.add(lblPiezas);
		
		tfPiezas = new JTextField();
		tfPiezas.setBounds(12, 168, 114, 20);
		tfPiezas.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(tfPiezas);
		tfPiezas.setColumns(10);
		
		
		
		chckbxNecesitaReceta = new JCheckBox("Necesita receta");
		chckbxNecesitaReceta.setFont(new Font("Dialog", Font.BOLD, 14));
		chckbxNecesitaReceta.setBounds(283, 168, 155, 23);
		contentPane.add(chckbxNecesitaReceta);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(12, 226, 117, 25);
		contentPane.add(btnCancelar);
		
		
		
		/**
		 * Listener para el boton cancelar
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductControl.closeWindow();
			}
		});
		
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(321, 226, 117, 25);
		contentPane.add(btnAgregar);
		
		
		
		/**
		 * Listener para el boton agregar producto
		 */
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProductControl.addProdruct(tfNombre.getText(),
												tfUbicacion.getText(),
												tfPrecio.getText(),
												tfComponenteActivo.getText(),
												tfPiezas.getText(),
												chckbxNecesitaReceta.isSelected());
			}
		});
	}

	
	/**
	 * Método encargado de mostrar un diálogo con el cual el usuario podrá decidir
	 * si continua o no con la operación (Registrar producto)
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
	 * Método encargado de mostrar, y conectar la ventana con su control
	 * 
	 * @param addProductControl
	 */
	@Override
	public void show(ControlAgregarProductoAInventario addProductControl) {
		this.addProductControl = addProductControl;
		this.setVisible(true);
	}

	/**
	 * Método encargado de mostrar un mensaje
	 * de error (cuando algun dato ingresado es incorrecto)
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
		tfUbicacion.setText("");
		tfPrecio.setText("");
		tfComponenteActivo.setText("");
		tfPiezas.setText("");
		chckbxNecesitaReceta.setSelected(false);
	}
}
