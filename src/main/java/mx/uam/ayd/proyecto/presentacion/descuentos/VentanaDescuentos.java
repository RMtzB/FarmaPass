package mx.uam.ayd.proyecto.presentacion.descuentos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
@Component
public class VentanaDescuentos extends JFrame {
	ControlDescuentos ctrlDesc;
	private JPanel contentPane;
	private JLabel lblInformacion;
	private JDateChooser dttxtFecha;
	private JTextField txtDescuento;
	JCheckBox chbxDuracion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDescuentos frame = new VentanaDescuentos();
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
	public VentanaDescuentos() {
		setTitle("Agregar Descuento");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setBounds(100, 100, 467, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblInformacion = new JLabel("Informacion del producto");
		lblInformacion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblInformacion.setBounds(20, 28, 399, 31);
		contentPane.add(lblInformacion);
		
		JLabel lblNewLabel_1 = new JLabel("Descuento:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 101, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		chbxDuracion = new JCheckBox("Activar duracion");
		chbxDuracion.setBounds(20, 186, 122, 23);
		contentPane.add(chbxDuracion);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		if(validado()) {
					if(chbxDuracion.isSelected())								
						ctrlDesc.agregarDescConDuracion(Integer.parseInt( txtDescuento.getText()) , dttxtFecha.getDate()+"");
					else
						ctrlDesc.agregarDesc(Integer.parseInt( txtDescuento.getText()));
		}		
				
			}
		});
		btnConfirmar.setBounds(345, 227, 89, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(246, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_2 = new JLabel("Termina el");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(198, 98, 70, 14);
		contentPane.add(lblNewLabel_2);
		
		dttxtFecha = new JDateChooser();
		dttxtFecha.setBounds(278, 95, 141, 20);
		contentPane.add(dttxtFecha);
		
			txtDescuento = new JTextField();
			txtDescuento.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if(txtDescuento.getText().length()>=3)
						e.consume();
					else {
					char c=e.getKeyChar();
					if(c<'0'||c>'9') e.consume();
					}
				}
			});
			txtDescuento.setBounds(87, 100, 61, 20);
			contentPane.add(txtDescuento);
	}

	public void mostrar(ControlDescuentos ctrlDesc,String producto) {
		this.ctrlDesc=ctrlDesc;
		lblInformacion.setText(producto);
		this.setVisible(true);
	}
	
	private boolean validado() {
		boolean r=false;
		
		if(!txtDescuento.getText().equals(""))
		{
			if(chbxDuracion.isSelected()) {
				
				if(!(dttxtFecha.getDate()==null))
					r= true;
				else JOptionPane.showMessageDialog(null, "Selecciona una fecha");
				
			}else r= true;
		
		} else JOptionPane.showMessageDialog(null, "El campo de Descuento no puede estar vacio");
		if( Integer.parseInt(txtDescuento.getText() )>100 ) {
			r=false;
			JOptionPane.showMessageDialog(null, "No se permiten descuentos mayores al 100%");
		}
	return r;	
	}
	
}
