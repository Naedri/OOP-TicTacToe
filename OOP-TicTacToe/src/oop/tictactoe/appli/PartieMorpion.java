package oop.tictactoe.appli;

import oop.tictactoe.mouvements.MouvementsMorpion;
import oop.tictactoe.mouvements.TourTicTacToe;
import oop.tictactoe.grille.Grille;
import oop.tictactoe.jouer.*;


public class PartieMorpion extends PartieTicTacToe {
	
	private Joueur joueur1 ;
	private Joueur joueur2 ;
	private Match match ;
	private Grille grille ;
	private MouvementsMorpion mouvements ;
	
	public PartieMorpion() {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille();
		match = new Match();
		mouvements = new MouvementsMorpion(grille);
	}

	public PartieMorpion(int ligneGrille, int colonneGrille) {
		joueur1 = new Joueur();
		joueur2 = new Joueur();
		grille = new Grille(ligneGrille, colonneGrille);
		mouvements = new MouvementsMorpion(grille);
		match = new Match();
	}
	
	public void lancerPartie() {
		grille.afficherGrille();
		while(!(match.estTermine() || match.estVictoire())) {
			match.tourDebut();
			Joueur joueurActuel = ( match.getTour()%2 == 0 ) ? joueur2 : joueur1 ;
			System.out.println(In_Interaction.afficherMessageTour(joueurActuel));
			
			boolean saisieCorrecte = false;
			int[] saisieCellule  = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne

			while (!saisieCorrecte) {
				saisieCellule = In_Interaction.saisirCellule(grille);
				System.out.println(In_Interaction.afficherMessageCellule(joueurActuel, saisieCellule));
				if (grille.estVideCellule(saisieCellule[0], saisieCellule[1]))
					if (grille.existeAdjacent(saisieCellule[0], saisieCellule[1]))
						saisieCorrecte = true ;
					else
						System.out.println("La case selectionne ne comporte pas de jeton adjacent.\n");
				else
					System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
				}
			grille.placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
			
			grille.afficherGrille();
			if (mouvements.completeForme(saisieCellule[0], saisieCellule[1], 3) >=1 ) {
				joueurActuel.marquerPoint();
				match.setVictoire(joueur1, joueur2);
			}
			System.out.println(In_Interaction.afficherMessageResultat(match, joueurActuel));
		}
	}
}
