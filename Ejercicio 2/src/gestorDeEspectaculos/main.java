/**
 * 
 */
package gestorDeEspectaculos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

/**
 * @author Ricardo Espantaleón Pérez
 *
 */
public class Main {

	static Scanner input = new Scanner(System.in);

	static GestorDeEspectaculos gestorEspectaculos = null;

	static boolean adminLogeado = false;

	static String usuarioLogeado = null;

	/**
	 * Función Main del gestor de espectáculos
	 * 
	 * @param args Argumentos pasado mediante CLI
	 * 
	 * @author Ricardo Espantaleón
	 */
	public static void main(String[] args) {
		try {
			gestorEspectaculos = new GestorDeEspectaculos();
		} catch (Exception error) {
			System.err.print(error);

			systemPause();
			System.exit(1);
		}

		int ans = 0;

		while (true) {
			String nombreApellidos, nickName, email, password;
			limpiarPantalla();

			System.out.println("Gestor de espectáculos 1.0\n\n");
			System.out.println("[1]. Iniciar sesión\n\n");
			System.out.println("[2]. Registrarse\n\n");
			System.out.println("[3]. Iniciar sesión como administrador\n\n");
			System.out.println("[4]. Cerrar programa\n\n");

			System.out.print("Introduzca una opción: ");

			ans = input.nextInt();
			input.nextLine();
			System.out.print("\n");

			switch (ans) {
			case 1:
				limpiarPantalla();

				System.out.print("Introduzca su nombre de usuario: ");
				nickName = input.nextLine();

				if (gestorEspectaculos.existeNick(nickName)) {
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

				Espectador e = null;

				try {
					e = new Espectador(nickName, nombreApellidos, email);
				} catch (Exception error) {
					System.err.print(error);

					systemPause();
					break;
				}

				try {
					gestorEspectaculos.crearUsuario(e);
				} catch (Exception error) {
					System.err.print(error);

					systemPause();
					break;
				}

				System.out.print("Usuario creado correctamente.\n");
				System.out.format("Bienvenido %s (%s)\n\n", e.getNombreApellidos(), e.getNick());

				break;

			case 3:
				limpiarPantalla();

				System.out.print("Introduzca la contraseña de administración: ");
				password = input.nextLine();
				System.out.print("\n");

				if (gestorEspectaculos.passwordCorrecta(password)) {
					adminLogeado = true;

					menuAdminLogeado();
				} else {
					System.err.print("Error, la contraseña introducida es incorrecta\n\n");

					systemPause();
					break;
				}

				break;

			case 4:
				try {
					gestorEspectaculos.guardarFicheros();
				} catch (Exception error) {
					System.err.print(error);

					systemPause();
					System.exit(1);
				}

				System.exit(0);
				break;
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
					autor = gestorEspectaculos.getUsuario(usuarioLogeado);
				} catch (Exception e) {
					System.err.print("El usuario no existe en el sistema\n\n");

					systemPause();
					break;
				}

				Critica criticaCreada = new Critica(titulo, puntuacion, comentario, autor, null);
				try {
					gestorEspectaculos.crearCritica(criticaCreada);
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
					consulta = gestorEspectaculos.getCriticas();
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
					gestorEspectaculos.borrarCritica(usuarioLogeado, tituloABorrar);
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
					consultaValorar = gestorEspectaculos.getCriticas();
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
					gestorEspectaculos.votarCritica(autorAValorar, tituloAValorar, valoracion);
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

				if (!gestorEspectaculos.existeNick(nickABuscar)) {
					System.err.print("El usuario introducido no existe en el sistema\n\n");

					systemPause();
					break;
				}

				consultaUsuario = gestorEspectaculos.getCriticasUsuario(nickABuscar);

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
					gestorEspectaculos.actualizarEmailUsuario(usuarioLogeado, emailACambiar);
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
					gestorEspectaculos.actualizarNombreApellidosUsuario(usuarioLogeado, nombreYApellidosACambiar);
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
				try {
					gestorEspectaculos.guardarFicheros();
				} catch (Exception error) {
					System.err.print(error);
					
					systemPause();
					System.exit(1);
				}
				System.exit(0);
			}

		}

	}

	/**
	 * Función que muestra y gestiona el menú de opciones del administrador del
	 * sistema
	 * 
	 * @author Ricardo Espantaleón Pérez
	 * 
	 */
	private static void menuAdminLogeado() {
		int ans = 0;

		String titulo, categoria, descripcion;
		Date fecha;
		int localidades;
		
		while (true) {
			limpiarPantalla();

			System.out.print("Bienvenido: $sudo\n\n");
			System.out.print("[1] Dar de alta un espectáculo\n\n");
			System.out.print("[2] Cancelar todas las sesiones de un espectáculo\n\n");
			System.out.print("[3] Cancelar una sesión particular de un espectáculo\n\n");
			System.out.print("[4] Actualizar datos de un espectáculo\n\n");
			System.out.print("[5] Gestionar venta de entradas de un espectáculo\n\n");
			System.out.print("[6] Consultar localidades disponibles de un espectáculo (dada una fecha)\n\n");
			System.out.print("[7] Filtrar un espectáculo por título\n\n");
			System.out.print("[8] Filtrar espectáculos por categorías\n\n");
			System.out.print("[9] Cerrar sesión\n\n");
			System.out.print("[8] Cerrar programa\n\n");
			
			System.out.print("Introduzca una opción: ");
			// Se guarda en el buffer un \n al ser un int
			ans = input.nextInt();
			input.nextLine();
			System.out.print("\n");
			
			switch (ans) {
			case 1:
				System.out.print("Introduzca el título del espectáculo: ");
				titulo = input.nextLine();
				System.out.print("\n");
				
				System.out.print("Introduzca la categoría del espectáculo: ");
				categoria = input.nextLine();
				System.out.print("\n");
				
				System.out.print("Introduzca la descripción del espectáculo: ");
				descripcion = input.nextLine();
				System.out.print("\n");
				
				// TODO
				//crearEspectaculo(), debeis hacer la función abstracta genérica para el tipo de espectáculo en cuestión

				break;
			
			case 2:
				System.out.print("Introduzca el título del espectáculo a eliminar: ");
				titulo = input.nextLine();
				System.out.print("\n");
				
				try {
					gestorEspectaculos.cancelarTodasSesionesEspectaculo(titulo);
				} catch (Exception error) {
					System.err.print(error);
					
					systemPause();
					break;
				}
				
				System.out.print("Sesiones eliminadas correctamente\n\n");
				
				systemPause();
				break;
				
			case 3:
				// TODO
				System.out.print("Introduzca el título del espectáculo a eliminar: ");
				titulo = input.nextLine();
				System.out.print("\n");
				
				System.out.print("Introduzca las localidades de la sesión a eliminar: ");
				localidades = input.nextInt();
				input.nextLine();
				
				System.out.print("Introduzca la fecha de la sesión a eliminar: ");
				
				// TODO LLAMAR A LA FUNCIÓN QUE BORRA UNA SESIÓN EN CONCRETO DE UN ESPECTÁCULO PARA PODER PASARLE UNA NEW CRITICA(LOCALIDADES, FECHA)
				break;
			
			case 4:
				System.out.print("Introduzca el título del espectáculo a eliminar: ");
				titulo = input.nextLine();
				System.out.print("\n");
				
				System.out.print("Introduzca la nueva categoría del espectáculo: ");
				categoria = input.nextLine();
				System.out.print("\n");
				
				System.out.print("Introduzca la nueva descripción del espectáculo: ");
				descripcion = input.nextLine();
				System.out.print("\n");
				
				try {
					gestorEspectaculos.actualizarDatosEspectaculo(titulo, categoria, descripcion);
				} catch (Exception error) {
					System.err.print(error);
					
					systemPause();
					break;
				}
				
				System.out.print("Datos actualizados correctamente\n\n");
				
				systemPause();
				break;
				
			case 5:
				System.out.print("Introduzca el título del espectáculo: ");
				titulo = input.nextLine();
				System.out.print("\n");
				@SuppressWarnings("null") int numeroEntradas = (Integer) null;
				
				try {
					numeroEntradas = gestorEspectaculos.getNumeroEntradas(titulo);
				} catch(Exception error) {
					System.err.print(error);
					
					systemPause();
					break;
				}
				
				System.out.format("El espectáculo %s, posee un total de %s entradas", titulo, numeroEntradas);
				
				systemPause();
				break;
			
			case 6:
				System.out.print("Introduzca el título del espectáculo: ");
				titulo = input.nextLine();
				System.out.print("\n");
				@SuppressWarnings("null") int numeroLocalidades = (Integer) null;
				
				try {
					numeroLocalidades = gestorEspectaculos.getNumeroLocalidades(titulo);
				} catch(Exception error) {
					System.err.print(error);
					
					systemPause();
					break;
				}
				
				System.out.format("El espectáculo %s, posee un total de %s entradas", titulo, numeroLocalidades);
				
				systemPause();
				break;
			
			case 7:
				System.out.print("Introduzca el título del espectáculo: ");
				titulo = input.nextLine();
				System.out.print("\n");
				
				Espectaculo e = null;
				
				try {
					e = gestorEspectaculos.proximoEspectaculo(titulo);
				} catch (Exception error) {
					System.err.print(error);
					
					systemPause();
					break;
				}
				
				System.out.format("El espectáculo %s está disponible", e.getTitulo());
				
				systemPause();
				break;
			
			case 8:
				System.out.print("Introduzca la categoría de espectáculos a obtener: ");
				categoria = input.nextLine();
				System.out.print("\n");
				
				ArrayList<Espectaculo> e = null;
				
				try {
					e = gestorEspectaculos.getEspectaculosCategoria(categoria);
				} catch (Exception error) {
					System.err.print(error);
					
					systemPause();
					break;
				}
				
				// TODO -> Hacer la impresión de todos los espectáculos disponibles
				
				break;
				
			case 9: 
				
				try {
					gestorEspectaculos.guardarFicheros();
				} catch (Exception error) {
					System.err.print(error);
					
					systemPause();
					System.exit(1);
				}
				System.exit(0);
				
			}
				

		}
	}

	/**
	 * Función que pausa la ejecución hasta pulsar una tecla
	 * 
	 * @author Ricardo Espantaleón
	 * 
	 */
	public static void systemPause() {
		System.out.println("Presione \'ENTER\' para continuar...\n\n");
		input.nextLine();
	}

	/**
	 * Función que limpia el terminal de texto
	 * 
	 * @implNotem Implementar solo se vaya a compilar para ver su función en el bash
	 * @author Ricardo Espantaleón
	 */
	public static void limpiarPantalla() {

	}

}
