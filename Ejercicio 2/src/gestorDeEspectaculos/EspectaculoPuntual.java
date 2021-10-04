package gestorDeEspectaculos;

public class EspectaculoPuntual extends Espectaculo {

	public EspectaculoPuntual crearEspectaculoPuntual() {
		EspectaculoPuntual espectaculo = new EspectaculoPuntual();
		return espectaculo;
	}

	@Override
	public EspectaculoPaseMultiple crearEspectaculoPaseMultiple() {
		// TODO Auto-generated method stub
		EspectaculoPaseMultiple espectaculo = new EspectaculoPaseMultiple();
		return espectaculo;
	}

	@Override
	public EspectaculoTemporada crearEspectaculoTemporada() {
		// TODO Auto-generated method stub
		EspectaculoTemporada espectaculo = new EspectaculoTemporada();
		return espectaculo;
	}

}
