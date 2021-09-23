/**
 * 
 */
package gestorDeCriticas;

/**
 * @author Ricardo Espantaleón Pérez
 *
 */
public class Espectador {

	// Variables
	private String nombreApellidos;
	private String nick;
	private String email;

	// Métodos
	Espectador(String nick) {
		this.nick = nick;
		// ...
	}

	String getNombreApellidos() {
		return this.nombreApellidos;
	}

	// TODO (hacer gets y sets)

}
