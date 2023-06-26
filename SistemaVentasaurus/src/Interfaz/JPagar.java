package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;

public class JPagar extends JFrame {

	private JPanel contentPane;
	private JTextField cantEntr;
	private final JTable infoLugares = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPagar frame = new JPagar();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					JMenuPrincipal nuevo = new JMenuPrincipal();
					nuevo.setLocationRelativeTo(null);
					nuevo.setVisible(true);

				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public JPagar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 500, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox lugaes = new JComboBox();
		lugaes.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		lugaes.setBounds(30, 67, 192, 23);
		contentPane.add(lugaes);
		java.sql.Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasaurusdb", "root", "");

			// Crear una sentencia SQL
			stmt = conn.createStatement();

			// Ejecutar la consulta y obtener el resultado
			String consulta = "SELECT nombre,precio FROM localidad";
			rs = stmt.executeQuery(consulta);

			// Agregar los datos al JComboBox
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				lugaes.addItem(nombre);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error \n" + e);

		}
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnCancelar.setBounds(399, 292, 90, 23);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlMenuPrincipal();
			}
		});

		cantEntr = new JTextField();
		cantEntr.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		cantEntr.setBounds(346, 66, 42, 21);
		contentPane.add(cantEntr);
		cantEntr.setColumns(10);

		JTextArea txtrElegirPlatea = new JTextArea();
		txtrElegirPlatea.setText("Elegir ubicación");
		txtrElegirPlatea.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrElegirPlatea.setEditable(false);
		txtrElegirPlatea.setBackground(Color.LIGHT_GRAY);
		txtrElegirPlatea.setBounds(59, 24, 124, 36);
		contentPane.add(txtrElegirPlatea);

		JTextArea txtrIngresarCantidadDe = new JTextArea();
		txtrIngresarCantidadDe.setLineWrap(true);
		txtrIngresarCantidadDe.setText("Ingresar cantidad de tickets a comprar");
		txtrIngresarCantidadDe.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrIngresarCantidadDe.setEditable(false);
		txtrIngresarCantidadDe.setBackground(Color.LIGHT_GRAY);
		txtrIngresarCantidadDe.setBounds(284, 11, 171, 56);
		contentPane.add(txtrIngresarCantidadDe);

		JButton continuar = new JButton("Continuar");
		continuar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		continuar.setBounds(284, 292, 104, 23);
		contentPane.add(continuar);
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = cantEntr.getText();
				boolean contieneLetras = false;
				char letra = '\0';
				int numero = Integer.parseInt(texto);
				try {
					for (int i = 0; i < cantEntr.getText().length(); i++) {
					/*	if (!Character.isDigit(texto.charAt(i))) {
						contieneLetras = true;
						letra = texto.charAt(i); // Almacenar la letra encontrada
						break;
					}*/
						if (numero >= 1 && numero <= 10) {
							mostrarMensajeError("compraste "+ numero +" entradas para :"+"");	
							volverAlMenuPrincipal();
						} else if(numero>=11||!Character.isDigit(texto.charAt(i))){
							mostrarMensajeError("Error al ingresar cantidad de entradas") /*"\nCaracter a corregir "+letra)*/;
					} 
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error \n" + e2);
					 JMenuPrincipal nuevo  = new JMenuPrincipal();
					 nuevo.setLocationRelativeTo(null);
						nuevo.setVisible(true);
						dispose();
				}
			}
		});

		DefaultTableModel modeloTabla = new DefaultTableModel();
		infoLugares.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		infoLugares.setShowGrid(false);
		infoLugares.setEnabled(false);
		infoLugares.setRowSelectionAllowed(false);
		infoLugares.setModel(modeloTabla);
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventasaurusdb", "root", "");
			// Crear una sentencia SQL
			stmt = conn.createStatement();

			// Ejecutar la consulta y obtener el resultado
			String consulta = "SELECT nombre,precio,cupos FROM localidad";
			rs = stmt.executeQuery(consulta);

			// Obtener los metadatos de la consulta (nombres de columnas)
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumnas = rsmd.getColumnCount();
			for (int i = 1; i <= numColumnas; i++) {
				String nombreColumna = rsmd.getColumnName(i);
				modeloTabla.addColumn(nombreColumna);
			}
			// Agregar los datos a la tabla
			while (rs.next()) {
				Object[] fila = new Object[numColumnas];
				for (int i = 1; i <= numColumnas; i++) {
					fila[i - 1] = rs.getObject(i);
				}
				modeloTabla.addRow(fila);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error \n" + e);
		}
		JLabel imgTickets = new JLabel();
		ImageIcon icon = new ImageIcon("src/img/ticket.jpg");
		imgTickets.setIcon(icon);
		imgTickets.setBounds(10, 101, 237, 179);
		contentPane.add(imgTickets);

		infoLugares.setCellSelectionEnabled(true);
		infoLugares.setColumnSelectionAllowed(true);
		infoLugares.setBounds(257, 126, 232, 155);
		contentPane.add(infoLugares);

		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		txtrNombre.setText("   Nombre");
		txtrNombre.setEditable(false);
		txtrNombre.setBounds(257, 103, 78, 23);
		contentPane.add(txtrNombre);

		JTextArea txtrPrecio = new JTextArea();
		txtrPrecio.setEditable(false);
		txtrPrecio.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		txtrPrecio.setText("   Precio");
		txtrPrecio.setBounds(335, 103, 78, 23);
		contentPane.add(txtrPrecio);

		JTextArea txtrCupos = new JTextArea();
		txtrCupos.setEditable(false);
		txtrCupos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		txtrCupos.setText("   Cupos");
		txtrCupos.setBounds(411, 103, 78, 23);
		contentPane.add(txtrCupos);

		
		
		
	}
	public void volverAlMenuPrincipal(){
		JMenuPrincipal nuevo = new JMenuPrincipal();
		nuevo.setLocationRelativeTo(null);
		nuevo.setVisible(true);
		dispose();
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
	
}
