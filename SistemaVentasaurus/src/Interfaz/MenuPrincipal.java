package Interfaz;

import javax.swing.JOptionPane;

public class MenuPrincipal {

	public static void principal() {
		String[] opciones = {"Ver conciertos","Solicitar Devolucion","Ingreso Empleado","Salir"};
		int eleccion=JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - ¡ Bienvenido !", 0, 0, null, opciones, 0);
	switch (eleccion) {
	case 0:
		MenuCliente.listaConciertosCliente();
		break;
	case 1:
		MenuCliente.SolicitudDeDevolucion();
		break;
	case 2:
		if (login()) {
				MenuControl.principal();
			} else {
				JOptionPane.showMessageDialog(null, "Incorrecto");
				principal();
			}
		break;
	case 3:
		
		break;
	default:
		break;
	}
		
	}
	
	public static boolean login() {
		String dni = JOptionPane.showInputDialog("Ingresar dni");
		if (dni==null) {
			principal();
		}
		String codigo = JOptionPane.showInputDialog("Ingresar codigo");
		if (codigo==null) {
			principal();
		}
		if (dni.equals("1234") && codigo.equals("1234")) {
			return true;
		} else {
			return false;
		}
	}
}
