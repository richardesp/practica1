package gestorDeEspectaculos;

import java.util.ArrayList;

/**
 * La clase Critica es un TAD que se encarga de almacenar y gestionar todo los
 * atributos relacionados con la sesión de un espectáculo puntual
 * 
 * @author Enrique Estevez Mayoral
 * @see Espectaculo
 * @version 1.0
 */
public class EspectaculoPuntual extends Espectaculo {

	// Variables

	// Métodos

	/**
	 * Constructor parametrizado de la clase EspectaculoPuntual
	 * 
	 * @param titulo      Cadena que contiene el título del espectáculo
	 * @param categorias  Cadena que contiene las categorias del espectáculo
	 * @param descripcion Cadena que contiene la descrpcion del espectáculo
	 * @param sesion      Clase de tipo Sesion que contiene la fecha y localidades
	 *                    de la sesion de un espectaculo
	 * @author Enrique Estevez Mayoral
	 */
	public EspectaculoPuntual(String titulo, String categorias, String descripcion, ArrayList<Sesion> sesion) {
		super(titulo, categorias, descripcion, sesion);
		this.titulo = titulo;
		this.categorias = categorias;
		this.descripcion = descripcion;
		this.sesiones = sesion;
	}

}
