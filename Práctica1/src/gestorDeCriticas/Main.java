package gestorDeCriticas;

/**
 * @author ricardo Espectador, Espectaculo, gestorDeCriticas (singleton)
 * 
 */

import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("HOLA");
		GestorCriticas gestorCriticas = GestorCriticas.getInstance();
		Espectador e1 = new Espectador("Ricardo", null, null);
		Espectador e2 = new Espectador("Nico", null, null);
		Espectador e3 = new Espectador("Olga", null, null);
		ArrayList<Espectador> usuarios = new ArrayList<Espectador>();

		try {
			Critica c = new Critica("Hola", (float) 5.0, "hola", e1);
			c.setPuntuacion((float) 5.6);
			gestorCriticas.crearUsuario(e1);
			/*
			 * gestorCriticas.crearUsuario(e1); gestorCriticas.crearUsuario(e2);
			 * gestorCriticas.crearUsuario(e3); if(!gestorCriticas.listaUsuariosVacia())
			 */
			usuarios = gestorCriticas.getUsuarios();

		} catch (Exception error) {
			error.printStackTrace();
		}

		System.out.println("Aqui sigo, me sigo imprimiendo pese al catch");

		/*
		 * for(int i = 0; i < usuarios.size(); ++i)
		 * System.out.println(usuarios.get(i).getNick());
		 */

	}

}
