package partie;

import composant_independant.Jeton;
import direction.Direction;

public abstract class CA_Grille implements In_Grille {
	
	private Jeton[][] grille;

	// ******* METHODE GRILLE *******

	// ******* METHODE GRILLE CONSTRUCTEUR *******
	public CA_Grille(int nbrLignes, int nbrColonnes) {
		assert (nbrLignes > 0 && nbrColonnes > 0);
		this.grille = new Jeton[nbrLignes][nbrColonnes];
		this.viderGrille(); // initialisation
	}

	public CA_Grille() {
		this.grille = new Jeton[3][3];
		this.viderGrille(); // initialisation
	}

	/**
	 * viderGrille permet de mettre/initialiser tout les jetons de la grille a
	 * JETON_VIDE
	 */
	private void viderGrille() {
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = Jeton.JETON_VIDE;
			}
		}
	}

	// ******* METHODE GRILLE GETTEURS *******
	public int getColonnes() {
		return this.grille[0].length;
	}

	public int getLignes() {
		return this.grille.length;
	}

	public int getNbrCellules() {
		return this.grille[0].length * this.grille.length;
	}

	public Jeton[][] getGrille() {
		return grille;
	}

	/**
	 *
	 * @param ligne   de la cellule indique le 0 compte
	 * @param colonne de la cellule indique le 0 compte
	 * @return un JETON (contenant un symbole X ou O ou x ou o et un boolean pour
	 *         indiquer l ouverture
	 */
	public Jeton getCellule(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); // la cellule doit être dans la grille
		return this.grille[ligne][colonne];
	}

	// ******* METHODE GRILLE PLACEMENT JETON *******
	/**
	 * place un jeton dans la grille La cellule ciblee peut etre vide
	 * 
	 * @param jeton        à placer (seuls JETON_X ou JETON_O sont autorisés)
	 * @param ligneCible   de la cellule de la grille le 0 compte
	 * @param colonneCible de la cellule de la grille le 0 compte
	 */
	public void placerJeton(Jeton jeton, int ligneCible, int colonneCible) {
		assert (ligneCible < this.grille.length && ligneCible >= 0); // la cellule doit être dans la grille
		assert (colonneCible < this.grille[0].length && colonneCible >= 0); // la cellule doit être dans la grille
		this.grille[ligneCible][colonneCible] = jeton;
	}

	// ******* METHODE GRILLE EVALUATION *******

	// ******* METHODE GRILLE EVALUATION EST VIDE *******
	/**
	 * estVideCellule
	 * 
	 * @param ligne   de la cellule de la grille le 0 compte
	 * @param colonne de la cellule de la grille le 0 compte
	 * @return la cellule est elle vide ?
	 */
	public boolean estVideCellule(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); // la cellule doit être dans la grille
		return getCellule(ligne, colonne).estVideJeton();
	}

	/**
	 * toutes les cellules de la grille sont ils vides ?
	 * 
	 * @return
	 */
	public boolean estVideGrille() {
		boolean estVide = true;
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				if (!ligneJeton[i].estVideJeton()) {
					estVide = false;
				}
			}
		}
		return estVide;
	}

	/**
	 * estPleineGrille
	 * 
	 * @return toutes les cellules de la grille contiennent elles JETON_VIDE ?
	 */
	public boolean estPleineGrille() {
		boolean estPleine = true;
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				if (ligneJeton[i].estVideJeton()) {
					estPleine = false;
				}
			}
		}
		return estPleine;
	}

	// ******* METHODE GRILLE EVALUATION JETON *******

	/**
	 * renvoie le nombre de jeton observe dans une grille
	 * 
	 * @param jetonEvalue evalue
	 * @return le nombre de jeton observe dans une grille
	 */
	public int getNbrJeton(Jeton jetonEvalue) {
		int nbr = 0;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (getCellule(i, j).estEgal(jetonEvalue)) {
					++nbr;
				}
			}
		}
		return nbr;
	}

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
	public boolean sontDifferentes(int ligne1, int colonne1, int ligne2, int colonne2) {
		return (ligne1 != ligne2 || colonne1 != colonne2);
	}

	// ******* METHODE GRILLE EVALUATION GRILLE *******
	/**
	 * comparaison de grille utilisable pour s assurer que des grilles generees
	 * aléatoirement sont differentes
	 * 
	 * @param grille1     de jeton
	 * @param grilleCible de jeton
	 * @return true si grille1 comporte au moins un jeton different de grille2
	 */
	public boolean estEgaleGrille(Jeton[][] grille2) {
		// comparaison de taille
		if ((this.grille.length != grille.length) || (this.grille[0].length != grille2[0].length)) {
			return false;
		}
		// comparaison des cellules
		for (int i = 0; i < this.getLignes(); ++i) {
			for (int j = 0; j < this.getColonnes(); ++j) {
				if (!grille[i][j].estEgal(grille2[i][j]))
					return false;
			}
		}
		return true;
	}

	// ******* METHODE GRILLE AFFICHAGE *******
	/**
	 * toString
	 * 
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	public String toStringGrille() {
		String sGrille = " "; // decalage pour les noms de lignes en dizaines

		// ligne des indices de colonnes
		for (int j = 1; j <= getColonnes(); ++j)
			if (j < 10) {
				sGrille += " " + " " + " " + j;
			} else
				sGrille += " " + " " + j;
		sGrille += "\n";

		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (int ligne = 1; ligne <= getLignes(); ++ligne) {
			if (ligne < 10) {
				sGrille += " " + ligne;
			} else
				sGrille += ligne;
			for (int colonne = 0; colonne < getColonnes(); ++colonne) {
				sGrille += " " + getCellule((ligne - 1), colonne).toString();
			}
			sGrille += "\n";
		}
		return sGrille;
	}

//	public String toStringGrille() {
//		String sGrille = " "; // decalage pour les noms de lignes en dizaines
//		int ligne = 0;
//
//		// ligne des indices de colonnes
//		for (int i = 1; i <= this.grille[0].length; ++i)
//			if (i < 10) {
//				sGrille += " " + " " + " " + i;
//			} else
//				sGrille += " " + " " + i;
//		sGrille += "\n";
//		++ligne;
//
//		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
//		// jetons
//		for (Jeton[] ligneJeton : grille) {
//			if (ligne < 10) {
//				sGrille += " " + ligne;
//			} else
//				sGrille += ligne;
//			for (int i = 0; i < ligneJeton.length; i++) {
//				sGrille += " " + ligneJeton[i].toString();
//			}
//			sGrille += "\n";
//			++ligne;
//		}
//		return sGrille;
//	}

	/**
	 * Affiche en system out la String du ToString
	 */
	public void afficherGrille() {
		System.out.println(this.toStringGrille());
	}

	// ******* METHODE GRILLE GET_NEXT_CELLULE *******
	/**
	 * coordNextJeton permet de savoir quelles sont les coordonnes (ligne,colonne)
	 * du jeton image càd le jeton contenu dans la cellule projetee depuis la
	 * cellule de la grille a ligne,colonne vers la direction donnee a la
	 * profondeur/distance donnee Le jeton peut etre vide Pas de limite de
	 * profondeur
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public int[] coordNextJeton(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (existeNextCellule(ligne, colonne, profondeur, direction));

		int[] coord = new int[2];
		int ligneCible = profondeur * direction.getDligne() + ligne;
		int colonneCible = profondeur * direction.getDcolonne() + colonne;

		coord[0] = ligneCible;
		coord[1] = colonneCible;
		return coord;
	}

	/**
	 * Pour les elements donnes, existeNextCellule permet de savoir si la cellule
	 * image est comprise dans la grille Pas d indication de la nature du jeton Pas
	 * de limite de profondeur cellule image cad : càd le jeton contenu dans la
	 * cellule projetee depuis la cellule de la grille a ligne,colonne vers la
	 * direction donnee a la profondeur/distance donnee
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public boolean existeNextCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille

		boolean existe = false;

		int ligneCible = profondeur * direction.getDligne() + ligne;
		int colonneCible = profondeur * direction.getDcolonne() + colonne;

		if (ligneCible < getLignes() && ligneCible >= 0 && colonneCible < getColonnes() && colonneCible >= 0) {
			existe = true;
		}
		return existe;
	}

	/**
	 * getNextJeton permet d obtenir le jeton image càd le jeton contenu dans la
	 * cellule projetee depuis la cellule de la grille a ligne,colonne vers la
	 * direction donnee a la profondeur/distance donnee
	 * 
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public Jeton getNextJeton(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); // la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); // la cellule doit être dans la grille
		assert (existeNextCellule(ligne, colonne, profondeur, direction));

		int[] coord = coordNextJeton(ligne, colonne, profondeur, direction);

		return getCellule(coord[0], coord[1]);
	}

}
