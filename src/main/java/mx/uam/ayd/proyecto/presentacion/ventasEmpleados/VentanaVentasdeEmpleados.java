package mx.uam.ayd.proyecto.presentacion.ventasEmpleados;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import mx.uam.ayd.proyecto.negocio.modelo.Venta;
import mx.uam.ayd.proyecto.presentacion.altaBajaUsuarios.ControlAltaYBajaUsuarios;
import mx.uam.ayd.proyecto.presentacion.cierreVenta.RowsRenderer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ventana para ver ventas de cliente
 * 
 * @author David Castellanos
 * @since  16/02/2021
 */
@SuppressWarnings("serial")
@Component
public class VentanaVentasdeEmpleados extends JFrame {
	
	private JPanel contentPane;
	private JPanel panel;
	private ControlVentasdeEmpleados controlVentasdeEmpleados;
	private Empleado empleado;
	private JScrollPane scrollPaneVentasEmpleado;
	private JTextField textTotal;
	private JTable tablaVentas;
	private float ventaTotal=0;
	DefaultTableModel modeloVentasEmpleado = new DefaultTableModel();
	
	public VentanaVentasdeEmpleados() {
		
		setTitle("Ventas de Empleado");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 623, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 114, 587, 171);
		contentPane.add(panel);
		scrollPaneVentasEmpleado = new JScrollPane();		
		panel.add(scrollPaneVentasEmpleado);
		
		JLabel lblNewLabel = new JLabel("Ventas de empleado");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));
		lblNewLabel.setBounds(193, 11, 227, 24);
		contentPane.add(lblNewLabel);
		
		/**
		 * muestra  todas las ventas del mes del empleado
		 */
		JButton btnMes = new JButton("Mes");
		btnMes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				ventaTotal=0;
				textTotal.setText("");
				Calendar fecha = new GregorianCalendar();
				int ano = fecha.get(Calendar.YEAR);
				int mes = fecha.get(Calendar.MONTH)+1;
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				for (int i = 1; i < 32; i++) {
					dia = i;
				String fechaF = dia + "/" + mes + "/" + ano;
				controlVentasdeEmpleados.obtenerVentas(fechaF,empleado.getUsuario());
				}
				textTotal.setText( String.valueOf(ventaTotal));
				
			}
		});
		btnMes.setBounds(256, 65, 89, 23);
		contentPane.add(btnMes);
		
		JButton btnAño = new JButton("Año");
		btnAño.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnAño.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarTabla();
				ventaTotal=0;
				textTotal.setText("");
				Calendar fecha = new GregorianCalendar();
				int ano = fecha.get(Calendar.YEAR);
				int mes = fecha.get(Calendar.MONTH)+1;
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				for (int m = 1; m < 13; m++) {
					for (int d = 1; d < 32; d++) {
						dia = d;
						mes = m;
						String fechaF = dia + "/" + mes + "/" + ano;
						controlVentasdeEmpleados.obtenerVentas(fechaF,empleado.getUsuario());
					}
				}
				textTotal.setText( String.valueOf(ventaTotal));
			}
		});
		btnAño.setBounds(378, 65, 89, 23);
		contentPane.add(btnAño);
		
		/**
		 * muestra  todas las ventas del dia de empleado
		 */
		JButton btnDia = new JButton("Día");
		btnDia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarTabla();
				ventaTotal=0;
				textTotal.setText("");
				Calendar fecha = new GregorianCalendar();
				int ano = fecha.get(Calendar.YEAR);
				int mes = fecha.get(Calendar.MONTH)+1;
				int dia = fecha.get(Calendar.DAY_OF_MONTH);
				String fechaF = dia + "/" + mes + "/" + ano;
				controlVentasdeEmpleados.obtenerVentas(fechaF,empleado.getUsuario());
				textTotal.setText( String.valueOf(ventaTotal));
			}
		});
		btnDia.setBounds(136, 65, 89, 23);
		contentPane.add(btnDia);
		
		JLabel lblVerVentasPor = new JLabel("Ver ventas por:");
		lblVerVentasPor.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblVerVentasPor.setBounds(10, 62, 166, 24);
		contentPane.add(lblVerVentasPor);
		
		/**
		 * muestra  todas las ventas del empleado
		 */
		JButton btnTodo = new JButton("Todo");
		btnTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				ventaTotal=0;
				textTotal.setText("");
				controlVentasdeEmpleados.obtenerVentasEmpleado(empleado.getUsuario());
				textTotal.setText( String.valueOf(ventaTotal));
			}
		});
		btnTodo.setBounds(499, 65, 89, 23);
		contentPane.add(btnTodo);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblTotal.setBounds(439, 296, 52, 24);
		contentPane.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		textTotal.setBounds(492, 300, 105, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);
		
		JButton btnRegresar = new JButton("Cancelar");
		btnRegresar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVentasdeEmpleados.termina();
			}
		});
		btnRegresar.setBounds(391, 351, 89, 23);
		contentPane.add(btnRegresar);
		
		JButton btnTerminar = new JButton("Confirmar");
		btnTerminar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVentasdeEmpleados.terminaVentas();
				}
		});
		btnTerminar.setBounds(508, 351, 89, 23);
		contentPane.add(btnTerminar);
		
		tablaVentas = new JTable(modeloVentasEmpleado) { 
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		modeloVentasEmpleado.addColumn("Fecha");
		modeloVentasEmpleado.addColumn("Cantidad");
		modeloVentasEmpleado.addColumn("Precio");
		tablaVentas.setPreferredScrollableViewportSize(panel.getSize());
				
	}

	public void muestraDialogoConMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	//Muestra la ventana
	public void muestra(ControlVentasdeEmpleados controlVentasdeEmpleados,Empleado empleado) {
		 this.controlVentasdeEmpleados= controlVentasdeEmpleados;
		 this.empleado= empleado;
		 setVisible(true);
		 limpiarTabla();
		 ventaTotal=0;
		 controlVentasdeEmpleados.obtenerVentasEmpleado(empleado.getUsuario());
		 textTotal.setText( String.valueOf(ventaTotal));
		 scrollPaneVentasEmpleado.setViewportView(tablaVentas);
		 
	}
	
	//Metodo para limpiar tabla
	public void limpiarTabla() { //3 
		if (tablaVentas.getRowCount() > 0) { // solo esto
			int filas = tablaVentas.getRowCount();
			try {
				for (int i = 0; filas > i; i++) {
					modeloVentasEmpleado.removeRow(0);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al limpiar la tabla de usuarios.");
			}
		}		
	}
	
	//Agrega ventas de empleado y los manda a la tabla
	public void agregaVentasdeEmpleados(Venta e){
		
		String a[] = new String[6];
		a[0] = e.getFecha();
		if(e.getCantidad()==0) {
			a[1] = "Recarga";
		}else {
			a[1] =  String.valueOf(e.getCantidad());
		}
		
		a[2] = String.valueOf(e.getTotal());
		ventaTotal=  ventaTotal+ e.getTotal();
		modeloVentasEmpleado.addRow(a);
		tablaVentas.setModel(modeloVentasEmpleado);
		RowsRenderer rr = new RowsRenderer(2);
		tablaVentas.setDefaultRenderer(Object.class, rr);
		
	}
	
	//Metodo para mostrar si no llega a ver ventas 
	public void sinProductos(String mensaje) {
		
	}
}
