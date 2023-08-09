 package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Datos.Concierto;
import Datos.Localidad;
import Negocio.Verifica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminConcierto extends JFrame {

	private JPanel contentPane;
	static Verifica verifica = new Verifica();

	/**
	 * Launch the application.
	 */

			public static void run(Concierto concierto) {
				try {
					adminConcierto frame = new adminConcierto(concierto);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the frame.
	 */
	public adminConcierto(Concierto concierto) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 313);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel(concierto.getNombre());
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		labelNombre.setBounds(0, 0, 525, 32);
		contentPane.add(labelNombre);
		
		JLabel labelDireccion1 = new JLabel(concierto.getDireccion());
		labelDireccion1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		labelDireccion1.setBounds(64, 43, 122, 14);
		contentPane.add(labelDireccion1);
		
		JLabel labelFecha1 = new JLabel(concierto.getFecha());
		labelFecha1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		labelFecha1.setBounds(276, 43, 135, 14);
		contentPane.add(labelFecha1);
		
		JLabel lblNewLabel = new JLabel("Direccion:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 43, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha:");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(233, 43, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textPane.setBounds(10, 68, 203, 192);
		textPane.setText(concierto.getDescripcion());
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textPane_1.setBounds(223, 68, 269, 192);
		textPane_1.setText(formatearLocalidad(concierto.getLocalidades()));
		contentPane.add(textPane_1);
		
		JButton editarButton = new JButton("Editar Informacion");
		editarButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		editarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				adminEditarConcierto.run(concierto);
			}
		});
		editarButton.setBounds(25, 271, 150, 30);
		contentPane.add(editarButton);
		
		JButton cancelarButton = new JButton("Cancelar Concierto");
		cancelarButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res0 = JOptionPane.showConfirmDialog(null, "¿Esta seguro que quiere cancelar el concierto?");
				if(res0 == 0) {
					if(verifica.cancelarConcierto(concierto.getId())){
					JOptionPane.showMessageDialog(null, "Concierto cancelado con exito");
					}else {
						JOptionPane.showMessageDialog(null, "Hubo un error al intentar cancelar el concierto, intentelo mas tarde");
					}
				}
				dispose();
				MenuAdmin.principal();
			}
		});
		cancelarButton.setBounds(185, 271, 171, 32);
		contentPane.add(cancelarButton);
		
		JButton volverButton = new JButton("Volver");
		volverButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuAdmin.principal();
			}
		});
		volverButton.setBounds(367, 270, 108, 32);
		contentPane.add(volverButton);
		
		
	}
	
	public static String formatearLocalidad(LinkedList<Localidad> localidades) {
		String mensaje="Localidades: ";
		for (Localidad localidad : localidades) {
			mensaje = mensaje+" \n"+localidad;
		}
		
		return mensaje;
	}
}
