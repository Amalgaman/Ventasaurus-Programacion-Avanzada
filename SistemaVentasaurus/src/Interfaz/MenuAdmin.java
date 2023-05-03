package Interfaz;

import javax.swing.JOptionPane;

public class MenuAdmin {
	
	public static void principal() {
		//Variables inicializadas
		int op = 0;
		String[] opciones = {"Ver Conciertos","Ver Solicitudes de Devolucion","Salir"};
		
		do {
		op = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - Administracion", 0, 0, null, opciones, 0);
		
		switch(op) {
		case 0:
			listaConciertos();
			break;
		case 1:
			listaSolicitudes();
			break;
		default:
			break;	
		}
		}while(op<2);
		
	}
	
	public static void listaConciertos() {
		//Variables inicializadas
		int op = 0;
		String opConcierto = " ";
		String[] abmConcierto ={"Editar Informacion","Cancelar Concierto","Eliminar Concierto","Volver"};
		
		//lista de conciertos
		String[] opcionesConcierto ={"Crear Concierto","Los Redondos 03/05/2023","SodaStereo 08/05/2023","Las Pastillas del Abuelo 08/10/2023","Hermetica 12/05/2023","Fito Paez 13/07/2023","Leon Gieco 08/05/2023","Sumo 24/10/2023"};
		
		opConcierto = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona un Concierto" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,opcionesConcierto // el objeto
				,opcionesConcierto[0] // posicion del que va aparecer seleccionado
				);
		
		//Esto es para salir del menu sin que se rompa
		if(opConcierto == null) {
		  
		}else if (opConcierto.equals("Crear Concierto")) {
			altaConcierto();
		}else {
			op = JOptionPane.showOptionDialog(null, opConcierto
					+" \nLa aclamada banda hara su gira de despedida"
					+" \na lo grande, realizando un recorrido por sus"
					+" \ngrandes exitos ¿Que estas esperando? Saca tu"
					+" \nentrada."
					+" \nPrecios desde $1200", "Ventasaurus - Administracion", 0, 0, null, abmConcierto, 0);
			
			switch(op) {
			case 0:
				altaConcierto();
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Concierto cancelado con exito");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Concierto eliminado con exito");
				break;
			default:
				break;	
			}
		}
	}
	
	public static void altaConcierto(){
		//En el futuro podriamos armar un Formulario con JFrame, por ahora son JOptionPanel

		JOptionPane.showInputDialog("Introduzca nombre del concierto");
		JOptionPane.showInputDialog("Introduzca descripcion");
		JOptionPane.showInputDialog("Introduzca precio de entrada");
		JOptionPane.showInputDialog("Introduzca direccion del concierto");
		
		JOptionPane.showMessageDialog(null, "El concierto se genero con exito");
		
		listaConciertos();
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
			JOptionPane.showOptionDialog(null, opSolicitud
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
