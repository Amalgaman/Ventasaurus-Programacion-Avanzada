package Interfaz;

import javax.swing.JOptionPane;

public class MenuCliente {

	/*public static void clienteVerMenu() {
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
				// CompraryPagar();
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
*/
	public static void listaConciertosCliente() {
		String opConcierto;
		int op;
		String[] opcClienteConc = { "Comprar entradas", "Volver" };
		String[] opcionesConcierto = { "Los Redondos 03/05/2023", "SodaStereo 08/05/2023",
				"Las Pastillas del Abuelo 08/10/2023", "Hermetica 12/05/2023", "Fito Paez 13/07/2023",
				"Leon Gieco 08/05/2023", "Sumo 24/10/2023" };

		opConcierto = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Selecciona un Concierto" // Mensaje de la ventana
				, "Ventasaurus - Conciertos" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, null // null para icono defecto de la ventana
				, opcionesConcierto // el objeto
				, opcionesConcierto[0] // posicion del que va aparecer seleccionado
		);
if (opConcierto==null) {
	MenuPrincipal.principal();
}else {
	
		op = JOptionPane.showOptionDialog(null,
				opConcierto + " \nLa aclamada banda hara su gira de despedida"
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
		
		String[] opcionesDev = { "Los Redondos 03/05/2023  x2 ", "SodaStereo 08/05/2023  x1",
				"Las Pastillas del Abuelo 08/10/2023  x3", "Hermetica 12/05/2023  x2", "Fito Paez 13/07/2023  x4",
				"Leon Gieco 08/05/2023  x2", "Sumo 24/10/2023 x1"};
		String dni;
		do {
				dni=JOptionPane.showInputDialog(null, "Ingrese su DNI");
		} while (dni.length()!=8);
		opDevolucion = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Selecciona un Concierto para generar una devolucion" // Mensaje de la ventana
				, "Ventasaurus - Devoluciones" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, null // null para icono defecto de la ventana
				, opcionesDev // el objeto
				, opcionesDev[0] // posicion del que va aparecer seleccionado
		);
		JOptionPane.showMessageDialog(null, "Solicitud recibida exitosamente "
				+ "\nSe le mandara un mail cuando el admin lo apruebe");
		MenuPrincipal.principal();

	}

	public static void CompraryPagar(String nombreConcierto) {// se entrga los tickets al cliente
		int cantEntradas;
		do {
			cantEntradas = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Ingresar cantidad de tickets a comprar"));
		} while (cantEntradas <= 0);
		JOptionPane.showMessageDialog(null, "Compraste " + cantEntradas + " entradas para : " + nombreConcierto);
		listaConciertosCliente();
	}

}
