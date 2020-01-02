package oop.tictactoe.grille;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import oop.tictactoe.grille.Jeton;

public class Grille implements In_Grille {

	private Jeton[][] grille;

	// constructeur
	public Grille(int nbrLignes, int nbrColonnes) {
		this.grille = new Jeton[nbrLignes][nbrColonnes];
		this.viderGrille(); //initialisation
	}
	public Grille(int nbrLignes, int nbrColonnes, boolean aleatoire) {
		this.grille = new Jeton[nbrLignes][nbrColonnes];
		if (aleatoire)
			this.remplirAleaGrille(); //initialisation
		else
			this.viderGrille();
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
	public int getNbrCellules() {
		return this.grille[0].length * this.grille.length ;
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
			sGrille += "\n";
			++ligne;
		}
		return sGrille;
	}
	
	/**
	 * 
	 */
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
	
	//remplissageGrille
	/**
	 * remplissage aléatoire avec JEONT caractère ouvert,  
	 * à partir d une liste de JETON finie de taille égale à celle de la grille
	 * tirage aléatoire sans remise de la liste de JETON dans chacune des cellules
	 */
	public void remplirAleaGrille() {
		//initialisation de la liste des jetons
		//comme le joueurX commence il a un avantage
		//ainsi si le nombre de jeton est impair il y aura un jeton O de plus
		LinkedList<Jeton> listeJetons= new LinkedList<Jeton>(); //Linked list car acces terminaux constant
		for (int i = 0; i < getNbrCellules(); ++i) {
			listeJetons.addLast(Jeton.values()[(i+1) %2 +1]);
		}
		
		//brassage de la liste des jetons
		//https://www.tutorialspoint.com/java/util/collections_shuffle.htm
		Collections.shuffle(listeJetons);
		
	    //insertion de la liste de jetons dans la grille
		//boucle for each pour la Linked list qui travaille difficilement avec des indices
		for (Jeton[] ligneJeton : this.grille) {
			for (int i = 0; i < ligneJeton.length; i++) {
				ligneJeton[i] = listeJetons.getFirst();
				listeJetons.removeFirst();	
			}
		}
		
//		//Ne fonctionne pas
//		for (Jeton[] ligneJeton : this.grille) {
//			for (Jeton jeton : ligneJeton) {
//				jeton = listeJetons.getFirst();
//				listeJetons.removeFirst();
//			}
//		}
	}
	
