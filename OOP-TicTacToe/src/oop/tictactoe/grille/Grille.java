package oop.tictactoe.grille;

import oop.tictactoe.grille.Jeton;

public class Grille {

	private Jeton[][] grille;

	// constructeur
	public Grille(int nbrLignes, int nbrColonnes) {
		this.grille = new Jeton[nbrLignes][nbrColonnes];
		this.viderGrille(); //initialisation
	}
	
	
	// si il n y a pas de taille préciser on fait une grille de TIC TAC TOE de 3*3
	public Grille() {
		this.grille = new Jeton[3][3];//ce sont des references qui s attendent a recevoir des jetons mais ils sont nulls au depart
		this.viderGrille(); //initialisation
	}

	// getters
	public int getColonnes() {
		return this.grille[0].length;
	}
	
	public int getLignes() {
		return this.grille.length;
	}
	
	/**
	 *
	 * @param ligne de la cellule indique  le 0 compte
	 * @param colonne de la cellule indique  le 0 compte
	 * @return un JETON (contenant un symbole X ou O ou x ou o et un boolean pour indiquer l ouverture
	 */
	public Jeton getCellule(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		return this.grille[ligne][colonne];
	}

	/**
	 * toString
	 * @return une chaine de caractère contenant l'etat de la grille
	 */
	public String toStringGrille() {
		String sGrille = "" ;
		int ligne = 0;

		// ligne des indices de colonnes
		for (int i = 1; i <= this.grille[0].length; ++i)
			sGrille += " " + " " + " " + i ;

		sGrille += "\n";
		++ligne;

		// il faut d'abord parcourir les reference de ligne de jeton pour acceder aux
		// jetons
		for (Jeton[] ligneJeton : grille) {
			sGrille += ligne;
			for (int i = 0; i < ligneJeton.length; i++) {
				sGrille += " " + ligneJeton[i].toString();
			}
//			for (Jeton cellule : ligneJeton)
//				sGrille = " " + cellule.toString();
			sGrille += "\n";
			++ligne;
		}
		return sGrille;
	}
	
	public void afficherGrille() {
		System.out.println(this.toStringGrille());
	}

	// methodes specifiques
	/**
	 * estVideCellule
	 * @param ligne de la cellule de la grille le 0 compte
	 * @param colonne de la cellule de la grille  le 0 compte
	 * @return la cellule est elle vide ?
	 */
	public boolean estVideCellule(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		return getCellule(ligne, colonne).estVideJeton();
	}
	
	/**
	 * viderGrille 
	 * permet de mettre tout les jetons de la grille a JETON_VIDE 
	 * ne pourra être appellee que si la grille contient des references nulles
	 */
	private void viderGrille() {
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = Jeton.JETON_VIDE;
			}
//			for (Jeton cellule : ligneJeton) {
//				cellule = Jeton.JETON_VIDE;
//			}
		}
	}

	/**
	 * estPleineGrille
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
		return estPleine ;
	}
	
	/**
	 * place un jeton dans la grille
	 * verifie que la cellule ciblé est vide
	 * @param jeton à placer (seuls JETON_X ou JETON_O sont autorisés)
	 * @param ligne de la cellule de la grille  le 0 compte
	 * @param colonne de la cellule de la grille  le 0 compte
	 */
	public void placerJeton(Jeton jeton, int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		assert (estVideCellule(ligne, colonne)); // la cellule doit etre vide
		assert (!jeton.estVideJeton() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
		this.grille[ligne][colonne] = jeton;
	}
	
	//remplissageGrille
	/**
	 * remplissage aléatoire avec JEONT caractère ouvert,  
	 * à partir d une liste de JETON finie de taille égale à celle de la grille
	 * tirage aléatoire sans remise de la liste de JETON dans chacune des cellules
	 * verifie que la grille est remplie de JETON vide au départ
	 * verifie qu'une victoire n a pas été formée, 
	 * 	si c'est le cas on change le symbole insére, 
	 * 	si cela forme une victoire, on laisse le jeton 
	 *  mais on va  modifier l autre accessible depuis une profondeur 1, 
	 *  on verifie si ce changement génère une victoire si oui on modifie jeton profondeur 2
	 * 		si au bout de x profondeur toujours une victoire
	 * 		on relance le remplissage du debut
	 */
	public void remplissageGrille() {
		
	}
	
	//permutationJeton
	/**
	 * permute deux jetons de la grille
	 * verifie que les les cellules sont rempli de JETON
	 * verifie que les deux JETONS a permuter sont ouverts 	 
	 * @param ligneJ1
	 * @param colonneJ1
	 * @param colonneJ1
	 * @param ligneJ2
	 */
	public void permutationJeton(int ligneJ1, int colonneJ1, int colonneJ2, int ligneJ2) {
		
	}
	
}
