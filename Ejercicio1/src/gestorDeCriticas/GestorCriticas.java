/**
 * 
 */
package gestorDeCriticas;

import java.util.ArrayList;
import java.util.Properties;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Clase con patrón de diseño Singleton dedicada a la gestión de usuarios y
 * críticas del sistema
 * 
 * @author Ricardo Espantaleón Pérez
 * @author Nicolás López Delgado
 * @see Documentación.pdf
 * @see java.util.ArrayList
 * @see Critica
 * @see Espectador
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

	// Fichero de propiedades el cual va a almacenar la dirección de los usuarios o
	// las criticas
	static final Properties ficheroPropiedades = new Properties();

	// Métodos

	/**
	 * Función que devuelve la instancia de la clase con la ruta de los datos de
	 * usuarios y los datos de las críticas
	 * 
	 * @param rutaDatosUsuarios Ruta del fichero de datos de usuarios
	 * @param rutaDatosCriticas Ruta del fichero de datos de críticas
	 * 
	 * @throws FileNotFoundException Lanza una excepción en caso de que no se haya
	 *                               encontrado el fichero de propiedades
	 * @throws Exception             Lanza una excepción genérica en caso de que
	 *                               haya sucedido un error genérico
	 * @return Devuelve la instancia de la clase
	 * @author Ricardo Espantaleón Pérez
	 * @deprecated Versión obsoleta, se hace uso de su versión mediante la lectura
	 *             del fichero de propeidades del proyecto
	 */
	public static GestorCriticas getInstance(String rutaDatosUsuarios, String rutaDatosCriticas)
			throws FileNotFoundException, Exception {
		try {
			exportarFicheroPropiedades(rutaDatosUsuarios, rutaDatosCriticas);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("La ruta indicada no ha sido encontrada");

		} catch (Exception e) {
			throw new Exception("Error al exportar el fichero de propiedades");
		}

		if (instance == null)
			instance = new GestorCriticas();

		return instance;
	}

	/**
	 * Función que devuelve la única instancia posible (singleton) de la clase,
	 * leyendo directamente los datos de las rutas desde el fichero de propiedades
	 * 
	 * @throws IOException Lanza una excepción en caso de no poder importar el
	 *                     fichero de propiedades
	 * @author Ricardo Espantaleón Pérez
	 * @return La instancia de la clase gestor
	 */
	public static GestorCriticas getInstance() throws IOException {
		if (instance == null)
			try {
				instance = new GestorCriticas();
			} catch (IOException e) {
				throw new IOException("Error al importar el fichero de propiedades");
			}

		return instance;
	}

	/**
	 * Constructor sin parámetros el cual lee el fichero de propiedades del programa
	 * para determinar la ruta de los datos de los usuarios y de las críticas del
	 * sistema
	 * 
	 * @throws IOException lanza una excepción en caso de no poder importar el
	 *                     fichero de propiedades del proyecto
	 * @author Ricardo Espantaleón Pérez
	 */
	private GestorCriticas() throws IOException, FileNotFoundException {
		this.usuarios = new ArrayList<Espectador>();
		this.criticas = new ArrayList<Critica>();

		try {
			importarFicheroPropiedades();
		} catch (IOException e) {
			throw new IOException("Error al importar el fichero de propiedades");

		}

		String rutaUsuarios = ficheroPropiedades.getProperty("rutaDatosUsuarios");
		String rutaCriticas = ficheroPropiedades.getProperty("rutaDatosCriticas");

		leerFicheroUsuarios(rutaUsuarios);
		leerFicherosCriticas(rutaCriticas);

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
	 * Función que comprueba si la lista de usuarios está vacía
	 * 
	 * @return Devuelve verdadero si la lista de usuarios está vacía
	 * @author Ricardo Espantaleón Pérez
	 */
	public boolean listaUsuariosVacia() {
		return usuarios.isEmpty();
	}

	/**
	 * Función que comprueba si la lista de críticas está vacía
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
	 * @exception Exception Lanza una excepción en caso de que no exista el usuario
	 *                      en el sistema
	 * 
	 * @param nick Nickname del usuario a recuperar
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
	 * Función que registra un usuario en el sistema, siempre y cuando no exista
	 * previamente
	 * 
	 * @param espectador Espectador a crear en el sistema
	 * @throws Exception Lanza una excepción en caso de que ya exista el usuario en
	 *                   el sistema
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
	 * @throws Exception Lanza una excepción en caso de que no exista el usuario en
	 *                   el sistema
	 * @param nick Nickname del usuario a eliminar
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
	 * @exception Exception Lanza una excepción en caso de que no exista el usuario
	 *                      en el sistema
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
	 * @exception Exception Lanza una excepción en caso de que no exista el usuario
	 *                      en el sistema
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
		try {
			usuarios.get(indiceUsuario).setEmail(email);
		} catch (Exception error) {
			throw new Exception("El formato del email es incorrecto");
		}
	}

	/**
	 * Función que comprueba si un usuario ha creado una crítica
	 * 
	 * @param critica Crítica a comprobar si ha sido creada previamente en el
	 *                sistema por parte de un usuario
	 * @return Verdadero si el usuario ya ha creado dicha crítica y falso en caso
	 *         contrario
	 * @author Ricardo Espantaleón
	 */
	private boolean criticaCreada(Critica critica) {
		ArrayList<Critica> criticasUsuario = getCriticasUsuario(critica.getAutor().getNick());

		if (criticasUsuario.isEmpty())
			return false;

		for (int i = 0; i < criticasUsuario.size(); ++i) {
			if (criticasUsuario.get(i).getTitulo().equalsIgnoreCase(critica.getTitulo()))
				return true;
		}

		return false;
	}

	/**
	 * La función añade una crítica nueva al vector
	 * 
	 * @param critica Crítica a agregar en el sistema
	 * @exception Exception Se lanza una excepción si la crítica ya existe en el
	 *                      sistema
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
	 * @param nick   Nickname del creador de la crítica
	 * @param titulo Título de la obra
	 * @exception Exception Salta si no existe una crítica de ese espectador con ese
	 *                      título
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
	 * Función que devuelve todas las críticas del gestor
	 *
	 * 
	 * @exception Exception Se lanza si la lista de críticas está vacía
	 * @author Nicolás López
	 * @return Devuelve el vector de críticas del sistema
	 */
	public ArrayList<Critica> getCriticas() throws Exception {
		if (listaCriticasVacia())
			throw new Exception("Lista de críticas vacía");

		return criticas;
	}

	/**
	 * Función que comprueba si existe una crítica en el sistema
	 * 
	 * @param nick   Nickname del autor de la crítica
	 * @param titulo Título de la critica a localizar
	 * @return Retorna verdadero en caso de que exista la crítica en el sistema y
	 *         falso en caso contrario
	 */
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
	 * Función que vota una crítica de otro autor
	 * 
	 * @param nick       Autor de la crítica
	 * @param titulo     Título a la que va diriga la crítica
	 * @param valoracion valor de la nota
	 * @exception Exception Se lanza si no existe el nick inicializado
	 * @exception Exception Se lanza si las valoraciones no están en rangos del 0 al
	 *                      10
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
			if (nick.equalsIgnoreCase(criticas.get(i).getNickAutor())
					&& titulo.equalsIgnoreCase(criticas.get(i).getTitulo())) {
				indiceCritica = i;
			}
		}
		criticas.get(indiceCritica).agregarValoracion(valoracion);
	}

	/**
	 * Función que devuelve las críticas de un usuario específico
	 * 
	 * @param nick Identificador de usuario
	 * @return Retorna el vector de críticas del usuario
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
	 * @exception Exception Lanza una excepción en caso de que la lista de usuarios
	 *                      en el sistema esté vacía
	 * @return Devuelve la lista de usuarios del sistema
	 * @author Ricardo Espantaleón Pérez
	 */
	public ArrayList<Espectador> getUsuarios() throws Exception {
		if (listaUsuariosVacia())
			throw new Exception("Lista de usuarios vacía");

		return usuarios;
	}

	/**
	 * Función que exporta al fichero de propiedades las rutas de los ficheros que
	 * contienen tanto los usuarios como las críticas del gestor
	 * 
	 * @param rutaDatosUsuarios Ruta virtual del fichero de usuarios
	 * @param rutaDatosCriticas Ruta virtual del fichero de críticas
	 * @throws Exception             Lanzará una excepción genérica en caso de no
	 *                               exportar el fichero de propiedades
	 * @throws FileNotFoundException Lanzará una excepción en caso de no encontrar
	 *                               la ruta indicada
	 * @throws Exception             Lanzará una excepción genérica en caso de no
	 *                               poder importar el fichero de propiedades
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	static public void exportarFicheroPropiedades(String rutaDatosUsuarios, String rutaDatosCriticas)
			throws Exception, FileNotFoundException {
		ficheroPropiedades.setProperty("rutaDatosUsuarios", rutaDatosUsuarios);
		ficheroPropiedades.setProperty("rutaDatosCriticas", rutaDatosCriticas);

		try (final OutputStream outputstream = new FileOutputStream("./config/gestorCriticas.properties");) {
			ficheroPropiedades.store(outputstream, "Archivo actualizado");
			outputstream.close();
		} catch (FileNotFoundException error) {
			throw new Exception("Error, el fichero de propiedades no ha sido encontrado");
		} catch (Exception error) {
			throw new Exception("Error, al exportar el fichero de propiedades");
		}

	}

	/**
	 * Función que importa el fichero de propiedades al gestor
	 * 
	 * @throws IOException Lanzará la excepción en caso de que no se haya podido
	 *                     importar
	 * 
	 * @see FileInputStream
	 * @author Ricardo Espantaleón Pérez
	 */
	static public void importarFicheroPropiedades() throws IOException {
		InputStream entrada = null;

		try {
			entrada = new FileInputStream("./config/gestorCriticas.properties");

			ficheroPropiedades.load(entrada);

		} catch (IOException error) {
			throw new IOException("Error al importar el fichero de propiedades");

		}
	}

	/**
	 * Función que lee un fichero con los usuarios en un formato específico
	 * 
	 * @param ruta Ruta del fichero de críticas
	 * @see File
	 * @see FileReader
	 * @see BufferedReader
	 * @author Nicolás López
	 */
	private void leerFicheroUsuarios(String ruta) {
		File archivo;
		FileReader fr;
		BufferedReader br;

		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				int index1 = linea.indexOf(",");
				int indexFinal = linea.lastIndexOf(",");
				String nick = linea.substring(0, index1);
				String nombre = linea.substring(index1 + 1, indexFinal);
				String email = linea.substring(indexFinal + 1);
				Espectador e = new Espectador(nick, nombre, email);
				usuarios.add(e);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error leyendo archivo" + e);
		}
	}

	/**
	 * Función que lee un fichero con las criticas en un formato específico
	 * 
	 * @param Ruta del fichero de críticas
	 * @see File
	 * @see FileReader
	 * @see BufferedReader
	 * @author Nicolás López
	 */
	private void leerFicherosCriticas(String ruta) {
		File archivo;
		FileReader fr;
		BufferedReader br;
		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				int index1 = linea.indexOf(",");
				int index2 = linea.indexOf(",", index1 + 1);
				int index3 = linea.indexOf(",", index2 + 1);
				int indexFinal = linea.lastIndexOf(",");

				String titulo = linea.substring(0, index1);
				int puntuacion = Integer.parseInt(linea.substring(index1 + 1, index2));
				String comentario = linea.substring(index2 + 1, index3);
				int indice = Integer.parseInt(linea.substring(index3 + 1, indexFinal));
				float valoracion = Float.parseFloat(linea.substring(indexFinal + 1));

				Espectador usuario = usuarios.get(indice);
				Critica c = new Critica(titulo, puntuacion, comentario, usuario);
				c.agregarValoracion(valoracion);
				criticas.add(c);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error leyendo archivo" + e);

		}

	}

	/**
	 * Función que escribe un fichero con las críticas en un formato específico
	 * 
	 * @param ruta La ruta del fichero de críticas
	 * @see File
	 * @see FileReader
	 * @see BufferedReader
	 * @author Nicolás López
	 */
	public void escribirFicheroCriticas(String ruta) {
		File archivo;
		FileWriter fw;
		BufferedWriter bw;

		try {
			archivo = new File(ruta);
			fw = new FileWriter(archivo);
			bw = new BufferedWriter(fw);
			if (criticas.get(0).getValoracion().size() < 1)
				fw.write(criticas.get(0).getTitulo() + "," + (int) criticas.get(0).getPuntuacion() + ","
						+ criticas.get(0).getComentario() + "," + 0 + "," + 0);
			else {
				fw.write(criticas.get(0).getTitulo() + "," + (int) criticas.get(0).getPuntuacion() + ","
						+ criticas.get(0).getComentario() + "," + 0 + "," + criticas.get(0).hacerMediaValoracion());
			}
			for (int i = 1; i < criticas.size(); i++) {
				if (criticas.get(i).getValoracion().size() < 1)
					fw.write("\n" + criticas.get(i).getTitulo() + "," + (int) criticas.get(i).getPuntuacion() + ","
							+ criticas.get(i).getComentario() + "," + i + "," + 0);
				else {
					fw.write("\n" + criticas.get(i).getTitulo() + "," + (int) criticas.get(i).getPuntuacion() + ","
							+ criticas.get(i).getComentario() + "," + i + "," + criticas.get(i).hacerMediaValoracion());
				}
			}
			fw.close();
			bw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error escribiendo archivo" + e);
		}
	}

	/**
	 * Lee un fichero con las criticas en un formato específico
	 * 
	 * @param ruta La ruta del fichero de usuarios
	 * @see File
	 * @see FileReader
	 * @see BufferedReader
	 * @author Nicolás López
	 */
	public void escribirFicheroUsuarios(String ruta) {
		File archivo;
		FileWriter fw;
		BufferedWriter bw;

		try {
			archivo = new File(ruta);
			fw = new FileWriter(archivo);
			bw = new BufferedWriter(fw);
			fw.write(usuarios.get(0).getNick() + "," + usuarios.get(0).getNombreApellidos() + ","
					+ usuarios.get(0).getEmail());
			for (int i = 1; i < usuarios.size(); i++) {
				fw.write("\n" + usuarios.get(i).getNick() + "," + usuarios.get(i).getNombreApellidos() + ","
						+ usuarios.get(i).getEmail());
			}
			fw.close();
			bw.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error escribiendo archivo" + e);
		}
	}
}
