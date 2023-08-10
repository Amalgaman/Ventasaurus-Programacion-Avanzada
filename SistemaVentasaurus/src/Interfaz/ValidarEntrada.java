package Interfaz;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Datos.Comprobante;
import Datos.Concierto;
import Datos.Conexion;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ValidarEntrada extends JFrame {

	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
			public static void run(Concierto concierto) {
				try {
					ValidarEntrada frame = new ValidarEntrada(concierto);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

	}

	/**
	 * Create the frame.
	 */
	public ValidarEntrada(Concierto concierto) {
		setTitle("Validación de entrada");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
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
		
		JLabel lblNewLabel = new JLabel("Ingresar dni");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 406, 50);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		textField.setBounds(164, 70, 96, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 118, 406, 104);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Validar");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LinkedList<Comprobante> entradas = new LinkedList<Comprobante>();
				
				String sql="SELECT entrada.id, cliente.dni FROM `entrada` INNER JOIN cliente on cliente.id=entrada.id_cliente WHERE entrada.id NOT in (SELECT DISTINCT id_entrada FROM detalle_devolucion WHERE id_devolucion in (SELECT devolucion.id FROM devolucion WHERE devolucion.estado='Aprobada')) AND entrada.id_localidad in (SELECT localidad.id FROM localidad WHERE id_concierto=?)AND entrada.c_devolucion!=-1;";
				try {
				String datos[]= new String[2];
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, concierto.getId());
				ResultSet result = stmt.executeQuery();
				while(result.next()) {
					datos[0]= result.getString(1); //id
					datos[1]= result.getString(2); //dni
					entradas.add(new Comprobante(Integer.parseInt(datos[0]),datos[1]));
				}
				//conexion.close();
				if (textField.getText().equals("")) {
					lblNewLabel_1.setText("Se debe introducir un dni");
				} else {
					int flag=0;
					for (Comprobante comprobante : entradas) {
						System.out.println("Cdni: "+comprobante.getDnicliente());
						if (comprobante.getDnicliente().equals(textField.getText())) {
							flag++;
						}
					}
					if (flag>0) { 
						lblNewLabel_1.setText("Entrada validada para "+flag+" personas");
						sql="UPDATE `entrada` SET `c_devolucion` = '-1' WHERE id not in (SELECT id_entrada from detalle_devolucion WHERE id_devolucion in (SELECT devolucion.id FROM devolucion WHERE devolucion.estado='Aprobada')) AND id_cliente IN (SELECT id FROM cliente WHERE dni=?)AND id_localidad in (SELECT localidad.id FROM localidad WHERE id_concierto=?);";
						
						try {
							stmt = (PreparedStatement) conexion.prepareStatement(sql);
							stmt.setString(1, textField.getText());
							stmt.setInt(2, concierto.getId());
							stmt.executeUpdate();
						}catch(Exception excepcion){
							System.out.println("error "+excepcion.getMessage());
						}
					}else{
						lblNewLabel_1.setText("No hay entradas disponibles para ese dni");
					}
				}
				
				} catch(Exception excepcion){
					System.out.println(excepcion.getMessage());
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose(); 
				MenuControl.run("");
			}
		});
		panel.add(btnNewButton_1);
	}
}

