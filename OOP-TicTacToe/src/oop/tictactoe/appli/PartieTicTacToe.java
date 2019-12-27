package oop.tictactoe.appli;

import oop.tictactoe.mouvements.MouvementsPermutation;
import oop.tictactoe.mouvements.MouvementsTicTacToe;
import oop.tictactoe.jouer.*;
import oop.tictactoe.grille.*;


public class PartieTicTacToe {
	
	private Interface interf ;	
	private Joueur joueur1 ;
	private Joueur joueur2 ;
	private Match match ;
	private Grille grille ;
	
	public PartieTicTacToe() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille();
		match = new Match();
		interf = new Interface();
	}
	//ATTENTION PEUT ETRE PASSER DE STRING A VOID POUR GET MESSAGE POUR EVITER REPEPTITOIN DE SYSO
	public void lancerPartie() {
		match.tourDebut();
		grille.afficherGrille();
		while(!(match.estTermine() || match.estVictoire())) {
			match.tourDebut();
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur1 : joueur2 ;
			interf.afficherMessageTour(joueurActuel);
			interf.setSaisieCellule(grille);
			interf.afficherMessageCellule(joueurActuel);
			grille.placerJeton(joueurActuel.getJeton(), interf.getSaisieLigne(), interf.getSaisieColonne());
			grille.afficherGrille();
			if (MouvementsTicTacToe.alignementCellule(grille, interf.getSaisieLigne(), interf.getSaisieColonne(), 3) >=1 ) {
				joueurActuel.marquerPoint();
				match.setVictoire(joueur1, joueur2);
			}
			interf.afficherMessageResultat(match, joueurActuel);
		}
	}

}
