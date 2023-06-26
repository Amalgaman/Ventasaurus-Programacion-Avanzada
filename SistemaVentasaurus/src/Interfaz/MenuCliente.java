package Interfaz;


import java.util.LinkedList;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.Conexion;

import Datos.Entrada;
import Jframes.DevolverEntradas;

public class MenuCliente {

	
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	/*public static void clienteVerMenu() {
		String[] opciones = { "Ver Conciertos", "Comprar entradas", "Solicitar devolucion", "Salir" };
		int xd;
		do {
			xd = JOptionPane.showOptionDialog(null, "Seleccione una de las opciones", "Ventasaurus - Bienvenido !", 0,
					0, null, opciones, 0);
			switch (xd) {
			case 0:
				listaConciertosCliente();
				break;
			case 1:
				// CompraryPagar();
				break;
			case 2:
				SolicitudDeDevolucion();
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Abuenoadiosmaster");
				break;
			default:
				break;
			}
		} while (xd != 3);// repetir hasta que le de salir
		// listaConciertos();
	}
*/
	public  void listaConciertosCliente() {
		String opConcierto;
		int op;
		String[] opcClienteConc = { "Comprar entradas", "Volver" };
		String[] opcionesConcierto = { "Los Redondos 03/05/2023", "SodaStereo 08/05/2023",
				"Las Pastillas del Abuelo 08/10/2023", "Hermetica 12/05/2023", "Fito Paez 13/07/2023",
				"Leon Gieco 08/05/2023", "Sumo 24/10/2023" };

		opConcierto = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
				, "Selecciona un Concierto" // Mensaje de la ventana
				, "Ventasaurus - Conciertos" // Titulo de la ventana
				, JOptionPane.QUESTION_MESSAGE // Icono
				, null // null para icono defecto de la ventana
				, opcionesConcierto // el objeto
				, opcionesConcierto[0] // posicion del que va aparecer seleccionado
		);
		if (opConcierto!=null) {
		op = JOptionPane.showOptionDialog(null,
				opConcierto + " \nLa aclamada banda hara su gira de despedida"
						+ " \na lo grande, realizando un recorrido por sus"
						+ " \ngrandes exitos ¿Que estas esperando? Saca tu" + " \nentrada." + " \nPrecios desde $1200",
				"Ventasaurus - Administracion", 0, 0, null, opcClienteConc, 0);

		switch (op) {
		case 0:
			CompraryPagar(opConcierto);
			break;
		case 1:
			listaConciertosCliente();
			break;
		default:
			break;
		}
		}
	}

	public void SolicitudDeDevolucion() {
		
		
		//String sql="SELECT id FROM cliente WHERE dni=?";
		String aux=JOptionPane.showInputDialog("Ingresar dni");
		if (aux!=null && !aux.equals("")) {
			int dni=Integer.parseInt(aux);
			DevolverEntradas.run(dni);
		}else {
			MenuPrincipal.principal();
		}
		/*
		int idcliente=-1;
		
		sql ="SELECT entrada.id, c_devolucion, concierto.nombre, localidad.nombre, localidad.precio  FROM `entrada` INNER JOIN localidad on localidad.id = entrada.id_localidad INNER JOIN concierto on localidad.id_concierto= concierto.id WHERE entrada.id_cliente=(SELECT id FROM cliente WHERE dni=?) AND c_devolucion >0 AND entrada.id not in (SELECT DISTINCT id_entrada FROM detalle_devolucion WHERE id_devolucion not in (SELECT devolucion.id FROM devolucion WHERE devolucion.estado='Aprobada'));";
		String[] datos = new String[5]; 
		LinkedList<Entrada> entradas =new LinkedList<Entrada>();
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, dni);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				datos[0]= result.getString(1); //id
				datos[1]= result.getString(2); //codigo
				datos[2]= result.getString(3); //nombre conc
				datos[3]= result.getString(4); //localidad
				datos[4]= result.getString(5); //precio
				entradas.add(new Entrada(Integer.parseInt(datos[0]),datos[3],datos[4],datos[2],datos[1]));
			}
				//Ventana.main(entradas);
				String[] opcionesDev= new String[entradas.size()];
				int i=0;
				for (Entrada entrada : entradas) {
					opcionesDev[i]=entrada.toString();
					i++;
				}
				//DevolverEntradas panel= new DevolverEntradas(dni);
				String opDevolucion = (String) JOptionPane.showInputDialog(null // para que se muestre centrado
						, "Selecciona una entrada para generar una devolucion" // Mensaje de la ventana
						, "Ventasaurus - Devoluciones" // Titulo de la ventana
						, JOptionPane.QUESTION_MESSAGE // Icono
						, null // null para icono defecto de la ventana
						, opcionesDev // el objeto
						, opcionesDev[0] // posicion del que va aparecer seleccionado
				);
				for (Entrada entrada : entradas) {
					if (entrada.toString().equals(opDevolucion)) {
						String codigo=JOptionPane.showInputDialog("Ingresar codigo de devolución");
						if (entrada.getCodigoDevolucion().equals(codigo)) {
							int devolucionid=-1;
							sql ="INSERT INTO `devolucion` (`estado`, `creacion`, `id_cliente`) VALUES ('pendiente', ?, ?);";
							
							try {
								LocalDate fecha = LocalDate.now();
								
								stmt = (PreparedStatement) conexion.prepareStatement(sql);
								stmt.setInt(2, idcliente);
								stmt.setObject(1,Date.valueOf(fecha));
								stmt.executeUpdate();
								
							}catch(Exception excepcion){
								System.out.println(excepcion.getMessage());
								
							}
							sql ="SELECT MAX(id) FROM devolucion;";
							try {
								
								stmt = (PreparedStatement) conexion.prepareStatement(sql);
								result = stmt.executeQuery();
								while(result.next()) {
									devolucionid=Integer.parseInt(result.getString(1)); //id
								}
							}catch(Exception excepcion){
								System.out.println(excepcion.getMessage());
							}
							sql ="INSERT INTO `detalle_devolucion` (`id_devolucion`, `id_entrada`) VALUES (?, ?);";
							
							try {
								
								stmt = (PreparedStatement) conexion.prepareStatement(sql);
								stmt.setInt(1, devolucionid);
								stmt.setInt(2, entrada.getId());
								stmt.executeUpdate();
							}catch(Exception excepcion){
								System.out.println(excepcion.getMessage());
								
							} //Borrar de esta liena al if, para implementar jframe ocn la funcion guardar que esta en la clase solicitud
							JOptionPane.showMessageDialog(null, "Solicitud recibida exitosamente "+ opDevolucion
									+ "\nSe le mandara un mail cuando el admin lo apruebe");
						} else {
							JOptionPane.showMessageDialog(null, "Codigo incorrecto");
						}
						
					}
				}
				
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
		}
		
		//MenuPrincipal.principal();
*/
	}

	public  void CompraryPagar(String nombreConcierto) {// se entrga los tickets al cliente
		int cantEntradas;
		do {
			cantEntradas = Integer
					.parseInt(JOptionPane.showInputDialog(null, "Ingresar cantidad de tickets a comprar"));
		} while (cantEntradas <= 0);
		JOptionPane.showMessageDialog(null, "Compraste " + cantEntradas + " entradas para : " + nombreConcierto);
		listaConciertosCliente();
	}

}
