package utilitaires;

import grille.CA_Grille;
import grille.Direction;
import grille.Forme;
import grille.Jeton;

public interface Utils_Grille_Evaluation_Forme {

	/**
	 * existeForme permet de savoir si les cellules de toute une forme à partir d un
	 * point donné (en haut a gauche de la forme) sont inclus dans la grille
	 * 
	 * mais pas de savoir si elle existe juste de savoir si elle est comprises dans
	 * la grille à partir du point en haut a gauche
	 * 
	 * @param ligneOrigine
	 * @param colonneOrigine
	 * @param forme
	 * @return
	 */
	public static boolean existeForme(int ligne, int colonne, CA_Grille grille, Forme forme) {
		boolean existe = true;
		// le premier point de la forme est evalue en coordonne [ligne,colonne]

		for (int i = 0; i < forme.getNbrPoint(); ++i) {
			// on obtient les parametres de la projection (directionCible et
			// profondeurCible) pour parvenir à la cellule suivante
			Direction directionCible = Direction.values()[forme.getOrientation()[i]];
			int profondeurCible = forme.getDistance()[i];

			// on verifie que la cellule cible existe
			if (!grille.existeNextCellule(ligne, colonne, profondeurCible, directionCible)) {
				return false; // si il y a au moins un point de la forme qui n est pas dans la grille on
								// renvoie false
			} else {
				// si elle existe on extrait ses coordonnees pout les reutiliser dans la boucle
				int[] coordCible = grille.coordNextJeton(ligne, colonne, profondeurCible, directionCible);
				ligne = coordCible[0];
				colonne = coordCible[1];
			}
		}
		return existe;
	}

	/**
	 * getCoordForme donne un tableau de coordonne (ligne colonne) permettant d
	 * identifier les cellules impliquees dans la realisation de la forme donnee
	 * mais ne donne la forme que pour le point donne grille[ligne,colonne] etant un
	 * point le point en haut a gauche de la forme
	 * 
	 * @param ligneOrigine
	 * @param colonneOrigine
	 * @param forme
	 * @return unt table contenant x coordonnes (donc une table de table a deux
	 *         dimensions)
	 */
	public static int[][] getCoordForme(int ligne, int colonne, CA_Grille grille, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (existeForme(ligne, colonne, grille, forme));

		int[][] coord = new int[forme.getNbrPoint()][2];

		// le premier point de la forme est evalue en coordonne [ligne,colonne]
		coord[0][0] = ligne;
		coord[0][1] = colonne;

		for (int i = 1; i < forme.getNbrPoint(); ++i) {
			// on obtient les parametres de la projection (directionCible et
			// profondeurCible) pour parvenir à la cellule suivante
			Direction directionCible = Direction.values()[forme.getOrientation()[i - 1]];
			int profondeurCible = forme.getDistance()[i - 1];
			coord[i] = grille.coordNextJeton(ligne, colonne, profondeurCible, directionCible);
			ligne = coord[i][0];
			colonne = coord[i][1];
		}
		return coord;
	}

	/**
	 * permet de renvoyer le contenue des cellules (cad jetons) appartenant à une
	 * seule forme à partir d un point donnée dans une chaine de caractere
	 * 
	 * @param ligne   du point d ancarge de la forme
	 * @param colonne du point d ancrage de la forme
	 * @param forme   evaluee
	 * @return
	 */
	public static String getJetonForme(int ligne, int colonne, CA_Grille grille, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (existeForme(ligne, colonne, grille, forme));

		int[][] coordDesJetons = getCoordForme(ligne, colonne, grille, forme);

		String sJeton = "";

		for (int i = 0; i < coordDesJetons.length; ++i) {
			sJeton += grille.getCellule(coordDesJetons[i][0], coordDesJetons[i][1]).getSymbole();
		}

		return sJeton;
	}

	/**
	 * permet de renvoyer le contenue des cellules (cad jetons) (dans une chaine de
	 * caractere) appartenant à plusieurs formes derivees d une seule (forme donnee)
	 * les formes sont derivees à partir de forme.transForme() qui renvoie une forme
	 * dont les indices sont decale de int decalageIndice permettant ainsi les
	 * jetons de toutes les cellules existantes incluses dans une forme pour lequel
	 * le point donnee est considere succesivement comme chaqu'un des points de la
	 * forme
	 * 
	 * @param ligne   du point d ancarge de la forme
	 * @param colonne du point d ancrage de la forme
	 * @param forme   evaluee d origine
	 * @return
	 */
	public static String getJetonFormeAll(int ligne, int colonne, CA_Grille grille, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille

		String sJetonAll = "";

		for (int i = 0; i < forme.getNbrPoint(); ++i) {
			Forme formeTemp = forme.transForme(i);
			if (existeForme(ligne, colonne, grille, formeTemp)) {
				sJetonAll += getJetonForme(ligne, colonne, grille, formeTemp);
			}
			sJetonAll += ",";
		}

		return sJetonAll;
	}

	/**
	 * permet d evaluer si un point complete une forme
	 * 
	 * @param ligne
	 * @param colonne
	 * @param forme
	 * @return
	 */
	public static boolean estCompleteForme(int ligne, int colonne, CA_Grille grille, Forme forme) {
		assert (ligne < grille.getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < grille.getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (!grille.estVideCellule(ligne, colonne)); // la cellule evaluee ne doit pas etre vide

		// quelle chaine devrait on avoir pour que la forme soit complete
		Jeton jetonCible = grille.getCellule(ligne, colonne);
		String formeCible = "";
		for (int i = 1; i <= forme.getNbrPoint(); ++i) {
			formeCible += jetonCible.getSymbole();
		}

		// quelle sont les formes derivees dans lesquelles sont impliques la
		// cellule[ligne,colonne] observe
		// pou le template donnee (forme)
		String formeEvaluee = getJetonFormeAll(ligne, colonne, grille, forme);

		// comparaison des chaines
		return formeEvaluee.contains(formeCible);
	}

}
