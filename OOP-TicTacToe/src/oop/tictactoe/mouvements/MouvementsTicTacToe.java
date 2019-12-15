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
		assert (ligne < grille.getLignes() && colonne < grille.getColonnes()); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur > 2);
		--profondeur; //il faut -1 pour avoir profondeur = que la quantité de cellule alignée car on ne copte pas la cellule iniitale

		// evalueJeton dont on evalue l implication dans un alignement avec d'autres
		// jetons : cibleJeton
		Jeton evalueJeton = grille.getCellule(ligne, colonne);

		/// evalueAligne ligne de jeton que le joueur souhaiterait avoir à partir de
		/// evalueJeton
		String evalueAligne = "";
		for (int i = 1; i <= profondeur; ++i) {
			// evalueAligne ligne de jeton que le joueur souhaiterait avoir à partir de
			// evalueJeton
			evalueAligne += evalueJeton.getSymbole();
		}

		// cibleAligne ligne de jeton observé dans la direction donnée
		String cibleAligne = "";
		int cibleColonne = 0;
		int cibleLigne = 0;

		// direction donnee
		int coeffProfondeur = 0;
		do {
			// cibleJeton jeton que l on ajoute à cibleLigne
			cibleColonne = coeffProfondeur * direction.getDcolonne() + colonne;
			cibleLigne = coeffProfondeur * direction.getDligne() + ligne;
			if (cibleLigne < grille.getLignes() && cibleColonne < grille.getColonnes()) {
				Jeton cibleJeton = grille.getCellule(cibleLigne, cibleColonne);
				cibleAligne += cibleJeton.getSymbole();
			}
			++coeffProfondeur;
		} while (coeffProfondeur <= profondeur && cibleLigne < grille.getLignes()
				&& cibleColonne < grille.getColonnes());

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// cibleJeton jeton que l on ajoute à cibleLigne
			cibleColonne = coeffProfondeur * direction.getDcolonne() + colonne;
			cibleLigne = coeffProfondeur * direction.getDligne() + ligne;
			if (cibleLigne < grille.getLignes() && cibleColonne < grille.getColonnes()) {
				Jeton cibleJeton = grille.getCellule(cibleLigne, cibleColonne);
				cibleAligne = cibleJeton.getSymbole() + cibleAligne;
			}
			++coeffProfondeur;
		} while (coeffProfondeur <= profondeur && cibleLigne < grille.getLignes()
				&& cibleColonne < grille.getColonnes());

		// comparaison des chaines
		if (cibleAligne.contains(evalueAligne))
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
		assert (ligne < grille.getLignes() && colonne < grille.getColonnes()); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur > 2);
		
		int alignement = 0;

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST))
			// pas besoin de (Direction dd : Direction.values()) car
			// alignementCellule parcours également les directions inverses
			if (alignementCellule(grille, ligne, colonne, profondeur, oneDirection))
				++alignement;
		return alignement;
	}

}
