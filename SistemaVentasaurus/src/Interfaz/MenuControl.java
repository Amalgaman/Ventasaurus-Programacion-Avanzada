package Interfaz;

import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import Datos.Comprobante;
import Datos.Concierto;
import Datos.Conexion;
import Negocio.Verifica;

public class MenuControl {
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	static Verifica verifica = new Verifica();
	
	
	public void principal() {
		String[] opciones = {"Elegir Concierto","Salir"};
		int eleccion=JOptionPane.showOptionDialog(null, "Seleccione una opcion", "Ventasaurus - Control", 0, 0, null, opciones, 0);
		if (eleccion==0) {
			listaConciertos();
		} 
	}
	
	public void listaConciertos() {
		String opConcierto = " ";
		LinkedList<Concierto> listaTraida = verifica.verificaListaConciertos();
		
		String[] conciertoLista = new String[listaTraida.size()];
		for (Concierto concierto : listaTraida) {
			conciertoLista[listaTraida.indexOf(concierto)] = concierto.getNombre();
		}
		
		opConcierto = (String) JOptionPane.showInputDialog(
				null // para que se muestre centrado
				,"Selecciona un Concierto" // Mensaje de la ventana
				,"Ventasaurus - Seleccion de concierto" // Titulo de la ventana
				,JOptionPane.QUESTION_MESSAGE // Icono
				,null //null para icono defecto de la ventana
				,conciertoLista // el objeto
				,conciertoLista[0] // posicion del que va aparecer seleccionado
				);
		if (opConcierto!=null) {
			for (Concierto con : listaTraida) {
				if (con.getNombre().equals(opConcierto)) {
					control(con.getId());		
				}
			}
		}
	}
	
	public void control(int id) {
		
		LinkedList<Comprobante> entradas = new LinkedList<Comprobante>();
		
		String sql="SELECT entrada.id, cliente.dni FROM `entrada` INNER JOIN cliente on cliente.id=entrada.id_cliente WHERE entrada.id NOT in (SELECT DISTINCT id_entrada FROM detalle_devolucion WHERE id_devolucion in (SELECT devolucion.id FROM devolucion WHERE devolucion.estado='Aprobada')) AND entrada.id_localidad in (SELECT localidad.id FROM localidad WHERE id_concierto=?)AND entrada.c_devolucion!=-1;";
		try {
		String datos[]= new String[2];
		stmt = (PreparedStatement) conexion.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		while(result.next()) {
			datos[0]= result.getString(1); //id
			datos[1]= result.getString(2); //dni
			entradas.add(new Comprobante(Integer.parseInt(datos[0]),datos[1]));
		}
		//conexion.close();
		String dni= JOptionPane.showInputDialog("Ingresar dni del cliente");
		if (dni==null) {
			listaConciertos();
		} else {
			int flag=0;
			for (Comprobante comprobante : entradas) {
				System.out.println("Cdni: "+comprobante.getDnicliente());
				if (comprobante.getDnicliente().equals(dni)) {
					flag++;
				}
			}
			if (flag>0) { 
				JOptionPane.showMessageDialog(null, "Entrada validada para "+flag+" personas");
				validarEntrada(dni,id);
			}else{
				JOptionPane.showMessageDialog(null, "No hay entradas para ese dni en este concierto");
			}
			control(id);
		}
		
		} catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
		}
		
		
	}
	
	public void validarEntrada (String dni, int id) {
		
		String sql="UPDATE `entrada` SET `c_devolucion` = '-1' WHERE id not in (SELECT id_entrada from detalle_devolucion WHERE id_devolucion in (SELECT devolucion.id FROM devolucion WHERE devolucion.estado='Aprobada')) AND id_cliente IN (SELECT id FROM cliente WHERE dni=?)AND id_localidad in (SELECT localidad.id FROM localidad WHERE id_concierto=?);";
		
		try {
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setString(1, dni);
			stmt.setInt(2, id);
			stmt.executeUpdate();
		}catch(Exception excepcion){
			System.out.println("error "+excepcion.getMessage());
		}
	}
	
}