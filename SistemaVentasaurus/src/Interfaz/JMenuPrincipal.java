package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class JMenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuPrincipal frame = new JMenuPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 750, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton verConciertos = new JButton("Ver conciertos disponibles");
		verConciertos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuCliente nuevo = new JMenuCliente();
				nuevo.setLocationRelativeTo(null);
				nuevo.setVisible(true);
				dispose();
			}
		});
		
		verConciertos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		verConciertos.setBounds(458, 82, 226, 43);
		contentPane.add(verConciertos);
		
		JButton Devoluciones = new JButton("Solicitar devolución");
		Devoluciones.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		Devoluciones.setBounds(482, 210, 177, 43);
		contentPane.add(Devoluciones);
		
		JButton login = new JButton("Ingresar al sistema");
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		login.setBounds(482, 323, 177, 43);
		contentPane.add(login);
		
		JButton salirBoton = new JButton("Salir");
		salirBoton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		salirBoton.setBounds(525, 448, 89, 23);
		contentPane.add(salirBoton);
		salirBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel imgDino = new JLabel();
		 ImageIcon icon = new ImageIcon("src/img/dino.jpg");
		 imgDino.setIcon(icon);
		imgDino.setBounds(40, 54, 351, 471);
		contentPane.add(imgDino);
		
		JTextArea bienvenida = new JTextArea();
		bienvenida.setEditable(false);
		bienvenida.setBackground(Color.LIGHT_GRAY);
		bienvenida.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bienvenida.setText("Bienvenido a Ventasaurus");
		bienvenida.setBounds(235, 11, 250, 43);
		contentPane.add(bienvenida);
	}
}
