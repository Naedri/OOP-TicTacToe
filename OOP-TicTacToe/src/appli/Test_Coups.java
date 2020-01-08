package appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grille.Direction;
import grille.Forme;
import grille.Jeton;

class Test_Coups {

	@Test
	void testTourTicTacToe() {
		System.out.println("testTourTicTacToe EN COURS \n");

		TourTicTacToe grille = new TourTicTacToe();
		Jeton jo = Jeton.JETON_O;
		Joueur joueurO = new Joueur(jo);
		TourTicTacToe tourO = new TourTicTacToe(grille, joueurO);

		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());

		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); // on commence a 0

		// jO sans autres jetons
		assertTrue(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD_OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD_EST));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.EST));
		assertTrue(tourO.isAlignement1D1DI(2, 2, 3, Direction.SUD_EST)); // attention c est true parce que la foncion
																			// regarde dans les deux sens
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.SUD));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.SUD_OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.OUEST));
		assertTrue(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD_OUEST));

		// ajout autres jetons
		Jeton jx = Jeton.JETON_X;
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);

		// jO avec autres jetons
		assertTrue(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD_OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD_EST));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.EST));
		assertTrue(tourO.isAlignement1D1DI(2, 2, 3, Direction.SUD_EST)); // attention c est true parce que la foncion
																			// regarde dans les deux sens
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.SUD));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.SUD_OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 2, 3, Direction.OUEST));
		assertTrue(tourO.isAlignement1D1DI(2, 2, 3, Direction.NORD_OUEST));

		// jx avec jo mais ne marque pas de points
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.NORD_OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.NORD));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.NORD_EST));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.EST));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.SUD_EST));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.SUD));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.SUD_OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.OUEST));
		assertFalse(tourO.isAlignement1D1DI(2, 1, 3, Direction.NORD_OUEST));

		// multiple alignements
		// alignements en diagonal
		assertEquals(0, tourO.nbrDirectAvecAlign(2, 0, 3));
		assertEquals(1, tourO.nbrDirectAvecAlign(2, 2, 3));

		// alignements horizontaux
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jo, 1, 0);
		assertEquals(0, tourO.nbrDirectAvecAlign(2, 0, 3));
		assertEquals(0, tourO.nbrDirectAvecAlign(2, 1, 3));
		assertEquals(1, tourO.nbrDirectAvecAlign(2, 2, 3));
		assertEquals(1, tourO.nbrDirectAvecAlign(1, 2, 3));

		System.out.println("testTourTicTacToe FAIT \n");
	}

	@Test
	void testTourMorpion() {
		System.out.println("testTourMorpion EN COURS \n");

		Jeton jx = Jeton.JETON_X;
		Jeton jo = Jeton.JETON_O;

		Grille grille = new Grille();
		Joueur joueurO = new Joueur(jo);
		TourMorpion tour = new TourMorpion(grille, joueurO);

		// nbrAlignementXD

		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jo, 1, 0);
		grille.placerJeton(jo, 0, 0);
		grille.placerJeton(jo, 1, 1);
		assertEquals(1, tour.nbrDirectAvecAlign(1, 2, 3));
		assertEquals(1, tour.nbrDirectAvecAlign(1, 2, 2));
		assertEquals(Direction.EST, tour.directionAlignementXD(1, 2, 3)); // pas ouest mais est car sens horaire et l
																			// orientation obeserve est avec les
																			// inverses
		grille.placerJeton(jo, 2, 2);
		assertEquals(Direction.NORD, tour.directionAlignementXD(1, 2, 2)); // pas sud mais nord et pas est car nord est
																			// le premier a faire un alignement

		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);

		assertEquals(1, tour.nbrDirectAvecAlign(1, 2, 3));
		assertEquals(2, tour.nbrDirectAvecAlign(1, 2, 2));

		assertEquals(Direction.EST, tour.directionAlignementXD(1, 2, 3)); // pas ouest mais est car sens horaire et l
																			// orientation obeserve est avec les
																			// inverses
		assertEquals(Direction.NORD, tour.directionAlignementXD(1, 2, 2)); // pas sud mais nord et pas est car nord est
																			// le premier a faire un alignement

		// fermeAlignementXD
		tour.fermeAlignementXD(1, 2, 3);

		Grille grilleFerme = new Grille();
		grilleFerme.placerJeton(jo, 1, 2);
		grilleFerme.placerJeton(jo, 1, 0);
		grilleFerme.placerJeton(jo, 0, 0);
		grilleFerme.placerJeton(jo, 1, 1);
		grilleFerme.placerJeton(jo, 2, 2);
		grilleFerme.placerJeton(jx, 2, 0);
		grilleFerme.placerJeton(jx, 2, 1);

		assertFalse(grille.estEgale(grilleFerme));

		grilleFerme.ouvertToFermeJeton(1, 0);
		grilleFerme.ouvertToFermeJeton(1, 1);
		grilleFerme.ouvertToFermeJeton(1, 2);

		assertTrue(grille.estEgale(grilleFerme));

		grille.placerJeton(jo, 0, 1);
		grille.placerJeton(jo, 0, 2);
		assertEquals(1, tour.nbrDirectAvecAlign(0, 2, 2));
		assertEquals(1, tour.nbrDirectAvecAlign(0, 2, 3));
		assertEquals(Direction.EST, tour.directionAlignementXD(0, 2, 3)); // pas ouest mais est car sens horaire et l
																			// orientation obeserve est avec les
																			// inverses
		assertEquals(Direction.EST, tour.directionAlignementXD(0, 2, 2)); // pas ouest mais est car sens horaire et l
																			// orientation obeserve est avec les
																			// inverses

		grilleFerme = new Grille(7, 7);
		grille = new Grille(7, 7);

		grille.placerJeton(jo, 0, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2);

		for (int i = 0; i < 3; i++) {
			grilleFerme.placerJeton(jo, i, i);
			grilleFerme.ouvertToFermeJeton(i, i);
		}

		assertFalse(grille.estEgale(grilleFerme));
		tour = new TourMorpion(grille, joueurO);
		assertEquals(1, tour.nbrDirectAvecAlign(0, 0, 3));
		assertEquals(1, tour.nbrDirectAvecAlign(1, 1, 3));
		assertEquals(1, tour.nbrDirectAvecAlign(2, 2, 3));

		assertEquals(1, tour.nbrDirectAvecAlign(1, 1, 2)); // ce n est pas le nombre d alignement mais le nombre de
															// direction pour laquelle un alignement a ete trouvé

		tour.fermeAlignementXD(2, 2, 3);

		assertTrue(grille.estEgale(grilleFerme));
