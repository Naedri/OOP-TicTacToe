package appli;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grille.Jeton;

class Test_Permutation {

	@Test
	void testgrillePermutation(){
		System.out.println("testgrillePermutation EN COURS \n");
		
//		Jeton jx = Jeton.JETON_X;
//		Jeton jo = Jeton.JETON_O;
//		
//		//grille avec deux joueurs DIFFERENTS AVEC s=coup gagnant
//		PartiePermutation grille = (PartiePermutation) new PartieMorpion();
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
//		
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		System.out.println("faire : 1-2 puis 1-3");
//		//1-2
//		//1-3
//		grille.jouerCoup(joueurx);
//		grille.evaluerCoup(joueurx, joueuro);
//		
//		assertEquals(1, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		
//		//grille avec deux joueurs differents INVERSE AVEC coup gagnant
//		grille = new PartiePermutation();
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
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		System.out.println("faire : 1-2 puis 1-3");
//		grille.jouerCoup(joueurx);
//		grille.evaluerCoup(joueurx, joueuro);
//		
//		assertEquals(1, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());		
//		
//		//grille avec deux joueurs DIFFERENT mais sans coup gagnant
//		grille = new PartiePermutation();
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
//		
//		joueuro = new Joueur(jo);
//		joueurx = new Joueur(jx);
//				
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		System.out.println("faire : 2-1 puis 3-1");
//
//		grille.permutationJeton(2, 1, 3, 1);
//		grille.evaluerCoup(joueuro, joueurx);
//		assertEquals(0, joueuro.getScore());
//		assertEquals(1, joueurx.getScore());
//		
//		//grille avec deux joueurs DIFFERENT avec coup gagnant diagonal
//		grille = new PartiePermutation();
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
//		assertEquals(0, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
//		grille.afficherGrille();
//		System.out.println("faire : 2-2 puis 3-2");
//		//2-2
//		//3-2
//		grille.jouerCoup(joueurx);
//		grille.evaluerCoup(joueurx, joueuro);
//		grille.afficherGrille();
//		assertEquals(1, joueuro.getScore());
//		assertEquals(0, joueurx.getScore());
		
		
		System.out.println("test Permutation FAIT \n");
	}

}
