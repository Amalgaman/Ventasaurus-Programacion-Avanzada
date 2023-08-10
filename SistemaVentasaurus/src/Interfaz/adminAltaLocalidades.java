 package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Verifica;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminAltaLocalidades extends JFrame {

	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputPrecio;
	private JTextField inputCupos;
	static Verifica verifica = new Verifica();

	/**
	 * Launch the application.
	 */

			public static void run(int id) {
				try {
					adminAltaLocalidades frame = new adminAltaLocalidades(id);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

	}

	/**
	 * Create the frame.
	 */
	public adminAltaLocalidades(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton guardarBoton = new JButton("Guardar");
		guardarBoton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		guardarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(verifica.validarLocalidad(inputNombre.getText(),Integer.parseInt(inputCupos.getText()), Double.parseDouble(inputPrecio.getText()), id)) {
					JOptionPane.showMessageDialog(null, "La localidad se guardo con exito");
				}else {
					JOptionPane.showMessageDialog(null, "Hubo un error al guardar la localidad");
				}
				if(JOptionPane.showConfirmDialog(null, "¿Ingresar otra localidad?") == 1) {
					dispose();
					MenuAdmin.principal();
				}
			}
		});
		guardarBoton.setBounds(70, 214, 89, 23);
		contentPane.add(guardarBoton);
		
		JButton cancelarBoton = new JButton("Cancelar");
		cancelarBoton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		cancelarBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuAdmin.principal();
			}
		});
		cancelarBoton.setBounds(255, 214, 89, 23);
		contentPane.add(cancelarBoton);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel.setBounds(35, 42, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ingresar Nueva Localidad");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 5, 401, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(35, 97, 53, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cupos Totales");
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(35, 147, 99, 14);
		contentPane.add(lblNewLabel_3);
		
		inputNombre = new JTextField();
		inputNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		inputNombre.setBounds(35, 67, 347, 20);
		contentPane.add(inputNombre);
		inputNombre.setColumns(10);
		
		inputPrecio = new JTextField();
		inputPrecio.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		inputPrecio.setBounds(35, 121, 347, 20);
		contentPane.add(inputPrecio);
		inputPrecio.setColumns(10);
		
		inputCupos = new JTextField();
		inputCupos.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		inputCupos.setBounds(35, 172, 344, 20);
		contentPane.add(inputCupos);
		inputCupos.setColumns(10);
	}

}