//		System.out.println("fermerAlignement XD");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();

		// fermeAlignementXD avec 3
		grille = new Grille(6, 6);
		joueurO = new Joueur(Jeton.JETON_O);
		grille.placerJeton(jx, 0, 1);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 0, 3);
		grille.placerJeton(jx, 0, 4);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		tour = new TourMorpion(grille, joueurO);
		tour.fermeAlignementXD(0, 2, 3);

		grilleFerme = new Grille(6, 6);
		grilleFerme.placerJeton(jx, 0, 1);
		grilleFerme.placerJeton(jo, 1, 1);
		grilleFerme.placerJeton(jx, 1, 2);
		Jeton jxmin = Jeton.JETON_X_MIN;
		grilleFerme.placerJetonFerme(jxmin, 0, 2);
		grilleFerme.placerJetonFerme(jxmin, 0, 3);
		grilleFerme.placerJetonFerme(jxmin, 0, 4);

		assertTrue(grille.estEgale(grilleFerme));
		assertTrue(grilleFerme.estEgale(grille));
//		
//		System.out.println("fermerAlignement XD avec 3");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();
//		
		// fermeAlignementXD avec 4
		grille = new Grille(6, 6);
		joueurO = new Joueur(Jeton.JETON_O);
		grille.placerJeton(jx, 0, 1);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 0, 3);
		grille.placerJeton(jx, 0, 4);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		tour = new TourMorpion(grille, joueurO);
		tour.fermeAlignementXD(0, 2, 4);

		grilleFerme = new Grille(6, 6);
		grilleFerme.placerJeton(jo, 1, 1);
		grilleFerme.placerJeton(jx, 1, 2);
		jxmin = Jeton.JETON_X_MIN;
		grilleFerme.placerJetonFerme(jxmin, 0, 1);
		grilleFerme.placerJetonFerme(jxmin, 0, 2);
		grilleFerme.placerJetonFerme(jxmin, 0, 3);
		grilleFerme.placerJetonFerme(jxmin, 0, 4);

		assertTrue(grille.estEgale(grilleFerme));
		assertTrue(grilleFerme.estEgale(grille));

