package gestorDeEspectaculos;


import java.util.ArrayList;

public class EspectaculoPuntual extends Espectaculo {

	// Variables
	String titulo;
	String categorias;
	String descripcion;
	ArrayList<Sesion> sesiones;

	// Métodos

	/**
	 * Constructor parametrizado de la clase EspectaculoPuntual
	 * 
	 * @param titulo      Cadena que contiene el título del espectáculo
	 * @param categorias  Cadena que contiene las categorias del espectáculo
	 * @param descripcion Cadena que contiene la descrpcion del espectáculo
	 * @param sesiones    Clase de tipo Sesion que contiene la fecha y localidades
	 *                    de lasesion de un espectaculo
	 * @author Enrique Estevez Mayoral
	 */
	public EspectaculoPuntual(String titulo, String categorias, String descripcion, Sesion sesion) {
		super (titulo,categorias,descripcion);
		this.titulo = titulo;
		this.categorias = categorias;
		this.descripcion = descripcion;
		this.sesiones = new ArrayList<Sesion>();
		sesiones.add(sesion);
	}
	public ArrayList<Sesion> getSesiones() {
		return sesiones;
	}
	
}
