package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Espectaculo {

	String titulo;
	String categorias;
	String descripcion;
	ArrayList<Sesion> sesiones;

	Espectaculo(String titulo, String categoria, String descripcion, ArrayList<Sesion> sesiones) {
		this.titulo = titulo;
		this.categorias = categoria;
		this.descripcion = descripcion;
		this.sesiones = sesiones;
	}

	/**
	 * Función que devuelve el título del Espectáculo
	 * 
	 * @return El título del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	protected String getTitulo() {
		return titulo;
	}

	/**
	 * Función que devuelve la categoria del Espectáculo
	 * 
	 * @return Las categorias del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	protected String getCategorias() {
		return categorias;
	}

	/**
	 * Función que devuelve la descripción del Espectáculo
	 * 
	 * @return La descrpcion del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	protected String getDescripcion() {
		return descripcion;
	}

	/**
	 * Función que devuelve las sesiones del Espectaculo
	 * 
	 * @return Las sesiones del espectáculo
	 * @author Enrique Estevez Mayoral
	 */
	protected ArrayList<Sesion> getSesiones() {
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
	protected void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	/**
	 * Cambia el valor de la variable descripcion por el valor pasado por argumento
	 * a la función
	 * 
	 * @param titulo Titulo a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 */
	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Cambia el valor de la variable sesiones por el valor pasado por argumento a
	 * la función
	 * 
	 * @author Enrique Estevez Mayoral
	 */
	protected void setSesiones(ArrayList<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * Función que elimina todas las sesiones asociadas al espectáculo
	 * 
	 * @author Ricardo Espantaleón
	 */
	public void eliminaSesiones() {
		this.sesiones.clear();
	}

	/**
	 * Función que elimina una sesión de un espectaculo
	 * 
	 * 
	 * @param fecha Fecha y hora de la sesión a eliminar
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	protected void eliminaSesion(Date fecha) {
		for (int i = 0; i < sesiones.size(); ++i) {
			if (sesiones.get(i).getFecha().compareTo(fecha) == 0) {
				sesiones.remove(i);
				break;
			}
		}
	}

	public boolean entradasDisponibles() {

		return true;
	}

}