//		System.out.println("fermerAlignement XD avec 4");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();

		// fermeAlignementXD avec 2
		grille = new Grille(6, 6);
		joueurO = new Joueur(Jeton.JETON_O);
		grille.placerJeton(jx, 0, 1);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 0, 3);
		grille.placerJeton(jx, 0, 4);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		tour = new TourMorpion(grille, joueurO);
		tour.fermeAlignementXD(0, 2, 2);

		grilleFerme = new Grille(6, 6);
		grilleFerme.placerJeton(jo, 1, 1);
		grilleFerme.placerJeton(jx, 0, 1);
		grilleFerme.placerJeton(jx, 0, 3);
		grilleFerme.placerJeton(jx, 0, 4);
		jxmin = Jeton.JETON_X_MIN;
		grilleFerme.placerJetonFerme(jxmin, 0, 2);
		grilleFerme.placerJetonFerme(jxmin, 1, 2);

		assertTrue(grille.estEgale(grilleFerme));
		assertTrue(grilleFerme.estEgale(grille));
//		System.out.println("fermerAlignement XD avec 2");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();

		System.out.println("testTourMorpion FAIT \n");
	}

//	@Test
//	void testTourPermutation(){
//		System.out.println("testTourPermutation EN COURS \n");
//		
//		Jeton jx = Jeton.JETON_X;
//		Jeton jo = Jeton.JETON_O;
//		
//		//tour avec deux joueurs DIFFERENTS AVEC s=coup gagnant
//		Grille grille = new Grille();
//		grille.placerJeton(jx, 0, 0);
//		grille.placerJeton(jx, 2, 0);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 2, 1);
//		grille.placerJeton(jo, 0, 1);
//		grille.placerJeton(jo, 1, 0);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jo, 1, 2);
//		grille.placerJeton(jo, 2, 2);
//		
//		Joueur joueuro = new Joueur(jo);
//		Joueur joueurx = new Joueur(jx);
//		TourPermutation tour = new TourPermutation(grille, joueurx, joueuro);
//		
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		System.out.println("faire : 1-2 puis 1-3");
//		//1-2
//		//1-3
//		tour.jouerCoup();
//		tour.evaluerCoup();
//		
//		assertEquals(1, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		
//		//tour avec deux joueurs differents INVERSE AVEC coup gagnant
//		grille = new Grille();
//		grille.placerJeton(jx, 0, 0);
//		grille.placerJeton(jx, 2, 0);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 2, 1);
//		grille.placerJeton(jo, 0, 1);
//		grille.placerJeton(jo, 1, 0);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jo, 1, 2);
//		grille.placerJeton(jo, 2, 2);
//		
//		joueuro = new Joueur(jo);
//		joueurx = new Joueur(jx);
//		tour = new TourPermutation(grille, joueuro, joueurx);
//		
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		System.out.println("faire : 1-2 puis 1-3");
//		//1-2
//		//1-3
//		tour.jouerCoup();
//		tour.evaluerCoup();
//		
//		assertEquals(1, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());		
//		
//		//tour avec deux joueurs DIFFERENT mais sans coup gagnant
//		grille = new Grille();
//		grille.placerJeton(jx, 0, 0);
//		grille.placerJeton(jx, 2, 0);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 2, 1);
//		grille.placerJeton(jo, 0, 1);
//		grille.placerJeton(jo, 1, 0);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jo, 1, 2);
//		grille.placerJeton(jo, 2, 2);
//		
//		joueuro = new Joueur(jo);
//		joueurx = new Joueur(jx);
//		
//		tour = new TourPermutation(grille, joueurx, joueuro);
//		
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		System.out.println("faire : 2-1 puis 3-1");
//		//2-1
//		//3-1
//		tour.jouerCoup();
//		tour.evaluerCoup();
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		
//		//tour avec deux joueurs DIFFERENT avec coup gagnant diagonal
//		grille = new Grille();
//		grille.placerJeton(jx, 0, 0);
//		grille.placerJeton(jx, 2, 0);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 2, 1);
//		grille.placerJeton(jo, 0, 1);
//		grille.placerJeton(jo, 1, 0);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jo, 1, 2);
//		grille.placerJeton(jo, 2, 2);
//		
//		joueuro = new Joueur(jo);
//		joueurx = new Joueur(jx);
//		
//		tour = new TourPermutation(grille, joueurx, joueuro);
//		
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		grille.afficherGrille();
//		System.out.println("faire : 2-2 puis 3-2");
//		//2-2
//		//3-2
//		tour.jouerCoup();
//		tour.evaluerCoup();
//		grille.afficherGrille();
//		assertEquals(0, joueuro.getScore());
//		assertEquals(1, joueurx.getScore());
//		
//		
//		System.out.println("testTourPermutation FAIT \n");
//	}
//	
	@Test
	void testTourForme() {
		System.out.println("testTourForme EN COURS \n");

		Grille grille = new Grille();
		Jeton jx = Jeton.JETON_X;
		Jeton jo = Jeton.JETON_O;
		grille.placerJeton(jo, 0, 0);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 2, 1);
		grille.placerJeton(jo, 0, 1);
		grille.placerJeton(jo, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		grille.placerJeton(jo, 2, 2);

		Joueur joueuro = new Joueur(jo);
		Forme carre = new Forme(1);
		Forme losange = new Forme(2);
		Forme etoile = new Forme(3);
		assertEquals(4, carre.getNbrPoint());
		assertEquals(4, losange.getNbrPoint());
		assertEquals(5, etoile.getNbrPoint());

		TourForme tour;
		tour = new TourForme(grille, joueuro, carre);

		assertTrue(tour.existeForme(0, 0, carre));
		assertTrue(tour.existeForme(1, 1, carre));
		assertFalse(tour.existeForme(2, 2, carre));
		assertFalse(tour.existeForme(0, 2, carre));
		assertFalse(tour.existeForme(2, 0, carre));
		assertFalse(tour.existeForme(1, 2, carre));
		assertFalse(tour.existeForme(2, 1, carre));
		assertFalse(tour.existeForme(2, 2, carre));

		// getCoordForme
		int[][] coordTest = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } }; // tour du carre dans le sens des
																					// aiguilles d une montre

		int[][] coordTest1 = new int[carre.getNbrPoint()][2];
		coordTest1 = tour.getCoordForme(0, 0, carre);

		assertArrayEquals(coordTest, coordTest1);

		// getJetonForme
		String jetonCarre = tour.getJetonForme(0, 0, carre);
		String StrCarre = "OOOO";
		assertEquals(jetonCarre, StrCarre);

		grille.permutationJeton(0, 2, 0, 1);
		jetonCarre = tour.getJetonForme(0, 0, carre);
		StrCarre = "OXOO";
		assertEquals(jetonCarre, StrCarre);
		jetonCarre = tour.getJetonForme(0, 1, carre);
		StrCarre = "XOXO";
		assertEquals(jetonCarre, StrCarre);

		grille.permutationJeton(0, 2, 0, 1);

		// getJetonFormeAll
		grille = new Grille();
		grille.placerJeton(jo, 0, 0);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 2, 1);

		tour = new TourForme(grille, joueuro, carre);

		System.out.println(tour.getJetonFormeAll(1, 1, carre));
		grille.afficherGrille();

		grille.placerJeton(jo, 2, 2);

		System.out.println(tour.getJetonFormeAll(1, 1, carre));
		grille.afficherGrille();

		grille.placerJeton(jo, 1, 1);

		System.out.println(tour.getJetonFormeAll(1, 1, carre));
		grille.afficherGrille();

		// estCompleteForme
		grille = new Grille();
		grille.placerJeton(jo, 0, 0);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 0, 2);
		grille.placerJeton(jx, 2, 1);
		grille.placerJeton(jo, 0, 1);
		grille.placerJeton(jo, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jx, 1, 2);
		grille.placerJeton(jo, 2, 2);
		grille.afficherGrille();

		tour = new TourForme(grille, joueuro, carre);
		assertTrue(tour.estCompleteForme(0, 0));
		assertTrue(tour.estCompleteForme(1, 1));
		assertTrue(tour.estCompleteForme(0, 1));
		assertTrue(tour.estCompleteForme(1, 0));

		assertFalse(tour.estCompleteForme(0, 2));
		assertFalse(tour.estCompleteForme(1, 2));
		assertFalse(tour.estCompleteForme(2, 2));
		assertFalse(tour.estCompleteForme(2, 0));
		assertFalse(tour.estCompleteForme(2, 1));
		assertFalse(tour.estCompleteForme(2, 2));

		System.out.println("testTourForme FAIT \n");
	}

}
