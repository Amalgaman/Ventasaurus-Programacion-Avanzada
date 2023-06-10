package Interfaz;

import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.Entrada;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class Ventana extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(LinkedList<Entrada> entradas) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana(entradas);
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
	public Ventana(LinkedList<Entrada> entradas) {
		//System.out.println(entradas);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for (Entrada entrada : entradas) {
			JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
			chckbxNewCheckBox.setBounds(151, 158, 93, 21);
			contentPane.add(chckbxNewCheckBox);
		}
		
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(151, 195, 93, 21);
		contentPane.add(chckbxNewCheckBox_1);
	}
}
