/**
 * 
 */
package gestorDeCriticas;

/**
 * La clase Espectador con atributos nombre y apellidos, nick , y email
 * 
 * @author Enrique Estévez Mayoral
 * @author Ricardo Espantaleón Pérez
 *
 */
public class Espectador {

	// Variables
	private String nombreApellidos;
	private String nick;
	private String email;

	// Metodos

	/**
	 * Constructor de la clase Espectador que se le pasa por argumento el nick, el
	 * nombre y los apellidos, y el email
	 * 
	 * @param nick
	 * @param nombreApellidos
	 * @param email
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	Espectador(String nick, String nombreApellidos, String email) {
		this.nick = nick;
		this.nombreApellidos = nombreApellidos;
		this.email = email;
	}

	/**
	 * Devuelve el nombre y apellidos del Espectador
	 * 
	 * @return nombreApellidos
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public String getNombreApellidos() {
		return this.nombreApellidos;
	}

	/**
	 * Devuelve el nick del Espectador
	 * 
	 * @return nick
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public String getNick() {
		return this.nick;
	}

	/**
	 * Devuelve el email del Espectador
	 * 
	 * @return email
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Cambia el valor de la variable nombreApellidos por el valor pasado por
	 * argumento a la función
	 * 
	 * @param nombreApellidos
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	/**
	 * Cambia el valor de la variable nick por el valor pasado por argumento a la
	 * función
	 * 
	 * @param nick
	 * @author Enrique Estévez Mayoral
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Cambia el valor de la variable email por el valor pasado por argumento a la
	 * función
	 * 
	 * @param email
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
