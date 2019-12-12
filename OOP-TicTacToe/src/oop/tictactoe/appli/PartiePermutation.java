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

}
