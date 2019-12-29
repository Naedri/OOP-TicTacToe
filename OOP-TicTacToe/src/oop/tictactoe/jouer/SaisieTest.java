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

		
		assertTrue(In_Interaction.estValideSaisie(s1));
		assertTrue(In_Interaction.estValideSaisie(s10));
		assertTrue(In_Interaction.estValideSaisie(s1S));		
		
		assertFalse(In_Interaction.estValideSaisie(s1F));
		assertFalse(In_Interaction.estValideSaisie(s2F));
		assertFalse(In_Interaction.estValideSaisie(s3F));	
		assertFalse(In_Interaction.estValideSaisie(s4F));	
		assertFalse(In_Interaction.estValideSaisie(s5F));	
		assertFalse(In_Interaction.estValideSaisie(s6F));	
		
		int s1T[] = {1,2};
		int s1TR[] = In_Interaction.extraitCelluleSaisie(s1);
		assertEquals(s1T[0], In_Interaction.extraitCelluleSaisie(s1)[0]);
		assertEquals(s1T[1], In_Interaction.extraitCelluleSaisie(s1)[1]);
		assertEquals(s1T[0], s1TR[0]);
		assertEquals(s1T[1], s1TR[1]);
	}

}
