package oop.tictactoe.appli;

import Utilitaires.Utils_Grille_Evaluation_Alignement;
import oop.tictactoe.grille.Jeton;
import oop.tictactoe.interaction.Messages_Saisie;
import oop.tictactoe.interaction.MessagePlacement;

public class PartieTicTacToe extends CA_Grille_Partie {
	
	private int nbrAlign ; //nombre de jetons a aligner
	private int[] saisieCellule ;
	
	public PartieTicTacToe() {
		//taille de 3*3 et pointMax =1 et nbrTourMax=9
		super(3,3);
		nbrAlign = 3 ;
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne) {
		super(choixGrilleLigne, choixGrilleColonne);
		nbrAlign = 3 ;
	}
	
	public PartieTicTacToe(int choixGrilleLigne, int choixGrilleColonne, int choixNbrAlignements) {
		super(choixGrilleLigne, choixGrilleColonne);
		nbrAlign = choixNbrAlignements ;
	}
	
	@Override
	public void jouerCoup(Joueur joueurActuel) {
		boolean saisieCorrecte = false;
		
		while (!saisieCorrecte) {
			saisieCellule = Messages_Saisie.saisirCellule( getGrille());
			System.out.println(Messages_Saisie.afficherMessageCellule(joueurActuel, saisieCellule));
			if ( estVideCellule(saisieCellule[0], saisieCellule[1]))
				saisieCorrecte = true ;
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		placerJeton(joueurActuel.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(MessagePlacement.afficherMessageCoupJoue(joueurActuel, saisieCellule));
		
	}

	@Override
	public void evaluerCoup(Joueur joueur1, Joueur joueur2) {
		assert(saisieCellule != null);//on oblige le joueur a avoir jouer un coup
		if (Utils_Grille_Evaluation_Alignement.nbrDirectAvecAlign(saisieCellule[0], saisieCellule[1], nbrAlign, this) >=1 ) {
			// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
			Jeton jetonEvalue = getCellule(saisieCellule[0],  saisieCellule[1]);
			if (jetonEvalue.estEgal(joueur1.getJeton())){
				joueur1.marquerPoint();
			}
			if (jetonEvalue.estEgal(joueur2.getJeton())){
				joueur2.marquerPoint();
			}	
		}		
	}

	@Override
	public boolean estFinie() {
		return (getScoreJ1() >= 1 || getScoreJ2() >= 1 || estPleineGrille());
	}
	
}
