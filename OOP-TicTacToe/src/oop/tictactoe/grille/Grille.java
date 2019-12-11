package oop.tictactoe.grille;
import java.util.EnumSet;

public class Grille {

	private int lignes;
	private int colonnes;
	private Jeton[][] grille;

	// constructeur
	public Grille(int hauteur, int largeur) {
		this.colonnes = largeur;
		this.lignes = hauteur;
		this.grille = new Jeton[lignes][colonnes];
	}

	public Grille() {
		this.colonnes = 3;
		this.lignes = 3;
		this.grille = new Jeton[3][3];
	}

	// getters
	public int getColonnes() {
		return colonnes;
	}

	public int getLignes() {
		return lignes;
	}

	public Jeton getCellule(int ligne, int colonne) {
		assert (ligne <= this.lignes && colonne <= this.colonnes);
		return this.grille[ligne][colonne];
	}

	// toString
	public String toString() {
		String sGrille = "";
		int ligne = 0;

		// ligne des indices de colonnes
		for (int i = 0; i <= this.colonnes; ++i)
			sGrille += " " + " " + i + " ";

		sGrille += "\n";
		++ligne;

		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (Jeton[] reference : grille) {
			sGrille += ligne;
			for (Jeton cellule : reference)
				sGrille = " " + cellule.toString();
			sGrille += "\n";
			++ligne;
		}
		return sGrille;
	}

	// methodes specifiques
	public boolean estVideCellule(int ligne, int colonne) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		return getCellule(ligne, colonne).estVide();
	}


	public boolean estPleineGrille() {
		boolean estVide = true;
		for (Jeton[] reference : grille)
			for (Jeton cellule : reference)
				if (!cellule.estVide())
					estVide = false;
		return estVide;
	}

	public void placerJeton(Jeton jeton, int ligne, int colonne) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		assert (estVideCellule(ligne, colonne)); // la cellule doit etre vide
		assert (!jeton.estVide() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
		this.grille[ligne][colonne] = jeton;
	}
	
	
	//celluleMiroir
	public boolean existeCelluleMiroir(int ligneOrigine, int colonneOrigine, int ligneProjete, int colonneProjete ) {
		assert (ligneOrigine <= this.lignes && colonneOrigine <= this.colonnes);
		assert (ligneProjete <= this.lignes && colonneProjete <= this.colonnes);
				
		int ligneMiroir = (ligneOrigine - ligneProjete) + ligneOrigine ;
		int colonneMiroir =  (colonneOrigine - colonneProjete) + colonneOrigine;
		
		return (ligneMiroir <= this.lignes && colonneMiroir <= this.colonnes);
	}
	
	public Jeton getCelluleMiroir(int ligneOrigine, int colonneOrigine, int ligneProjete, int colonneProjete ) {
		assert (ligneOrigine <= this.lignes && colonneOrigine <= this.colonnes);
		assert (ligneProjete <= this.lignes && colonneProjete <= this.colonnes);
				
		int ligneMiroir = (ligneOrigine - ligneProjete) + ligneOrigine ;
		int colonneMiroir =  (colonneOrigine - colonneProjete) + colonneOrigine;
		assert (ligneMiroir <= this.lignes && colonneMiroir <= this.colonnes); //on verifie que l on reste dans la grille
		return this.grille[ligneMiroir][colonneMiroir];
	}

	// alignementCellule-Adrien	
	/**
	 * 
	 * @param ligne de la cellule observée
	 * @param colonne de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées dans grille
	 * @param direction direction et direction opposée vers laquelle observer
	 * @return si un alignement a été trouvé
	 */
	public boolean alignementCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert(profondeur >= 2);
		
		return true;
	}
	
	/**
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @return le nombre d'alignement qui ont été trouvés avec alignementCellule dans toutes les directions
	 */
	public int alignementCellule(int ligne, int colonne, int profondeur) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		int alignement = 0 ;
	    for (Direction oneDirection : EnumSet.range(Direction.NORD, Direction.SUD_EST)) //pas besoin de (Direction dd : Direction.values()) car alignementCellule parcours également les directions inverse 
	    	if (alignementCellule(ligne, colonne, profondeur, oneDirection))
	    		++alignement ;
	    return alignement ;
	}
	
}
