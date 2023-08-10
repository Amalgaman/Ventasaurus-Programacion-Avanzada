package Interfaz;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Datos.Concierto;
import Datos.Conexion;
import Datos.Entrada;
import Datos.Localidad;
import Datos.SolicitudDevolucion;
//import Jframe.DevolverEntradas;
//import Jframe.GenerarSolicitudAdmin;
//import Jframe.VentanaError;
import Negocio.Verifica;

public class MenuAdmin {
	
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	static Verifica verifica = new Verifica();
	
	public static void principal() {
		//Variables inicializadas
		int op = 0;
		String[] opciones = {"Ver Conciertos","Ver Solicitudes de Devolucion","Cerrar Sesion"};
		
		do {
		
		op = JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - Administracion", 0, 0, null, opciones, 0);
		
		switch(op) {
		case 0:
			op = listaConciertos();
			break;
		case 1:
			op = listaSolicitudes();
			break;
		case 2:
			JMenuPrincipal frame = new JMenuPrincipal();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            break;
		default:
			JMenuPrincipal frames = new JMenuPrincipal();
            frames.setLocationRelativeTo(null);
            frames.setVisible(true);
			break;	
		}

		}while(op<2 && op>-1);
	}
	
	public static int listaConciertos() {
		
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
//			JMenuPrincipal frame = new JMenuPrincipal();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
		}else {
			
		 if (opConcierto.equals("Crear nuevo Concierto")) {
			 adminAltaConcierto.run();
			 return 3;
		}else {
			for (Concierto concierto : listaTraida) {
				if (opConcierto.equals(concierto.getNombre())) {
					cElegido = listaTraida.indexOf(concierto);
				}
			}
			
			adminConcierto.run(listaTraida.get(cElegido));
			return 3;
		}
	} return 0;
	}
	
	public static int listaSolicitudes()  {
		//Variables inicializadas
	    int op = 0;
		String opSolicitud = " ";
		String[] abmSolicitud ={"Autorizar Devolucion","Rechazar Solicitud","Volver"};
		
		//lista de solicitudes
		SolicitudDevolucion aux= new SolicitudDevolucion(0,"","",0,"",0,0,null);
		LinkedList<SolicitudDevolucion> solicitudes = aux.traerSolicitudes();
		
		if (solicitudes == null) {
			String[] opcionesSolicitud= {"Generar solicitud"};
			opSolicitud = (String) JOptionPane.showInputDialog(
					null // para que se muestre centrado
					,"Selecciona una Solicitud" // Mensaje de la ventana
					,"Ventasaurus - Administracion" // Titulo de la ventana
					,JOptionPane.QUESTION_MESSAGE // Icono
					,null //null para icono defecto de la ventana
					,opcionesSolicitud // el objeto
					,opcionesSolicitud[0] // posicion del que va aparecer seleccionado
					);
		} else {
			
		String[] opcionesSolicitud = new String[solicitudes.size()+1];
		opcionesSolicitud[0]="Generar solicitud";
		for (int i=1; i<=solicitudes.size();i++) {
			opcionesSolicitud[i]=solicitudes.get(i-1).toString();
		}
		
		opSolicitud = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona una Solicitud" // Mensaje de la ventana
				,"Ventasaurus - Administracion" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,opcionesSolicitud // el objeto
				,opcionesSolicitud[0] // posicion del que va aparecer seleccionado
				);
		
		}
		//Esto es para salir del menu sin que se rompa
		if(opSolicitud == null) {
			return 1;
		}else if (opSolicitud.equals("Generar solicitud")) {

			generarSolicitud();
			return 3;
		}else {
			for (SolicitudDevolucion solicitud : solicitudes) {
				if (solicitud.toString().equals(opSolicitud)) {
					
					op = JOptionPane.showOptionDialog(null, opSolicitud
							+ solicitud.detalle(), "Ventasaurus - Administracion", 0, 0, null, abmSolicitud, 0);
					switch(op) {
					case 0:
						if (solicitud.aprobar(true)) {
							JOptionPane.showMessageDialog(null, "Devolucion autorizada con exito. "
									+ "Se le envio un correo electronico al cliente");
						} else {
							JOptionPane.showMessageDialog(null, "Ocurrió un problema y no se puede aprobar en este momento");
						}
						break;
					case 1:
						if (solicitud.aprobar(false)) {
						JOptionPane.showMessageDialog(null, "Solicitud rechazada con exito");
						} else {
							JOptionPane.showMessageDialog(null, "Ocurrió un problema y no se puede rechazar en este momento");
						}
						break;
					default:
						break;	
					}
					
				}
			}
			return 3;
		
		}
	}
	
	
	
public static void generarSolicitud() {
		
	String aux=JOptionPane.showInputDialog("Ingresar dni");
	if (aux!=null && !aux.equals("")) {
		int dni=Integer.parseInt(aux);
		GenerarSolicitudAdmin.run(dni,false);
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
		

	}
	
	
	
	
}

