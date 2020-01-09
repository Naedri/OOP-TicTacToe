package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import composant_independant.Jeton;
import partie.PartieMorpion;

class Test_Permutation {

	@Test
	void testgrillePermutation() {
		System.out.println("testgrillePermutation EN COURS \n");

		Jeton jx = Jeton.JETON_X;
		Jeton jo = Jeton.JETON_O;

		PartieMorpion grille = new PartieMorpion(3, 3);
		grille.placerJeton(jx, 0, 0);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);
		grille.placerJeton(jo, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jo, 2, 2);

		grille.placerJeton(jo, 0, 1);
		grille.placerJeton(jx, 0, 2);

		PartieMorpion grille2 = new PartieMorpion(3, 3);
		grille2.placerJeton(jx, 0, 0);
		grille2.placerJeton(jx, 2, 0);
		grille2.placerJeton(jx, 2, 1);
		grille2.placerJeton(jo, 1, 0);
		grille2.placerJeton(jo, 1, 1);
		grille2.placerJeton(jo, 1, 2);
		grille2.placerJeton(jo, 2, 2);

		grille2.placerJeton(jx, 0, 1);
		grille2.placerJeton(jo, 0, 2);
		grille.permutationJeton(0, 1, 0, 2);

		assertTrue(grille2.estEgaleGrille(grille.getGrille()));

		System.out.println("test Permutation FAIT \n");
	}
}
