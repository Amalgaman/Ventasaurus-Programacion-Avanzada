package Interfaz;

import java.util.LinkedList;

import javax.swing.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.Concierto;

//import java.util.Date;

//import java.sql.Date;

import Datos.Conexion;
import Negocio.Verifica;

public class MenuCliente {

	// 3 pregunta
	// 2 advertencia
	// 1 info
	// 0 cruz

	/*
	 * public static void clienteVerMenu() { String[] opciones = { "Ver Conciertos",
	 * "Comprar entradas", "Solicitar devolucion", "Salir" }; int xd; do { xd =
	 * JOptionPane.showOptionDialog(null, "Seleccione una de las opciones",
	 * "Ventasaurus - Bienvenido !", 0, 0, null, opciones, 0); switch (xd) { case 0:
	 * listaConciertosCliente(); break; case 1: // CompraryPagar(); break; case 2:
	 * SolicitudDeDevolucion(); break; case 3: JOptionPane.showMessageDialog(null,
	 * "Abuenoadiosmaster"); break; default: break; } } while (xd != 3);// repetir
	 * hasta que le de salir // listaConciertos(); }
	 */
	public static void listaConciertosCliente() {
		String opConcierto;
		ImageIcon icon = new ImageIcon("src/img/tickets.png");	
		int op;
		String[] opcClienteConc = { "Comprar entradas", "Volver" };
		String[] opcionesConcierto = { "Los Redondos 03/05/2023", "SodaStereo 08/05/2023",
				"Las Pastillas del Abuelo 08/10/2023", "Hermetica 12/05/2023", "Fito Paez 13/07/2023",
				"Leon Gieco 08/05/2023", "Sumo 24/10/2023" };

		opConcierto = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Selecciona un Concierto" // Mensaje de la ventana
				, "Ventasaurus - Conciertos" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, icon // null para icono defecto de la ventana
				, opcionesConcierto // el objeto
				, opcionesConcierto[0] // posicion del que va aparecer seleccionado
		);
		if (opConcierto != null) {
			op = JOptionPane.showOptionDialog(null, opConcierto + " \nLa aclamada banda hara su gira de despedida"
					+ " \na lo grande, realizando un recorrido por sus"
					+ " \ngrandes exitos ¿Que estas esperando? Saca tu" + " \nentrada." + " \nPrecios desde $1200",
					"Ventasaurus - Administracion", 0, 0, null, opcClienteConc, 0);

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

	public static void SolicitudDeDevolucion() {
		String opDevolucion;
		//
		/*
		 
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();
	
		String[] conciertoLista = new String[listaTraida.size()+1];
		conciertoLista[0] = "Crear nuevo Concierto";
		
		i=1;
		
		for (Concierto concierto : listaTraida) {
			conciertoLista[i] = concierto.getNombre();
			i++;
		}
		
		opConcierto = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona un Concierto" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,conciertoLista // el objeto
				,conciertoLista[0] // posicion del que va aparecer seleccionado
				); 
		 
		 
		 */
		String[] opcionesDev = { "Los Redondos 03/05/2023  x2 ", "SodaStereo 08/05/2023  x1",
				"Las Pastillas del Abuelo 08/10/2023  x3", "Hermetica 12/05/2023  x2", "Fito Paez 13/07/2023  x4",
				"Leon Gieco 08/05/2023  x2", "Sumo 24/10/2023 x1" };
		//
		String dni;
		ImageIcon icon = new ImageIcon("src/img/pago.png");
		do {
			dni = JOptionPane.showInputDialog(null, "Ingrese su DNI");
		} while (dni == null || dni.length() <= 7);

		// habria q optimizar esta parte (tratar d hacerlo sin el try catch)
		//try {
			opDevolucion = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
					, "Selecciona un Concierto para generar una devolucion" // Mensaje de la ventana
					, "Ventasaurus - Devoluciones" // Titulo de la ventana
					, JOptionPane.QUESTION_MESSAGE // Icono
					, icon // null para icono defecto de la ventana
					, opcionesDev // el objeto
					, opcionesDev[0] // posicion del que va aparecer seleccionado
			);
			// no toma el else?
			if (opDevolucion != null && !opDevolucion.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Solicitud recibida exitosamente" + "\nRecibirá un mail cuando la devolución se apruebe",
						"Recibido", 1, null);
			}else {
				JOptionPane.showMessageDialog(null,
						"No se eligio ningun concierto / Ocurrio un error al elegir el concierto"
								+ "\nVolviendo al menú principal",
						"Error inesperado", 0, null);
			}
			
			// el catch lo soluciona
		/*
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Ocurrio el siguiente error al tratar de generar una devolucion: \n" + e);
		}*/

		// guardarDevolucion();

		// no llamar a MenuPrincipal.principal(); el do while lo hace solo

		// MenuPrincipal.principal();
	}

	public static void guardarDevolucion() { // falta la validacion y agregarlo a la base
		/*
		 * no va :( 
		 * 
		 * Conexion con = new Conexion();
		 * 
		 * Connection conexion = (Connection) con.conectar();
		 * 
		 * PreparedStatement stmt;
		 * 
		 * String devolucionEstado="";
		 * 
		 * // Fecha actual Date fechaActual = new Date();
		 * 
		 * // Convertir la fecha actual para pasar a la base de datos java.sql.Date
		 * fechaSQL = new java.sql.Date(fechaActual.getTime());
		 * 
		 * String estadopedido="A revisar";
		 * 
		 * String sql
		 * ="INSERT INTO `devolucion`(`id`, `estado`, `creacion`, `id_cliente`) VALUES (?,?,?,?')"
		 * ; (
		 * 
		 * 
		 * try { /* stmt = (PreparedStatement) conexion.prepareStatement(sql);
		 * stmt.setInt(1, 0); stmt.setString(2, devolucionEstado()); stmt.setDate(3,
		 * fechaSQL()); stmt.setInt(4, dni()); stmt.executeUpdate(); conexion.close();
		 * 
		 * 
		 * }catch(Exception excepcion){ System.out.println(excepcion.getMessage());
		 * 
		 * }
		 */
	}
	
// se entrga los tickets al cliente
// averiguar valor 
	public static void CompraryPagar(String nombreConcierto) {
		int cantEntradas = 0;
		ImageIcon icon = new ImageIcon("src/img/pago.png");
		try {
			cantEntradas = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Ingresar cantidad de tickets a comprar",""));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio el siguiente error al tratar de comprar las entradas:\n" + e);
			JOptionPane.showMessageDialog(null, "No pudiste compraste entradas");
		}
		if (cantEntradas > 0) {
			JOptionPane.showMessageDialog(null, "Compraste " + cantEntradas + " entrada/s para : " + nombreConcierto
					+ "\nPrecio final de la compra : 'x'","Gracias por comprar en Ventasaurus"/* precio sacado de la base de datos */, 0, icon);
		}else {
			JOptionPane.showMessageDialog(null, "No compraste entradas");
		}

		listaConciertosCliente();
	}

}
