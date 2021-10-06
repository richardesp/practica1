package gestorDeEspectaculos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class EspectaculoPuntual extends Espectaculo {

	// Variables
	String titulo;
	String categorias;
	String descripcion;
	Sesion sesion;

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
		this.sesion = sesion;
	}
	public Sesion getSesion() {
		return sesion;
	}
	
}
this.categorias = categorias;
	}

	/**
	 * Cambia el valor de la variable descripcion por el valor pasado por argumento
	 * a la función
	 * 
	 * @param titulo Titulo a cambiar a la instancia
	 * @author Enrique Estevez Mayoral
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Cambia el valor de la variable sesiones por el valor pasado por argumento a
	 * la función
	 * 
	 * @author Enrique Estevez Mayoral
	 */
	public void setSesiones(ArrayList<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	/**
	 * Función que compara 2 fechas
	 * 
	 * @param fecha Fecha de la sesion que se quiere comprobar
	 * @return 
	 * @throws Exception
	 * @author Enrique Estevez Mayoral
	 */
	private boolean fechaMayorActual(Date fecha) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		String fechaEspectaculo = formatter.format(fecha);
		String fechaActual = formatter.format(date);

		if (fechaEspectaculo.compareTo(fechaActual) > 0)
			throw new Exception("La fecha del espectaculo es");

		return true;
	}
	
	/**
	 * Función que crea un espectáculo
	 * 
	 * @param titulo el titulo del espectaculo
	 * @param categorias la categoria del espectáculo
	 * @param descripcion descripcion del espectaculo
	 * @param sesiones la lista de sesiones del espectaculo
	 * @return un espectaculo
	 * @throws Exception Se lanza cuando no tiene el número correcto de elementos
	 * @author Enrique Estevez Mayoral
	 */
	
	@Override
	public Espectaculo crearEspectaculoP(String titulo, String categorias, String descripcion,
			ArrayList<Sesion> sesiones) throws Exception {
		if (sesiones.size() != 1)
			throw new Exception("El formato de las sesiones no es válido para el Espectáculo puntual\n\n");

		EspectaculoPuntual e = new EspectaculoPuntual(titulo, categorias, descripcion, sesiones);

		return e;
	}

}
