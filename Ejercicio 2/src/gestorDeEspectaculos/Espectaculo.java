package gestorDeEspectaculos;

import java.sql.Date;

public abstract class Espectaculo {
	// Espectáculo puntual
	public abstract EspectaculoPuntual crearEspectaculoPuntual();

	// Espectáculo de pase multiple
	public abstract EspectaculoPaseMultiple crearEspectaculoPaseMultiple();

	// Espectáculo de temporada
	public abstract EspectaculoTemporada crearEspectaculoTemporada();

	// Variables
	String titulo;
	String categorias;
	String descripcion;
	Date fecha;
	int localidades;

	/**
	 * Devuelve el título del Espectáculo
	 * 
	 * @return titulo
	 * @author Enrique Estevez Mayoral
	 */
	String getTitulo() {
		return titulo;
	}

	/**
	 * Devuelve la categoria del Espectáculo
	 * 
	 * @return categorias
	 * @author Enrique Estevez Mayoral
	 */
	String getCategorias() {
		return categorias;
	}

	/**
	 * Devuelve la descripción del Espectáculo
	 * 
	 * @return descripcion
	 * @author Enrique Estevez Mayoral
	 */
	String getDescripcion() {
		return descripcion;
	}

	/**
	 * Devuelve la fecha del Espectaculo
	 * 
	 * @return fecha
	 * @author Enrique Estevez Mayoral
	 */
	Date getFecha() {
		return fecha;
	}

	/**
	 * Devuelve las localidades del Espectáculo
	 * 
	 * @return localidades
	 * @author Enrique Estevez Mayoral
	 */
	int getLocalidades() {
		return localidades;
	}

	/**
	 * Cambia el valor de la variable titulo por el valor pasado por
	 * argumento a la función
	 * 
	 * @param titulo Titulo a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 * 
	 */
	void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Cambia el valor de la variable categorias por el valor pasado por
	 * argumento a la función
	 * 
	 * @param categorias Categoria a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 */
	void setCategorias(String categorias) {
		this.categorias = categorias;
	}

	/**
	 * Cambia el valor de la variable descripcion por el valor pasado por
	 * argumento a la función
	 * 
	 * @param titulo Titulo a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 */
	void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Cambia el valor de la variable fecha por el valor pasado por
	 * argumento a la función
	 * 
	 * @author Enrique Estevez Mayoral
	 */
	void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Cambia el valor de la variable localidades por el valor pasado por
	 * argumento a la función
	 * 
	 * @author Enrique Estevez Mayoral
	 */
	void setLocalidades(int localidades) {
		this.localidades = localidades;
	}

}
