package oop.tictactoe.grille;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GrilleTest {

	@Test 
	void testJeton(){
		
		Jeton jo =  Jeton.JETON_O ;
		Jeton jo2 =  Jeton.JETON_O ;
		Jeton jo3 = Jeton.JETON_O_MIN ;

		Jeton jx =  Jeton.JETON_X ;
		assertTrue(jo.estEgal(jo2));
		assertTrue(jo.estEgal(jo));
		
		assertEquals('O', jo.getSymbole());
		assertEquals('O', jo2.getSymbole());
		assertEquals('X', jx.getSymbole());
		
		assertFalse(jo.estVideJeton());
		assertTrue(jo2.estOuvert());
		
		jo2 = jo2.ouvertToFerme();
		assertFalse(jo2.estOuvert());
		assertEquals('o', jo2.getSymbole());
		assertTrue(jo2.estEgal(jo3));
	}
	
	@Test
	void testGrille() {
		
		Grille grille = new Grille();
		grille.afficherGrille();

		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());
		

		
		Jeton jo =  Jeton.JETON_O ;
		Jeton jx = Jeton.JETON_X;

		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); //on commence a 0

		grille = new Grille();
		grille.afficherGrille();
		grille.placerJeton(jx, 2, 2);
		grille.placerJeton(jo, 0, 0);
		grille.afficherGrille();
		assertTrue(grille.getCellule(2,2).estEgal(jx));
		assertTrue(grille.getCellule(0,0).estEgal(jo));
//		assertTrue(grille.getCellule(3,2).estEgal(jx));	//ne doit pas marcher car 3 est hors grille

		
		grille = new Grille(5,4);
		assertFalse(grille.estPleineGrille());
		assertEquals(5, grille.getLignes());
		assertEquals(4, grille.getColonnes());
		
		grille.placerJeton(jx, 0, 0);
		grille.placerJeton(jo, 0, 1);
		grille.afficherGrille();

		
		assertTrue(grille.getCellule(0,0).estEgal(jx));
		assertTrue(grille.getCellule(0,1).estEgal(jo));
		
		grille.placerJeton(jo, 0, 2);
		grille.placerJeton(jx, 1, 0);
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 1, 2);
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jo, 2, 1);
		grille.placerJeton(jo, 2, 2);
		grille.afficherGrille();
	}

}
