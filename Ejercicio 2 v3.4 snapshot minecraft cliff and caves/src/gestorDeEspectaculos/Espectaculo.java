package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Espectaculo {

	String titulo;
	String categorias;
	String descripcion;
	ArrayList<Sesion> sesiones;

	
	Espectaculo(String titulo,String categoria,String descripcion){
		this.titulo=titulo;
		this.categorias=categoria;
		this.descripcion=descripcion;
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
	 * @param sesion Sesión a eliminar
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	protected void eliminaSesion(Sesion sesion) {
		for (int i = 0; i < sesiones.size(); ++i) {
			if (sesiones.get(i).equals(sesion)) {
				sesiones.remove(i);
				break;
			}
		}
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
	
	/**
	 * Función que comprueba que quedan entradas disponibles de un espectáculo
	 * 
	 * @return Devuelve verdadero en caso de que haya entradas disponibles de dicho espectaculo, falso en caso contrario
	 */
	public boolean entradasDisponibles() {
		
		return true;
	}

}
