package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import interaction.Messages_Saisie;

class Test_Saisie {

	@Test
	void test() {
		String s1 = "1-2";
		String s10 = "01-0002";
		String s1S = "1 - 2";

		String s1F = "22";
		String s2F = "1-2-3";
		String s3F = "1 - &";
		String s4F = "-1-2-";
		String s5F = "a-b";
		String s6F = "-1-2";

		assertTrue(Messages_Saisie.estValideSaisie(s1));
		assertTrue(Messages_Saisie.estValideSaisie(s10));
		assertTrue(Messages_Saisie.estValideSaisie(s1S));

		assertFalse(Messages_Saisie.estValideSaisie(s1F));
		assertFalse(Messages_Saisie.estValideSaisie(s2F));
		assertFalse(Messages_Saisie.estValideSaisie(s3F));
		assertFalse(Messages_Saisie.estValideSaisie(s4F));
		assertFalse(Messages_Saisie.estValideSaisie(s5F));
		assertFalse(Messages_Saisie.estValideSaisie(s6F));

		int s1T[] = { 1, 2 };
		int s1TR[] = Messages_Saisie.extraitCelluleSaisie(s1);
		assertEquals(s1T[0], Messages_Saisie.extraitCelluleSaisie(s1)[0]);
		assertEquals(s1T[1], Messages_Saisie.extraitCelluleSaisie(s1)[1]);
		assertEquals(s1T[0], s1TR[0]);
		assertEquals(s1T[1], s1TR[1]);
	}

}
