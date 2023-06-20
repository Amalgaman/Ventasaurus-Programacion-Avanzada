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
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton verConciertos = new JButton("Ver conciertos disponibles");
		verConciertos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		verConciertos.setBounds(10, 494, 226, 43);
		contentPane.add(verConciertos);
		
		JButton Devoluciones = new JButton("Solicitar devolución");
		Devoluciones.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		Devoluciones.setBounds(244, 494, 177, 43);
		contentPane.add(Devoluciones);
		
		JButton login = new JButton("Ingresar al sistema");
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		login.setBounds(431, 494, 195, 43);
		contentPane.add(login);
		
		JButton salirBoton = new JButton("Salir");
		salirBoton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		salirBoton.setBounds(636, 504, 89, 23);
		contentPane.add(salirBoton);
		
		JLabel imgDino = new JLabel();
		 ImageIcon icon = new ImageIcon("src/img/dino.jpg");
		 imgDino.setIcon(icon);
		imgDino.setBounds(200, 54, 350, 412);
		contentPane.add(imgDino);
		
		JTextArea bienvenida = new JTextArea();
		bienvenida.setEditable(false);
		bienvenida.setBackground(UIManager.getColor("CheckBox.background"));
		bienvenida.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bienvenida.setText("Bienvenido a Ventasaurus");
		bienvenida.setBounds(235, 0, 250, 43);
		contentPane.add(bienvenida);
	}
}
