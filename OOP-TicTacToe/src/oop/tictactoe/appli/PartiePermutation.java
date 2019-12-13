package oop.tictactoe.appli;

import oop.tictactoe.grille.*;

public class PartiePermutation {
	
	/**
	 * saisiePermutation renvoie l input de l utilisateur sous format brute (table à 1 colonne 2 lignes)
	 * messagePermutation message sous forme en reponse de l appel deux fois à saisie cellule saisieCellule, avec la forme : L’utilisateur X (resp O) a permute la cellule «[i],[j]» avec la cellule  «[i],[j]»
	 */
	public String saisiePermutation ;
	private String messagePermutation ;
	
	public String getMessagePermutation() {
		return this.messagePermutation;
	}
	
	/**
	 * 
	 */
	private void setMessagePermutation() {
		this.saisiePermutation = messagePermutation;
	}
	
	
	
	/**
	 * enleverJeton puis placerJeton dans la cellule qui vient d etre enlever à partir de la cellule destination puis enleverJeton dans la cellule source puis placerJeton dans la cellule source
	 */
	public void permutationJeton() {
		//enleverJeton
		//placerJeton
	}
	
	//jetonAdjacent
	public boolean existeAdjacent(int ligne, int colonne) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule doit etre vide
	    
		for (Direction direction : Direction.values()) {
	    	int cibleColonne = direction.getDcolonne() + colonne ;
			int cibleLigne =  direction.getDligne() + ligne ;
			if (cibleLigne <= this.lignes && cibleColonne <= this.colonnes) {
				Jeton cibleJeton = this.grille[cibleLigne][cibleColonne] ;
				if (!cibleJeton.estVide())
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
	public void placerJetonAdjacent(Jeton jeton, int ligne, int colonne) {
		//assert (this.tour >=2 );
		assert (existeAdjacent(ligne,colonne));
		assert (estVideCellule(ligne, colonne)); // la cellule doit etre vide
		this.placerJeton(jeton, ligne, colonne);
	}
	
}
