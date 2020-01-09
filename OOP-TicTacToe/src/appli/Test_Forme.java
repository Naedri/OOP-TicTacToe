package appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grille.Forme;
import grille.Jeton;
import utilitaires.Utils_Grille_Evaluation_Forme;

class Test_Forme {

	@Test
	void testPartieForme() {
		System.out.println("testPartieForme EN COURS \n");

		PartieForme tour = new PartieForme(3,3);
		Jeton jx = Jeton.JETON_X;
		Jeton jo = Jeton.JETON_O;
		tour.placerJeton(jo, 0, 0);
		tour.placerJeton(jx, 2, 0);
		tour.placerJeton(jx, 0, 2);
		tour.placerJeton(jx, 2, 1);
		tour.placerJeton(jo, 0, 1);
		tour.placerJeton(jo, 1, 0);
		tour.placerJeton(jo, 1, 1);
		tour.placerJeton(jx, 1, 2);
		tour.placerJeton(jo, 2, 2);

		Forme carre = new Forme(1);
		Forme losange = new Forme(2);
		Forme croix = new Forme(3);
		assertEquals(4, carre.getNbrPoint());
		assertEquals(4, losange.getNbrPoint());
		assertEquals(5, croix.getNbrPoint());
		
		assertTrue(Utils_Grille_Evaluation_Forme.existeForme(0, 0, tour,carre));
		assertTrue(Utils_Grille_Evaluation_Forme.existeForme(1, 1, tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 2, tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(0, 2, tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 0, tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(1, 2, tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 1, tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 2, tour,carre));

		// getCoordForme
		int[][] coordTest = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } }; // tour du carre dans le sens des
																					// aiguilles d une montre

		int[][] coordTest1 = new int[carre.getNbrPoint()][2];
		coordTest1 = Utils_Grille_Evaluation_Forme.getCoordForme(0, 0, tour, carre);

		assertArrayEquals(coordTest, coordTest1);

		// getJetonForme
		String jetonCarre = Utils_Grille_Evaluation_Forme.getJetonForme(0, 0,tour , carre);
		String StrCarre = "OOOO";
		
		assertEquals("OOOO", jetonCarre);;
		tour.afficherGrille();
		tour.placerJeton(jo, 0, 2);
		tour.placerJeton(jx, 0, 1);
		jetonCarre = Utils_Grille_Evaluation_Forme.getJetonForme(0, 0,tour, carre);
		StrCarre = "OXOO";
		assertEquals(jetonCarre, StrCarre);
		jetonCarre = Utils_Grille_Evaluation_Forme.getJetonForme(0, 1,tour, carre);
		StrCarre = "XOXO";
		assertEquals(jetonCarre, StrCarre);

		// getJetonFormeAll
		tour = new PartieForme(3,3);
		tour.placerJeton(jo, 0, 0);
		tour.placerJeton(jx, 2, 0);
		tour.placerJeton(jx, 0, 2);
		tour.placerJeton(jx, 2, 1);

		System.out.println(Utils_Grille_Evaluation_Forme.getJetonFormeAll(1, 1, tour,carre));
		tour.afficherGrille();

		tour.placerJeton(jo, 2, 2);

		System.out.println(Utils_Grille_Evaluation_Forme.getJetonFormeAll(1, 1, tour,carre));
		tour.afficherGrille();

		tour.placerJeton(jo, 1, 1);

		System.out.println(Utils_Grille_Evaluation_Forme.getJetonFormeAll(1, 1, tour,carre));
		tour.afficherGrille();

		// estCompleteForme
		tour = new PartieForme(3,3);
		tour.placerJeton(jo, 0, 0);
		tour.placerJeton(jx, 2, 0);
		tour.placerJeton(jx, 0, 2);
		tour.placerJeton(jx, 2, 1);
		tour.placerJeton(jo, 0, 1);
		tour.placerJeton(jo, 1, 0);
		tour.placerJeton(jo, 1, 1);
		tour.placerJeton(jx, 1, 2);
		tour.placerJeton(jo, 2, 2);
		tour.afficherGrille();

		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(0, 0,tour,carre));
		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(1, 1,tour,carre));
		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(0, 1,tour,carre));
		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(1, 0,tour,carre));

		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(0, 2,tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(1, 2,tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 2,tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 0,tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 1,tour,carre));
		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 2,tour,carre));

		System.out.println("testPartieForme FAIT \n");
	}

}
