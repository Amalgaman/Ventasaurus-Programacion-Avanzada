package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Verifica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField inputDni;
	private JTextField inputPass;
	static Verifica verifica = new Verifica();
	private JButton volver;

	/**
	 * Launch the application.
	 */
			public static void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the frame.
	 */
	public Login() {
		setFont(new Font("Tahoma", Font.PLAIN, 18));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese DNI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(95, 11, 99, 23);
		contentPane.add(lblNewLabel);
		
		inputDni = new JTextField();
		inputDni.setBounds(59, 45, 170, 20);
		contentPane.add(inputDni);
		inputDni.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(verifica.verificaIngresarEmpleado(inputDni.getText(), inputPass.getText())) {
				case "null": //Esto es por si el usuario toca "Cancelar" o "Cerrar"
					break;
				case "admin":
					dispose();
					MenuAdmin.principal();
					break;
				case "control":
					MenuControl.run("");
					dispose();
					break;
				case "incorrecto":
					JOptionPane.showMessageDialog(null, "DNI o Contraseña incorrectos");
					break;
				case "error":
					JOptionPane.showMessageDialog(null, "Error al conectar con servidor, intentelo mas tarde");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado, intentelo mas tarde");
					break;	
				}
				
			}
		});
		btnNewButton.setBounds(39, 129, 99, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrese Contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(69, 72, 154, 23);
		contentPane.add(lblNewLabel_1);
		
		inputPass = new JTextField();
		inputPass.setBounds(59, 98, 170, 20);
		contentPane.add(inputPass);
		inputPass.setColumns(10);
		
		volver = new JButton("Volver");
		volver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuPrincipal nuevo = new JMenuPrincipal();
				nuevo.setLocationRelativeTo(null);
				nuevo.setVisible(true);
				dispose();
			}
		});
		volver.setBounds(159, 129, 99, 35);
		contentPane.add(volver);
	}
	
}
