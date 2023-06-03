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
			switch(login()) {
			case "null": //Esto es por si el usuario toca "Cancelar" o "Cerrar"
				break;
			case "admin":
				MenuAdmin.principal();
				break;
			case "control":
				MenuControl.principal();
				break;
			case "incorrecto":
				JOptionPane.showMessageDialog(null, "DNI o Contraseña incorrectos");
				break;
			case "error":
				JOptionPane.showMessageDialog(null, "Error al conectar con servidor");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Ocurrio un error inesperado");
				break;	
			}
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
