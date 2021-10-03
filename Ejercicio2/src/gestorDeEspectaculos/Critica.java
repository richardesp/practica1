/**
 * 
 */
package gestorDeEspectaculos;

import java.util.ArrayList;

/**
 * La clase Critica es un TAD que se encarga de almacenar y gestionar todo los
 * atributos relacionados con la crítica
 * 
 * @author Enrique Estévez Mayoral
 * @author Ricardo Espantaleón Pérez
 * @see Espectador
 * @version 1.0
 */
public class Critica {

	// Variables

	// Título de la obra a criticar
	private String titulo;

	// Flotante que contiene la puntuación a la obra
	private float puntuacion;

	// Comentario asociado a la crítica de la obra
	private String comentario;

	// Autor de la crítica. Una crítica tiene un único creador (espectador)
	private Espectador autor;

	// Array de las distintas valoraciones que se han dado a la crítica
	private ArrayList<Float> valoracion;

	// Métodos

	/**
	 * Constructor parametrizado de la clase Crítica
	 * 
	 * 
	 * @param titulo     Cadena que contiene el título de la obra a críticar
	 * @param puntuacion Flotante que contiene la puntuación a la obra
	 * @param comentario Cadena la cual contiene un breve resumen de la crítica
	 * @param autor      TAD Espectador el cual almacena el autor de la crítica
	 *                   realizada
	 * 
	 * @exception assert Contiene un aserto el cual especifica que la puntuación ha
	 *                   de ir comprendida entre 0 y 10
	 * 
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public Critica(String titulo, float puntuacion, String comentario, Espectador autor) {
		assert (puntuacion >= 0 && puntuacion <= 10) : "La puntuación debe estar comprendida entre 0 y 10";
		this.titulo = titulo;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
		this.autor = autor;
		valoracion = new ArrayList<Float>();
	}

	/**
	 * Función que devuelve el título de la obra de la crítica
	 * 
	 * @return El título de la obra de la crítica
	 * @author Enrique Estévez Mayoral
	 */
	public String getTitulo() {
		return this.titulo;
	}

	/**
	 * Función que devuelve la puntuación de la crítica
	 * 
	 * @return La puntuación a la obra de la crítica
	 * @author Enrique Estévez Mayoral
	 */
	public float getPuntuacion() {
		return this.puntuacion;
	}

	/**
	 * Función que devuelve el comentario de la crítica
	 * 
	 * @return El comentario asociado a la crítica
	 * @author Enrique Estévez Mayoral
	 */
	public String getComentario() {
		return this.comentario;
	}

	/**
	 * Función que devuelve el nick del autor de la crítica
	 * 
	 * @return El nickName asociado al autor de la obra
	 * @author Enrique Estévez Mayoral
	 */
	public String getNickAutor() {
		return this.autor.getNick();
	}

	/**
	 * Función que retornar el TAD completo (Espectador) del autor
	 * 
	 * @return El autor de la crítica
	 * @see Espectador
	 */
	public Espectador getAutor() {
		return autor;
	}

	/**
	 * Devuelve la puntuacion de la Critica
	 * 
	 * @return El vector de valoraciones por parte de los distintos usuarios
	 *         asociada a la crítica
	 * 
	 * @see ArrayList
	 * @author Enrique Estévez Mayoral
	 */
	public ArrayList<Float> getValoracion() {
		return this.valoracion;
	}

	/**
	 * Función que cambia el valor de la variable título por el valor pasado como
	 * argumento a la función
	 * 
	 * @param titulo Cadena la cual contiene el nuevo el título de la obra
	 * @author Enrique Estévez Mayoral
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Función que cambia el valor de la variable puntuación por el valor pasado
	 * como argumento a la función
	 * 
	 * @param puntuacion Flotante que contiene la nueva puntuación asociada a la
	 *                   obra por parte del autor
	 * 
	 * @exception assert Aserto el cual específica que la puntuación ha de estar
	 *                   comprendida entre 0 y 10
	 * 
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void setPuntuacion(float puntuacion) {
		assert (puntuacion >= 0 && puntuacion <= 10) : "La puntuación debe estar comprendida entre 0 y 10";
		this.puntuacion = puntuacion;

	}

	/**
	 * Función que cambia el valor de la variable comentario por el valor pasado
	 * como argumento a la función
	 * 
	 * @param comentario Cadena la cual contiene el nuevo comentario asociado a la
	 *                   crítica
	 * @author Enrique Estévez Mayoral
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Función que cambia el valor de la variable nick del autor por el valor pasado
	 * como argumento a la función
	 * 
	 * @param nickAutor Cadena la cual contiene el nuevo nickName del autor
	 * 
	 * @see Espectador
	 * @author Enrique Estévez Mayoral
	 */
	public void setNickAutor(String nickAutor) {
		autor.setNick(nickAutor);
	}

	/**
	 * Función que cambia el vector de valoraciones asociadas a la crítica
	 * 
	 * @param valoracion Vector el cual contiene las nuevas valoraciones de la
	 *                   crítica
	 * @see ArrayList
	 * @author Enrique Estévez Mayoral
	 */
	public void setValoracion(ArrayList<Float> valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * Función que agrega una valoración al Array valoraciones, solo si su valor
	 * esta entre 0 y 10
	 * 
	 * @param valoracionCritica Flotante el cual contiene la valoración a la crítica
	 *                          realizada
	 * 
	 * @exception assert Aserto el cual especifica que la valoración a la crítica de
	 *                   un usuario debe estar comprendida entre 0 y 10
	 * 
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void agregarValoracion(float valoracionCritica) {
		assert (valoracionCritica >= 0 && valoracionCritica <= 10)
				: "La puntuación debe estar comprendida entre 0 y 10";
		valoracion.add(valoracionCritica);

	}

	/**
	 * Función que realiza la media aritmética de todas las valoraciones
	 * 
	 * @return Un flotante con toda la media de valoraciones asociada a la crítica
	 * @throws Exception Se lanza si no hay valoraciones a la crítica
	 * @author Ricardo Espantaleón Pérez
	 */
	public float hacerMediaValoracion() throws Exception {
		if (valoracion.isEmpty())
			throw new Exception("No hay valoraciones de esta crítica");
		float suma = 0;
		for (int i = 0; i < valoracion.size(); i++) {
			suma += valoracion.get(i);
		}
		float retorno = suma / valoracion.size();
		return retorno;
	}

}

