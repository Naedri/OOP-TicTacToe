package oop.tictactoe.grille;

import java.util.EnumSet;

import oop.tictactoe.grille.Direction;
import oop.tictactoe.grille.Jeton;

public class Grille {

	private int lignes;
	private int colonnes;
	private Jeton[][] grille;

	// constructeur
	public Grille(int hauteur, int largeur) {
		this.colonnes = largeur;
		this.lignes = hauteur;
		this.grille = new Jeton[lignes][colonnes];
		this.viderGrille();
	}

	public Grille() {
		this.colonnes = 3;
		this.lignes = 3;
		this.grille = new Jeton[3][3]; //ce sont des reference squi s attendent � recevoir des jetons mais ils sont null au d�part
		this.viderGrille();
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
	
	//viderGrille
	private void viderGrille() {
		for (Jeton[] ligneJeton : this.grille) {
			for (Jeton cellule : ligneJeton) {
				cellule.viderJeton();
				// cellule = Jeton.JETON_VIDE; //ne fonctionne pas
			}
		}
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
	
	//remplissageGrille
	/**
	 * remplissage aléatoire avec caractère ouvert, 
	 * au bout d un nombre d insertion definie, à chaque insertion de nouveau jeton 
	 * controler si une forme de victoire vient d etre generee, 
	 * si c est le cas on change le symbole insérer, 
	 * si cela forme une victoire, on laisse le jeton 
	 * mais on va  modifier le jeton n-1 constituant le forme dans la linkedList, 
	 * on verifie si ce changement génère une victoire pour le joueur opposé, 
	 * si c est le cas on laisse le jeton dans sa forme initiale (avant verif) 
	 * et on passe au jeton n+1
	 */
	public void remplissageGrille() {
		
	}
	
	private void enleverJeton() {
		
	}
	
	
	
	
}
