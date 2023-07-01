package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;

public class Jtutorial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jtutorial frame = new Jtutorial();
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
	public Jtutorial() {
		setResizable(false);
		setUndecorated(true);
		
		setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton volverMenuPrincipal = new JButton("Volver atras");
		volverMenuPrincipal.setBounds(537, 406, 123, 23);
		volverMenuPrincipal.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		contentPane.add(volverMenuPrincipal);
		volverMenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volverAlMenuPrincipalOg();
			}
		});
		
		JTextArea txtrPaso1 = new JTextArea();
		txtrPaso1.setText("Paso 1:  Ingresar su numero de dni (sin puntos) Ejemplo------>");
		txtrPaso1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrPaso1.setEditable(false);
		txtrPaso1.setBackground(Color.LIGHT_GRAY);
		txtrPaso1.setBounds(10, 11, 517, 35);
		contentPane.add(txtrPaso1);
		
		JTextArea txtrIngreseSuDni = new JTextArea();
		txtrIngreseSuDni.setText("Ingrese su dni");
		txtrIngreseSuDni.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		txtrIngreseSuDni.setEditable(false);
		txtrIngreseSuDni.setBackground(Color.LIGHT_GRAY);
		txtrIngreseSuDni.setBounds(538, 5, 102, 23);
		contentPane.add(txtrIngreseSuDni);
		
		JTextArea dni = new JTextArea();
		dni.setBounds(526, 28, 123, 16);
		contentPane.add(dni);
		
		JButton btnComprobarDni = new JButton("Comprobar dni");
		btnComprobarDni.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnComprobarDni.setBounds(526, 49, 123, 23);
		contentPane.add(btnComprobarDni);
		
		JCheckBox soloDisponibles = new JCheckBox("Ver solo los conciertos disponibles");
		soloDisponibles.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		soloDisponibles.setBounds(135, 100, 207, 23);
		contentPane.add(soloDisponibles);
		
		JTextArea txtrPaso2 = new JTextArea();
		txtrPaso2.setText("Paso 2: Clikear ");
		txtrPaso2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrPaso2.setEditable(false);
		txtrPaso2.setBackground(Color.LIGHT_GRAY);
		txtrPaso2.setBounds(10, 98, 130, 23);
		contentPane.add(txtrPaso2);
		
		JTextArea txtrPaso3 = new JTextArea();
		txtrPaso3.setText("Paso 3: Tocar el boton ");
		txtrPaso3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrPaso3.setEditable(false);
		txtrPaso3.setBackground(Color.LIGHT_GRAY);
		txtrPaso3.setBounds(10, 164, 183, 23);
		contentPane.add(txtrPaso3);
		
		JButton continuarBoton1 = new JButton("Continuar");
		continuarBoton1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		continuarBoton1.setBounds(197, 167, 102, 23);
		contentPane.add(continuarBoton1);
		
		JTextArea txtrParaVerLos = new JTextArea();
		txtrParaVerLos.setText("ese momento y seleccionar uno de ellos");
		txtrParaVerLos.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrParaVerLos.setEditable(false);
		txtrParaVerLos.setBackground(Color.LIGHT_GRAY);
		txtrParaVerLos.setBounds(19, 125, 640, 23);
		contentPane.add(txtrParaVerLos);
		
		JTextArea txtrPaso4 = new JTextArea();
		txtrPaso4.setText("Paso 4: Seleccionar una de las ubicaciones disponibles");
		txtrPaso4.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrPaso4.setEditable(false);
		txtrPaso4.setBackground(Color.LIGHT_GRAY);
		txtrPaso4.setBounds(11, 223, 428, 28);
		contentPane.add(txtrPaso4);
		
		JTextArea txtrPaso5 = new JTextArea();
		txtrPaso5.setText("Paso 5: Escribir la cantidad de entradas a comprar (Minimo 1, Maximo 10)");
		txtrPaso5.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrPaso5.setEditable(false);
		txtrPaso5.setBackground(Color.LIGHT_GRAY);
		txtrPaso5.setBounds(10, 287, 605, 35);
		contentPane.add(txtrPaso5);
		
		JTextArea txtrPaso6 = new JTextArea();
		txtrPaso6.setText("Paso 6: Tocar el boton ");
		txtrPaso6.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrPaso6.setEditable(false);
		txtrPaso6.setBackground(Color.LIGHT_GRAY);
		txtrPaso6.setBounds(10, 353, 186, 25);
		contentPane.add(txtrPaso6);
		
		JButton continuarBoton2 = new JButton("Continuar");
		continuarBoton2.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		continuarBoton2.setBounds(198, 357, 102, 23);
		contentPane.add(continuarBoton2);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(0, 153, 670, 16);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(0, 84, 670, 16);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.DARK_GRAY);
		separator_2.setBounds(5, 208, 670, 16);
		contentPane.add(separator_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBackground(Color.DARK_GRAY);
		separator_2_1.setBounds(0, 266, 670, 16);
		contentPane.add(separator_2_1);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setBackground(Color.DARK_GRAY);
		separator_2_2.setBounds(-2, 332, 670, 16);
		contentPane.add(separator_2_2);
		
		JTextArea txtrParaVerLos_1 = new JTextArea();
		txtrParaVerLos_1.setText("para ver los conciertos disponibles en ");
		txtrParaVerLos_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrParaVerLos_1.setEditable(false);
		txtrParaVerLos_1.setBackground(Color.LIGHT_GRAY);
		txtrParaVerLos_1.setBounds(351, 98, 309, 23);
		contentPane.add(txtrParaVerLos_1);
		
		JTextArea txtrParaPasarA = new JTextArea();
		txtrParaPasarA.setText("para pasar a la siguiente pestaña");
		txtrParaPasarA.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrParaPasarA.setEditable(false);
		txtrParaPasarA.setBackground(Color.LIGHT_GRAY);
		txtrParaPasarA.setBounds(305, 164, 281, 23);
		contentPane.add(txtrParaPasarA);
		
		JTextArea txtrParaComprarLas = new JTextArea();
		txtrParaComprarLas.setText("para comprar las entradas");
		txtrParaComprarLas.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		txtrParaComprarLas.setEditable(false);
		txtrParaComprarLas.setBackground(Color.LIGHT_GRAY);
		txtrParaComprarLas.setBounds(305, 355, 221, 25);
		contentPane.add(txtrParaComprarLas);
		btnComprobarDni.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String dniText = dni.getText();
			for (int i = 0; i < dniText.length(); i++) {
		        if (Character.isDigit(dniText.charAt(i))&&(dniText.length() == 8)) {
		            mostrarMensaje("El DNI es válido");
		        } else if ((dniText.length() != 8)) {
					mostrarMensaje("El DNI debe tener 8 caracteres");
				} else {
					mostrarMensaje("El DNI no es válido");
				}   
		        
		    }}
		});
		
		
		
		
		
}
	private void mostrarMensaje(String mensaje) {
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


	public void volverAlMenuPrincipalOg() {
		JMenuPrincipal nuevo = new JMenuPrincipal();
		nuevo.setLocationRelativeTo(null);
		nuevo.setVisible(true);
		dispose();
	}
}

