package Negocio;

import java.util.LinkedList;

import Datos.Concierto;
import Datos.Empleado;
import Datos.Localidad;

public class Verifica {
	Empleado nuevoempleado = new Empleado(null, null, null, null, null, null);
	Concierto nuevoconcierto = new Concierto(0,"","","",false,"");
	Localidad nuevalocalidad = new Localidad(0,"", 0, 0,0);
	
	public LinkedList<Concierto> verificaListaConciertos(){
		
		LinkedList<Concierto> conciertos = nuevoconcierto.traerConciertos();
		
		return conciertos;
	}
	
	public int validarConcierto(String nombre,String descripcion, String direccion, String fecha) {
	
	nuevoconcierto.setNombre(nombre);
	nuevoconcierto.setDescripcion(descripcion);
	nuevoconcierto.setDireccion(direccion);
	nuevoconcierto.setFecha(fecha);
	
	return nuevoconcierto.guardarConcierto();

	}
	
	public boolean eliminarConcierto(int id) {

			return nuevoconcierto.eliminarConcierto(id);
	}
	
	public boolean editarConcierto(int id) {
		if(id>0) {
			return nuevoconcierto.editarConcierto(id);
		}else {
			return false;
		}
	}
	
	//public 
	
   public LinkedList<Localidad> verificaListaLocalidades(){
		
		LinkedList<Localidad> localidades = nuevalocalidad.traerLocalidades();
		
		return localidades;
	}
	
	public boolean validarLocalidad(String nombre,int cupos, double precio, int idConcierto) {
	
	nuevalocalidad.setNombre(nombre);
	nuevalocalidad.setCuposTotal(cupos);
	nuevalocalidad.setPrecio(precio);
	
	return nuevalocalidad.guardarLocalidad(idConcierto);

	}
	
	public boolean eliminarLocalidad(int id) {

			return nuevoconcierto.eliminarConcierto(id);
	}
	
	public boolean editarLocalidad(int id) {
		if(id>0) {
			return nuevoconcierto.editarConcierto(id);
		}else {
			return false;
		}
	}
	
	public String verificaIngresarEmpleado(String dni, String password){
		
		return nuevoempleado.ingresar(dni,password); 
	}
}
