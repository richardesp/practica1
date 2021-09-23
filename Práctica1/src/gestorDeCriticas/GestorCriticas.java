/**
 * 
 */
package gestorDeCriticas;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Singleton !!!
 * 
 * @author ricardo
 *
 */
public class GestorCriticas {

	// Variables
	private static GestorCriticas instance = null;
	private ArrayList<Espectador> usuarios;
	private ArrayList<Critica> criticas;

	// Métodos
	public static GestorCriticas getInstance() {
		if (instance == null)
			instance = new GestorCriticas();
		return instance;
	}
	
	/**
	 * Registra un usuario en el sistema (comprueba que no exista ese nick)
	 * @author
	 * @param
	 * @return
	 */
	public boolean crearUsuario(Espectador espectador) {
		return true;
	}
	
	/**
	 * Función que elimina un usuario (retorna falso si no existe y true en caso contrario)
	 * @param nick
	 * @return
	 */
	public boolean eliminarUsuario(String nick) {
		return true;
	}
	
	/**
	 * Función que consulta un usuario y si no existe lanza una excepción
	 * @param nick
	 * @return
	 */
	public Espectador consultarUsuario(String nick) /*throw Exception*/ {
		return null;
	}
	
	/**
	 * LLamar a set del espectador
	 * @param nombreApellidos
	 * @return
	 */
	public boolean actualizarNombreApellidosUsuario(String nick, String nombreApellidos) {
		return true;
	}
	
	/**
	 * LLamar a set del espectador
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
	 * @return
	 */
	private boolean existeNick(String nick) {
		return true;
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
	public boolean votarCritica(String nick, string titulo) {
		
	}
	
	/**
	 * 
	 * @param nick
	 * @return
	 */
	public ArrayList<Critica> consultarCriticasUsuario(String nick) {
		return null;
	}
	
	
	
}
