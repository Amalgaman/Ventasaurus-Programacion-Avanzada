package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Localidad {
	
	private int id;
	private String nombre;
	private int cuposTotal;
	private int cuposDisponibles;
	private double precio;
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	public Localidad(int id, String nombre, int cuposTotal, int cuposDisponibles,double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cuposTotal = cuposTotal;
		this.cuposDisponibles = cuposDisponibles;
		this.precio = precio;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCuposTotal() {
		return cuposTotal;
	}

	public void setCuposTotal(int cuposTotal) {
		this.cuposTotal = cuposTotal;
	}

	public int getCuposDisponibles() {
		return cuposDisponibles;
	}

	public void setCuposDisponibles(int cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public boolean guardarLocalidad(int idConcierto) {
		
		String sql ="INSERT INTO `localidad`(`nombre`, `precio`, `cupos`, `id_concierto`) VALUES (?,?,?,?)";
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());
			stmt.setDouble(2, this.getPrecio());
			stmt.setInt(3, this.getCuposTotal());
			stmt.setInt(4, idConcierto);
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
		
	}
	
	public LinkedList<Localidad> traerLocalidades() {
		LinkedList<Localidad> localidades = new LinkedList<Localidad>();
		String sql ="SELECT * FROM `localidad`";
		//  Datos: 
		String[] datos = new String[6]; 
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[0]= result.getString(1); //id
				datos[1]= result.getString(2); //precio
				datos[2]= result.getString(3); //nombre
				datos[3]= result.getString(4); //cupos

				localidades.add(new Localidad(Integer.parseInt(datos[0]),datos[2],Integer.parseInt(datos[3]),0,Double.parseDouble(datos[2])));
				 
			}
			
			return localidades;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		}
	
	}
	public boolean eliminarLocalidad(int id) {
		
		String sql ="DELETE FROM `concierto` WHERE id = ?";
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
	}
	
	public boolean editarLocalidad(int id) {
		
		String sql ="UPDATE `persona` SET `nombre`='?',`apellido`='?',`dni`='?' WHERE id = ?";
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
	}
	
}
