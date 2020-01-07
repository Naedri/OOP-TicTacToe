package oop.tictactoe.appli;

import oop.tictactoe.grille.Forme;

public class Appli {
	
	/**
	 * Veuillez tapez dans les argurments des paramètres du lancement de l’application :
	 * < 0 > Pour la selection d un jeu via un menu interactif.
	 * < 1 > Pour jouer au TicTacToe.
	 * < 2 > Pour jouer au Morpion.
	 * < 3 > Pour jouer au TicTacToe extension Forme.
	 * < 4 > Pour jouer au TicTacToe extension Permutation.
	 * @param args
	 */
	public static void main(String[] args) {
		
		PartieTicTacToe partie = null ; 

	    for(String s : args){

			int choixJeu = Integer.parseUnsignedInt(s);
			int choixGrilleLigne;
			int choixGrilleColonne;
			int choixNbrAlignements = 3;
			int choixForme = 1 ;
	    	
			switch (choixJeu) {
			case 1 :
				choixGrilleLigne = 3;
				choixGrilleColonne = 3;
				System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
				partie = new PartieTicTacToe(choixGrilleLigne,choixGrilleColonne,choixNbrAlignements);
				break;
			case 2 :
				choixGrilleLigne = 5;
				choixGrilleColonne = 6;
				System.out.println("La partie de Morpion va commencer, preparez-vous !\n");
				partie = new PartieMorpion(choixGrilleLigne,choixGrilleColonne,choixNbrAlignements);
				break;
			case 3 :
				choixGrilleLigne = 12;
				choixGrilleColonne = 12;
				//forme choix
				System.out.println("Determinons la forme  qui permettra de marquer un point.");
				System.out.println(Forme.toStringFormeDispoConsigne());
				System.out.println("Veuillez choisir une forme pas son indice, entre 1 et "+ Forme.getListFormesDispo().length + " compris.");
				choixForme = Menu.setChoix(1, Forme.getListFormesDispo().length);
				Forme formeChoisie = null ;
				switch (choixForme) {
					case 1 :
						//carre
						formeChoisie = Forme.CARRE;
						break ;
					case 2 :
						//losange
						formeChoisie = Forme.LOSANGE;
						break;
					case 3 :
						//croix
						formeChoisie = Forme.CROIX;
						break;
				}
				System.out.println(formeChoisie.toStringFormeChoisie());
				System.out.println("La partie de TicTacToe Forme va commencer, preparez-vous !\n");
				partie = new PartieForme(formeChoisie);
				break;
			case 4 :
				choixGrilleLigne = 5;
				choixGrilleColonne = 6;
				System.out.println("La partie de TicTacToe Permutation va commencer, preparez-vous !\n");
				partie = new PartiePermutation(choixGrilleLigne,choixGrilleColonne,choixNbrAlignements);
				break;
				
			default:
				Menu.affichageMenuPrincipal();
				break ;
			}
			
			if (partie != null) {
				partie.lancerPartie();
			}
		}
	}
}
