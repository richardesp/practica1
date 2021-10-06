package gestorDeEspectaculos;

import java.util.ArrayList;
import java.util.Date;

public class ConcreteFactory extends AbstractFactory {

	public EspectaculoPuntual crearEspectaculoPuntual(String titulo,String categorias,String descripcion,Date fecha,int localidades) {
		Sesion sesion=new Sesion(localidades,fecha);
		EspectaculoPuntual espectaculo= new EspectaculoPuntual(titulo,categorias,descripcion,sesion);
	return espectaculo;
	}
	
	public EspectaculoPaseMultiple crearEspectaculoPaseMultiple(String titulo,String categorias,String descripcion,ArrayList<Date> fecha,int localidades) {
		
	EspectaculoPaseMultiple espectaculo;
	ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
	for (int i = 0; i < fecha.size(); i++) {
		sesiones.add(new Sesion(localidades, fecha.get(i)));
	}
	espectaculo=new EspectaculoPaseMultiple(titulo,categorias,descripcion,sesiones);
	return espectaculo;
	}
	
	public EspectaculoTemporada crearEspectaculoTemporada(String titulo, String categorias, String descripcion, Date fechaInicio,
			Date fechaFin, int localidades) {
		EspectaculoTemporada espectaculo;
		Date fechaAux = fechaInicio;
		long diferenciaTiempo = fechaFin.getTime() - fechaInicio.getTime();
		long diferenciaEnDias = (diferenciaTiempo / (1000 * 60 * 60 * 24)) % 365;
		int nSesiones = (int) diferenciaEnDias % 7;
		ArrayList<Sesion> sesiones = new ArrayList<Sesion>();
		for (int i = 0; i < nSesiones; i++) {
			Sesion sesion = new Sesion(localidades, fechaAux);
			sesiones.add(sesion);
			fechaAux.setTime(fechaAux.getTime() + 7 * 1000 * 60 * 60 * 24);
		}
		espectaculo = new EspectaculoTemporada(titulo, categorias, descripcion, sesiones);
		return espectaculo;
	}
}
