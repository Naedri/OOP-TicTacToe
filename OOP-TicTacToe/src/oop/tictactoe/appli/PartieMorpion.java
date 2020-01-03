package oop.tictactoe.appli;

import oop.tictactoe.tours.TourMorpion;
import oop.tictactoe.tours.TourTicTacToe;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.jouer.*;

public class PartieMorpion extends PartieTicTacToe {
	
	private Joueur joueur1 ;
	private Joueur joueur2 ;
	private Match match ;
	private Grille grille ;
	
	public PartieMorpion() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille();
		match = new Match(0,grille.getNbrCellules()); //nombre de point max = infini ; nombre de coup max = nombre taille grille
	}
	
	public PartieMorpion(int grilleNrbLignes, int grilleNbrColonnes) {
		assert(grilleNrbLignes >=0 && grilleNbrColonnes >= 0);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille(grilleNrbLignes,grilleNbrColonnes);
		match = new Match(0,grille.getNbrCellules()); //nombre de point max = infini ; nombre de coup max = nombre taille grille
	}
	
	
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourMorpion tour = new TourMorpion(grille, joueurActuel);

			tour.jouerCoup();
			grille.afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
		}
		//on compte les points
		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));

	}
}