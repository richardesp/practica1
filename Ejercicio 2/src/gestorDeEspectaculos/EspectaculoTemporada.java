package gestorDeEspectaculos;

public class EspectaculoTemporada extends Espectaculo {
	
	public EspectaculoTemporada crearEspectaculoTemporada() {
		EspectaculoTemporada espectaculo = new EspectaculoTemporada();
		return espectaculo;
	}

	@Override
	public EspectaculoPuntual crearEspectaculoPuntual() {
		// TODO Auto-generated method stub
		EspectaculoPuntual espectaculo = new EspectaculoPuntual();
		return espectaculo;
	}

	@Override
	public EspectaculoPaseMultiple crearEspectaculoPaseMultiple() {
		// TODO Auto-generated method stub
		EspectaculoPaseMultiple espectaculo = new EspectaculoPaseMultiple();
		return espectaculo;
	}
}
