package Datos;

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
}
