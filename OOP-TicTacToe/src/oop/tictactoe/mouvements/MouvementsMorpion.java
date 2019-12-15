package oop.tictactoe.mouvements;

import java.util.EnumSet;

import oop.tictactoe.grille.*;

public class MouvementsMorpion extends MouvementsTicTacToe {
		
	//jetonAdjacent
	/**
	 * 
	 * @param ligne
	 * @param colonne
	 * @return existe il un jeton adjacent
	 */
	public boolean existeAdjacent(Grille grille, int ligne, int colonne) {
		assert (ligne <= grille.getLignes() && colonne <= grille.getColonnes()); //la cellule doit etre dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule doit etre vide
	    
		for (Direction direction : Direction.values()) {
	    	int cibleColonne = direction.getDcolonne() + colonne ;
			int cibleLigne =  direction.getDligne() + ligne ;
			if (cibleLigne <= grille.getLignes() && cibleColonne <= grille.getColonnes()) {
				Jeton cibleJeton = grille.getCellule(cibleLigne, cibleColonne) ;
				if (!cibleJeton.estVideJeton())
					return true ;
			}
	    }	    	
		return false ;
	}
	/**
	 * placer un jeton acolle aux autres existant
	 * @param jeton
	 * @param ligne
	 * @param colonne
	 */
	public void placerJetonAdjacent(Grille grille, Jeton jeton, int ligne, int colonne) {
		//assert (this.tour >=2 );
		assert (existeAdjacent(grille, ligne,colonne));
		assert (grille.estVideCellule(ligne, colonne)); // la cellule doit etre vide
		grille.placerJeton(jeton, ligne, colonne);
	}

}
