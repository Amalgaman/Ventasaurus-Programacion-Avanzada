package Datos;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Entrada {
	private int id;
	private String localidad;
	private String precio;
	private String concierto;
	private String codigoDevolucion;
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	public Entrada(int id, String localidad, String precio, String concierto, String codigoDevolucion) {
		super();
		this.id = id;
		this.localidad = localidad;
		this.precio = precio;
		this.concierto = concierto;
		this.codigoDevolucion = codigoDevolucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getConcierto() {
		return concierto;
	}

	public void setConcierto(String concierto) {
		this.concierto = concierto;
	}

	public String getCodigoDevolucion() {
		return codigoDevolucion;
	}

	public void setCodigoDevolucion(String codigoDevolucion) {
		this.codigoDevolucion = codigoDevolucion;
	}
	
	
	@Override
	public String toString() {
		return id+"["+concierto+"] [" + localidad + "] [" + precio + "]";
	}

	public int contarEntradas(int idLocalidad) {
		String sql ="SELECT COUNT(*) FROM `entrada` WHERE id_localidad = ?";
		int entradas = 0;
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, idLocalidad);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				 entradas =Integer.parseInt(result.getString(1)) ;
			}
			
			return entradas;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return -1;
		}
			
	}
}
