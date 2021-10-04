package gestorDeEspectaculos;

import java.util.ArrayList;

public abstract class Espectaculo {
	// Funciones Abstractas
	public abstract Espectaculo crearEspectaculo(String titulo,String categorias,String descripcion ,ArrayList<Sesion> sesiones) throws Exception;


}
