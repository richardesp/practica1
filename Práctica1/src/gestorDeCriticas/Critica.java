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
 * @author Ricardo Espantaleón Pérez
 *
 */
public class Critica {

	// Variables
	private String titulo;
	private float puntuacion;
	private String comentario;
	// Una crítica tiene un único creador (espectador)
	private Espectador autor;
	private ArrayList<Float> valoracion;

	// Metodo

	/**
	 * Constructor de la clase Critica, que se le pasa por argumento el titulo, la
	 * puntuacion, el comentario y el nick del autor
	 * 
	 * 
	 * @param titulo
	 * @param puntuacion
	 * @param comentario
	 * @param autor
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	Critica(String titulo, float puntuacion, String comentario, Espectador autor) {
		assert (puntuacion >= 0 && puntuacion <= 10) : "La puntuación debe estar comprendida entre 0 y 10";
		this.titulo = titulo;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
		this.autor = autor;
		valoracion = new ArrayList<Float>();
	}

	/**
	 * Devuelve el titulo de la Critica
	 * 
	 * @return titulo
	 * @author Enrique Estévez Mayoral
	 */
	public String getTitulo() {
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
	public String getComentario() {
		return this.comentario;
	}

	/**
	 * Devuelve el nick del autor de la Critica
	 * 
	 * @return Retorna el nick del autor
	 * @author Enrique Estévez Mayoral
	 */
	public String getNickAutor() {
		return this.autor.getNick();
	}

	/**
	 * 
	 * @return Retorna el autor de la crítica
	 */
	public Espectador getAutor() {
		return autor;
	}

	/**
	 * Devuelve la puntuacion de la Critica
	 * 
	 * @return valoracion
	 * @author Enrique Estévez Mayoral
	 */
	public ArrayList<Float> getValoracion() {
		return this.valoracion;
	}

	/**
	 * Cambia el valor de la variable titulo por el valor pasado por argumento a la
	 * función
	 * 
	 * @param titulo
	 * @author Enrique Estévez Mayoral
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Cambia el valor de la variable puntuacion por el valor pasado por argumento a
	 * la función, si el valor es negativo no se produce el cambio
	 * 
	 * @param puntuacion
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void setPuntuacion(float puntuacion) {
		assert (puntuacion >= 0 && puntuacion <= 10) : "La puntuación debe estar comprendida entre 0 y 10";
		this.puntuacion = puntuacion;

	}

	/**
	 * Cambia el valor de la variable comentario por el valor pasado por argumento a
	 * la función
	 * 
	 * @param comentario
	 * @author Enrique Estévez Mayoral
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Cambia el valor de la variable nickAutor por el valor pasado por argumento a
	 * la función
	 * 
	 * @param nickAutor
	 * @author Enrique Estévez Mayoral
	 */
	public void setNickAutor(String nickAutor) {
		autor.setNick(nickAutor);
	}

	/**
	 * Cambia el valor de la variable valoracion por el valor pasado por argumento a
	 * la función
	 * 
	 * @param valoracion
	 * @author Enrique Estévez Mayoral
	 */
	public void setValoracion(ArrayList<Float> valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * Agrega una valoracion al Array valoraciones, solo si su valor esta entre 0 y
	 * 10
	 * 
	 * @param valoracionCritica
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void agregarValoracion(float valoracionCritica) {
		assert (valoracionCritica >= 0 && valoracionCritica <= 10)
				: "La puntuación debe estar comprendida entre 0 y 10";
		valoracion.add(valoracionCritica);

	}

	/**
	 * Realiza la media de todas las valoraciones
	 * 
	 * @return un float con la media
	 * @throws Exception Se lanza si no hay valoraciones a una obra
	 */
	public float hacerMediaValoracion() throws Exception {
		if (valoracion.isEmpty())
			throw new Exception("No hay valoraciones de esta obra");
		float suma = 0;
		for (int i = 0; i < valoracion.size(); i++) {
			suma += valoracion.get(i);
		}
		float retorno = suma / valoracion.size();
		return retorno;
	}

}

