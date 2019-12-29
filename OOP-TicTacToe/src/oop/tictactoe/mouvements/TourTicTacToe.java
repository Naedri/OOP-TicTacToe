package oop.tictactoe.mouvements;

import java.util.EnumSet;

import oop.tictactoe.grille.*;

public class MouvementsTicTacToe {
	
	// alignementCellule
	// renommer eveluerJeton en jetonEvalue pur marque que c est une variable
	/**
	 * alignement pour une direction donnee
	 * 
	 * @param ligne      de la cellule observée
	 * @param colonne    de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées
	 *                   dans grille
	 * @param direction  et direction opposée vers laquelle observer un alignement
	 * @return si un alignement a été trouvé
	 */
	public static boolean alignementCellule(Grille grille, int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur > 2);
//		--profondeur; //il faut -1 pour avoir profondeur = que la quantité de cellule alignée car on ne copte pas la cellule iniitale

		// evalueJeton dont on evalue l implication dans un alignement avec d'autres
		// jetons : cibleJeton
		Jeton evalueJeton = grille.getCellule(ligne, colonne);
		
		/// evalueAligne ligne de jeton que le joueur souhaiterait avoir à partir de
		/// evalueJeton
		String aligneEvalue = "";
		for (int i = 1; i <= profondeur; ++i) {
			// evalueAligne ligne de jeton que le joueur souhaiterait avoir à partir de
			// evalueJeton
			aligneEvalue += evalueJeton.getSymbole();
		}

		// cibleAligne ligne de jeton observé dans la direction donnée
		String aligneCible = "";
		int colonneCible = 0;
		int ligneCible = 0;

		// direction donnee
		int coeffProfondeur = 0;
		do {
			// cibleJeton jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton cibleJeton = grille.getCellule(ligneCible, colonneCible);
				aligneCible += cibleJeton.getSymbole();
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur 
				&& ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// cibleJeton jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() 
					&& ligneCible >= 0 && colonneCible >= 0) {
				Jeton cibleJeton = grille.getCellule(ligneCible, colonneCible);
				aligneCible = cibleJeton.getSymbole() + aligneCible;
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
	 * alignement pour toutes les directions
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return le nombre d'alignement qui ont été trouvés avec alignementCellule
	 *         dans toutes les directions
	 */
	public static int alignementCellule(Grille grille, int ligne, int colonne, int profondeur) {
		assert (ligne < grille.getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur > 2);
		
		int alignement = 0;

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_OUEST))
			// pas besoin de (Direction dd : Direction.values()) car
			// alignementCellule parcours également les directions inverses
			if (alignementCellule(grille, ligne, colonne, profondeur, oneDirection)) {
				++alignement;
			}
		return alignement;
	}

}
