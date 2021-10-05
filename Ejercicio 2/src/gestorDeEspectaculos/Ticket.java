package gestorDeEspectaculos;

import java.util.Date;

public class Ticket {

	//variables
	private String titulo;
	private String descripcion;
	private String categorias;
	Date fecha;
	
	//funciones asociadas
	public Ticket(String titulo,String descripcion,String categorias,Date fecha) {
		this.titulo=titulo;
		this.descripcion=descripcion;
		this.categorias=categorias;
		this.fecha=fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getCategorias() {
		return categorias;
	}
	public Date getFecha() {
		return fecha;
	}
}
