package oop.tictactoe.appli;

import oop.tictactoe.tours.TourTicTacToe;
import oop.tictactoe.jouer.*;
import oop.tictactoe.grille.*;


public class PartieTicTacToe  implements In_Partie {
	
	protected Joueur joueur1 ;
	protected Joueur joueur2 ;
	protected Match match ;
	protected Grille grille ;
//	protected int nbrAlign ; //nombre de jetons a aligner
	
	public PartieTicTacToe() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille();
		match = new Match(1);//nombre de point max = 1
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne) {
		assert(choixGrilleLigne >=0 && choixGrilleColonne >= 0);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille(choixGrilleLigne, choixGrilleColonne);
		match = new Match(1); //nombre de point max = 1
	}
	
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			
			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourTicTacToe tour = new TourTicTacToe(grille, joueurActuel);

			tour.jouerCoup();
			grille.afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

//			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
			System.out.println(In_Interaction.afficherMessageResultat(match, joueurActuel));
		}
		//on compte les points
//		System.out.println(In_Interaction.afficherMessageResultat(match,joueur1,joueur2));;

	}
}
