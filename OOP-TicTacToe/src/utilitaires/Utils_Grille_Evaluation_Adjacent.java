package utilitaires;

import partie.*;
import direction.Direction;

public class Utils_Grille_Evaluation_Adjacent {

	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE ADJACENT JETON *******

	/**
	 * existe il dans les cellules voisines de la cellule donnee [ligne,colonne] des
	 * jetons non vides la cellule peut etre vide mais doit etre dans la grille
	 * 
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public static boolean existeAdjacent(int ligne, int colonne, CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille

		for (Direction oneDirection : Direction.values())
			if (grille.existeNextCellule(ligne, colonne, 1, oneDirection)) {
				if (!grille.getNextJeton(ligne, colonne, 1, oneDirection).estVideJeton()) {
					return true;
				}
			}
		return false;

	}

	/**
	 * les cellules donnees sont elles adjacents doivent etre des ellules
	 * differentes Il n est pas verifie que les cellules comprennent des jetons non
	 * vides
	 * 
	 * @param ligne1
	 * @param colonne1
	 * @param colonne2
	 * @param ligne2
	 * @return
	 */
	public static boolean sontAdjacents(int ligne1, int colonne1, int ligne2, int colonne2, CA_Grille grille) {
		assert (ligne1 != ligne2 || colonne1 != colonne2); // les jetons doivent etre differents

		assert (ligne1 < grille.getLignes() && ligne1 >= 0); // la cellule doit être dans la grille
		assert (colonne1 < grille.getColonnes() && colonne1 >= 0); // la cellule doit être dans la grille
		assert (ligne2 < grille.getLignes() && ligne2 >= 0); // la cellule doit être dans la grille
		assert (colonne2 < grille.getColonnes() && colonne2 >= 0); // la cellule doit être dans la grille

		boolean adjacent = false;

		if ((Math.abs(ligne1 - ligne2) <= 1) && (Math.abs(colonne1 - colonne2) <= 1)) {
			adjacent = true;
		}

		return adjacent;
	}

}
