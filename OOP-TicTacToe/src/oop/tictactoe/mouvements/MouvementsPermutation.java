package oop.tictactoe.mouvements;

import oop.tictactoe.grille.*;

public class MouvementsPermutation extends MouvementsMorpion {
		
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

	
}
