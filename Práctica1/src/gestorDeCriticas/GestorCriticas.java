/**
 * 
 */
package gestorDeCriticas;

import java.util.ArrayList;

/**
 * Singleton !!!
 * 
 * @author Ricardo Espantaleón Pérez
 * @author Nicolás López Delgado
 *
 */
public class GestorCriticas {

	// Variables

	// Instancia del patrón de diseño singleton
	private static GestorCriticas instance = null;
	// Lista de usuarios
	private ArrayList<Espectador> usuarios;
	// Lista de críticas, solo podrá existir una crítica si existe su
	// correspondiente autor en el vector de usuarios
	// sino lanzo excepción
	private ArrayList<Critica> criticas;

	// Métodos

	/**
	 * Función que devuelve la instancia de la clase
	 * 
	 * @return Devuelve la instancia de la clase
	 * @author Ricardo Espantaleón Pérez
	 */
	public static GestorCriticas getInstance() {
		if (instance == null)
			instance = new GestorCriticas();
		return instance;
	}

	/**
	 * Constructor sin parámetros
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	private GestorCriticas() {
		this.usuarios = new ArrayList<Espectador>();
		this.criticas = new ArrayList<Critica>();
	}

	/**
	 * Función que comprueba que exista un usuario en el sistema
	 * 
	 * @param nick Nickname del usuario
	 * @return Verdadero si existe el usuario y falso si no existe
	 * @author Ricardo Espantaleón Pérez
	 */
	public boolean existeNick(String nick) {
		if (listaUsuariosVacia())
			return false;
		for (int index = 0; index < usuarios.size(); ++index) {
			if (usuarios.get(index).getNick() == nick)
				return true;
		}

		return false;
	}

	/**
	 * 
	 * @return Devuelve verdadero si la lista de usuarios está vacía
	 * @author Ricardo Espantaleón Pérez
	 */
	public boolean listaUsuariosVacia() {
		return usuarios.size() == 0;
	}

	/**
	 * 
	 * @return Devuelve verdadero si la lista de críticas está vacía
	 * @author Ricardo Espantaleón Pérez
	 */
	public boolean listaCriticasVacia() {
		return criticas.size() == 0;
	}

	/**
	 * Función que devuelve un usuario si existe en el sistema
	 * 
	 * @exception Lanza una excepción en caso de que no exista el usuario en el
	 *                  sistema
	 * 
	 * @param nick
	 * @return Devuelve un usuario existente en el sistema
	 * @author Ricardo Espantaleón Pérez
	 */
	public Espectador getUsuario(String nick) throws Exception {
		if (!existeNick(nick))
			throw new Exception("Usuario no existente en el sistema");

		Espectador usuario = new Espectador(null, null, null);
		for (int index = 0; index < usuarios.size(); ++index) {
			if (usuarios.get(index).getNick() == nick) {
				usuario = usuarios.get(index);
				break;
			}
		}

		return usuario;

	}

	/**
	 * Registra un usuario en el sistema, siempre y cuando no exista previamente
	 * 
	 * @param espectador
	 * @return Devuelve true si el usuario ha podido crearse, en caso de que ya
	 *         exista con dicho nick devolverá false
	 * @exception Lanza una excepción en caso de que ya exista el usuario en el
	 *                  sistema
	 * @author Ricardo Espantaleón Pérez
	 */
	public void crearUsuario(Espectador espectador) throws Exception {
		if (existeNick(espectador.getNick()))
			throw new Exception("Usuario ya registrado");
		usuarios.add(espectador);

	}

	/**
	 * Función que elimina un usuario (retorna falso si no existe y true en caso
	 * contrario)
	 * 
	 * @exception Lanza una excepción en caso de que no exista el usuario en el
	 *                  sistema
	 * @param nick
	 * @return
	 * @author Ricardo Espantaleón Pérez
	 */
	public void eliminarUsuario(String nick) throws Exception {
		if (!existeNick(nick))
			throw new Exception("Usuario no existente en el sistema");
		Espectador espectador = getUsuario(nick);
		usuarios.remove(espectador);
	}

	/**
	 * LLamar a set del espectador
	 * 
	 * @param nombreApellidos
	 * @return
	 */
	public boolean actualizarNombreApellidosUsuario(String nick, String nombreApellidos) {
		return true;
	}

	/**
	 * LLamar a set del espectador
	 * 
	 * @param email
	 * @return
	 */
	public boolean actualizarEmailUsuario(String nick, String email) {
		return true;
	}

	/**
	 * 
	 * @param nick
	 * @param critica
	 */
	public void crearCritica(String nick, Critica critica) {

	}

	/**
	 * 
	 * @param nick
	 * @param critica
	 */
	public void borrarCritica(String nick, String titulo) {

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Critica> consultarCriticas() {
		return null;
	}

	/**
	 * 
	 * @param nick
	 * @param titulo
	 * @return
	 */
	public boolean votarCritica(String nick, String titulo) {
		return true;
	}

	/**
	 * 
	 * @param nick
	 * @return
	 */
	public ArrayList<Critica> consultarCriticasUsuario(String nick) {
		return null;
	}

	/**
	 * Función que devuelve la lista de todos los usuarios registrados en el sistema
	 * 
	 * @exception Lanza una excepción en caso de que la lista de usuarios en el
	 *                  sistema esté vacía
	 * @return Devuelve la lista de usuarios del sistema
	 * @author Ricardo Espantaleón Pérez
	 */
	public ArrayList<Espectador> getUsuarios() throws Exception {
		if (listaUsuariosVacia())
			throw new Exception("Lista de usuarios vacía");
		return usuarios;
	}

}
