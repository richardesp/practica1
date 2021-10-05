/**
 * 
 */
package gestorDeEspectaculos;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private ArrayList<Ticket> pases;
	private Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

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
	public Espectador(String nick, String nombreApellidos, String email) throws Exception {
		if (!validarEmail(email))
			throw new Exception("El formato del email es incorrecto");
		this.nick = nick;
		this.nombreApellidos = nombreApellidos;
		this.email = email;
		this.pases= new ArrayList<Ticket>();
		
	}

	public Espectador(Espectador e) throws Exception {
		if (!validarEmail(e.getEmail()))
			throw new Exception("El formato del email es incorrecto");

		this.nick = e.getNick();
		this.nombreApellidos = e.getNombreApellidos();
		this.email = e.getEmail();
		this.asistenteEspectaculos=e.asistenteEspectaculos;
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
	
	/**
	 * Función para devolver los espectáculos asociados al espectador
	 *
	 * @return vector de espectáculos
	 * @exception SAlta si el vector está vacío
	 * @author Nicolás López
	 */
	public ArrayList<Ticket> getAsistenteEspectaculos() throws Exception{
		if(pases.isEmpty())
			throw new Exception("No hay espectáculos");
		return pases;
	}
	/**
	 * Función que añade una sesión al vector de espectaculos
	 * 
	 * @param un espectáculo
	 * @author Nicolás López
	 */
	public void asistirEspectaculo(Ticket espectaculo) {
		pases.add(espectaculo);
	}
}
