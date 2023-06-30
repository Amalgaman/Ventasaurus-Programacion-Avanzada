package Interfaz;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class JMenuPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JMenuPrincipal frame = new JMenuPrincipal();
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
	public JMenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton verConciertos = new JButton("Ver conciertos disponibles");
		verConciertos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JMenuCliente nuevo = new JMenuCliente();
				nuevo.setLocationRelativeTo(null);
				nuevo.setVisible(true);
				dispose();
			}
		});
		
		verConciertos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		verConciertos.setBounds(50, 70, 206, 43);
		contentPane.add(verConciertos);
		
		JButton Devoluciones = new JButton("Solicitar devolución");
		Devoluciones.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		Devoluciones.setBounds(346, 70, 211, 43);
		contentPane.add(Devoluciones);
		Devoluciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*JMenuCliente nuevo = new JMenuCliente();
				nuevo.setLocationRelativeTo(null);
				nuevo.setVisible(true);
				dispose();*/
            	JOptionPane.showMessageDialog(null, "En proceso");
			}
		});
		
		JButton login = new JButton("Ingresar al sistema");
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		login.setBounds(657, 70, 182, 43);
		contentPane.add(login);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*JMenuCliente nuevo = new JMenuCliente();
				nuevo.setLocationRelativeTo(null);
				nuevo.setVisible(true);
				dispose();*/
            	JOptionPane.showMessageDialog(null, "En proceso");
			}
		});
		
		JButton salirBoton = new JButton("Salir");
		salirBoton.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		salirBoton.setBounds(411, 349, 89, 23);
		ImageIcon icon = new ImageIcon("src/img/chau.gif");
//		salirBoton.setIcon(icon);
		contentPane.add(salirBoton);
		salirBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMensaje(icon);
				dispose();
			}
		});
	
		
		JTextArea bienvenida = new JTextArea();
		bienvenida.setEditable(false);
		bienvenida.setBackground(Color.LIGHT_GRAY);
		bienvenida.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		bienvenida.setText("Bienvenido a Ventasaurus");
		bienvenida.setBounds(336, 11, 250, 43);
		contentPane.add(bienvenida);
		
		JLabel conciertoImg = new JLabel("");
		conciertoImg.setBounds(76, 136, 206, 145);
		ImageIcon iconCon = new ImageIcon("src/img/compra.jpg");
		conciertoImg.setIcon(iconCon);
		contentPane.add(conciertoImg);
		  conciertoImg.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            	JMenuCliente nuevo = new JMenuCliente();
					nuevo.setLocationRelativeTo(null);
					nuevo.setVisible(true);
					dispose();
	            }
	        });
	
		
		JLabel devolucionImg = new JLabel("");
		devolucionImg.setBounds(352, 136, 211, 138);
		ImageIcon iconDev = new ImageIcon("src/img/devolucion.jpg");
		devolucionImg.setIcon(iconDev);
		contentPane.add(devolucionImg);
		 devolucionImg.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            	//Hacer devolucion
					//dispose();
	            	JOptionPane.showMessageDialog(null, "En proceso");
	            }
	        });
		
		JLabel loginImg = new JLabel("");
		loginImg.setBounds(674, 135, 164, 145);
		ImageIcon iconLog = new ImageIcon("src/img/admin.jpg");
		loginImg.setIcon(iconLog);
		contentPane.add(loginImg);
		loginImg.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	//Hacer login
				//dispose();
            	JOptionPane.showMessageDialog(null, "En proceso");
            }
        });
		
	}
	 private static void mostrarMensaje(Icon mensaje) {
	        JDialog dialogo = new JDialog();
	        dialogo.setUndecorated(true);
	        // Establecer el tamaño del diálogo en función del mensaje
	        int ancho = 575;
	        int alto = 575;
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
	 private static void mostrarMensajeBienv(Icon mensaje) {
	        JDialog dialogo = new JDialog();
	        dialogo.setUndecorated(true);
	        // Establecer el tamaño del diálogo en función del mensaje
	        int ancho = 575;
	        int alto = 575;
	        dialogo.setSize(ancho, alto);
	        JLabel etiqueta = new JLabel(mensaje);
	        etiqueta.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
	        dialogo.setLocationRelativeTo(null);
	        dialogo.getContentPane().add(etiqueta);
	        int duracionMilisegundos = 3000;
	        Timer temporizador = new Timer(duracionMilisegundos, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                dialogo.dispose();
	                JMenuPrincipal frame = new JMenuPrincipal();
	    			frame.setLocationRelativeTo(null);
	    			frame.setVisible(true);
	            }
	        });
	        temporizador.setRepeats(false);
	        temporizador.start();

	        dialogo.setVisible(true);
	
	      

	        
	    }
	 }
