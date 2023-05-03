package Interfaz;

import javax.swing.JOptionPane;

public class MenuCliente {

	public static void clienteVerMenu() {
		String[] opciones = { "Ver Conciertos", "Comprar entradas", "Solicitar devolucion", "Salir" };
		int xd;
		do {
			xd = JOptionPane.showOptionDialog(null, "Seleccione una de las opciones", "Ventasaurus - Bienvenido !", 0,
					0, null, opciones, 0);
			switch (xd) {
			case 0:
				listaConciertosCliente();
				break;
			case 1:
				CompraryPagar();
				break;
			case 2:
				SolicitudDeDevolucion();
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Abuenoadiosmaster");
				break;
			default:
				break;
			}
		} while (xd != 3);// repetir hasta que le de salir
		// listaConciertos();
	}

	public static void listaConciertosCliente() {
		String opConcierto;
		String[] opcionesConcierto = { "Los Redondos", "SodaStereo", "Las Pastillas del Abuelo", "Hermetica",
				"Fito Paez", "Leon Gieco", "Sumo" };

		opConcierto = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Selecciona un Concierto" // Mensaje de la ventana
				, "Ventasaurus - Administracion" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, null // null para icono defecto de la ventana
				, opcionesConcierto // el objeto
				, opcionesConcierto[0] // posicion del que va aparecer seleccionado
		);

	}

	public static void SolicitudDeDevolucion() {
		String opDevolucion;
		String[] opcionesDev = { "Los Redondos", "SodaStereo", "Las Pastillas del Abuelo", "Hermetica", "Fito Paez",
				"Leon Gieco", "Sumo" };

		opDevolucion = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Selecciona un Concierto para generar una devolucion" // Mensaje de la ventana
				, "Ventasaurus - Devoluciones" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, null // null para icono defecto de la ventana
				, opcionesDev // el objeto
				, opcionesDev[0] // posicion del que va aparecer seleccionado
		);

	}

	public static void CompraryPagar() {// se entrga los tickets al cliente
		int cantEntradas;
		do {
			cantEntradas = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Ingresar cantidad de tickets a comprar"));
		} while (cantEntradas <= 0);
		JOptionPane.showMessageDialog(null,
				"Compraste " + cantEntradas + " entradas de la banda :" /* nombreDeBanda */);
	}

}
