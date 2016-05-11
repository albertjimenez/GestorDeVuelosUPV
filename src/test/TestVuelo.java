package test;

import org.junit.Assert;

import modelo.Vuelo;

public class TestVuelo {

	@org.junit.Test
	public void test() {
		Vuelo v = new Vuelo("100", "Valencia", "Mallorca", "10:30", "21:30");
		System.out.println(v);
		Assert.assertEquals(0, v.getNumPasajeros(), 0);
		v.reservar("Yee", 'V');
		Assert.assertEquals(1, v.getNumPasajeros(), 0);
		Assert.assertEquals(true, v.hayLibres());

		v.reservar("Yee2", 'P');
		Assert.assertEquals(2, v.getNumPasajeros(), 0);

		v.cancelarReserva(2);
		Assert.assertEquals(1, v.getNumPasajeros(), 0);
		Assert.assertEquals(true, v.estaLibre(2));

	}

}
