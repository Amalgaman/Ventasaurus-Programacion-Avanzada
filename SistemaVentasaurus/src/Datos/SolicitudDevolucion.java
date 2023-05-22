package Datos;

import java.util.LinkedList;

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
	
}
