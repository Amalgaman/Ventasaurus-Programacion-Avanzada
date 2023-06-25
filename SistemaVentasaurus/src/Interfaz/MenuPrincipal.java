package Interfaz;

import javax.swing.JOptionPane;

import Negocio.Verifica;

public class MenuPrincipal {

	static Verifica verifica = new Verifica();
	
	public static void principal() {
		String[] opciones = {"Ver conciertos","Solicitar Devolucion","Ingreso Empleado","Salir"};
		int eleccion;
		boolean cerrar = false;
		do {
			eleccion=JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - ¡ Bienvenido !", 0, 0, null, opciones, 0);
	
		switch (eleccion) {
		case 0:
			MenuCliente.listaConciertosCliente();
			break;
		case 1:
			MenuCliente.SolicitudDeDevolucion();
			break;
		case 2:
			Login.run();
			cerrar=true;
			break;
		default:
			cerrar = true;
			break;
		}
		}while(!cerrar);
		
		
	}
	
}
