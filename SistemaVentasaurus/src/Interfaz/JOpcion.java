package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class JOpcion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JOpcion frame = new JOpcion();
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
	public JOpcion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 250, 150);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton menu = new JButton("Menu principal");
		menu.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		contentPane.add(menu);
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlMenuPrincipal();
			}
		});
		
		JButton concierto = new JButton("Ver conciertos");
		concierto.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		contentPane.add(concierto);
		concierto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAConciertos();
			}
		});
		
		
	}
	public void volverAlMenuPrincipal() {
		JMenuPrincipal nuevo = new JMenuPrincipal();
		nuevo.setLocationRelativeTo(null);
		nuevo.setVisible(true);
		dispose();
	}
	public void volverAConciertos() {
		JMenuCliente nuevo = new JMenuCliente();
		nuevo.setLocationRelativeTo(null);
		nuevo.setVisible(true);
		dispose();
	}

}
