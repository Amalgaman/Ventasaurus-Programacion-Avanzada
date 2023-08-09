 package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.Concierto;
import Negocio.Verifica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminEditarConcierto extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDireccion;
	private JTextField inputFecha;
	static Verifica verifica = new Verifica();

	/**
	 * Launch the application.
	 */

			public static void run(Concierto concierto) {
				try {
					adminEditarConcierto frame = new adminEditarConcierto(concierto);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the frame.
	 */
	public adminEditarConcierto(Concierto concierto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(22, 11, 270, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(22, 64, 342, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Direccion");
		lblNewLabel_2.setBounds(22, 153, 377, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha");
		lblNewLabel_3.setBounds(22, 196, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		inputNombre = new JTextField();
		inputNombre.setBounds(20, 33, 348, 20);
		inputNombre.setText(concierto.getNombre());
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		JTextArea inputDescripcion = new JTextArea();
		inputDescripcion.setBounds(22, 89, 342, 61);
		inputDescripcion.setText(concierto.getDescripcion());
		contentPane.add(inputDescripcion);
		
		inputDireccion = new JTextField();
		inputDireccion.setBounds(22, 169, 342, 20);
		inputDireccion.setText(concierto.getDireccion());
		contentPane.add(inputDireccion);
		inputDireccion.setColumns(10);
		
		inputFecha = new JTextField();
		inputFecha.setBounds(22, 215, 342, 20);
		inputFecha.setText(concierto.getFecha());
		contentPane.add(inputFecha);
		inputFecha.setColumns(10);
		
		JButton guardarButton = new JButton("Guardar");
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifica.validarModificacionConcierto(inputNombre.getText(), inputDescripcion.getText(), inputDireccion.getText(), inputFecha.getText(),concierto.getId())) {
					JOptionPane.showMessageDialog(null, "El concierto se modifico con exito");
					dispose();
					MenuAdmin.principal();
				}else {
					JOptionPane.showMessageDialog(null, "Error al intentar modificar el concierto no pudo concretarse");
					dispose();
				}
			}
		});
		guardarButton.setBounds(87, 252, 89, 23);
		contentPane.add(guardarButton);
		
		JButton vaolverButton = new JButton("Cancelar");
		vaolverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuAdmin.principal();
			}
		});
		vaolverButton.setBounds(211, 252, 89, 23);
		contentPane.add(vaolverButton);
	}
}
