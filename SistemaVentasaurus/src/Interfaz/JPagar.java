package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class JPagar extends JFrame {

	private JPanel contentPane;
	private JTextField cantEntr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPagar frame = new JPagar();
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
	public JPagar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(30, 67, 191, 30);
		contentPane.add(comboBox);
		
		cantEntr = new JTextField();
		cantEntr.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		cantEntr.setBounds(273, 74, 171, 23);
		contentPane.add(cantEntr);
		cantEntr.setColumns(10);
		
		
		JTextArea txtrElegirPlatea = new JTextArea();
		txtrElegirPlatea.setText("Elegir platea");
		txtrElegirPlatea.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrElegirPlatea.setEditable(false);
		txtrElegirPlatea.setBackground(Color.LIGHT_GRAY);
		txtrElegirPlatea.setBounds(69, 20, 104, 36);
		contentPane.add(txtrElegirPlatea);
		
		JTextArea txtrIngresarCantidadDe = new JTextArea();
		txtrIngresarCantidadDe.setLineWrap(true);
		txtrIngresarCantidadDe.setText("Ingresar cantidad de tickets a comprar");
		txtrIngresarCantidadDe.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrIngresarCantidadDe.setEditable(false);
		txtrIngresarCantidadDe.setBackground(Color.LIGHT_GRAY);
		txtrIngresarCantidadDe.setBounds(273, 11, 171, 56);
		contentPane.add(txtrIngresarCantidadDe);
		
		JButton continuar = new JButton("Continuar");
		continuar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		continuar.setBounds(284, 292, 104, 23);
		contentPane.add(continuar);
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = cantEntr.getText();
				int numero = Integer.parseInt(texto);
				for (int i = 0; i < cantEntr.getText().length(); i++) {
					
					if(!Character.isDigit(cantEntr.getText().charAt(i))) {
						JOptionPane.showMessageDialog(null, "El caracter ´"  + i + "´ no es valido");
					}else if(numero>=1 && numero<=10){
						JOptionPane.showMessageDialog(null, "Compraste "+cantEntr.getText()+" entradas");
						JMenuPrincipal nuevo  = new JMenuPrincipal();
						nuevo.setVisible(true);
						dispose();		
					}else {
						JOptionPane.showMessageDialog(null, "Numero no valido");
					}
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnCancelar.setBounds(399, 292, 90, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuPrincipal nuevo = new JMenuPrincipal();
				nuevo.setVisible(true);
				dispose();
			}
		});
		
		
		JLabel imgTickets = new JLabel();
		ImageIcon icon = new ImageIcon("src/img/ticket.jpg");
		imgTickets.setIcon(icon);
		 imgTickets.setBounds(252, 108, 237, 179);
		contentPane.add(imgTickets);
	}
}
