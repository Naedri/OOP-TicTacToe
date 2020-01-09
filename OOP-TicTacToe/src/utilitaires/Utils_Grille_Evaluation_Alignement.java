package utilitaires;

import java.util.EnumSet;

import grille.CA_Grille;
import grille.Direction;
import grille.Jeton;

public interface Utils_Grille_Evaluation_Alignement {

	/**
	 * renvoie une chaine de symbole de jetons obtenus dans une direction donnee 
	 * de taille inferieure ou egale a la profondeur 
	 * (tant que la projection est dans la grille)
	 * a partir d une case de la grille (ligne, colonne)
	 * Attention la case de depart n est pas comprise dans la chaine
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @param grille
	 * @return
	 */
	public static String getLigneJeton (int ligne, int colonne, int profondeur, Direction direction, CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (profondeur > 0);
		
		// aligneCible ligne de jeton observé dans la direction donnée
		String aligneCible = "";
		int coeffProfondeur = 1;
		
		while (coeffProfondeur <= profondeur && grille.existeNextCellule(ligne, colonne, coeffProfondeur, direction)) {
			int colonneCible = coeffProfondeur * direction.getDcolonne()+colonne;
			int ligneCible = coeffProfondeur * direction.getDligne()+ligne;
			aligneCible += grille.getCellule(ligneCible, colonneCible).getSymbole();
			++coeffProfondeur;
		}		
		return aligneCible;
	}
	
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
	public static boolean isDirectInAlign(int ligne, int colonne, int profondeur, Direction direction,
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

		// aligneCible ligne de jeton observé dans les directions données
		String aligneCible = "";
		String inverse = getLigneJeton(ligne, colonne, profondeur, direction.inverser(), grille);
		inverse = new StringBuilder(inverse).reverse().toString() ;
		aligneCible += inverse ;
		aligneCible += jetonEvalue.getSymbole() ;
		aligneCible += getLigneJeton(ligne, colonne, profondeur, direction, grille);
		
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
			if (isDirectInAlign(ligne, colonne, profondeur, oneDirection, grille)) {
				++alignement;
			}
		}
		return alignement;
	}
	
	/**
	 * existe t il une direction pour laquelle un alignement de taille profondeur a ete trouve ?
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param grille
	 * @return
	 */
	public static boolean isAlign (int ligne, int colonne, int profondeur, CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		

		for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST)) {
			if (isDirectInAlign(ligne, colonne, profondeur, oneDirection, grille)) {
				return true ;
			}
		}
		return false;
	}
	
	/**
	 * AVANT appel de cette fonction il devra avoir ete verifie qu il avait
	 * des alignements
	 * renvoie les directions (droites et inverses) pour lesquelles
	 * un alignement a ete trouve
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return table des directions  pour lesquelles un alignement a ete trouve
	 */
	public static Direction[] getDirectAlign(int ligne, int colonne, int profondeur, CA_Grille grille) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert (profondeur >= 2);
		assert (isAlign(ligne, colonne, profondeur, grille));

		Direction[] tableDirect = new Direction[nbrDirectAvecAlign (ligne,colonne,profondeur, grille)];
		int indice = 0 ;
		for (Direction direction : EnumSet.range(Direction.NORD, Direction.SUD_EST))
			if (isDirectInAlign(ligne, colonne, profondeur, direction, grille)) {
				tableDirect [indice] = direction;
				++indice ;
			}
		
		return tableDirect;
	}
	
	

}
