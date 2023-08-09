package Datos;

import java.sql.ResultSet;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SolicitudDevolucion {

	private int id;
	private String estado;
	private String fecha;
	private int idcliente;
	private String dni;
	private int cantidad;
	private double total;
	private LinkedList<Entrada> lista;
	
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	
	
	public SolicitudDevolucion(int id, String estado, String fecha, int idcliente, String dni, int cantidad,
			double total, LinkedList<Entrada> lista) {
		super();
		this.id = id;
		this.estado = estado;
		this.fecha = fecha;
		this.idcliente = idcliente;
		this.dni = dni;
		this.cantidad = cantidad;
		this.total = total;
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
		return total;
	}
	public void setDinero(double dinero) {
		this.total = dinero;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public LinkedList<Entrada> getLista() {
		return lista;
	}
	public void setLista(LinkedList<Entrada> lista) {
		this.lista = lista;
	}
	
	
	
	@Override
	public String toString() {
		
		return "SolicitudDevolucion [id=" + id + ", estado=" + estado + ", fecha=" + fecha + ", total=" + total
				+ "]" ;
	}
	
	public String detalle() {
		String entradas="\nDetalle:\n";
		for (Entrada entrada : lista) {
			entradas+=entrada.toString()+"\n";
		}
		return entradas;
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
				String sql ="INSERT INTO `detalle_devolucion` (`id_devolucion`, `id_entrada`) VALUES (?, ?);";
				
				try {
					
					stmt = (PreparedStatement) conexion.prepareStatement(sql);
					stmt.setInt(1, id);
					stmt.setInt(2, entrada.getId());
					//stmt.setInt(4, this.g);
					stmt.executeUpdate();
					conexion.close();
					return id;
					
				}catch(Exception excepcion){
					System.out.println(excepcion.getMessage());
				}
			}
			
			String sql ="INSERT INTO `devolucion`(`id`,`estado`, `creacion`, `id_cliente`) VALUES (?,?,?,?)";
		
			try {
				
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, this.getEstado());
				stmt.setString(3, this.getFecha());
				stmt.setInt(4, this.getIdcliente());
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
		//LinkedList<Entrada> entradas = new LinkedList<Entrada>();
		String sql ="SELECT * FROM vw_devoluciones WHERE id NOT in (SELECT detalle_devolucion.id_devolucion from detalle_devolucion WHERE detalle_devolucion.id_entrada in (SELECT entrada.id from entrada WHERE entrada.c_devolucion<0)) AND estado='pendiente' ORDER by creacion DESC;";
		//  Datos: 
		String[] datos = new String[11]; 
		
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
				
				//sql ="SELECT entrada.id 'id_entrada', localidad.precio 'precio' FROM detalle_devolucion INNER JOIN devolucion on devolucion.id=detalle_devolucion.id_devolucion INNER JOIN entrada on entrada.id=detalle_devolucion.id_entrada INNER JOIN localidad on localidad.id=entrada.id_localidad WHERE devolucion.id='?'";
				LinkedList<Entrada> entradas = new LinkedList<Entrada>();
				sql="SELECT entrada.id, localidad.nombre, localidad.precio, concierto.nombre, entrada.c_devolucion FROM `detalle_devolucion` INNER JOIN entrada on entrada.id=detalle_devolucion.id_entrada INNER JOIN localidad on entrada.id_localidad=localidad.id INNER JOIN concierto ON concierto.id=localidad.id_concierto WHERE detalle_devolucion.id_devolucion=?;";
				try {
					
					stmt = (PreparedStatement) conexion.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(datos[0]));
					ResultSet result2 = stmt.executeQuery();
					
					while(result2.next()) {
						datos[6]= result2.getString(1); //id
						datos[7]= result2.getString(2); //localidad
						datos[8]= result2.getString(3); //precio
						datos[9]= result2.getString(4); //concierto
						datos[10]= result2.getString(5); //codigo
						
						
						entradas.add(new Entrada(Integer.parseInt(datos[6]),datos[7],datos[8],datos[9],datos[10]));
					}
				}catch(Exception excepcion){
					System.out.println(excepcion.getMessage());
					return null;
				}
				
				solicitudes.add(new SolicitudDevolucion(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4], 1,Double.parseDouble(datos[5]),entradas));
				
			}
			conexion.close();
			return solicitudes;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		} 
		/*
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
	}*/
	
	}
	
	public LinkedList<SolicitudDevolucion> traerTodas(){
		LinkedList<SolicitudDevolucion> solicitudes = new LinkedList<SolicitudDevolucion>();
		//LinkedList<Entrada> entradas = new LinkedList<Entrada>();
		String sql ="SELECT * FROM vw_devoluciones WHERE id NOT in (SELECT detalle_devolucion.id_devolucion from detalle_devolucion WHERE detalle_devolucion.id_entrada in (SELECT entrada.id from entrada WHERE entrada.c_devolucion<0)) ORDER by creacion DESC;";
		//  Datos: 
		String[] datos = new String[11]; 
		
		try {
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				datos[0]= result.getString(1); //id
				datos[1]= result.getString(2); //estado
				datos[2]= result.getString(3); //creacion
				datos[3]= result.getString(4); //id cliente
				datos[4]= result.getString(5); //dni
				datos[5]= result.getString(7); //total
				
				//sql ="SELECT entrada.id 'id_entrada', localidad.precio 'precio' FROM detalle_devolucion INNER JOIN devolucion on devolucion.id=detalle_devolucion.id_devolucion INNER JOIN entrada on entrada.id=detalle_devolucion.id_entrada INNER JOIN localidad on localidad.id=entrada.id_localidad WHERE devolucion.id='?'";
				LinkedList<Entrada> entradas = new LinkedList<Entrada>();
				sql="SELECT entrada.id, localidad.nombre, localidad.precio, concierto.nombre, entrada.c_devolucion FROM `detalle_devolucion` INNER JOIN entrada on entrada.id=detalle_devolucion.id_entrada INNER JOIN localidad on entrada.id_localidad=localidad.id INNER JOIN concierto ON concierto.id=localidad.id_concierto WHERE detalle_devolucion.id_devolucion=?;";
				try {
					
					stmt = (PreparedStatement) conexion.prepareStatement(sql);
					stmt.setInt(1, Integer.parseInt(datos[0]));
					ResultSet result2 = stmt.executeQuery();
					
					while(result2.next()) {
						datos[6]= result2.getString(1); //id
						datos[7]= result2.getString(2); //localidad
						datos[8]= result2.getString(3); //precio
						datos[9]= result2.getString(4); //concierto
						datos[10]= result2.getString(5); //codigo
						
						
						entradas.add(new Entrada(Integer.parseInt(datos[6]),datos[7],datos[8],datos[9],datos[10]));
					}
				}catch(Exception excepcion){
					System.out.println(excepcion.getMessage());
					return null;
				}
				
				solicitudes.add(new SolicitudDevolucion(Integer.parseInt(datos[0]),datos[1],datos[2],Integer.parseInt(datos[3]), datos[4], 1,Double.parseDouble(datos[5]),entradas));
				
			}
			conexion.close();
			return solicitudes;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return null;
		} 
		/*
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
	}*/
	}
	
	public boolean aprobar (boolean op) {
		String sql ="UPDATE `devolucion` SET `estado` = ? WHERE `devolucion`.`id` = ?;";
		String estado="";
		if (op) {
			estado="Aprobada";
		} else {
			estado="Rechazada";
		}
		try {
			
			stmt = (PreparedStatement) conexion.prepareStatement(sql);
			stmt.setString(1, estado);
			stmt.setInt(2, this.getId());
			stmt.executeUpdate();
			conexion.close();
			return true;
			
		}catch(Exception excepcion){
			System.out.println(excepcion.getMessage());
			return false;
		}
		
	}
	
}
