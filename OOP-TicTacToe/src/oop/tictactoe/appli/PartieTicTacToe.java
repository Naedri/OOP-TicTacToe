package oop.tictactoe.appli;

import oop.tictactoe.tours.TourTicTacToe;
import oop.tictactoe.jouer.*;
import oop.tictactoe.grille.*;


public class PartieTicTacToe {
	
	private Joueur joueur1 ;
	private Joueur joueur2 ;
	private Match match ;
	private Grille grille ;
	
	public PartieTicTacToe() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille();
		match = new Match(1);//nombre de point max = 1
	}
	
	public void lancerPartie() {
		System.out.println("La partie de TicTacToe va commencer, preparez-vous !\n");
		grille.afficherGrille();
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			System.out.println(In_Interaction.afficherMessageTour(joueurActuel));
			TourTicTacToe tour = new TourTicTacToe(grille, joueurActuel);

			tour.jouerCoup();
			grille.afficherGrille();
			tour.evaluerCoup();
			
			match.evalMatchParTour (joueurActuel);

			System.out.println(In_Interaction.afficherMessageResultat(match, joueurActuel));
		}
	}
}
