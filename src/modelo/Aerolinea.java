package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Aerolinea {
	// Atributos de la clase
	private final String LINEA_ANULAR = "=== === === === === === === ===";
	private String nom;
	private Vuelo[] listaV;
	private int numV;

	/**
	 * Constructor de Aerolineas
	 * 
	 * @param n
	 *            El nombre de la aerolinea
	 */
	public Aerolinea(String n) {
		this.nom = n;
		numV = 0;
		listaV = new Vuelo[10];
		leerVuelos();

	}

	private void leerVuelos() { // Haciendo lectura con clase Scanner
		try {
			Scanner sc = new Scanner(new File(nom));
			int contarLineas = 0;
			while (sc.hasNext()) {
				String id = sc.next();
				String o = sc.next();
				String d = sc.next();
				String hsa = sc.next();
				String hll = sc.next();
				Vuelo miVuelo = new Vuelo(id, o, d, hsa, hll);

				int numPasajeros = sc.nextInt();
				if (numPasajeros > 0) {
					for (int i = 0; i < numPasajeros; i++) {
						int numAsiento = sc.nextInt();
						String nombre = sc.next();
						String apellido = sc.next();
						miVuelo.reservar(nombre + " " + apellido, numAsiento);

					}

				}
				listaV[numV] = miVuelo;
				numV++;
				// TODO implementarlo de otra manera, porque esto es muy sucio
				// Si se puede usar el metodo split de String mejor
				while (contarLineas < 8 && sc.next().equals("===")) {
					contarLineas++;
				}
				contarLineas = 0;

			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + nom + "not found on " + getClass().getName());

		}

	}

	public String getNombre() {
		return nom;
	}

	public Vuelo buscarVuelo(String id) {
		for (int i = 0; i < numV; i++)
			if (listaV[i].getIdent().equals(id))
				return listaV[i];
		return null;

	}

	public void mostrarVuelos(String o, String d) {
		for (int i = 0; i < numV; i++)
			if (listaV[i].getOrigen().equals(o) && listaV[i].getDestino().equals(d) && listaV[i].hayLibres())
				System.out.println("\t" + listaV[i].toString() + "\t" + listaV[i].getLibres());
	}

	public String toString() {
		String cadenaAviones = "";
		for (int i = 0; i < numV; i++)
			cadenaAviones += nom + "\t" + listaV[i].toString() + "\t\t" + listaV[i].getLibres() + "\n";
		return cadenaAviones;
	}

	public void guardarAerolinea() throws Exception {
		PrintWriter printer = new PrintWriter(new File(nom + ".txt"));
		for (int i = 0; i < numV; i++) {
			Vuelo miVuelo = listaV[i];
			miVuelo.guardarVuelo(printer);
			printer.write(miVuelo.getNumPasajeros() + "\n");
			// TODO revisar la escritura de los pasajeros
			printer.write(LINEA_ANULAR + "\n");
		}

		printer.close();
	}

} // Aerolinea
