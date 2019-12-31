package oop.tictactoe.mouvements;

import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;

public class MouvementsMorpion extends TourTicTacToe {
	
	private Grille grille;
	private Joueur joueur;
	int[] saisieCellule ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		
	public MouvementsMorpion(Grille grille, Joueur joueurActuel) {
		super(grille, joueurActuel);
		// TODO Auto-generated constructor stub
	}
	/**
	 * placer un jeton acolle aux autres existant
	 * @param jeton
	 * @param ligne
	 * @param colonne
	 */
	public void placerJetonAdjacent(int ligne, int colonne) {
		//assert (this.tour >=2 );
		assert (grille.existeAdjacent(ligne,colonne));
		assert (grille.estVideCellule(ligne, colonne)); // la cellule doit etre vide
		grille.placerJeton(joueur.getJeton(), ligne, colonne);
	}
	
	/**
	 * 
	 */
	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			if (grille.estVideCellule(saisieCellule[0], saisieCellule[1])) {
				if (grille.existeAdjacent(saisieCellule[0], saisieCellule[1])) {
					saisieCorrecte = true ;
				}
				else
					System.out.println("La case selectionnee ne comporte pas de jeton adjacent. Veuillez recommencer.\n");
			}
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		placerJetonAdjacent(saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueur, saisieCellule));
	}
	
	
	/**
	 * 
	 */
	public void evaluerCoup() {
		assert(saisieCellule != null);
		if (alignementCelluleXD(saisieCellule[0], saisieCellule[1], 3) >=1 ) {
			grille.fermerAlignementJetons(saisieCellule[0], saisieCellule[1], 3);
			joueur.marquerPoint();
		}
	}
	
}
