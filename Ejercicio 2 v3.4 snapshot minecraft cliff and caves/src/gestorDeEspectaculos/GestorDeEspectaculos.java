package gestorDeEspectaculos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase con patrón de diseño Singleton dedicada a la gestión del sistema
 * 
 * @author Ricardo Espantaleón Pérez
 * @author Nicolás López Delgado
 * @see Documentación.pdf
 */
public class GestorDeEspectaculos {

	// Instancia del patrón de diseño singleton
	private static GestorDeEspectaculos instance = null;
	// Lista de usuarios
	private ArrayList<Espectador> usuarios;
	// Lista de críticas, solo podrá existir una crítica si existe su
	// correspondiente autor en el vector de usuarios
	// sino lanzo excepción
	private ArrayList<Critica> criticas;
	// Lista de Espectaculos
	private ArrayList<Espectaculo> espectaculos;
	// Fichero de propiedades el cual va a almacenar la dirección de los usuarios o
	// las criticas
	static final Properties ficheroPropiedades = new Properties();
	// factory
	ConcreteFactory factoria = new ConcreteFactory();

	// Métodos

	/**
	 * Función que devuelve la instancia de la clase
	 * 
	 * @return Devuelve la instancia de la clase
	 * @author Ricardo Espantaleón Pérez
	 */
	public static GestorDeEspectaculos getInstance(String rutaDatosUsuarios, String rutaDatosCriticas)
			throws FileNotFoundException, Exception {
		try {
			exportarFicheroPropiedades(rutaDatosUsuarios, rutaDatosCriticas);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("La ruta indicada no ha sido encontrada");

		} catch (Exception e) {
			throw new Exception("Error al exportar el fichero de propiedades");
		}

		if (instance == null)
			instance = new GestorDeEspectaculos();

		return instance;
	}

