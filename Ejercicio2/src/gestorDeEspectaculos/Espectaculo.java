package gestorDeEspectaculos;

public abstract interface Espectaculo {
	// Espectáculo puntual
	public abstract EspectaculoPuntual crearEspectaculoPuntual();

	// Espectáculo de pase multiple
	public abstract EspectaculoPaseMultiple crearEspectaculoPaseMultiple();

	// Espectáculo de temporada
	public abstract EspectaculoTemporada crearEspectaculoTemporada();
}
