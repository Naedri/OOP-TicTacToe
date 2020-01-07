package oop.tictactoe.appli;

import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.Joueur;
import oop.tictactoe.jouer.Match;

public abstract class CA_Grille_Partie extends CA_Grille implements In_Partie{
	
	private Joueur joueur1 ;
	private Joueur joueur2 ;
	private Match match ;
	
	public CA_Grille_Partie(int nbrLignes, int nbrColonnes, int nombrePointMax, int nombreTourMax) {
		super(nbrLignes, nbrColonnes);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		match = new Match(nombrePointMax, nombreTourMax);
	}
	
	public void lancerPartie() {
		afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;

			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));

			jouerCoup(joueurActuel);
			afficherGrille();
			evaluerCoup(joueur1, joueur2);
			
			match.evalVictoireParPointMax (joueurActuel);

			System.out.println(In_Interaction.afficherMessageFinTour(joueurActuel));
		}
		//on compte les points
		System.out.println(In_Interaction.afficherMessageResultat(match, joueur1, joueur2));

	}

	protected abstract void jouerCoup(Joueur joueurActuel);

	protected abstract void evaluerCoup(Joueur joueur1, Joueur joueur2);


}