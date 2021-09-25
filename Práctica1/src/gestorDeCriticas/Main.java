package gestorDeCriticas;

/**
 * @author ricardo Espectador, Espectaculo, gestorDeCriticas (singleton)
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner input = new Scanner(System.in);

	static private String usuarioLogeado = null;

	static GestorCriticas gestorCriticas = GestorCriticas.getInstance();

	/**
	 * Función main del gestor de Críticas. Será el menú principal que gestionara la
	 * I/O del programa
	 * 
	 * @param args Los argumentos pasados mediante CLI
	 * @author Ricardo Espantaleón
	 */
	public static void main(String[] args) {
		int ans = 0;

		while (true) {
			String nombreApellidos, nickName, email;

			limpiarPantalla();

			System.out.print("Gestor de críticas 1.0\n\n");
			System.out.print("[1]. Iniciar sesión\n\n");
			System.out.print("[2]. Registrarse\n\n");
			System.out.print("[3]. Cerrar el programa\n\n");

			System.out.print("Introduzca una opción: ");
			// Se guarda en el buffer un \n al ser un int
			ans = input.nextInt();
			input.nextLine();
			System.out.print("\n");

			switch (ans) {
			case 1:
				limpiarPantalla();

				System.out.print("Introduzca su nombre de usuario: ");
				nickName = input.nextLine();

				if (gestorCriticas.existeNick(nickName)) {
					usuarioLogeado = nickName;
					menuUsuarioLogeado();

				} else {
					System.err.print("Error, el nick introducido no se encuentra en el sistema\n\n");
					systemPause();

					break;
				}

				break;

			case 2:
				limpiarPantalla();

				System.out.print("Introduzca su nombre y apellido: ");
				nombreApellidos = input.nextLine();
				System.out.print("\n");

				System.out.print("Introduzca su nickname: ");
				nickName = input.nextLine();
				System.out.print("\n");

				System.out.print("Introduzca su email: ");
				email = input.nextLine();
				System.out.print("\n");

				Espectador e;

				// Compruebo que se crea correctamente el usuario
				try {
					e = new Espectador(nickName, nombreApellidos, email);

				} catch (Exception error) {
					System.err.print("Error, formato de email incorrecto\n\n");
					systemPause();

					// Me salgo del case dado que ha habido un error
					break;

				}

				// Compruebo que el usuario se agrega correctamente
				try {
					gestorCriticas.crearUsuario(e);

				} catch (Exception error) {
					System.err.print("Error, el usuario ya ha sido registrado\n\n");
					systemPause();

					// Me salgo del case dado que ha habido un error
					break;
				}

				System.out.print("Usuario creado correctamente.\n");
				System.out.format("Bienvenido %s (%s)\n\n", e.getNombreApellidos(), e.getNick());

				systemPause();

				break;

			case 3:
				System.exit(0);

			}

		}
	}

	static private void menuUsuarioLogeado() {
		int ans = 0;

		while (true) {
			limpiarPantalla();

			System.out.format("Bienvenido: %s\n\n", usuarioLogeado);
			System.out.print("[1] Crear una crítica\n\n");
			System.out.print("[2] Consultar todas las críticas disponibles\n\n");
			System.out.print("[3] Borrar una crítica\n\n");
			System.out.print("[4] Votar la utilidad de una crítica\n\n");
			System.out.print("[5] Buscar la crítica de un usuario\n\n");
			System.out.print("[6] Cerrar sesión\n\n");
			System.out.print("[7] Cerrar programa\n\n");

			System.out.print("Introduzca una opción: ");
			// Se guarda en el buffer un \n al ser un int
			ans = input.nextInt();
			input.nextLine();
			System.out.print("\n");

			switch (ans) {
			case 1:

				break;

			case 2:

				break;

			case 3:

				break;

			case 4:

				break;

			case 5:

				break;

			case 6:
				return;

			case 7:
				System.exit(0);
			}

		}
	}

	/**
	 * Función que limpia el terminal de texto
	 * 
	 * @exception Lanzará una excepción en caso de no poder limpiar el terminal
	 *                    tanto en un S.O. como Windows o Linux
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public static void limpiarPantalla() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}

		} catch (Exception error) {
			System.err.print("Error al limpiar el terminal\n");

		}
	}

	/**
	 * Función que pausa la ejecución hasta pulsar una tecla
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public static void systemPause() {
		System.out.println("Presiona cualquier tecla para continuar...\n\n");
		input.nextLine();
	}

}
