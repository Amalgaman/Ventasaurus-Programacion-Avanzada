package Jframes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.Concierto;
import Interfaz.MenuPrincipal;
import Negocio.Verifica;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuControl extends JFrame {

	static Verifica verifica = new Verifica();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
			public static void run() {
				try {
					MenuControl frame = new MenuControl();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

	}

	/**
	 * Create the frame.
	 */
	public MenuControl() {
		setTitle("Menu Control");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 209);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();
		String[] conciertoLista = new String[listaTraida.size()];
		
		for (Concierto concierto : listaTraida) {
			conciertoLista[listaTraida.indexOf(concierto)] = concierto.getNombre();
		}
		
		//String[] opciones= {"ada","ada","ada","ada","ada","ada","ada","ada","ada","ada","ada","ada","ada"};
		if (listaTraida.isEmpty()) {
			JLabel lblNewLabel = new JLabel("Error en la base de datos");
			lblNewLabel.setBounds(10, 68, 290, 21);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		} else {
		}
		JComboBox comboBox = new JComboBox(conciertoLista);
		comboBox.setBounds(10, 68, 290, 21);
		
		panel_1.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Selección de concierto");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 290, 54);
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Concierto con : listaTraida) {
					if(comboBox.getSelectedItem().equals(con.getNombre())) {
						dispose();
						ValidarEntrada.run(con);
					}
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MenuPrincipal.principal();
			}
		});
		panel.add(btnNewButton_1);
		
		
	}
}
