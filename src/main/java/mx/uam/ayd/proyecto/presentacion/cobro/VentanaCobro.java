package mx.uam.ayd.proyecto.presentacion.cobro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cliente;

import javax.swing.JProgressBar;

@SuppressWarnings("serial")
@Component
public class VentanaCobro extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldTotal;
	private JTextField textFieldRecibi;
	private JTextField textFieldCambio;
	private ControlCobro controlCobro;
	private float total;
	private String responsable;
	private Cliente cliente;

	
	/**
	 * VentanaCobro: 
	 */
	public VentanaCobro() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Cobro");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JCheckBox chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setSelected(true);
		chckbxEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JCheckBox chckbxTarjeta = new JCheckBox("Tarjeta");
		chckbxTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panel = new JPanel();

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setEnabled(false);

		JButton btnRegresar = new JButton("Regresar");

		JButton btnAprobado = new JButton("Aprobado");
		btnAprobado.setVisible(false);
		JProgressBar progressBar = new JProgressBar();
		progressBar.setVisible(false);

		GroupLayout gl_panel = new GroupLayout(panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(16)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxTarjeta, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxEfectivo, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(44).addComponent(btnRegresar)
						.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE).addComponent(btnFinalizar)
						.addGap(43)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(17).addComponent(chckbxEfectivo)
										.addGap(18).addComponent(chckbxTarjeta))
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(panel,
										GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnRegresar)
								.addComponent(btnFinalizar, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
						.addContainerGap()));

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblRecibi = new JLabel("Recibí:");
		lblRecibi.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblCambio = new JLabel("Cambio:");
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setVisible(false);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textFieldTotal = new JTextField();
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);

		textFieldRecibi = new JTextField();
		textFieldRecibi.setColumns(10);

		textFieldCambio = new JTextField();
		textFieldCambio.setEditable(false);
		textFieldCambio.setColumns(10);

		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
								.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 49,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCambio, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRecibi, GroupLayout.PREFERRED_SIZE, 60,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldCambio, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
										.addComponent(textFieldRecibi).addComponent(textFieldTotal)))
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 75,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(progressBar,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup().addGap(92).addComponent(btnAprobado)))
				.addContainerGap(50, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(27)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTotal))
				.addGap(14)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldRecibi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRecibi))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(lblEstado).addComponent(
						progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(17)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblCambio).addComponent(
						textFieldCambio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnAprobado).addContainerGap()));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

		// Listener de la opción efectivo

		textFieldRecibi.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (textFieldRecibi.getText().length() == 0) {
					textFieldCambio.setText(" ");
					btnFinalizar.setEnabled(false);
				} else {
					if (textFieldRecibi.getText().length() != 0) {
						cambio();
						float a = Float.parseFloat(textFieldCambio.getText());
						if (a >= 0)
							btnFinalizar.setEnabled(true);
					}
				}
			}

			/**
			 * Llena campo cambio
			 */

			private void cambio() {
				float recibi = Float.parseFloat(textFieldRecibi.getText());
				float cambio = recibi - total;
				textFieldCambio.setText(String.valueOf(cambio));
			}

		});

		// Listener de la opción tarjeta

		chckbxEfectivo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxEfectivo.isSelected() == true) {
					chckbxTarjeta.setSelected(false);
					lblEstado.setVisible(false);
					progressBar.setVisible(false);
					btnAprobado.setVisible(false);
					lblRecibi.setVisible(true);
					lblCambio.setVisible(true);
					textFieldRecibi.setVisible(true);
					textFieldCambio.setVisible(true);
					textFieldRecibi.setText("");
					textFieldCambio.setText("");
					btnFinalizar.setEnabled(false);

				}

			}
		});

		chckbxTarjeta.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxTarjeta.isSelected() == true) {
					chckbxEfectivo.setSelected(false);
					lblRecibi.setVisible(false);
					lblCambio.setVisible(false);
					textFieldRecibi.setVisible(false);
					textFieldCambio.setVisible(false);
					lblEstado.setVisible(true);
					progressBar.setVisible(true);
					btnAprobado.setVisible(true);
					btnFinalizar.setEnabled(false);
				}
			}
		});

		btnAprobado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread() {
					public void run() {
						for (int i = 1; i <= 100; i++) {
							try {
								sleep(10);
								progressBar.setValue(i);
							} catch (InterruptedException ex) {
								JOptionPane.showMessageDialog(null, "No se acepto la tarjeta");
							}
						}
					}
				};
				hilo.start();
				btnFinalizar.setEnabled(true);
			}
		});

		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldRecibi.setText("");
				textFieldCambio.setText("");
				btnFinalizar.setEnabled(false);
				controlCobro.termina();
			}
		});

		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float a = Float.parseFloat(textFieldCambio.getText());
				if (a < 0) {
					JOptionPane.showMessageDialog(null,
							"La venta no se puede realizar porque la cantidad Recibida es incorrecta");
				} else {
					controlCobro.obtenerLista(total, responsable, cliente);
//					controlCobro.obtenerLista(total,responsable);
					textFieldRecibi.setText("");
					textFieldCambio.setText("");
					controlCobro.limpiarTabla();
					controlCobro.terminaRecarga();
					controlCobro.terminaTodo();
				}

			}
		});

	}
	
	// Métodos que ocupa la ventana
	//Recibe al cliente y el nombre del empleado para asociarl3es las ventas ademas de mostrar la ventana de cobro 
	// @param total,responsable,cliente controlCobro
	
	public void muestra(ControlCobro controlCobro, float total, String responsable, Cliente cliente) {
		textFieldTotal.setText(String.valueOf(total));
		this.controlCobro = controlCobro;
		this.total = total;
		this.cliente = cliente;
		this.responsable = responsable;
		setVisible(true);
	}

	// Métodos que ocupa la ventana
	public void muestra(ControlCobro controlCobro, float total, String responsable) {
		textFieldTotal.setText(String.valueOf(total));
		this.controlCobro = controlCobro;
		this.total = total;
		this.responsable = responsable;
		setVisible(true);
	}

	public void muestraDialogo() {
		JOptionPane.showMessageDialog(null, "La venta se realizó con exito");

	}
}
