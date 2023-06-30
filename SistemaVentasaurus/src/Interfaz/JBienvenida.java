package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Interfaz.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class JBienvenida extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageIcon icon = new ImageIcon("src/img/dino.jpg");
					mostrarMensaje(icon);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JBienvenida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}
	private static void mostrarMensaje(Icon mensaje) {
        JDialog dialogo = new JDialog();
        dialogo.setUndecorated(true);
        
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
