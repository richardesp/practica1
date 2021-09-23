/**
 * 
 */
package gestorDeCriticas;

import java.util.ArrayList;

/**
 * La clase Critica con atributos titulo, puntuacion, comentario, nick del autor
 * y valoracion
 * 
 * @author Enrique Estévez Mayoral
 *
 */
public class Critica {

	// Variables
	private String titulo;
	private float puntuacion;
	private String comentario;
	private String nickAutor;
	private ArrayList<Float> valoracion;

	// Metodo

	/**
	 * Constructor de la clase Critica, que se le pasa por argumento el titulo, la
	 * puntuacion, el comentario y el nick del autor
	 * 
	 * @param titulo
	 * @param puntuacion
	 * @param comentario
	 * @param nickAutor
	 * @author Enrique Estévez Mayoral
	 */
	Critica(String titulo, float puntuacion, String comentario, String nickAutor) {
		this.titulo = titulo;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
		this.nickAutor = nickAutor;
	}

	/**
	 * Devuelve el titulo de la Critica
	 * 
	 * @return titulo
	 * @author Enrique Estévez Mayoral
	 */
	String getTitulo() {
		return this.titulo;
	}

	/**
	 * Devuelve la puntuacion de la Critica
	 * 
	 * @return puntuacion
	 * @author Enrique Estévez Mayoral
	 */
	float getPuntuacion() {
		return this.puntuacion;
	}

	/**
	 * Devuelve el comentario de la Critica
	 * 
	 * @return comentario
	 * @author Enrique Estévez Mayoral
	 */
	String getComentario() {
		return this.comentario;
	}

	/**
	 * Devuelve el nick del autor de la Critica
	 * 
	 * @return nickAutor
	 * @author Enrique Estévez Mayoral
	 */
	String getNickAutor() {
		return this.nickAutor;
	}

	/**
	 * Devuelve la puntuacion de la Critica
	 * 
	 * @return valoracion
	 * @author Enrique Estévez Mayoral
	 */
	ArrayList<Float> getValoracion() {
		return this.valoracion;
	}

	/**
	 * Cambia el valor de la variable titulo por el valor pasado por argumento a la
	 * función
	 * 
	 * @param titulo
	 * @author Enrique Estévez Mayoral
	 */
	void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Cambia el valor de la variable puntuacion por el valor pasado por argumento a
	 * la función, si el valor es negativo no se produce el cambio
	 * 
	 * @param puntuacion
	 * @author Enrique Estévez Mayoral
	 */
	void setPuntuacion(float puntuacion) {
		if (puntuacion >= 0 && puntuacion <= 10)
			this.puntuacion = puntuacion;
		else
			System.out.println("Ese valor no es valido, debe ser un valor entre 0 y 10");

	}

	/**
	 * Cambia el valor de la variable comentario por el valor pasado por argumento a
	 * la función
	 * 
	 * @param comentario
	 * @author Enrique Estévez Mayoral
	 */
	void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Cambia el valor de la variable nickAutor por el valor pasado por argumento a
	 * la función
	 * 
	 * @param nickAutor
	 * @author Enrique Estévez Mayoral
	 */
	void setNickAutor(String nickAutor) {
		this.nickAutor = nickAutor;
	}

	/**
	 * Cambia el valor de la variable valoracion por el valor pasado por argumento a
	 * la función
	 * 
	 * @param valoracion
	 * @author Enrique Estévez Mayoral
	 */
	void setValoracion(ArrayList<Float> valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * Agrega una valoracion al Array valoraciones, solo si su valor esta entre 0 y
	 * 10
	 * 
	 * @param valoracionCritica
	 * @author Enrique Estévez Mayoral
	 */
	public void agregarValoracion(float valoracionCritica) {
		if (valoracionCritica >= 0 && valoracionCritica <= 10)
			valoracion.add(valoracionCritica);
		else
			System.out.println("Ese valor no es valido, debe ser un valor entre 0 y 10");
	}

}
