package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Concierto {
	private int id;
	private String nombre;
	private String descripcion;
	private String direccion;
	private String fecha;
	private int entDisponibles;

	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;

	public Concierto(int id,String nombre, String descripcion, String direccion, String fecha, int entDisponibles) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.fecha = fecha;
		this.entDisponibles = entDisponibles;
	}

	public String getNombre() {
		return nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getEntDisponibles() {
		return entDisponibles;
	}

	public void setEntDisponibles(int entDisponibles) {
		this.entDisponibles = entDisponibles;
	}

	public boolean guardarConcierto() {
		
		String sql ="INSERT INTO `concierto`(`nombre`, `descripcion`, `direccion`, `fecha`, `entradas_disponibles`) VALUES (?,?,?,?,?)";
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());
			stmt.setString(2, this.getDescripcion());
			stmt.setString(3, this.getDireccion());
			stmt.setString(4, this.getFecha());
			stmt.setInt(5, this.getEntDisponibles());
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
		
	}
	
	public LinkedList<Concierto> traerConciertos() {
		LinkedList<Concierto> conciertos = new LinkedList<Concierto>();
		String sql ="SELECT * FROM `concierto`";
		//  Datos: (int id, String nombre, String descripcion, String direccion, String fecha, int entDisponibles)
		String[] datos = new String[6]; 
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[0]= result.getString(1);
				datos[1]= result.getString(2);
				datos[2]= result.getString(3);
				datos[3]= result.getString(4);
				datos[4]= result.getString(5);
				datos[5]= result.getString(6);
				conciertos.add(new Concierto(Integer.parseInt(datos[0]),datos[1],datos[2],datos[3],datos[4],Integer.parseInt(datos[5])));
				 
			}
			
			return conciertos;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		}
	
	}
	public boolean eliminarConcierto(int id) {
		
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
	
	public boolean editarConcierto(int id) {
		
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
