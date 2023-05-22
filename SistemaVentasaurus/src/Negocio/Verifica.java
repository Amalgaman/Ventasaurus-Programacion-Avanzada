package Negocio;

import java.util.LinkedList;

import Datos.Concierto;

public class Verifica {
	Concierto nuevoconcierto = new Concierto(0,"","","","",0);
	
	public LinkedList<Concierto> verificaListaConciertos(){
		
		LinkedList<Concierto> conciertos = nuevoconcierto.traerConciertos();
		
		return conciertos;
	}
	
	public boolean validarConcierto(String nombre,String descripcion, String direccion, String fecha, int entDisponibles) {
	
	nuevoconcierto.setNombre(nombre);
	nuevoconcierto.setDescripcion(descripcion);
	nuevoconcierto.setDireccion(direccion);
	nuevoconcierto.setFecha(fecha);
	nuevoconcierto.setEntDisponibles(entDisponibles);
	
	return nuevoconcierto.guardarConcierto();

	}
	
	public boolean eliminarConcierto(int id) {

			return nuevoconcierto.eliminarConcierto(id);
	}
	
	public boolean Editar(int id) {
		if(id>0) {
			return nuevoconcierto.editarConcierto(id);
		}else {
			return false;
		}
	}
}
