package gestorDeEspectaculos;

import java.util.ArrayList;
import java.util.Date;

/**
 * La clase Critica es un TAD que se encarga de almacenar y gestionar todo los
 * atributos relacionados con una AbstractFactory
 * 
 * @author Nicolás López
 * @see Espectaculo
 * @see Sesion
 * @version 1.0
 */
public abstract class AbstractFactory {

	/**
	 * Función que crea un espectáculo puntual
	 * 
	 * @param titulo      El titulo del espectáculo
	 * @param categorias  La categoria del espectáculo
	 * @param descripcion La descripción del espectáculo
	 * @param fecha       La fecha y hora del espectáculo
	 * @param localidades El número de localidades del espectáculo
	 * @throws Exception Lanza una excepción genérica en caso de que haya sucedido
	 *                   un error genérico
	 * @return Un espectaculo puntual
	 * @author Nicolás López
	 */
	public abstract EspectaculoPuntual crearEspectaculoPuntual(String titulo, String categorias, String descripcion,
			Date fecha, int localidades) throws Exception;

	/**
	 * Función que crea un espectáculo de pase múltiple
	 * 
	 * @param titulo      El titulo del espectáculo
	 * @param categorias  La categoria del espectáculo
	 * @param descripcion La descripción del espectáculo
	 * @param fecha       Las fecha y las horas en los que se representará el
	 *                    resultado
	 * @param localidades El número de localidades del espectáculo
	 * @throws Exception Lanza una excepción genérica en caso de que haya sucedido
	 *                   un error genérico
	 * @return Un espectáculo de pase múltiple
	 * @author Nicolás López
	 */
	public abstract EspectaculoPaseMultiple crearEspectaculoPaseMultiple(String titulo, String categorias,
			String descripcion, ArrayList<Date> fechas, int localidades) throws Exception;

	/**
	 * Función que crea un espectáculo de temporada
	 * 
	 * @param titulo      El titulo del espectáculo
	 * @param categorias  La categoria del espectáculo
	 * @param descripcion La descripción del espectáculo
	 * @param fechaInicio La fecha y la hora en la que empieza el espectaculo de
	 *                    temporada
	 * @param fechaFin    La fecha y la hora en la que acaba el espectaculo de
	 *                    temporada
	 * @param localidades El número de localidades del espectáculo
	 * @throws Exception Lanza una excepción genérica en caso de que haya sucedido
	 *                   un error genérico
	 * @return Un espectáculo de temporada
	 * @author Nicolás López
	 */
	public abstract EspectaculoTemporada crearEspectaculoTemporada(String titulo, String categorias, String descripcion,
			Date fechaInicio, Date fechaFin, int localidades) throws Exception;
}