	/**
	 * Función que devuelve la única instancia posible (singleton) de la clase
	 * 
	 * @return La instancia de la clase gestor
	 */
	public static GestorDeEspectaculos getInstance() throws IOException, FileNotFoundException {
		if (instance == null)
			try {
				instance = new GestorDeEspectaculos();
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
	 * @author Ricardo Espantaleón Pérez
	 */
	public GestorDeEspectaculos() throws IOException, FileNotFoundException {
		this.usuarios = new ArrayList<Espectador>();
		this.criticas = new ArrayList<Critica>();
		this.espectaculos = new ArrayList<Espectaculo>();
		/*
		 * try { importarFicheroPropiedades(); } catch (IOException e) { throw new
		 * IOException("Error al importar el fichero de propiedades");
		 * 
		 * }
		 * 
		 * String rutaUsuarios = ficheroPropiedades.getProperty("rutaDatosUsuarios");
		 * String rutaCriticas = ficheroPropiedades.getProperty("rutaDatosCriticas");
		 * 
		 * leerFicheroUsuarios(rutaUsuarios); leerFicherosCriticas(rutaCriticas);
		 */
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
	 * Lee un fichero con los usuarios en un formato específico
	 * 
	 * @param ruta la ruta del archivo
	 * @exception Se lanza si no se encuentra fichero
	 * @return
	 * @author Nicolás López
	 */
	private void leerFicheroUsuarios(String ruta) {
		// TODO
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
	 * Lee un fichero con las criticas en un formato específico
	 * 
	 * @param ruta la ruta de un fichero
	 * @exception Se lanza cuando la ruta no corresponde a un fichero
	 * @return
	 * @author Nicolás López
	 */
	private void leerFicherosCriticas(String ruta) {
		// TODO
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
				int indiceEspectaculos = Integer.parseInt(linea.substring(index3 + 1, indexFinal));
				float valoracion = Float.parseFloat(linea.substring(indexFinal + 1));

				Espectador usuario = usuarios.get(indiceEspectaculos);
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
	 * Escribe un fichero con las criticas en un formato específico
	 * 
	 * @param ruta la ruta de un fichero
	 * @exception Se lanza cuando la ruta no corresponde a un fichero
	 * @return
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
	 * @param ruta la ruta de un fichero
	 * @exception Se lanza cuando la ruta no corresponde a un fichero
	 * @return
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

	/**
	 * Función que comprueba si está vacía una lista
	 * 
	 * @return true si está vacía, false si no
	 * @author Nicolás López
	 */
	public boolean listaEspectaculosVacia() {
		return espectaculos.isEmpty();
	}

	/**
	 * Función que devuelve todos los espectaculos
	 * 
	 * @return un array de espectaculos
	 * @throws Exception si la lista está vacía
	 * @author Nicolás López
	 */
	public ArrayList<Espectaculo> getEspectaculos() throws Exception {
		if (listaEspectaculosVacia())
			throw new Exception("Lista vacía");
		return espectaculos;
	}

	/**
	 * Función que devuelve los espectáculos que hay según su titulo
	 * 
	 * @param titulo el titulo del espectaculo
	 * @return un espectaculo
	 * @throws Exception Se lanza cuando la lista está vacía
	 * @author Nicolás López
	 */
	public ArrayList<Espectaculo> getEspectaculosTitulo(String titulo) throws Exception {
		if (listaEspectaculosVacia())
			throw new Exception("Lista vacía");
		ArrayList<Espectaculo> listaRetorno = new ArrayList<Espectaculo>();
		for (int i = 0; i < espectaculos.size(); i++) {
			if (titulo.equalsIgnoreCase(espectaculos.get(i).getTitulo()))
				listaRetorno.add(espectaculos.get(i));

		}
		return listaRetorno;
	}

	/**
	 * Función que crea un espectáculo puntual
	 * 
	 * @param titulo      el titulo del espectaculo
	 * @param categorias  la categoria del espectáculo
	 * @param descripcion descripcion del espectaculo
	 * @param date        la fecha de la sesión del espectáculo
	 * @return un espectaculo
	 * @author Nicolás López
	 * @throws Exception
	 */
	public void crearEspectaculoPuntual(String titulo, String categorias, String descripcion, Date fecha,
			int localidades) throws Exception {
		if (buscarEspectaculoTitulo(titulo) != -1)
			throw new Exception("Error, ya existe el espectáculo");

		try {
			espectaculos.add(factoria.crearEspectaculoPuntual(titulo, categorias, descripcion, fecha, localidades));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Función que crea un espectáculo de pase múltiple
	 * 
	 * @param titulo      el titulo del espectaculo
	 * @param categorias  la categoria del espectáculo
	 * @param descripcion descripcion del espectaculo
	 * @param fechas      la lista de fechas del espectaculo
	 * @param localidades las localidades que tiene el sitio donde se celebra
	 * @return un espectaculo
	 * @author Nicolás López
	 * @throws Exception
	 */
	public void crearEspectaculoPaseMultiple(String titulo, String categorias, String descripcion,
			ArrayList<Date> fechas, int localidades) throws Exception {
		if (buscarEspectaculoTitulo(titulo) != -1)
			throw new Exception("Error, ya existe el espectáculo");
		try {
			espectaculos
					.add(factoria.crearEspectaculoPaseMultiple(titulo, categorias, descripcion, fechas, localidades));
		} catch (Exception error) {
			throw new Exception(error);
		}

	}

	/**
	 * Función que crea un espectáculo de temporada
	 * 
	 * @param titulo      el titulo del espectaculo
	 * @param categorias  la categoria del espectáculo
	 * @param descripcion descripcion del espectaculo
	 * @param fechas      la lista de fechas del espectaculo
	 * @param localidades las localidades que tiene el sitio donde se celebra
	 * @return un espectaculo
	 * @author Nicolás López
	 * @throws Exception
	 */
	public void crearEspectaculoTemporada(String titulo, String categorias, String descripcion, Date fechaInicio,
			Date fechaFin, int localidades) throws Exception {
		if (buscarEspectaculoTitulo(titulo) != -1)
			throw new Exception("Error, ya existe el espectáculo");
		try {
			espectaculos.add(factoria.crearEspectaculoTemporada(titulo, categorias, descripcion, fechaInicio, fechaFin,
					localidades));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Función vende entradas
	 * 
	 * @param titulo     el titulo del espectaculo
	 * @param fecha      la fecha del espectaculo
	 * @param espectador el espectador que compra la entrada
	 * @para indice del espectador que compra la entrada
	 * @author Nicolás López
	 */
	public void venderEntradas(String titulo, Date fecha, Espectador espectador) throws Exception {
		int indiceEspectaculo, indiceSesion;
		if (buscarEspectaculoTitulo(titulo) == -1)
			throw new Exception("no hay espectáculos con ese título");
		indiceEspectaculo = buscarEspectaculoTitulo(titulo);
		if (buscarFecha(indiceEspectaculo, fecha) == -1)
			throw new Exception("No existe esa sesión");
		indiceSesion = buscarFecha(indiceEspectaculo, fecha);
		espectaculos.get(indiceEspectaculo).getSesiones().get(indiceSesion).setLocalidades(
				espectaculos.get(indiceEspectaculo).getSesiones().get(indiceSesion).getLocalidades() - 1);
		espectaculos.get(indiceEspectaculo).getSesiones().get(indiceSesion).entradaVendida();
	}

	/**
	 * Función que busca un espectaculo por su título
	 * 
	 * @param titulo el titulo del espectaculo
	 * @return el indice del vector donde se encuentra o -1 si no se encuentra
	 * @author Nicolás López
	 */
	public int buscarEspectaculoTitulo(String titulo) {
		for (int i = 0; i < espectaculos.size(); i++) {
			if (titulo.equalsIgnoreCase(espectaculos.get(i).getTitulo()))
				return i;
		}

		return -1;
	}

	/**
	 * Función que busca un espectaculo por su título
	 * 
	 * @param titulo el titulo del espectaculo
	 * @return el indice del vector donde se encuentra o -1 si no se encuentra
	 * @author Nicolás López
	 */
	public ArrayList<Espectaculo> buscarEspectaculoCategoria(String categorias) throws Exception {
		ArrayList<Espectaculo> categoria = new ArrayList<Espectaculo>();
		for (int i = 0; i < espectaculos.size(); i++) {
			if (categorias.equalsIgnoreCase(espectaculos.get(i).getCategorias()))
				categoria.add(espectaculos.get(i));
		}
		if (categoria.isEmpty())
			throw new Exception("Error, no existe ningún espectáculo con esa categoria");
		return categoria;
	}

	/**
	 * Función que busca una sesion en concreto dentro de un espectaculo a partir de
	 * su fecha
	 * 
	 * @param indice el indice en el que buscar
	 * @param titulo el titulo del espectaculo
	 * @return el indice del vector donde se encuentra o -1 si no se encuentra
	 * @author Nicolás López
	 */
	public int buscarFecha(int indice, Date fecha) {
		for (int i = 0; i < espectaculos.get(indice).getSesiones().size(); i++) {
			if (fecha.compareTo(espectaculos.get(indice).getSesiones().get(i).getFecha()) == 0) {
				return i;
			}
		}
		return -1;
	}

	public int consultarEntradasVendidas(Sesion sesion) {
		return sesion.getVendidas();
	}

	/**
	 * Función que comprueba que existe un espectáculo en el gestor
	 * 
	 * @param titulo Título del espectaculo a comprobar si existe
	 * @return Retorna verdadero en caso de que exista el espectáculo y falso en
	 *         caso contrario
	 */
	public boolean existeEspectaculo(String titulo) {
		for (int i = 0; i < espectaculos.size(); ++i) {
			if (espectaculos.get(i).getTitulo().equalsIgnoreCase(titulo))
				return true;
		}

		return false;
	}

	/**
	 * Función que elimina un espectáculo y todas sus sesiones en cuestión
	 * 
	 * @param titulo Título del espectáculo a eliminar
	 * @throws Exception Lanza una excepción en caso de que el espectaculo no exista
	 * 
	 * @author Ricardo Espantaleón
	 */
	public void cancelarTodasSesionesEspectaculo(String titulo) throws Exception {
		if (buscarEspectaculoTitulo(titulo) == -1)
			throw new Exception("El espectáculo no existe en el sistema\n\n");

		for (int i = 0; i < espectaculos.size(); ++i) {
			if (espectaculos.get(i).getTitulo().equalsIgnoreCase(titulo)) {
				espectaculos.get(i).eliminaSesiones();
				break;
			}

		}
	}

	/**
	 * Función que comprueba si existe una sesión en el sistema
	 * 
	 * @param sesion Sesión a comprobar si existe en el sistema
	 * @return Verdadero en caso de que exista y falso en caso contrario
	 * 
	 * @author Ricardo Espantaleón
	 */
	public boolean existeSesion(Sesion sesion) {
		ArrayList<Sesion> sesiones = null;

		for (int i = 0; i < espectaculos.size(); ++i) {
			sesiones = espectaculos.get(i).getSesiones();

			for (int j = 0; j < sesiones.size(); ++j) {
				if (sesiones.get(j).equals(sesion))
					return true;
			}
		}

		return false;
	}

	/**
	 * Función que cancela una sesión en particular del sistema
	 * 
	 * @param titulo Título del espectáculo del cual queremos borrar una sesión
	 * @param sesion Sesión en cuestión que queremos borrar
	 * @throws Exception Lanzará una excepción en caso de que no exista el
	 *                   espectáculo o la sesión
	 */
	public void cancelarSesionEspectaculo(String titulo, Date fecha) throws Exception {
		if (buscarEspectaculoTitulo(titulo) == -1)
			throw new Exception("El espectáculo no existe en el sistema\n\n");

		int indiceEspetaculo = buscarEspectaculoTitulo(titulo);
		if (buscarFecha(indiceEspetaculo, fecha) == -1)
			throw new Exception("La sesión no existe en el sistema\n\n");

		for (int i = 0; i < espectaculos.size(); ++i) {
			if (espectaculos.get(i).getTitulo().equalsIgnoreCase(titulo)) {
				espectaculos.get(i).eliminaSesion(fecha);
				break;
			}
		}
	}

	/**
	 * Función que devuelve un espectáculo en particular
	 * 
	 * @param titulo Título del espectáculo a buscar
	 * @return Retorna la instancia el espectáculo
	 * @throws Exception Lanza una excepción en caso de que no exista el espectáculo
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public Espectaculo getEspectaculo(String titulo) throws Exception {
		if (!existeEspectaculo(titulo))
			throw new Exception("El espectáculo no existe en el sistema\n\n");

		Espectaculo e = null;

		for (int i = 0; i < espectaculos.size(); ++i) {
			if (espectaculos.get(i).getTitulo().equalsIgnoreCase(titulo)) {
				e = espectaculos.get(i);
				break;
			}
		}

		return e;
	}

	/**
	 * Función que actualiza los datos de un espectáculo
	 *
	 * @implSpec Importante inicializar a null aquellas variables que no queramos
	 *           actualizar
	 * @param Titulo      Título del espectáculo a buscar del cual queramos
	 *                    actualizar sus datos
	 * @param categoria   Categoría nueva a asignar
	 * @param descripcion Descripción nueva a asignar
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public void actualizarDatosEspectaculo(String titulo, String categoria, String descripcion) throws Exception {
		int indiceEspectaculo;

		try {
			indiceEspectaculo = buscarEspectaculoTitulo(titulo);
		} catch (Exception error) {
			throw new Exception(error);
		}

		if (categoria != "")
			espectaculos.get(indiceEspectaculo).setCategorias(categoria);

		if (descripcion != "")
			espectaculos.get(indiceEspectaculo).setCategorias(categoria);
	}

	/**
	 * Función que me devuelve los espectáculos disponibles de una categoría
	 * 
	 * @param categoria Categoría de espectáculos a buscar
	 * @return Retorna un ArrayList de espectáculos de una categoría en concreto
	 * @see ArrayList
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public ArrayList<Espectaculo> espectaculosDisponibles(String categoria) {
		ArrayList<Espectaculo> espectaculosDisponibles = new ArrayList<Espectaculo>();

		for (int i = 0; i < espectaculos.size(); ++i) {
			if (espectaculos.get(i).getCategorias().equalsIgnoreCase(categoria)
					&& espectaculos.get(i).entradasDisponibles())
				espectaculosDisponibles.add(espectaculos.get(i));
		}

		return espectaculosDisponibles;
	}

	/**
	 * Función que me devuelve todos los espectáculos disponibles
	 * 
	 * @return Retorna un ArrayList de espectáculos de una categoría en concreto
	 * @see ArrayList
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public ArrayList<Espectaculo> espectaculosDisponibles() {
		ArrayList<Espectaculo> espectaculosDisponibles = new ArrayList<Espectaculo>();

		for (int i = 0; i < espectaculos.size(); ++i) {
			if (espectaculos.get(i).entradasDisponibles())
				espectaculosDisponibles.add(espectaculos.get(i));
		}

		return espectaculosDisponibles;
	}

	/**
	 * Función que me retorna todas las críticas de un espectáculo
	 * 
	 * @param titulo Título del espectáculo a encontrar
	 * @return Retorna un arrayList de todas las críticas asociados al espectáculo
	 * @throws Exception Lanza una excepción en caso de que el espectáculo no exista
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public ArrayList<Critica> consultarCriticas(String titulo) throws Exception {
		ArrayList<Critica> criticasEspectaculo = new ArrayList<Critica>();

		if (!existeEspectaculo(titulo))
			throw new Exception("El espectáculo introducido no existe en el sistema\n\n");

		for (int i = 0; i < criticas.size(); ++i) {
			if (criticas.get(i).getTitulo().equalsIgnoreCase(titulo))
				criticasEspectaculo.add(criticas.get(i));
		}

		return criticasEspectaculo;
	}

	/*
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * 
	 * FUNCIONES A REALIZAR DEL MENÚ MAIN PARA QUE FUNCIONE EL PROGRAMA, LO DEMÁS
	 * SERÁ OBSOLETO
	 * 
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 */

	/**
	 * Función que se encarga de guardar todos los datos pertinentes de los vectores
	 * en los ficheros
	 * 
	 * @throws Exception Lanzará una excepción en caso de que suceda cualquier error
	 */
	public void guardarFicheros() throws Exception {

	}

	/**
	 * Función que se encarga de comprobar si la password que está almacenada en el
	 * fichero de propiedades coincide con la password introducida
	 * 
	 * @implNote Es recomendable crear una variable password en el gestor, la cual
	 *           se inicialize una vez el constructor lea el fichero de propiedades
	 *           para no tener que complicarse con la password
	 * 
	 * @param password Contraseña a comprobar si coincide con la de administración
	 * @return
	 */
	public boolean passwordCorrecta(String password) {

		return password.equalsIgnoreCase("PW1234");
	}

	// Revisar la clase Critica, la cual recibe un espectaculo como variable, no es
	// eficiente, mejor asignar la variable titulo a espectaculo.titulo

	/**
	 * La función añade una crítica nueva al vector
	 * 
	 * @param critica la crítica ya inicializada
	 * @exception Se lanza si la crítica ya existe o si el espectáculo asociado
	 *               todavía no se ha celebrado
	 * @return
	 * @author Nicolás López
	 * 
	 */
	public void crearCritica(Critica critica) throws Exception {
		if (criticaCreada(critica))
			throw new Exception("Crítica ya existente en el sistema");

		int indiceEspectaculo = buscarEspectaculoTitulo(critica.getTitulo());

		if (indiceEspectaculo == -1)
			throw new Exception("No existe ningún espectáculo con ese título en el sistema\n\n");

		for (int i = 0; i < espectaculos.get(indiceEspectaculo).getSesiones().size(); i++) {

			if (!fechaMayorActual(espectaculos.get(indiceEspectaculo).getSesiones().get(i).getFecha())) {
				// Por tanto no se ha celebrado
				criticas.add(critica);

			} else
				throw new Exception("La crítica debe crearse una vez haya ocurrido el espectáculo\n\n");
		}
	}

	// HACER LA FUNCIÓN ABSTRACTE GENÉRICA PARA LA PRIMERA OPCIÓN DEL MENU USUARIO
	// ADMIN

	/**
	 * La función devuelve el número de entradas de un espectáculo
	 * 
	 * @param titulo
	 * @param fecha
	 * @return el número de entradas de dicho espectáculo
	 * @throws Exception Lanza una excepción si no encuentra el espectáculo
	 */
	public int getNumeroEntradas(String titulo, Date fecha) throws Exception {
		if (buscarEspectaculoTitulo(titulo) == -1) {
			throw new Exception("No existe espectaculo");
		}

		int indiceEspectaculo = buscarEspectaculoTitulo(titulo);
		if (buscarFecha(indiceEspectaculo, fecha) == -1) {
			throw new Exception("No existe esa fecha");
		}
		int indiceFecha = buscarFecha(indiceEspectaculo, fecha);

		return espectaculos.get(indiceEspectaculo).getSesiones().get(indiceFecha).getVendidas();
	}

	/**
	 * La función devuelve el número de localidades de un espectáculo
	 * 
	 * @param titulo
	 * @param fecha
	 * @return el número de localidades de dicho espectáculo
	 * @throws Exception Lanza una excepción si no encuentra el espectáculo
	 */
	public int getNumeroLocalidades(String titulo, Date fecha) throws Exception {
		if (buscarEspectaculoTitulo(titulo) == -1) {
			throw new Exception("No existe espectaculo");
		}
		int indiceEspectaculo = buscarEspectaculoTitulo(titulo);
		if (buscarFecha(indiceEspectaculo, fecha) == -1) {
			throw new Exception("No existe esa fecha");
		}
		int indiceFecha = buscarFecha(indiceEspectaculo, fecha);

		return espectaculos.get(indiceEspectaculo).getSesiones().get(indiceFecha).getLocalidades();

	}

	/**
	 * Función que me devuelve un próximo espectáculo
	 * 
	 * @param titulo
	 * @return
	 * @throws Exception Lanza excepción si no existe
	 * @pre Debe tener una fecha superior a la actual
	 * @pre Debe tener un número de entradas > 0
	 */
	public ArrayList<Sesion> proximoEspectaculo(String titulo) throws Exception {
		if (buscarEspectaculoTitulo(titulo) == -1) {
			throw new Exception("No existe el espectáculo");
		}
		int indice = buscarEspectaculoTitulo(titulo);
		ArrayList<Sesion> sesionesFecha = new ArrayList<Sesion>();
		for (int i = 0; i < espectaculos.get(indice).getSesiones().size(); i++) {
			if (fechaMayorActual(espectaculos.get(indice).getSesiones().get(i).getFecha()) == true) {
				sesionesFecha.add(espectaculos.get(indice).getSesiones().get(i));
			}
		}
		return sesionesFecha;
	}

	// MIRAR ESPECTACULO DONDE TENGO QUE HACER GETFECHATOSTRING

	/**
	 * Función que filtra espectáculos por su categoría
	 * 
	 * @param categoria la categoria del espectáculo
	 * @return un espectaculo
	 * @author Nicolás López
	 * @pre Que exista la categoría
	 * @pre Que la fecha del espectaculo sea posterior a la actual
	 * @pre Que debe tener un numero de entradas > 0
	 */
	public ArrayList<Espectaculo> getEspectaculosCategoria(String categoria) throws Exception {
		ArrayList<Espectaculo> retorno = new ArrayList<Espectaculo>();

		for (int i = 0; i < espectaculos.size(); i++) {
			if (categoria.equalsIgnoreCase(espectaculos.get(i).getCategorias()))
				retorno.add(espectaculos.get(i));
		}

		if (retorno.isEmpty())
			throw new Exception("No existe ningún espectáculo asociado a esa categoría\n\n");

		return retorno;
	}

	/**
	 * Función que compara 2 fechas, la actual y la del espectáculo
	 * 
	 * @param fechaEspectaculo Fecha de la sesion que se quiere comprobar
	 * @return Verdadero si la fecha del espectáculo es mayor a la actual
	 * @author Enrique Estevez Mayoral
	 */
	static public boolean fechaMayorActual(Date fechaEspectaculo) {

		Date fechaActual = new Date();

		if (fechaEspectaculo.compareTo(fechaActual) > 0)
			return true;

		return false;
	}

	/**
	 * Función que compara 2 fechas, la actual y la del espectáculo
	 * 
	 * @param fechaEspectaculo Fecha de la sesion que se quiere comprobar
	 * @return Verdadero si la fecha del espectáculo es menor a la actual
	 * @author Enrique Estevez Mayoral
	 */
	static public boolean fechaMenorActual(Date fechaEspectaculo) {

		Date fechaActual = new Date();

		if (fechaEspectaculo.compareTo(fechaActual) < 0)
			return true;

		return false;
	}

}
