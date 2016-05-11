package test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import modelo.Aerolinea;
import modelo.Vuelo;

public class TestAerolinea {

	@Test
	public void testAirFrance() {
		System.out.println("Pruebo constructor");
		Aerolinea ae = null;
		try {
			ae = new Aerolinea("AirFrance");

		} catch (Exception e) {
			System.out.println(ae == null);
			fail("He faileado");
		}

		Assert.assertEquals(ae.getNombre(), "AirFrance");
		Assert.assertEquals(ae.buscarVuelo("AF201"), new Vuelo("AF201", "Paris", "Valencia", "15:00", "21:15"));
		ae.mostrarVuelos("Paris", "Valencia");
		try {
			ae.guardarAerolinea();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAlitalia() {
		System.out.println("Pruebo constructor");
		Aerolinea ae = null;
		try {
			ae = new Aerolinea("Alitalia");

		} catch (Exception e) {
			System.out.println(ae == null);
			fail("He faileado");
		}

		Assert.assertEquals(ae.getNombre(), "Alitalia");
		Assert.assertEquals(ae.buscarVuelo("AI201"), new Vuelo("AI201", "Milan", "Valencia", "14:10", "18:35"));
		ae.mostrarVuelos("Milan", "Valencia");
		try {
			ae.guardarAerolinea();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
