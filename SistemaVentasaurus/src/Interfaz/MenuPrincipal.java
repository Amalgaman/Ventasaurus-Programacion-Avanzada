package Interfaz;

import javax.swing.JOptionPane;

public class MenuPrincipal {

	
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
			switch(login()) {
			case 0:
				MenuAdmin.principal();
				break;
			case 1:
				MenuControl.principal();
				break;
			default:
				JOptionPane.showMessageDialog(null, "Incorrecto");
				break;
			}
			break;
		default:
			cerrar = true;
			break;
		}
		}while(!cerrar);
		
		
	}
	
	public static int login() {
		String dni = JOptionPane.showInputDialog("Ingresar dni");
		
		if (dni==null) {
			principal();
		}
		String codigo = JOptionPane.showInputDialog("Ingresar codigo");
		
		if (codigo==null) {
			principal();
		}
		
		if (dni.equals("1234") && codigo.equals("1234")) {
			return 0;
		} if (dni.equals("4321") && codigo.equals("1234")) {
			return 1;
		}else {
			return 3;
		}
	}
}
