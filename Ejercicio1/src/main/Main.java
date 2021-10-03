package main;

import gestorDeCriticas.GestorCriticas;
import gestorDeCriticas.Critica;
import gestorDeCriticas.Espectador;
import java.io.IOException;

/**
 * @author ricardo Espectador, Espectaculo, gestorDeCriticas (singleton)
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner input = new Scanner(System.in);

	static private String usuarioLogeado = null;

	static GestorCriticas gestorCriticas = null;

	String rutaUsuario = "./data/datosUsuarios.txt";
	
	String rutaCritica = new String("./data/datosCriticas.txt");
	/**
	 * Función main del gestor de Críticas. Será el menú principal que gestionara la
	 * I/O del programa
	 * 
	 * @param args Los argumentos pasados mediante CLI
	 * @author Ricardo Espantaleón
	 */
	public static void main(String[] args) {
		
		// Primero exportamos al fichero de propiedades las rutas virtuales donde voy a almacenar mis ficheros
		
		// En caso de querer exportar el fichero de propiedades previamente para especificar la ruta de los datos, descomentar 
		// la sección
		
		/*try {
			GestorCriticas.exportarFicheroPropiedades("./data/datosUsuarios.txt", "./data/datosCriticas.txt");
		} catch (FileNotFoundException error) {
			System.err.print("La ruta indicada del fichero de propiedades no ha sido encontrada\n\n");
			
			systemPause();
			System.exit(1);

		} catch (Exception error) {
			System.err.print("Error al exportar el fichero de propiedades\n\n");

			systemPause();
			System.exit(1);
		}*/
		
		try {
			gestorCriticas = GestorCriticas.getInstance();
		} catch (IOException error) {
			System.err.print("Error al importar el fichero de propiedades\n\n");
			
			systemPause();
			System.exit(1);
		}
		
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
				gestorCriticas.escribirFicheroUsuarios("./data/datosUsuarios.txt");
				gestorCriticas.escribirFicheroCriticas("./data/datosCriticas.txt");
				System.exit(0);

			}

		}
	}

	/*
	 * Función que muestra y gestiona todas las opciones de cada usuario
	 * 
	 * @author Ricardo Espantaleón
	 */
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
			System.out.print("[6] Actualizar email\n\n");
			System.out.print("[7] Actualizar nombre y apellidos\n\n");
			System.out.print("[8] Cerrar sesión\n\n");
			System.out.print("[9] Cerrar programa\n\n");

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

				System.out.print("Introduzca el título de la obra a criticar: ");
				titulo = input.nextLine();
				System.out.print("\n");
				System.out.print("Introduzca la valoración (entero del 1 al 10): ");
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
					System.out.println("\tCrítica[" + (i + 1) + "]\n" + "\tTítulo: " + consulta.get(i).getTitulo()
							+ "\n\tPuntuación: " + consulta.get(i).getPuntuacion() + "\n\tComentario: "
							+ consulta.get(i).getComentario() + "\n\tAutor: " + consulta.get(i).getNickAutor() + "\n");
					if (!consulta.get(i).getValoracion().isEmpty()) {
						float media = 0;
						try {
							media = consulta.get(i).hacerMediaValoracion();
						} catch (Exception e) {
							System.err.print("No hay valoraciones\n");
						}

						System.out.println("\tValoracion: " + media + "\n");
					}
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
					System.err.print(
							"Error, la crítica introducida no ha sido encontrada (recuerda que solo puedes borrar aquellas crítica propias)\n\n");

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

				if (contador == 0) {
					System.err.print("No existe ninguna crítica a valorar\n\n");

					systemPause();
					break;
				}

				for (int i = 0; i < consultaValorar.size(); i++) {
					if (!usuarioLogeado.equalsIgnoreCase(consultaValorar.get(i).getNickAutor()))
						System.out.println(
								"\tCrítica[" + (i + 1) + "]\n" + "\tTítulo: " + consultaValorar.get(i).getTitulo()
										+ "\n\tPuntuación: " + consultaValorar.get(i).getPuntuacion()
										+ "\n\tComentario: " + consultaValorar.get(i).getComentario() + "\n\tAutor: "
										+ consultaValorar.get(i).getNickAutor() + "\n");
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
					input.nextLine();
					System.err.print("El formato de la valoración debe ser un flotante\n\n");

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

				System.out.print("La crítica introducida ha sido votada correctamente\n");

				systemPause();
				break;

			case 5:
				limpiarPantalla();
				String nickABuscar;
				System.out.println("Introduzca el nick a buscar\n");
				nickABuscar = input.nextLine();
				ArrayList<Critica> consultaUsuario = new ArrayList<Critica>();

				if (!gestorCriticas.existeNick(nickABuscar)) {
					System.err.print("El usuario introducido no existe en el sistema\n\n");

					systemPause();
					break;
				}

				consultaUsuario = gestorCriticas.getCriticasUsuario(nickABuscar);

				if (consultaUsuario.isEmpty()) {
					System.err.print("El usuario no posee ninguna crítica en el sistema\n\n");

					systemPause();
					break;
				}

				if (consultaUsuario.isEmpty()) {
					System.err.print("El usuario no tiene ninguna crítica en el sistema\n\n");

					systemPause();
					break;
				}

				for (int i = 0; i < consultaUsuario.size(); i++) {
					System.out
							.println("\tCrítica[" + (i + 1) + "]\n" + "\tTítulo: " + consultaUsuario.get(i).getTitulo()
									+ "\n\tPuntuación: " + consultaUsuario.get(i).getPuntuacion() + "\n\tComentario: "
									+ consultaUsuario.get(i).getComentario() + "\n\tAutor: "
									+ consultaUsuario.get(i).getNickAutor() + "\n");
					if (!consultaUsuario.get(i).getValoracion().isEmpty()) {
						float media = 0;
						try {
							media = consultaUsuario.get(i).hacerMediaValoracion();
						} catch (Exception e) {
							System.err.print("No hay valoraciones\n");
						}

						System.out.println("\tValoracion: " + media + "\n");
					}
				}

				systemPause();
				break;

			case 6:
				String emailACambiar;
				System.out.print("Introduzca el nuevo email: ");
				emailACambiar = input.nextLine();

				try {
					gestorCriticas.actualizarEmailUsuario(usuarioLogeado, emailACambiar);
				} catch (Exception error) {
					System.err.print("El formato del email introducido es incorrecto\n\n");

					systemPause();
					break;
				}

				System.out.print("Email cambiado correctamente\n\n");

				systemPause();
				break;

			case 7:
				String nombreYApellidosACambiar;
				System.out.print("Introduzca su nombre y apellidos: ");
				nombreYApellidosACambiar = input.nextLine();

				try {
					gestorCriticas.actualizarNombreApellidosUsuario(usuarioLogeado, nombreYApellidosACambiar);
				} catch (Exception error) {
					System.err.print("El usuario introducido no existe en el sistema\n\n");

					systemPause();
					break;
				}
				System.out.print("Nombre y apellidos cambiados correctamente\n\n");

				systemPause();
				break;

			case 8:
				return;

			case 9:
				gestorCriticas.escribirFicheroUsuarios("./data/datosUsuarios.txt");
				gestorCriticas.escribirFicheroCriticas("./data/datosCriticas.txt");
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
