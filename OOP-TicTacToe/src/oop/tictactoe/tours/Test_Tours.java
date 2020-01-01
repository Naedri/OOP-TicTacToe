package oop.tictactoe.tours;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.jouer.Joueur;

class Test_Tours {

	@Test
	void testTourTicTacToe() {
		
		Grille grille = new Grille();
		Jeton jo =  Jeton.JETON_O ;
		Joueur joueurO = new Joueur(jo);
		TourTicTacToe tourO = new TourTicTacToe(grille, joueurO);
		
		grille.afficherGrille();

		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());
		
		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); //on commence a 0
		grille.afficherGrille();
		
		//jO sans autres jetons
		assertTrue(tourO.alignementCellule1D(2,2,3, Direction.NORD_OUEST));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.NORD));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.NORD_EST));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.EST));
		assertTrue(tourO.alignementCellule1D(2,2,3, Direction.SUD_EST)); //attention c est true parce que la foncion regarde dans les deux sens
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.SUD));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.SUD_OUEST));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.OUEST));
		assertTrue(tourO.alignementCellule1D(2,2,3, Direction.NORD_OUEST));

		//ajout autres jetons
		Jeton jx =  Jeton.JETON_X ;
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);
		grille.afficherGrille();
		
		//jO avec autres jetons
		assertTrue(tourO.alignementCellule1D(2,2,3, Direction.NORD_OUEST));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.NORD));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.NORD_EST));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.EST));
		assertTrue(tourO.alignementCellule1D(2,2,3, Direction.SUD_EST)); //attention c est true parce que la foncion regarde dans les deux sens
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.SUD));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.SUD_OUEST));
		assertFalse(tourO.alignementCellule1D(2,2,3, Direction.OUEST));
		assertTrue(tourO.alignementCellule1D(2,2,3, Direction.NORD_OUEST));
		
		
		//jx avec jo mais ne marque pas de points
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.NORD_OUEST));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.NORD));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.NORD_EST));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.EST));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.SUD_EST)); //attention c est true parce que la foncion regarde dans les deux sens
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.SUD));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.SUD_OUEST));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.OUEST));
		assertFalse(tourO.alignementCellule1D(2,1,3, Direction.NORD_OUEST));

		//multiple alignements
		assertEquals(0, tourO.alignementCelluleXD(2,0,3));
		assertEquals(1, tourO.alignementCelluleXD(2,2,3));
		
		System.out.println("1 - testTourTicTacToe FAIT \n");
	}
	
	@Test
	void testTourMorpion() {		
		
		
		
		System.out.println("1 - testTourMorpion FAIT \n");

	}

}
