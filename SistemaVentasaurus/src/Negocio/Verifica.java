package Negocio;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Datos.Concierto;
import Datos.Empleado;
import Datos.Localidad;

public class Verifica {
	Empleado nuevoempleado = new Empleado(null, null, null, null, null, null);
	Concierto nuevoconcierto = new Concierto(0, "", "", "", false, "");
	Localidad nuevalocalidad = new Localidad(0, "", 0, 0, 0);

	public LinkedList<Concierto> verificaListaConciertos() {

		LinkedList<Concierto> conciertos = nuevoconcierto.traerConciertos();

		return conciertos;
	}

	public int validarConcierto(String nombre, String descripcion, String direccion, String fecha) {

		nuevoconcierto.setNombre(nombre);
		nuevoconcierto.setDescripcion(descripcion);
		nuevoconcierto.setDireccion(direccion);
		nuevoconcierto.setFecha(fecha);

		return nuevoconcierto.guardarConcierto();

	}

	public boolean eliminarConcierto(int id) {

		return nuevoconcierto.eliminarConcierto(id);
	}

	public boolean editarConcierto(int id) {
		if (id > 0) {
			return nuevoconcierto.editarConcierto(id);
		} else {
			return false;
		}
	}

	public boolean CantEntradas(int cantEntradas, String nombreConcierto, int precioEntrada) {
		ImageIcon icon = new ImageIcon("src/img/pago.png");
		if (cantEntradas >= 1 && precioEntrada == 35000 || cantEntradas >= 1 && precioEntrada == 20000
				|| cantEntradas >= 1 && precioEntrada == 50000) {
			JOptionPane.showMessageDialog(null,
					"Compraste " + cantEntradas + " entrada/s para : " + nombreConcierto
							+ "\nPrecio final de la compra : $'" + precioEntrada * cantEntradas + "'",
					"Gracias por comprar en Ventasaurus", 0, icon);
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "No compraste entradas");
			return false;
		}
	}

	public void verificarDni(int dni) {
		ImageIcon icon = new ImageIcon("src/img/dni.jpg");
		// ver si el (String) rompe algo
		do {
			dni = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese su DNI", "Dni",
					JOptionPane.PLAIN_MESSAGE, icon, null, ""));
		} while (dni <= 11111111 || dni >= 99999999);

	}

	public LinkedList<Localidad> verificaListaLocalidades() {

		LinkedList<Localidad> localidades = nuevalocalidad.traerLocalidades();

		return localidades;
	}

	public boolean validarLocalidad(String nombre, int cupos, double precio, int idConcierto) {

		nuevalocalidad.setNombre(nombre);
		nuevalocalidad.setCuposTotal(cupos);
		nuevalocalidad.setPrecio(precio);

		return nuevalocalidad.guardarLocalidad(idConcierto);

	}

	public boolean eliminarLocalidad(int id) {

		return nuevoconcierto.eliminarConcierto(id);
	}

	public boolean editarLocalidad(int id) {
		if (id > 0) {
			return nuevoconcierto.editarConcierto(id);
		} else {
			return false;
		}
	}

	public String verificaIngresarEmpleado(String dni, String password) {

		return nuevoempleado.ingresar(dni, password);
	}
}
