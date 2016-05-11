package modelo;

import java.io.PrintWriter;

public class Vuelo {
	// Atributos de la clase

	private String ident;
	private String origen;
	private String destino;
	private String hsalida;
	private String hllegada;
	// Atributo para representar el numero de asientos libres
	private int libres;
	// Posiciones impares -> Ventanilla
	// Posiciones pares -> Pasillo
	// NO se usa la posicion 0
	private String[] asiento;
	private int numP;
	private final int MAX_PASAJEROS = 50;

	/**
	 * Constructor con parametros que crea el objeto Vuelo, e inicaliza el
	 * vector Asientos y el Numero de pasajeros
	 * 
	 * @param id
	 *            Ident, identificador del vuelo
	 * @param orig
	 *            Ciudad de origen
	 * @param dest
	 *            Ciudad de destino
	 * @param hsal
	 *            Hora de salida
	 * @param hlleg
	 *            Hora de llegada
	 */

	public Vuelo(String id, String orig, String dest, String hsal, String hlleg) {
		this.ident = id;
		this.origen = orig;
		this.destino = dest;
		this.hsalida = hsal;
		this.hllegada = hlleg;
		libres = MAX_PASAJEROS;
		// 51 elementos puesto que asiento[0] NO se usa
		asiento = new String[51];
		numP = 0;
	}

	// --------------------------------------------------------------------------

	// GETTERS
	public String getIdent() {
		return ident;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public int getNumPasajeros() {
		return numP;
	}

	public int getLibres() {
		return libres;
	}

	public boolean hayLibres() {
		return numP != MAX_PASAJEROS;
	}

	public boolean estaLibre(int a) {
		return asiento[a] == null;
	}

	// FIN GETTERS

	// --------------------------------------------------------------------------

	/**
	 * Realiza una reserva SIEMPRE, mediante las preferencias del usuario, en
	 * caso de que no este disponible su preferencia se asigna otro asiento
	 * 
	 * @param pas
	 *            Informacion del pasajero
	 * @param pref
	 *            Preferencia, V ventana, P pasillo
	 */
	public void reservar(String pas, char pref) {
		if (!hayLibres())
			System.out.println("Esta todo completo");
		else {
			int posicion = asientoLibre(pref);
			if (posicion == -1) {
				System.out.println("No queda ningun asiento en " + pref);
				System.out.println("Reservando en otra preferencia");
				switch (pref) {
				case 'V':
					asiento[asientoLibre('P')] = pas;
					System.out.println("Satisfactorio");
					System.out.println("Su reserva:");
					System.out.println(pas + " " + posicion + pref + " " + ident + " " + origen + " " + destino + " "
							+ hsalida + " " + hllegada);
					break;

				case 'P':
					asiento[asientoLibre('V')] = pas;
					System.out.println("Satisfactorio");
					System.out.println("Su reserva:");
					System.out.println(pas + " " + posicion + pref + " " + ident + " " + origen + " " + destino + " "
							+ hsalida + " " + hllegada);

					break;
				}

			} else {
				asiento[posicion] = pas;
				System.out.println("Satisfactorio");
				System.out.println("Su reserva:");
				System.out.println(pas + " " + posicion + pref + " " + ident + " " + origen + " " + destino + " "
						+ hsalida + " " + hllegada);

			}

			numP++;
			libres--;
		} // Fin del else
	}

	/**
	 * Comprueba si el asiento citado está libre bajo una preferencia
	 * 
	 * @param pref
	 *            Si quiere V ventana o P pasillo
	 * @return -1 en caso de no haber sitios libres, o un numero > 0 que
	 *         corresponde a la posicion
	 */
	private int asientoLibre(char pref) {

		switch (pref) {
		case 'V':
			for (int i = 1; i < asiento.length; i += 2)
				if (asiento[i] == null)
					return i;
			break;

		case 'P':
			for (int i = 2; i < asiento.length; i += 2)
				if (asiento[i] == null)
					return i;
			break;

		default:
			System.out.println("Error con la preferencia del asiento " + pref);
			break;
		}
		return -1;

	}

	/**
	 * Realiza una reserva sin comprobacion ya que viene dado por un fichero de
	 * texto
	 * 
	 * @param pas
	 *            Informacion del pasajero
	 * @param as
	 *            Asiento o posicion en el vector que debe ocupar
	 */
	public void reservar(String pas, int as) {
		/*
		 * No hace ningun tipo de validacion de datos puesto que este metodo se
		 * invoca en la lectura de datos desde fichero (metodo leerVuelos de la
		 * clase Aerolinea) y los datos se suponen correctos
		 */
		asiento[as] = pas;
		numP++;
		libres--;

	}

	/**
	 * Cancela la reserva solo si se encuentra dentro del intervalo 1 < X < 51
	 * 
	 * @param numasiento
	 *            Numero de asiento o posicion
	 */
	public void cancelarReserva(int numasiento) {
		if (numasiento >= 1 && numasiento < 51) {
			asiento[numasiento] = null;
			numP--;
			libres++;
			System.out.println("Reserva cancelada en el asiento " + numasiento);
		} else
			System.out.println("El asiento " + numasiento + "no es un asiento valido");
	}

	public String toString() {
		return ident + "\t" + origen + "\t" + destino + "\t" + hsalida + "\t" + hllegada;
	}

	public void guardarVuelo(PrintWriter fich) throws Exception {
		String miCadena = toString();
		fich.write(miCadena + "\n");
	}

	// Implementado para poder realizar los tests
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(this instanceof Vuelo))
			return false;
		Vuelo v = (Vuelo) obj;
		return v.toString().equals(toString());
	}

} // Vuelo
