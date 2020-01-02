package oop.tictactoe.appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_Appli {

//	// statut ok
//	@Test
//	void testTicTacToe() {
//		PartieTicTacToe partieTicTacToe = new PartieTicTacToe() ;
//		partieTicTacToe.lancerPartie() ;
//	}
	
	// statut pas ok
	@Test
	void testMorpion() {
		int ligneGrille = 5, colonneGrille = 6;
		PartieMorpion partieMorpion = new PartieMorpion(ligneGrille, colonneGrille) ;
		partieMorpion.lancerPartie() ;
	}

}
