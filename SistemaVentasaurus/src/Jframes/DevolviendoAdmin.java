package Jframes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.Conexion;
import Datos.Entrada;
import Interfaz.MenuAdmin;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

public class DevolviendoAdmin extends JFrame {
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

			public static void run(LinkedList<Entrada> ids,int dni) {
				try {
					DevolviendoAdmin frame = new DevolviendoAdmin(ids,dni);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	/**
	 * Create the frame.
	 */
	public DevolviendoAdmin(LinkedList<Entrada>  ids,int dni) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 214);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>" +"Se ha enviado una solicitud de devolución \n que será revisada por el administrador" + "</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 396, 194);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 224, 416, 29);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		String sql="SELECT id FROM cliente where dni=?";
		int clienteid=-5;
		try {
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, dni);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				clienteid=Integer.parseInt(result.getString(1)); //id
			}
		}catch(Exception excepcion){
			System.out.println("error 1 "+excepcion.getMessage());
		}
		
		sql ="INSERT INTO `devolucion` (`estado`, `creacion`, `id_cliente`) VALUES ('pendiente', ?, ?);";
		
		try {
			LocalDate fecha = LocalDate.now();
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(2, clienteid);
			stmt.setObject(1,Date.valueOf(fecha));
			stmt.executeUpdate();
			
		}catch(Exception excepcion){
			System.out.println("error 2 "+excepcion.getMessage());
			
		}
		sql ="SELECT MAX(id) FROM devolucion;";
		int devolucionid=-5;
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				devolucionid=Integer.parseInt(result.getString(1)); //id
			}
		}catch(Exception excepcion){
			System.out.println("error 3 "+excepcion.getMessage());
		}
		
		for (Entrada entrada : ids) {
			sql ="INSERT INTO `detalle_devolucion` (`id_devolucion`, `id_entrada`) VALUES (?, ?);";
			try {
				
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, devolucionid);
				stmt.setInt(2, entrada.getId());
				stmt.executeUpdate();
			}catch(Exception excepcion){
				System.out.println("error 4 "+excepcion.getMessage());
				
			}
		}
		
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MenuAdmin.principal();
			}
		});
		panel_1.add(btnNewButton);
		
		if (ids.isEmpty()) {
			lblNewLabel.setText("No has seleccionado entradas");
			JButton btnNewButton_1 = new JButton("Volver");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GenerarSolicitudAdmin.run(dni,false);
					dispose();
				}
			});
			panel_1.add(btnNewButton_1);
		}
	}
}
