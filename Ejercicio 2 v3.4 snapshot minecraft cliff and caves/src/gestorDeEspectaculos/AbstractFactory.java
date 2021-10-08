package gestorDeEspectaculos;

import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractFactory {

	
	public abstract EspectaculoPuntual crearEspectaculoPuntual (String titulo,String categorias,String descripcion,Date fecha,int localidades);
	public abstract EspectaculoPaseMultiple crearEspectaculoPaseMultiple(String titulo,String categorias,String descripcion,ArrayList<Date> fechas,int localidades);
	public abstract EspectaculoTemporada crearEspectaculoTemporada(String titulo, String categorias, String descripcion, Date fechaInicio,
			Date fechaFin, int localidades);
}
