package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Datos.Concierto;
import Datos.Conexion;
import Datos.Localidad;
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
import java.sql.ResultSet;

import javax.swing.JTextField;
import java.awt.Color;

public class MenuControl extends JFrame {
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;

	static Verifica verifica = new Verifica();
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
			public static void run(String buscar) {
				try {
					MenuControl frame = new MenuControl(buscar);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

	}

	/**
	 * Create the frame.
	 */
	public MenuControl(String buscar) {
		setTitle("Menu Control");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 209);
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
		
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();
		
		LinkedList<Concierto> conciertos = new LinkedList<Concierto>();
		String sql ="SELECT * FROM `concierto` WHERE nombre LIKE '%"+buscar+"%'";
		//  Datos: int id, String nombre, String descripcion, String direccion, boolean cancelado, String fecha, int entDisponibles, LinkedList<Localidad> localidades
		
		String[] datos = new String[6]; 
		Localidad localidad = new Localidad(0, "", 0, 0,0);
		
		try {
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			//stmt.setString(1, buscar);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[0]= result.getString(1);
				datos[1]= result.getString(2);
				datos[2]= result.getString(3);
				datos[3]= result.getString(4);
				datos[4]= result.getString(5);
				datos[5]= result.getString(6);
				conciertos.add(new Concierto(Integer.parseInt(datos[0])
						,datos[1]
						,datos[2]
						,datos[3]
						,Boolean.parseBoolean(datos[4])
						,datos[5]
						,localidad.traerLocalidadesXConcierto(Integer.parseInt(datos[0]))));
			}
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
		}
		
		String[] conciertoLista = new String[conciertos.size()];
		
		for (Concierto concierto : conciertos) {
			conciertoLista[conciertos.indexOf(concierto)] = concierto.getNombre();
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
		comboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		comboBox.setBounds(10, 52, 290, 21);
		
		panel_1.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Selección de concierto");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 290, 54);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		textField.setBounds(10, 112, 290, 19);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar por nombre");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 93, 127, 19);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				if (textField==null) {
					MenuControl.run("");
				} else {
				MenuControl.run(textField.getText());
				}
			}
		});
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(214, 92, 85, 21);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(255, 255, 255));
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
		btnNewButton_1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JMenuPrincipal frame = new JMenuPrincipal();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
			}
		});
		panel.add(btnNewButton_1);
		
		
	}
}
