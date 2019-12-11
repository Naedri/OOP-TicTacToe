package oop.tictactoe.grille;

public class Grille {
	
	private int lignes;
	private int colonnes;
	private Cellule[][] grille ;
	 
	//constructeur
	public Grille(int hauteur, int largeur) {
		this.colonnes = largeur;
		this.lignes = hauteur;
		this.grille = new Cellule[lignes][colonnes];
	}

	public Grille() {
		this.colonnes = 3 ;
		this.lignes = 3 ;
		this.grille = new Cellule[3][3];
	}
	
	//getteurs
	public int getColonnes() {
		return colonnes;
	}
	public int getLignes() {
		return lignes;
	}
	public Cellule getCellule(int ligne, int colonne) {
		return grille[ligne][colonne];
	}
	
		
	//toString
	public String toString() {
		String sGrille = "" ;
		int ligne = 0 ;
		
		//ligne des indices de colonnes
		for (int i = 0; i <= this.colonnes; ++i)
			sGrille += " "+ " " + i + " " ;

		sGrille += "\n";
		++ligne;
		
		//il faut d'abord parcourir les reference de ligne de jeton pour accéder aux jetons
		for (Cellule[] reference : grille) {
			sGrille += ligne ;
			for (Cellule cellule : reference)
				sGrille = " " + cellule.toString();
			sGrille += "\n";
			++ligne;
		}
		return sGrille ;
	}
	
	//méthodes spécifiques
	public boolean estVideCellule(int ligne, int colonne) {
		return getCellule(ligne,colonne).estVide();
	}
	
	public boolean estPleineGrille() {
		boolean estVide = true ;
		for (Cellule[] reference : grille)
			for (Cellule cellule : reference)
				if (!cellule.estVide())
					estVide = false;
		return estVide;
	}
	
	void placerJeton(Cellule jeton, int ligne, int colonne) {
		assert(ligne<= this.lignes && colonne<= this.colonnes);
		assert(estVideCellule(ligne,colonne)); //la cellule doit etre vide
		assert(!jeton.estVide() && jeton.estOuvert()); //le jeton place ne doit pas etre vide ni ferme
		this.grille[ligne][colonne]=jeton;
	}
	
	//estAligner 
	
}
