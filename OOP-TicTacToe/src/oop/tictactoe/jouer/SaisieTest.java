package oop.tictactoe.jouer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SaisieTest {

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

		
		assertTrue(Messages.estValideSaisie(s1));
		assertTrue(Messages.estValideSaisie(s10));
		assertTrue(Messages.estValideSaisie(s1S));		
		
		assertFalse(Messages.estValideSaisie(s1F));
		assertFalse(Messages.estValideSaisie(s2F));
		assertFalse(Messages.estValideSaisie(s3F));	
		assertFalse(Messages.estValideSaisie(s4F));	
		assertFalse(Messages.estValideSaisie(s5F));	
		assertFalse(Messages.estValideSaisie(s6F));	
		
		int s1T[] = {1,2};
		int s1TR[] = Messages.extraitCelluleSaisie(s1);
		assertEquals(s1T[0], Messages.extraitCelluleSaisie(s1)[0]);
		assertEquals(s1T[1], Messages.extraitCelluleSaisie(s1)[1]);
		assertEquals(s1T[0], s1TR[0]);
		assertEquals(s1T[1], s1TR[1]);
	}

}
