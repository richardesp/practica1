package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EspectaculoTemporada extends Espectaculo {

	// Variables
	String titulo;
	String categorias;
	String descripcion;
	Sesion sesion;

	// Métodos
	/**
	 * Constructor parametrizado de la clase EspectaculoTemporada
	 * 
	 * @param titulo      Cadena que contiene el título del espectáculo
	 * @param categorias  Cadena que contiene las categorias del espectáculo
	 * @param descripcion Cadena que contiene la descrpcion del espectáculo
	 * @param sesiones    Clase de tipo Sesion que contiene la fecha y localidades
	 *                    de lasesion de un espectaculo
	 * @author Enrique Estevez Mayoral
	 */
	public EspectaculoTemporada(String titulo, String categorias, String descripcion, Sesion sesion) {
		this.titulo = titulo;
		this.categorias = categorias;
		this.descripcion = descripcion;
		this.sesion = sesion;
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
	Sesion getSesiones() {
		return sesion;
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
	public void setSesiones(Sesion sesion) {
		this.sesion = sesion;
	}
	
	
	/**
	 * Función que compara 2 fechas
	 * 
	 * @param fecha Fecha de la sesion que se quiere comprobar
	 * @return 
	 * @throws Exception
	 * @author Enrique Estevez Mayoral
	 */
	private boolean fechaMayorActual(Date fecha) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		String fechaEspectaculo = formatter.format(fecha);
		String fechaActual = formatter.format(date);

		if (fechaEspectaculo.compareTo(fechaActual) > 0)
			throw new Exception("La fecha del espectaculo es");

		return true;
	}
	
	
	// Definirla
	
	public Espectaculo crearEspectaculo(String titulo, String categorias, String descripcion,
			ArrayList<Sesion> sesiones) throws Exception {
		if (sesiones.size() != 1)
			throw new Exception("El formato de las sesiones no es válido para el Espectáculo puntual\n\n");

		EspectaculoTemporada e = new EspectaculoTemporada(titulo, categorias, descripcion, sesiones.get(0));

		return e;
	}

}
