package oop.tictactoe.appli;

import org.junit.jupiter.api.Test;

class Test_Appli {

//	// statut ok
//	@Test
//	void testTicTacToe() {
//		PartieTicTacToe partieTicTacToe = new PartieTicTacToe() ;
//		System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
//		partieTicTacToe.lancerPartie() ;
//	}
	
	// statut ok
	@Test
	void testMorpion() {
		int ligneGrille = 5, colonneGrille = 6;
		PartieMorpion partieMorpion = new PartieMorpion(ligneGrille, colonneGrille) ;
		System.out.println("La partie de Morpion va commencer, preparez-vous !\n");
		partieMorpion.lancerPartie() ;
	}

}
