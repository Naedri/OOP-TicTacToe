package tests;

import org.junit.jupiter.api.Test;

import partie.PartieForme;

class Test_Appli {

//	// statut ok
//	@Test
//	void testTicTacToe() {
//		PartieTicTacToe partieTicTacToe = new PartieTicTacToe() ;
//		System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
//		partieTicTacToe.lancerPartie() ;
//	}

//	// statut ok
//	@Test
//	void testMorpion() {
//		int ligneGrille = 3, colonneGrille = 5;
//		PartieMorpion partieMorpion = new PartieMorpion(ligneGrille, colonneGrille) ;
//		System.out.println("La partie de Morpion va commencer, preparez-vous !\n");
//		partieMorpion.lancerPartie() ;
//	}

//	// statut ok
//	@Test
//	void testPermutation() {
//		int ligneGrille = 3, colonneGrille = 3;
//		PartiePermutation partiePermutation = new PartiePermutation(ligneGrille, colonneGrille);
//		System.out.println("La partie de Permutation va commencer, preparez-vous !\n");
//		partiePermutation.lancerPartie();
//	}

	// statut ok
	@Test
	void testForme() {
		PartieForme partieForme = new PartieForme(3, 3);
		System.out.println("La partie de Forme va commencer, preparez-vous !\n");
		partieForme.lancerPartie();
	}

}
