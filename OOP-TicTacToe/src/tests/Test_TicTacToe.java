package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import composant_independant.Jeton;
import direction.Direction;
import partie.PartieTicTacToe;
import utilitaires.Utils_Grille_Evaluation_Alignement;

class Test_TicTacToe {

	@Test
	void testPartieTicTacToe() {
		System.out.println("testPartieTicTacToe EN COURS \n");

		PartieTicTacToe grille = new PartieTicTacToe();
		Jeton jo = Jeton.JETON_O;

		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());

		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); // on commence a 0

		// jO sans autres jetons
		assertTrue(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD_OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD_EST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.EST, grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.SUD_EST, grille)); // attention
																											// c est
																											// true
																											// parce que
																											// la
																											// foncion
		// regarde dans les deux sens
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.SUD, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.SUD_OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.OUEST, grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD_OUEST, grille));

		// ajout autres jetons
		Jeton jx = Jeton.JETON_X;
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);

		// jO avec autres jetons
		assertTrue(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD_OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD_EST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.EST, grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.SUD_EST, grille)); // attention
																											// c est
																											// true
																											// parce que
																											// la
																											// foncion
		// regarde dans les deux sens
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.SUD, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.SUD_OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.OUEST, grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 2, 3, Direction.NORD_OUEST, grille));

		// jx avec jo mais ne marque pas de points
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.NORD_OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.NORD, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.NORD_EST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.EST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.SUD_EST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.SUD, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.SUD_OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.OUEST, grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.isDirectInAlign(2, 1, 3, Direction.NORD_OUEST, grille));

		// multiple alignements
		// alignements en diagonal
		assertEquals(0, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 0, 3, grille));
		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 2, 3, grille));

		// alignements horizontaux
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jo, 1, 0);
		assertEquals(0, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 0, 3, grille));
		assertEquals(0, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 1, 3, grille));
		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 2, 3, grille));
		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(1, 2, 3, grille));

		System.out.println("testPartieTicTacToe FAIT \n");
	}
}
