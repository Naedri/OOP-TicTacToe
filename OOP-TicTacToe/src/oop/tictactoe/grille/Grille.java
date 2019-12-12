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
		this.grille = new Jeton[3][3];
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
	
	/**
	 * enleverJeton puis placerJeton dans la cellule qui vient d etre enlever à partir de la cellule destination puis enleverJeton dans la cellule source puis placerJeton dans la cellule source
	 */
	public void permutationJeton() {
		//enleverJeton
		//placerJeton
	}
	
	//jetonAdjacent
	public boolean existeAdjacent(int ligne, int colonne) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule doit etre vide
	    
		for (Direction direction : Direction.values()) {
	    	int cibleColonne = direction.getDcolonne() + colonne ;
			int cibleLigne =  direction.getDligne() + ligne ;
			if (cibleLigne <= this.lignes && cibleColonne <= this.colonnes) {
				Jeton cibleJeton = this.grille[cibleLigne][cibleColonne] ;
				if (!cibleJeton.estVide())
					return true ;
			}
	    }	    	
		return false ;
	}
	/**
	 * placer un jeton acolle aux autres existant
	 * @param jeton
	 * @param ligne
	 * @param colonne
	 */
	public void placerJetonAdjacent(Jeton jeton, int ligne, int colonne) {
		//assert (this.tour >=2 );
		assert (existeAdjacent(ligne,colonne));
		assert (estVideCellule(ligne, colonne)); // la cellule doit etre vide
		this.placerJeton(jeton, ligne, colonne);
	}
	
	
	/*
	 */
	public boolean existeCelluleMiroir(int ligneOrigine, int colonneOrigine, int ligneProjete, int colonneProjete ) {
		assert (ligneOrigine <= this.lignes && colonneOrigine <= this.colonnes);
		assert (ligneProjete <= this.lignes && colonneProjete <= this.colonnes);
				
		int ligneMiroir = (ligneOrigine - ligneProjete) + ligneOrigine ;
		int colonneMiroir =  (colonneOrigine - colonneProjete) + colonneOrigine;
		
		return (ligneMiroir <= this.lignes && colonneMiroir <= this.colonnes);
	}
	
	/**
	 * 
	 * @param ligneOrigine
	 * @param colonneOrigine
	 * @param ligneProjete
	 * @param colonneProjete
	 * @return
	 */
	public Jeton getCelluleMiroir(int ligneOrigine, int colonneOrigine, int ligneProjete, int colonneProjete ) {
		assert (ligneOrigine <= this.lignes && colonneOrigine <= this.colonnes);
		assert (ligneProjete <= this.lignes && colonneProjete <= this.colonnes);
				
		int ligneMiroir = (ligneOrigine - ligneProjete) + ligneOrigine ;
		int colonneMiroir =  (colonneOrigine - colonneProjete) + colonneOrigine;
		
		assert (ligneMiroir <= this.lignes && colonneMiroir <= this.colonnes); //on verifie que l on reste dans la grille
		return this.grille[ligneMiroir][colonneMiroir];
	}

	// alignementCellule
	/**
	 * 
	 * @param ligne de la cellule observée
	 * @param colonne de la cellule observée
	 * @param profondeur est le nombre de cellule observées au max qui sont alignées dans grille
	 * @param direction et direction opposée vers laquelle observer un alignement
	 * @return si un alignement a été trouvé
	 */
	public boolean alignementCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne <= this.lignes && colonne <= this.colonnes); //la cellule doit être dans la grille
		assert (!estVideCellule(ligne, colonne)); // la cellule evaluée ne doit pas etre vide
		assert(profondeur >= 2);
		
		//evalueJeton dont on evalue l implication dans un alignement avec d'autres jetons : jetonCible
		Jeton evalueJeton = this.getCellule(ligne,colonne);
		
		///evalueAligne ligne de jeton que le joueur souhaiterait avoir à partir de evalueJeton
		String evalueAligne =  "";
		for(int i = 1; i <= profondeur ; ++i) {	
			//evalueAligne ligne de jeton que le joueur souhaiterait avoir à partir de evalueJeton
			evalueAligne += evalueJeton.getJeton();
		}
		
		// cibleAligne ligne de jeton observé dans la direction donnée
		String cibleAligne =  "";
		int cibleColonne = 0;
		int cibleLigne = 0;
		
		//direction donnee
		int coeffProfondeur = 0;
		do {
			//cibleJeton jeton que l on ajoute à cibleLigne
			cibleColonne = coeffProfondeur*direction.getDcolonne() + colonne ;
			cibleLigne = coeffProfondeur*direction.getDligne() + ligne ;
			if (cibleLigne <= this.lignes && cibleColonne <= this.colonnes) {
				Jeton cibleJeton = this.grille[cibleLigne][cibleColonne] ;
				cibleAligne += cibleJeton.getJeton() ;
			}
			++coeffProfondeur;			
		} while (coeffProfondeur <= profondeur && cibleLigne <= this.lignes && cibleColonne <= this.colonnes);
		
		//direction oppposee
		direction = direction.inverser();
		coeffProfondeur = 1; //on ne souhaite pas rajouter le jeton central
		do {
			//cibleJeton jeton que l on ajoute à cibleLigne
			cibleColonne = coeffProfondeur*direction.getDcolonne() + colonne ;
			cibleLigne = coeffProfondeur*direction.getDligne() + ligne ;
			if (cibleLigne <= this.lignes && cibleColonne <= this.colonnes) {
				Jeton cibleJeton = this.grille[cibleLigne][cibleColonne] ;
				cibleAligne = cibleJeton.getJeton() + cibleAligne;
			}
			++coeffProfondeur;			
		} while (coeffProfondeur <= profondeur && cibleLigne <= this.lignes && cibleColonne <= this.colonnes);
		
		//comparaison des chaines
		if (cibleAligne.contains(evalueAligne))
			return true;
		else
			return false;

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
