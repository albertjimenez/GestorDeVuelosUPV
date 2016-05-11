package modelo;

import java.util.Locale;
import java.util.Scanner;

public class GestorVuelos {
	static Scanner tec = new Scanner(System.in).useLocale(Locale.US);
	static Aerolinea[] listaC = new Aerolinea[3];
	static final String[] vectorNombresAerolineas = { "AirFrance", "Iberia", "Alitalia", };

	public static int menu() {

		System.out.println("\n*********** MENU PRINCIPAL ***********");
		System.out.println("           1. Mostrar Vuelos");
		System.out.println("           2. Reservar");
		System.out.println("           3. Cancelar");
		System.out.println("           0. Terminar");
		System.out.println("**************************************");
		System.out.print("Elige una opcion: ");

		return tec.nextInt();
	}

	public static void mostrarTodos(Aerolinea listaC[]) {
		System.out.println("\n   AEROLINEA   IDVUELO     ORIGEN      DESTINO    HSALIDA  HLLEGADA   LIBRES");
		for (int i = 0; i < listaC.length; i++)
			System.out.println(listaC[i].toString());

		System.out.println("---------------------------------------------------------------------------------");

	}

	public static void mostrarVuelos(Aerolinea listaC[], String o, String d) {
		System.out.println("\n   AEROLINEA   IDVUELO     ORIGEN      DESTINO    HSALIDA  HLLEGADA   LIBRES");
		for (int i = 0; i < listaC.length; i++)
			listaC[i].mostrarVuelos(o, d);

		System.out.println("---------------------------------------------------------------------------------");

		/* COMPLETAR */
	}

	public static Vuelo encontrarVuelo(Aerolinea listaC[], String i, String o, String d) {

		for (int j = 0; j < listaC.length; j++) {
			Vuelo v = listaC[j].buscarVuelo(i);
			if (v != null && v.getOrigen().equals(o) && v.getDestino().equals(d))
				return v;
		}
		return null;
	}

	public static Vuelo encontrarVuelo(Aerolinea listaC[], String i) {

		for (int j = 0; j < listaC.length; j++)
			if (listaC[j].buscarVuelo(i) != null)
				return listaC[j].buscarVuelo(i);

		return null;
	}

	/**
	 * Metodo privado creado especialmente para facilitar el guardado de todas
	 * las aerolineas
	 * 
	 * @param listaC
	 *            La lista estatica creada en esta misma clase
	 */
	static void guardarTodo(Aerolinea[] listaC) {
		for (int i = 0; i < listaC.length; i++)
			try {
				listaC[i].guardarAerolinea();
			} catch (Exception e) {
				System.out.println("No se ha podido guardar la lista");
			}

	}

	public static void main(String args[]) throws Exception {
		// Declarar e inicializar listaC, array de aerolineas,
		// creando las 3 aerolineas
		for (int i = 0; i < vectorNombresAerolineas.length; i++) {
			// System.out.println("elementos: " + vectorNombresAerolineas[i]);
			listaC[i] = new Aerolinea(vectorNombresAerolineas[i]);
		}

		// Repetir hasta que se decida terminar

		int eleccion = menu();
		boolean fin = false;
		while (!fin) {

			switch (eleccion) {
			case 1:
				mostrarTodos(listaC);
				// eleccion = menu();
				break;
			case 2:
				// Scanner sc = new Scanner(System.in);
				System.out.print("Ciudad de origen: ");
				String origen = tec.next();
				System.out.print("Ciudad de destino: ");
				String destino = tec.next();
				mostrarVuelos(listaC, origen, destino);
				System.out.print("Identificador de vuelo: ");
				String id = tec.next();
				Vuelo miVuelo = encontrarVuelo(listaC, id, origen, destino);
				if (miVuelo != null) {
					System.out.println("Preferencia de asiento V o P: ");

					// Metodo upper case para poner mayusculas en caso de que
					char pref = tec.next().toUpperCase().charAt(0);
					System.out.println("Introduce tu nombre para reservar: ");
					miVuelo.reservar(tec.next(), pref);

				} else
					System.out.println("No existe un vuelo con el identificador " + id + " " + origen + " " + destino);

				// sc.close();
				// eleccion = menu();
				break;

			case 3:
				// Scanner otroSC = new Scanner(System.in);
				System.out.println("Introduce el identificador de vuelo: ");
				String idanu = tec.next();
				Vuelo unVuelo = encontrarVuelo(listaC, idanu);
				if (unVuelo != null) {
					System.out.println("Introduce tu asiento: ");
					unVuelo.cancelarReserva(tec.nextInt());
					System.out.println("Reserva anulada");
				} else
					System.out.println("No existe un vuelo con el identificador " + idanu);

				// otroSC.close();
				// eleccion = menu();
				break;

			case 0:
				guardarTodo(listaC);
				fin = true;
				tec.close();
				break;

			default:
				System.out.println("No es un numero valido");
				break;
			}
			if (fin)
				break;
			else
				eleccion = menu();

		}

		// Presentar el menu de seleccion de las distintas opciones

		// Gestionar adecuadamente la opcion seleccionada

		// 1. Mostrar Vuelos
		// Mostrar todos los vuelos de todas las aerol�neas

		// 2. Reservar
		// Solicitar origen ori y destino des del vuelo

		// Mostrar todos los vuelos con origen ori y destino des
		// con plazas libres

		// Pedir identificador de vuelo elegido

		// Encontrar vuelo con dicho identificador,
		// origen ori y destino des

		// Si existe

		// Pedir nombre pasajero y preferencia de asiento

		// Hacer la reserva

		// Si no existe, mostrar un mensaje

		// 3. Cancelar
		// Pedir identificador idanu de vuelo

		// Encontrar vuelo con dicho identificador idanu

		// Si existe

		// Pedir y validar numero de asiento

		// Cancelar la reserva

		// Mostrar un mensaje

		// Si no existe, mostrar un mensaje

		// 0. Terminar
		// Guardar cada aerolinea en un fichero del mismo nombre
	}

} // GestorVuelos
