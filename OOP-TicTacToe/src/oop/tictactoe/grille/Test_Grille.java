package oop.tictactoe.grille;


import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;

import oop.tictactoe.appli.CA_Grille_Partie_FermetureJeton;
import oop.tictactoe.appli.Joueur;
import oop.tictactoe.appli.PartiePermutation;
import oop.tictactoe.appli.PartieTicTacToe;

class Test_Grille {

	@Test 
	void testJeton(){
		System.out.println("Test jetons.");
		Jeton jo =  Jeton.JETON_O ;
		Jeton jo2 =  Jeton.JETON_O ;
		Jeton jo3 = Jeton.JETON_O_MIN ;

		Jeton jx =  Jeton.JETON_X ;
		assertTrue(jo.estEgal(jo2));
		assertTrue(jo.estEgal(jo));
		assertFalse(jo.estEgal(jo3));
		
		assertEquals('O', jo.getSymbole());
		assertEquals('O', jo2.getSymbole());
		assertEquals('o', jo3.getSymbole());
		assertEquals('X', jx.getSymbole());
		
		assertFalse(jo.estVideJeton());
		assertEquals('o', jo2.getSymbole());
		assertTrue(jo2.estEgal(jo3));
	}
	
	@Test
	void testGrillePlacerJeton() {
		System.out.println("Test placer Jeton.");
		
		PartieTicTacToe grille = new PartieTicTacToe();			

		assertTrue(grille.estVideGrille());
		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());
		
		Jeton jo =  Jeton.JETON_O ;
		Jeton jx = Jeton.JETON_X;

		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		assertFalse(grille.estVideGrille());
		assertFalse(grille.estPleineGrille());
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); //on commence a 0
		
		grille = new PartieTicTacToe();
		for (int i = 0; i<grille.getLignes(); ++i) {
			for (int j = 0; j < grille.getColonnes(); j++) {
				grille.placerJeton(jo, i, j);
			}
		}
		assertTrue(grille.estPleineGrille());
		assertFalse(grille.estVideGrille());


		grille =  new PartieTicTacToe();
		grille.placerJeton(jx, 2, 2);
		grille.placerJeton(jo, 0, 0);
		assertTrue(grille.getCellule(2,2).estEgal(jx));
		assertTrue(grille.getCellule(0,0).estEgal(jo));
