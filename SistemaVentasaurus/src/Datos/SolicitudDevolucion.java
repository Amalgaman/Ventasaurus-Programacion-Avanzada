package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SolicitudDevolucion {

	private int id;
	private String estado;
	private double dinero;
	private String fecha;
	private LinkedList<Entrada> lista;
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	public SolicitudDevolucion(int id, String estado, double dinero, String fecha, LinkedList<Entrada> lista) {
		super();
		this.id = id;
		this.estado = estado;
		this.dinero = dinero;
		this.fecha = fecha;
		this.lista = lista;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public LinkedList<Entrada> getLista() {
		return lista;
	}
	public void setLista(LinkedList<Entrada> lista) {
		this.lista = lista;
	}
	
	
	public int contarSolicitudes(){
		String sql ="SELECT max(id) FROM `devolucion`";
		int id = 0;
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
			id = 1 + result.getInt(1);
			}
			    conexion.close();
			    return id;
			    
			}catch(Exception excepcion){
				System.out.println(excepcion.getMessage());
				return 0;
			}
	}
	
	public int guardarSolicitud() {
		
		int id = this.contarSolicitudes();
		
		if(id <= 0) {
			return 0;
		}else {
			
			for (Entrada entrada : lista) {
				
			}
			
			String sql ="INSERT INTO `devolucion`(`id`,`estado`, `creacion`, `id_cliente`) VALUES (?,?,?,?)";
		
			try {
				
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, this.getEstado());
				stmt.setString(3, this.getFecha());
				stmt.setInt(4, this.g);
				stmt.executeUpdate();
				conexion.close();
				return id;
				
			}catch(Exception excepcion){
				System.out.println(excepcion.getMessage());
				return 0;
			}
		
		}

	}
	
	public LinkedList<SolicitudDevolucion> traerSolicitudes() {
		LinkedList<SolicitudDevolucion> solicitudes = new LinkedList<SolicitudDevolucion>();
		LinkedList<Entrada> entradas = new LinkedList<Entrada>();
		String sql ="SELECT * FROM `vw_devoluciones`";
		//  Datos: 
		String[] datos = new String[8]; 
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[0]= result.getString(1); //id
				datos[1]= result.getString(2); //estado
				datos[2]= result.getString(3); //creacion
				datos[3]= result.getString(4); //id cliente
				datos[4]= result.getString(5); //dni
				datos[5]= result.getString(6); //total
				
				sql ="SELECT entrada.id 'id_entrada', localidad.precio 'precio' FROM detalle_devolucion INNER JOIN devolucion on devolucion.id=detalle_devolucion.id_devolucion INNER JOIN entrada on entrada.id=detalle_devolucion.id_entrada INNER JOIN localidad on localidad.id=entrada.id_localidad WHERE devolucion.id='?'";
				
				//int id, String estado, double dinero, String fecha, LinkedList<Entrada> lista
				solicitudes.add(new SolicitudDevolucion(Integer.parseInt(datos[0]),datos[1],Double.parseDouble(datos[5]),datos[2],new LinkedList<Entrada>()));
				 
			}
			
			return solicitudes;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		}
		
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(datos[0]));
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				datos[6]= result.getString(1); //id entrada
				datos[7]= result.getString(2); //precio
				
				LinkedList<Entrada>
				//int id, String estado, double dinero, String fecha, LinkedList<Entrada> lista
				solicitudes.add(new SolicitudDevolucion(Integer.parseInt(datos[0]),datos[1],Double.parseDouble(datos[5]),datos[2],new LinkedList<Entrada>()));
				 
			}
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
	}
	
}
