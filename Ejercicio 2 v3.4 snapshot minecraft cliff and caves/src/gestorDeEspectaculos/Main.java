/**
 * 
 */
package gestorDeEspectaculos;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

				Critica criticaCreada = new Critica(titulo, puntuacion, comentario, autor);
				try {
					gestorEspectaculos.crearCritica(criticaCreada);
				} catch (Exception e) {
					System.err.print(e);

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
		int tipoEspectaculo;
		Date fecha;
		int localidades;

		while (true) {
			limpiarPantalla();

			System.out.print("Bienvenido: $sudo\n\n");
			System.out.print("[1] Dar de alta un espectáculo\n\n"); // Ok ! -> Error en pase de temporada
			System.out.print("[2] Cancelar todas las sesiones de un espectáculo\n\n"); // Ok !
			System.out.print("[3] Cancelar una sesión particular de un espectáculo\n\n"); // Ok !
			System.out.print("[4] Actualizar datos de un espectáculo\n\n"); // Ok !
			System.out.print("[5] Gestionar venta de entradas de un espectáculo\n\n"); // Ok !
			System.out.print("[6] Consultar localidades disponibles de un espectáculo (dada una fecha)\n\n");
			System.out.print("[7] Filtrar las sesiones de un espectáculo\n\n"); // Ok !
			System.out.print("[8] Filtrar espectáculos por categorías\n\n"); // Ok !
			System.out.print("[9] Cerrar sesión\n\n"); // Ok !
			System.out.print("[10] Cerrar programa\n\n"); // Ok !

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

				System.out.print("Introduzca el número de localidades donde se va a realizar el espectáculo: ");
				localidades = input.nextInt();
				input.nextLine();
				System.out.print("\n");

				System.out.print(
						"Introduzca el tipo de espectáculo que es: (1. Puntual, 2. Pase múltiple, 3. Pase de temporada): ");
				tipoEspectaculo = input.nextInt();
				input.nextLine();
				System.out.print("\n");

				if (tipoEspectaculo != 1 && tipoEspectaculo != 2 && tipoEspectaculo != 3) {
					System.err.print("Error, el tipo de espectáculo introducido no consta en el sistema\n\n");

					systemPause();
					break;
				}

				if (tipoEspectaculo == 1) {
					String strFecha;
					System.out.print("Introduzca la fecha del espectáculo (dd/MM/yyyy HH:mm): ");
					strFecha = input.nextLine();
					System.out.print("\n");

					try {
						fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strFecha);

					} catch (ParseException e) {
						System.err.print(e);

						systemPause();
						break;
					}

					try {
						gestorEspectaculos.crearEspectaculoPuntual(titulo, categoria, descripcion, fecha, localidades);
					} catch (Exception e) {
						System.err.print(e);

						systemPause();
						break;
					}

					System.out.print("Espectáculo puntual creado correctamente\n\n");

					systemPause();
					break;

				} else if (tipoEspectaculo == 2) {
					ArrayList<Date> fechas = new ArrayList<Date>();
					String strFecha;

					System.out.print("Introduzca el el número de fechas en las que se realizará el espectáculo: ");
					int nFechas = input.nextInt();
					input.nextLine();
					System.out.print("\n");

					for (int i = 1; i <= nFechas; ++i) {

						System.out.print("Introduzca la fecha número " + i + " (dd/MM/yyyy HH:mm): ");
						strFecha = input.nextLine();
						try {
							fechas.add(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strFecha));
						} catch (ParseException e) {
							System.err.print(e);

							systemPause();
							break;
						}

					}

					try {
						gestorEspectaculos.crearEspectaculoPaseMultiple(titulo, categoria, descripcion, fechas,
								localidades);
					} catch (Exception e) {
						System.err.print(e);

						systemPause();
						break;
					}

					System.out.print("Espectáculo de pase múltiple creado correctamente\n\n");

					systemPause();
					break;

				} else {
					String strFechaInicio, strFechaFinal;
					Date fechaInicio, fechaFinal;

					System.out.print("Introduzca la fecha de inicio del pase de temporada (dd/MM/yyyy HH:mm): ");
					strFechaInicio = input.nextLine();
					System.out.print("Introduzca la fecha de finalización del pase de temporada (dd/MM/yyyy HH:mm): ");
					strFechaFinal = input.nextLine();

					try {
						fechaInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strFechaInicio);
						fechaFinal = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strFechaFinal);

					} catch (ParseException e) {
						System.err.print(e);

						systemPause();
						break;
					}

					try {
						gestorEspectaculos.crearEspectaculoTemporada(titulo, categoria, descripcion, fechaInicio,
								fechaFinal, localidades);
					} catch (Exception e) {
						System.err.print(e);

						systemPause();
						break;
					}

					System.out.print("Espectáculo de pase de temporada creado correctamente\n\n");

					systemPause();
					break;

				}

				// TODO
				// crearEspectaculo(), debeis hacer la función abstracta genérica para el tipo
				// de espectáculo en cuestión

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

				System.out.print("Introduzca la fecha de la sesión a eliminar (dd/MM/yyyy HH:mm): ");
				String strfechaEliminar = input.nextLine();
				System.out.print("\n");

				Date fechaEliminar;

				try {
					fechaEliminar = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strfechaEliminar);
				} catch (ParseException e) {
					System.err.print(e);

					systemPause();
					break;
				}

				try {
					gestorEspectaculos.cancelarSesionEspectaculo(titulo, fechaEliminar);
				} catch (Exception e) {
					System.err.print(e);
				}

				systemPause();

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

				Espectaculo e = null;

				try {
					e = gestorEspectaculos.getEspectaculo(titulo);
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
				int numeroEntradas = 0;
				try {
					int indiceEspectaculo = gestorEspectaculos.buscarEspectaculoTitulo(titulo);
					for (int i = 0; i < gestorEspectaculos.getEspectaculos().get(indiceEspectaculo).getSesiones()
							.size(); i++) {

						numeroEntradas = gestorEspectaculos.getNumeroEntradas(titulo, gestorEspectaculos
								.getEspectaculos().get(indiceEspectaculo).getSesiones().get(i).getFecha());
						System.out.format("El espectáculo %s, en la sesión %d, posee un total de %s entradas\n\n",
								titulo, i + 1, numeroEntradas);
					}
				} catch (Exception error) {
					error.printStackTrace();
					System.err.print(error);
				}
				systemPause();

				break;

			case 6:
				System.out.print("Introduzca el título del espectáculo: ");
				titulo = input.nextLine();
				System.out.print("\n");
				@SuppressWarnings("null")
				int numeroLocalidades = (Integer) null;
				try {
					int indexEspectaculo = gestorEspectaculos.buscarEspectaculoTitulo(titulo);
					for (int i = 0; i < gestorEspectaculos.getEspectaculos().get(indexEspectaculo).getSesiones()
							.size(); i++) {

						numeroLocalidades = gestorEspectaculos.getNumeroLocalidades(titulo, gestorEspectaculos
								.getEspectaculos().get(indexEspectaculo).getSesiones().get(i).getFecha());
						System.out.println("Sesión 1: " + numeroLocalidades);
					}
				} catch (Exception error) {
					System.err.print(error);
				}
				systemPause();
				break;

			case 7:
				System.out.print("Introduzca el título del espectáculo: ");
				titulo = input.nextLine();
				System.out.print("\n");

				ArrayList<Sesion> sesiones = new ArrayList<Sesion>();

				try {
					sesiones = gestorEspectaculos.proximoEspectaculo(titulo);

				} catch (Exception error) {
					System.err.print(error);

					systemPause();
					break;
				}

				if (sesiones.size() == 0) {
					System.err.print("El espectáculo no tiene ninguna sesión\n\n");

					systemPause();
					break;
				}

				for (int i = 0; i < sesiones.size(); i++) {
					System.out.println("Fecha: " + sesiones.get(i).getFecha() + "\nLocalidades disponibles: "
							+ sesiones.get(i).getLocalidades() + "\nEntradas Vendidas:" + sesiones.get(i).getVendidas()
							+ "\n");
				}

				systemPause();
				break;

			case 8:
				System.out.print("Introduzca la categoría de espectáculos a obtener: ");
				categoria = input.nextLine();
				System.out.print("\n");

				ArrayList<Espectaculo> sesionesCategoria = null;

				try {
					sesionesCategoria = gestorEspectaculos.getEspectaculosCategoria(categoria);
				} catch (Exception error) {
					System.err.print(error);

					systemPause();
					break;
				}

				// TODO -> Hacer la impresión de todos los espectáculos disponibles
				// Imprimir el titulo y los datos del espectaculo \n
				// Imprimes las sesiones

				for (int i = 0; i < sesionesCategoria.size(); ++i) {
					System.out.print("[" + (i + 1) + "]\n" + "Título: " + sesionesCategoria.get(i).getTitulo() + "\n"
							+ "Categoría: " + sesionesCategoria.get(i).getCategorias() + "\n" + "Descripción: "
							+ sesionesCategoria.get(i).getDescripcion() + "\n\n");
				}

				systemPause();

				break;

			case 9:

				return;

			case 10:
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
