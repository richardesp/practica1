package gestorDeEspectaculos;

import java.util.ArrayList;
import java.util.Date;

/**
 * La clase Critica es un TAD que se encarga de almacenar y gestionar todo los
 * atributos relacionados con una ConcreteFactory
 * 
 * @author Nicolás López
 * @see Espectaculo
 * @see Sesion
 * @version 1.0
 */
public class ConcreteFactory extends AbstractFactory {

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
	public EspectaculoPuntual crearEspectaculoPuntual(String titulo, String categorias, String descripcion, Date fecha,
			int localidades) throws Exception {
		if (!GestorDeEspectaculos.fechaMayorActual(fecha))
			throw new Exception("Error, La fecha es anterior");
		Sesion sesion = new Sesion(localidades, fecha);
		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
		sesiones.add(sesion);
		EspectaculoPuntual espectaculo = new EspectaculoPuntual(titulo, categorias, descripcion, sesiones);

		return espectaculo;
	}

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
	public EspectaculoPaseMultiple crearEspectaculoPaseMultiple(String titulo, String categorias, String descripcion,
			ArrayList<Date> fecha, int localidades) throws Exception {
		if (fecha.size() < 1)

			throw new Exception("Mínimo 2 fechas");
		for (int i = 0; i < fecha.size(); i++) {
			if (!GestorDeEspectaculos.fechaMayorActual(fecha.get(i)))
				throw new Exception("Erpr, Fecha menor que la actual");
		}

		EspectaculoPaseMultiple espectaculo;
		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();

		for (int i = 0; i < fecha.size(); i++) {
			sesiones.add(new Sesion(localidades, fecha.get(i)));
		}

		espectaculo = new EspectaculoPaseMultiple(titulo, categorias, descripcion, sesiones);

		return espectaculo;
	}

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
	public EspectaculoTemporada crearEspectaculoTemporada(String titulo, String categorias, String descripcion,
			Date fechaInicio, Date fechaFin, int localidades) throws Exception {
		if (!GestorDeEspectaculos.fechaMayorActual(fechaInicio))
			throw new Exception("Error, La fecha es anterior");
		if (fechaInicio.getTime() > fechaFin.getTime()) {
			throw new Exception("La fecha inicio es mayor que la de final");
		}
		EspectaculoTemporada espectaculo;
		Date fechaAux = fechaInicio;
		long diferenciaTiempo = fechaFin.getTime() - fechaInicio.getTime();
		long diferenciaEnDias = ((diferenciaTiempo / (1000 * 60 * 60 * 24)) % 365);
		int nSesiones = (int) diferenciaEnDias % 7;

		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();

		for (int i = 0; i < nSesiones; i++) {
			Sesion sesion = new Sesion(localidades, fechaAux);
			sesiones.add(sesion);
			fechaAux.setTime(fechaAux.getTime() + (7 * (1000 * 60 * 60 * 24)));

		}

		espectaculo = new EspectaculoTemporada(titulo, categorias, descripcion, sesiones);

		return espectaculo;
	}
}
