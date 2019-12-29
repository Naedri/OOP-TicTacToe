package oop.tictactoe.grille;

public interface In_Grille {
	
	/**
	 * 
	 * @return nombre de Colonnes de la grille
	 */
	public int getColonnes() ;
	/**
	 * 
	 * @return nombre de lignes de la grille
	 */
	public int getLignes();
	
	/**
	 *
	 * @param ligne de la cellule indique  le 0 compte
	 * @param colonne de la cellule indique  le 0 compte
	 * @return un JETON (contenant un symbole X ou O ou x ou o et un boolean pour indiquer l ouverture
	 */
	public Jeton getCellule(int ligne, int colonne);
	
	/**
	 * toString
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	public String toStringGrille();
	
	/**
	 * renvoie le toStringGrille
	 */
	public void afficherGrille();
	
	/**
	 * estVideCellule
	 * @param ligne de la cellule de la grille le 0 compte
	 * @param colonne de la cellule de la grille  le 0 compte
	 * @return la cellule est elle vide ?
	 */
	public boolean estVideCellule(int ligne, int colonne);
	
	/**
	 * estPleineGrille
	 * @return toutes les cellules de la grille contiennent elles JETON_VIDE ?
	 */
	public boolean estPleineGrille() ;
}
