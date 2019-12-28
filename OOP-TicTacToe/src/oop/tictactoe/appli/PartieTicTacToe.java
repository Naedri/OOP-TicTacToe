package oop.tictactoe.appli;

import oop.tictactoe.mouvements.MouvementsTicTacToe;
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
		match = new Match();
	}
	
	public void lancerPartie() {
		grille.afficherGrille();
		while(!(match.estTermine() || match.estVictoire())) {
			match.tourDebut();
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			System.out.println(Messages.afficherMessageTour(joueurActuel));
			
			boolean saisieCorrecte = false;
			int[] saisieCellule  = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne

			while (!saisieCorrecte) {
				saisieCellule = Messages.saisirCellule(grille);
				System.out.println(Messages.afficherMessageCellule(joueurActuel, saisieCellule));
				if (grille.estVideCellule(saisieCellule[0], saisieCellule[1]))
					saisieCorrecte = true ;
				else
					System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
				}
			grille.placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
			
			grille.afficherGrille();
			if (MouvementsTicTacToe.alignementCellule(grille,saisieCellule[0], saisieCellule[1], 3) >=1 ) {
				joueurActuel.marquerPoint();
				match.setVictoire(joueur1, joueur2);
			}
			System.out.println(Messages.afficherMessageResultat(match, joueurActuel));
		}
	}
}
