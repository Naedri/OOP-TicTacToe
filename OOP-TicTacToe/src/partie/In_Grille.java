//****************************************************************
//In_Grille.java
//****************************************************************
package partie;

import jeton.Jeton;

public interface In_Grille {

	// ******* METHODE GRILLE *******

	// ******* METHODE GRILLE GETTEURS *******
	/**
	 * 
	 * @return nombre de Colonnes de la grille
	 */
	public int getColonnes();

	/**
	 * 
	 * @return nombre de lignes de la grille
	 */
	public int getLignes();

	/**
	 * 
	 * @return nombre de cellules de la grille
	 */
	public int getNbrCellules();

	public Jeton[][] getGrille();

	/**
	 *
	 * @param ligne   de la cellule indique le 0 compte
	 * @param colonne de la cellule indique le 0 compte
	 * @return un JETON (contenant un symbole X ou O ou x ou o et un boolean pour
	 *         indiquer l ouverture
	 */
	public Jeton getCellule(int ligne, int colonne);

	// ******* METHODE GRILLE EVALUATION *******

	// ******* METHODE GRILLE EVALUATION EST VIDE *******
	/**
	 * estVideCellule
	 * 
	 * @param ligne   de la cellule de la grille le 0 compte
	 * @param colonne de la cellule de la grille le 0 compte
	 * @return la cellule est elle vide ?
	 */
	public boolean estVideCellule(int ligne, int colonne);

	/**
	 * toutes les cellules de la grille sont ils vides ?
	 * 
	 * @return
	 */
	public boolean estVideGrille();

	/**
	 * estPleineGrille
	 * 
	 * @return toutes les cellules de la grille contiennent elles JETON_VIDE ?
	 */
	public boolean estPleineGrille();

	// ******* METHODE GRILLE EVALUATION JETON *******

	/**
	 * renvoie le nombre de jeton observe dans une grille
	 * 
	 * @param jetonEvalue evalue
	 * @return le nombre de jeton observe dans une grille
	 */
	public int getNbrJeton(Jeton jetonEvalue);

	// ******* METHODE GRILLE EVALUATION CELLULE *******
	/**
	 * Les cellules sont elles de coordonnees differentes
	 * 
	 * @param ligne1
	 * @param colonne1
	 * @param ligne2
	 * @param colonne2
	 * @return
	 */
	public boolean sontDifferentes(int ligne1, int colonne1, int ligne2, int colonne2);

	// ******* METHODE GRILLE EVALUATION GRILLE *******
	/**
	 * comparaison de grille utilisable pour s assurer que des grilles generees
	 * aléatoirement sont differentes
	 * 
	 * @param grille1     de jeton
	 * @param grilleCible de jeton
	 * @return true si grille1 comporte au moins un jeton different de grille2
	 */
	public boolean estEgaleGrille(Jeton[][] grille2);

	// ******* METHODE GRILLE PLACEMENT JETON *******
	/**
	 * place un jeton dans la grille La cellule ciblee peut etre vide
	 * 
	 * @param jeton        à placer (seuls JETON_X ou JETON_O sont autorisés)
	 * @param ligneCible   de la cellule de la grille le 0 compte
	 * @param colonneCible de la cellule de la grille le 0 compte
	 */
	public void placerJeton(Jeton jeton, int ligneCible, int colonneCible);

	// ******* METHODE GRILLE AFFICHAGE *******
	/**
	 * toString
	 * 
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	public String toStringGrille();

	/**
	 * Affiche en system out la String du ToString
	 */
	public void afficherGrille();
}