	/**
	 * comparaison de grille
	 * utilisable pour s assurer que des grilles generees aléatoirement sont differentes
	 * @param grille1 de jeton
	 * @param grilleCible de jeton
	 * @return true si grille1 comporte au moins un jeton different de grille2
	 */
	public boolean estEgale(Grille grilleCible) {
		
		//comparaison de taille
		if (this.getNbrCellules() != grilleCible.getNbrCellules()) {
			return false;
		}
		
		//comparaison des cellules
		for (int i=0;i < this.getLignes(); ++i) {
			for (int j = 0; j < this.getColonnes(); ++j) {
				Jeton jetonCible1 = this.getCellule(i, j);
				Jeton jetonCible2 = grilleCible.getCellule(i, j);
				if (! (jetonCible1.estEgal(jetonCible2))) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * place un jeton dans la grille
	 * verifie que la cellule ciblé est vide
	 * @param jeton à placer (seuls JETON_X ou JETON_O sont autorisés)
	 * @param ligneCible de la cellule de la grille  le 0 compte
	 * @param colonneCible de la cellule de la grille  le 0 compte
	 */
	public void placerJeton(Jeton jeton, int ligneCible, int colonneCible) {
		assert (ligneCible < this.grille.length && ligneCible >= 0); //la cellule doit être dans la grille
		assert (colonneCible < this.grille[0].length && colonneCible >= 0); //la cellule doit être dans la grille
		assert (estVideCellule(ligneCible, colonneCible)); // la cellule doit etre vide
		assert (!jeton.estVideJeton() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
		this.grille[ligneCible][colonneCible] = jeton;
	}
	
	public void placerJetonAdjacent(Jeton jeton, int ligneCible, int colonneCible) {
		assert (ligneCible < this.grille.length && ligneCible >= 0); //la cellule doit être dans la grille
		assert (colonneCible < this.grille[0].length && colonneCible >= 0); //la cellule doit être dans la grille
		assert (estVideCellule(ligneCible, colonneCible)); // la cellule doit etre vide
		assert (!jeton.estVideJeton() && jeton.estOuvert()); // le jeton place ne doit pas etre vide ni ferme
	
		assert(existeAdjacent(ligneCible, colonneCible));
		
		placerJeton(jeton,  ligneCible, colonneCible) ;

	}
	
	//permutationJeton
	/**
	 * permute deux jetons de la grille
	 * verifie que les deux jetons electionnes sont dans la grille
	 * verifie que les deux jetons sont adjacents
	 * verifie que les les cellules sont rempli de JETON
	 * verifie que les deux JETONS a permuter sont ouverts 	 
	 * @param ligneJ1
	 * @param colonneJ1
	 * @param colonneJ1
	 * @param ligneJ2
	 */
	public void permutationJeton(int ligneJ1, int colonneJ1, int colonneJ2, int ligneJ2) {
		assert(ligneJ1 != ligneJ2 && colonneJ1 != colonneJ2); //les jetons doivent etre differents

		assert (ligneJ1 < this.grille.length && ligneJ1 >= 0); //la cellule doit être dans la grille
		assert (colonneJ1 < this.grille[0].length && colonneJ1 >= 0); //la cellule doit être dans la grille
		assert (ligneJ2 < this.grille.length && ligneJ2 >= 0); //la cellule doit être dans la grille
		assert (colonneJ2 < this.grille[0].length && colonneJ2 >= 0); //la cellule doit être dans la grille
		
		assert(ligneJ1 != ligneJ2 && colonneJ1 != colonneJ2); //les jetons doivent etre differents
		assert(sontAdjacents(ligneJ1, colonneJ1, colonneJ2, ligneJ2)); //les jetons doivent etre adjacents
		
		assert (!estVideCellule(ligneJ1, colonneJ1)); // la cellule ne doit pas etre vide		
		assert (!estVideCellule(ligneJ2, colonneJ2)); // la cellule ne doit pas etre vide
		
		assert (getCellule(ligneJ1,colonneJ1).estOuvert() );//les jetons a permutter doivent être ouverts
		assert (getCellule(ligneJ2,colonneJ2).estOuvert() );//les jetons a permutter doivent être ouverts

		//si les jetons sont differents
		if (! getCellule(ligneJ1,colonneJ1).estEgal(getCellule(ligneJ2,colonneJ2)) ) {
			
			//insertion des clones dans la grille
			if ( getCellule(ligneJ1,colonneJ1).estEgal(Jeton.JETON_X)) {
				grille[ligneJ1][colonneJ1] = Jeton.JETON_O ;
				grille[ligneJ2][colonneJ2] = Jeton.JETON_X ;
			}
			if(getCellule(ligneJ1,colonneJ1).estEgal(Jeton.JETON_O)) {
				grille[ligneJ1][colonneJ1] = Jeton.JETON_X ;
				grille[ligneJ2][colonneJ2] = Jeton.JETON_O ;
			}
		}
	}


	//nextJeton avec Direction
	/**
	 * coordNextJeton permet de savoir quelles sont les coordonnes (ligne,colonne) du jeton image
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public int[] coordNextJeton(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = new int[2];
		int ligneCible = profondeur * direction.getDligne() + ligne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		coord[0]=ligneCible;
		coord[1]=colonneCible;
		return coord ;
	
	}


	/**
	 * Pour les elements donnes, existeNextCellule permet de savoir si la cellule image est comprise dans la grille
	 * Pas d indication de la nature du jeton
	 * cellule image cad :
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public boolean existeNextCellule(int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		
		boolean existe = false;
		
		int ligneCible = profondeur * direction.getDligne() + ligne ;
		int colonneCible =  profondeur * direction.getDcolonne() + colonne ;
		
		if(ligneCible < getLignes() && ligneCible >= 0 
				&& colonneCible < getColonnes() && colonneCible >= 0) {
			existe = true;
		}
		return existe;
	}


	/**
	 * getNextJeton permet d obtenir le jeton image 
	 * càd le jeton contenu dans la cellule 
	 * projetee depuis la cellule de la grille a ligne,colonne
	 * vers la direction donnee a la profondeur/distance donnee
	 * @param ligne
	 * @param colonne
	 * @param profondeur
	 * @param direction
	 * @return
	 */
	public Jeton getNextJeton (int ligne, int colonne, int profondeur, Direction direction) {
		assert (ligne < getLignes() && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < getColonnes() && colonne >= 0); //la cellule doit être dans la grille
		assert(existeNextCellule(ligne, colonne, profondeur, direction));
		
		int[] coord = coordNextJeton(ligne, colonne, profondeur, direction);
	
		return getCellule(coord[0], coord[1]);
	}
	
	/**
	 * existe il dans les cellules voisines de la cellule donnee [ligne,colonne]
	 * des jetons non vides
	 * la cellule peut etre vide mais doit etre dans la grille
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public boolean existeAdjacent(int ligne, int colonne) {
		assert (ligne < this.grille.length && ligne >= 0); //la cellule doit être dans la grille
		assert (colonne < this.grille[0].length && colonne >= 0); //la cellule doit être dans la grille
		
		for (Direction oneDirection : Direction.values())
			if (existeNextCellule(ligne, colonne, 1, oneDirection)) {
				if (! getNextJeton(ligne, colonne, 1, oneDirection).estVideJeton()) {
					return true;
				}
			}
		return false;
		
	}
	

	/**
	 * les cellules donnees sont elles adjacents
	 * doivent etre des ellules differentes
	 * @param ligneJ1
	 * @param colonneJ1
	 * @param colonneJ2
	 * @param ligneJ2
	 * @return
	 */
	public boolean sontAdjacents(int ligneJ1, int colonneJ1, int colonneJ2, int ligneJ2) {
		assert(ligneJ1 != ligneJ2 && colonneJ1 != colonneJ2); //les jetons doivent etre differents

		assert (ligneJ1 < this.grille.length && ligneJ1 >= 0); //la cellule doit être dans la grille
		assert (colonneJ1 < this.grille[0].length && colonneJ1 >= 0); //la cellule doit être dans la grille
		assert (ligneJ2 < this.grille.length && ligneJ2 >= 0); //la cellule doit être dans la grille
		assert (colonneJ2 < this.grille[0].length && colonneJ2 >= 0); //la cellule doit être dans la grille
		
		boolean adjacent = false ;
		
		if ( (Math.abs(ligneJ1-ligneJ2) <= 1) && (Math.abs(colonneJ1-colonneJ2) <= 1) )
			adjacent = true ;
		
		return adjacent;
	}

	//morpion
	public void fermerAlignementJetons(int ligne, int colonne, int profondeur) {
		
		
		for (Direction oneDirection : Direction.values()) {
			
			int[] coordCible = coordNextJeton(ligne, colonne, profondeur, oneDirection);

			int ligneCible = coordCible[0];
			int colonneCible = coordCible[1];
			
			if ( getCellule(ligneCible,colonneCible).estEgal(Jeton.JETON_X)) {
				grille[ligneCible][colonneCible] = Jeton.JETON_X_MIN ;
			}
			else {
				grille[ligneCible][colonneCible] = Jeton.JETON_O_MIN ;
			}
		}
		
	}
	
}
