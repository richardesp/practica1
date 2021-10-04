/**
 * 
 */
package gestorDeCriticas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Espectador es un TAD que se encarga de almacenar todos los datos
 * relacionados con el Espectador de una obra
 * 
 * @author Enrique Estévez Mayoral
 * @author Ricardo Espantaleón Pérez
 * @see java.util.regex.Matcher
 * @version 1.0
 */
public class Espectador {

	// Variables

	// Nombre y apellidos del espectador
	private String nombreApellidos;

	// Nickname del espectador en el sistema
	private String nick;

	// Email del espectador
	private String email;

	// Patrón REGEX para comprobar que el email es válido
	private Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	// Metodos

	/**
	 * Constructor parametrizado de la clase Espectador que se le pasa por argumento
	 * el nick, el nombre y los apellidos, y el email
	 * 
	 * @param nick            Nickname del espectador a crear
	 * @param nombreApellidos Nombre y apellidos del espectador a crear
	 * @param email           Email del espectador a crear
	 * @throws Exception Lanza excepción en caso de que el email no tenga un formato
	 *                   válido
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public Espectador(String nick, String nombreApellidos, String email) throws Exception {
		if (!validarEmail(email))
			throw new Exception("El formato del email es incorrecto");
		this.nick = nick;
		this.nombreApellidos = nombreApellidos;
		this.email = email;
	}

	/**
	 * Constructor parametrizado que recibe un espectador
	 * 
	 * @param e Espectador a asignar a la nueva instancia
	 * @throws Exception Lanza excepción en caso de que el email no tenga un formato
	 *                   válido
	 * @author Ricardo Espantaleón Pérez
	 * @author Enrique Estévez Mayoral
	 */
	public Espectador(Espectador e) throws Exception {
		if (!validarEmail(e.getEmail()))
			throw new Exception("El formato del email es incorrecto");

		this.nick = e.getNick();
		this.nombreApellidos = e.getNombreApellidos();
		this.email = e.getEmail();
	}

	/**
	 * Función que devuelve el nombre y apellidos del Espectador
	 * 
	 * @return Devuelve el nombre y los apellidos de la instancia
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public String getNombreApellidos() {
		return this.nombreApellidos;
	}

	/**
	 * Función que devuelve el nick del Espectador
	 * 
	 * @return Devuelve el nickname de la instancia
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public String getNick() {
		return this.nick;
	}

	/**
	 * Función que devuelve el email del Espectador
	 * 
	 * @return Devuelve el email de la instancia
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Función que cambia el valor de la variable nombreApellidos por el valor
	 * pasado como argumento a la función
	 * 
	 * @param nombreApellidos Nombre y apellidos a asignar a la instancia
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	/**
	 * Función que cambia el valor de la variable nick por el valor pasado como
	 * argumento a la función
	 * 
	 * @param nick Nickname a asignar a la instancia
	 * @author Enrique Estévez Mayoral
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Función que cambia el valor de la variable email por el valor pasado como
	 * argumento a la función
	 * 
	 * @param email Email a cambiar a la instancia
	 * @throws Exception Lanza una excepción en caso de que el email no tenga un
	 *                   formato válido
	 * @author Enrique Estévez Mayoral
	 * @author Ricardo Espantaleón Pérez
	 */
	public void setEmail(String email) throws Exception {
		if (!validarEmail(email))
			throw new Exception("El formato del email es incorrecto");

		this.email = email;
	}

	/**
	 * Función para comprobar si el email introducido cumple la REGEX
	 * 
	 * @param email parámetro a comprobar si cumple la REGEX
	 * @return Verdadero si cumple el formato y falso en caso contrario
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	private boolean validarEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

		return matcher.find();
	}
}
