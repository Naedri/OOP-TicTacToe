package appli;

import grille.Jeton;

public abstract class CA_Grille_Partie_FermetureJeton extends CA_Grille_Partie {

	private boolean[][] grilleOuvertureJetons;

	public CA_Grille_Partie_FermetureJeton(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes);
		grilleOuvertureJetons = new boolean[nbrLignes][nbrColonnes];
		iniGrilleFermeture();
	}

	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE Fermeture *******
	// les jetons sont au depart ouvert (true)
	private void iniGrilleFermeture() {
		for (boolean[] ligneJeton : grilleOuvertureJetons) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = true;
			}
		}
	}

	public void ouvertToFermeJeton(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); // la cellule doit être dans la grille
		assert (grilleOuvertureJetons[ligne][colonne]); // le jeton doit etre initialement ouvert
		grilleOuvertureJetons[ligne][colonne] = false;
	}

	public boolean estOuvert(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); // la cellule doit être dans la grille
		return grilleOuvertureJetons[ligne][colonne];
	}

	// ******* METHODE GRILLE AFFICHAGE *******
	
	/**
	 * toStringGrille avec les jetons fermes
	 * 
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	public String toStringGrilleFerme() {
		String sGrille = " "; // decalage pour les noms de lignes en dizaines

		// ligne des indices de colonnes
		for (int j = 1; j <= getColonnes(); ++j)
			if (j < 10) {
				sGrille += " " + " " + " " + j;
			} 
			else {
				sGrille += " " + " " + j;
			}
		sGrille += "\n";

		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (int ligne = 1; ligne <= getLignes(); ++ligne ) {
			if (ligne < 10) {
				sGrille += " " + ligne;
			} 
			else {
				sGrille += ligne;
			}
			for (int colonne = 0; colonne < getColonnes(); ++colonne) {
				sGrille += " " + toStringJetonOouF((ligne-1), colonne);
			}
			sGrille += "\n";
		}
		return sGrille;
	}
	
	/**
	 * Affiche en system out la String du ToString avec les jetons fermes
	 */
	@Override
	public void afficherGrille() {
		System.out.println(this.toStringGrilleFerme());
	}
	

	/**
	 * renvoie l equivalent du symbole ferme pour le jeton donne
	 */
	public Character getSymboleJetonFerme(Jeton jeton) {
		if (jeton == Jeton.JETON_X) {
			return 'x';
		}
		if (jeton == Jeton.JETON_O) {
			return 'o';
		}
		else {
			return ' ';
		}
	}

	/**
	 * renvoie le symbole d un jeton ouvert ou ferme en fonction de la table
	 * grilleOuvertureJetons qui comprend tous les jetons fermes
	 * 
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public Character getSymboleJetonOouF(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); // la cellule doit être dans la grille

		if (estOuvert(ligne, colonne)) {
			return getCellule(ligne, colonne).getSymbole();
		} 
		else {
			return getSymboleJetonFerme(getCellule(ligne, colonne));
		}
	}
	
	public String toStringJetonOouF(int ligne, int colonne ){
		return "" + '[' + getSymboleJetonOouF(ligne, colonne) + ']'; // "" shortcut to cast from char to string
	}

}