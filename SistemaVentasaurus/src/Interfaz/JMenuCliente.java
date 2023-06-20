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
import javax.swing.JComboBox;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel imgConcierto = new JLabel();
		 ImageIcon icon = new ImageIcon("src/img/dinosrock.png");
		 imgConcierto.setIcon(icon);
		imgConcierto.setBounds(33, 34, 454, 391);
		contentPane.add(imgConcierto);
		
		JButton comprarPagar = new JButton("Elegir");
		comprarPagar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		comprarPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comprarPagar.setBounds(539, 387, 100, 23);
		contentPane.add(comprarPagar);
		
		JButton volverMenuPrincipal = new JButton("Volver atras");
		volverMenuPrincipal.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		volverMenuPrincipal.setBounds(528, 439, 123, 23);
		contentPane.add(volverMenuPrincipal);
		
		JComboBox listaConciertos = new JComboBox();
		listaConciertos.setBounds(497, 34, 166, 29);
		contentPane.add(listaConciertos);
	}
}
