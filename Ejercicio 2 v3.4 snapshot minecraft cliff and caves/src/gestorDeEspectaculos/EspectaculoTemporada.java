package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase Critica es un TAD que se encarga de almacenar y gestionar todo los
 * atributos relacionados con la sesión de un espectáculo de temporada
 * 
 * @author Enrique Estevez Mayoral
 * @see Espectaculo
 * @version 1.0
 */
public class EspectaculoTemporada extends Espectaculo {

	// Métodos
	/**
	 * Constructor parametrizado de la clase EspectaculoTemporada
	 * 
	 * @param titulo      Cadena que contiene el título del espectáculo
	 * @param categorias  Cadena que contiene las categorias del espectáculo
	 * @param descripcion Cadena que contiene la descrpcion del espectáculo
	 * @param sesiones    Clase de tipo Sesion que contiene la fecha y localidades
	 *                    de la sesion de un espectaculo
	 * @author Enrique Estevez Mayoral
	 */
	public EspectaculoTemporada(String titulo, String categorias, String descripcion, ArrayList<Sesion> sesiones) {
		super(titulo, categorias, descripcion, sesiones);
		this.titulo = titulo;
		this.categorias = categorias;
		this.descripcion = descripcion;
		this.sesiones = sesiones;
	}

}
