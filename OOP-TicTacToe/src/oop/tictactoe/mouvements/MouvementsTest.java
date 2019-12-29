package oop.tictactoe.mouvements;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.grille.Jeton;

class mouvementssTest {

	@Test
	void testTicTacToe() {
		
		Grille grille = new Grille();
		TourTicTacToe mouvements = new TourTicTacToe(grille);
		
		grille.afficherGrille();

		assertFalse(grille.estPleineGrille());
		assertEquals(3, grille.getLignes());
		assertEquals(3, grille.getColonnes());
		
		Jeton jo =  Jeton.JETON_O ;
		assertTrue(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 0, 0);
		assertFalse(grille.estVideCellule(0, 0));
		grille.placerJeton(jo, 1, 1);
		grille.placerJeton(jo, 2, 2); //on commence a 0
		grille.afficherGrille();
		
		//jO sans autres jetons
		assertTrue(mouvements.completeForme(2,2,3, Direction.NORD_OUEST));
		assertFalse(mouvements.completeForme(2,2,3, Direction.NORD));
		assertFalse(mouvements.completeForme(2,2,3, Direction.NORD_EST));
		assertFalse(mouvements.completeForme(2,2,3, Direction.EST));
		assertTrue(mouvements.completeForme(2,2,3, Direction.SUD_EST)); //attention c est true parce que la foncion regarde dans les deux sens
		assertFalse(mouvements.completeForme(2,2,3, Direction.SUD));
		assertFalse(mouvements.completeForme(2,2,3, Direction.SUD_OUEST));
		assertFalse(mouvements.completeForme(2,2,3, Direction.OUEST));
		assertTrue(mouvements.completeForme(2,2,3, Direction.NORD_OUEST));

		//ajout autres jetons
		Jeton jx =  Jeton.JETON_X ;
		grille.placerJeton(jx, 2, 0);
		grille.placerJeton(jx, 2, 1);
		grille.afficherGrille();
		
		//jO avec autres jetons
		assertTrue(mouvements.completeForme(2,2,3, Direction.NORD_OUEST));
		assertFalse(mouvements.completeForme(2,2,3, Direction.NORD));
		assertFalse(mouvements.completeForme(2,2,3, Direction.NORD_EST));
		assertFalse(mouvements.completeForme(2,2,3, Direction.EST));
		assertTrue(mouvements.completeForme(2,2,3, Direction.SUD_EST)); //attention c est true parce que la foncion regarde dans les deux sens
		assertFalse(mouvements.completeForme(2,2,3, Direction.SUD));
		assertFalse(mouvements.completeForme(2,2,3, Direction.SUD_OUEST));
		assertFalse(mouvements.completeForme(2,2,3, Direction.OUEST));
		assertTrue(mouvements.completeForme(2,2,3, Direction.NORD_OUEST));
		
		
		//jx avec jo mais ne marque pas de points
		assertFalse(mouvements.completeForme(2,1,3, Direction.NORD_OUEST));
		assertFalse(mouvements.completeForme(2,1,3, Direction.NORD));
		assertFalse(mouvements.completeForme(2,1,3, Direction.NORD_EST));
		assertFalse(mouvements.completeForme(2,1,3, Direction.EST));
		assertFalse(mouvements.completeForme(2,1,3, Direction.SUD_EST)); //attention c est true parce que la foncion regarde dans les deux sens
		assertFalse(mouvements.completeForme(2,1,3, Direction.SUD));
		assertFalse(mouvements.completeForme(2,1,3, Direction.SUD_OUEST));
		assertFalse(mouvements.completeForme(2,1,3, Direction.OUEST));
		assertFalse(mouvements.completeForme(2,1,3, Direction.NORD_OUEST));

		//multiple alignements
		assertEquals(0, mouvements.completeForme(2,0,3));
		assertEquals(1, mouvements.completeForme(2,2,3));
	}

}
