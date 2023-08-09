package Datos;

public class Comprobante {

	
	private int identrada;
	private String dnicliente;
	
	public Comprobante(int identrada, String dnicliente) {
		super();
		this.identrada = identrada;
		this.dnicliente = dnicliente;
	}

	public int getIdentrada() {
		return identrada;
	}

	public void setIdentrada(int identrada) {
		this.identrada = identrada;
	}

	public String getDnicliente() {
		return dnicliente;
	}

	public void setDnicliente(String dnicliente) {
		this.dnicliente = dnicliente;
	}

	@Override
	public String toString() {
		return "Comprobante [identrada=" + identrada + ", dnicliente=" + dnicliente + "]";
	}
	
	
	
	
}
