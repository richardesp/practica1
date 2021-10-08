package gestorDeEspectaculos;

import java.util.Date;

public class Sesion {
int localidades;
Date fecha;
int vendidas;

public Sesion(int localidades, Date fecha) {
	this.localidades=localidades;
	this.fecha=fecha;
	vendidas=0;
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
public void entradaVendida() {
	vendidas=vendidas+1;
}
public int getVendidas() {
	return vendidas;
}
}
