package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Concierto { 
	private int id;
	private String nombre;
	private String descripcion;
	private String direccion;
	private String fecha;
	private boolean cancelado;
	private LinkedList<Localidad> localidades;

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;

	

	public Concierto(int id, String nombre, String descripcion, String direccion, boolean cancelado, String fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.fecha = fecha;
		this.cancelado = cancelado;
	}
	public Concierto(int id, String nombre, String descripcion, String direccion, boolean cancelado, String fecha, LinkedList<Localidad> localidades) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.fecha = fecha;
		this.cancelado = cancelado;
		this.localidades = localidades;
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

	public LinkedList<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(LinkedList<Localidad> localidades) {
		this.localidades = localidades;
	}

	public int traerIdConcierto(){
		String sql ="SELECT max(id) FROM `concierto`";
		int id = 0;
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
			id = 1 + result.getInt(1);
			}

			    return id;
			    
			}catch(Exception excepcion){
				System.out.println(excepcion.getMessage());
				return 0;
			}
	}
	
	
	public int guardarConcierto() {
		
		int id = this.traerIdConcierto();
		
		if(id <= 0) {
			return 0;
		}else {
			String sql ="INSERT INTO `concierto`(`id`,`nombre`, `descripcion`, `direccion`, `fecha`, `cancelado`) VALUES (?,?,?,?,?,?)";
		
			try {
				
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, this.getNombre());
				stmt.setString(3, this.getDescripcion());
				stmt.setString(4, this.getDireccion());
				stmt.setString(5, this.getFecha());
				stmt.setBoolean(6, this.isCancelado());
				stmt.executeUpdate();
				conexion.close();
				return id;
				
			}catch(Exception excepcion){
				System.out.println(excepcion.getMessage());
				return 0;
			}
		
		}

	}
	
	public LinkedList<Concierto> traerConciertos() {
		LinkedList<Concierto> conciertos = new LinkedList<Concierto>();
		String sql ="SELECT * FROM `concierto`";
		//  Datos: int id, String nombre, String descripcion, String direccion, boolean cancelado, String fecha, int entDisponibles, LinkedList<Localidad> localidades
		String[] datos = new String[6]; 
		Localidad localidad = new Localidad(0, "", 0, 0,0);
		
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
				conciertos.add(new Concierto(Integer.parseInt(datos[0])
						,datos[1]
						,datos[2]
						,datos[3]
						,Boolean.parseBoolean(datos[4])
						,datos[5]
						,localidad.traerLocalidadesXConcierto(Integer.parseInt(datos[0]))));
			
				
				 
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
	
	public boolean modificarConcierto() {
		
		String sql ="UPDATE `concierto` SET `nombre`=?,`descripcion`=?,`direccion`=?,`fecha`=? WHERE id = ?";
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setString(1, this.nombre);
			stmt.setString(2, this.descripcion);
			stmt.setString(3, this.direccion);
			stmt.setString(4, this.fecha);
			stmt.setInt(5, this.id);
			stmt.executeUpdate();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
	}
	
	public boolean cancelarConcierto(int id) {
		String sql ="UPDATE `concierto` SET `cancelado`=1 WHERE id = ?";
		
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
