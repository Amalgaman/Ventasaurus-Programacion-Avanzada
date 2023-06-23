package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;

public class JMenuCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuCliente frame = new JMenuCliente();
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
	public JMenuCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imgConcierto = new JLabel();
		 ImageIcon icon = new ImageIcon("src/img/dinosrock.png");
		 imgConcierto.setIcon(icon);
		imgConcierto.setBounds(33, 60, 454, 391);
		contentPane.add(imgConcierto);
		
		JTextArea dni = new JTextArea();
		dni.setBounds(497, 42, 123, 16);
		contentPane.add(dni);
		JButton comprarPagar = new JButton("Continuar");
		comprarPagar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		comprarPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < dni.getText().length(); i++) {
					
					if(!Character.isDigit(dni.getText().charAt(i))   ) {
						JOptionPane.showMessageDialog(null, "El caracter ´"  + /* i */dni.getText().charAt(i) + "´ no es valido");
					}else {
						JPagar nuevo  =new JPagar();
						nuevo.setVisible(true);
						dispose();
						
					}
				}
				
				
			}
		});
		comprarPagar.setBounds(417, 428, 123, 23);
		contentPane.add(comprarPagar);
		
		JButton volverMenuPrincipal = new JButton("Volver atras");
		volverMenuPrincipal.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		volverMenuPrincipal.setBounds(551, 428, 123, 23);
		contentPane.add(volverMenuPrincipal);
		volverMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuPrincipal nuevo = new JMenuPrincipal();
				nuevo.setVisible(true);
				dispose();
			}
		});
		
		JComboBox listaConciertos = new JComboBox();
		listaConciertos.setBounds(453, 111, 221, 23);
		contentPane.add(listaConciertos);
		
		JTextArea txtrEli = new JTextArea();
		txtrEli.setText("Elegir concierto e ingresar dni para sacar un ticket");
		txtrEli.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrEli.setEditable(false);
		txtrEli.setBackground(Color.LIGHT_GRAY);
		txtrEli.setBounds(30, 6, 413, 43);
		contentPane.add(txtrEli);
		
		JTextArea txtrIngreseSuDni = new JTextArea();
		txtrIngreseSuDni.setText("Ingrese su dni");
		txtrIngreseSuDni.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrIngreseSuDni.setEditable(false);
		txtrIngreseSuDni.setBackground(Color.LIGHT_GRAY);
		txtrIngreseSuDni.setBounds(509, 15, 102, 23);
		contentPane.add(txtrIngreseSuDni);
		
		JTextArea txtrConciertosDisponibles = new JTextArea();
		txtrConciertosDisponibles.setText("Conciertos disponibles");
		txtrConciertosDisponibles.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrConciertosDisponibles.setEditable(false);
		txtrConciertosDisponibles.setBackground(Color.LIGHT_GRAY);
		txtrConciertosDisponibles.setBounds(484, 79, 157, 25);
		contentPane.add(txtrConciertosDisponibles);
		
	}
}
