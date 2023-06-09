package Datos;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class SolicitudDevolucion {

	private int id;
	private String estado;
	private double dinero;
	private String fecha;
	private LinkedList<Entrada> lista;
	
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
	
	public int traerIdSolicitud(){
		String sql ="SELECT max(id) FROM `devolucion`";
		Conexion con = new Conexion();
		
		Connection conexion = (Connection) con.conectar();
		
		PreparedStatement stmt;
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
	Conexion con = new Conexion();
	
	Connection conexion = (Connection) con.conectar();
	
	PreparedStatement stmt;
	int id = this.traerIdSolicitud();
	String fecha =JOptionPane.showInputDialog(null,"Fecha de devolucion del ticket");
	LocalDate fechaTicket = LocalDate.parse(fecha);
		if(id <= 0) {
			return 0;
		}else {
			String sql ="INSERT INTO `devolucion`(`id`, `creacion`, `id_cliente`) VALUES (?,?,?)";
		
			try {
				
				stmt = (PreparedStatement) conexion.prepareStatement(sql);
				stmt.setInt(1, id);
				stmt.setString(2, fecha);
				stmt.setLong(3, 1);
				stmt.executeUpdate();
			//	conexion.close();
				return id;
				
			}catch(Exception excepcion){
				System.out.println(excepcion.getMessage());
				return 0;
			}
		
		}

	}
	
}
