package mx.uam.ayd.proyecto.presentacion.ventasEmpleados;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@SuppressWarnings("serial")
@Component
public class VentanaVentasdeEmpleados extends JFrame {
	
	private JPanel contentPane;
	private ControlVentasdeEmpleados controlVentasdeEmpleados;
	private Empleado empleado;
	JLabel lblNewLabel_1;
	
	public VentanaVentasdeEmpleados() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Ventas de Empleado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel);
		
		 lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1);

		
	

		
		
	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	//Muestra la ventana
	public void muestra(ControlVentasdeEmpleados controlVentasdeEmpleados,Empleado empleado) {
		 this.controlVentasdeEmpleados= controlVentasdeEmpleados;
		 this.empleado= empleado;
		 
		 lblNewLabel_1.setText(empleado.getNivel());
		 setVisible(true);
	}
	
}
