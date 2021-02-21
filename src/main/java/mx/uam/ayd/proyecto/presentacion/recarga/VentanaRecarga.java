package mx.uam.ayd.proyecto.presentacion.recarga;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaRecarga extends JFrame {

	private JPanel contentPane;
	private JTextField textNumero;
	private ControlRecarga controlRecarga;
	private ImageIcon img1 , img2 , img3, img4;
	private String compañia,usuario;
	JComboBox <String>comboBox_1;

	/**
	 * Create the frame.
	 */
	public VentanaRecarga() {
		
		setTitle("Recarga");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 602, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		 comboBox_1 = new JComboBox<>();
		comboBox_1.setToolTipText("Seleccione monto");
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setBounds(122, 286, 145, 38);
		comboBox_1.addItem("...");
		comboBox_1.addItem("20");
		comboBox_1.addItem("50");
		comboBox_1.addItem("100");
		comboBox_1.addItem("150");
		contentPane.add(comboBox_1);
		
	
		JLabel lblNewLabel = new JLabel("Monto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(30, 290, 66, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNmero = new JLabel("Número");
		lblNmero.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNmero.setBounds(24, 214, 66, 30);
		contentPane.add(lblNmero);
		
		textNumero = new JTextField();
		textNumero.setToolTipText("Ingrese número");
		textNumero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textNumero.setBounds(122, 214, 243, 30);
		contentPane.add(textNumero);
		textNumero.setColumns(1);
		 /**
		  * Verifica el sistema si no hay campos vacios, cuando hayan se muestran mensajes hasta que se llenen 
		  */
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textNumero.getText()=="") {
						muestraDialogoConMensaje("Ingrese numero de telefono");
						
					}else {

						if((String) comboBox_1.getSelectedItem() == "...") {
							muestraDialogoConMensaje("No se a ingresado monto");	
						}else {
							if(textNumero.getText().length() != 10) {
								muestraDialogoConMensaje("No son diez digitos");
								
							}else
								controlRecarga.iniciaConfirmacion(Integer.valueOf(textNumero.getText()), compañia, Integer.valueOf((String) comboBox_1.getSelectedItem()),usuario);
						}
						
					}
										
				}catch (Exception ex) {
					
				}
						
			}
		});
		btnNewButton.setBounds(423, 369, 95, 30);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlRecarga.termina();
			}
		});
		btnCancelar.setBounds(289, 369, 95, 30);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("RECARGA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(154, 10, 284, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Farmapass");
		lblNewLabel_1_1.setBounds(10, 10, 65, 13);
		contentPane.add(lblNewLabel_1_1);
		
		
		/**
		 * Botones de compañia cuando se oprime uno se habilita la casilla de numeros
		 */
		JButton btnTelcel = new JButton("");
		JLabel lblTelcel = new JLabel("");	
		lblTelcel.setAutoscrolls(true);
		ImageIcon telcel= new ImageIcon(getClass().getResource("Telcel.png"));
		Image image1 = telcel.getImage();
		img1 = new ImageIcon(image1.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		btnTelcel.setIcon(img1);
		contentPane.add(lblTelcel);
		btnTelcel.setOpaque(false);
		btnTelcel.setContentAreaFilled(false);
		btnTelcel.setBorderPainted(false);
		
		btnTelcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compañia = "Telcel";
				textNumero.setEditable(true);
				
			}
		});
		btnTelcel.setBounds(30, 111, 95, 65);
		contentPane.add(btnTelcel);
		
		JButton btnMovistar = new JButton("");
		JLabel lblMovistar = new JLabel("");	
		lblTelcel.setAutoscrolls(true);
		ImageIcon movistar= new ImageIcon(getClass().getResource("Movistar.png"));
		Image image2 = movistar.getImage();
		img2 = new ImageIcon(image2.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		btnMovistar.setIcon(img2);
		contentPane.add(lblMovistar);
		
		btnMovistar.setOpaque(false);
		btnMovistar.setContentAreaFilled(false);
		btnMovistar.setBorderPainted(false);
		btnMovistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compañia = "Movistar";
				textNumero.setEditable(true);
			}
		});
		btnMovistar.setBounds(172, 111, 95, 65);
		contentPane.add(btnMovistar);
		
		JButton btnAtyt = new JButton("");
		JLabel lblAtyt = new JLabel("");	
		lblAtyt.setAutoscrolls(true);
		ImageIcon Atyt= new ImageIcon(getClass().getResource("AT&T.png"));
		Image image3 = Atyt.getImage();
		img3 = new ImageIcon(image3.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		btnAtyt.setIcon(img3);
		contentPane.add(lblAtyt);
		btnAtyt.setOpaque(false);
		btnAtyt.setContentAreaFilled(false);
		btnAtyt.setBorderPainted(false);
		
		btnAtyt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compañia = "AT&T";
				textNumero.setEditable(true);
			}
		});
		btnAtyt.setBounds(308, 111, 107, 65);
		contentPane.add(btnAtyt);
		
		JButton btnUnefon = new JButton("");
		JLabel lblUnefon = new JLabel("");
		lblUnefon.setAutoscrolls(true);
		ImageIcon unefon= new ImageIcon(getClass().getResource("Unefon.png"));
		Image image4 = unefon.getImage();
		img4 = new ImageIcon(image4.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		btnUnefon.setIcon(img4);
		contentPane.add(lblUnefon);
		btnUnefon.setOpaque(false);
		btnUnefon.setContentAreaFilled(false);
		btnUnefon.setBorderPainted(false);
		
		
		btnUnefon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compañia = "Unefon";
				textNumero.setEditable(true);
			}
		});
		btnUnefon.setBounds(457, 111, 95, 65);
		contentPane.add(btnUnefon);
		
		JLabel lblSeleccioneCompaia = new JLabel("Seleccione compañia:");
		lblSeleccioneCompaia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeleccioneCompaia.setBounds(20, 51, 200, 30);
		contentPane.add(lblSeleccioneCompaia);
	}
	
	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void muestra(ControlRecarga controlRecarga,String usuario) {
		this.controlRecarga= controlRecarga;
		textNumero.setEditable(false);
		textNumero.setText("");
		this.usuario=usuario;
		setVisible(true);
		
	}
}
