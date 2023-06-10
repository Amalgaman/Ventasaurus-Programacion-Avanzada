package Interfaz;

import java.util.LinkedList;

import javax.swing.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.Concierto;

//import java.util.Date;

//import java.sql.Date;

import Datos.Conexion;
import Datos.SolicitudDevolucion;
import Negocio.Verifica;

public class MenuCliente {

	//iconos significados
	// 3 pregunta
	// 2 advertencia
	// 1 info
	// 0 cruz

	static Verifica verifica = new Verifica();

	Conexion con = new Conexion();

	Connection conexion = (Connection) con.conectar();

	PreparedStatement stmt;

	public static void listaConciertosCliente() {
		// conectado a la base

		int i = 0;
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();

		String[] conciertoLista = new String[listaTraida.size()];

		for (Concierto concierto : listaTraida) {
			conciertoLista[i] = concierto.getNombre();
			i++;
		}

		String opConcierto;
		ImageIcon icon = new ImageIcon("src/img/tickets.png");
		ImageIcon iconBanda = new ImageIcon("src/img/dinosrock.png");

		int op;
		String[] opcClienteConc = { "Comprar entradas", "Volver" };


//si la lista esta vacia decir que no hay conciertos
		if (conciertoLista.length == 0) {
			JOptionPane.showMessageDialog(null,
					"No hay conciertos desponibles en este momento\nIntentelo mas tarde y/o comuniquese con un administrador",
					":(", JOptionPane.DEFAULT_OPTION, new ImageIcon("src/img/troste.jpg"));
		} else if (conciertoLista.length != 0) {
			opConcierto = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
					, "Selecciona un Concierto" // Mensaje de la ventana
					, "Ventasaurus - Conciertos" // Titulo de la ventana
					, JOptionPane.QUESTION_MESSAGE // Icono
					, icon // null para icono defecto de la ventana
					, conciertoLista// el objeto
					, conciertoLista[0] // posicion del que va aparecer seleccionado
			);

//revisar 
//mostrar desc de la base de datos
			if (opConcierto != null) {
				op = JOptionPane.showOptionDialog(null, opConcierto + " \nLa aclamada banda hara su gira de despedida"
						+ " \na lo grande, realizando un recorrido por sus"
						+ " \ngrandes exitos ¿Que estas esperando? Saca tu" + " \nentrada." + " \nPrecios desde $1200",
						"Ventasaurus - Conciertos", 0, 0, iconBanda, opcClienteConc, 0);
				switch (op) {
				case 0:
					CompraryPagar(opConcierto);
					break;
				case 1:
					listaConciertosCliente();
					break;
				default:
					break;
				}
			}
		}
	}

	public static void SolicitudDeDevolucion() {
		// falta conectarlo a la base d datos
		String opDevolucion;

		int i = 0;
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();

		String[] conciertoLista = new String[listaTraida.size()];

		for (Concierto concierto : listaTraida) {
			conciertoLista[i] = concierto.getNombre();
			i++;
		}

		int dni = 0;
		try {
			verifica.verificarDni(dni);
			ImageIcon icon = new ImageIcon("src/img/pago.png");
			// habria q optimizar esta parte (tratar d hacerlo sin el try catch)
			// try {
			opDevolucion = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
					, "Selecciona un Concierto para generar una devolucion" // Mensaje de la ventana
					, "Ventasaurus - Devoluciones" // Titulo de la ventana
					, JOptionPane.QUESTION_MESSAGE // Icono
					, icon // null para icono defecto de la ventana
					, conciertoLista // el objeto
					, conciertoLista[0] // posicion del que va aparecer seleccionado
			);
			// no toma el else?
			if (opDevolucion != null && !opDevolucion.isEmpty()) {
				SolicitudDevolucion soli = new SolicitudDevolucion(1, "En proceso", 1200, null, null);
				ImageIcon icon2 = new ImageIcon("src/img/correo.png");

				//rellenar con datos
				//fecha automatica'preguntar
				soli.guardarSolicitud();
				JOptionPane.showMessageDialog(null,
						"Solicitud recibida exitosamente" + "\nRecibirá un mail cuando la devolución se apruebe",
						"Recibido", 1, icon2);
				
			} else {
				JOptionPane.showMessageDialog(null,
						"No se eligio ningun concierto / Ocurrio un error al elegir el concierto"
								+ "\nVolviendo al menú principal",
						"Error inesperado", 0, null);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ocurrio un error al ingresar al dni:\n" + e + "\nVolviendo al menú principal");
		}

	}

// averiguar valor 
	public static void CompraryPagar(String nombreConcierto) {
		// falta conectarlo a la base d datos
		int cantEntradas = 0;

		ImageIcon icon = new ImageIcon("src/img/ticket.jpg");
		try {
			cantEntradas = Integer
					.parseInt((String) JOptionPane.showInputDialog(null, "Ingresar cantidad de tickets a comprar",
							"Ventasaurus", JOptionPane.PLAIN_MESSAGE, icon, null, ""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio el siguiente error al tratar de comprar las entradas:\n" + e);
			JOptionPane.showMessageDialog(null, "No pudiste compraste entradas");
		}
		verifica.CantEntradas(cantEntradas, nombreConcierto);
		listaConciertosCliente();
	}

}
