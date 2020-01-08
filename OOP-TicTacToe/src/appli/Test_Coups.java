package appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grille.Direction;
import grille.Forme;
import grille.Jeton;
import utilitaires.Utils_Grille_Evaluation_Alignement;
import utilitaires.Utils_Grille_Evaluation_Forme;

class Test_Coups {

	@Test
	void testPartieTicTacToe() {
		System.out.println("testPartieTicTacToe EN COURS \n");

		PartieTicTacToe grille = new PartieTicTacToe();
		Jeton jo = Jeton.JETON_O;
		Joueur joueurO = new Joueur(jo);

		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());

		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); // on commence a 0

		// jO sans autres jetons
		assertTrue(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD_OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD_EST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.EST,grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.SUD_EST,grille)); // attention c est true parce que la foncion
																			// regarde dans les deux sens
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.SUD,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.SUD_OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.OUEST,grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD_OUEST,grille));

		// ajout autres jetons
		Jeton jx = Jeton.JETON_X;
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);

		// jO avec autres jetons
		assertTrue(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD_OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD_EST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.EST,grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.SUD_EST,grille)); // attention c est true parce que la foncion
																			// regarde dans les deux sens
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.SUD,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.SUD_OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.OUEST,grille));
		assertTrue(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 2, 3, Direction.NORD_OUEST,grille));

		// jx avec jo mais ne marque pas de points
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.NORD_OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.NORD,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.NORD_EST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.EST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.SUD_EST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.SUD,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.SUD_OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.OUEST,grille));
		assertFalse(Utils_Grille_Evaluation_Alignement.appartientAlign(2, 1, 3, Direction.NORD_OUEST,grille));

		// multiple alignements
		// alignements en diagonal
		assertEquals(0, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 0, 3,grille));
		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 2, 3,grille));

		// alignements horizontaux
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jo, 1, 0);
		assertEquals(0, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 0, 3,grille));
		assertEquals(0, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 1, 3,grille));
		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(2, 2, 3,grille));
		assertEquals(1, Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(1, 2, 3,grille));

		System.out.println("testPartieTicTacToe FAIT \n");
	}

	@Test
	void testPartieMorpion() {
		System.out.println("testPartieMorpion EN COURS \n");

		Jeton jx = Jeton.JETON_X;
		Jeton jo = Jeton.JETON_O;

		PartieMorpion tour = new PartieMorpion();

		// nbrAlignementXD

		tour.placerJeton(jo, 1, 2);
		tour.placerJeton(jo, 1, 0);
		tour.placerJeton(jo, 0, 0);
		tour.placerJeton(jx, 1, 1);
		
		tour.afficherGrille();
		String stro = tour.getLigneJetonOouF(1, 3, 3, Direction.OUEST);
		System.out.println(stro);
		assertEquals("OXO", stro);
		assertEquals('X',tour.getSymboleJetonOouF(1,1));

		tour.ouvertToFermeJeton(1, 1);
		stro = tour.getLigneJetonOouF(1, 3, 3, Direction.OUEST);
		assertEquals("OxO", stro);
		assertEquals('x',tour.getSymboleJetonOouF(1,1));
		
		tour.placerJeton(jx, 3, 0);
		assertEquals('X',tour.getSymboleJetonOouF(3,0));
		tour.ouvertToFermeJeton(3, 0);
		assertEquals('x',tour.getSymboleJetonOouF(3,0));
		
		tour.placerJeton(jo, 3, 1);
		assertEquals('O',tour.getSymboleJetonOouF(3,1));
		tour.ouvertToFermeJeton(3, 1);
		assertEquals('o',tour.getSymboleJetonOouF(3,1));


		tour.afficherGrille();
		tour.placerJeton(jo, 0, 1);
		tour.placerJeton(jo, 0, 2);
		tour.afficherGrille();
		assertEquals('O',tour.getSymboleJetonOouF(0,0));

		
		tour.fermeAlignementXD(0, 2, 3);


//		assertEquals(1, tour.nbrDirectAvecAlign(1, 2, 2));
//		assertEquals(Direction.EST, tour.directionAlignementXD(1, 2, 3)); // pas ouest mais est car sens horaire et l
//																			// orientation obeserve est avec les
//																			// inverses
//		grille.placerJeton(jo, 2, 2);
//		assertEquals(Direction.NORD, tour.directionAlignementXD(1, 2, 2)); // pas sud mais nord et pas est car nord est
//																			// le premier a faire un alignement
//
//		grille.placerJeton(jx, 2, 0);
//		grille.placerJeton(jx, 2, 1);
//
//		assertEquals(1, tour.nbrDirectAvecAlign(1, 2, 3));
//		assertEquals(2, tour.nbrDirectAvecAlign(1, 2, 2));
//
//		assertEquals(Direction.EST, tour.directionAlignementXD(1, 2, 3)); // pas ouest mais est car sens horaire et l
//																			// orientation obeserve est avec les
//																			// inverses
//		assertEquals(Direction.NORD, tour.directionAlignementXD(1, 2, 2)); // pas sud mais nord et pas est car nord est
//																			// le premier a faire un alignement

//		// fermeAlignementXD
//		tour.fermeAlignementXD(1, 2, 3);
//
//		PartieTicTacToe grilleFerme = new PartieTicTacToe();
//		grilleFerme.placerJeton(jo, 1, 2);
//		grilleFerme.placerJeton(jo, 1, 0);
//		grilleFerme.placerJeton(jo, 0, 0);
//		grilleFerme.placerJeton(jo, 1, 1);
//		grilleFerme.placerJeton(jo, 2, 2);
//		grilleFerme.placerJeton(jx, 2, 0);
//		grilleFerme.placerJeton(jx, 2, 1);
//
//		assertFalse(grille.estEgale(grilleFerme));
//
//		grilleFerme.ouvertToFermeJeton(1, 0);
//		grilleFerme.ouvertToFermeJeton(1, 1);
//		grilleFerme.ouvertToFermeJeton(1, 2);
//
//		assertTrue(grille.estEgale(grilleFerme));
//
//		grille.placerJeton(jo, 0, 1);
//		grille.placerJeton(jo, 0, 2);
//		assertEquals(1, tour.nbrDirectAvecAlign(0, 2, 2));
//		assertEquals(1, tour.nbrDirectAvecAlign(0, 2, 3));
//		assertEquals(Direction.EST, tour.directionAlignementXD(0, 2, 3)); // pas ouest mais est car sens horaire et l
//																			// orientation obeserve est avec les
//																			// inverses
//		assertEquals(Direction.EST, tour.directionAlignementXD(0, 2, 2)); // pas ouest mais est car sens horaire et l
//																			// orientation obeserve est avec les
//																			// inverses
//
//		grilleFerme = new PartieTicTacToe(7, 7);
//		grille = new PartieTicTacToe(7, 7);
//
//		grille.placerJeton(jo, 0, 0);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jo, 2, 2);
//
//		for (int i = 0; i < 3; i++) {
//			grilleFerme.placerJeton(jo, i, i);
//			grilleFerme.ouvertToFermeJeton(i, i);
//		}
//
//		assertFalse(grille.estEgale(grilleFerme));
//		tour = new PartieMorpion(grille, joueurO);
//		assertEquals(1, tour.nbrDirectAvecAlign(0, 0, 3));
//		assertEquals(1, tour.nbrDirectAvecAlign(1, 1, 3));
//		assertEquals(1, tour.nbrDirectAvecAlign(2, 2, 3));
//
//		assertEquals(1, tour.nbrDirectAvecAlign(1, 1, 2)); // ce n est pas le nombre d alignement mais le nombre de
//															// direction pour laquelle un alignement a ete trouvé
//
//		tour.fermeAlignementXD(2, 2, 3);
//
//		assertTrue(grille.estEgale(grilleFerme));
//		System.out.println("fermerAlignement XD");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();

		// fermeAlignementXD avec 3
//		grille = new PartieTicTacToe(6, 6);
//		joueurO = new Joueur(Jeton.JETON_O);
//		grille.placerJeton(jx, 0, 1);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 0, 3);
//		grille.placerJeton(jx, 0, 4);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jx, 1, 2);
//		tour = new PartieMorpion(grille, joueurO);
//		tour.fermeAlignementXD(0, 2, 3);
//
//		grilleFerme = new PartieTicTacToe(6, 6);
//		grilleFerme.placerJeton(jx, 0, 1);
//		grilleFerme.placerJeton(jo, 1, 1);
//		grilleFerme.placerJeton(jx, 1, 2);
//		Jeton jxmin = Jeton.JETON_X;
//		grilleFerme.placerJetonFerme(jxmin, 0, 2);
//		grilleFerme.placerJetonFerme(jxmin, 0, 3);
//		grilleFerme.placerJetonFerme(jxmin, 0, 4);
//
//		assertTrue(grille.estEgale(grilleFerme));
//		assertTrue(grilleFerme.estEgale(grille));
////		
//		System.out.println("fermerAlignement XD avec 3");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();
//		
		// fermeAlignementXD avec 4
//		grille = new PartieTicTacToe(6, 6);
//		joueurO = new Joueur(Jeton.JETON_O);
//		grille.placerJeton(jx, 0, 1);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 0, 3);
//		grille.placerJeton(jx, 0, 4);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jx, 1, 2);
//		tour = new PartieMorpion(grille, joueurO);
//		tour.fermeAlignementXD(0, 2, 4);
//
//		grilleFerme = new PartieTicTacToe(6, 6);
//		grilleFerme.placerJeton(jo, 1, 1);
//		grilleFerme.placerJeton(jx, 1, 2);
//		jxmin = Jeton.JETON_X;
//		grilleFerme.placerJetonFerme(jxmin, 0, 1);
//		grilleFerme.placerJetonFerme(jxmin, 0, 2);
//		grilleFerme.placerJetonFerme(jxmin, 0, 3);
//		grilleFerme.placerJetonFerme(jxmin, 0, 4);
//
//		assertTrue(grille.estEgale(grilleFerme));
//		assertTrue(grilleFerme.estEgale(grille));

//		System.out.println("fermerAlignement XD avec 4");
//		grille.afficherGrille();
//		grilleFerme.afficherGrille();

		// fermeAlignementXD avec 2
//		grille = new PartieTicTacToe(6, 6);
//		joueurO = new Joueur(Jeton.JETON_O);
//		grille.placerJeton(jx, 0, 1);
//		grille.placerJeton(jx, 0, 2);
//		grille.placerJeton(jx, 0, 3);
//		grille.placerJeton(jx, 0, 4);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jx, 1, 2);
//		tour = new PartieMorpion(grille, joueurO);
//		tour.fermeAlignementXD(0, 2, 2);
//
//		grilleFerme = new PartieTicTacToe(6, 6);
//		grilleFerme.placerJeton(jo, 1, 1);
//		grilleFerme.placerJeton(jx, 0, 1);
//		grilleFerme.placerJeton(jx, 0, 3);
//		grilleFerme.placerJeton(jx, 0, 4);
//		jxmin = Jeton.JETON_X;
//		grilleFerme.placerJetonFerme(jxmin, 0, 2);
//		grilleFerme.placerJetonFerme(jxmin, 1, 2);
//
//		assertTrue(grille.estEgale(grilleFerme));
//		assertTrue(grilleFerme.estEgale(grille));
////		System.out.println("fermerAlignement XD avec 2");
////		grille.afficherGrille();
////		grilleFerme.afficherGrille();

		System.out.println("testPartieMorpion FAIT \n");
	}

