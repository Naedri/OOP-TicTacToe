package oop.tictactoe.tours;

import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.In_Interaction;
import oop.tictactoe.jouer.In_MessagesPlacement;
import oop.tictactoe.jouer.Joueur;

public class TourMorpion extends TourTicTacToe {
	
	private Grille grille;
	private Joueur joueur;
	private int[] saisieCellule ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
		
	public TourMorpion(Grille grille, Joueur joueurActuel) {
		super(grille, joueurActuel);
		this.grille = grille;
		this.joueur = joueurActuel;
		this.saisieCellule = new int[2]; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	}
	
	/**
	 * permet la saisie et le placement de jeton
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
		grille.placerJeton(joueur.getJeton(), saisieCellule[0], saisieCellule[1]);
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
