package Interfaz;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// borrar los // para usar el switch
//		int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccine como ingresar \n1 Cliente \n2 Empleado"));
//		switch (a) {
//		case 1:
			MenuCliente.clienteVerMenu();
//			break;
//		case 2:
			MenuAdmin.principal();
//			break;
//		default:
//			break;
//		}
		
	}

}
