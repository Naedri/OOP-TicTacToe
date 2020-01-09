package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import composant_independant.Jeton;
import direction.Direction;
import partie.PartieMorpion;
import utilitaires.Utils_Grille_Evaluation_Alignement;

class Test_Morpion {

	@Test
	void testPartieMorpion() {
		System.out.println("testPartieMorpion EN COURS \n");

		Jeton jx = Jeton.JETON_X;
		Jeton jo = Jeton.JETON_O;

		PartieMorpion tour = new PartieMorpion();
		PartieMorpion grille = tour;
		// nbrAlignementXD

		tour.placerJeton(jo, 1, 2);
		tour.placerJeton(jo, 1, 0);
		tour.placerJeton(jo, 0, 0);
		tour.placerJeton(jx, 1, 1);

		tour.afficherGrille();
		String stro = tour.getLigneJetonOouF(1, 3, 3, Direction.OUEST);
		System.out.println(stro);
		assertEquals("OXO", stro);
		assertEquals('X', tour.getSymboleJetonOouF(1, 1));

		tour.ouvertToFermeJeton(1, 1);
		stro = tour.getLigneJetonOouF(1, 3, 3, Direction.OUEST);
		assertEquals("OxO", stro);
		assertEquals('x', tour.getSymboleJetonOouF(1, 1));

		tour.placerJeton(jx, 3, 0);
		assertEquals('X', tour.getSymboleJetonOouF(3, 0));
		tour.ouvertToFermeJeton(3, 0);
		assertEquals('x', tour.getSymboleJetonOouF(3, 0));

		tour.placerJeton(jo, 3, 1);
		assertEquals('O', tour.getSymboleJetonOouF(3, 1));
		tour.ouvertToFermeJeton(3, 1);
		assertEquals('o', tour.getSymboleJetonOouF(3, 1));

		tour.afficherGrille();
		tour.placerJeton(jo, 0, 1);
		tour.placerJeton(jo, 0, 2);
		tour.afficherGrille();
		assertEquals('O', tour.getSymboleJetonOouF(0, 0));

		tour.fermeAlignementXD(0, 2, 3);

		tour.afficherGrille();

		assertEquals(2, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(1, 2, 2, tour));
//		// pas ouest mais est car sens horaire et l orientation obeserve est avec les inverses
		Direction[] directions = new Direction[] { Direction.NORD, Direction.SUD_EST };
		assertArrayEquals(directions, Utils_Grille_Evaluation_Alignement.getDirectAlign(1, 2, 2, tour));
		grille.placerJeton(jo, 2, 2);
		assertEquals(Direction.NORD, Utils_Grille_Evaluation_Alignement.getDirectAlign(1, 2, 2, tour)[0]); // pas sud
																											// mais nord
																											// et pas
																											// est car
																											// nord est
		// le premier a faire un alignement

		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);

		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(1, 2, 3, tour));
		assertEquals(2, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(1, 2, 2, tour));
		assertEquals(Direction.NORD, Utils_Grille_Evaluation_Alignement.getDirectAlign(1, 2, 2, tour)[0]); // pas sud
																											// mais nord
																											// et pas
																											// est car
																											// nord est
		// le premier a faire un alignement

		// fermeAlignementXD avec 3
		grille = new PartieMorpion(6, 6);
		grille.placerJeton(jx, 0, 1);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 0, 3);
		grille.placerJeton(jx, 0, 4);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		grille.fermeAlignementXD(0, 2, 3);

		PartieMorpion grilleFerme = new PartieMorpion(6, 6);
		grilleFerme.placerJeton(jx, 0, 1);
		grilleFerme.placerJeton(jx, 0, 2);
		grilleFerme.placerJeton(jx, 0, 3);
		grilleFerme.placerJeton(jx, 0, 4);
		grilleFerme.placerJeton(jo, 1, 1);
		grilleFerme.placerJeton(jx, 1, 2);
		grilleFerme.ouvertToFermeJeton(0, 2);
		grilleFerme.ouvertToFermeJeton(0, 3);
		grilleFerme.ouvertToFermeJeton(0, 4);

		assertTrue(grille.estEgaleGrille(grilleFerme.getGrille()));
		assertTrue(grilleFerme.estEgaleGrille(grille.getGrille()));
//		
		System.out.println("fermerAlignement XD avec 3");
		grille.afficherGrille();
		grilleFerme.afficherGrille();

//		 fermeAlignementXD avec 4
		grille = new PartieMorpion(6, 6);
		grille.placerJeton(jx, 0, 1);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 0, 3);
		grille.placerJeton(jx, 0, 4);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		grille.afficherGrille();
		grille.getLigneJetonOouF(0, 2, 4, Direction.EST);
		grille.appartientAlignOouF(0, 2, 4, Direction.EST);
		grille.isDirectAvecAlignOouF(0, 2, 4);
		grille.fermeAlignementXD(0, 2, 4);

		grilleFerme = new PartieMorpion(6, 6);
		grilleFerme.placerJeton(jx, 0, 1);
		grilleFerme.placerJeton(jx, 0, 2);
		grilleFerme.placerJeton(jx, 0, 3);
		grilleFerme.placerJeton(jx, 0, 4);
		grilleFerme.placerJeton(jo, 1, 1);
		grilleFerme.placerJeton(jx, 1, 2);
		grilleFerme.ouvertToFermeJeton(0, 1);
		grilleFerme.ouvertToFermeJeton(0, 2);
		grilleFerme.ouvertToFermeJeton(0, 3);
		grilleFerme.ouvertToFermeJeton(0, 4);

		assertTrue(grille.estEgaleGrille(grilleFerme.getGrille()));
		assertTrue(grilleFerme.estEgaleGrille(grille.getGrille()));
//		
		System.out.println("fermerAlignement XD avec 4");
		grille.afficherGrille();
		grilleFerme.afficherGrille();

		// fermer avec Coordonnees
		// a partir de getCoordAlignJetonsXDOouF
		grille = new PartieMorpion(3, 3);
		grille.placerJeton(jx, 0, 0);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);
		grille.placerJeton(jo, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jo, 2, 2);

		grille.placerJeton(jo, 0, 1);
		grille.placerJeton(jx, 0, 2);

		int[][] jetonsAFermerEvalue = grille.getCoordAlignJetonsXDOouF(0, 2, 3);
		int[][] jetonsAFermerExpected = new int[][] { { 0, 2 }, { 1, 2 }, { 2, 2 } };
		assertArrayEquals(jetonsAFermerExpected, jetonsAFermerEvalue);

		System.out.println("fermerAlignement XD avec coord");

		System.out.println("testPartieMorpion FAIT \n");
	}

}
