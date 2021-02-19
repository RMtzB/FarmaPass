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
		setBounds(100, 100, 623, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 114, 587, 219);
		contentPane.add(panel);
		scrollPaneVentasEmpleado = new JScrollPane();		
		panel.add(scrollPaneVentasEmpleado);
		
		JLabel lblNewLabel = new JLabel("Ventas de empleado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(222, 11, 141, 24);
		contentPane.add(lblNewLabel);
		
		/**
		 * muestra  todas las ventas del mes del empleado
		 */
		JButton btnMes = new JButton("Mes");
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
		JButton btnDia = new JButton("Dia");
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
		lblVerVentasPor.setFont(new Font("Tahoma", Font.PLAIN, 15));
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
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal.setBounds(407, 344, 60, 24);
		contentPane.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setBounds(483, 348, 105, 20);
		contentPane.add(textTotal);
		textTotal.setColumns(10);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVentasdeEmpleados.termina();
			}
		});
		btnRegresar.setBounds(335, 424, 89, 23);
		contentPane.add(btnRegresar);
		
		JButton btnTerminar = new JButton("Terminar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlVentasdeEmpleados.terminaVentas();
				}
		});
		btnTerminar.setBounds(466, 424, 89, 23);
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
		 controlVentasdeEmpleados.obtenerVentasEmpleado(empleado.getUsuario());
		 textTotal.setText( String.valueOf(ventaTotal));
		 scrollPaneVentasEmpleado.setViewportView(tablaVentas);
		 
	}
	
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
	
	public void agregaVentasdeEmpleados(Venta e) {
		
		String a[] = new String[6];
		a[0] = e.getFecha();
		a[1] =  String.valueOf(e.getCantidad());
		a[2] = String.valueOf(e.getTotal());
		ventaTotal=  ventaTotal+ e.getTotal();
		modeloVentasEmpleado.addRow(a);
		tablaVentas.setModel(modeloVentasEmpleado);
		RowsRenderer rr = new RowsRenderer(2);
		tablaVentas.setDefaultRenderer(Object.class, rr);
		
	}
	
	public void sinProductos(String mensaje) {
		
	}
}
