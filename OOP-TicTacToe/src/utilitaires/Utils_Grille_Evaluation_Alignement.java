package utilitaires;

import java.util.EnumSet;

import grille.CA_Grille;
import grille.Direction;
import grille.Jeton;

public interface Utils_Grille_Evaluation_Alignement {

	/**
	 * alignement pour UNE Direction donnee ET son Inversee
	 * 
	 * @param ligne      de la cellule observée
	 * @param colonne    de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées
	 *                   dans grille doit etre >=2
	 * @param direction  et direction opposée vers laquelle observer un alignement
	 * @return si un alignement a été trouvé
	 */
	public static boolean isAlignement1D1DI(int ligne, int colonne, int profondeur, Direction direction,
			CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);

		// jetonEvalue dont on evalue l implication dans un alignement avec d'autres
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
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() && ligneCible >= 0
					&& colonneCible >= 0) {
				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
				aligneCible += jetonCible.getSymbole();
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur && ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; // on ne souhaite pas rajouter le jeton central
		do {
			// jetonCible jeton que l on ajoute à cibleLigne
			colonneCible = coeffProfondeur * direction.getDcolonne() + colonne;
			ligneCible = coeffProfondeur * direction.getDligne() + ligne;
			if (ligneCible < grille.getLignes() && colonneCible < grille.getColonnes() && ligneCible >= 0
					&& colonneCible >= 0) {
				Jeton jetonCible = grille.getCellule(ligneCible, colonneCible);
				aligneCible = jetonCible.getSymbole() + aligneCible;
			}
			++coeffProfondeur;
		} while (coeffProfondeur < profondeur && ligneCible < grille.getLignes() && colonneCible < grille.getColonnes()
				&& ligneCible >= 0 && colonneCible >= 0);

		// comparaison des chaines
		return aligneCible.contains(aligneEvalue);
	}

	/**
	 * alignement pour TOUTES les Directions disponibles le nombre de direction pour
	 * laquelle un alignement a ete trouvé
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return le nombre de direction/orientation qui ont été trouvés avec
	 *         alignementCellule dans toutes les directions
	 */
	public static int nbrDirectAvecAlign(int ligne, int colonne, int profondeur, CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);

		int alignement = 0;

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST)) {
			if (isAlignement1D1DI(ligne, colonne, profondeur, oneDirection, grille)) {
				++alignement;
			}
		}
		return alignement;
	}

	/**
	 * il faut qu avant l appel de cette fonction il ai ete verifie qu il y avait
	 * bel et bien un alignement renvoie la PREMIERE direction trouvée pour laquel
	 * un alignement a ete trouve
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return PREMIERE direction trouvée pour laquel un alignement a ete trouve
	 */
	public static Direction directionAlignementXD(int ligne, int colonne, int profondeur, CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (nbrDirectAvecAlign(ligne, colonne, profondeur, grille) >= 1); // il faut qu avant l appel de cette
																				// fonction il ai ete verifie qu il y
																				// avait bel et bien un alignement

		Direction oneDirection = null;

		for (Direction direction : EnumSet.range(Direction.NORD, Direction.SUD_EST))
			if (isAlignement1D1DI(ligne, colonne, profondeur, direction, grille)) {
				oneDirection = direction;
				return oneDirection;
			}

		return oneDirection;
	}

}