package Interfaz;

import javax.swing.JOptionPane;

public class MenuControl {
	public static void principal() {
		String[] opciones = {"Elegir Concierto","Salir"};
		int eleccion=JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - Control", 0, 0, null, opciones, 0);
		if (eleccion==0) {
			listaConciertos();
		} 
	}
	
	public static void listaConciertos() {
		String opConcierto = " ";
		String[] opcionesConcierto ={"Los Redondos","SodaStereo","Las Pastillas del Abuelo","Hermetica","Fito Paez","Leon Gieco","Sumo"};
		
		opConcierto = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona un Concierto" // Mensaje de la ventana
				,"Ventasaurus - Seleccion de concierto" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,opcionesConcierto // el objeto
				,opcionesConcierto[0] // posicion del que va aparecer seleccionado
				);
		if (opConcierto!=null) {
			control();
		}
	}
	
	public static void control() {
		String datos= JOptionPane.showInputDialog("Ingresar datos de cliente y entrada");
		if (datos==null) {
			listaConciertos();
		} else if (datos.equalsIgnoreCase("correcto")) {
			JOptionPane.showMessageDialog(null, "Entrada verificada!");
			control();
		} else {
			JOptionPane.showMessageDialog(null, "Los datos no coinciden");
			control();
		}
		
	}
}