package Interfaz;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Verifica;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class adminAltaConcierto extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDireccion;
	private JTextField inputFecha;
	static Verifica verifica = new Verifica();

	/**
	 * Launch the application.
	 */

			public static void run() {
				try {
					adminAltaConcierto frame = new adminAltaConcierto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the frame.
	 */
	public adminAltaConcierto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(22, 22, 270, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(22, 64, 342, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Direccion");
		lblNewLabel_2.setBounds(22, 153, 377, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha");
		lblNewLabel_3.setBounds(22, 193, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JTextArea inputDescripcion = new JTextArea();
		inputDescripcion.setBounds(22, 89, 342, 61);
		contentPane.add(inputDescripcion);
		
		JButton guardarButton = new JButton("Guardar");
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = verifica.validarConcierto(inputNombre.getText(), inputDescripcion.getText(), inputDireccion.getText(), inputFecha.getText());
				
				if(id > 0) {
					
					JOptionPane.showMessageDialog(null, "El concierto se genero con exito");
					String nombreLoc;
					int cupoLoc;
					double precioLoc;
					
					//Generamos las localidades, minimo 1
					do {
						adminAltaLocalidades.run(id);

					}while(JOptionPane.showConfirmDialog(null, "¿Ingresar otra localidad?") == 0);
				
				}else {
					JOptionPane.showMessageDialog(null, "El alta del concierto no pudo concretarse");
					dispose();
					MenuAdmin.principal();
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
		
		inputNombre = new JTextField();
		inputNombre.setBounds(22, 36, 342, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		inputDireccion = new JTextField();
		inputDireccion.setBounds(22, 165, 342, 20);
		contentPane.add(inputDireccion);
		inputDireccion.setColumns(10);
		
		inputFecha = new JTextField();
		inputFecha.setBounds(22, 206, 342, 20);
		contentPane.add(inputFecha);
		inputFecha.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Crear Nuevo Concierto");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(0, 0, 384, 25);
		contentPane.add(lblNewLabel_4);
	}

}