//		assertTrue(grille.getCellule(3,2).estEgal(jx));	//ne doit pas marcher car 3 est hors grille
		
		grille = new PartieTicTacToe(5,4);
		assertFalse(grille.estPleineGrille());
		assertEquals(5, grille.getLignes());
		assertEquals(4, grille.getColonnes());
		
		grille.placerJeton(jx, 0, 0);
		grille.placerJeton(jo, 0, 1);
		
		assertTrue(grille.getCellule(0,0).estEgal(jx));
		assertTrue(grille.getCellule(0,1).estEgal(jo));
		
		grille.placerJeton(jo, 0, 2);
		grille.placerJeton(jx, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jo, 2, 1);
		grille.placerJeton(jo, 2, 2);
		
		assertFalse(grille.estPleineGrille());
		
	}
	
	@Test
	void testGrilleAdjacent() {
		CA_Grille_Partie_FermetureJeton grille = new CA_Grille_Partie_FermetureJeton(6,7);
		assertFalse(grille.estPleineGrille());
		
		System.out.println("Test sans jeton.");

		for (int i=0;i < grille.getLignes(); ++i) {
			for (int j = 0; j < grille.getColonnes(); ++j) {
				assertFalse(tour.existeAdjacent(i, j));
//				System.out.println("Il n existe pas de cellule non vide adjacente pour la cellule "+ (i+1)+"ligne "+(j+1)+"colonne.");
			}
		}

		System.out.println("Test avec un jeton O.");

		Jeton jo = Jeton.JETON_O;
		
		grille.placerJeton(jo, 0, 0);
		assertTrue(tour.existeAdjacent(0, 1));
		assertTrue(tour.existeAdjacent(1, 1));
		assertTrue(tour.existeAdjacent(1, 0));
		
		//(i==0 && j==0) 
		for (int i=0;i < grille.getLignes(); ++i) {
			for (int j = 0; j < grille.getColonnes(); ++j) {
				if ((i==0 && j==1) || (i==1 && j==1) || (i==1 && j==0) ) {
					assertTrue (tour.existeAdjacent(i, j));
//					System.out.println("Il existe une cellule non vide adjacente pour la cellule "+ (i+1)+"ligne "+(j+1)+"colonne.");

				}
				else {
					assertFalse(tour.existeAdjacent(i, j));
//					System.out.println("Il n existe pas de cellule non vide adjacente pour la cellule "+ (i+1)+"ligne "+(j+1)+"colonne.");

				}
			}
		}
		
		System.out.println("Test avec un jeton X.");
		
		grille = new CA_Grille_Partie_FermetureJeton(6,7);
		assertFalse(grille.estPleineGrille());
		
		Jeton jx = Jeton.JETON_X;
		
		grille.placerJeton(jx, 0, 0);
		assertTrue(tour.existeAdjacent(0, 1));
		assertTrue(tour.existeAdjacent(1, 1));
		assertTrue(tour.existeAdjacent(1, 0));
		
		//(i==0 && j==0) 
		for (int i=0;i < grille.getLignes(); ++i) {
			for (int j = 0; j < grille.getColonnes(); ++j) {
				if ((i==0 && j==1) || (i==1 && j==1) || (i==1 && j==0) ) {
					assertTrue (tour.existeAdjacent(i, j));
//					System.out.println("Il existe une cellule non vide adjacente pour la cellule "+ (i+1)+"ligne "+(j+1)+"colonne.");

				}
				else {
					assertFalse(tour.existeAdjacent(i, j));
//					System.out.println("Il n existe pas de cellule non vide adjacente pour la cellule "+ (i+1) +"ligne "+(j+1)+"colonne.");

				}
			}
		}
		
		System.out.println("Test avec deux jetons.");
		grille = new CA_Grille_Partie_FermetureJeton(6,6);
		grille.placerJeton(jo, 0, 0);
		grille.placerJeton(jx, 2, 2);
		assertTrue(tour.existeAdjacent(1, 1));
		assertTrue(tour.existeAdjacent(1, 2));
		assertTrue(tour.existeAdjacent(1, 3));
		assertTrue(tour.existeAdjacent(2, 3));
		assertTrue(tour.existeAdjacent(3, 3));
		assertTrue(tour.existeAdjacent(3, 2));
		assertTrue(tour.existeAdjacent(3, 1));
		assertTrue(tour.existeAdjacent(2, 1));
		
		
		for (int i=0;i < grille.getLignes(); ++i) {
//			System.out.println("Il existe une cellule non vide adjacente pour la cellule "+ (i+1)+"ligne "+5+"colonne.");
//			System.out.println("Il existe une cellule non vide adjacente pour la cellule "+ (i+1) +"ligne "+6+"colonne.");
//			System.out.println("Il existe une cellule non vide adjacente pour la cellule "+ 5+"ligne "+(i+1)+"colonne.");
//			System.out.println("Il existe une cellule non vide adjacente pour la cellule "+ 6+"ligne "+(i+1)+"colonne.");

			assertFalse(tour.existeAdjacent(4, i));
			assertFalse(tour.existeAdjacent(5, i));
			assertFalse(tour.existeAdjacent(i,4));
			assertFalse(tour.existeAdjacent(i,5));
		}
		
		assertTrue(tour.sontAdjacents(0, 0, 1, 1));
		assertFalse(tour.sontAdjacents(0, 0, 2, 2));
		assertTrue(tour.sontAdjacents( 1, 1, 0, 0));
		assertFalse(tour.sontAdjacents(2, 2,0,0));
		
		assertTrue(tour.sontAdjacents(1, 1, 2, 2));
		assertTrue(tour.sontAdjacents(2, 2, 1,1));
		assertFalse(tour.sontAdjacents(2, 2, 0, 0));
		
		assertTrue(tour.sontAdjacents(1, 2, 1, 1));
		assertFalse(tour.sontAdjacents(1, 3, 1, 1));

		
	}
	
	@Test
	void testGrilleRemplissageAleatoire() {
		System.out.println("Test remplissage aleatoire.");
		System.out.println("remplissage aleatoire 1");
		CA_Grille grille1 = new PartiePermutation(6,7);
		CA_Grille grille2 = new PartiePermutation(6,7);
		CA_Grille grille3 = new PartiePermutation(6,7);
//		grille1.afficherGrille();
		System.out.println("remplissage aleatoire 2");
//		grille2.afficherGrille();
		System.out.println("remplissage aleatoire 3");
//		grille3.afficherGrille();
		
		assertFalse(grille1.estEgaleGrille(grille2.getGrille()));
		assertFalse(grille1.estEgaleGrille(grille3.getGrille()));
		assertFalse(grille2.estEgaleGrille(grille3.getGrille()));
	}

	
	@Test
	void testGrillePermutationJeton(){
		System.out.println("Test permutation jeton.");

		Jeton jx = Jeton.JETON_X ;
		Jeton jo = Jeton.JETON_O ;
		Joueur j1 = Joueur(jo);
		Joueur j2 = Joueur(jx);
		
		PartiePermutation partie = new PartiePermutation(4,4);
		PartiePermutation partieC = new PartiePermutation(4,4);

		TourPermutation grille = new TourPermutation(partie,j1,j2);

		
		partie.placerJeton(jo, 0,0);
		partie.placerJeton(jx, 1,1);
		partie.placerJeton(jo, 2,2);
		partie.placerJeton(jo, 1,2);

		partieC.placerJeton(jo, 0,0);
		partieC.placerJeton(jx, 1,1);
		partieC.placerJeton(jo, 2,2);
		partieC.placerJeton(jo, 1,2);

		
		grille.permutationJeton(1, 1, 2, 2);
		assertFalse(partie.estEgaleGrille(partieC.getGrille()));
		grille.permutationJeton(1, 1, 2, 2);
		assertFalse(partie.estEgaleGrille(partieC.getGrille()));
		
		grille.permutationJeton(0,0, 1, 1);
		assertFalse(partie.estEgaleGrille(partieC.getGrille()));
		grille.permutationJeton(0,0, 1, 1);
		assertFalse(partie.estEgaleGrille(partieC.getGrille()));
		
		grille.permutationJeton(1, 2, 1, 1);
		assertFalse(partie.estEgaleGrille(partieC.getGrille()));
		grille.permutationJeton(1, 1, 1, 2);
		assertFalse(partie.estEgaleGrille(partieC.getGrille()));
	}
	
