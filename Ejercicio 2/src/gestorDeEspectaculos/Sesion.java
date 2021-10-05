package gestorDeEspectaculos;

import java.util.Date;

public class Sesion {
int localidades;
Date fecha;

public Sesion(int localidades, Date fecha) {
	this.localidades=localidades;
	this.fecha=fecha;
}

public int getLocalidades() {
	return localidades;
	}
public Date getFecha() {
	return fecha;
}
public void setLocalidades(int localidades) {
	this.localidades=localidades;
}
public void setFecha(Date fecha) {
	this.fecha=fecha;
}
}
