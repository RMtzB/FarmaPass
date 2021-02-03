package mx.uam.ayd.proyecto.presentacion.descuentos.EliminarDescuentos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
@Component
public class VentanaEliminarDesuento extends JFrame {
	ControlEliminarDescuento controlEliminarDescuento;
	private JPanel contentPane;
	JLabel lblNombre;
	JLabel lblDescuento;
	JLabel lblFecha;
	/**
	 * Create the frame.
	 */
	public VentanaEliminarDesuento() {
		setTitle("Eliminar Descuento");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 523, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("Nombre Del Producto");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNombre.setBounds(106, 27, 337, 21);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel_1 = new JLabel("Descuento Actual:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 101, 110, 14);
		contentPane.add(lblNewLabel_1);
		
		lblDescuento = new JLabel("50%");
		lblDescuento.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
		lblDescuento.setBounds(134, 97, 63, 28);
		contentPane.add(lblDescuento);
		
		JLabel lblNewLabel_1_1 = new JLabel("A termintar el");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(261, 102, 110, 14);
		contentPane.add(lblNewLabel_1_1);
		
		lblFecha = new JLabel("15/12/2021");
		lblFecha.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
		lblFecha.setBounds(358, 97, 128, 28);
		contentPane.add(lblFecha);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlEliminarDescuento.EliminarDescuento(lblNombre.getText());
				dispose();
			}
		});
		btnConfirmar.setBounds(397, 199, 100, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(286, 199, 101, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel_1_2 = new JLabel("¿Seguro que desea eliminar este descuento?");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(79, 149, 342, 21);
		contentPane.add(lblNewLabel_1_2);
	}

	public void mostrar(ControlEliminarDescuento controlEliminarDescuento, String producto, String descuento,
			String fecha) {
		this.controlEliminarDescuento=controlEliminarDescuento;
		lblNombre.setText(producto);
		lblDescuento.setText(descuento+" %");
		lblFecha.setText(fecha);
		setVisible(true);
	}
}
