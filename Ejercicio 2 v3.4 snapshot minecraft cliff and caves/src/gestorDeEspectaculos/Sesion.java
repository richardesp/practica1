package gestorDeEspectaculos;

import java.util.Date;

/**
 * La clase Critica es un TAD que se encarga de almacenar y gestionar todo los
 * atributos relacionados con la sesión de un espectáculo
 * 
 * @author Enrique Estévez Mayoral
 * @author Nicolás López
 * @see Espectaculo
 * @version 1.0
 */
public class Sesion {
	int localidades;
	Date fecha;
	int vendidas;

	/**
	 * Constructor parametrizado de la clase Sesion
	 * 
	 * @param localidades Numero entero que contiene las localidades que tiene una
	 *                    sesión de un espectáculo
	 * @param fecha       Fecha y hora de una sesión de un espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	public Sesion(int localidades, Date fecha) {
		this.localidades = localidades;
		this.fecha = fecha;
		vendidas = 0;
	}

	/**
	 * Función que devuelve las localidades de la clase Sesion
	 * 
	 * @return Devuelve el número de localidades
	 * @author Enrique Estévez Mayoral
	 */
	public int getLocalidades() {
		return localidades;
	}

	/**
	 * Función que devuelve la fecha y hora de la clase Sesion
	 * 
	 * @return Devuelve la fecha y hora
	 * @author Enrique Estévez Mayoral
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Función que cambia el valor de la variable localidades por el valor pasado
	 * como argumento a la función
	 * 
	 * @param localidades Número de localidades de la sesión
	 * @author Enrique Estévez Mayoral
	 */
	public void setLocalidades(int localidades) {
		this.localidades = localidades;
	}

	/**
	 * Función que cambia el valor de la variable fecha por el valor pasado como
	 * argumento a la función
	 * 
	 * @param fecha Fecha y hora de la sesión
	 * @author Enrique Estévez Mayoral
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Función que calcula las entradas vendiads de la sesión
	 *
	 * @author Nicolás López
	 */
	public void entradaVendida() {
		vendidas = vendidas + 1;
	}

	/**
	 * Función que devuelve las localidades vendidas de la clase Sesion
	 * 
	 * @return Devuelve el número de localidades vendidas
	 * @author Nicolás López
	 */
	public int getVendidas() {
		return vendidas;
	}
}
