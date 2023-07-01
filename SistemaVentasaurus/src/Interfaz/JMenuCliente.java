package Interfaz;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.*;
import Datos.Conexion;
import Negocio.Verifica;
import javax.swing.*;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class JMenuCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtInfoDelConcierto;
	private final JTable infoLugares = new JTable();
	private String nombreConcierto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuCliente frame = new JMenuCliente();
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
	public JMenuCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel imgConcierto = new JLabel();
		ImageIcon icon = new ImageIcon("src/img/dinosrock.png");
		imgConcierto.setIcon(icon);
		imgConcierto.setBounds(10, 97, 383, 319);
		contentPane.add(imgConcierto);

		JTextArea dni = new JTextArea();
		dni.setBounds(222, 68, 123, 16);
		contentPane.add(dni);

		

		JButton volverMenuPrincipal = new JButton("Volver atras");
		volverMenuPrincipal.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		volverMenuPrincipal.setBounds(551, 428, 123, 23);
		contentPane.add(volverMenuPrincipal);
		volverMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlMenuPrincipalOg();
			}
		});

		JTextArea infoConciertos = new JTextArea();
		infoConciertos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		infoConciertos.setEditable(false);
		infoConciertos.setBounds(417, 181, 257, 219);
		infoConciertos.setLineWrap(true); // Ajuste de línea activado
		infoConciertos.setWrapStyleWord(true); // Ajuste de palabra activado
		infoConciertos.setPreferredSize(new Dimension(221, 206)); // Ajusta la altura del JTextArea
		contentPane.add(infoConciertos);

		JComboBox listaConciertos = new JComboBox();
		listaConciertos.setBounds(417, 111, 257, 23);
		contentPane.add(listaConciertos);
		java.sql.Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		listaConciertos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String conciertoSeleccionado = (String) listaConciertos.getSelectedItem();
		        nombreConcierto = conciertoSeleccionado;
				if (conciertoSeleccionado != null) {
					
					try (java.sql.Connection conn = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/ventasaurusdb", "root", "");
							Statement stmt = conn.createStatement()) {

						String consulta = "SELECT * FROM concierto WHERE nombre = '" + conciertoSeleccionado + "'";
						ResultSet rs = stmt.executeQuery(consulta);

						if (rs.next()) {
							String nombre = rs.getString("nombre");
							String descripcion = rs.getString("descripcion");
							String direccion = rs.getString("direccion");
							String fecha = rs.getString("fecha");

							// Mostrar la información del concierto en el JTextArea
							infoConciertos.setText("Nombre: " + nombre + "\nDescripcion: " + descripcion + "\nFecha: "
									+ fecha + "\nDireccion: " + direccion);
						} else {
							// No se encontró información del concierto
							infoConciertos.setText("No se encontró información del concierto");
						}
						conn.close();
					} catch (Exception ex) {
						ImageIcon iconsad = new ImageIcon("src/img/troste.jpg"); 
						JOptionPane.showMessageDialog(null, "Error al obtener la lista de conciertos:\n" + ex.getMessage()
								+ "\n\n Comuniquese con un administrador e intentelo más tarde",null, 0, iconsad);
					}
				}
			}
		});

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasaurusdb", "root", "");

			// Crear una sentencia SQL
			stmt = conn.createStatement();

			// Ejecutar la consulta y obtener el resultado
			String consulta = "SELECT nombre FROM concierto";
			rs = stmt.executeQuery(consulta);

			// Agregar los datos al JComboBox
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				listaConciertos.addItem(nombre);
			}
			conn.close();

		} catch (Exception e) {
			ImageIcon iconsad = new ImageIcon("src/img/troste.jpg"); 
			JOptionPane.showMessageDialog(null, "Error al obtener la lista de conciertos:\n" + e.getMessage()
					+ "\n\n Comuniquese con un administrador e intentelo más tarde",null, 0, iconsad);
			
			 
//			volverAlMenuPrincipalOg();
		}

		JTextArea txtrEli = new JTextArea();
		txtrEli.setText("Debe ingresar dni para sacar tickets del concierto que elija");
		txtrEli.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrEli.setEditable(false);
		txtrEli.setBackground(Color.LIGHT_GRAY);
		txtrEli.setBounds(116, 9, 469, 35);
		contentPane.add(txtrEli);

		JTextArea txtrIngreseSuDni = new JTextArea();
		txtrIngreseSuDni.setText("Ingrese su dni");
		txtrIngreseSuDni.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrIngreseSuDni.setEditable(false);
		txtrIngreseSuDni.setBackground(Color.LIGHT_GRAY);
		txtrIngreseSuDni.setBounds(116, 63, 102, 23);
		contentPane.add(txtrIngreseSuDni);

		JTextArea txtrConciertosDisponibles = new JTextArea();
		txtrConciertosDisponibles.setText("Lista de conciertos ");
		txtrConciertosDisponibles.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrConciertosDisponibles.setEditable(false);
		txtrConciertosDisponibles.setBackground(Color.LIGHT_GRAY);
		txtrConciertosDisponibles.setBounds(467, 86, 141, 21);
		contentPane.add(txtrConciertosDisponibles);

		txtInfoDelConcierto = new JTextField();
		txtInfoDelConcierto.setText("Informacion del concierto seleccionado");
		txtInfoDelConcierto.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		txtInfoDelConcierto.setEditable(false);
		txtInfoDelConcierto.setBounds(417, 161, 257, 23);
		contentPane.add(txtInfoDelConcierto);
		txtInfoDelConcierto.setColumns(10);

		JButton comprarPagar = new JButton("Continuar");
		comprarPagar.setEnabled(false);
		comprarPagar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		comprarPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dniText = dni.getText();
				if (dniText.length() != 8) {
					mostrarMensajeError("Ingrese un DNI válido (8 dígitos)");
				} else {
					boolean contieneLetras = false;
					char letra = '\0'; // Variable para almacenar la letra encontrada
					for (int i = 0; i < dniText.length(); i++) {
						if (!Character.isDigit(dniText.charAt(i))) {
							contieneLetras = true;
							letra = dniText.charAt(i); // Almacenar la letra encontrada
							break;
						}
					}
					if (contieneLetras) {
						mostrarMensajeError("El DNI no puede contener lo siguiente '" + letra + "'");
					} else {
						JPagar nuevo = new JPagar(nombreConcierto);
						nuevo.setLocationRelativeTo(null);
						nuevo.setVisible(true);
						dispose();
					}
				}
			}

			private void mostrarMensajeError(String mensaje) {
				JDialog dialogo = new JDialog();
				dialogo.setUndecorated(true);
				// Establecer el tamaño del diálogo en función del mensaje
				int ancho = 400;
				int alto = 100 + (mensaje.length() / 30) * 20; // Ajusta el alto según la longitud del mensaje

				dialogo.setSize(ancho, alto);

				JLabel etiqueta = new JLabel(mensaje);
				etiqueta.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
				dialogo.setLocationRelativeTo(null);

				dialogo.getContentPane().add(etiqueta);

				int duracionMilisegundos = 3000;
				Timer temporizador = new Timer(duracionMilisegundos, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialogo.dispose();
					}
				});
				temporizador.setRepeats(false);
				temporizador.start();

				dialogo.setVisible(true);
			}
		});

		comprarPagar.setBounds(417, 428, 123, 23);
		contentPane.add(comprarPagar);
		
		JCheckBox soloDisponibles = new JCheckBox("Ver solo los conciertos disponibles");
		soloDisponibles.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		soloDisponibles.setBounds(434, 44, 207, 34);
		contentPane.add(soloDisponibles);
		soloDisponibles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String consulta = "SELECT * FROM concierto";
				if (soloDisponibles.isSelected()) {
					LocalDate fechaActual = LocalDate.now();
					String fechaActualFormato = fechaActual.format(DateTimeFormatter.ISO_LOCAL_DATE);
					consulta += " WHERE fecha >= '" + fechaActualFormato + "'";
					comprarPagar.setEnabled(true); // Mostrar el botón "Continuar"
				} else {
					comprarPagar.setEnabled(false); // Ocultar el botón "Continuar"
				}

				try (java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasaurusdb",
						"root", "");
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(consulta)) {

					listaConciertos.removeAllItems();
					while (rs.next()) {
						String nombre = rs.getString("nombre");
						listaConciertos.addItem(nombre);
					}
					conn.close();
				} catch (Exception ex) {
					ImageIcon icon = new ImageIcon("src/img/troste.jpg"); 
					comprarPagar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Error al obtener la lista de conciertos:\n" + ex.getMessage()
							+ "\n\n Comuniquese con un administrador e intentelo más tarde",null, 0, icon);
					
				}

			}
		});

	}
	

	public void volverAlMenuPrincipalOg() {
		JMenuPrincipal nuevo = new JMenuPrincipal();
		nuevo.setLocationRelativeTo(null);
		nuevo.setVisible(true);
		dispose();
	}
	
}
