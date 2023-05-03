package Interfaz;

import javax.swing.JOptionPane;

public class MenuAdmin {
	
	public static void principal() {
		String[] opciones = {"Ver Conciertos","Ver Solicitudes de Devolucion","Generar Devolucion","Salir"};
		JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - Administracion", 0, 0, null, opciones, 0);
		listaConciertos();
	}
	
	public static void listaConciertos() {
		String opConcierto = " ";
		String[] opcionesConcierto ={"Crear Concierto","Los Redondos","SodaStereo","Las Pastillas del Abuelo","Hermetica","Fito Paez","Leon Gieco","Sumo"};
		
		opConcierto = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona un Concierto" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,opcionesConcierto // el objeto
				,opcionesConcierto[0] // posicion del que va aparecer seleccionado
				);
		
		
	}
}
