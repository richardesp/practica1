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
			if (nick.equals(usuarios.get(index).getNick()))
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
		return usuarios.isEmpty();
	}

	/**
	 * 
	 * @return Devuelve verdadero si la lista de críticas está vacía
	 * @author Ricardo Espantaleón Pérez
	 */
	public boolean listaCriticasVacia() {
		return criticas.isEmpty();
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

		Espectador e = null;

		for (int index = 0; index < usuarios.size(); ++index) {
			if (nick.equals(usuarios.get(index).getNick())) {
				e = usuarios.get(index);
				break;
			}
		}

		return e;
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
	 * La función cambia el nombre y apellidos a un usuario
	 * 
	 * @param nombreApellidos el nombre nuevo que se quiere cambiar
	 * @param nick            identificador del usuario
	 * @return
	 * @exception Lanza una excepción en caso de que no exista el usuario en el
	 *                  sistema
	 * @author Nicolás López Delgado
	 */
	public void actualizarNombreApellidosUsuario(String nick, String nombreApellidos) throws Exception {
		if (!existeNick(nick))
			throw new Exception("Usuario no existente en el sistema");
		int indiceUsuario = -1;
		for (int i = 0; i < usuarios.size(); i++) {
			if (nick.equalsIgnoreCase(usuarios.get(i).getNick())) {
				indiceUsuario = i;
				break;
			}
		}
		usuarios.get(indiceUsuario).setNombreApellidos(nombreApellidos);

	}

	/**
	 * La funcion actualiza el email de un usuario
	 * 
	 * @param email (El email que se quiere cambiar)
	 * @param nick  identificador de usuario
	 * @return true si se ha podido crear y false si no se consiguió
	 * @exception Lanza una excepción en caso de que no exista el usuario en el
	 *                  sistema
	 * @author Nicolás López
	 */
	public void actualizarEmailUsuario(String nick, String email) throws Exception {
		if (!existeNick(nick))
			throw new Exception("Usuario no existente en el sistema");
		int indiceUsuario = -1;
		for (int i = 0; i < usuarios.size(); i++) {
			if (nick.equalsIgnoreCase(usuarios.get(i).getNick())) {
				indiceUsuario = i;
			}
		}
		usuarios.get(indiceUsuario).setEmail(email);
	}

	private boolean criticaCreada(Critica critica) {
		ArrayList<Critica> criticasUsuario = getCriticasUsuario(critica.getAutor().getNick());

		for (int i = 0; i < criticasUsuario.size(); ++i) {
			if (criticasUsuario.get(i).getTitulo().equalsIgnoreCase(critica.getTitulo()))
				return true;
		}

		return false;
	}

	/**
	 * La función añade una crítica nueva al vector
	 * 
	 * @param critica la crítica ya inicializada
	 * @exception Se lanza si la crítica ya existe
	 * @return
	 * @author Nicolás López
	 * 
	 */
	public void crearCritica(Critica critica) throws Exception {
		if (criticaCreada(critica))
			throw new Exception("Crítica ya existente en el sistema");
		criticas.add(critica);
	}

	/**
	 * La función borra una critica del vector criticas
	 * 
	 * @param espectador el espectador al que pertenece la crítica
	 * @param titulo     el titulo de la crítica
	 * @return
	 * @exception Salta si no existe una crítica de ese espectador con ese título
	 * @author Nicolás López
	 */
	public void borrarCritica(String nick, String titulo) throws Exception {
		if (!existeCritica(nick, titulo))
			throw new Exception("La crítica no existe");

		int indiceCritica = -1;
		for (int i = 0; i < criticas.size(); i++) {
			if (nick.equalsIgnoreCase(criticas.get(i).getNickAutor())
					&& titulo.equalsIgnoreCase(criticas.get(i).getTitulo())) {
				indiceCritica = i;
			}
		}
		criticas.remove(indiceCritica);
	}

	/**
	 * Devuelve todas las críticas del gestor
	 *
	 * 
	 * @exception Se lanza si la lista de críticas está vacía
	 * @author Nicolás López
	 * @return un vector con las críticas
	 */
	public ArrayList<Critica> getCriticas() throws Exception {
		if (listaCriticasVacia())
			throw new Exception("Lista de críticas vacía");

		return criticas;
	}

	private boolean existeCritica(String nick, String titulo) {
		if (listaCriticasVacia())
			return false;

		for (int i = 0; i < criticas.size(); ++i) {
			if (criticas.get(i).getTitulo().equalsIgnoreCase(titulo)
					&& criticas.get(i).getNickAutor().equalsIgnoreCase(nick))
				return true;
		}

		return false;
	}

	/**
	 * Vota una crítica de otro autor
	 * 
	 * @param nick       Autor de la crítica
	 * @param titulo     Título a la que va diriga la crítica
	 * @param valoracion valor de la nota
	 * @exception Se lanza si no existe el nick inicializado
	 * @exception Se lanza si las valoraciones no están en rangos del 0 al 10
	 * @return
	 * @author Ricardo Espantaleón
	 * @author Nicolás López
	 */
	public void votarCritica(String nick, String titulo, float valoracion) throws Exception {
		if (!existeNick(nick))
			throw new Exception("Usuario no existente en el sistema");

		if (valoracion < 0 || valoracion > 10)
			throw new Exception("Valoraciones deben ser un flotante entre 0 y 10");

		int indiceCritica = -1;
		for (int i = 0; i < criticas.size(); i++) {
			if (nick.equalsIgnoreCase(criticas.get(i).getNickAutor()) && titulo.equalsIgnoreCase(criticas.get(i).getTitulo())) {
				indiceCritica = i;
			}
		}
		criticas.get(indiceCritica).agregarValoracion(valoracion);
	}

	/**
	 * Función que devuelve las críticas de un usuario específico
	 * 
	 * @param nick el identificador de usuario
	 * @return un vector con las críticas del usuario
	 * @author Nicolás López
	 */
	public ArrayList<Critica> getCriticasUsuario(String nick) {
		ArrayList<Critica> criticasUsuario = new ArrayList<Critica>();
		for (int i = 0; i < criticas.size(); i++) {
			if (nick.equalsIgnoreCase(criticas.get(i).getNickAutor())) {
				criticasUsuario.add(criticas.get(i));
			}

		}
		return criticasUsuario;
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
