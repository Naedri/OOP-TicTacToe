//****************************************************************
//Test_forme.java
//****************************************************************
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import partie.Forme;

class Test_forme {

	@Test
	void test() {

		int[] distance;
		int[] orientation;

		Forme forme;

		// carre
		forme = new Forme(1);
		distance = new int[] { 1, 1, 1, 1 };
		orientation = new int[] { 2, 4, 6, 0 };
		assertArrayEquals(distance, forme.transForme(0).getDistance());
		assertArrayEquals(orientation, forme.transForme(0).getOrientation());

		orientation = new int[] { 0, 2, 4, 6 };
		assertArrayEquals(distance, forme.transForme(1).getDistance());
		assertArrayEquals(orientation, forme.transForme(1).getOrientation());

		orientation = new int[] { 0, 2, 4, 6 };
		assertArrayEquals(distance, forme.transForme(5).getDistance());
		assertArrayEquals(orientation, forme.transForme(5).getOrientation());

	}

}
