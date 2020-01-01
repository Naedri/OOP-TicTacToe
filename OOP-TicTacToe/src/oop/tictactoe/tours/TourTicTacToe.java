package oop.tictactoe.tours;

import java.util.EnumSet;
import oop.tictactoe.grille.*;
import oop.tictactoe.jouer.*;

public class TourTicTacToe implements In_Tour, In_MessagesPlacement {
	
	private Grille grille;
	private Joueur joueur;
	private int[] saisieCellule ; //saisieCellule[0] = Ligne et saisieCellule[1] = Colonne
	
	public TourTicTacToe(Grille grille, Joueur joueurActuel) {
		this.grille = grille;
		this.joueur = joueurActuel;
		this.saisieCellule = new int[2];
	}

	/**
	 * alignement pour UNE Direction donnee
	 * 
	 * @param ligne      de la cellule observée
	 * @param colonne    de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées
	 *                   dans grille
	 * @param direction  et direction opposée vers laquelle observer un alignement
	 * @return si un alignement a été trouvé
	 */
	public boolean alignementCellule1D(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur > 2);//ATTENTION PEUT ETRE >=2
		
		// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
		// jetons : jetonCible
		Jeton jetonEvalue = grille.getCellule(ligne, colonne);
		
		/// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
		/// jetonEvalue
		String aligneEvalue = "";
		for (int i = 1; i <= profondeur; ++i) {
			// aligneEvalue ligne de jeton que le joueur souhaiterait avoir à partir de
			// jetonEvalue
			aligneEvalue += jetonEvalue.getSymbole();
		}

		// aligneCible ligne de jeton observé dans la direction donnée
		String aligneCible = "";
		int colonneCible = 0;
		int ligneCible = 0;

		// direction donnee
		int coeffProfondeur = 0;
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
				aligneCible += jetonCible.getSymbole();
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
				aligneCible = jetonCible.getSymbole() + aligneCible;
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// comparaison des chaines
		if (aligneCible.contains(aligneEvalue))
			return true;
		else
			return false;
	}

	/**
	 * alignement pour TOUTES les Directions disponibles
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return le nombre d'alignement qui ont été trouvés avec alignementCellule
	 *         dans toutes les directions
	 */
	public int alignementCelluleXD(int ligne, int colonne, int profondeur) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur > 2);
		
		int alignement = 0;

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_OUEST))
			// pas besoin de (Direction dd : Direction.values()) car
			// alignementCellule parcours également les directions inverses
			if (alignementCellule1D(ligne, colonne, profondeur, oneDirection)) {
				++alignement;
			}
		return alignement;
	}
	
	/**
	 * permet la saisie et le placement de jeton
	 */
	public void jouerCoup() {
		boolean saisieCorrecte = false;

		while (!saisieCorrecte) {
			saisieCellule = In_Interaction.saisirCellule(grille);
			System.out.println(In_Interaction.afficherMessageCellule(joueur, saisieCellule));
			if (grille.estVideCellule(saisieCellule[0], saisieCellule[1]))
				saisieCorrecte = true ;
			else
				System.out.println("La case selectionnee est pleine. Veuillez recommencer.\n");
			}
		grille.placerJeton(joueur.getJeton(), saisieCellule[0], saisieCellule[1]);
		System.out.println(In_MessagesPlacement.afficherMessageCoupJoue(joueur, saisieCellule));
	}
	
	/**
	 * le coup qui vient d etre joue permet il au joueur de gagner un point de victoire
	 */
	public void evaluerCoup() {
		assert(saisieCellule != null);
		if (alignementCelluleXD(saisieCellule[0], saisieCellule[1], 3) >=1 ) {
			joueur.marquerPoint();
		}
	}

}
