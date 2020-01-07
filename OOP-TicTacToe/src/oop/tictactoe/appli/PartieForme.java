package oop.tictactoe.appli;

import oop.tictactoe.tours.In_Tour;
import oop.tictactoe.tours.TourForme;
import oop.tictactoe.grille.Forme;
import oop.tictactoe.jouer.*;

public class PartieForme extends PartieTicTacToe implements In_Partie {
	
	private Forme forme ;
	
	public PartieForme() {
		super(12,12);
		forme = Forme.CARRE;
	}
	
	public PartieForme(Forme forme) {
		super(12,12);
		this.forme = forme;
	}
	
	@Override
	public void lancerPartie() {
		afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			In_Tour tour = new TourForme(this, joueurActuel, forme);

			tour.jouerCoup();
			afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
		}
		//on compte les points
		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));

	}
}
