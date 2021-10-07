package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EspectaculoTemporada extends Espectaculo {

	ArrayList<Sesion> sesiones;

	// Métodos
	/**
	 * Constructor parametrizado de la clase EspectaculoTemporada
	 * 
	 * @param titulo      Cadena que contiene el título del espectáculo
	 * @param categorias  Cadena que contiene las categorias del espectáculo
	 * @param descripcion Cadena que contiene la descrpcion del espectáculo
	 * @param sesiones    Clase de tipo Sesion que contiene la fecha y localidades
	 *                    de lasesion de un espectaculo
	 * @author Enrique Estevez Mayoral
	 */
	public EspectaculoTemporada(String titulo, String categorias, String descripcion, ArrayList<Sesion> sesiones) {
		super (titulo,categorias,descripcion);
		this.titulo = titulo;
		this.categorias = categorias;
		this.descripcion = descripcion;
		this.sesiones = sesiones;
	}
	
	public ArrayList<Sesion> getSesiones(){
		return sesiones;
	}
	
	public void CrearSesion(Date fecha,int localidades) {
		sesiones.add(new Sesion(localidades,fecha));
	}
	
}
