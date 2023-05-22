package Datos;

public class Entrada {
	private int id;
	private String tipo;
	private String precio;
	private String concierto;
	private String codigoDevolucion;
	
	public Entrada(int id, String tipo, String precio, String concierto, String codigoDevolucion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.precio = precio;
		this.concierto = concierto;
		this.codigoDevolucion = codigoDevolucion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getConcierto() {
		return concierto;
	}

	public void setConcierto(String concierto) {
		this.concierto = concierto;
	}

	public String getCodigoDevolucion() {
		return codigoDevolucion;
	}

	public void setCodigoDevolucion(String codigoDevolucion) {
		this.codigoDevolucion = codigoDevolucion;
	}
	
}
