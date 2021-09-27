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
				limpiarPantalla();

				String titulo, comentario;
				float puntuacion;

				System.out.println("Introduzca el título de la obra a criticar: ");
				titulo = input.nextLine();
				System.out.print("\n");
				System.out.print("Introduzca la valoración (flotante del 1 al 10): ");
				try {
					puntuacion = input.nextFloat();
					input.nextLine();

				} catch (Exception e) {
					System.err.print("El formato debe ser numérico flotante\n\n");

					systemPause();
					break;
				}

				// Me aseguro de que el valor sea válido
				if (puntuacion > 10) {
					puntuacion = 10;

				} else if (puntuacion < 0) {
					puntuacion = 0;

				}
				System.out.print("\n");
				System.out.print("Introduzca el comentario: ");
				comentario = input.nextLine();
				System.out.print("\n");

				Espectador autor = null;

				try {
					autor = gestorCriticas.getUsuario(usuarioLogeado);
				} catch (Exception e) {
					System.err.print("El usuario no existe en el sistema\n\n");

					systemPause();
					break;
				}

				Critica criticaCreada = new Critica(titulo, puntuacion, comentario, autor);
				try {
					gestorCriticas.crearCritica(criticaCreada);
				} catch (Exception e) {
					System.err.print("Ya has creado una crítica sobre este Espectáculo\n\n");

					systemPause();
					break;
				}

				System.out.print("Crítica creada correctamente\n\n");
				systemPause();

				break;

			case 2:
				limpiarPantalla();

				ArrayList<Critica> consulta = new ArrayList<Critica>();

				try {
					consulta = gestorCriticas.getCriticas();
				} catch (Exception e) {
					System.err.print("No existe ninguna crítica en el sistema\n\n");

					systemPause();
					break;
				}

				for (int i = 0; i < consulta.size(); i++) {
					System.out.println("Crítica[" + (i + 1) + "]\n" + "Título: " + consulta.get(i).getTitulo()
							+ "\nPuntuación: " + consulta.get(i).getPuntuacion() + "\nComentario: "
							+ consulta.get(i).getComentario() + "\nAutor: " + consulta.get(i).getNickAutor() + "\n");
				}

				systemPause();
				break;

			case 3:
				limpiarPantalla();

				String tituloABorrar;
				System.out.print("Introduzca el título de la obra a borrar: ");
				tituloABorrar = input.nextLine();
				System.out.print("\n");
				try {
					gestorCriticas.borrarCritica(usuarioLogeado, tituloABorrar);
				} catch (Exception e) {
					System.err.print("Error, la crítica introducida no ha sido encontrada (recuerda que solo puedes borrar aquellas crítica propias)\n\n");
					
					systemPause();
					break;
				}

				System.out.print("La crítica ha sido eliminada correctamente\n\n");
				systemPause();

				break;

			case 4:
				String tituloAValorar, autorAValorar;
				float valoracion;

				ArrayList<Critica> consultaValorar = new ArrayList<Critica>();
				try {
					consultaValorar = gestorCriticas.getCriticas();
				} catch (Exception e) {
					System.err.print("Error, no existe ninguna crítica en el sistema\n\n");
					
					systemPause();
					break;
				}
				
				System.out.println("las obras disponibles para valorar son:\n");
				// Le mostramos todas las críticas que no son suyas para que elija cual valorar
				
				// Compruebo si existe al menos una crítica que no haya creado él para valorar
				int contador = 0;
				
				for (int i = 0; i < consultaValorar.size(); i++) {
					if (!usuarioLogeado.equalsIgnoreCase(consultaValorar.get(i).getNickAutor()))
						contador++;
				}
				
				if(contador == 0) {
					System.err.print("No existe ninguna crítica a valorar\n\n");
					
					systemPause();
					break;
				}
				
				for (int i = 0; i < consultaValorar.size(); i++) {
					if (!usuarioLogeado.equalsIgnoreCase(consultaValorar.get(i).getNickAutor()))
						System.out.print("Crítica[" + (i + 1) + "]\n" + "Título: " + consultaValorar.get(i).getTitulo()
								+ "\nPuntuación: " + consultaValorar.get(i).getPuntuacion() + "\nComentario: "
								+ consultaValorar.get(i).getComentario() + "\nAutor: " + consultaValorar.get(i).getNickAutor() + "\n");
				}
				
				System.out.print("\n");
				systemPause();
				
				System.out.print("Introduzca el título de la obra a valorar: ");
				tituloAValorar = input.nextLine();
				System.out.print("\n");
				System.out.print("Introduzca la valoracion (flotante del 1 al 10): ");
				try {
					valoracion = input.nextFloat();
					input.nextLine();
					
				} catch (Exception e) {
					System.err.print("El formato de la valoración debe ser un flotante");
					
					systemPause();
					break;
				}
				
				System.out.print("Introduzca el autor de la crítica: ");
				autorAValorar = input.nextLine();
				System.out.print("\n\n");
				
				try {
					gestorCriticas.votarCritica(autorAValorar, tituloAValorar, valoracion);
				} catch (Exception e) {
					System.err.print("La crítica introducida no existe\n\n");
					
					systemPause();
					break;
				}
				
				System.out.print("La crítica introducida ha sido eliminada correctamente\n\n");
				
				systemPause();
				break;

			case 5:
				/*
				 * String nickABuscar;
				 * System.out.println("Introduzca el título de la obra a valorar\n");
				 * nickABuscar = input.nextLine(); if (!gestorCriticas.existeNick(nickABuscar))
				 * { throw new Exception("El usuario no existe en el sistema\n"); }
				 * ArrayList<Critica> consultaUsuario = new ArrayList<Critica>(); consulta =
				 * gestorCriticas.getCriticasUsuario(nickABuscar); for (int i = 0; i <
				 * consultaUsuario.size(); i++) { System.out.println("Título: " +
				 * consultaUsuario.get(i).getTitulo() + "\nPuntuación: " +
				 * consultaUsuario.get(i).getPuntuacion() + "\nComentario:" +
				 * consulta.get(i).getComentario()); }
				 */
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
			error.printStackTrace();

		}
	}

	/**
	 * Función que pausa la ejecución hasta pulsar una tecla
	 * 
	 * @author Ricardo Espantaleón Pérez
	 */
	public static void systemPause() {
		System.out.println("Presiona \'ENTER\' para continuar...\n\n");
		input.nextLine();
	}

}
