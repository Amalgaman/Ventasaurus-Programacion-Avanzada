package Interfaz;

import javax.swing.JOptionPane;
import Jframes.Login;

import Negocio.Verifica;

public class MenuPrincipal {

	static Verifica verifica = new Verifica();
	
	public static void principal() {
		MenuCliente cliente=new MenuCliente();
		String[] opciones = {"Ver conciertos","Solicitar Devolucion","Ingreso Empleado","Salir"};
		int eleccion;
		boolean cerrar = false;
		do {
			eleccion=JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - ¡ Bienvenido !", 0, 0, null, opciones, 0);
	
		switch (eleccion) {
		case 0:
			cliente.listaConciertosCliente();
			break;
		case 1:
			cliente.SolicitudDeDevolucion();
			cerrar=true;
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
	
	public static String login() {
		String dni = JOptionPane.showInputDialog("Ingresar dni");
		
		if (dni==null) {
			return "null";
		}else {
			String password = JOptionPane.showInputDialog("Ingresar contraseña");
			if (password==null) {
				return "null";
			}else {
				return verifica.verificaIngresarEmpleado(dni, password);
			}
		}
		
	}
}
