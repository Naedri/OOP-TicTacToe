package partie;

import composant_independant.*;
import utilitaires.Utils_Grille_Evaluation_Adjacent;

public abstract class CA_Grille_Partie_FermetureJeton extends CA_Grille_Partie {

	private boolean[][] grilleOuvertureJetons;

	public CA_Grille_Partie_FermetureJeton(int nbrLignes, int nbrColonnes) {
		super(nbrLignes, nbrColonnes);
		grilleOuvertureJetons = new boolean[nbrLignes][nbrColonnes];
		iniGrilleFermeture();
	}

	// ******* GETTEURS ********

	/**
	 * Compte le nombre de jeton ouvert
	 * 
	 * @return
	 */
	public int getNbrOuvert() {
		int cpt = 0;
		for (int i = 0; i < grilleOuvertureJetons.length; i++) {
			for (int j = 0; j < grilleOuvertureJetons[0].length; j++) {
				if (grilleOuvertureJetons[i][j]) {
					++cpt;
				}
			}
		}
		return cpt;
	}

	// ******* METHODE GRILLE *******

	// ******* METHODE GRILLE PERMUTATION *******
	/**
	 * permute deux jetons de la grille verifie que les deux jetons electionnes sont
	 * dans la grille verifie que les deux jetons sont adjacents verifie que les les
	 * cellules sont rempli de JETON Il n est PAS verifie que les deux JETONS a
	 * permuter soient ouverts
	 * 
	 * @param ligne1
	 * @param colonne1
	 * @param colonne1
	 * @param ligne2
	 */
	public void permutationJeton(int ligne1, int colonne1, int ligne2, int colonne2) {
		assert (sontDifferentes(ligne1, colonne1, ligne2, colonne2)); // les jetons doivent etre differents
		assert (ligne1 < getLignes() && ligne1 >= 0); // la cellule doit être dans la grille
		assert (colonne1 < getColonnes() && colonne1 >= 0); // la cellule doit être dans la grille
		assert (ligne2 < getLignes() && ligne2 >= 0); // la cellule doit être dans la grille
		assert (colonne2 < getColonnes() && colonne2 >= 0); // la cellule doit être dans la grille

		assert (!estVideCellule(ligne1, colonne1)); // la cellule ne doit pas etre vide
		assert (!estVideCellule(ligne2, colonne2)); // la cellule ne doit pas etre vide

		assert (Utils_Grille_Evaluation_Adjacent.sontAdjacents(ligne1, colonne1, ligne2, colonne2, this));

		// permutation jeton
		if ((!getCellule(ligne1, colonne1).estEgal(getCellule(ligne2, colonne2)))) {
			Jeton jtemp = getCellule(ligne1, colonne1);
			placerJeton(getCellule(ligne2, colonne2), ligne1, colonne1);
			placerJeton(jtemp, ligne2, colonne2);
		}
		// permutation ouverture
		if ((estOuvert(ligne1, colonne1) && !estOuvert(ligne2, colonne2))
				|| (!estOuvert(ligne1, colonne1) && estOuvert(ligne2, colonne2))) {
			if (estOuvert(ligne1, colonne1)) {
				ouvertToFermeJeton(ligne1, colonne1);
				fermeToOuvertJeton(ligne2, colonne2);
			} else {
				fermeToOuvertJeton(ligne1, colonne1);
				ouvertToFermeJeton(ligne2, colonne2);
			}
		}
	}

	// ******* METHODE GRILLE Fermeture *******
	// les jetons sont au depart ouvert (true)
	private void iniGrilleFermeture() {
		for (boolean[] ligneJeton : grilleOuvertureJetons) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = true;
			}
		}
	}

	/**
	 * ferme le jeton (ligne, colonne) fournie
	 * 
	 * @param ligne
	 * @param colonne
	 */
	public void ouvertToFermeJeton(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); // la cellule doit être dans la grille
		assert (grilleOuvertureJetons[ligne][colonne]); // le jeton doit etre initialement ouvert
		assert (!estVideCellule(ligne, colonne));
		grilleOuvertureJetons[ligne][colonne] = false;
	}

	/**
	 * ouvre le jeton (ligne, colonne) fournie
	 * 
	 * @param ligne
	 * @param colonne
	 */
	private void fermeToOuvertJeton(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); // la cellule doit être dans la grille
		assert (!grilleOuvertureJetons[ligne][colonne]); // le jeton doit etre initialement ferme
		assert (!estVideCellule(ligne, colonne));
		grilleOuvertureJetons[ligne][colonne] = true;
	}

	/**
	 * ferme la table de jeton (ligne, colonne) fournie
	 * 
	 * @param coordCible
	 */
	public void ouvertsToFermesJetons(int[][] coordCible) {
		assert (coordCible != null);
		for (int i = 0; i < coordCible.length; i++) {
			ouvertToFermeJeton(coordCible[i][0], coordCible[i][1]);
		}
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
			} else {
				sGrille += " " + " " + j;
			}
		sGrille += "\n";

		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (int ligne = 1; ligne <= getLignes(); ++ligne) {
			if (ligne < 10) {
				sGrille += " " + ligne;
			} else {
				sGrille += ligne;
			}
			for (int colonne = 0; colonne < getColonnes(); ++colonne) {
				sGrille += " " + toStringJetonOouF((ligne - 1), colonne);
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
		} else {
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
		} else {
			return getSymboleJetonFerme(getCellule(ligne, colonne));
		}
	}

	public String toStringJetonOouF(int ligne, int colonne) {
		return "" + '[' + getSymboleJetonOouF(ligne, colonne) + ']'; // "" shortcut to cast from char to string
	}

}