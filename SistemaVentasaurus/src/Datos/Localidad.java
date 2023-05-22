package Datos;

public class Localidad {
	
	private String nombre;
	private int cupos;
	private double precio;
	
	public Localidad(String nombre, int cupos, double precio) {
		super();
		this.nombre = nombre;
		this.cupos = cupos;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
