package oop.tictactoe.grille;

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

	// getteurs
	public int getColonnes() {
		return colonnes;
	}

	public int getLignes() {
		return lignes;
	}

	public Jeton getCellule(int ligne, int colonne) {
		return grille[ligne][colonne];
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

	void placerJeton(Jeton jeton, int ligne, int colonne) {
		assert (ligne <= this.lignes && colonne <= this.colonnes);
		assert (estVideCellule(ligne, colonne)); // la cellule doit etre vide
		assert (!jeton.estVide() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
		this.grille[ligne][colonne] = jeton;
	}

	// estAligner

}