private Joueur Joueur(Jeton jx) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Test
//	void testGrilleFermetureJeton(){
//		System.out.println("Test fermeture jeton.");
//
//		Jeton jx = Jeton.JETON_X ;
//		Jeton jo = Jeton.JETON_O ;
//		
//		PartieMorpion grille = new PartieMorpion(4,4);
//		
//		grille.placerJeton(jo, 0, 2);
//		grille.placerJeton(jx, 1, 0);
//		grille.placerJeton(jo, 1, 1);
//		grille.placerJeton(jo, 1, 2);
//		grille.placerJeton(jx, 2, 0);
//		grille.placerJeton(jo, 2, 1);
//		grille.placerJeton(jo, 2, 2);
//		grille.placerJeton(jo, 2, 3);
//
//		grille.ouvertToFermeJeton(1, 2);		
//		
//		assertTrue(grille.getCellule(1, 1).estOuvert());
//		assertFalse(grille.getCellule(1, 2).estOuvert());
//	}
	
	@Test
	void testGrille() {
		CA_Grille grille = new PartieTicTacToe(4,4);

		Jeton jx = Jeton.JETON_X ;
		Jeton jo = Jeton.JETON_O ;
		grille.placerJeton(jo, 0, 2);
		grille.placerJeton(jx, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jo, 2, 1);
		grille.placerJeton(jo, 2, 2);
		grille.placerJeton(jo, 2, 3);
		assertEquals(6, grille.getNbrJeton(jo));
		assertEquals(2, grille.getNbrJeton(jx));
		grille.placerJeton(jo, 3, 0);
		grille.placerJeton(jx, 0, 3);
		grille.placerJeton(jx, 3,3);
		assertEquals(7, grille.getNbrJeton(jo));
		assertEquals(4, grille.getNbrJeton(jx));
		grille.placerJeton(jx, 1,3);
		assertEquals(7, grille.getNbrJeton(jo));
		assertEquals(5, grille.getNbrJeton(jx));
	}
	
	@Test
	void testGrilleDifferentesCellules(){
		System.out.println("Test Differentes Cellules.");

		Jeton jx = Jeton.JETON_X ;
		Jeton jo = Jeton.JETON_O ;
		
		CA_Grille grille = new PartieTicTacToe(4,4);
		
		grille.placerJeton(jo, 0, 2);
		grille.placerJeton(jx, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jo, 2, 1);
				
		assertFalse(grille.sontDifferentes(0, 1, 0, 1));
		assertFalse(grille.sontDifferentes(1, 1, 1, 1));
		assertFalse(grille.sontDifferentes(0, 0, 0, 0));
		assertFalse(grille.sontDifferentes(1, 0, 1, 0));
		assertFalse(grille.sontDifferentes(2, 2, 2, 2));
		assertFalse(grille.sontDifferentes(1, 2, 1, 2));
		assertFalse(grille.sontDifferentes(0, 2, 0, 2));

		
		assertTrue(grille.sontDifferentes(1, 0, 0, 1));
		assertTrue(grille.sontDifferentes(0, 1, 1, 0));
		assertTrue(grille.sontDifferentes(1, 0, 0, 1));
		assertTrue(grille.sontDifferentes(0, 1, 1, 0));
		assertTrue(grille.sontDifferentes(1, 2, 0, 1));
		assertTrue(grille.sontDifferentes(1, 2, 2, 1));
		
	}
	
	@Test
	void testGrilleDeplacement(){
		CA_Grille grille = new CA_Grille_Partie_FermetureJeton(5,5);
		grille.placerJeton(Jeton.JETON_X, 2, 2);
//		grille.afficherGrille();
		
		assertTrue(grille.existeNextCellule(0, 0, 1, Direction.SUD));		
		assertTrue(grille.existeNextCellule(0, 0, 2, Direction.SUD));
		assertTrue(grille.existeNextCellule(0, 0, 3, Direction.SUD));
		assertTrue(grille.existeNextCellule(0, 0, 4, Direction.SUD));
		assertFalse(grille.existeNextCellule(0, 0, 5, Direction.SUD));
		assertFalse(grille.existeNextCellule(0, 0, 6, Direction.SUD));

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.NORD_OUEST)) {
			assertTrue(grille.existeNextCellule(1, 1, 1, oneDirection));
		}
		
		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.NORD_OUEST)) {
			assertTrue(grille.existeNextCellule(2, 2, 2, oneDirection));
		}
		
		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.NORD_OUEST)) {
			assertFalse(grille.existeNextCellule(2, 2, 3, oneDirection));
		}

		int[] coordTest ;
		
		coordTest = new int[] {1,0};
		assertArrayEquals(coordTest, grille.coordNextJeton(0, 0, 1, Direction.SUD));
		
		coordTest = new int[] {2,0};
		assertArrayEquals(coordTest, grille.coordNextJeton(0, 0, 2, Direction.SUD));
		
		coordTest = new int[] {3,0};
		assertArrayEquals(coordTest, grille.coordNextJeton(0, 0, 3, Direction.SUD));
		
		coordTest = new int[] {2,4};
		assertArrayEquals(coordTest, grille.coordNextJeton(2, 2, 2, Direction.EST));
		
		coordTest = new int[] {2,0};
		assertArrayEquals(coordTest, grille.coordNextJeton(2, 2, 2, Direction.OUEST));
		
		coordTest = new int[] {0,0};
		assertArrayEquals(coordTest, grille.coordNextJeton(2, 2, 2, Direction.NORD_OUEST));
		
		coordTest = new int[] {4,0};
		assertArrayEquals(coordTest, grille.coordNextJeton(2, 2, 2, Direction.SUD_OUEST));
		
	}
	
}
