package gestorDeEspectaculos;

public class EspectaculoPaseMultiple extends Espectaculo {
	
	public EspectaculoPaseMultiple crearEspectaculoPaseMultiple() {
		EspectaculoPaseMultiple espectaculo = new EspectaculoPaseMultiple();
		return espectaculo;
	}

	@Override
	public EspectaculoPuntual crearEspectaculoPuntual() {
		// TODO Auto-generated method stub
		EspectaculoPuntual espectaculo = new EspectaculoPuntual();
		return espectaculo;
		
	}

	@Override
	public EspectaculoTemporada crearEspectaculoTemporada() {
		// TODO Auto-generated method stub
		EspectaculoTemporada espectaculo = new EspectaculoTemporada();
		return espectaculo;
	}

}
