package mx.uam.ayd.proyecto.presentacion.recarga;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaConfirmacion extends JFrame {

	private JPanel contentPane;
	private JTextField textNumero;
	private JTextField textMonto;
	private JTextField textCompañia;
	private ControlRecarga controlRecarga;
	private JProgressBar progressBar;

	

	/**
	 * Create the frame.
	 */
	public VentanaConfirmacion() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textNumero = new JTextField();
		textNumero.setEditable(false);
		textNumero.setBounds(294, 71, 168, 34);
		contentPane.add(textNumero);
		textNumero.setColumns(10);
		
		progressBar= new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setBounds(191, 357, 208, 20);
		contentPane.add(progressBar);
		
		JLabel lblHaciendoRecarga = new JLabel("Haciendo Recarga:");
		lblHaciendoRecarga.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHaciendoRecarga.setVisible(false);
		lblHaciendoRecarga.setBounds(41, 347, 208, 30);
		contentPane.add(lblHaciendoRecarga);
		
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
		
		textMonto = new JTextField();
		textMonto.setEditable(false);
		textMonto.setColumns(10);
		textMonto.setBounds(294, 154, 168, 34);
		contentPane.add(textMonto);
		
		textCompañia = new JTextField();
		textCompañia.setEditable(false);
		textCompañia.setColumns(10);
		textCompañia.setBounds(294, 238, 168, 34);
		contentPane.add(textCompañia);
		
		JLabel lblNewLabel = new JLabel("CONFIRMACIÓN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(160, 10, 266, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				progressBar.setVisible(true);
				lblHaciendoRecarga.setVisible(true);
				Thread hilo=new Thread(){
					public void run(){
						for(int i=1;i<=100;i++) {
							try {
								sleep(10);
								progressBar.setValue(i);
							} catch (InterruptedException ex) {
								JOptionPane.showMessageDialog(null, "No se acepto");
							}
						}
					}
				};
				hilo.start();
				
				controlRecarga.iniciaCobro(Integer.valueOf(textMonto.getText()));
				
			}
		});
		btnNewButton.setBounds(472, 304, 95, 30);
		contentPane.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlRecarga.terminaE();
			}
		});
		btnEditar.setBounds(330, 304, 95, 30);
		contentPane.add(btnEditar);
		
		JLabel lblNewLabel_1 = new JLabel("Farmapass");
		lblNewLabel_1.setBounds(10, 10, 65, 13);
		contentPane.add(lblNewLabel_1);
		
		
		
	   
		
	}
	public void muestra(ControlRecarga controlRecarga,int numero,String compañia,int monto) {
		this.controlRecarga = controlRecarga;
		textNumero.setText(String.valueOf(numero));
		textCompañia.setText(compañia);
		textMonto.setText(String.valueOf(monto));
		setVisible(true);

	}
}
