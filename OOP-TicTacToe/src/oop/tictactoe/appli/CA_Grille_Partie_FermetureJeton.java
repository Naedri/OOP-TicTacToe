package oop.tictactoe.appli;

import oop.tictactoe.grille.Jeton;

public abstract class CA_Grille_Partie_FermetureJeton extends CA_Grille_Partie {
	
	private boolean[][] grilleOuvertureJetons ;
	
	public CA_Grille_Partie_FermetureJeton(int nbrLignes, int nbrColonnes, 
			int nombrePointMax, int nombreTourMax) {
		super(nbrLignes, nbrColonnes, nombrePointMax, nombreTourMax);
		grilleOuvertureJetons = new boolean[nbrLignes][nbrColonnes];
		iniGrilleFermeture();
		}	
	
	// ******* METHODE GRILLE *******
	// ******* METHODE GRILLE Fermeture *******
	//les jetons sont au depart ouvert (true)
	private void iniGrilleFermeture() {
		for (boolean[] ligneJeton : grilleOuvertureJetons) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = true;
			}
		}
	}
	public void ouvertToFermeJeton(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); //la cellule doit être dans la grille
		grilleOuvertureJetons[ligne][colonne] = true ;
	}
	public boolean estOuvert(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); //la cellule doit être dans la grille
		return grilleOuvertureJetons[ligne][colonne];
	}
	
	// ******* METHODE GRILLE AFFICHAGE *******
	/**
	 * toString
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	@Override
	public String toStringGrille() {
		String sGrille = " " ; // decalage pour les noms de lignes en dizaines
		int ligne = 0;
	
		// ligne des indices de colonnes
		for (int i = 1; i <= getColonnes(); ++i)
			if (i<10) {
				sGrille += " " + " " + " " + i ;
			}
			else
				sGrille += " " + " " + i ;
		sGrille += "\n";
		++ligne;
	
		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (Jeton[] ligneJeton : getGrille()) {
			if (ligne<10) {
				sGrille += " " + ligne;
			}
			else
				sGrille += ligne;
			for (int i = 0; i < ligneJeton.length; i++) {
				sGrille += " " + getSymboleJetonOF(ligne-1,i);//-1 car il y a un decalage de ligne
			}
			sGrille += "\n";
			++ligne;
		}
		return sGrille;
	}
	
	/**
	 * renvoie l equivalent d un jeton ferme dans la grille pour le jeton donne
	 */
	public String toStringJetonFerme(Jeton jeton) {
		return "" + '[' + getSymboleJetonFerme(jeton) + ']' ;
	}
	
	/**
	 * renvoie l equivalent du symbole ferme pour le jeton donne
	 */
	public Character getSymboleJetonFerme(Jeton jeton) {
		Character jetonFerme ;
		if (jeton.estEgal(Jeton.JETON_X)){
			jetonFerme = 'x';
		}
		else
			jetonFerme = 'o';
		return jetonFerme ;
	}
	
	/**
	 * renvoie le symbole d un jeton ouvert ou ferme en fonction de la table grilleOuvertureJetons
	 * qui comprend tous les jetons fermes
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public Character getSymboleJetonOF(int ligne, int colonne) {
		assert (ligne < this.grilleOuvertureJetons.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grilleOuvertureJetons[0].length && colonne >= 0); //la cellule doit être dans la grille
		
		if (estOuvert(ligne, colonne)) {
			return getCellule(ligne, colonne).getSymbole();
		}
		else
			return getSymboleJetonFerme(getCellule(ligne, colonne));
		
	}
}