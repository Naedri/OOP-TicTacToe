package oop.tictactoe.appli;

import oop.tictactoe.tours.In_Tour;
import oop.tictactoe.tours.TourTicTacToe;
import oop.tictactoe.jouer.*;


public class PartieTicTacToe extends CA_PartieGrille implements In_Partie {
	
	protected Joueur joueur1 ;
	protected Joueur joueur2 ;
	protected Match match ;
	protected int nbrAlign ; //nombre de jetons a aligner
	
	public PartieTicTacToe() {
		super();
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		match = new Match(1,9);//nombre de point max = 1 et nombre de tour max = 9
		nbrAlign = 3 ;
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne) {
		super(choixGrilleLigne, choixGrilleColonne);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		match = new Match(1,9); //nombre de point max = 1 et nombre de tour max = 9
		nbrAlign = 3 ;
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne, int choixNbrAlignements) {
		super(choixGrilleLigne, choixGrilleColonne);
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		match = new Match(1,9); //nombre de point max = 1 et nombre de tour max = 9
		nbrAlign = choixNbrAlignements ;
	}

	public void lancerPartie() {
		afficherGrille();
		//on fait des tours
		while(!(match.estTourMax() || match.getVictoire())) {
			match.tourDebut();
			
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			
			System.out.println(In_Interaction.afficherMessageDebutTour(joueurActuel));
			In_Tour tour = new TourTicTacToe(this, joueurActuel, nbrAlign);

			tour.jouerCoup();
			afficherGrille();
			tour.evaluerCoup();
						
			System.out.println(afficherMessageResultat(match, joueurActuel));
		}
	}
		
	/**
	 * messageResultat
	 * indiquant victoire ou non
	 * il faut qu elle soit appelee après un coup joue
	 * (càd "C est au joueur suivant" ou "Le Joueur X a gagné la partie")
	 * @param m match en cours
	 * @param joueurActuel joueur qui vient de jouer
	 * @return
	 */
	public static String afficherMessageResultat(Match m, Joueur joueurActuel) {
		assert(joueurActuel != null && m != null);
		String messageResultat ;
		m.evalVictoireParPointMax(joueurActuel); //mise a jour
		
		if (m.estTermine(joueurActuel)) {
			if (m.getVictoire()) {
				messageResultat = "C est un match victorieux pour le joueur "+joueurActuel.getJeton().getSymbole()+".\n";
			}
			else {
				messageResultat = "C est un match nul.\n" ;
			}
		}
		else {
			messageResultat = "Le joueur "+joueurActuel.getJeton().getSymbole()+" a termine son tour.\n";
		}
		return messageResultat;
	}
}
