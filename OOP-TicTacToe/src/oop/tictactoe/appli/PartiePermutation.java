package oop.tictactoe.appli;

import oop.tictactoe.tours.TourPermutation;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.jouer.*;


public class PartiePermutation extends PartieMorpion implements In_Partie{
		
	public PartiePermutation() {
		super();
		grille = new Grille(true);
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes) {
		super(grilleNrbLignes, grilleNrbLignes);
		grille = new Grille(grilleNrbLignes,grilleNbrColonnes, true);
	}
	
	@Override
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
//			Joueur joueurAutre = ( match.getTour()%2 == 0 ) ? joueur1 : joueur2 ;
			Joueur joueurAutre = (joueurActuel.getJeton().estEgal(joueur1.getJeton())) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourPermutation tour = new TourPermutation(grille, joueurActuel, joueurAutre);

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