//	@Test
//	void testTourPermutation(){
//		System.out.println("testTourPermutation EN COURS \n");
//		
//		Jeton jx = Jeton.JETON_X;
//		Jeton jo = Jeton.JETON_O;
//		
//		//tour avec deux joueurs DIFFERENTS AVEC s=coup gagnant
//		PartieTicTacToe grille = new PartieTicTacToe();
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
//		grille = new PartieTicTacToe();
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
//		grille = new PartieTicTacToe();
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
//		grille = new PartieTicTacToe();
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
	void testPartieForme() {
		System.out.println("testPartieForme EN COURS \n");

		PartieForme tour = new PartieForme();
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

		Joueur joueuro = new Joueur(jo);
		Forme carre = Forme.CARRE;
		Forme losange = Forme.LOSANGE;
		Forme croix = Forme.CROIX;
		assertEquals(4, carre.getNbrPoint());
		assertEquals(4, losange.getNbrPoint());
		assertEquals(5, croix.getNbrPoint());

		tour.afficherGrille();
		
		assertTrue(Utils_Grille_Evaluation_Forme.existeForme(0, 0, tour,carre));
		assertTrue(Utils_Grille_Evaluation_Forme.existeForme(1, 1, tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 2, tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(0, 2, tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 0, tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(1, 2, tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 1, tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.existeForme(2, 2, tour,carre));

		// getCoordForme
		int[][] coordTest = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } }; // tour du carre dans le sens des
																					// aiguilles d une montre

		int[][] coordTest1 = new int[carre.getNbrPoint()][2];
		coordTest1 = Utils_Grille_Evaluation_Forme.getCoordForme(0, 0, tour, carre);

		assertArrayEquals(coordTest, coordTest1);

		// getJetonForme
		String jetonCarre = Utils_Grille_Evaluation_Forme.getJetonForme(0, 0,tour , carre);
		String StrCarre = "OOOO";
		
		assertEquals(jetonCarre, StrCarre);
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
		tour = new PartieForme();
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
		tour = new PartieForme();
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
//
//		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(0, 0,tour,carre));
//		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(1, 1,tour,carre));
//		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(0, 1,tour,carre));
//		assertTrue(Utils_Grille_Evaluation_Forme.estCompleteForme(1, 0,tour,carre));
//
//		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(0, 2,tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(1, 2,tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 2,tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 0,tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 1,tour,carre));
//		assertFalse(Utils_Grille_Evaluation_Forme.estCompleteForme(2, 2,tour,carre));

		System.out.println("testPartieForme FAIT \n");
	}

}
