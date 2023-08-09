package Negocio;

import java.util.LinkedList;
import java.util.regex.Pattern;

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

	public int validarConcierto(String nombre,String descripcion, String direccion, String fecha) {


        if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]", nombre)) {
               nuevoconcierto.setNombre(nombre);

               if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]", descripcion)) {
                   nuevoconcierto.setDescripcion(descripcion);

                    if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]*", direccion)) {
                        nuevoconcierto.setDireccion(direccion);

                        if(Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", fecha)) {
                            nuevoconcierto.setFecha(fecha);

                            System.out.println("Match found");

                            return nuevoconcierto.guardarConcierto();

                        }else {
                             System.out.println("Fecha Match not found");
                        }
                    }else {
                         System.out.println("Direccion Match not found");
                       }
               }else {
                     System.out.println("Descripcion Match not found");
               }
           } else {
             System.out.println("Nombre Match not found");
           }


   return 0;

   }
	
	public boolean validarModificacionConcierto(String nombre,String descripcion, String direccion, String fecha,int id) {

        if(id>0) {
            nuevoconcierto.setId(id);

            if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]", nombre)) {
                nuevoconcierto.setNombre(nombre);

                if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]", descripcion)) {
                    nuevoconcierto.setDescripcion(descripcion);

                     if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]*", direccion)) {
                         nuevoconcierto.setDireccion(direccion);

                         if(Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", fecha)) {
                             nuevoconcierto.setFecha(fecha);

                             System.out.println("Match found");

                             return nuevoconcierto.modificarConcierto();

                         }else {
                              System.out.println("Fecha Match not found");
                         }
                     }else {
                          System.out.println("Direccion Match not found");
                        }
                }else {
                      System.out.println("Descripcion Match not found");
                }
            } else {
              System.out.println("Nombre Match not found");
            }
        }

        return false;

    }

	public boolean eliminarConcierto(int id) {

		return nuevoconcierto.eliminarConcierto(id);
	}

	public boolean cancelarConcierto(int id) {

        return nuevoconcierto.cancelarConcierto(id);
    }
	

    public boolean validarLocalidad(String nombre,int cupos, double precio, int idConcierto) {

        if(idConcierto > 0) {
            nuevoconcierto.setNombre(nombre);

            if(Pattern.matches("[a-zA-Z-0-9 ,.¿?]*", nombre)) {
                nuevalocalidad.setNombre(nombre);
                    if(cupos>0) {
                        nuevalocalidad.setCuposTotal(cupos);
                                if(precio>0) {
                                    nuevalocalidad.setPrecio(precio);
                                    return nuevalocalidad.guardarLocalidad(idConcierto);
                                }else {
                                      System.out.println("Precio LocalidadMatch not found");
                                }
                    }else {
                          System.out.println("Cupos Localidad Match not found");
                    }
            }else {
                  System.out.println("Nombre Localidad Match not found");
            }


            }
        return false;
    }

    public boolean eliminarLocalidad(int id) {

            return nuevoconcierto.eliminarConcierto(id);
    }

	public boolean CantEntradas(int cantEntradas, Concierto Concierto, double precioEntrada,double precioUnitario) {
		ImageIcon icon = new ImageIcon("src/img/pago.png");
		if (cantEntradas >= 1 ) {
			JOptionPane.showMessageDialog(null,
					"Compraste " + cantEntradas + " entrada/s para : " + Concierto.getNombre()
							+ "\nPrecio de la entrada : $ " + precioUnitario
							+ "\nPrecio final de la compra : $ " + precioEntrada ,
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

	public String verificaIngresarEmpleado(String dni, String password) {

		return nuevoempleado.ingresar(dni, password);
	}
}
