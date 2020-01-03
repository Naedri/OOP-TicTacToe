package oop.tictactoe.appli;

import oop.tictactoe.tours.TourForme;
import oop.tictactoe.tours.TourMorpion;
import oop.tictactoe.grille.Forme;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.jouer.*;


public class PartieForme extends PartieTicTacToe implements In_Partie {
	
	private Forme forme ;
	
	public PartieForme() {
		super(12,12);
		forme = new Forme(1);
	}
	
	public PartieForme(int choixForme) {
		super(12,12);
		forme = new Forme(choixForme);
	}
	
	@Override
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourForme tour = new TourForme(grille, joueurActuel, forme);

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
