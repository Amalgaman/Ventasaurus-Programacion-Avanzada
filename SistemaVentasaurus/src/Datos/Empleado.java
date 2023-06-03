package Datos;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Empleado {
	
	private String dni;
	private String nombre;
	private String apellido;
	private String sucursal;
	private String password;
	private String rol;
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;

	public Empleado(String dni, String nombre, String apellido, String sucursal, String password, String rol) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sucursal = sucursal;
		this.password = password;
		this.rol = rol;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String ingresar(String dni,String password) {
		
		String sql ="SELECT rol FROM `empleado` where dni=? AND password=?";
			
			try {
				
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, Integer.parseInt(dni));
				stmt.setString(2, password);
				ResultSet result = stmt.executeQuery();
				
				if(!result.next()) {
					return "incorrecto";
				}else {
					this.setRol(result.getString(1));
				
				return this.getRol();
				}
				
			}catch(Exception excepcion){
				System.out.println(excepcion.getMessage());
				return "error";
			}

    }
}