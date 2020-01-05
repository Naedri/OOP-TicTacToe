package oop.tictactoe.appli;

import oop.tictactoe.tours.TourPermutation;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.jouer.*;


public class PartiePermutation extends PartieMorpion implements In_Partie{
		
	//la partie durera tant qu il y aura des jetons a ligner pour un des deux joueurs
	
	public PartiePermutation() {
		super();
		grille = new Grille(true);
		match = new Match(0,0);
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes) {
		super(grilleNrbLignes, grilleNrbLignes);
		grille = new Grille(grilleNrbLignes,grilleNbrColonnes, true);
		match = new Match(0,0);
	}
	
	public PartiePermutation(int grilleNrbLignes, int grilleNbrColonnes, int choixNbrAlignements) {
		super(grilleNrbLignes, grilleNrbLignes, choixNbrAlignements);
		grille = new Grille(grilleNrbLignes,grilleNbrColonnes, true);
		match = new Match(0,0);
	}

	@Override
	public void lancerPartie() {
		grille.afficherGrille();
		//on fait des tours
		int resteJeton = grille.getNbrJeton(joueur1.getJeton());
		while(resteJeton >= nbrAlign) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
//			Joueur joueurAutre = ( match.getTour()%2 == 0 ) ? joueur1 : joueur2 ;
			Joueur joueurAutre = (joueurActuel.getJeton().estEgal(joueur1.getJeton())) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			TourPermutation tour = new TourPermutation(grille, joueurActuel, joueurAutre, nbrAlign);

			tour.jouerCoup();
			grille.afficherGrille();
			tour.evaluerCoup();
			
			match.evalVictoireParPointMax (joueurActuel);

			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
			
			//reste t il des jetons a aligner pour les joueurs ?
			if (grille.getNbrJeton(joueur1.getJeton()) < grille.getNbrJeton(joueur2.getJeton())) {
				resteJeton =  grille.getNbrJeton(joueur1.getJeton()) ;
			}
			else
				resteJeton =  grille.getNbrJeton(joueur2.getJeton()) ;
		}
		//on compte les points
		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));

	}
}
