package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class EspectaculoPuntual extends Espectaculo {

	// Variables
	String titulo;
	String categorias;
	String descripcion;
	ArrayList<Sesion> sesiones;

	// Métodos

	/**
	 * Constructor parametrizado de la clase EspectaculoPuntual
	 * 
	 * @param titulo      Cadena que contiene el título del espectáculo
	 * @param categorias  Cadena que contiene las categorias del espectáculo
	 * @param descripcion Cadena que contiene la descrpcion del espectáculo
	 * @param sesiones    Clase de tipo Sesion que contiene la fecha y localidades
	 *                    de lasesion de un espectaculo
	 * @author Enrique Estevez Mayoral
	 */
	public EspectaculoPuntual(String titulo, String categorias, String descripcion, ArrayList<Sesion> sesiones) {
		this.titulo = titulo;
		this.categorias = categorias;
		this.descripcion = descripcion;
		this.sesiones = sesiones;
	}

	/**
	 * Función que devuelve el título del Espectáculo
	 * 
	 * @return El título del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	String getTitulo() {
		return titulo;
	}

	/**
	 * Función que devuelve la categoria del Espectáculo
	 * 
	 * @return Las categorias del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	String getCategorias() {
		return categorias;
	}

	/**
	 * Función que devuelve la descripción del Espectáculo
	 * 
	 * @return La descrpcion del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	String getDescripcion() {
		return descripcion;
	}

	/**
	 * Función que devuelve las sesiones del Espectaculo
	 * 
	 * @return Las sesiones del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	ArrayList<Sesion> getSesiones() {
		return sesiones;
	}

	/**
	 * Cambia el valor de la variable titulo por el valor pasado por argumento a la
	 * función
	 * 
	 * @param titulo Titulo a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 * 
	 */
	void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Cambia el valor de la variable categorias por el valor pasado por argumento a
	 * la función
	 * 
	 * @param categorias Categoria a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 */
	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	/**
	 * Cambia el valor de la variable descripcion por el valor pasado por argumento
	 * a la función
	 * 
	 * @param titulo Titulo a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Cambia el valor de la variable sesiones por el valor pasado por argumento a
	 * la función
	 * 
	 * @author Enrique Estevez Mayoral
	 */
	public void setSesiones(ArrayList<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * Función que compara 2 fechas
	 * 
	 * @param fechaEspectaculo Fecha de la sesion que se quiere comprobar
	 * @author Enrique Estevez Mayoral
	 */
	private boolean fechaMayorActual(Date fechaEspectaculo) {

		Date fechaActual = new Date();

		if (fechaEspectaculo.compareTo(fechaActual) > 0)
			return true;

		return false;
	}
	
	/**
	 * Función que crea un espectáculo
	 * 
	 * @param titulo el titulo del espectaculo
	 * @param categorias la categoria del espectáculo
	 * @param descripcion descripcion del espectaculo
	 * @param sesiones la lista de sesiones del espectaculo
	 * @return un espectaculo
	 * @throws Exception Se lanza cuando no tiene el número correcto de elementos
	 * @author Enrique Estevez Mayoral
	 */
	
	@Override
	public Espectaculo crearEspectaculoP(String titulo, String categorias, String descripcion,
			ArrayList<Sesion> sesiones) throws Exception {
		if (sesiones.size() != 1)
			throw new Exception("El formato de las sesiones no es válido para el Espectáculo puntual\n\n");

		EspectaculoPuntual e = new EspectaculoPuntual(titulo, categorias, descripcion, sesiones);

		return e;
	}

}
