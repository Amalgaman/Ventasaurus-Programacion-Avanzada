package Interfaz;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import Datos.Concierto;
import Datos.Localidad;
import Negocio.Verifica;

public class MenuAdmin {
	
	static Verifica verifica = new Verifica();
	
	public static void principal() {
		//Variables inicializadas
		int op = 0;
		String[] opciones = {"Ver Conciertos","Ver Solicitudes de Devolucion","Cerrar Sesion"};
		
		do {
		op = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - Administracion", 0, 0, null, opciones, 0);
		
		switch(op) {
		case 0:
			listaConciertos();
			break;
		case 1:
			listaSolicitudes();
			break;
		case 2:
			MenuPrincipal.principal();
		default:
			break;	
		}
		}while(op<2);
		
	}
	
	public static void listaConciertos() {
		
		//Variables inicializadas
		int op = 0, i=0, cElegido=0;
		String opConcierto = " ";
		String[] abmConcierto ={"Editar Informacion","Cancelar Concierto","Eliminar Concierto","Volver"};
		
		//lista de conciertos
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();
	
		String[] conciertoLista = new String[listaTraida.size()+1];
		conciertoLista[0] = "Crear nuevo Concierto";
		
		i=1;
		
		for (Concierto concierto : listaTraida) {
			conciertoLista[i] = concierto.getNombre();
			i++;
		}
		
		
		opConcierto = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona un Concierto" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,conciertoLista // el objeto
				,conciertoLista[0] // posicion del que va aparecer seleccionado
				);
		
		//Esto es para salir del menu sin que se rompa
		if(opConcierto == null) {
		  
		}else {
			
		 if (opConcierto.equals("Crear nuevo Concierto")) {
			 adminAltaConcierto.run();
		}else {
			for (Concierto concierto : listaTraida) {
				if (opConcierto.equals(concierto.getNombre())) {
					cElegido = listaTraida.indexOf(concierto);
				}
			}
			
			adminConcierto.run(listaTraida.get(cElegido));
		}
		 listaConciertos();
	}
	}
	public static void altaConcierto(){
		//En el futuro podriamos armar un Formulario con JFrame, por ahora son JOptionPanel
		
		String nombre = JOptionPane.showInputDialog("Introduzca nombre del concierto");
		String descripcion = JOptionPane.showInputDialog("Introduzca descripcion");
		String direccion = JOptionPane.showInputDialog("Introduzca direccion del concierto");
		String fecha = JOptionPane.showInputDialog("Introduzca fecha del concierto");
		
		//Validamos la informacion ingresada, devolvemos el id del concierto creado. Si la operacion falla, devuelve 0
		int id = verifica.validarConcierto(nombre, descripcion, direccion, fecha);
		
		if(id > 0) {
			
			JOptionPane.showMessageDialog(null, "El concierto se genero con exito");
			String nombreLoc;
			int cupoLoc;
			double precioLoc;
			
			//Generamos las localidades, minimo 1
			do {
				nombreLoc = JOptionPane.showInputDialog("Introduzca nombre de la localidad");
				cupoLoc = Integer.parseInt(JOptionPane.showInputDialog("Introduzca cupos totales"));
				precioLoc = Double.parseDouble(JOptionPane.showInputDialog("Introduzca precio de localidad"));
				
				if(verifica.validarLocalidad(nombreLoc, cupoLoc, precioLoc, id)) {
					JOptionPane.showMessageDialog(null, "La localidad se guardo con exito");
				}else {
					JOptionPane.showMessageDialog(null, "Hubo un error al guardar la localidad");
				}
			}while(JOptionPane.showConfirmDialog(null, "¿Ingresar otra localidad?") == 0);
		
		}else {
			JOptionPane.showMessageDialog(null, "El alta del concierto no pudo concretarse");
		}
		
	}
	
	public static void listaSolicitudes() {
		//Variables inicializadas
	    int op = 0;
		String opSolicitud = " ";
		String[] abmSolicitud ={"Autorizar Devolucion","Rechazar Solicitud","Volver"};
		
		//lista de solicitudes
		String[] opcionesSolicitud ={"Generar Solicitud","Ramirez Raul - 19/01/2023","Martinez Silvina- 12/02/2023","Smith Mauro- 12/12/2022","Gutierrez Oscar - 19/01/2023"};
		
		opSolicitud = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona una Solicitud" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,opcionesSolicitud // el objeto
				,opcionesSolicitud[0] // posicion del que va aparecer seleccionado
				);
		
		//Esto es para salir del menu sin que se rompa
		if(opSolicitud == null) {
		  
		}else if (opSolicitud.equals("Generar Solicitud")) {
			altaSolicitud();
		}else {
			op = JOptionPane.showOptionDialog(null, opSolicitud
					+" \nLos Redondos 19/02/2023"
					+" \nCantidad: 2"
					+" \nPrecio $2200", "Ventasaurus - Administracion", 0, 0, null, abmSolicitud, 0);
			switch(op) {
			case 0:
				JOptionPane.showMessageDialog(null, "Devolucion autorizada con exito. "
						+ "Se le envio un correo electronico al cliente");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Solicitud rechazada con exito");
				break;
			default:
				break;	
			}
		}
	}
	
	public static void altaSolicitud(){
		//En el futuro podriamos armar un Formulario con JFrame, por ahora son JOptionPanel

		String[] listaEntradas ={"Los Redondos 03/05/2023 - $1200","SodaStereo 08/05/2023 - $2000","Las Pastillas del Abuelo 08/10/2023 - $1500"};
		
		JOptionPane.showInputDialog("Introduzca DNI del cliente");
		JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Seleccione la entrada que quiere cancelar" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,listaEntradas // el objeto
				,listaEntradas[0] // posicion del que va aparecer seleccionado
				);
		
		JOptionPane.showMessageDialog(null, "La solicitud se genero con exito");
		
		listaSolicitudes();
	}
	
	
}
